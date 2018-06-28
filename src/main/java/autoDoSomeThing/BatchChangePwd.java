package autoDoSomeThing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cainiaolicai.RecordToFile;
import cainiaolicai.cnUser;
import util.HttpClientUtil;
import util.OperateOracle;
import util.SHA256Util;
import util.Utils;

public class BatchChangePwd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//取出需要修改密码的user
		OperateOracle op = new OperateOracle();
		List<cnUser> cnUsers = op.getChangePwdUsers(4);
		BatchChangePwd batchChangePwd = new BatchChangePwd();
		for (cnUser cnUser : cnUsers) {
			batchChangePwd.changePwdFlow(cnUser);
		}
	}
	
	public void changePwdFlow(cnUser user){
		if(user.getDeviceID()==null){
			user.setDeviceID(Utils.randomHexString(16));
		}
		if(user.getUser_agent()==null){
			 Random random = new Random();
		     int s = random.nextInt(Utils.user_agents.length);
		     user.setUser_agent(Utils.user_agents[s]);
		}
		if(user.getPassword()==null){
		     user.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");;
		}
		HttpClientUtil httpUtil = new HttpClientUtil();
		Map<String,String> para = new HashMap<String, String>();
		Random random = new Random();
		para.put("telephone", user.getTelephone());
		para.put("password", user.getPassword());
		
		httpUtil.setDeviceID(user.getDeviceID());
		httpUtil.setUser_agent(user.getUser_agent());
//		HttpHost target  = new HttpHost("192.168.1.4", 8888,  "http");
//		httpUtil.setTarget(target);
		//登录
		String login_res = httpUtil.doPost("http://app.cainiaolc.com/user/login", para, "utf-8");
		System.out.println("登录："+login_res);
		String  cnUserID = JSONObject.parseObject(login_res).get("Data").toString();
		httpUtil.setCnUserID(cnUserID);
		user.setCnuserID(cnUserID);
		//打開app
		para.clear();
		para.put("version", "1.1.8");
		para.put("w", "360");
		para.put("h", "640");
		String appOpen_res = httpUtil.doPost("http://app.cainiaolc.com/log/appOpen", para, "utf-8");
		System.out.println("打开app："+appOpen_res);
		//查看菜点
		String coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println("金币："+coin_userSumary);
		//修改密码
		para.clear();
		para.put("oldPwd", user.getPassword());
		String newRandomPwd = Utils.randomHexString(9);
		user.setRandomPwd(newRandomPwd);
		String sha256Pwd = SHA256Util.getSHA256StrJava(newRandomPwd);
		user.setPassword(sha256Pwd);
		para.put("newPwd", sha256Pwd);
		String changePwd_res = httpUtil.doPost("http://app.cainiaolc.com/user/changePwd", para, "utf-8");
		System.out.println("修改密码结果："+changePwd_res);
		String  changeStatu = JSONObject.parseObject(changePwd_res).get("Success").toString();
		if("true".equals(changeStatu)){
			OperateOracle operateOracle = new OperateOracle();
			if(!operateOracle.updatePwd(user)){
				RecordToFile.record(new String[] {"更新密码失败="+user.getTelephone()+":"+user.getRandomPwd()+"--"+user.getPassword()}, "changePwdError.txt");
			};
		}
	}

}

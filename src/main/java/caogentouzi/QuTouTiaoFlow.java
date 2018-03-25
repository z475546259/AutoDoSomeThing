package caogentouzi;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;

public class QuTouTiaoFlow {

	public static void main(String[] args) {
		QuTouTiaoFlow q = new QuTouTiaoFlow();
		// TODO Auto-generated method stub
		q.login();
	}
	
	public void login(){
		HttpClientUtil httpUtil = new HttpClientUtil();
		
//		httpUtil.setCnUserID(user.getCnuserID());
//		httpUtil.setDeviceID(user.getDeviceID());
//		httpUtil.setUser_agent(user.getUser_agent());
		//登录
		String login_res =httpUtil.doQuTouTiaoGet("http://api.1sapp.com/member/login?OSVersion=6.0.1&deviceCode=99000870807523&dtu=002&lat=29.49221&lon=106.5017&network=wifi&password=475546259&telephone=15923584508&time=1521883226159&tk=r7kEVXZP7tTciNzQrcjeLi-Aq_7BBrEJKONZFNyMc2wH&uuid=2fd378b4281b428386d23554c6a773e1&version=20710&versionName=2.7.10.0104.2205&sign=3ddbbb73a1a55b871aee348147382148", "UTF-8");
		System.out.println(login_res);
		JSONObject json1 = JSONObject.parseObject(login_res);
		String token = json1.getString("token");
		//绑定设备
		Map<String,String> para = new HashMap<String, String>();
		para.put("OSVersion", "6.0.1");
		para.put("app", "W3siZmlyc3RJbnN0YWxsVGltZSI6MTUxNDI4NjAzODQxOSwibGFzdFVwZGF0ZVRpbWUiOjE1MTc0MjQ4MTY2MDMsInBhY2thZ2VOYW1lIjoiY29tLnNzLmFuZHJvaWQuYXJ0aWNsZS5uZXdzIiwidmVyc2lvbkNvZGUiOjB9XQ==");
		para.put("deviceCode", "99000870807523");
		para.put("dtu", "002");
		para.put("lat", "29.492796");
		para.put("lon", "106.502235");
		para.put("network", "wifi");
 		para.put("time", "1521947170719");
		para.put("tk", "r7kEVXZP7tTciNzQrcjeLi-Aq_7BBrEJKONZFNyMc2wH");
		para.put("token", token);
		para.put("uuid", "2fd378b4281b428386d23554c6a773e1");
		para.put("version", "20710");
		para.put("versionName", "2.7.10.0104.2205");
		para.put("sign", "61e7c76b9954c656d681ceefcf6846af");
		String appInstall =httpUtil.doQuTouTiaoPost("http://api.1sapp.com/member/appInstall", para, "utf-8");
		System.out.println(appInstall);
	}

}

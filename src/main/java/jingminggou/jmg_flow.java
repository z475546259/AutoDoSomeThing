package jingminggou;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import util.HttpClientUtil;

public class jmg_flow {
	public static void main(String[] args) {
		jmg_flow aa =  new jmg_flow();
		aa.autoDo();
	}
	public void autoDo() {
		HttpClientUtil httpclient = new HttpClientUtil();
		Map<String,String> para = new HashMap<String, String>();
		para.put("password", "5b330cea7f858abe4da9b9350bdb2520");
		para.put("appversion", "401000");
		para.put("phone", "15923584508");
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		para.put("timestamp", sdf.format(date));
		para.put("uid", "16201050");
		para.put("type", "1");
		para.put("os", "Android");
		para.put("devcode", "8bcdc105-44f2-3d2e-91b3-d0dce0192bfa");
		//login
		String login_res = httpclient.doPost("http://sv.ismartgo.cn:29090/appsv2/app/login.do", para, "utf-8");
		System.out.println(login_res);
		//Ç©µ½
		para.clear();
		para.put("uid", "16201050");
		para.put("devcode", "8bcdc105-44f2-3d2e-91b3-d0dce0192bfa");
		para.put("appversion", "401000");
		para.put("t", Calendar.getInstance().getTimeInMillis()+"");
		String dailySign_res = httpclient.doPost("http://sv.ismartgo.cn:29090/appsv2/app/dailySign.do", para, "utf-8");
		System.out.println(dailySign_res);
	}
}

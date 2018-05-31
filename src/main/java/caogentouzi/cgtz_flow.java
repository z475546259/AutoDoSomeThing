package caogentouzi;

import java.util.HashMap;
import java.util.Map;

public class cgtz_flow {
	public static void main(String[] args) {
		cgtz_flow aa = new cgtz_flow();
		aa.autoDo();
	}
	public void autoDo(){
		HttpClientUtil_cgtz httpUtil = new HttpClientUtil_cgtz();
		Map<String,String> para = new HashMap<String, String>();
		para.put("req_from_mobile","1");
		para.put("userId","191800222447");
		para.put("user_id", "191800222447");
		para.put("token", "CvvNETJDoXob5u-QY0p6lUJTRQNYa3LeOqAMjlGiPeU");
		String res = httpUtil.doPost("https://h5.cgtz.com/cgtz-club/rest/score/do/task/sign.json", para, "utf-8");
		System.out.println(res);
	}
	
}

package cainiaolicai;

import util.HttpRequestUtil;

public class cnlc_login {
	public static void main(String[] args) {
		cnlc_login aa = new cnlc_login();
		aa.login();
	}
	public void login(){
		 String sr= HttpRequestUtil.sendPost("http://app.cainiaolc.com/user/login ","telephone=15923584508&password=d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b",true);
		 System.out.println(sr);
	}
	
}

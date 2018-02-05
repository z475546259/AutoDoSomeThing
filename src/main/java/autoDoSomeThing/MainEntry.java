package autoDoSomeThing;

import cainiaolicai.cnUser;
import cainiaolicai.cnlc_flow;
import jingminggou.jmg_flow;

public class MainEntry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("~~~~主函数运行了~~~");
		cnUser zzq1 = new cnUser();
		zzq1.setTelephone("15923584508");
		zzq1.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq1.setCnuserID("2568");
		zzq1.setDeviceID("31d2b13db676f532");
		zzq1.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
//		zzq1.setAccept_secret("8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("17320413743");//妈妈用的电信卡
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq2.setCnuserID("850152");
		zzq1.setDeviceID("5ad2b13ddfdsf354");
		zzq2.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 6.0.0; Redmi 4A MIUI/V8.5.3.0.MCCCNED)");
//		zzq2.setAccept_secret("8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
		cnUser zzq3 = new cnUser();
		zzq3.setTelephone("13512386223");//老婆手机号
		zzq3.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq3.setCnuserID("850649");
		zzq3.setDeviceID("db656fd41773b822");
		zzq3.setUser_agent("Dalvik/1.6.0 (Linux; U; Android 4.4.4; HM 2A MIUI/7.11.16)");
		cnUser zzq4 = new cnUser();
		zzq4.setTelephone("15310088377");//老婆公司手机号
		zzq4.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq4.setCnuserID("853034");
		zzq4.setDeviceID("39902e20922d0ec3");
		zzq4.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 5.1; HUAWEI TAG-AL00 Build/HUAWEITAG-AL00)");
		cnUser zzq5 = new cnUser();
		zzq5.setTelephone("13512376362");//妈妈移动手机号
		zzq5.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq5.setCnuserID("854372");
		zzq5.setDeviceID("cd667fd538874d833");
		zzq5.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 5.1; HUAWEI TAG-AL00 Build/HUAWEITAG-AL00)");
		cnUser zzq6 = new cnUser();
		zzq6.setTelephone("15823270454");//爸爸移动手机号
		zzq6.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq6.setCnuserID("858732");
		zzq6.setDeviceID("ef778fd649985f944");
		zzq6.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 5.0; HUAWEI TAG-AL00 Build/HUAWEITAG-AL00)");
		cnUser zzq7 = new cnUser();
		zzq7.setTelephone("18108303047");//我用的电信手机号
		zzq7.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq7.setCnuserID("879386");
		zzq7.setDeviceID("776b965a7eba92d2");
		zzq7.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 6.0.1; OPPO R9sk Build/MMB29M)");
		cnUser zzq8 = new cnUser();
		zzq8.setTelephone("18723618976");//刘刚手机号
		zzq8.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq8.setCnuserID("872971");
		zzq8.setDeviceID("887b965a7eba93e3");
		zzq8.setUser_agent("Dalvik/2.1.0 (Linux; U; Android 6.0.1; OPPO R9sk Build/MMB29M)");
		
		
		cnlc_flow flow =  new cnlc_flow();
		flow.autoDo(zzq1);
		flow.autoDo(zzq2);
		flow.autoDo(zzq3);
		flow.autoDo(zzq4);
		flow.autoDo(zzq5);
		flow.autoDo(zzq6);
		flow.autoDo(zzq7);
		flow.autoDo(zzq8);//精明购的自动签到
		jmg_flow aa =  new jmg_flow();
		aa.autoDo();
		System.out.println("~~~~主函数完了~~~");
	}

}

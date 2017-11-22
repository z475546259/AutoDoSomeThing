package autoDoSomeThing;

import cainiaolicai.cnUser;
import cainiaolicai.cnlc_flow;

public class MainEntry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("~~~~主函数运行了~~~");
		cnUser zzq1 = new cnUser();
		zzq1.setTelephone("15923584508");
		zzq1.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq1.setCnuserID("2568");
		zzq1.setDeviceID("31d2b13db676f532");
//		zzq1.setAccept_secret("8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("17320413743");
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq2.setCnuserID("850152");
		zzq1.setDeviceID("5ad2b13ddfdsf354");
//		zzq2.setAccept_secret("8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
		cnlc_flow flow =  new cnlc_flow();
		flow.autoDo(zzq1);
		flow.autoDo(zzq2);
		System.out.println("~~~~主函数完了~~~");
	}

}

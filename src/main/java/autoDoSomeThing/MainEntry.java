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
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("17320413743");
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		cnlc_flow flow =  new cnlc_flow();
		flow.autoDo(zzq1);
		flow.autoDo(zzq2);
		System.out.println("~~~~主函数完了~~~");
	}

}

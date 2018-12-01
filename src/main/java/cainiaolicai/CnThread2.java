package cainiaolicai;

import com.zzq.springcloud.entities.cnUser;

public class CnThread2 implements Runnable{
	public cnUser user;

	public cnUser getUser() {
		return user;
	}

	public void setUser(cnUser user) {
		this.user = user;
	}


	public CnThread2() {
		super();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			cnlc_flow2 flow = new cnlc_flow2();
			flow.autoDo();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("线程报错了，捕获："+e.getMessage());
			RecordToFile.record(new String[]{"线程报错了，捕获："+e.getMessage(),e.getMessage()}, "error-log.txt",true);

		}
//		Thread.currentThread().notifyAll();
	}
	
	

}

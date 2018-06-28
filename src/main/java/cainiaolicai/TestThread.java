package cainiaolicai;

import java.util.Random;

public class TestThread implements Runnable{
	public cnUser user;

	public cnUser getUser() {
		return user;
	}

	public void setUser(cnUser user) {
		this.user = user;
	}
	
	public TestThread(cnUser user) {
		super();
		this.user = user;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		cnlc_flow flow = new cnlc_flow();
//		flow.autoDo(user);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("线程名："+Thread.currentThread().getName()+",打印随机数："+new Random());
	}
	
	

}

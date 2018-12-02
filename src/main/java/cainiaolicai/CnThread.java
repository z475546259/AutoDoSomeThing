package cainiaolicai;

import com.zzq.springcloud.entities.cnUser;

public class CnThread implements Runnable{
	private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CnThread.class);
	public cnUser user;

	public cnUser getUser() {
		return user;
	}

	public void setUser(cnUser user) {
		this.user = user;
	}
	
	public CnThread(cnUser user) {
		super();
		this.user = user;
	}
	public CnThread() {
		super();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			cnlc_flow flow = new cnlc_flow();
			flow.autoDo(user);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("线程报错了，捕获："+e.getMessage());
			LOGGER.error(e);
		}
//		Thread.currentThread().notifyAll();
	}
	
	

}

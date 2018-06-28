package cainiaolicai;

public class CnThread implements Runnable{
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		cnlc_flow flow = new cnlc_flow();
		flow.autoDo(user);
//		Thread.currentThread().notifyAll();
	}
	
	

}

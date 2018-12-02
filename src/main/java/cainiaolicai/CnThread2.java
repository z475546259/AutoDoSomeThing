package cainiaolicai;

import com.zzq.springcloud.entities.cnUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CnThread2 implements Runnable{
	private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CnThread2.class);

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
	Logger logger = LoggerFactory.getLogger(CnThread2.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			cnlc_flow2 flow = new cnlc_flow2();
			flow.autoDo();
		} catch (Exception e) {
			// TODO: handle exception
			RecordToFile.record(new String[]{"线程报错了，捕获："+e.getMessage(),e.getMessage()+e.toString()}, "error-log.txt",true);
			LOGGER.error("线程报错了，捕获：",e);
		}
//		Thread.currentThread().notifyAll();
	}
	
	

}

package autoDoSomeThing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cainiaolicai.ListDom4J;
import cainiaolicai.RecordToFile;
import cainiaolicai.cnUser;
import cainiaolicai.cnlc_flow;
import jingminggou.jmg_flow;
import util.OperateOracle;

public class MainEntry {

	public static void main(String[] args) {
		System.out.println("~~~~主函数运行了~~~");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		Long beginLongTime = c.getTimeInMillis();
		String beginTime = sdf.format(c.getTime());
		RecordToFile.record(new String[] {"菜鸟理财开始时间="+beginTime}, "countTime.txt");
		
		cnlc_flow flow =  new cnlc_flow();
//		List<cnUser> cnUsers = ListDom4J.turnDomtoCnUsers();
		OperateOracle op = new OperateOracle();
		List<cnUser> cnUsers = op.getCnUsers();
		for (cnUser cnUser : cnUsers) {
			try {
				flow.autoDo(cnUser);
			} catch (Exception e) {
				e.printStackTrace();
				RecordToFile.record(new String[] {"异常信息:("+cnUser.getUser_name()+cnUser.getTelephone()+")"+e.toString()}, "countTime.txt");
				continue;
			}
			
		}
	
		Calendar c2 = Calendar.getInstance();
		Long endLongTime = c2.getTimeInMillis();
		String endTime = sdf.format(c2.getTime());
		RecordToFile.record(new String[] {"菜鸟理财结束时间="+endTime}, "countTime.txt");
		RecordToFile.record(new String[] {"----菜鸟理财本次用时="+(endLongTime-beginLongTime)/1000/60+"分钟"}, "countTime.txt");
		//精明购的自动签到
		jmg_flow aa =  new jmg_flow();
		aa.autoDo();
		System.out.println("~~~~主函数完了~~~");
	}
}

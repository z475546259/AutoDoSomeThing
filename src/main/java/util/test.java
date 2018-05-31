package util;

import cainiaolicai.cnUser;

public class test {
	public static void main(String[] args) {
		OperateOracle operateOracle = new OperateOracle();
//		operateOracle.AddData(1,"菜鸟理财");
		cnUser user = new cnUser();
		user.setUser_name("张治强");
		user.setTelephone("15923584508");
		operateOracle.updateAppData("菜鸟理财",user);
	}
}

package util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class test {
	public static void main(String[] args) {
		OperateOracle operateOracle = new OperateOracle();
//		operateOracle.AddData(1,"菜鸟理财");
		operateOracle.updateAppData("菜鸟理财","张治强","15923584508","","475546259",200,"16284");
	}
}

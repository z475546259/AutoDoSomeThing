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
		operateOracle.updateAppData("菜鸟理财","易码13","15584451604","","d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b",100,"16284");
	}
}

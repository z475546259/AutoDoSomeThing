package autoDoSomeThing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;
import util.ThreadPoolManager;

public class Test {
    //默认输出路径
    private static Logger LOGGER = Logger.getLogger(Test.class);
	public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("erro",e);
            LOGGER.info("info",e);
            LOGGER.debug("debug",e);
            LOGGER.warn("warn",e);
        }
	}
    public static void test() throws Exception {
	    throw new Exception();
    }
}

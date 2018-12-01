//package autoDoSomeThing;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//import util.HttpClientUtil;
//import util.ThreadPoolManager;
//
//public class Test {
//	 private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
//	 private  ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance("threadPoolManager");
//	public static void main(String[] args) {
//		HttpClientUtil httpUtil = new HttpClientUtil();
//		//获取别人发帖内容列表
//		String article_list= httpUtil.doGet("http://app.cainiaolc.com/forum/recommends?page=0&perpage=1000", "utf-8");
//		JSONObject json1 = JSONObject.parseObject(article_list);
//		JSONArray data = json1.getJSONArray("Data");
//		System.out.println(data.size());
//		int[] cates_keys = {225410,225411,225412,225413,240756,467782};
//		Map<Integer,String> cates = new HashMap<>();
//		cates.put(225410, "p2p");
//		cates.put(225411, "fund");
//		cates.put(225412, "insurance");
//		cates.put(225413, "bank");
//		cates.put(240756, "ls");
//		cates.put(467782, "jjzt");
//		Random random = new Random();
//		System.out.println(cates_keys[random.nextInt(cates_keys.length)]);
//		List<String> contentList = new ArrayList<>();
//		List<String> contentIDs = new ArrayList<>();
//		for (Object article : data) {
//			JSONObject article_json = (JSONObject) article;
//			if(!article_json.get("content").toString().contains("...")){
//				contentList.add(article_json.get("content").toString());
//			}
//			if(Integer.parseInt(article_json.get("commentNum").toString())>3){
//				if(!(article_json.get("authorInfo").equals("菜导")||article_json.get("cateLabel").equals("活捉菜导"))){
//					contentIDs.add(article_json.get("id").toString());
//				}
//			}
//		}
//		System.out.println("contentList大小=="+contentList.size());
//		System.out.println("contentIDs大小=="+contentIDs.size());
//	}
//
//}

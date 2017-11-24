package cainiaolicai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;

public class cnlc_flow {
	public static void main(String[] args) {
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("17320413743");
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq2.setCnuserID("850152");
		cnlc_flow flow =  new cnlc_flow();
		flow.autoDo(zzq2);
//		HttpClientUtil aa =  new HttpClientUtil();
//		System.out.println(aa.doFormPost("http://app.cainiaolc.com/forum/post", new HashMap<String, String>(), "GB2312"));
	}
	public void autoDo(cnUser user){
		HttpClientUtil httpUtil = new HttpClientUtil();
		Map<String,String> para = new HashMap<String, String>();
		para.put("telephone", user.getTelephone());
		para.put("password", user.getPassword());
		httpUtil.setCnUserID(user.getCnuserID());
		httpUtil.setDeviceID(user.getDeviceID());
		//登录
		String login_res = httpUtil.doPost("http://app.cainiaolc.com/user/login", para, "utf-8");
		System.out.println("登录："+login_res);
		//查看菜点
		String coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println("金币："+coin_userSumary);
		//进入APP
		para.clear();
		para.put("version", "1.1.8");
		para.put("w", "360");
		para.put("h", "640");
		String appOpen = httpUtil.doPost("http://app.cainiaolc.com/log/appOpen", para, "utf-8");
		System.out.println("进入APP："+appOpen);
		//绑定token
		para.clear();
		para.put("os", "android");
		para.put("token", "");
		para.put("version", "");
		para.put("regId", "190e35f7e07b8825172");
		String bindToken = httpUtil.doPost("http://app.cainiaolc.com/account/bindToken", para, "utf-8");
		System.out.println("绑定token："+bindToken);
		//获取用户信息
		String user_info = httpUtil.doGet("http://app.cainiaolc.com/user/info", "utf-8");
		System.out.println(user_info);
		//打标历史
		String tag_history = httpUtil.doGet("http://app.cainiaolc.com/user/tagHistory", "utf-8");
		System.out.println(tag_history);
		//主页
		String api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
		System.out.println(api_homeData);
		List<String> ids = new ArrayList<String>();
		getIDs(api_homeData,ids);
		
        for (String id : ids) {
        	//查看文章
        	String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/detailSimple?id="+id, "utf-8");
    		System.out.println(article_detailSimple);
    		String coin_userSumary2 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
    		System.out.println(coin_userSumary2);
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        //发帖
//        String forum_content = "你们说一个人的资金大概分散投资到多少个平台合适？";
//        Map<String,String> forum_para = new HashMap<String, String>();
//        try {
////			forum_para.put("content-disposition: form-data; name=\"content\"\r\n" + 
////					"Content-Length: "+forum_content.getBytes("utf-8").length, forum_content);
////			forum_para.put("content-disposition: form-data; name=\"category\"\r\n" + 
////		        		"Content-Length: 3", "p2p");
////		    forum_para.put("content-disposition: form-data; name=\"cateId\"\r\n" + 
////		    		"Content-Length: 6", "225410");
////		    forum_para.put("content-disposition: form-data; name=\"upload\"\r\n" + 
////		    		"Content-Length: 1", "0");
//		    forum_para.put("content", forum_content);
//			forum_para.put("category", "p2p");
//		    forum_para.put("cateId", "225410");
//		    forum_para.put("upload", "0");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       
//        String forum_post = httpUtil.doFormPost("http://app.cainiaolc.com/forum/post", forum_para, "utf-8");
//		System.out.println(forum_post);
//		String coin_userSumary3 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//		System.out.println(coin_userSumary3);
		//收藏文章
		int randomId = (new Random()).nextInt(ids.size());
		String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/favor?id="+ids.get(randomId)+"&status=1", "utf-8");
		System.out.println(article_detailSimple);
		String coin_userSumary4 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println(coin_userSumary4);
		//分享文章
//		for(int i=0;i<2;i++) {
////			int shareRandomId = (new Random()).nextInt(ids.size());
//			Map<String,String> share = new HashMap<String, String>();
////			System.out.println(ids.get(shareRandomId));
//			share.put("path", "/articleShare");
//			share.put("referer", "/article/"+"659091");
//			String share_result1 = httpUtil.doPost("http://app.cainiaolc.com/log/menuClick", para, "utf-8");
//			share.clear();
//			share.put("fk", "659091");
//			share.put("type", "7");
//			String share_result2 = httpUtil.doPost("http://app.cainiaolc.com/log/articleShare", para, "utf-8");
//			System.out.println(share_result1 + " and "+share_result2);	
//			String coin_userSumary5 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//			System.out.println(coin_userSumary5);
//		}
		
	}
	public void getIDs(String api_homeData,List<String> ids) {
		JSONObject json1 = JSONObject.parseObject(api_homeData);
		JSONObject json1_data = (JSONObject)json1.get("Data");
		JSONArray  articles = json1_data.getJSONArray("articles");
//		JSONArray  carousels = json1_data.getJSONArray("carousels");
//		JSONArray  posts = json1_data.getJSONArray("posts");
//		JSONArray  refers = json1_data.getJSONArray("refers");
		getJsonID(articles, ids);
//		getJsonID(carousels, ids);
//		getJsonID(posts, ids);
//		getJsonID(refers, ids);
	}
	
	public void getJsonID(JSONArray  jsonArray,List<String> ids) {
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) { 
        	JSONObject job = (JSONObject) iterator.next(); 
        	ids.add(job.get("id").toString());
        }
	}
	
}

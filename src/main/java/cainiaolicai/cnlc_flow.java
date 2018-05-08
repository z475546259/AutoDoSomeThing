package cainiaolicai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpHost;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import util.HttpClientUtil;
import util.OperateOracle;
import util.Utils;

public class cnlc_flow {
	public static void main(String[] args) {
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("15046351474");
		zzq2.setUser_name("易码28");
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq2.setDeviceID(Utils.randomHexString(16));
		 Random random = new Random();
	     int s = random.nextInt(Utils.user_agents.length);
	     zzq2.setUser_agent(Utils.user_agents[s]);
		cnlc_flow flow =  new cnlc_flow();
		flow.autoDo(zzq2);
//		HttpClientUtil aa =  new HttpClientUtil();
//		System.out.println(aa.doFormPost("http://app.cainiaolc.com/forum/post", new HashMap<String, String>(), "GB2312"));
	}
	public void autoDo(cnUser user){
		HttpClientUtil httpUtil = new HttpClientUtil();
		Map<String,String> para = new HashMap<String, String>();
		Random random = new Random();
		para.put("telephone", user.getTelephone());
		para.put("password", user.getPassword());
		
		httpUtil.setDeviceID(user.getDeviceID());
		httpUtil.setUser_agent(user.getUser_agent());
//		HttpHost target  = new HttpHost("192.168.1.4", 8888,  "http");
//		httpUtil.setTarget(target);
		//登录
		String login_res = httpUtil.doPost("http://app.cainiaolc.com/user/login", para, "utf-8");
		System.out.println("登录："+login_res);
		String  cnUserID = JSONObject.parseObject(login_res).get("Data").toString();
		httpUtil.setCnUserID(cnUserID);
		user.setCnuserID(cnUserID);
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
		System.out.println(user.getTelephone()+"的用户信息"+user_info);
		//打标历史
		String tag_history = httpUtil.doGet("http://app.cainiaolc.com/user/tagHistory", "utf-8");
//		System.out.println(tag_history);
		//主页
		String api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
		System.out.println(api_homeData);
		List<String> ids = new ArrayList<String>();
		getIDs(api_homeData,ids);

		for (String id : ids) {
			//查看文章
			String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/detailSimple?id="+id, "utf-8");
//    		System.out.println(article_detailSimple);
			String coin_userSumary2 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//    		System.out.println(coin_userSumary2);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//收藏文章
		int randomId = (new Random()).nextInt(ids.size());
		String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/favor?id="+ids.get(randomId)+"&status=1", "utf-8");
		System.out.println(article_detailSimple);
		String coin_userSumary4 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println(coin_userSumary4);
		//分享文章
		int first_id = 0;
		for(int i=0;i<2;i++) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//主页
			api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
			System.out.println("为了分享访问主页"+api_homeData);
			int shareRandomId = random.nextInt(ids.size());
			while(shareRandomId==first_id){
				shareRandomId = random.nextInt(ids.size());
			}
			first_id = shareRandomId;
			String article_detailSimple2 = httpUtil.doGet("http://app.cainiaolc.com/article/detailSimple?id="+ids.get(shareRandomId), "utf-8");
			System.out.println(article_detailSimple2);
			Map<String,String> share = new HashMap<String, String>();
//      			System.out.println(ids.get(shareRandomId));
			share.put("path", "/articleShare");
			share.put("referer", "/article/"+ids.get(shareRandomId));
			String share_result1 = httpUtil.doPost("http://app.cainiaolc.com/log/menuClick", share, "utf-8");
			share.clear();
			share.put("fk", ids.get(shareRandomId));
			share.put("type", "7");
			String share_result2 = httpUtil.doPost("http://app.cainiaolc.com/log/articleShare", share, "utf-8");
			System.out.println("第"+i+"次随机分享的文章ID是："+ ids.get(shareRandomId)+ " and "+share_result2);
			String coin_userSumary5 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			System.out.println(coin_userSumary5);
			
		}
		//获取别人发帖内容列表
		String article_list= httpUtil.doGet("http://app.cainiaolc.com/forum/list?page=1&perpage=1000&cate=225410&order=time", "utf-8");
		JSONObject json1 = JSONObject.parseObject(article_list);
		JSONArray  articles = json1.getJSONArray("Data");
		List<String> contentList = new ArrayList<String>();
		List<String> contentIDs =  new ArrayList<String>();
		for (Object article : articles) {
			JSONObject article_json = (JSONObject) article;
			if(!article_json.get("content").toString().contains("...")){
				contentList.add(article_json.get("content").toString());
			}
			if(Integer.parseInt(article_json.get("commentNum").toString())>3){
				contentIDs.add(article_json.get("id").toString());
			}
		}
		System.out.println(contentList.size()+"-----"+contentIDs.size());
//        发帖
		String forum_content = contentList.get(random.nextInt(contentList.size()));
		System.out.println("随机剽窃的文章=="+forum_content);
		Map<String,String> forum_para = new HashMap<String, String>();
		try {
			forum_para.put("content", forum_content);
			forum_para.put("category", "p2p");
			forum_para.put("cateId", "225410");
			forum_para.put("upload", "0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String forum_post = httpUtil.doFormPost("http://app.cainiaolc.com/forum/post", forum_para, "utf-8");
		System.out.println("发帖后返回内容=="+forum_post);
		httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//		String coin_userSumary3 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//		System.out.println(coin_userSumary3);
		//获取随机帖子的回复列表
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int contentID  =  random.nextInt(contentIDs.size());
			Map<String,String> aclist = new HashMap<String, String>();
			aclist.put("id", contentIDs.get(contentID));
			aclist.put("perpage", "100");
			aclist.put("page", "0");
			String content_comments = httpUtil.doPost("http://app.cainiaolc.com/forum/aclist", aclist, "utf-8");
			JSONObject content_comments_JSN = JSONObject.parseObject(content_comments);
			JSONArray  comments_jsn = content_comments_JSN.getJSONArray("Data");
			List<String> comments = new ArrayList<String>();
			for (Object comment : comments_jsn) {
				JSONObject comment_json = (JSONObject) comment;
				comments.add(comment_json.getString("content"));
			}
			//随机获取一个评论
			String comment = comments.get(random.nextInt(comments.size()));
			int exceptionTimes = 0;
			while(comment.contains("谢谢分享")) {
				comment = comments.get(random.nextInt(comments.size()));
				if(exceptionTimes>3) {
					comment = "特殊情况，凡事要辩证的看";
				}
				exceptionTimes++;
			}
			System.out.println("随机获取的评论内容=="+comment);
			//回帖
			Map<String,String> sub_comment = new HashMap<String, String>();
			sub_comment.put("id", contentIDs.get(contentID));
			sub_comment.put("cid", "");
			sub_comment.put("refid", "");
			sub_comment.put("content", comment);
			String re_comment = httpUtil.doPost("http://app.cainiaolc.com/forum/comment", sub_comment, "utf-8");
			System.out.println("回帖后的状态=="+re_comment);
			String detail = httpUtil.doPost("http://app.cainiaolc.com/forum/detail", sub_comment, "utf-8");
			System.out.println("回帖后查看状态=="+detail);
			httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			
		}
		String replayCommentresult = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		JSONObject finaJson = JSONObject.parseObject(replayCommentresult);
		Integer score = finaJson.getJSONObject("Data").getInteger("score");
		System.out.println("流程完毕后最后的结果==="+score);
		OperateOracle operateOracle = new OperateOracle();
//		operateOracle.updateAppData("菜鸟理财",user.getUser_name(),user.getTelephone(),"",user.getPassword(),score,user.getCnuserID());
		operateOracle.updateAppData("菜鸟理财",user,score);
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
	public List<String> getHistoryList(JSONArray  articles){
		List<String> contentList = new ArrayList<String>();
		for (Object article : articles) {
			JSONObject article_json = (JSONObject) article;
			if(!article_json.get("content").toString().contains("...")){
				contentList.add(article_json.get("content").toString());
			}
		}
		return contentList;
	}


	public List<String> getHistoryListWithCommentNum(JSONArray  articles){
		List<String> contentIDs = new ArrayList<String>();
		for (Object article : articles) {
			JSONObject article_json = (JSONObject) article;
			if(Integer.parseInt(article_json.get("commentNum").toString())>2){
				contentIDs.add(article_json.get("id").toString());
			}
		}
		return contentIDs;
	}
}

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

public class cnlc_flow {
	public static void main(String[] args) {
		cnUser zzq2 = new cnUser();
		zzq2.setTelephone("13922962046");
		zzq2.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");
		zzq2.setCnuserID("922404");
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
		httpUtil.setCnUserID(user.getCnuserID());
		httpUtil.setDeviceID(user.getDeviceID());
		httpUtil.setUser_agent(user.getUser_agent());
//		HttpHost target  = new HttpHost("192.168.1.4", 8888,  "http");
//		httpUtil.setTarget(target);
		//��¼
		String login_res = httpUtil.doPost("http://app.cainiaolc.com/user/login", para, "utf-8");
		System.out.println("��¼��"+login_res);
		//�鿴�˵�
		String coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println("��ң�"+coin_userSumary);
		//����APP
		para.clear();
		para.put("version", "1.1.8");
		para.put("w", "360");
		para.put("h", "640");
		String appOpen = httpUtil.doPost("http://app.cainiaolc.com/log/appOpen", para, "utf-8");
		System.out.println("����APP��"+appOpen);
		//��token
		para.clear();
		para.put("os", "android");
		para.put("token", "");
		para.put("version", "");
		para.put("regId", "190e35f7e07b8825172");
		String bindToken = httpUtil.doPost("http://app.cainiaolc.com/account/bindToken", para, "utf-8");
		System.out.println("��token��"+bindToken);
		//��ȡ�û���Ϣ
		String user_info = httpUtil.doGet("http://app.cainiaolc.com/user/info", "utf-8");
		System.out.println(user_info);
		//�����ʷ
		String tag_history = httpUtil.doGet("http://app.cainiaolc.com/user/tagHistory", "utf-8");
		System.out.println(tag_history);
		//��ҳ
		String api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
		System.out.println(api_homeData);
		List<String> ids = new ArrayList<String>();
		getIDs(api_homeData,ids);
		
        for (String id : ids) {
        	//�鿴����
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
        //��ȡ���˷��������б�
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
//        ����
        String forum_content = contentList.get(random.nextInt(contentList.size()));
        System.out.println("������Ե�����=="+forum_content);
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
       
        String forum_post = httpUtil.doFormPost("http://app.cainiaolc.com/forum/post", forum_para, "utf-8");
		System.out.println("�����󷵻�����=="+forum_post);
//		String coin_userSumary3 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//		System.out.println(coin_userSumary3);
		//��ȡ������ӵĻظ��б�
		for (int i = 0; i < 2; i++) {
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
			//�����ȡһ������
			String comment = comments.get(random.nextInt(comments.size()));
			System.out.println("�����ȡ����������=="+comment);
			//����
			Map<String,String> sub_comment = new HashMap<String, String>();
			sub_comment.put("id", contentIDs.get(contentID));
			sub_comment.put("cid", "");
			sub_comment.put("refid", "");
			sub_comment.put("content", comment);
			String re_comment = httpUtil.doPost("http://app.cainiaolc.com/forum/comment", sub_comment, "utf-8");
			System.out.println("�������״̬=="+re_comment);
		}
		//�ղ�����
		int randomId = (new Random()).nextInt(ids.size());
		String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/favor?id="+ids.get(randomId)+"&status=1", "utf-8");
		System.out.println(article_detailSimple);
		String coin_userSumary4 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
		System.out.println(coin_userSumary4);
		//��������
		int first_id = 0;
		for(int i=0;i<2;i++) {
			//��ҳ
			api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
			System.out.println("Ϊ�˷��������ҳ"+api_homeData);
			int shareRandomId = random.nextInt(ids.size());
			while(shareRandomId==first_id){
				shareRandomId = random.nextInt(ids.size());
			}
			first_id = shareRandomId;
			String article_detailSimple2 = httpUtil.doGet("http://app.cainiaolc.com/article/detailSimple?id="+ids.get(shareRandomId), "utf-8");
			System.out.println(article_detailSimple2);
			Map<String,String> share = new HashMap<String, String>();
//			System.out.println(ids.get(shareRandomId));
			share.put("path", "/articleShare");
			share.put("referer", "/article/"+ids.get(shareRandomId));
			String share_result1 = httpUtil.doPost("http://app.cainiaolc.com/log/menuClick", share, "utf-8");
			share.clear();
			share.put("fk", ids.get(shareRandomId));
			share.put("type", "7");
			String share_result2 = httpUtil.doPost("http://app.cainiaolc.com/log/articleShare", share, "utf-8");
			System.out.println("��"+i+"��������������ID�ǣ�"+ ids.get(shareRandomId)+ " and "+share_result2);	
			String coin_userSumary5 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			System.out.println(coin_userSumary5);
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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

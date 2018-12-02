package cainiaolicai;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzq.springcloud.entities.cnUser;
import util.*;

import java.util.*;

public class cnlc_flow2 {
	private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(cnlc_flow2.class);
	public static void main(String[] args) {
		cnlc_flow2 flow =  new cnlc_flow2();
		flow.autoDo();
	}
	public void autoDo(){
		cnUser user =  new cnUser();
		//从消息队列获取user
		user = MQManager.MQConsumerQueueReceiveCnUser();
		while(user!=null){
			if(user.getDeviceID()==null||"".equalsIgnoreCase(user.getDeviceID())){
				user.setDeviceID(Utils.randomHexString(16));
			}
			if(user.getUser_agent()==null||"".equalsIgnoreCase(user.getUser_agent())){
				Random random = new Random();
				int s = random.nextInt(Utils.user_agents.length);
				user.setUser_agent(Utils.user_agents[s]);
			}
			if(user.getPassword()==null||"".equalsIgnoreCase(user.getPassword())){
				user.setPassword("d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b");;
			}
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
			LOGGER.info(user.getTelephone()+"登录："+login_res);
			String  cnUserID = JSONObject.parseObject(login_res).get("Data").toString();
			httpUtil.setCnUserID(cnUserID);
			user.setCnuserID(cnUserID);
			//打開app
			para.clear();
			para.put("version", "1.1.8");
			para.put("w", "360");
			para.put("h", "640");
			String appOpen_res = httpUtil.doPost("http://app.cainiaolc.com/log/appOpen", para, "utf-8");
			System.out.println("打开app："+appOpen_res);
			//查看菜点
			String coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			System.out.println("金币："+coin_userSumary);
			Integer score =  JSONObject.parseObject(coin_userSumary).getJSONObject("Data").getInteger("score");
			Integer beginScore =  user.getScore();
			System.out.println("登录后的奖励是=="+(score-user.getScore()));
			user.setScore(score);
			//进入APP
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
			JSONObject userJson = JSONObject.parseObject(user_info);
			String codeMine = userJson.getJSONObject("Data").getString("codeMine");
			user.setCodeMine(codeMine);
			System.out.println(user.getTelephone()+"的用户信息"+user_info);
			//打标历史
			httpUtil.doGet("http://app.cainiaolc.com/user/tagHistory", "utf-8");
			//主页
			String api_homeData = httpUtil.doGet("http://app.cainiaolc.com/api/homeData", "utf-8");
			System.out.println(api_homeData);
			List<String> ids = new ArrayList<String>();
			getIDs(api_homeData,ids);
			int count_article = 0;
			for (String id : ids) {
				if(count_article>15) {break;}
				count_article++;
				//查看文章
				httpUtil.doGet("http://app.cainiaolc.com/article/detailSimple?id="+id, "utf-8");
				//httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
				try {
					Thread.sleep(12000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//查看菜点
				coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
				System.out.println("金币："+coin_userSumary);
				score =  JSONObject.parseObject(coin_userSumary).getJSONObject("Data").getInteger("score");
				System.out.println("查看第"+count_article+"篇文章后的奖励是=="+(score-user.getScore()));
				user.setScore(score);
			}

			//收藏文章 如果userID尾数与星期相同就收藏文章
			int xq = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			if(Integer.parseInt(cnUserID)%7!=xq){
				int randomId = (new Random()).nextInt(ids.size());
				String article_detailSimple = httpUtil.doGet("http://app.cainiaolc.com/article/favor?id="+ids.get(randomId)+"&status=1", "utf-8");
				System.out.println(article_detailSimple);
				String coin_userSumary4 = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
				System.out.println(coin_userSumary4);
				//查看菜点
				coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
				System.out.println("金币："+coin_userSumary);
				score =  JSONObject.parseObject(coin_userSumary).getJSONObject("Data").getInteger("score");
				System.out.println("收藏文章后的奖励是=="+(score-user.getScore()));
				user.setScore(score);
			}
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
			coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			System.out.println("金币："+coin_userSumary);
			score =  JSONObject.parseObject(coin_userSumary).getJSONObject("Data").getInteger("score");
			System.out.println("分享文章后的奖励是=="+(score-user.getScore()));
			user.setScore(score);



			//发帖
//			ReceiveMessage rm = new ReceiveMessage();
//			String post = rm.getOnePost();
//			String post_id = post.split("_")[0];
//			String post_content = post.split("_")[1];
//			Integer app_post_id = 0 ;
//			if(!(post.equals("")||post==null)){
//				Map<String,String> forum_para = new HashMap<String, String>();
//				try {
//					Thread.sleep(20000);
//					forum_para.put("content",post_content);
//					forum_para.put("category", "p2p");
//					forum_para.put("cateId", 225410+"");
//					forum_para.put("upload", "0");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String forum_post = httpUtil.doFormPost("http://app.cainiaolc.com/forum/post", forum_para, "utf-8");
//				System.out.println("发帖后返回内容=="+forum_post);
//				httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
//				String article_list= httpUtil.doGet("http://app.cainiaolc.com/forum/list?page=0&perpage=10&cate="+225410+"&order=time", "utf-8");
//				System.out.println("获取帖子列表后："+article_list);
//				JSONObject json1 = JSONObject.parseObject(article_list);
//				JSONArray  articles = json1.getJSONArray("Data");
//				for (Object obj:articles) {
//					JSONObject j = (JSONObject) obj;
//					if(j.getString("content").equals(post_content)){
//						app_post_id = j.getInteger("id");
////						operateOracle.updatePost(Integer.parseInt(post_id),app_post_id);
//						break;
//					}
//				}
//			}

			Set<String> contentIDs = new HashSet<>();
			int[] recommends_keys = {225410,225411,467782};
			int recommends_cate = recommends_keys[random.nextInt(recommends_keys.length)];
			try {
				Thread.sleep(random.nextInt(20)*10);
			} catch (InterruptedException e1) {
				// 获取大规模数据list前随机休息一下，减少线程并发之间导致服务器压力过大而崩溃
				e1.printStackTrace();
			}
			String hot_artices_str= httpUtil.doGet("http://app.cainiaolc.com/forum/recommends?page=1&perpage=200&cate="+recommends_cate, "utf-8");


			System.out.println("获取热门帖子列表后："+hot_artices_str);
			JSONObject hot_artices = JSONObject.parseObject(hot_artices_str);
			JSONArray  hotarticles = hot_artices.getJSONArray("Data");
			if(hotarticles!=null){
				for (Object article : hotarticles) {
					JSONObject article_json = (JSONObject) article;
					if(Integer.parseInt(article_json.get("commentNum").toString())>=3){
						if(!(article_json.get("authorInfo").equals("菜导")||article_json.get("cateLabel").equals("活捉菜导")||article_json.get("authorLevel").toString().contains("vip"))){
							contentIDs.add(article_json.get("id").toString());
						}
					}
				}
			}

			System.out.println("评论数大于3的帖子数有---"+contentIDs.size());
			//回复两个评论
			for(int i=0;i<1;i++){
				//随机选一个帖子id
				String random_article_id = getRandomElement(contentIDs);
				System.out.println("随机获取的帖子id是=="+random_article_id);
				//获取帖子评论详情
				Map<String,String> article_comments = new HashMap<>();
				article_comments.put("id",random_article_id);
				article_comments.put("perpage","500");
				article_comments.put("page","0");
				String article_comments_str = httpUtil.doPost("http://app.cainiaolc.com/forum/aclist", article_comments, "utf-8");
				JSONObject article_comments_json = JSONObject.parseObject(article_comments_str);
				JSONArray  comments_jsonArray = article_comments_json.getJSONArray("Data");
				if(comments_jsonArray!=null){
					int random_comment = random.nextInt(comments_jsonArray.size());
					JSONObject comment_json = (JSONObject)comments_jsonArray.get(random_comment);
					String comment_id = comment_json.getString("id");
					String content = comment_json.getString("content");
					String replay_content = TuLing.getMessageByInput(content);
					if(!"请求次数超限制!".equalsIgnoreCase(replay_content)){
					    break;
                    }
					//回复评论
					Map<String,String> sub_comment = new HashMap<String, String>();

					sub_comment.clear();
					sub_comment.put("id", random_article_id);
					String detail = httpUtil.doPost("http://app.cainiaolc.com/forum/detail", sub_comment, "utf-8");
					System.out.println("回帖后查看状态=="+detail);

					httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");

					try {
						Thread.sleep(9000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					sub_comment.clear();
					sub_comment.put("id", random_article_id);
					sub_comment.put("perpage", "10");
					sub_comment.put("page", "0");
					httpUtil.doPost("http://app.cainiaolc.com/forum/aclist", sub_comment, "utf-8");


					String postId = getRandomElement(contentIDs);
					sub_comment.put("id", random_article_id);
					sub_comment.put("cid", comment_id);
					sub_comment.put("refid", "");
					sub_comment.put("content", replay_content);
					String re_comment = httpUtil.doPost("http://app.cainiaolc.com/forum/comment", sub_comment, "utf-8");
					System.out.println("回帖后的状态=="+re_comment);

					sub_comment.clear();
					sub_comment.put("id", random_article_id);
					detail = httpUtil.doPost("http://app.cainiaolc.com/forum/detail", sub_comment, "utf-8");
					System.out.println("回帖后查看状态=="+detail);

					sub_comment.clear();
					sub_comment.put("id", random_article_id);
					sub_comment.put("perpage", "10");
					sub_comment.put("page", "0");
					httpUtil.doPost("http://app.cainiaolc.com/forum/aclist", sub_comment, "utf-8");

					httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
				}
			}
			coin_userSumary = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			System.out.println("金币："+coin_userSumary);
			score =  JSONObject.parseObject(coin_userSumary).getJSONObject("Data").getInteger("score");
			System.out.println("回帖后的奖励是=="+(score-user.getScore()));
			user.setScore(score);

			String replayCommentresult = httpUtil.doGet("http://app.cainiaolc.com/coin/userSummary", "utf-8");
			JSONObject finaJson = JSONObject.parseObject(replayCommentresult);
			score = finaJson.getJSONObject("Data").getInteger("score");
			System.out.println("流程完毕后最后的结果==="+score);
			user.setEarn(score-beginScore);
			user.setScore(score);

//		operateOracle.updateAppData("菜鸟理财",user.getUser_name(),user.getTelephone(),"",user.getPassword(),score,user.getCnuserID());
			MysqlManager.updateCnUser(user);

			user = MQManager.MQConsumerQueueReceiveCnUser();
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
	private static Random random;
	//双重校验锁获取一个Random单例
	public static Random getRandom() {
		if(random==null){
			synchronized (cnlc_flow2.class) {
				if(random==null){
					random =new Random();
				}
			}
		}

		return random;
	}
	/**
	 * 获得一个[0,max)之间的整数。
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int max) {
		return Math.abs(getRandom().nextInt())%max;
	}
	/**
	 * 从set中随机取得一个元素
	 * @param set
	 * @return
	 */
	public static <E> E getRandomElement(Set<E> set){
		int rn = getRandomInt(set.size());
		int i = 0;
		for (E e : set) {
			if(i==rn){
				return e;
			}
			i++;
		}
		return null;
	}
}


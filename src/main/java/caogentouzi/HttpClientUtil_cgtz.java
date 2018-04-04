package caogentouzi;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import util.SSLClient;  
/* 
 * 鍒╃敤HttpClient杩涜post璇锋眰鐨勫伐鍏风被 
 */  
public class HttpClientUtil_cgtz {  
	private HttpClient httpClient = null;
	public List<String> cookies =new ArrayList<String>();
	HttpHost target ;// = new HttpHost("192.168.1.4", 8888,  "http"); 
	public HttpHost getTarget() {
		return target;
	}
	public void setTarget(HttpHost target) {
		this.target = target;
	}

	Date date = Calendar.getInstance().getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	public String cnUserID ;
	public String deviceID ;
	public String user_agent;
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getCnUserID() {
		return cnUserID;
	}
	public void setCnUserID(String cnUserID) {
		this.cnUserID = cnUserID;
	}
	public HttpClientUtil_cgtz(){
		try {
			this.httpClient =  new SSLClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    public String doPost(String url,Map<String,String> map,String charset){  
//        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
//            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            // 设置通用的请求属性
            httpPost.addHeader("Cache-Control","no-cache");
            httpPost.addHeader("Pragma","no-cache");
            httpPost.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
            httpPost.addHeader("Accept-Encoding","gzip, deflate");
            httpPost.addHeader("Accept-Language","zh-CN,en-US;q=0.8");
            httpPost.addHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0.1; Redmi 4A Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 Duiba/1.0.5");
            httpPost.addHeader("X-Requested-With","XMLHttpRequest");
            httpPost.addHeader("Cookie","CGTZSESSID=6mcdek49splk8r252gm28m0040; PHPSESSID=tbeiiu2p505htt60qggpeijp65; cgtz_token=e83c117fdc6f7acd1d4a6598d607ee8c9944ae79s%3A40%3A%224765e44695ecaf1587cdc3f559d51d0ff461ae1f%22%3B; _cookie_=b60ad47103d1546a89cee81af52d22f2703446b2fc634c05c5c18d0a489140aa4371177c0fddbcbb3ad1f82ce2250ea44e2b49ff6b91f469e4d501979776a38f");
//            httpPost.addHeader("Content-Length","108");
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("Referer","https://h5.cgtz.com/cgtz/sign/signin.html");
            httpPost.addHeader("Origin","https://h5.cgtz.com");
            httpPost.addHeader("Connection","keep-alive");
            httpPost.addHeader("Host","h5.cgtz.com");
            //璁剧疆鍙傛暟  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            for(int i=0;i<cookies.size();i++){
            	httpPost.addHeader("Cookie", cookies.get(i));
      	  	}
            HttpResponse response;
            if(target==null){
            	 response = httpClient.execute(httpPost);
            }else{
            	 response = httpClient.execute(target,httpPost);
            }
            
             
//            
            if(response != null){  
//            	System.out.println(response.getAllHeaders()[0].getName());
            	for(Header header:response.getAllHeaders() ){
//            		System.out.println(header.getName()+"="+header.getValue());
            		if(header.getName().equalsIgnoreCase("Set-Cookie")){
            			cookies.add(header.getValue());
            		}
            	}
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
//                    result = EntityUtils.toString(resEntity,charset); 
                    result = EntityUtils.toString(resEntity); 
//                    System.out.println(result);
                    httpPost.releaseConnection();
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace(); 
            httpPost.releaseConnection();
        }  
        return result;  
    }  
    
    
    public String doFormPost(String url,Map<String,String> map,String charset){  
      HttpPost httpPost = null;  
      String result = null;  
      try{ 
          httpPost = new HttpPost(url);  
          // 设置通用的请求属性
          httpPost.addHeader("accept","application/json");
          httpPost.addHeader("Accept-Encoding","gzip");
          httpPost.addHeader("user-agent",user_agent);
//          httpPost.addHeader("Content-Length","556");
          httpPost.addHeader("Content-Type","multipart/form-data; boundary=d8067f7c-133d-4ec6-94ae-ae1da7c45225");
          httpPost.addHeader("accept-secret","c9373cf0994ac5dc1ef3827698881cd76865b84020f2dc5e5eb54c52ba1a4baa");
          httpPost.addHeader("accept-time","2017010221730");
          httpPost.addHeader("cnos","android");
          httpPost.addHeader("cnpid","cainiaolc");
          httpPost.addHeader("cnuser","850152");
          httpPost.addHeader("cnver","V1");
          httpPost.addHeader("cnversion","1.1.8");
          httpPost.addHeader("deviceid","31d2b13db676f532");
          httpPost.addHeader("Connection","Keep-Alive");
          httpPost.addHeader("Host","app.cainiaolc.com");
          
          MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"d8067f7c-133d-4ec6-94ae-ae1da7c45225", Charset.defaultCharset());
          multipartEntity.addPart("content", new StringBody(map.get("content"), Charset.forName("UTF-8")));
          multipartEntity.addPart("category", new StringBody("p2p", Charset.forName("UTF-8")));
          multipartEntity.addPart("cateId", new StringBody("225410", Charset.forName("UTF-8")));
          multipartEntity.addPart("upload", new StringBody("0", Charset.forName("UTF-8")));
          httpPost.setEntity(multipartEntity);
          HttpResponse response;
          if(target==null){
          	 response = httpClient.execute(httpPost);
          }else{
          	 response = httpClient.execute(target,httpPost);
          }
          if(response != null){  
          	for(Header header:response.getAllHeaders() ){
          		if(header.getName().equalsIgnoreCase("Set-Cookie")){
          			cookies.add(header.getValue());
          		}
          	}
              HttpEntity resEntity = response.getEntity();  
              if(resEntity != null){  
                  result = EntityUtils.toString(resEntity,charset);
//                  System.out.println(result);
                  httpPost.releaseConnection();
              }  
          }  
      }catch(Exception ex){  
          ex.printStackTrace(); 
          httpPost.releaseConnection();
      }  
      return result;  
  }  
    
    public String doGet(String url,String charset){  
//      HttpClient httpClient = null;  
      HttpGet httpGet = null;  
      String result = null;  
      try{  
//          httpClient = new SSLClient();  
    	  httpGet = new HttpGet(url);  
    	  
    	  // 设置通用的请求属性
//    	  httpGet.addHeader("accept","application/json");
//    	  httpGet.addHeader("Accept-Encoding","gzip");
    	  httpGet.addHeader("user-agent",user_agent);
//    	  httpGet.addHeader("Content-Length","95");
//    	  httpGet.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    	  httpGet.addHeader("accept-secret","8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
//    	  accept-secret: 60f1aef485e6944df9954893f5d23f3cf830e834ac0dd95de580561c17e3dbaf
    	  httpGet.addHeader("accept-time",sdf.format(date));
    	  httpGet.addHeader("cnos","android");
    	  httpGet.addHeader("cnpid","cainiaolc");
    	  httpGet.addHeader("cnuser",cnUserID);
    	  httpGet.addHeader("cnver","V1");
    	  httpGet.addHeader("cnversion","1.1.8");
    	  httpGet.addHeader("deviceid",deviceID);
    	  httpGet.addHeader("Connection","Keep-Alive");
    	  httpGet.addHeader("Host","app.cainiaolc.com");
    	  for(int i=0;i<cookies.size();i++){
    		  httpGet.addHeader("Cookie", cookies.get(i));
    		  System.out.println("已有cookie=="+cookies.get(i));
    	  }
    	  
          //璁剧疆鍙傛暟  
    	  HttpResponse response;
          if(target==null){
          	 response = httpClient.execute(httpGet);
          }else{
          	 response = httpClient.execute(target,httpGet);
          }
          if(response != null){  
          	for(Header header:response.getAllHeaders() ){
//        		System.out.println(header.getName()+"="+header.getValue());
        		if(header.getName().equalsIgnoreCase("Set-Cookie")){
        			cookies.add(header.getValue());
        			System.out.println("cookie=="+header.getValue());
        		}
        	}
              HttpEntity resEntity = response.getEntity();  
              if(resEntity != null){  
//                  result = EntityUtils.toString(resEntity,charset); 
                  result = EntityUtils.toString(resEntity,"utf-8"); 
                  
                  httpGet.releaseConnection();
//                  System.out.println(result);
              }  
          }  
      }catch(Exception ex){  
          ex.printStackTrace(); 
          httpGet.releaseConnection();
      }  
      return result;  
  } 
}  
package util;

import java.net.URI;
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
/* 
 * 鍒╃敤HttpClient杩涜post璇锋眰鐨勫伐鍏风被 
 */  
public class HttpClientUtil {  
	private HttpClient httpClient = null;
	public List<String> cookies =new ArrayList<String>();
	public HttpClientUtil(){
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
//            httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//            httpPost.addHeader("Origin", "https://bosslogin.zbj.com");
//            httpPost.addHeader("Referer", "https://bosslogin.zbj.com/cp-index2?appid=39&back_url=http%3A%2F%2Fboss.zbj.com%2F");
//            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
//            httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
//            httpPost.addHeader("Connection", "keep-alive");
            
            // 设置通用的请求属性
            httpPost.addHeader("accept","application/json");
            httpPost.addHeader("Accept-Encoding","gzip");
            httpPost.addHeader("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
//            httpPost.addHeader("Content-Length","95");
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
            httpPost.addHeader("accept-time","20170102101022");
            httpPost.addHeader("cnos","android");
            httpPost.addHeader("cnpid","cainiaolc");
//            connection.setRequestProperty("cnuser","0");
            httpPost.addHeader("cnver","V1");
            httpPost.addHeader("cnversion","1.1.8");
            httpPost.addHeader("deviceid","31d2b13db676f532");
            httpPost.addHeader("Connection","Keep-Alive");
            httpPost.addHeader("Host","app.cainiaolc.com");
            
            
            
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
            HttpResponse response = httpClient.execute(httpPost);  
            
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
                    result = EntityUtils.toString(resEntity,charset); 
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
//      HttpClient httpClient = null;  
      HttpPost httpPost = null;  
      String result = null;  
      try{  
          httpPost = new HttpPost(url);  
          
          // 设置通用的请求属性
          httpPost.addHeader("accept","application/json");
          httpPost.addHeader("Accept-Encoding","gzip");
          httpPost.addHeader("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
//          httpPost.addHeader("Content-Length","95");
          httpPost.addHeader("Content-Type","multipart/form-data; boundary=8c66dda2-b6fd-49f5-bc63-6ae5e55a294e");
          httpPost.addHeader("accept-secret","8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
          httpPost.addHeader("accept-time","20170102101022");
          httpPost.addHeader("cnos","android");
          httpPost.addHeader("cnpid","cainiaolc");
//          connection.setRequestProperty("cnuser","0");
          httpPost.addHeader("cnver","V1");
          httpPost.addHeader("cnversion","1.1.8");
          httpPost.addHeader("deviceid","31d2b13db676f532");
          httpPost.addHeader("Connection","Keep-Alive");
          httpPost.addHeader("Host","app.cainiaolc.com");
          
          
          MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
          // 上传的文件  
//          builder.addBinaryBody(fileParamName, file);  
          // 设置其他参数  
          for (Entry<String, String> entry : map.entrySet()) {  
              builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));  
          }  
          HttpEntity httpEntity = builder.build();  
          httpPost.setEntity(httpEntity);  
          
          
          
          
          
          //璁剧疆鍙傛暟  
//          List<NameValuePair> list = new ArrayList<NameValuePair>();  
//          Iterator iterator = map.entrySet().iterator();  
//          while(iterator.hasNext()){  
//              Entry<String,String> elem = (Entry<String, String>) iterator.next();  
//              list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
//          }  
//          if(list.size() > 0){  
//              UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
//              httpPost.setEntity(entity);  
//          }  
          for(int i=0;i<cookies.size();i++){
          	httpPost.addHeader("Cookie", cookies.get(i));
    	  	}
          HttpResponse response = httpClient.execute(httpPost);  
          
          if(response != null){  
//          	System.out.println(response.getAllHeaders()[0].getName());
          	for(Header header:response.getAllHeaders() ){
//          		System.out.println(header.getName()+"="+header.getValue());
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
//    	  httpGet.addHeader("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
//    	  httpGet.addHeader("Content-Length","95");
//    	  httpGet.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    	  httpGet.addHeader("accept-secret","8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
//    	  accept-secret: 60f1aef485e6944df9954893f5d23f3cf830e834ac0dd95de580561c17e3dbaf
    	  httpGet.addHeader("accept-time","20170102114010");
    	  httpGet.addHeader("cnos","android");
    	  httpGet.addHeader("cnpid","cainiaolc");
    	  httpGet.addHeader("cnuser","2568");
    	  httpGet.addHeader("cnver","V1");
    	  httpGet.addHeader("cnversion","1.1.8");
    	  httpGet.addHeader("deviceid","31d2b13db676f532");
    	  httpGet.addHeader("Connection","Keep-Alive");
    	  httpGet.addHeader("Host","app.cainiaolc.com");
    	  for(int i=0;i<cookies.size();i++){
    		  httpGet.addHeader("Cookie", cookies.get(i));
    		  System.out.println("已有cookie=="+cookies.get(i));
    	  }
    	  
          //璁剧疆鍙傛暟  
          HttpResponse response = httpClient.execute(httpGet);  
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
                  result = EntityUtils.toString(resEntity,charset); 
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
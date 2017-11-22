package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientRequest {
	
	public static String get(HttpClient httpClient,String url) {
		 //httpClient
//	    HttpClient httpClient = new DefaultHttpClient();

	    // get method
	    HttpGet httpGet = new HttpGet(url);
	    
	    // set header
//	    String Au="Bearer "+access_token;
	    httpGet.setHeader("Authorization","sdfsadsf");  
	  
	    //response
	    HttpResponse response = null;  
	    try{
	        response = httpClient.execute(httpGet);
	    }catch (Exception e) {} 

	    //get response into String
	    String temp="";
	    try{
	        HttpEntity entity = response.getEntity();
	        temp=EntityUtils.toString(entity,"UTF-8");
	    }catch (Exception e) {} 
	    
	    return temp;
	}
	
	public static String post(HttpClient httpClient,String url,Map<String,String> para) {
		//httpClient
//	    HttpClient httpClient = new DefaultHttpClient();

	    // get method
	    HttpPost httpPost = new HttpPost(url);    
	  
	    // set header
	    // 设置通用的请求属性
	    httpPost.setHeader("accept","application/json");
	    httpPost.setHeader("Accept-Encoding","gzip");
	    httpPost.setHeader("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
	    httpPost.setHeader("Content-Length","95");
	    httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//        connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
	    httpPost.setHeader("accept-time","20170102101022");
	    httpPost.setHeader("cnos","android");
	    httpPost.setHeader("cnpid","cainiaolc");
//        connection.setRequestProperty("cnuser","0");
	    httpPost.setHeader("cnver","V1");
	    httpPost.setHeader("cnversion","1.1.8");
	    httpPost.setHeader("deviceid","31d2b13db676f532");
	    httpPost.setHeader("Connection","Keep-Alive");
	    httpPost.setHeader("Host","app.cainiaolc.com");

	    //set params
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    for (Entry<String, String> entry : para.entrySet()) {
	    	params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
//	    params.add(new BasicNameValuePair("client_id",client_id));
//	    params.add(new BasicNameValuePair("redirect_uri",redirect_uri));
//	    params.add(new BasicNameValuePair("client_secret",client_secret));
//	    params.add(new BasicNameValuePair("code",code));
//	    params.add(new BasicNameValuePair("grant_type","authorization_code"));
	    
	    
	    
	    
	    
	    
	    try{
	        httpPost.setEntity(new UrlEncodedFormEntity(params));
	    }catch (Exception e) {} 

	    //response
	    HttpResponse response = null;  
	    try{
	        response = httpClient.execute(httpPost);
	    }catch (Exception e) {}
	    
	    //get response into String
	    String temp="";
	    try{
	        HttpEntity entity = response.getEntity();
	        temp=EntityUtils.toString(entity,"UTF-8");
	    }catch (Exception e) {}
	    
	    return temp;    
	}
}

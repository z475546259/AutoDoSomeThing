package util;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;

public class bb {
	public static void Login() {  
	       String url = "http://www.***.com/login"; 
	       PostMethod postMethod = new PostMethod(url); 
	       HttpClient httpClient =  new HttpClient();
	       // 填入各个表单域的值  
	       NameValuePair[] data = {  
	               new NameValuePair("account", "yijianfeng_vip@163.com"),  
	               new NameValuePair("nextUrl", ""),  
	               new NameValuePair("lcallback", ""),  
	               new NameValuePair("password    ", "******"),  
	               new NameValuePair("persistent", "1"), };  
	       // 将表单的值放入postMethod中  
	       postMethod.setRequestBody(data);  
	       // 执行postMethod  
	       int statusCode = 0;  
	       try {  
	           statusCode = httpClient.executeMethod(postMethod);  
	       } catch (HttpException e) {  
	           e.printStackTrace();  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	       }  
	       // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发  
	       // 301或者302  
	       if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY  
	               || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {  
	           // 从头中取出转向的地址  
	           Header locationHeader = postMethod.getResponseHeader("location");  
	           String location = null;  
	           if (locationHeader != null) {  
	               location = locationHeader.getValue();  
	               System.out.println("diandianLogin:" + location);  
	           } else {  
	               System.err.println("Location field value is null.");  
	           }  
	           return;  
	       } else {  
	           System.out.println(postMethod.getStatusLine());  
	           String str = "";  
	           try {  
	               str = postMethod.getResponseBodyAsString();  
	           } catch (IOException e) {  
	               e.printStackTrace();  
	           }  
	           System.out.println(str);  
	       }  
	       postMethod.releaseConnection();  
	       return;  
	   }  
}

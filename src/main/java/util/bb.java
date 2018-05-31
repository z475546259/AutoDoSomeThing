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
	       // ������������ֵ  
	       NameValuePair[] data = {  
	               new NameValuePair("account", "yijianfeng_vip@163.com"),  
	               new NameValuePair("nextUrl", ""),  
	               new NameValuePair("lcallback", ""),  
	               new NameValuePair("password    ", "******"),  
	               new NameValuePair("persistent", "1"), };  
	       // ������ֵ����postMethod��  
	       postMethod.setRequestBody(data);  
	       // ִ��postMethod  
	       int statusCode = 0;  
	       try {  
	           statusCode = httpClient.executeMethod(postMethod);  
	       } catch (HttpException e) {  
	           e.printStackTrace();  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	       }  
	       // HttpClient����Ҫ����ܺ�̷����������POST��PUT�Ȳ����Զ�����ת��  
	       // 301����302  
	       if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY  
	               || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {  
	           // ��ͷ��ȡ��ת��ĵ�ַ  
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

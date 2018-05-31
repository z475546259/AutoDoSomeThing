package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
	public static URLConnection connection;
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(URLConnection connection,String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept","application/json");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setRequestProperty("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
            connection.setRequestProperty("Content-Length","95");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
            connection.setRequestProperty("accept-time","20170102101022");
            connection.setRequestProperty("cnos","android");
            connection.setRequestProperty("cnpid","cainiaolc");
//            connection.setRequestProperty("cnuser","0");
            connection.setRequestProperty("cnver","V1");
            connection.setRequestProperty("cnversion","1.1.8");
            connection.setRequestProperty("deviceid","31d2b13db676f532");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Host","app.cainiaolc.com");
            
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(URLConnection connection,String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept","application/json");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setRequestProperty("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
            connection.setRequestProperty("Content-Length","95");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
            connection.setRequestProperty("accept-time","20170102101022");
            connection.setRequestProperty("cnos","android");
            connection.setRequestProperty("cnpid","cainiaolc");
//            connection.setRequestProperty("cnuser","0");
            connection.setRequestProperty("cnver","V1");
            connection.setRequestProperty("cnversion","1.1.8");
            connection.setRequestProperty("deviceid","31d2b13db676f532");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Host","app.cainiaolc.com");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
//    public static void main(String[] args) {
//        //发送 GET 请求
//        String s=HttpRequest.sendGet("http://www.baidu.com", "key=123&v=456");
//        System.out.println(s);
//        
//        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://www.baidu.com", "key=123&v=456");
//        System.out.println(sr);
//    }
}
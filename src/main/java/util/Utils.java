package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
	 /**
     * 从Stream流里读取字符串
     */
    public static String getStringFromStream(InputStream in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        String line = null;
        try {
        	reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((line = reader.readLine()) != null) {
			    buffer.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return buffer.toString();
    }
    
    
    
    /**
     * 从StreamReader流里读取字符串
     */
    public static String getStringFromInputStreamReader(InputStreamReader in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        String line = null;
        try {
        	reader = new BufferedReader(in);
			while ((line = reader.readLine()) != null) {
			    buffer.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return buffer.toString();
    }
}

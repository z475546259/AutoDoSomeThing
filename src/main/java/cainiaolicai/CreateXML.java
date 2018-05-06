package cainiaolicai;

import java.io.FileOutputStream;  
import java.io.IOException;  
import org.jdom.Document;  
import org.jdom.Element;  
import org.jdom.JDOMException;  
import org.jdom.output.Format;  
import org.jdom.output.XMLOutputter;  

public class CreateXML {  

    cnUser[] users = new cnUser[]  
            {  
                    new cnUser("15923584508","d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b","2568","","31d2b13db676f532","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)","张治强"),  
                    new cnUser("15923584508","d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b","2568","","31d2b13db676f532","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)","张治强"),  
                    new cnUser("15923584508","d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b","2568","","31d2b13db676f532","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)","张治强"),  
                    new cnUser("15923584508","d5c91303b3963ea463d4d97b616f06224f2469bdb4d9984ca696dd37c7059a7b","2568","","31d2b13db676f532","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)","张治强"),  
                     
            };  

    public void BuildXMLDoc() throws IOException, JDOMException {     
        // 创建根节点 并设置它的属性 ;     
        Element root = new Element("cnUsers").setAttribute("count", "4").setAttribute("classfy","男人");     
        // 将根节点添加到文档中；     
        Document Doc = new Document(root);     

        for (int i = 0; i < users.length; i++) {    
           // 创建节点 book;     
           Element elements = new Element("cnUser");       
           // 给 book 节点添加子节点并赋值；     
           elements.addContent(new Element("telephone").setText(users[i].getTelephone()).setAttribute("sss","sss"));    
           elements.addContent(new Element("user_name").setText(users[i].getUser_name()));   
           elements.addContent(new Element("password").setText(users[i].getPassword()));
           elements.addContent(new Element("cn_user_id").setText(users[i].getCnuserID()));
           elements.addContent(new Element("device_id").setText(users[i].getDeviceID()));
           elements.addContent(new Element("user_agent").setText(users[i].getUser_agent()));
           //    
           root.addContent(elements);    
       }    
        // 输出 books.xml 文件；    
        // 使xml文件 缩进效果  
        Format format = Format.getPrettyFormat();  
        XMLOutputter XMLOut = new XMLOutputter(format);  
        XMLOut.output(Doc, new FileOutputStream("cnUser.xml"));  
    }   
    public static void main(String[] args) {    
       try {    
           CreateXML j2x = new CreateXML();    
           System.out.println("正在生成 cnUser.xml 文件...");    
           j2x.BuildXMLDoc();    
       } catch (Exception e) {    
           e.printStackTrace();    
       }    
       System.out.println("cnUser.xml 生成完毕");  
    }    
}    
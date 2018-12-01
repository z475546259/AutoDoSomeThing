//package cainiaolicai;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.zzq.springcloud.entities.cnUser;
//import org.dom4j.Attribute;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//public class ListDom4J {
// public static void main(String[] args) throws Exception {
//  SAXReader reader = new SAXReader();
//  File file = new File("cnUser.xml");
//  Document document = reader.read(file);
//  Element root = document.getRootElement();
//  List<Element> childElements = root.elements();
//  for (Element child : childElements) {
//
//   //未知属性名情况
//   /*List<Attribute> attributeList = child.attributes();
//   for (Attribute attr : attributeList) {
//    System.out.println(attr.getName() + ": " + attr.getValue());
//   }
//   List<Element> elementList = child.elements();
//   for (Element ele : elementList) {
//    System.out.println(ele.getName() + ": " + ele.getText());
//   }
//   System.out.println();*/
//
//   //已知属性名情况
//   System.out.println("telephone: " + child.elementText("telephone"));
//   System.out.println("user_name:" + child.elementText("user_name"));
//   System.out.println();
//  }
// }
//	 /**
//	  * 将dom文件转化为cnUser list
//	  * @return
//	  */
//	 public static List<cnUser> turnDomtoCnUsers(){
//		 List<cnUser> cnUsers = new ArrayList<cnUser>();
//		 SAXReader reader = new SAXReader();
//		  File file = new File("cnUser.xml");
//		  Document document;
//			try {
//				document = reader.read(file);
//				Element root = document.getRootElement();
//				 List<Element> childElements = root.elements();
//				  for (Element child : childElements) {
//					  cnUser cnuser = new cnUser();
//					  cnuser.setTelephone(child.elementText("telephone"));
//					  cnuser.setUser_name(child.elementText("user_name"));
//					  cnuser.setPassword(child.elementText("password"));
//					  cnuser.setCnuserID(child.elementText("cn_user_id"));
//					  cnuser.setDeviceID(child.elementText("device_id"));
//					  cnuser.setUser_agent(child.elementText("user_agent"));
//					  cnUsers.add(cnuser);
//				  }
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 return cnUsers;
//	 }
//
//
//}
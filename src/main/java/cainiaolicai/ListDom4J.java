package cainiaolicai;

import java.io.File;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ListDom4J {
 public static void main(String[] args) throws Exception {
  SAXReader reader = new SAXReader();
  File file = new File("cnUser.xml");
  Document document = reader.read(file);
  Element root = document.getRootElement();
  List<Element> childElements = root.elements();
  for (Element child : childElements) {

   //未知属性名情况
   /*List<Attribute> attributeList = child.attributes();
   for (Attribute attr : attributeList) {
    System.out.println(attr.getName() + ": " + attr.getValue());
   }
   List<Element> elementList = child.elements();
   for (Element ele : elementList) {
    System.out.println(ele.getName() + ": " + ele.getText());
   }
   System.out.println();*/

   //已知属性名情况
   System.out.println("telephone: " + child.elementText("telephone"));
   System.out.println("user_name:" + child.elementText("user_name"));
   System.out.println();
  }
 }
}
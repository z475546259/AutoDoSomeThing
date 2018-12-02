//package util;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//
///**
// * 本教程由 http://bbs.datahref.com/ 提供
// * Tess4j验证码识别示例
// * 工程中tessdata文件夹包含了识别英文所需的数据
// * 需要识别其他语言课到https://github.com/tesseract-ocr/tessdata下载相关数据
// * 放到tessdata文件夹中
// * @author hu
// *
// */
//public class OCRDemo {
//
//    public static void main(String[] args) throws TesseractException {
//        ITesseract instance = new Tesseract();
////        File imgDir = new File("0.png");
//        File imgDir =null;
//		try {
//			imgDir = getImageFromNetByUrl("http://m.cainiaolc.com/captcha/0.29656038503162563","aa.png");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        //对img_data文件夹中的每个验证码进行识别
//        //文件名即正确的结果
////        for (File imgFile : imgDir.listFiles()) {
//            //该例子输入的是文件，也可输入BufferedImage
////            String ocrResult = instance.doOCR(imgFile);
//            String ocrResult = instance.doOCR(imgDir);
//            //输出图片文件名，即正确识别结果
////            System.out.println("ImgFile: "+imgFile.getAbsolutePath());
//            System.out.println("ImgFile: "+imgDir.getAbsolutePath());
//            //输出识别结果
//            System.out.println("OCR Result: " + ocrResult);
////        }
//    }
//
//
//    public static File getImageFromNetByUrl(String strUrl,String path) throws Exception {
//        String imageName = strUrl.substring(strUrl.lastIndexOf("/") + 1,
//                strUrl.length());
////        _FakeX509TrustManager.allowAllSSL();
//        URL url = new URL(strUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        // conn.setRequestMethod("GET");
//        conn.setRequestProperty("User-Agent",
//                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        conn.setConnectTimeout(5 * 1000);
//        InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
//        byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
//        inStream.close();
//        conn.disconnect();
//        File file =null;
//        try {
//            file = new File(path);
////            DirectoryUtil.createFile(path+imageName);
//            FileOutputStream fops = new FileOutputStream(file);
//            fops.write(btImg);
//            fops.flush();
//            fops.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//
//    public static byte[] readInputStream(InputStream inStream) throws Exception {
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, len);
//        }
//        inStream.close();
//        return outStream.toByteArray();
//    }
//}
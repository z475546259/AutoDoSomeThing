package autoDoSomeThing;

import android.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.crypto.Cipher;

public final class ᵢˏ
{
  private static String ᵢˋ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
  public static String ᵢˋ(int paramInt)
  {
    Random localRandom = new Random(System.currentTimeMillis());
    StringBuilder localStringBuilder = new StringBuilder();
    while (paramInt > 0)
    {
      localStringBuilder.append(ᵢˋ.charAt(localRandom.nextInt(ᵢˋ.length())));
      paramInt -= 1;
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static String ᵢˋ(java.io.File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: sipush 4096
    //   5: newarray byte
    //   7: astore_3
    //   8: ldc 59
    //   10: invokestatic 65	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   13: astore 4
    //   15: new 67	java/io/FileInputStream
    //   18: dup
    //   19: aload_0
    //   20: invokespecial 70	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   23: astore_0
    //   24: aload_0
    //   25: aload_3
    //   26: invokevirtual 74	java/io/FileInputStream:read	([B)I
    //   29: istore_1
    //   30: iload_1
    //   31: ifle +26 -> 57
    //   34: aload 4
    //   36: aload_3
    //   37: iconst_0
    //   38: iload_1
    //   39: invokevirtual 78	java/security/MessageDigest:update	([BII)V
    //   42: goto -18 -> 24
    //   45: astore_2
    //   46: aload_0
    //   47: ifnull +7 -> 54
    //   50: aload_0
    //   51: invokevirtual 81	java/io/FileInputStream:close	()V
    //   54: ldc 83
    //   56: areturn
    //   57: aload 4
    //   59: invokevirtual 87	java/security/MessageDigest:digest	()[B
    //   62: invokestatic 91	com/qihoo/util/upgrade/ᵢˏ:ᵢˎ	([B)Ljava/lang/String;
    //   65: invokestatic 94	com/qihoo/util/upgrade/ᵢˏ:ᵢˎ	(Ljava/lang/String;)Ljava/lang/String;
    //   68: astore_2
    //   69: aload_0
    //   70: invokevirtual 81	java/io/FileInputStream:close	()V
    //   73: aload_2
    //   74: areturn
    //   75: astore_0
    //   76: aload_2
    //   77: areturn
    //   78: astore_2
    //   79: aconst_null
    //   80: astore_0
    //   81: aload_0
    //   82: ifnull +7 -> 89
    //   85: aload_0
    //   86: invokevirtual 81	java/io/FileInputStream:close	()V
    //   89: aload_2
    //   90: athrow
    //   91: astore_0
    //   92: goto -38 -> 54
    //   95: astore_0
    //   96: goto -7 -> 89
    //   99: astore_2
    //   100: goto -19 -> 81
    //   103: astore_0
    //   104: aload_2
    //   105: astore_0
    //   106: goto -60 -> 46
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramFile	java.io.File
    //   29	10	1	i	int
    //   1	1	2	localObject1	Object
    //   45	1	2	localException	Exception
    //   68	9	2	str	String
    //   78	12	2	localObject2	Object
    //   99	6	2	localObject3	Object
    //   7	30	3	arrayOfByte	byte[]
    //   13	45	4	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   24	30	45	java/lang/Exception
    //   34	42	45	java/lang/Exception
    //   57	69	45	java/lang/Exception
    //   69	73	75	java/io/IOException
    //   8	24	78	finally
    //   50	54	91	java/io/IOException
    //   85	89	95	java/io/IOException
    //   24	30	99	finally
    //   34	42	99	finally
    //   57	69	99	finally
    //   8	24	103	java/lang/Exception
  }
  
  public static String ᵢˋ(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new X509EncodedKeySpec(Base64.decode(paramString2.getBytes(), 0));
      paramString2 = KeyFactory.getInstance("RSA").generatePublic(paramString2);
      Cipher localCipher = Cipher.getInstance("RSA/ECB/NoPadding");
      localCipher.init(1, paramString2);
      paramString1 = ᵢˎ(ᵢˎ(localCipher.doFinal(paramString1.getBytes())));
      return paramString1;
    }
    catch (Exception paramString1) {}
    return "";
  }
  
  public static boolean ᵢˋ(String paramString)
  {
    byte[] arrayOfByte = new byte[4096];
    for (;;)
    {
      Object localObject;
      try
      {
        JarFile localJarFile = new JarFile(paramString);
        Enumeration localEnumeration = localJarFile.entries();
        paramString = null;
        if (localEnumeration.hasMoreElements())
        {
          localObject = (JarEntry)localEnumeration.nextElement();
          if ((((JarEntry)localObject).isDirectory()) || (((JarEntry)localObject).getName().startsWith("META-INF/"))) {
            continue;
          }
          if (((JarEntry)localObject).getName().contains("..")) {
            return false;
          }
          localObject = ᵢˋ(localJarFile, (JarEntry)localObject, arrayOfByte);
          if (localObject == null)
          {
            localJarFile.close();
            return false;
          }
          if (localObject.length != 1)
          {
            localJarFile.close();
            return false;
            if (paramString.length != 1)
            {
              localJarFile.close();
              return false;
            }
            if (paramString[0] == localObject[0]) {
              continue;
            }
            localJarFile.close();
            return false;
          }
        }
        else
        {
          localJarFile.close();
          boolean bool = new String(ᵢˏ(paramString[0].getEncoded())).equals(ᵢˎ.ᵢˑ);
          return bool;
        }
      }
      catch (Exception paramString)
      {
        return false;
      }
      if (paramString == null) {
        paramString = (String)localObject;
      }
    }
  }
  
  public static byte[] ᵢˋ(String paramString, byte[] paramArrayOfByte)
  {
    Object localObject = null;
    if ((paramString == null) || (paramArrayOfByte == null)) {
      return null;
    }
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return ᵢˎ(paramString, paramArrayOfByte);
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  public static byte[] ᵢˋ(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.equals(""))) {}
    for (;;)
    {
      return null;
      if (paramArrayOfByte.length > 175)
      {
        byte[] arrayOfByte = Arrays.copyOfRange(paramArrayOfByte, 175, paramArrayOfByte.length);
        paramArrayOfByte = Arrays.copyOfRange(paramArrayOfByte, 0, 175);
        try
        {
          Object localObject = new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEqrEWJ1UJyolyNMVp4MeUUfMRcaRLKbpv9acIhUiw/Pha1KmRDsgA1opVSK65HDwK2vTwcW1//lPMC9lsu9KZHvqROEsCwjeWCTh+u/RJo6zHPkKWaSm5qTDocPnkz1gzYZin4QhVNOXHaciuqR8lMRuYxLZ+Z+hFWCvC8EiSrQIDAQAB".getBytes("UTF-8"), 0));
          localObject = KeyFactory.getInstance("RSA").generatePublic((KeySpec)localObject);
          Signature localSignature = Signature.getInstance("MD5withRSA");
          localSignature.initVerify((PublicKey)localObject);
          localSignature.update(arrayOfByte);
          boolean bool = localSignature.verify(Base64.decode(paramArrayOfByte, 0));
          if (bool) {
            return arrayOfByte;
          }
        }
        catch (Exception paramArrayOfByte) {}
      }
    }
    return null;
  }
  
  public static byte[] ᵢˋ(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null)) {
      return null;
    }
    return ᵢˎ(paramArrayOfByte1, paramArrayOfByte2);
  }
  
  private static Certificate[] ᵢˋ(JarFile paramJarFile, JarEntry paramJarEntry, byte[] paramArrayOfByte)
  {
    try
    {
      paramJarFile = paramJarFile.getInputStream(paramJarEntry);
      while (paramJarFile.read(paramArrayOfByte, 0, 4096) != -1) {}
      paramJarFile.close();
      paramJarEntry.getCertificates();
      if (paramJarEntry != null) {}
      for (paramJarFile = paramJarEntry.getCertificates();; paramJarFile = null)
      {
        paramJarFile = (Certificate[])paramJarFile;
        return paramJarFile;
      }
      return null;
    }
    catch (IOException paramJarFile) {}
  }
  
  public static String ᵢˎ(String paramString)
  {
    Object localObject;
    if (paramString == null)
    {
      localObject = "";
      return localObject;
    }
    String str1 = "";
    int i = 0;
    for (;;)
    {
      localObject = str1;
      if (i >= paramString.length()) {
        break;
      }
      String str2 = Integer.toHexString(paramString.charAt(i) & 0xFF);
      localObject = str2;
      if (str2.length() == 1) {
        localObject = "0" + str2;
      }
      str1 = str1 + (String)localObject;
      i += 1;
    }
  }
  
  public static String ᵢˎ(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append((char)paramArrayOfByte[i]);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  private static byte[] ᵢˎ(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int n = 0;
    byte[] arrayOfByte2 = new byte[256];
    int j = 0;
    while (j < 256)
    {
      arrayOfByte2[j] = ((byte)j);
      j += 1;
    }
    byte[] arrayOfByte1;
    int k;
    int m;
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0))
    {
      arrayOfByte1 = null;
      paramArrayOfByte2 = new byte[paramArrayOfByte1.length];
      k = 0;
      m = 0;
      j = n;
    }
    while (j < paramArrayOfByte1.length)
    {
      m = m + 1 & 0xFF;
      k = k + (arrayOfByte1[m] & 0xFF) & 0xFF;
      int i = arrayOfByte1[m];
      arrayOfByte1[m] = arrayOfByte1[k];
      arrayOfByte1[k] = i;
      n = arrayOfByte1[m];
      int i1 = arrayOfByte1[k];
      int i2 = paramArrayOfByte1[j];
      paramArrayOfByte2[j] = ((byte)(arrayOfByte1[((n & 0xFF) + (i1 & 0xFF) & 0xFF)] ^ i2));
      j += 1;
      continue;
      k = 0;
      m = 0;
      j = 0;
      for (;;)
      {
        arrayOfByte1 = arrayOfByte2;
        if (k >= 256) {
          break;
        }
        m = m + ((paramArrayOfByte2[j] & 0xFF) + (arrayOfByte2[k] & 0xFF)) & 0xFF;
        i = arrayOfByte2[k];
        arrayOfByte2[k] = arrayOfByte2[m];
        arrayOfByte2[m] = i;
        j = (j + 1) % paramArrayOfByte2.length;
        k += 1;
      }
    }
    return paramArrayOfByte2;
  }
  
  private static char[] ᵢˏ(byte[] paramArrayOfByte)
  {
    int k = paramArrayOfByte.length;
    char[] arrayOfChar = new char[k * 2];
    int i = 0;
    if (i < k)
    {
      int m = paramArrayOfByte[i];
      int j = m >> 4 & 0xF;
      if (j >= 10)
      {
        j = j + 97 - 10;
        label44:
        arrayOfChar[(i * 2)] = ((char)j);
        j = m & 0xF;
        if (j < 10) {
          break label97;
        }
        j = j + 97 - 10;
      }
      for (;;)
      {
        arrayOfChar[(i * 2 + 1)] = ((char)j);
        i += 1;
        break;
        j += 48;
        break label44;
        label97:
        j += 48;
      }
    }
    return arrayOfChar;
  }
}

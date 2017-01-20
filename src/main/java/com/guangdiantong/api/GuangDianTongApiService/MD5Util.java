package com.guangdiantong.api.GuangDianTongApiService;

import java.security.MessageDigest;

public class MD5Util {

  public static String MD5(String src) {
    return MD5(src, null);
  }
  
  public static String MD5(String src, String charsetName) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      if (charsetName!=null && charsetName.trim().length()!=0) {
        md.update(src.getBytes(charsetName));
      }else{
        md.update(src.getBytes());
      }
      
      byte b[] = md.digest();

      int i;
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < b.length; offset++) {
        i = b[offset];
        if (i < 0)
          i += 256;
        if (i < 16)
          buf.append("0");
        buf.append(Integer.toHexString(i));
      }
      //32位加密  
      return buf.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}

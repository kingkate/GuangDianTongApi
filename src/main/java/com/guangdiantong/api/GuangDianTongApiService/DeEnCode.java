package com.guangdiantong.api.GuangDianTongApiService;

import java.nio.charset.Charset;


public class DeEnCode {

//    private static final String key0 = "FECOI()*&<MNCXZPKL";
    private static final Charset charset = Charset.forName("UTF-8");
//    private static byte[] keyBytes = key0.getBytes(charset);
    
    public static String encode(String str,String sn){
    	String result = "";
    	int j = 0;
    	for(int i=0;i<str.length();i++){
    		result = result + (char)(str.charAt(i)^sn.charAt(j));
    		j = (++j)%sn.length();
    	}
    	return result;
    }
    
    public static String decode(String dec,String key){
        byte[] e = dec.getBytes();
        byte[] dee = e;
        for(int i=0,size=e.length;i<size;i++){
            for(byte keyBytes0:key.getBytes()){
                e[i] = (byte) (dee[i]^keyBytes0);
            }
        }
        return new String(e);
    }

    public static void main(String[] args) {
        String s="you are right";
        String enc = encode(s,"kkkkk");
        String dec = decode(enc,"kkkkk");
        System.out.println(enc);
        System.out.println(dec);
    }
}
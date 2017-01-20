package com.guangdiantong.api.GuangDianTongApiService;

import javax.xml.bind.DatatypeConverter;

public class Base64 {

	public static String encode(byte[] binaryData) {
	  return DatatypeConverter.printBase64Binary(binaryData);
	}

	public static byte[] decode(String encoded) {
	  return DatatypeConverter.parseBase64Binary(encoded);
	}

}

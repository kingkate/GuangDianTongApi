package com.guangdiantong.api.GuangDianTongApiService;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static String get(String url){
		HttpGet get = new HttpGet(url);
		HttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(get);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

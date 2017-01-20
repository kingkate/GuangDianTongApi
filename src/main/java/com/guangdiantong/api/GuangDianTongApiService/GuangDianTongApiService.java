package com.guangdiantong.api.GuangDianTongApiService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GuangDianTongApiService {
	
	private static final String GDT_API_URL = "http://t.gdt.qq.com/conv/app/";
	
	public String receiveClick(){
		
		
		return null;
	}
	
	
	public static void main(String[] args) {
		GuangDianTongApiService service = new GuangDianTongApiService();
		RequestData data = generateData();
		service.convertDataUploadService(data);
	}
	
	
	private static RequestData generateData() {
		RequestData data = new RequestData();
		data.setAppId(112233);
		data.setClickId("007210548a030059ccdfd1d4");
		data.setMuId("0f074dc8e1f0547310e729032ac0730b");
		data.setConvTime("1422263664");
		data.setClientIp("10.11.12.13");
		data.setAdvertiserId(10000);
		data.setSignKey("test_sign_key");
		data.setEncryptKey("test_encrypt_key");
		data.setConvType("MOBILEAPP_ACTIVITE");
		data.setAppType("ANDROID");
		
		return data;
	}


	/**
	 * 
	 * @param appid Android应用为应用宝移动应用的id，或者iOS应用在Apple App Store的id；如果是移动联
盟推广的App，则由移动联盟系统分配appid
	 * @param data 加密处理后的用户数据
	 * @param convType 转化行为标记参数，激活参数取MOBILEAPP_ACTIVITE
	 * @param appType app类型；取值为 android 或 ios；注意是小写
	 * @param advertiserId 广告主在广点通（e.qq.com）的账户id（不是QQ号）
	 * @return
	 */
	public String convertDataUploadInner(int appid,String data,String convType,String appType,int advertiserId){
		String attachment = "conv_type="+convType+"&app_type="+appType+"&advertiser_id="+advertiserId;
		//构造最终请求
		String url = GDT_API_URL+appid+"/conv?v="+data+"&"+attachment;
		return HttpUtil.get(url);
	}
	
	public String convertDataUploadService(RequestData data){
		//组合参数生成query_string
		String queryString = getQueryString(data);
		//利用query_string构造page
		String page = GDT_API_URL+data.getAppId()+"/conv?";
		page = page + queryString;
		//将page进行urlencode得到encode_page
		String encodePage=null;
		try {
			encodePage = URLEncoder.encode(page,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//将encode_page组装成property
		String property = data.getSignKey()+"&GET&"+encodePage;
		//利用property生成signature
		String signature = MD5Util.MD5(property);
		//利用signature和query_string生成base_data
		String baseData = queryString+"&sign="+signature;
		// 利用base_data和encrypt_key生成data
//		try {
//			baseData = URLEncoder.encode(baseData.replace("\r\n", ""),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		baseData = baseData.replace("\\n", "");
//		baseData = baseData.substring(0,baseData.length()-1);
		String data1 = Base64.encode(DeEnCode.encode(baseData, data.getEncryptKey()).getBytes());
		return convertDataUploadInner(data.getAppId(), data1, data.getConvType(), data.getAppType(), data.getAdvertiserId());
	}

	private String getQueryString(RequestData data) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("click_id=").append(data.getClickId()).append("&");
		buffer.append("muid=").append(data.getMuId()).append("&");
		buffer.append("conv_time=").append(data.getConvTime()).append("&");
		buffer.append("client_ip=").append(data.getClientIp());
		return buffer.toString();
	}
}

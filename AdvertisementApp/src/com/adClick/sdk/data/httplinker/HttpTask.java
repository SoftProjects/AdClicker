package com.adClick.sdk.data.httplinker;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HttpTask {
	public static final int HTTP_POST = 0;
	public static final int HTTP_GET = 1;
	
	private String baseurl;
	private List<NameValuePair> nameValuePairs;
	private int method;
	
	public HttpTask(String baseurl,int method){
		this.baseurl = baseurl;
		this.nameValuePairs = new ArrayList<NameValuePair>();
		this.method = method;
	}
	
	public void addParameter(String key,String value){
		nameValuePairs.add(new BasicNameValuePair(key, value));
	}
	public void addParameter(List<NameValuePair> nameValuePairs){
		for(NameValuePair pair:nameValuePairs) 
			this.nameValuePairs.add(pair);
	}

	public String getBaseurl() {
		return baseurl;
	}
	public List<NameValuePair> getParameters() {
		return nameValuePairs;
	}
	public int getMethod() {
		return method;
	}
}

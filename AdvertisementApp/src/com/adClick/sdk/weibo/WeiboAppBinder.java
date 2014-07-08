package com.adClick.sdk.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

public class WeiboAppBinder{
	public static final String APP_KEY = "1098990230";
	public static final String REDIRECT_URL = "http://www.baidu.com/";
	public static final String SCOPE = "";
	
	public void getWeiboAuth(Activity context){
		Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(context);
        if (mAccessToken.isSessionValid()) {

        }else{
        	context.startActivityForResult(new Intent(context,AuthorActivity.class), 0);
        }     
	}
	
	public String getUID(Context context){
		Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(context);
        if (mAccessToken.isSessionValid()) {
        	return mAccessToken.getUid();
        }else return null;
	}
}

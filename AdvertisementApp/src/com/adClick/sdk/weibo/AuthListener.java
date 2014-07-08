package com.adClick.sdk.weibo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

public class AuthListener implements WeiboAuthListener {

	private Context context;
	AuthListener(Context context){
		this.context = context;
	}
	
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
//		Toast.makeText(context, "onCancel", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onComplete(Bundle values) {
//		Toast.makeText(context, "onComplete", Toast.LENGTH_LONG).show();
        Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
        if (mAccessToken.isSessionValid()) {
            AccessTokenKeeper.writeAccessToken(context, mAccessToken);
    		Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
        } else {
            // 1. 当您未在平台上注册的应用程序的包名与签名时；
            // 2. 当您注册的应用程序包名与签名不正确时；
            // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
    		Toast.makeText(context,"Obtained the code: " + values.getString("code"), Toast.LENGTH_LONG).show();
        }
	}

	@Override
	public void onWeiboException(WeiboException arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "发生错误请联系我们", Toast.LENGTH_LONG).show();
	}

}

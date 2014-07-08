package com.adClick.sdk.weibo;

import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AuthorActivity extends Activity {
	private SsoHandler mSsoHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WeiboAuth mWeiboAuth = new WeiboAuth(this, WeiboAppBinder.APP_KEY, WeiboAppBinder.REDIRECT_URL, WeiboAppBinder.SCOPE);
		mSsoHandler = new SsoHandler(this, mWeiboAuth);
		mSsoHandler.authorize(new AuthListener(this));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        finish();
	}

}

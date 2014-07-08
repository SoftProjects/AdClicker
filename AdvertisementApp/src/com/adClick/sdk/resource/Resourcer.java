package com.adClick.sdk.resource;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.adClick.sdk.weibo.WeiboAppBinder;

public class Resourcer {
	private static String PREFERENCES_NAME = "adclicker";
	private WeiboAppBinder weibo;
	private static Resourcer rs = null;
	private SharedPreferences pref;
	private Context context;

	public static Resourcer instance(Context context){
		if(rs == null) rs = new Resourcer(context);
		return rs;
	}
	
	private Resourcer(Context context){
		this.context = context;
       	pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		weibo = new WeiboAppBinder();
	}

//	public void share(){
//		Intent intent=new Intent(Intent.ACTION_SEND); 
//		intent.setType("text/*"); 
//		intent.putExtra(Intent.EXTRA_SUBJECT, "分享"); 
//		intent.putExtra(Intent.EXTRA_TEXT, "终于可以了!!!");  
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//		context.startActivity(Intent.createChooser(intent, "aa")); 
//	}
	
	public WeiboAppBinder getWeibo() {
        Editor editor = pref.edit();
        editor.putString("a", "aa");
        editor.commit();
		return weibo;
	}
	
	public void sendRequest(){
		
	}
	
//	public Bitmap getBitmap	(final ImageView iv,String url){
//		
//	}
}

package com.adClick.sdk.resource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.adClick.sdk.data.httplinker.HttpLinkClient;
import com.adClick.sdk.data.httplinker.HttpManager;
import com.adClick.sdk.data.httplinker.ImageLoader;
import com.adClick.sdk.weibo.WeiboAppBinder;

@SuppressLint("HandlerLeak")
public class Resourcer {
	private static final String IMAGE_CACHE = "/mnt/sdcard/test/";
	private static final int NUM_OF_IMAGELOADER = 5;
	private static final String PREFERENCES_NAME = "adclicker";
	private WeiboAppBinder weibo;
	private static Resourcer rs = null;
	private Context context;
	private HttpManager httpmanager;
	
	public static Resourcer instance(Context context){
		if(rs == null) rs = new Resourcer(context);
		return rs;
	}
	
	private Resourcer(Context context){
		this.context = context;
//       	pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		weibo = new WeiboAppBinder();
		httpmanager = new HttpManager(IMAGE_CACHE, NUM_OF_IMAGELOADER);
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
//        Editor editor = pref.edit();
//        editor.putString("a", "aa");
//        editor.commit();
		return weibo;
	}
	
//	public void sendRequest(){
//		
//	}
	
	public Bitmap setBitmap	(final ImageView iv,String url){
		ImageLoader il = new ImageLoader(IMAGE_CACHE);
		Bitmap bitmap = il.loadLocalBitmap(url);
		if(bitmap!=null) {
			iv.setImageBitmap(bitmap);
			return bitmap;
		}
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				iv.setImageBitmap((Bitmap)msg.obj);    
			}
		};
		
		httpmanager.getImage(url, handler);
		return null;
	}
	
	public HttpLinkClient getHttpClient(){
		return httpmanager.getClient();
	}
}

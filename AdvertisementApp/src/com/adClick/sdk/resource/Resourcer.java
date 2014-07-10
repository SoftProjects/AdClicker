package com.adClick.sdk.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.adClick.sdk.data.httplinker.HttpLinkClient;
import com.adClick.sdk.data.httplinker.HttpManager;
import com.adClick.sdk.data.httplinker.HttpTask;
import com.adClick.sdk.data.httplinker.ImageLoader;
import com.adClick.sdk.data.httplinker.backgroundService.BackgroundHttpService;
import com.adClick.sdk.weibo.WeiboAppBinder;

public class Resourcer {
	private static final String IMAGE_CACHE = "/mnt/sdcard/test/";
	private static final int NUM_OF_IMAGELOADER = 5;
	private static final String PREFERENCES_NAME = "adclicker";
	private WeiboAppBinder weibo;
	private static Resourcer rs = null;
	private HttpManager httpmanager;
	private Context context;
	
	public static Resourcer instance(Context context){
		if(rs == null) rs = new Resourcer(context);
		return rs;
	}
	
	private Resourcer(Context context){
		this.context = context.getApplicationContext();
		weibo = new WeiboAppBinder();
		httpmanager = new HttpManager(IMAGE_CACHE, NUM_OF_IMAGELOADER,this.context);
	}

//	public void share(){
//		Intent intent=new Intent(Intent.ACTION_SEND); 
//		intent.setType("text/*"); 
//		intent.putExtra(Intent.EXTRA_SUBJECT, "鍒嗕韩"); 
//		intent.putExtra(Intent.EXTRA_TEXT, "缁堜簬鍙互浜�!!!");  
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//		context.startActivity(Intent.createChooser(intent, "aa")); 
//	}
	
	public WeiboAppBinder getWeibo() {
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
	
	public HttpManager getHttpmanager() {
		return httpmanager;
	}

	public void test(){
		HttpTask task = new HttpTask("aaaa", HttpTask.HTTP_GET);
		task.addParameter("aas", "aaa");
		task.addParameter("sadads", "aaa");
		httpmanager.addRequestQueue(task );
//   	pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
//      Editor editor = pref.edit();
//      editor.putString("a", "aa");
//      editor.commit();
	}
}

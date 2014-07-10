package com.adClick.sdk.data.httplinker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.adClick.sdk.data.httplinker.backgroundService.BackgroundHttpService;
import com.adClick.sdk.data.httplinker.database.RequestQueueDB;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;


public class HttpManager {
	private List<ImageLoader> imageLoaders;
	private List<ImageTask> imageQueue;
	private HttpLinkClient client;
	
	private RequestQueueDB db;
	private Context context;
	public HttpManager(String imageCacheDir,int numOfImageLoader,Context context){
		context = context.getApplicationContext();
		client = new HttpLinkClient();
		imageQueue = new ArrayList<ImageTask>();
		imageLoaders = new ArrayList<ImageLoader>();
		
		for (int i = 0; i < numOfImageLoader; i++) {
			imageLoaders.add(new ImageLoader(imageCacheDir));
		}

		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				new Thread(new Runnable() {
					@Override
					public void run() {
						checkImageLoader();
					}
				}).start();
			}
		}, 1000, 2000);
		
		db = new RequestQueueDB(context);
		this.context = context;
	}
	
	public void addRequestQueue(HttpTask task){
		db.insert(task);
		//TODO Service
		context.startService(new Intent(context,BackgroundHttpService.class));
	}
	
	public void runHttp(HttpTask task,HttpHandler handler){
		if (task.getMethod() == HttpTask.HTTP_GET)
			client.asyGet(task.getBaseurl(), task.getParameters(), handler);
		else if(task.getMethod() == HttpTask.HTTP_POST) 
			client.asyPost(task.getBaseurl(), task.getParameters(), handler);
	}	
	
	public String runHttp(HttpTask task){
		String ret = null;
		if (task.getMethod() == HttpTask.HTTP_GET)
			ret = client.synGet(task.getBaseurl(), task.getParameters());
		else if(task.getMethod() == HttpTask.HTTP_POST) 
			ret = client.synPost(task.getBaseurl(), task.getParameters());
		return ret;
	}
	
	public void getImage(String url,Handler handler){
		imageQueue.add(new ImageTask(url, handler));
		checkImageLoader();

	}
	
	private void checkImageLoader(){
		for(ImageLoader il:imageLoaders){
			if(!il.isWorking() && !imageQueue.isEmpty()) {
				try {
					ImageTask it= imageQueue.remove(0);
					Bitmap bitmap = il.downloadBitmap(it.getUrl());
					
					Handler handler = it.getHandler();
					Message msg = handler.obtainMessage(0, bitmap);
					
					handler.sendMessage(msg);
				} catch (IOException e) {}
			}
		}
	}
	
	public HttpLinkClient getClient() {
		return client;
	}

	private class ImageTask{
		private String url;
		private Handler handler;
		private ImageTask(String url,Handler handler){
			this.url = url;
			this.handler = handler;
		}
		private String getUrl() {
			return url;
		}
		private Handler getHandler() {
			return handler;
		}
	}
}





















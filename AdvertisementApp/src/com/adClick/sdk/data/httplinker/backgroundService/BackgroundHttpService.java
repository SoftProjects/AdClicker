package com.adClick.sdk.data.httplinker.backgroundService;

import java.util.Timer;
import java.util.TimerTask;

import com.adClick.sdk.data.httplinker.database.RequestQueueDB;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

public class BackgroundHttpService extends Service {
//	private WakeLock wakeLock;
	private Timer mTimer = new Timer();

	@Override
	public void onCreate() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);   
	    BackgroundHttpReciever receiver = new BackgroundHttpReciever();   
	    registerReceiver(receiver, filter);
		
		mTimer.schedule(new MyTimerTask(), 0 , 10 * 1000);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent!=null && intent.getExtras()!=null){
			mTimer.cancel();
			mTimer.schedule(new MyTimerTask(), 0 , 10 * 1000);
		}
		return START_STICKY;
	}
	
	private boolean checkNetworkState(){
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivity!=null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo(); 
            if (info != null && info.isConnected()){
            	if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }  
            }
		}
        return false;
	}
	
	private class MyTimerTask extends TimerTask{  
		@Override  
	    public void run() {  
			if(!checkNetworkState()) return;
//			new TimeTable(new LogDataBase(BackgroundHttpService.this)).insert("aaa");
			RequestQueueDB db = new RequestQueueDB(BackgroundHttpService.this);
			HttpServiceTask task;
			while((task = db.getHttpTask())!=null){
				String ret = "" ;
//					= Resourcer.instance(BackgroundHttpService.this).getHttpmanager().runHttp(task);
				if(ret!=null){
					//TODO
					db.setSendingSuccess(task.getQid(), true);
				}else{
					db.setSendingSuccess(task.getQid(), false);
				}
			}
			
		}
		

	} 

}

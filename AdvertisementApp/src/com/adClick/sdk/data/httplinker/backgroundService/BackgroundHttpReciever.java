package com.adClick.sdk.data.httplinker.backgroundService;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BackgroundHttpReciever extends BroadcastReceiver{

	public static final String START_SERVICE = "com.adClick.sdk.startBackgroundHttpService";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if ( intent.getAction().equals(Intent.ACTION_TIME_TICK) || 
				 intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)
			){ 
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
				if (!(BackgroundHttpService.class.getName()).equals(service.service.getClassName())){
					context.startService(new Intent(context,BackgroundHttpService.class));
					break;
				}
			}
		} else if(intent.getAction().equals(START_SERVICE)){
			context.startService(new Intent(context,BackgroundHttpService.class));
		}else{
			//TODO
//			Resourcer.instance(context).getHttpmanager().addRequestQueue(task);
		}
	}

}

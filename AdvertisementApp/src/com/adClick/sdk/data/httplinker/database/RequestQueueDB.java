package com.adClick.sdk.data.httplinker.database;

import java.util.List;

import org.apache.http.NameValuePair;

import com.adClick.sdk.data.httplinker.HttpTask;
import com.adClick.sdk.data.httplinker.backgroundService.HttpServiceTask;
import com.adClick.sdk.data.interfaces.*;

import android.content.Context;

public class RequestQueueDB extends DatabaseHelper{
	private static final String dbName = "RequestQueue.db";
	private static int version = 4;
	
	private static DbTable[] tableInfos = new DbTable[]{
		new RequestQueueTable(null),
		new RequestQueueParameterTable(null),
	};
	
	public RequestQueueDB(Context context) {
		super(context, dbName, version, tableInfos);
	}
	
	public long insert(HttpTask task){
		long qid = new RequestQueueTable(this).add(task);
		new RequestQueueParameterTable(this).insertParameter(task.getParameters(),qid);
		return qid;
	}
	
	public HttpServiceTask getHttpTask(){
		HttpServiceTask task = new RequestQueueTable(this).getTask();
		if(task!=null){
			List<NameValuePair> para = new RequestQueueParameterTable(this).getParameter(task.getQid());
			task.addParameter(para);
		}
		return task;
	}
	
	public boolean setSendingSuccess(long qid,boolean isSuccess){
		int n;
		if(isSuccess){
			n = new RequestQueueTable(this).changeState(qid, RequestQueueTable.STATE_SENDING, RequestQueueTable.STATE_SUCCESS);
		}else{
			n= new RequestQueueTable(this).changeState(qid,RequestQueueTable.STATE_SENDING, RequestQueueTable.STATE_WAITING);
		}
		return (n == 1);
	}
}
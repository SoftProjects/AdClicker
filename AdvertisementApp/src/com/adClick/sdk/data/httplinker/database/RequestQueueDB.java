package com.adClick.sdk.data.httplinker.database;

import com.adClick.sdk.data.interfaces.*;

import android.content.Context;

public class RequestQueueDB extends DatabaseHelper{
	private static final String dbName = "RequestQueue.db";
	private static int version = 0;
	
	private static DbTable[] tableInfos = new DbTable[]{
		new RequestQueueTable(null),
		new RequestQueueParameterTable(null),
	};
	
	public RequestQueueDB(Context context) {
		super(context, dbName, version, tableInfos);
	}
}
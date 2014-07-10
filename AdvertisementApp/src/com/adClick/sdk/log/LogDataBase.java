package com.adClick.sdk.log;

import com.adClick.sdk.data.interfaces.DatabaseHelper;
import com.adClick.sdk.data.interfaces.DbTable;

import android.content.Context;

public class LogDataBase extends DatabaseHelper{
	private static String dbName = "logs.db";
	private static int version = 1;
	private static DbTable[] tableInfos = new DbTable[]{
		new TimeTable(null),
	};
	
	public LogDataBase(Context context) {
		super(context, dbName, version, tableInfos);
	}
}

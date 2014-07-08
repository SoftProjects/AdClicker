package com.adClick.sdk.data.database;

import com.adClick.sdk.data.interfaces.*;

import android.content.Context;

public class DataBase extends DatabaseHelper{
	private static final String dbName = "data.db";
	private static int version = 3;
	
	private static DbTable[] tableInfos = new DbTable[]{
//		new CodingDataTable(null),
//		new CheckinDataTable(null),
	};
	
	public DataBase(Context context) {
		super(context, dbName, version, tableInfos);
	}
}
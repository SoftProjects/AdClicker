package com.adClick.sdk.data.httplinker.database;

import com.adClick.sdk.data.database.DataBase;
import com.adClick.sdk.data.interfaces.DbTable;

public class RequestQueueTable extends DbTable{
	
	private DataBase dbHelper;
	public RequestQueueTable(DataBase dbHelper) {
		super();
		addColumns("qid", collumsType.TEXT);
		addColumns("url", collumsType.TEXT);
		addColumns("method", collumsType.TEXT);
		setTableName("requestQueue");
		this.dbHelper = dbHelper;
	}
	
	public void insert(String id){
//		ContentValues values = new ContentValues();
//		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");     
//		String   date   =   sDateFormat.format(new Date());
//		values.put("ID", id);
//		values.put("time", date);
//		dbHelper.getWritableDatabase().insert(getTableName(), "ID", values);
	}
}
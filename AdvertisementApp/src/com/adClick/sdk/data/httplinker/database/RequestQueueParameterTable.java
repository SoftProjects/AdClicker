package com.adClick.sdk.data.httplinker.database;

import com.adClick.sdk.data.database.DataBase;
import com.adClick.sdk.data.interfaces.DbTable;

public class RequestQueueParameterTable extends DbTable{
	
	private DataBase dbHelper;
	public RequestQueueParameterTable(DataBase dbHelper) {
		super();
		addColumns("qid", collumsType.TEXT);
		addColumns("key", collumsType.TEXT);
		addColumns("value", collumsType.TEXT);
		setTableName("requestQueueParameter");
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
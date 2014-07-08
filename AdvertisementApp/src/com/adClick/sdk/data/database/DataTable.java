package com.adClick.sdk.data.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.adClick.sdk.data.interfaces.DbTable;
import android.content.ContentValues;

public class DataTable extends DbTable{
	
	private DataBase dbHelper;
	public DataTable(DataBase dbHelper) {
		super();
		addColumns("ID", collumsType.TEXT);
		addColumns("time", collumsType.TEXT);
		setTableName("checkin");
		this.dbHelper = dbHelper;
	}
	
	public void insert(String id){
		ContentValues values = new ContentValues();
		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");     
		String   date   =   sDateFormat.format(new Date());
		values.put("ID", id);
		values.put("time", date);
		dbHelper.getWritableDatabase().insert(getTableName(), "ID", values);
	}
}
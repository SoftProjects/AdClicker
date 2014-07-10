package com.adClick.sdk.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adClick.sdk.data.interfaces.DbTable;

import android.content.ContentValues;
import android.util.Log;

public class TimeTable extends DbTable{
	
	private LogDataBase dbHelper;
	public TimeTable(LogDataBase dbHelper) {
		super();
		addColumns("time", collumsType.TEXT);
		addColumns("info", collumsType.TEXT);
		setTableName("tiemTable");
		this.dbHelper = dbHelper;
	}
	
	public void insert(String info){
		SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		String   date   =   sDateFormat.format(new Date());
		ContentValues values = new ContentValues();  
		values.put("time", date);
		values.put("info", info);
		dbHelper.getWritableDatabase().insert(getTableName(), "time", values);
		Log.i("change", date);
	}
}

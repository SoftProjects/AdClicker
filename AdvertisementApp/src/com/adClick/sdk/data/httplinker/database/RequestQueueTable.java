package com.adClick.sdk.data.httplinker.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.adClick.sdk.data.httplinker.HttpTask;
import com.adClick.sdk.data.httplinker.backgroundService.HttpServiceTask;
import com.adClick.sdk.data.interfaces.DatabaseHelper;
import com.adClick.sdk.data.interfaces.DbTable;

public class RequestQueueTable extends DbTable{
	public static final String STATE_WAITING = "waiting";
	public static final String STATE_SENDING = "sending";
	public static final String STATE_SUCCESS = "success";
	
	private DatabaseHelper dbHelper;
	public RequestQueueTable(DatabaseHelper dbHelper) {
		super();
		addColumns("url", collumsType.TEXT);
		addColumns("method", collumsType.TEXT);
		addColumns("state", collumsType.TEXT);
		setTableName("requestQueue");
		this.dbHelper = dbHelper;
	}
	
	public long add(HttpTask task){
		ContentValues values = new ContentValues();
		values.put("url", task.getBaseurl());
		values.put("method", task.getMethod());
		values.put("state", STATE_WAITING);
		return dbHelper.getWritableDatabase().insert(getTableName(), "url", values);
	}
	
	public HttpServiceTask getTask(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor ret = dbHelper.getWritableDatabase().query(getTableName(), null, "state=?", new String[]{STATE_WAITING}, null,null, "_ID asc", "1");
		if(ret.moveToFirst()){
			int qid = ret.getInt(ret.getColumnIndex("_ID"));
			String url = ret.getString(ret.getColumnIndex("url"));
			int method = ret.getInt(ret.getColumnIndex("method"));
			HttpServiceTask task = new HttpServiceTask(url, method,qid);
			
			if(changeState(qid,STATE_WAITING,STATE_SENDING,db)!=0) 
				return task;
		}
		return null;
	}
	
	private int changeState(long qid,String from,String to,SQLiteDatabase db){
		ContentValues values = new ContentValues();
		values.put("state", to);
		
		int n = db.update(getTableName(), values, "state=? AND _ID =?", new String[]{from,qid+""});
		
		return n;
	}
	
	int changeState(long qid,String from,String to){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		return changeState(qid,from,to,db);
	}
}
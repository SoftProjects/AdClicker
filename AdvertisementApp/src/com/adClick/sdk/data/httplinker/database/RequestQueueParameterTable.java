package com.adClick.sdk.data.httplinker.database;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.ContentValues;
import android.database.Cursor;

import com.adClick.sdk.data.database.DataBase;
import com.adClick.sdk.data.interfaces.DatabaseHelper;
import com.adClick.sdk.data.interfaces.DbTable;

public class RequestQueueParameterTable extends DbTable{
	
	private DatabaseHelper dbHelper;
	public RequestQueueParameterTable(DatabaseHelper dbHelper) {
		super();
		addColumns("qid", collumsType.TEXT);
		addColumns("key", collumsType.TEXT);
		addColumns("value", collumsType.TEXT);
		setTableName("requestQueueParameter");
		this.dbHelper = dbHelper;
	}
	
	public void insertParameter(List<NameValuePair> para,long qid){
		for(NameValuePair pair:para){
			ContentValues values = new ContentValues();
			values.put("qid", qid);
			values.put("key", pair.getName());
			values.put("value", pair.getValue());
			dbHelper.getWritableDatabase().insert(getTableName(), "qid", values);
		}
	}
	
	public List<NameValuePair> getParameter(long qid){
		Cursor ret = dbHelper.getWritableDatabase().query(getTableName(), null, "qid=?", new String[]{qid+""}, null,null,null);
		List<NameValuePair> ans = new ArrayList<NameValuePair>();
		while(ret.moveToNext()){
			String key = ret.getString(ret.getColumnIndex("key"));
			String value = ret.getString(ret.getColumnIndex("value"));
			ans.add(new BasicNameValuePair(key,value));
		}
		return ans;
	}
}
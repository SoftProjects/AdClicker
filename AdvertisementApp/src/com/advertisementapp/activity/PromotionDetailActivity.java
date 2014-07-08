package com.advertisementapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.advertisementapp.R;
import com.example.advertisementapp.R.layout;
import com.example.advertisementapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class PromotionDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promotion_detail);
		initView();
	}
	
	private void initView(){
		initActivityClauseList();
	}
	
	private void initActivityClauseList(){
		
         
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion_detail, menu);
		return true;
	}
	
	
	
	
	
	
	

}

package com.advertisementapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.advertisementapp.model.PromotionInfo;
import com.example.advertisementapp.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PromotionListAdapter extends BaseAdapter{
	private LayoutInflater inflater = null;  
    private List<PromotionInfo> arrays = null;
    public PromotionListAdapter(Context context, List<PromotionInfo> arrays){
    	this.arrays = arrays;
    	this.inflater = LayoutInflater.from(context);
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrays.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.promotion_list_item, null);
		PromotionInfo index_position = arrays.get(position);
		
		ImageView promotion_photo = (ImageView) v.findViewById(R.id.promotionListItem_photo);
		promotion_photo.setImageResource(R.drawable.image2);
		
		TextView activityTitleView = (TextView) v.findViewById(R.id.promotionListItem_top_column_title);
		activityTitleView.setText(index_position.getActivityTitle());
		
		TextView neededScoreView = (TextView) v.findViewById(R.id.promotionListItem_top_column_score);
		neededScoreView.setText(index_position.getNeededScore()+"");
		
		TextView restNumView = (TextView) v.findViewById(R.id.promotionListItem_bootom_column_restnum);
		restNumView.setText(index_position.getRestNum()+"");
		
		TextView restDayView = (TextView) v.findViewById(R.id.promotionListItem_bootom_column_restday);
		restDayView.setText(index_position.getRestDay()+"");

		TextView locationView = (TextView) v.findViewById(R.id.promotionListItem_bootom_column_location);
		locationView.setText(index_position.getLocation());
		
		return v;
	}

}

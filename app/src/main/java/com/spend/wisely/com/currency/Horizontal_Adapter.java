package com.spend.wisely.com.currency;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spend.wisely.R;

import java.util.List;



public class Horizontal_Adapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater layoutInflater;
	private List<AudioData> all_data_list;
	ImageView iconImageView;
	int pos;
	int targetWidth=140;
	int targetHeight=140;
	public Horizontal_Adapter(Activity activity, List<AudioData> all_data_list) {
		super();
		this.activity = activity;
		this.all_data_list = all_data_list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return all_data_list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public int getViewTypeCount() {

	    if (getCount() != 0)
	        return getCount();

	    return 1;
	}
	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = convertview;
		if (layoutInflater == null) {
			layoutInflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if (view == null) {
			view = layoutInflater
					.inflate(R.layout.horizontal_data_row, null);
		}
			iconImageView = (ImageView) view.findViewById(R.id.icon_img);
           iconImageView.setImageResource(all_data_list.get(position).getId());
        TextView mText=(TextView)view.findViewById(R.id.textView9);
         mText.setText(all_data_list.get(position).getAudio());
        Typeface face= Typeface.createFromAsset(activity.getAssets(), "ptsans.ttf");
        mText.setTypeface(face);
			//pos = position;



		
		return view;
	}



}

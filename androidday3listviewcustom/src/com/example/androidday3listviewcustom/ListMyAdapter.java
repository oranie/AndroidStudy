package com.example.androidday3listviewcustom;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListMyAdapter extends BaseAdapter{
	private Context context = null;
	private ArrayList<ListItem> data = null;
	private init resourse = 0;
	
	public MyListAdapter (Context context , ArrayList<ListItem> data,int resourse){
		this.context = context;
	    this.data = data;
	    this.resourse = resourse;
    }
	
	public int getCount() {
	    return data.size();
    }
	
	public Object getItem(int position) {
		return data.get(position);
    }

	public long getItemId(int position) {
		return data.get(position).getId();
    }

	public View getView(int position,View converView,ViewGroup parent) {
		Activity activity = (Activity)context;
		ListItem item = (ListItem)getItem(position);
	    LinearLayout v = (LinearLayout)activity.getLayoutInflater().inflate(resource, null);
	    ((TextView)v.findViewById(R.id.title)).setText(item.getTitle());
	    ((TextView)v.findViewById(R.id.tag)).setText(item.getTag());
	    ((TextView)v.findViewById(R.id.desc)).setText(item.getDesc());
	    
    }
	
}

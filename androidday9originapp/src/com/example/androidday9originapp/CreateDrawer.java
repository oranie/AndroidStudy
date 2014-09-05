package com.example.androidday9originapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CreateDrawer extends ActionBarActivity{
	private Context context;
	private ListView mDrawerListView;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	private View viewMain;
	
	public CreateDrawer(Context context) {
		this.context = context;
		viewMain = LayoutInflater.from(context).inflate(R.layout.activity_main, null); 
	}
	
	public void createDrawer() {
		mDrawer = (DrawerLayout) viewMain.findViewById(R.id.drawer_layout);
		mDrawerListView =  (ListView) viewMain.findViewById(R.id.drawerList);
		
		DrawerData drawerData = new DrawerData(context);
		ArrayList<String> drawerTitleList = drawerData.getDrawerTitleList();
		ArrayList<HashMap<String, String>> test = drawerData.getDrawerData();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
				android.R.layout.simple_list_item_1, drawerTitleList);
        mDrawerListView.setAdapter(adapter);
        
		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawer,
				R.drawable.ic_launcher,
				R.string.drawer_open,
				R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {}
			@Override
			public void onDrawerOpened(View drawerView) {}
			
			@Override
			public void onDrawerSlide(View drawerView,float slideOffset){
				super.onDrawerSlide(drawerView, slideOffset);
			}
			
			@Override
			public void onDrawerStateChanged(int newState){}
		};
		mDrawer.setDrawerListener(mDrawerToggle);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
    }
}

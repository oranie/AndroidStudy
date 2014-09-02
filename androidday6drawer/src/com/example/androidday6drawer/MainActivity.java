package com.example.androidday6drawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView mainText = (TextView)findViewById(R.id.drawer_main_text);
		mainText.setText("メインコンテンツ部分");
		TextView navigateText = (TextView)findViewById(R.id.drawer_text);
		navigateText.setText("ドロワー部分です。");
		
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawer,
				R.drawable.ic_launcher,
				R.string.drawer_open,
				R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
	            
            }
			@Override
			public void onDrawerOpened(View drawerView) {
	            
            }
			
			@Override
			public void onDrawerSlide(View drawerView,float slideOffset){
				super.onDrawerSlide(drawerView, slideOffset);
			}
			
			@Override
			public void onDrawerStateChanged(int newState){
				
			}
		};
		mDrawer.setDrawerListener(mDrawerToggle);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if (mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

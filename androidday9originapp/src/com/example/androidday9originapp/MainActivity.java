package com.example.androidday9originapp;

import java.util.ArrayList;

import android.R.anim;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	private ListView mDrawerListView;
	
	private ArrayAdapter<String> adapter;
	private ListView list;

	private String url = "http://feeds.feedburner.com/hatena/b/hotentry";
	private String body;
	private RequestQueue mRequestQueue;
	private ArrayList<String> xmlParseArrayList;
	
	public Listener<String> listener = new Listener<String>(){
		@Override
		public void onResponse(String response){
			body = response.toString();
			//Log.d("MainActivity","Main volley return : " + body);
			HatenaXmlParse hatenaXmlParse = new HatenaXmlParse();
			xmlParseArrayList = hatenaXmlParse.xmlParse(body);
			//Log.d("MainActivity","Array : " + xmlParseArrayList.toString());
			setXmlText(xmlParseArrayList);
		}
	};
	
	public ErrorListener errorListener = new ErrorListener() {
		@Override
        public void onErrorResponse(VolleyError error) {
        }
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		onClickReload();
		createDrawer() ;
	}
	
	public void createDrawer() {
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerListView =  (ListView) findViewById(R.id.drawerList);
		
		DrawerData drawerData = new DrawerData();
		ArrayList<String> drawerTitleList = drawerData.getDrawerTitleList();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
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
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	public void setXmlText(ArrayList<String> xmlArrayList) {
		String title = xmlArrayList.get(0);
		TextView titleView = (TextView)findViewById(R.id.title);
		titleView.setText(title);

		
		adapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1,xmlArrayList);
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		xmlArrayList.remove(0);
		
		list.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
			    int visibleItemCount, int totalItemCount) {
					if (firstVisibleItem + visibleItemCount + 3 == totalItemCount) {

                    }
			}
		});
    }

	public void onClickReload() {
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		GetHatenaXml getXml = new GetHatenaXml(Method.GET, url, listener,errorListener);
		mRequestQueue.add(getXml);
		mRequestQueue.start();
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
	

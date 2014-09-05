package com.example.androidday9originapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	public DrawerData mDrawerData;
	public ArrayList<String> mDrawerTitleList ;
	public ArrayList<String> mDrawerUrlList ;
	public ArrayList<HashMap<String, String>> mDrawerDataAll;
	
	private ArrayAdapter<String> adapter;
	private ListView list;

	private String url = "http://feeds.feedburner.com/hatena/b/hotentry";
	private String body;
	private RequestQueue mRequestQueue;
	private ArrayList<String> mXmlParseArrayList;
	private ArrayList<String> mXmlDescriptionList;
	private ArrayList<String> mXmlUrlList;

	
	public Listener<String> listener = new Listener<String>(){
		@Override
		public void onResponse(String response){
			body = response.toString();
			//Log.d("MainActivity","Main volley return : " + body);
			HatenaXmlParse hatenaXmlParse = new HatenaXmlParse();
			mXmlParseArrayList = hatenaXmlParse.xmlParse(body);
			mXmlDescriptionList = hatenaXmlParse.getDescriptionList();
			mXmlUrlList = hatenaXmlParse.getUrlList();
			setXmlText(mXmlParseArrayList,mXmlDescriptionList,mXmlUrlList);
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
		onClickReload(url);
		createDrawer(this);
		//drawerの描画処理分割したいけどnullになるので保留
		//CreateDrawer drawer = new CreateDrawer(this);
		//drawer.createDrawer();
	}
	
	public void createDrawer(Context context) {
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerListView =  (ListView) findViewById(R.id.drawerList);
		
		mDrawerData = new DrawerData(this);
		mDrawerTitleList = mDrawerData.getDrawerTitleList();
		mDrawerUrlList = mDrawerData.getDrawerUrlList();
		mDrawerDataAll = mDrawerData.getDrawerData();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, 
				android.R.layout.simple_list_item_1, mDrawerTitleList);
        mDrawerListView.setAdapter(adapter);
        
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener(){
        	public void onItemClick(AdapterView parent, View view, int position, long id) {
        	   	Log.d("Drawer","ClickOK!!!" + " position:" + Integer.toString(position) + " id:" + Long.toString(id));
        	   	//idを元にURLとタイトルを引いてメインコンテンツ部分を書き換える
        	   	Log.d("MainActivity","Drawer data arraylist:" +mDrawerDataAll.toString());
        	   	for (int i = 0; i < mDrawerData.drawerIdList.size(); i++) {
        	   		Log.d("MainActivity","Drawer data arraylist title:" + mDrawerDataAll.get(i).get("title"));
                }

        	   	String getTitle = mDrawerTitleList.get(position);
        	   	Log.d("MainActivity","drawer click title:" + getTitle);
        	   	String getUrl = mDrawerUrlList.get(position);
        	   	Log.d("MainActivity","drawer click URL:" + getUrl);
        	   	TextView textView = (TextView)findViewById(R.id.title);
        	   	textView.setText(getTitle);
        	   	onClickReload(getUrl);
        	   	mDrawer.closeDrawers();
        	}
        });
        
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

	public void setXmlText(final ArrayList<String> xmlArrayList,
			final ArrayList<String> xmlDescriptionList,
			final ArrayList<String> xmlUrlList) {
		String title = xmlArrayList.get(0);
		TextView titleView = (TextView)findViewById(R.id.title);
		titleView.setText(title);

		adapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1,xmlArrayList);
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		xmlArrayList.remove(0);
		xmlDescriptionList.remove(0);
		xmlUrlList.remove(0);
		//リストのクリック処理
		list.setOnItemClickListener(new OnItemClickListener() {
    	    public void onItemClick(AdapterView parent, View view, int position, long id) {
    	    	Log.d("Drawer","ClickOK!!!" + " position:" + Integer.toString(position) + " id:" + Long.toString(id));
    	    	Intent intent = new Intent(MainActivity.this, com.example.androidday9originapp.ContentActivity.class);
    	    	//タイトル、URL、拾えた詳細をセットして次の画面に引き渡す
        	    int i = position ;
    	    	intent.putExtra("title",xmlArrayList.get(i));
    	    	intent.putExtra("description",xmlDescriptionList.get(i) );
    	    	intent.putExtra("url", xmlUrlList.get(i));
    	    	startActivity(intent);
    	    }			
		});
		//スクロール処理
		list.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
			    int visibleItemCount, int totalItemCount) {
					if (firstVisibleItem + visibleItemCount + 3 == totalItemCount) {
						//スクロール下まで行った時の処理なんかおしゃれなの思いついたら実装する。
                    }
			}
		});
    }

	public void onClickReload(String url) {
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
	

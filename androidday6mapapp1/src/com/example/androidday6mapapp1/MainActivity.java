package com.example.androidday6mapapp1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity {

	private static final String TAG_MAP_FRAGMENT = "MAP_FRAGMENT";
	private SupportMapFragment mMapFragment;
	private GoogleMap mMap;
	private MarkerOptions mMarker;
	private static final LatLng TOKYO = new LatLng(35.681382, 139.766084);
	 
	
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;
	private ListView mDrawerList; 
	private String[] mMapTitles = {"test1","test2"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		TextView mainText  = (TextView)findViewById(R.id.container);
		mainText.setText("メインコンテンツ部分");
		
		TextView navigateText = (TextView)findViewById(R.id.drawer_text);
		navigateText.setText("ドロワー部分です。");
		*/
		
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		/*
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.id.drawer_list, mMapTitles));
		*/


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
		/*
		mDrawer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("TEST","ドロワータッチ！");
			}
		});
		*/
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			        .add(R.id.drawer_layout, new PlaceholderFragment()).commit();
		}
		
		mMapFragment = (SupportMapFragment) getSupportFragmentManager().
				findFragmentByTag(TAG_MAP_FRAGMENT);
		if (mMapFragment == null) {
	        mMapFragment = SupportMapFragment.newInstance();
	        getSupportFragmentManager().beginTransaction()
	        .add(R.id.container, mMapFragment,TAG_MAP_FRAGMENT)
	        .commit();
        }
		
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    
	    if (mMap == null) {
	        mMap = mMapFragment.getMap();
	        if (mMap != null) {
	        	mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	        	mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
	        		.bearing(30.0f)
	        		.target(new LatLng(100.681382, 139.766084))
	        		.zoom(20.0f)
	        		.tilt(60)
	        		.build()));
	            UiSettings ui = mMap.getUiSettings();
	            ui.setCompassEnabled(true);
	            ui.setRotateGesturesEnabled(true);
	            ui.setScrollGesturesEnabled(true);
	            ui.setTiltGesturesEnabled(true);
	            ui.setZoomControlsEnabled(true);
	            ui.setZoomGesturesEnabled(true);

	            LatLng location = new LatLng(100.681382, 139.766084);
	             
	            // マーカーの設定
	            MarkerOptions options = new MarkerOptions();
	            options.position(location);
	            options.title("テストアプリ");
	            options.snippet(location.toString());
	             
	            // マップにマーカーを追加
	            mMap.addMarker(options);
            }
        }
	    
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

	
	// A placeholder fragment containing a simple view.
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
			        false);
			return rootView;
		}
	}

	/*
	public void onClickMountain(View v) {
		//37.42223, −122.08429
		Log.d("TEST","移動スタート！！");
        CameraUpdate camera = CameraUpdateFactory
                .newCameraPosition(new CameraPosition.Builder()
                        .target(TOKYO)
                        .zoom(18.0f).build());
            mMap.animateCamera(camera);
            mMap.moveCamera(camera);
	}
	*/
}

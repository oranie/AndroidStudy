package com.example.androidday5service;

import com.example.androidday5service.R.color;

import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	
	public static final  int ACTIVITY_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SimpleReceiver receiver = new SimpleReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SimpleService.ACTION);
		registerReceiver(receiver, filter);
	}

	public void onStartClick(View viewi) {
		Intent intent = new Intent(this,com.example.androidday5service.SimpleService.class);
		startService(intent);
	    
    }
	public void onStopClick(View viewi) {
		Intent intent = new Intent(this,com.example.androidday5service.SimpleService.class);
		stopService(intent);
    }
	
	
}

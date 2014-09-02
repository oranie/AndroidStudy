package com.example.androidday5service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context,Intent intent){
		Log.d("TEST","Receiver exe");
		String msg = intent.getStringExtra("message");
		Toast.makeText(context, "åªç›éûçè" + msg, Toast.LENGTH_LONG).show();
	}

}

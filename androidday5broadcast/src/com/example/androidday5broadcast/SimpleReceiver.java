package com.example.androidday5broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context,Intent intent){
		String msg = intent.getStringExtra("message");
		Toast.makeText(context, "åªç›éûçè" + msg, Toast.LENGTH_LONG).show();
	}

}

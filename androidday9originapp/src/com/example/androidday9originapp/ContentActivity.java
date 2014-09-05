package com.example.androidday9originapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

public class ContentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		
		Intent intent = getIntent();
        if(intent != null){
            String title = intent.getStringExtra("title");
            Log.d("ContentActivity","title :" + title);
            String description = intent.getStringExtra("description");
            Log.d("ContentActivity","desc :" + description);
            String url = intent.getStringExtra("url");
            Log.d("ContentActivity","url :" + url);
            
            TextView titleView = (TextView) findViewById(R.id.title);
            TextView contentView = (TextView) findViewById(R.id.description);
            TextView urlView = (TextView) findViewById(R.id.url);
            titleView.setText(title);
            contentView.setText(description);
            urlView.setText(url);
            Linkify.addLinks(urlView, Linkify.WEB_URLS);
            
        }
	}

}

package com.example.androidday2activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		Button btn = (Button)findViewById(R.id.subButton1);
		btn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			//Intent intent = new Intent(SubActivity.this,com.example.androidday2activity.MainActivity.class);
			Intent i = getIntent();
			String value = i.getStringExtra("key");
			TextView tView = (TextView)findViewById(R.id.textView1);
			tView.setText(value);
			//startActivity(intent);
			//finish();
		}
		});
	}
}

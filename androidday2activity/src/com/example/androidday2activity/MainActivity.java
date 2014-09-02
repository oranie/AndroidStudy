package com.example.androidday2activity;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button)findViewById(R.id.mainButton1);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText editText = (EditText)findViewById(R.id.editText1);
				Intent intent = new Intent(MainActivity.this,com.example.androidday2activity.SubActivity.class);
				intent.putExtra("key", editText.getText().toString());
				startActivity(intent);
			}
		});
	}

}

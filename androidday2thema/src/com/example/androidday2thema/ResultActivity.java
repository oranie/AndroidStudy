package com.example.androidday2thema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent i = getIntent();
		float value = i.getFloatExtra("result", 0);
		TextView tView = (TextView)findViewById(R.id.resultView);
		tView.setText(Float.toString(value) + "ïbÇ≈äÆóπÇµÇ‹ÇµÇΩÅI");
		
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}
}

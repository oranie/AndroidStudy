package com.example.androidday7style1;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void onClick(View view){
		Button button = (Button)findViewById(view.getId());
		button.setBackgroundColor(Color.BLACK);
		button.setTextColor(Color.GRAY);
		
	}
}

package com.example.androidday5apr;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SubActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		
		Intent intent = this.getIntent();
		String txtName = intent.getStringExtra(Intent.EXTRA_TEXT);
		Toast.makeText(this, String.format("‚±‚ñ‚É‚¿‚í %s‚³‚ñ",txtName),Toast.LENGTH_LONG).show();
	}
}

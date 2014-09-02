package com.example.androidday5apr;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View v) {
		EditText txtKeywd = (EditText)findViewById(R.id.txtKeywd);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT,txtKeywd.getText().toString());
		startActivity(intent);
		/*
		EditText txtKeywd = (EditText)findViewById(R.id.txtKeywd);
		Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(txtKeywd.getText().toString()));
		startActivity(i);
		*/
    }
}

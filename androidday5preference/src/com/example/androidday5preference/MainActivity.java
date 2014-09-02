package com.example.androidday5preference;

import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public int counter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		counter = pref.getInt("counter", 0);
		Editor editor  = pref.edit();
		editor.putInt("counter", ++counter);
		editor.commit();
		
		String msg = "";
		
		msg += "ユーザー名:" + pref.getString("edittext_name", "ゲスト");
		msg += "\nパスワード:" + pref.getString("edittext_pw", "123abc");
		msg += "\n年齢:" + pref.getString("edittext_age", "20");
		Toast.makeText(this,"起動回数は" + Integer.toString(counter), Toast.LENGTH_LONG).show();
	}

	public void onClick(View view) {
		Intent intent = new Intent(this,MyConfig.class);
		startActivity(intent);
	    
    }

}

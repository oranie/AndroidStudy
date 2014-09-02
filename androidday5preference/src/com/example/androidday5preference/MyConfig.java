package com.example.androidday5preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyConfig  extends PreferenceActivity{

    @Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
	}
}

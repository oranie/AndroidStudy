package com.example.androidday4rakugaki;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	public int mWidth;
	public int mHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewGroup root = (ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content);
		mWidth = root.getHeight();
		mHeight = root.getWidth();
		Log.d("TEST","çÇÇ≥" +Integer.toString(mHeight));
		Log.d("TEST","ïù" +Integer.toString(mWidth));
	}
	
	public int getmWidth() {
		return mWidth;
	}

	public void setmWidth(int mWidth) {
		this.mWidth = mWidth;
	}

	public int getmHeight() {
		return mHeight;
	}

	public void setmHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	
}

package com.example.androidday8networkloader;


import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements LoaderCallbacks<String>{
	public String body;
	public String url = "http://googl.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView textView = (TextView)findViewById(R.id.textView);
		Log.d("TEST","Async EXECUTE");
		Bundle args = new Bundle();
		args.putString("url", url);
		getSupportLoaderManager().initLoader(0, args,this);
	}

	@Override
    public Loader<String> onCreateLoader(int arg0, Bundle args) {
		Log.d("TEST",args.toString());
		String url = args.getString("url");
		/*
		HttpGet httpGet = new HttpGet(this, url);
		return httpGet.loadInBackground();
		*/
		return new HttpGet(this, url);
    }


	@Override
    public void onLoadFinished(android.support.v4.content.Loader<String> arg0,
            String arg1) {
	    TextView textView = (TextView)findViewById(R.id.textView);
	    Log.d("TEST","arg0 : " + arg0.toString());
	    Log.d("TEST","arg1 : " + arg1);
	    textView.setText(arg1);
    }

	@Override
    public void onLoaderReset(android.support.v4.content.Loader<String> arg0) {
	    
    }
}
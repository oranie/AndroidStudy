package com.example.androidday8volley;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {
	private RequestQueue mRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		getVolley();
	}

	private void getVolley() {
		String url = "http://www.google.com/uds/GnewsSearch?q=Obama&v=1.0";
		mRequestQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>(){
			@Override
			public void onResponse(JSONObject response){
				Log.d("TEST","response" + response.toString());
				TextView textView = (TextView)findViewById(R.id.text);
				textView.setText(response.toString());
			}
		},null));
		mRequestQueue.start();
    }
}

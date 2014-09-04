package com.example.androidday8network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class HttpGet extends AsyncTask<Void, Void, String> {

	public TextView tView;
	public HttpGet(TextView textView) {
		super();
		tView = textView;
    }

	@Override
    protected String doInBackground(Void... arg0) {
		String body = null;
    	HttpURLConnection connection = null;
    	StringBuilder src = null;
    	URL url = null;
		
		Log.d("TEST","doInBackGrouund execute!");
        try {
        	url = new URL("http://google.com");

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();

            src = new StringBuilder();
            while (true) {
                byte[] line = new byte[1024];
                int size = is.read(line);
                if (size <= 0)
                    break;
                src.append(new String(line, "euc-jp"));
            }
            body = new String(src);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	connection.disconnect();
        }
	    return body;
    }

	protected void onPostExecute(String body) {
		Log.d("TEST","body : " + body);
		tView.setText(body);
		Log.d("TEST","SET OK!!!");
    }
	
}

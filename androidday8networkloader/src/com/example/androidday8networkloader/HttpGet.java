package com.example.androidday8networkloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.widget.TextView;

public class HttpGet extends AsyncTaskLoader<String> {
	private String reqUrl = null;
	private Context context = null;
	
	public HttpGet(Context context, String url) {
		super(context);
        this.reqUrl = url;
        this.context = context;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
    }

	@Override
    public String loadInBackground() {
		String body = null;
    	HttpURLConnection connection = null;
    	StringBuilder src = null;
    	URL url = null;
		
		Log.d("TEST","doInBackGrouund execute!");
        try {
        	url = new URL(reqUrl);

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
	
}

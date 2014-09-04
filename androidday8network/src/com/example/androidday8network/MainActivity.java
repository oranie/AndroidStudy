package com.example.androidday8network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	public String body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView textView = (TextView)findViewById(R.id.textView);
		HttpGet getreq = new HttpGet(textView);
		getreq.execute();
		//getreq.onPostExecute();
		
		
        /*
        final Handler mHandler =  new Handler(){
            public void handleMessage(Message message) {
                textView.setText((String) message.obj);
            };
        };
		
	    // Context 経由でインスタンスを取得する
	    //ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	    //NetworkInfo info = cm.getActiveNetworkInfo();
	    

	    Thread thread1 = new Thread(new Runnable() {
	    	@Override
	    	public void run() {
	            try {
	            	URL url = new URL("http://mixi.jp");
	            	HttpURLConnection connection = null;
	            	StringBuilder src = null;

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
	            }
	            Message msg = new Message();
	            Log.d("TEST",body);
	            msg.obj = new String(body);
	            mHandler.handleMessage(msg);
			}
		  });
		  thread1.start();
		  */
	}
}

package com.example.androidday3progress;

import javax.crypto.spec.PSource;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	private ProgressDialog pd;
	private Thread thread;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					pd.incrementProgressBy(1);
					break;
				case 0:
					pd.dismiss();
					break;
				}
			}
			
		};
	}

	public void onClick(View view) {
		pd = new ProgressDialog(this);
		pd.setTitle("ProgressDialogÇÃäÓñ{");
		pd.setMessage("èàóùíÜ");
		pd.setIndeterminate(false);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		pd.setProgress(0);
		pd.setCancelable(true);
		pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				handler.sendEmptyMessage(0);
				thread = null;
			}
		});
		pd.show();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
	                handler.sendEmptyMessage(1);
	                try {
	                    Thread.sleep(200);
                    } catch (InterruptedException e) {
                    	e.printStackTrace();
                    }
                }
				handler.sendEmptyMessage(0);
			}
		
		});
		thread.start();
	}
}

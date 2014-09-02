package com.example.androidday5service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class SimpleService extends IntentService {
	private final String TAG = "SimpleService";
	private Timer timer = new Timer();
	private static final int NOTIFY_ID = 0;
	private NotificationManager manager;
	public static final String ACTION = "SimpleService Action";
	
	public SimpleService(String name){
		super(name);
		Log.i(TAG,"contructor");
	}
	
	public SimpleService(){
		super("SimpleService");
		Log.i(TAG,"contructor");
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId){
		Log.i(TAG,"onStartCommand");
		Notification notif = new Notification.Builder(this)
			.setContentTitle("SimpleService")
			.setContentText("サービスは起動中です")
			.setSmallIcon(R.drawable.ic_launcher)
			.setWhen(System.currentTimeMillis())
			.setLights(0xffff0000, 500, 500)
			.setTicker("サービスのおしらせ")
			.setContentIntent(
					PendingIntent.getActivity(this,MainActivity.ACTIVITY_ID,
						new Intent(this,com.example.androidday5service.MainActivity.class),
						PendingIntent.FLAG_CANCEL_CURRENT)
			)
				.build();
		manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.notify(NOTIFY_ID,notif);
		
		
		//timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.i(TAG,"onStartCommand");
				Intent intent = new Intent(ACTION);
				intent.putExtra("message", (new Date().toString()));
				sendBroadcast(intent);
			}
		},0,5000);
		return START_STICKY;
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		manager.cancel(NOTIFY_ID);
		Log.i(TAG,"onDestroy");
	}
	
	@Override
	protected void onHandleIntent(Intent intent){
		Log.i(TAG,"onHandlerIntent");
		try {
	        Thread.sleep(5000);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
	}
	
	
	/*
	@Override
	public void onCreate(){
		super.onCreate();
		Log.i(TAG,"onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags,int startid){
		Log.i(TAG,"onStartCommand");
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.i(TAG,"onStartCommand Timer");
			}
		},0,10000);
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent){
		return null;
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i(TAG,"onDestroy");
		timer.cancel();
	}
	*/
}

package com.example.androidday2thema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity  implements OnClickListener {
	public long mStartTime;
	public long mStopTime;
	public boolean mFragReset;
	public int mPushResult;
	public int mOkResult ;
	public float resultMs;
	    
	public boolean mStopFrag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resetExecute();
		Button startBtn = (Button)findViewById(R.id.start);
		Button stopBtn = (Button)findViewById(R.id.stop);
		startBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);

		ArrayList<Integer> list = new ArrayList<Integer>();
		for ( int i = 1; i <= 16; i++){
			list.add(i);
		}
		Collections.shuffle(list);
		
		Resources resources = getResources();
		final TextView textView = (TextView)findViewById(R.id.textView1);
		int listNo = 0;
		for ( int i = 1; i <= 16; i++){
			String idName = String.format("btn%d", i);
			int resutId = resources.getIdentifier(idName, "id", getPackageName());
			Button btn = (Button)findViewById(resutId);
			btn.setOnClickListener(this);
			int l = i - 1;
			String SetNo = Integer.toString((list.get(l)));
			btn.setText(SetNo);
		}
		startBtn.setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						resetExecute();
						setStartTime(System.currentTimeMillis());
						Log.d("HelloWorld","START TIME :" + String.valueOf(mStartTime));
						textView.setText("まずは1を押してください！");
						
					}
				}
		);
			
		stopBtn.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View v) {
					if (mStopFrag == true) {
						setStopTime(System.currentTimeMillis());
						Log.d("HelloWorld", "STOP TIME :" + String.valueOf(mStopTime));
						TextView textView = (TextView)findViewById(R.id.textView1);
						long result = calcTime(getStartTime(),getStopTime());
						resultMs = (float) (result * 0.001);
						Intent intent = new Intent(MainActivity.this,com.example.androidday2thema.ResultActivity.class);
						intent.putExtra("result", resultMs);
						resetExecute();
						textView.setText(R.string.defaultText);
						startActivity(intent);
					}
				}
		});
	}
	
	public void resetExecute() {
		mStartTime = 0;
		mStopTime = 0;
		mFragReset = false;
		mPushResult = 0 ;
		mOkResult = 0;
		mStopFrag = false;
		resultMs = 0F;
		TextView tv = (TextView)findViewById(R.id.textView1);
    }

	public void onClick(View v){
		TextView tv = (TextView)findViewById(R.id.textView1);
				
		int nowId = Integer.valueOf(((Button) v).getText().toString());
		Log.d("TEST", Integer.toString(nowId));
		if ( nowId  == (mOkResult + 1) && nowId < 16 ){
			mOkResult++;
			tv.setText(String.format("%dまでいきました。次は%dです", nowId,(nowId + 1)));
		}
		if ( nowId  == (mOkResult + 1) && nowId == 16) {
			tv.setText(String.format("完了です。STOPを押してください。もう一度やるときはSTARTを押してください。", nowId,++nowId));
			mStopFrag = true;
        }
	}
	
	public long calcTime(long start,long stop) {
		Log.d("HelloWorld", "START :" + String.valueOf(start) + " STOP:" + String.valueOf(stop));
		long result = (stop - start);
		Log.d("HelloWorld", "RESULT TIME :" + String.valueOf(result));
		return result;
    }
	
	public long setStartTime(long start) {
		mStartTime = start;
		return mStartTime;
    }
	
	public long setStopTime(long stop) {
			mStopTime = stop;
		return mStopTime;
    }

	public long getStartTime() {
		return mStartTime;
    }
	
	public long getStopTime() {
		return mStopTime;
    }
}

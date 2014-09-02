package com.example.androidday7sensorsonic;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private SensorManager manager;
	private SensorEventListener listener;
	private List<Sensor> list;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		list = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		
		listener = new SensorEventListener() {
			public void onSensorChanged(SensorEvent event) {
				TextView textView = (TextView)findViewById(R.id.textview1);
				//Toast.makeText(MainActivity.this, "‰Á‘¬“x:" + event.values[0], Toast.LENGTH_LONG).show();
				//Log.d("SENSOR","‰Á‘¬“x:" + Float.toString(event.values[0]));
				if (event.values[0] > 1.0f) {
					textView.setText("‰Á‘¬“x:" + Float.toString(event.values[0]));
                }
			}
			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		};
	}

	@Override
	protected void onResume(){
		if (list.size() > 0){
			manager.registerListener(listener, list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
		}
		super.onResume();
	}

	@Override
	protected void onPause(){
		super.onPause();
		manager.unregisterListener(listener,list.get(0));
	}
}

















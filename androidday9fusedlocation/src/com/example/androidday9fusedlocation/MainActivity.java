package com.example.androidday9fusedlocation;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class MainActivity extends ActionBarActivity {
	
	private LocationClient locationClient;
	private TextView textView;

	private final LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onLocationChanged(final Location location) {
			MainActivity.this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Log.d("TEST", "run START");
					String text = textView.getText().toString();
					text = DateFormat.format("hh:mm:ss.sss", location.getTime())
							+ " - "
							+ location.getLatitude()
							+ "/"  
							+ location.getLongitude()
							+ "/" 
							+ location.getAccuracy()
							+ "\n" + text;
					Log.d("TEST", "run : " +  text.toString());
					textView.setText(text);
				}
			});
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView)findViewById(R.id.text);
		final Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			private boolean isStarted = false;
			
			@Override
			public void onClick(View v) {
				if (! isStarted){
					startLocate();
					button.setText("start");
					Log.d("TEST", "START");
				}else {
					stopLocate();
					button.setText("stop");
					Log.d("TEST", "START");
				}
				isStarted = !isStarted;
			}
		});

	}

	@Override
	protected void onDestroy(){
		stopLocate();
		super.onDestroy();
	}

	private void startLocate(){
		Log.d("TEST", "START LOCATE");
		locationClient = new LocationClient(this, new ConnectionCallbacks() {
			@Override
			public void onDisconnected() {
				locationClient = null;
			}
			
			@Override
			public void onConnected(Bundle bundle) {
				LocationRequest request = LocationRequest.create()
						.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
						.setInterval(5000);
				locationClient.requestLocationUpdates(request, locationListener);
				
			}
		}, new OnConnectionFailedListener(){
			@Override
			public void onConnectionFailed(ConnectionResult result){
				
			}
		});
		locationClient.connect();
		Log.d("TEST", "CONNECT OK");
	}
	
	private void stopLocate() {
	    if (locationClient == null || !locationClient.isConnected() ) {
	        return;
        }
	    locationClient.removeLocationUpdates(locationListener);
	    locationClient.disconnect();
    }
}

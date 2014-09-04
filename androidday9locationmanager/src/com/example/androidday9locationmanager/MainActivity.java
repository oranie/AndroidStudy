package com.example.androidday9locationmanager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	LocationManager manager;
	LocationListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			        .add(R.id.container, new PlaceholderFragment()).commit();
		}
		manager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
		listener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			
			@Override
			public void onProviderEnabled(String provider) {
			}
			
			@Override
			public void onProviderDisabled(String provider) {
			}
			
			@Override
			public void onLocationChanged(Location location) {
				Toast toast = Toast.makeText(MainActivity.this, Double.toString(location.getLongitude()), Toast.LENGTH_LONG);
				toast.show();
			}
		};
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

	@Override
	protected void onPause(){
		super.onPause();
		manager.removeUpdates(listener);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
			        false);
			return rootView;
		}
	}
}

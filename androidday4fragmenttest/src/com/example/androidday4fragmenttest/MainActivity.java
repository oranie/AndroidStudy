package com.example.androidday4fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
	        .add(R.id.container, new MainFragment()).commit();
		}

	}
	
	public static class MainFragment extends Fragment {

		private Button mButton;
		public MainFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
			        false);
			mButton = (Button)rootView.findViewById(R.id.button1);
			mButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
					getFragmentManager().beginTransaction()
			        .add(R.id.container, new SubFragment()).commit();
				}			
			});
			return rootView;
		}
	}
	
	public static class SubFragment extends Fragment {
		private Button mButton;
		
		public SubFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_sub, container,
			        false);
			mButton = (Button)rootView.findViewById(R.id.button1);
			mButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
					getFragmentManager().beginTransaction()
			        .add(R.id.container, new MainFragment()).commit();
				}			
			});
			return rootView;
		}
	}
	
	public static class MenuFragment extends Fragment {
		private Button mButton;
		
		public MenuFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_menu, container,
			        false);

			return rootView;
		}
	}
}

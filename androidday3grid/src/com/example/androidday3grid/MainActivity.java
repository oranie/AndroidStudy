package com.example.androidday3grid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] data = {"‚¢","‚ë","‚Í","‚É","‚Ù","‚Ö","‚Æ","‚¿","‚è","‚Ê","‚é","‚ð"};
		GridLayout grid = (GridLayout)this.findViewById(R.id.GridLayout1);
		for (int i = 0; i < data.length; i++) {
	        Button btn = new Button(this);
	        btn.setText(data[i]);
	        btn.setId(i);
	        grid.addView(btn);
        }
	}

}

package com.example.androidday2togle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		createSpinner();
	}
	
	public void createSpinner() {
		ArrayList<String> list = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		
		for (int i = 1; i < 35; i++){
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
			list.add(format.format(cal.getTime()));
		}
		
		//中身が文字列の配列アダプタを作成。ここでイメージ＋文字みたいなレイアウトに変更することも可能。
		//android.R.layout.simple_spinner_dropdown_itemははじめから定義されている奴
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			this, android.R.layout.simple_spinner_dropdown_item,list);
		
		Spinner spn = (Spinner)this.findViewById(R.id.spnArch);
		spn.setAdapter(adapter);
		
		spn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
	            // TODO Auto-generated method stub
				Spinner spinner = (Spinner) arg0;
				Log.d("TEST",spinner.getSelectedItem().toString());
            }

			@Override
            public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub
            }
		});
			    
    }
	

}

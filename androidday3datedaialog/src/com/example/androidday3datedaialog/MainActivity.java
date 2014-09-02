package com.example.androidday3datedaialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends ActionBarActivity {

	Calendar cal;
	Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cal = Calendar.getInstance();
	}

	public void onDateClick(View view) {
		DatePickerDialog d_date = new DatePickerDialog(
				this, new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker view, int year, int monthOfYear,
					        int dayOfMonth) {
						EditText txtDate = (EditText)MainActivity.this.findViewById(R.id.txtDate);
						txtDate.setText(String.format("%02d%02d%02d", year,monthOfYear + 1,dayOfMonth));
					}
				}, 
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH)
		);
		d_date.show();
    }
	
	public void onTimeClick(View view) {
		TimePickerDialog t_date = new TimePickerDialog(
				this,
				new TimePickerDialog.OnTimeSetListener() {
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						EditText txtDate = (EditText)MainActivity.this.findViewById(R.id.txtTime);
						txtDate.setText(String.format("%02d:%02d", hourOfDay,minute));
					}
				},
				cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE),
				true);
	    t_date.show();
    }
}

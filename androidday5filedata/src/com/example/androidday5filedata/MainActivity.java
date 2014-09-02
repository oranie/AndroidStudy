package com.example.androidday5filedata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	EditText txtMemo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtMemo = (EditText)findViewById(R.id.txtMemo);
	}

	public void onClick(View view) {
		StringBuffer str = new StringBuffer();
		try {
	        BufferedReader reader = new BufferedReader(
	        		new InputStreamReader(
	        				openFileInput("memo.data")));
	        while (reader.ready()) {
	        	str.append(reader.readLine());
	        }
	        reader.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		txtMemo.setText(str.toString());
		/*
		try {
	        BufferedWriter writer = new BufferedWriter(
	        		new OutputStreamWriter(openFileOutput("memo.data",Context.MODE_PRIVATE)));
	        writer.write(txtMemo.getText().toString());
	        writer.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
		}
	    */
    }

}

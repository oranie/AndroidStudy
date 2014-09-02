package com.example.androidday3sialog;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClick(View view) {
		final String[] items = {"A","B","O","AB"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("���t�^")
		.setIcon(R.drawable.ic_launcher)
		.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,String.format("�u%s�v���I������܂���", items[which]) ,
						Toast.LENGTH_LONG).show();
			}
		}).show();
    }
	
	/*
	public void onClick(View view) {
		EditText txtName = (EditText)findViewById(R.id.txtName);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("�_�C�A���O�̊�{")
		.setMessage("Android��Java�ŊJ���ł��܂����H")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("�͂�", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "�����ł��I", Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton("������", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"�s�����ł��I", Toast.LENGTH_LONG).show();
			}
		})
		.setNeutralButton("�L�����Z��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}
	*/
}

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
		builder.setTitle("血液型")
		.setIcon(R.drawable.ic_launcher)
		.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,String.format("「%s」が選択されました", items[which]) ,
						Toast.LENGTH_LONG).show();
			}
		}).show();
    }
	
	/*
	public void onClick(View view) {
		EditText txtName = (EditText)findViewById(R.id.txtName);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ダイアログの基本")
		.setMessage("AndroidはJavaで開発できますか？")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("はい", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "正解です！", Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"不正解です！", Toast.LENGTH_LONG).show();
			}
		})
		.setNeutralButton("キャンセル", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();
	}
	*/
}

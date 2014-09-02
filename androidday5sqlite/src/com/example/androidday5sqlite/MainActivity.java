package com.example.androidday5sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private SimpleDatabaseHelper helper = null;
	private EditText txtIsbn = null;
	private EditText txtTitle = null;
	private EditText txtPrice = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		helper = new SimpleDatabaseHelper(this);
		txtIsbn = (EditText)findViewById(R.id.txtIsbn);
		txtTitle = (EditText)findViewById(R.id.txtTitle);
		txtPrice = (EditText)findViewById(R.id.txtPrice);
	}

	public void onSave(View view) {
	    SQLiteDatabase db = helper.getWritableDatabase();
	    ContentValues cv = new ContentValues();
	    cv.put("isbn", txtIsbn.getText().toString());
	    cv.put("title", txtTitle.getText().toString());
	    cv.put("price", txtPrice.getText().toString());
	    db.insert("books", null, cv);
	    Toast.makeText(this, "データの登録に成功しました", Toast.LENGTH_LONG).show();
	}
	
	public void onDelete(View view) {
		String[] params = {txtIsbn.getText().toString()};
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("books", "isbn = ?", params);
		Toast.makeText(this, "データの削除に成功しました", Toast.LENGTH_LONG).show();
    }

	public void onSearch(View view) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] cols = {"isbn","title","price"};
		String[] params = {txtIsbn.getText().toString()};
		Cursor cs = db.query("books", cols, "isbn = ?", params, null,null,null,null);
		if (cs.moveToFirst()) {
	        txtTitle.setText(cs.getString(cs.getColumnIndex("title")));
	        txtPrice.setText(cs.getString(cs.getColumnIndex("price")));

        } else {
        	Toast.makeText(this,"データがありません", Toast.LENGTH_LONG).show();
        }
    }
}

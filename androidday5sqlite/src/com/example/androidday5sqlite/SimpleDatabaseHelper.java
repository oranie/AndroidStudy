package com.example.androidday5sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
	static final private String DBNAME = "sample.sqlite";
	static final private int VERSION = 1;
	
	public SimpleDatabaseHelper (Context context) {
		super(context, DBNAME, null, VERSION);
    }

	@Override
	public void onOpen(SQLiteDatabase db){
		super.onOpen(db);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		Log.d("DB","CREATE TABLE Go!!");
		db.execSQL("CREATE TABLE books (" +
				"isbn TEXT PRIMARY KEY,title TEXT,price INTEGER)");
		Log.d("DB","CREATE TABLE EXECTE");
		
		String[] isbns = {"978-4-8222-9613-1","978-4-7981-2631-9","978-4-8222-9612-4","978-4-7741-5067-3","978-4-7741-4980-6"};
		String[] titles = {"�A�v������낤!Android����","10���Ŋo����PHP����","�A�v������낤!HTML5����","HTML5�J���|�P�b�g���t�@�����X","Rails 3�|�P�b�g���t�@�����X"};
		int[] prices = {1995,2709,1995,2709,2780};
		db.beginTransaction();
		try {
			SQLiteStatement sqLiteStatement = db.compileStatement(
				"INSERT INTO books(isbn,title,price) VALUES(?,?,?)");
			for (int i = 0; i < isbns.length; i++) {
				sqLiteStatement.bindString(1, isbns[i]);
				sqLiteStatement.bindString(2, titles[i]);
				sqLiteStatement.bindLong(3, prices[i]);
				sqLiteStatement.executeInsert();
		        Log.d("DB","INIT INSERT EXECUTE");
			}
	        db.setTransactionSuccessful();
        } finally {
        	db.endTransaction();
        }
		/*
		db.execSQL("CREATE TABLE books (" +
			"isbn TEXT PRIMARY KEY,title TEXT,price INTEGER)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
			" VALUES('978-4-8222-9613-1','�A�v������낤!Android����',1995)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7981-2631-9','10���Ŋo����PHP����',2709)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-8222-9612-4','�A�v������낤!HTML5����',1995)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7741-5067-3','HTML5�J���|�P�b�g���t�@�����X',2709)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7741-4980-6','Rails 3�|�P�b�g���t�@�����X',2780)");
		*/
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int old_v,int new_v){
		db.execSQL("DROP TABLE IF EXITS books");
		onCreate(db);
	}
}

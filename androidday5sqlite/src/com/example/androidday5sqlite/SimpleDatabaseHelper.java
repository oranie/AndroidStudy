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
		String[] titles = {"アプリを作ろう!Android入門","10日で覚えるPHP入門","アプリを作ろう!HTML5入門","HTML5開発ポケットリファレンス","Rails 3ポケットリファレンス"};
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
			" VALUES('978-4-8222-9613-1','アプリを作ろう!Android入門',1995)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7981-2631-9','10日で覚えるPHP入門',2709)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-8222-9612-4','アプリを作ろう!HTML5入門',1995)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7741-5067-3','HTML5開発ポケットリファレンス',2709)");
		db.execSQL("INSERT INTO books(isbn,title,price" +
				" VALUES('978-4-7741-4980-6','Rails 3ポケットリファレンス',2780)");
		*/
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int old_v,int new_v){
		db.execSQL("DROP TABLE IF EXITS books");
		onCreate(db);
	}
}

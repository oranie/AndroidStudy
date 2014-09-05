package com.example.androidday9originapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DrawerDatabaseHelper extends SQLiteOpenHelper {
	static final private String DBNAME = "drawer.sqlite";
	static final private int VERSION = 1;
	
	public DrawerDatabaseHelper (Context context) {
		super(context, DBNAME, null, VERSION);
    }

	@Override
	public void onOpen(SQLiteDatabase db){
		super.onOpen(db);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE hatebu (" +
				"id long PRIMARY KEY,title TEXT,url TEXT)");
		
		String[] titles = {"総合","一般","社会","政治・経済","生活・人生","スポーツ・芸能・音楽","科学・学問","コンピュータ・IT","ゲーム・アニメ","おもしろ","動画"};
		String[] urls = {"http://feeds.feedburner.com/hatena/b/hotentry","http://b.hatena.ne.jp/hotentry.rss?mode=general",
				"http://b.hatena.ne.jp/hotentry/social.rss","http://b.hatena.ne.jp/hotentry/economics.rss",
				"http://b.hatena.ne.jp/hotentry/life.rss","http://b.hatena.ne.jp/hotentry/entertainment.rss",
				"http://b.hatena.ne.jp/hotentry/knowledge.rss","http://b.hatena.ne.jp/hotentry/it.rss",
				"http://b.hatena.ne.jp/hotentry/game.rss","http://b.hatena.ne.jp/hotentry/fun.rss",
				"http://feeds.feedburner.com/hatena/b/video"};
		db.beginTransaction();
		try {
			SQLiteStatement sqLiteStatement = db.compileStatement(
				"INSERT INTO hatebu(id,title,url) VALUES(?,?,?)");
			for (int i = 0; i < titles.length; i++) {
				sqLiteStatement.bindLong(1, i);
				sqLiteStatement.bindString(2, titles[i]);
				sqLiteStatement.bindString(3, urls[i]);
				sqLiteStatement.executeInsert();
		        Log.d("DB","INIT INSERT EXECUTE");
			}
	        db.setTransactionSuccessful();
        } finally {
        	db.endTransaction();
        }
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int old_v,int new_v){
		//db.execSQL("DROP TABLE IF EXITS books");
		//onCreate(db);
	}
}

package com.example.androidday9originapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DrawerData {
	public ArrayList<Integer> drawerIdList;
	public ArrayList<String> drawerTitleList ;
	public ArrayList<String> drawerUrlList;
	public ArrayList<HashMap<String,String>> drawerDataAll;

	private DrawerDatabaseHelper helper = null;
	private SQLiteDatabase hatebuDatabase;
	
	public DrawerData(Context context) {
		drawerDataAll = new ArrayList<HashMap<String,String>>();
		drawerIdList = new  ArrayList<Integer>();
		drawerTitleList = new ArrayList<String>();
		drawerUrlList = new ArrayList<String>();
		
		helper = new DrawerDatabaseHelper(context);
		drawerSearch();
		Log.d("SQLite RESULT", drawerTitleList.toString());
		
		HashMap<String,String> map = 
				new HashMap<String,String>();

		for (int i = 0; i < drawerIdList.size(); i++) {
			map.put("title", drawerTitleList.get(i));
			//Log.d("DrawerData","title : " + drawerTitleList.get(i));
			map.put("URL", drawerUrlList.get(i));
			//Log.d("DrawerData","Url : " + drawerUrlList.get(i));
			drawerDataAll.add(map);
			Log.d("DrawerData","hashmap add:" + drawerDataAll.get(i).get("title"));
			//Log.d("DrawerData","map :" + map.toString());
			map.clear();
        }
    }
	public void drawerSearch() {
		hatebuDatabase = helper.getWritableDatabase();
		Cursor cursor = hatebuDatabase.query("hatebu",
				new String[] {"id", "title","url"}, null, null, null, null, "id ASC");
        
        int indexid = cursor.getColumnIndex( "id" );
        int titleid = cursor.getColumnIndex( "title" );
        int urlid = cursor.getColumnIndex( "url" );

        while( cursor.moveToNext() ){
            drawerIdList.add(cursor.getInt( indexid ));
        	drawerTitleList.add(cursor.getString( titleid ));
            drawerUrlList.add(cursor.getString( urlid  ));
        }
        Log.d("SQLite",drawerTitleList.toString());
        Log.d("SQLite",drawerUrlList.toString());
    }

	public ArrayList<String> getDrawerTitleList() {
		Log.d("DrawerListGetter",drawerTitleList.toString());
	    return drawerTitleList;
    }
	
	public ArrayList<String> getDrawerUrlList() {
	    return drawerUrlList;
    }
	
	public ArrayList<HashMap<String, String>> getDrawerData() {
		Log.d("DrawerData","hashmap : " + drawerDataAll.toString());
	    return drawerDataAll;
    }
	
}

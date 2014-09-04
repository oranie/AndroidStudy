package com.example.androidday9originapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DrawerData {
	public ArrayList<String> drawerTitleList ;
	public ArrayList<String> drawerUrlList;
	public Map<String, String> drawerData;;
	private DrawerDatabaseHelper helper = null;
	private SQLiteDatabase hatebuDatabase;
	
	public DrawerData(Context context) {
		drawerData = new HashMap<String, String>();
		drawerTitleList = new ArrayList<String>();
		drawerUrlList = new ArrayList<String>();
		
		helper = new DrawerDatabaseHelper(context);
		drawerSearch();
		Log.d("SQLite RESULT", drawerTitleList.toString());
    }
	public void drawerSearch() {
		hatebuDatabase = helper.getWritableDatabase();
		Cursor cursor = hatebuDatabase.query("hatebu",
				new String[] {"id", "title","url"}, null, null, null, null, "id DESC");
        
        int indexid = cursor.getColumnIndex( "id" );
        int titleid = cursor.getColumnIndex( "title" );
        int urlid = cursor.getColumnIndex( "url" );

        while( cursor.moveToNext() ){
            // �������ʂ�Cursor������o��
            //cursor.getInt( id );
        	drawerTitleList.add(cursor.getString( titleid ));
            drawerUrlList.add(cursor.getString( urlid  ));
        }
    }
	
	/*
	public DrawerData() {
		drawerData = new HashMap<String, String>();
		//List<String> list = new ArrayList<String>(Arrays.asList("data1", "data2", "data3"));
		drawerTitleList = new ArrayList<String>(){{
			add("����");
			add("���");
			add("�Љ�");
			add("�����E�o��");
			add("�����E�l��");
			add("�X�|�[�c�E�|�\�E���y");
			add("�Ȋw�E�w��");
			add("�R���s���[�^�EIT");
			add("�Q�[���E�A�j��");
			add("��������");
			add("����");
			add("");
		}};
		
		Log.d("DrawerList",drawerTitleList.toString());
	}
	*/
	
	public ArrayList<String> getDrawerTitleList() {
		Log.d("DrawerListGetter",drawerTitleList.toString());
	    return drawerTitleList;
    }
	public ArrayList<String> getDrawerUrlList() {
	    return drawerUrlList;
    }
	public Map<String, String> getDrawData() {
	    return drawerData;
    }
	

}

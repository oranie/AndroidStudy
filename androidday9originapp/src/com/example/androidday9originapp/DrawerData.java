package com.example.androidday9originapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.androidday5sqlite.SimpleDatabaseHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DrawerData {
	public ArrayList<String> drawerTitleList ;
	public ArrayList<String> drawerUrlList;
	public Map<String, String> drawerData;;
	private SimpleDatabaseHelper helper = null;
	
	public DrawerData() {
		helper = new SimpleDatabaseHelper(this);

    }
	public void drawerSearch(View view) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] cols = {"id","title","url"};
		Cursor cs = db.query("hatebu", cols, "isbn = ?", null, null,null,null,null);
		if (cs.moveToFirst()) {

        } else {
        	Toast.makeText(this,"�f�[�^������܂���", Toast.LENGTH_LONG).show();
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

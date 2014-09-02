package com.example.androidday6actionbar;

import MyTabListener.MyTabListener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
     
     final ActionBar actionBar = getActionBar();
     actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
     actionBar.setDisplayShowTitleEnabled(false);

     Fragment fragment1 = new Fragment1();
     ActionBar.Tab tab1 = actionBar.newTab();
     tab1.setText("タブタイトル1");
     tab1.setIcon(R.drawable.ic_launcher);
     tab1.setTabListener(new MyTabListener(fragment1));
     actionBar.addTab(tab1);

     Fragment fragmet2 = new Fragment2();
     actionBar.addTab(actionBar.newTab().setText("タブタイトル2")
             .setTabListener(new MyTabListener(fragmet2)));
 }

 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.main, menu);
     return true;
 }
}

class Fragment1 extends Fragment {
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
     return inflater.inflate(R.layout.tab1_fragment, container, false);
 }
}

class Fragment2 extends Fragment {
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
     return inflater.inflate(R.layout.tab2_fragment, container, false);
 }
}
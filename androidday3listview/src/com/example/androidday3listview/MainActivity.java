package com.example.androidday3listview;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	ArrayAdapter<String> adapter;
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<String> data = new ArrayList<String>();
		data.add("胡椒");
		data.add("ターメリック");
		data.add("コリアンダー＾");
		data.add("生姜");
		data.add("ニンニク");
		data.add("サフラン");
		for (int i = 0 ;i < 30 ;i++){
			data.add(Integer.toString(i));
		}
		adapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1,data);
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		
		list.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			public void onScroll(AbsListView view, int firstVisibleItem,
			    int visibleItemCount, int totalItemCount) {
					if (firstVisibleItem + visibleItemCount + 3 == totalItemCount) {
	                    adapter.add("新胡椒");
	                    adapter.add("新 ターメリック");
	                    adapter.add("新 コリアンダー");
                    }
			}
		});
		
		/*
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> av,View view,int position,long id) {
				String msg = "選択したのは　";
				for (int i = 0;i < list.getChildCount();i++){
					CheckedTextView check = (CheckedTextView)list.getChildAt(i);
					if (check.isChecked()){
						//本来はStringBuilderを使うべき
						msg += check.getText() + ",";
					}
				}
				msg = msg.substring(0,msg.length() -1);
				Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
            }
		});
		*/
		
		/*
		adapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1,data);
		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		
		
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> av,View view,int position,long id) {
	            adapter.remove((String)((TextView)view).getText());
	            return false;
            }
		});
		*/
		/*
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> av,View view,int position,long id) {
	                adapter.remove((String)((TextView)view).getText());
                }
			} 
		);
		*/
	}

}

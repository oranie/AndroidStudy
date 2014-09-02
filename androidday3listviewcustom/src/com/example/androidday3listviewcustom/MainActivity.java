package com.example.androidday3listviewcustom;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String titles[] = {"title1","title2","title3"};
		String tags[] = {"tag1","tag2","tag3"};
		String descs[] = {"desc1","desc2","desc3"};
	
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
	
		for (int i = 0; i < titles.length;i++){
			HashMap<String, String> item = new HashMap<String,String>();
			item.put("title", titles[i]);
			item.put("tag", tags[i]);
			item.put("desc", descs[i]);
			data.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item,new String[]{"title","tag","desc"},
				new int[]{R.id.title,R.id.tag,R.id.desc}
		);
		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
	}

}

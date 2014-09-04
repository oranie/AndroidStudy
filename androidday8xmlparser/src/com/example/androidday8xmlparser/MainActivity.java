package com.example.androidday8xmlparser;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.TransformerException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.integer;
import android.R.string;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {
	String url = "http://ja.wikipedia.org/w/index.php?title=%E7%89%B9%E5%88%A5:Recentchanges&feed=rss";
	String body;
	private RequestQueue mRequestQueue;
	
	public Listener<String> listener = new Listener<String>(){
		@Override
		public void onResponse(String response){
			body = response.toString();
			String parseText = xmlParse(body);
			setXmlText(parseText);
		}
	};
	
	public ErrorListener errorListener = new ErrorListener() {

		@Override
        public void onErrorResponse(VolleyError error) {
        }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		GetXml getXml = new GetXml(Method.GET, url, listener,errorListener);
		mRequestQueue.add(getXml);
		mRequestQueue.start();
	}
	
	public String xmlParse(String body) {
		XmlPullParser xmlPullParser = Xml.newPullParser();
		String parseText = "";
		boolean itemFrag = false;
		boolean titleFrag = false;
		int counter = 0;
		
		try {
			xmlPullParser.setInput( new StringReader ( body ) );
		} catch (XmlPullParserException e) {
			Log.d("XmlPullParserSample", "Error");
		}
		
		try {
			int eventType;
			eventType = xmlPullParser.getEventType();
		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT && counter < 6) {
		    	if (eventType == XmlPullParser.START_TAG && "item".equals(xmlPullParser.getName())) {
	                itemFrag = true;
                }
		        if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName()) && itemFrag == true) {
		        	
		            //Log.d("XmlPullParserSampleUrl", xmlPullParser.nextText());
		            itemFrag = false;
		            parseText += (counter +":" +  xmlPullParser.nextText() +  "\n");
		            counter++;
		        }
		    }
			
		} catch (Exception e) {
			Log.d("XmlPullParserSample", "Error");
		}
	    return parseText;
    }
	
	public void setXmlText(String body) {
		TextView textView = (TextView)findViewById(R.id.text);
		textView.setText(body);
    }
	
}
	

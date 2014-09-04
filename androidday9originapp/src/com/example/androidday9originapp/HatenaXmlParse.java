package com.example.androidday9originapp;

import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class HatenaXmlParse {
	private XmlPullParser xmlPullParser = Xml.newPullParser();
	private String parseText ;
	private int counter ;
	private StringBuilder sb;
	private ArrayList<String> xmlArrayList;
	
	public HatenaXmlParse(){
		xmlPullParser = Xml.newPullParser();
		parseText = "";
		counter = 0;
		sb = new StringBuilder();
		xmlArrayList = new ArrayList<String>();
	}
	
	public ArrayList<String> xmlParse(String body) {
		try {
			Log.d("HatenaXmlParse","xmlParser get body : " + body);
			xmlPullParser.setInput( new StringReader ( body ) );
		} catch (XmlPullParserException e) {
			Log.d("HatenaXmlParse", "Error");
		}
		
		try {
			int eventType;
			eventType = xmlPullParser.getEventType();
		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT && counter < 30) {
		    	/*if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName())) {
	                itemFrag = true;
                }*/
		        if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName())) {
		        	String title = xmlPullParser.nextText() + "\n";
		        	//Log.d("HatenaXmlParse","counter : " + counter +  " title : " + title);
		        	if (title != null){
		        		//Log.d("HatenaXmlParse", "title : " + title);
		        		sb.append(title +  "\n");
		        		counter++;
		        		xmlArrayList.add(sb.toString());
		        		sb.setLength(0);
		        	}
		        }
		    }
			
		} catch (Exception e) {
			Log.d("HatenaXmlParse", "Error" + e.toString());
		}
		parseText = new String(sb);
		Log.d("HatenaXmlParse", "xmlArrayList : " + xmlArrayList);
		Log.d("HatenaXmlParse", "xmlArrayList count : " + Integer.toString(xmlArrayList.size()));
	    return xmlArrayList;
    }
}

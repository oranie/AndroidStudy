package com.example.androidday9originapp;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class HatenaXmlParse {
	private XmlPullParser xmlPullParser = Xml.newPullParser();
	private int counter ;
	private StringBuilder sb;
	private ArrayList<String> xmlArrayList;
	private ArrayList<String> xmlUrlList;
	private ArrayList<String> xmlDescriptionList;
	private ArrayList<HashMap<String, String>> xmlTitlteUrlList;
	
	public HatenaXmlParse(){
		xmlPullParser = Xml.newPullParser();
		counter = 0;
		sb = new StringBuilder();
		xmlArrayList = new ArrayList<String>();
		xmlUrlList = new ArrayList<String>();
		xmlDescriptionList = new ArrayList<String>();
	}
	
	public ArrayList<String> xmlParse(String body) {
		try {
			//Log.d("HatenaXmlParse","xmlParser get body : " + body);
			xmlPullParser.setInput( new StringReader ( body ) );
		} catch (XmlPullParserException e) {
			Log.d("HatenaXmlParse", "Error");
		}
		
		Pattern p = Pattern.compile("^[0-9a-zA-Z].*$");
		
		try {
			int eventType;
			eventType = xmlPullParser.getEventType();
		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT && counter < 30) {
		    	/*if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName())) {
	                itemFrag = true;
                }*/
		        if (eventType == XmlPullParser.START_TAG && "title".equals(xmlPullParser.getName())) {
		        	String title = xmlPullParser.nextText();
		        	//Log.d("HatenaXmlParse","counter : " + counter +  " title : " + title);
		        	if (title != null){
		        		//Log.d("HatenaXmlParse", "title : " + title);
		        		sb.append(title +  "\n");
		        		counter++;
		        		xmlArrayList.add(sb.toString());
		        		sb.setLength(0);
		        	}
		        }
		        if (eventType == XmlPullParser.START_TAG && "link".equals(xmlPullParser.getName())) {
		        	String url = xmlPullParser.nextText();
		        	Matcher m = p.matcher(url);
		        	//Log.d("HatenaXmlParse","counter : " + counter +  " title : " + title);
		        	if (m.find()){
		        		//Log.d("HatenaXmlParse", "url : " + url);
		        		sb.append(url +  "\n");
		        		xmlUrlList.add(sb.toString());
		        		sb.setLength(0);
		        	}
		        }
		        if (eventType == XmlPullParser.START_TAG && "description".equals(xmlPullParser.getName())) {
		        	String description = xmlPullParser.nextText();
		        	//Log.d("HatenaXmlParse","counter : " + counter +  " title : " + title);
		        	if (description != null){
		        		//Log.d("HatenaXmlParse", "desc : " + description);
		        		sb.append(description +  "\n");
		        		xmlDescriptionList.add(sb.toString());
		        		sb.setLength(0);
		        	}
		        }
		    }
			
		} catch (Exception e) {
			Log.d("HatenaXmlParse", "Error" + e.toString());
		}
		//parseText = new String(sb);
		//Log.d("HatenaXmlParse", "xmlArrayList : " + xmlArrayList);
		//Log.d("HatenaXmlParse", "xmlArrayList count : " + Integer.toString(xmlArrayList.size()));
	    return xmlArrayList;
    }
	
	public ArrayList<String> getUrlList() {
		return xmlUrlList;
    }
	
	public ArrayList<String> getDescriptionList() {
		return xmlDescriptionList;
    }
	
	public ArrayList<HashMap<String, String>> getXmlTitlteUrlList() {
		return xmlTitlteUrlList;
    }
}

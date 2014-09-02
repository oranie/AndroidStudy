package com.example.androidday2webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView wv = (WebView)this.findViewById(R.id.wv);
		wv.setWebChromeClient(new WebChromeClient());
		wv.setWebViewClient(new WebViewClient());
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://www.google.co.jp/");
	}
}

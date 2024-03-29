package com.example.androidday9originapp;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class GetHatenaXml  extends Request<String> {
	private final Listener<String> mListener;
	
	private String charset = "UTF8";

	public GetHatenaXml(int method, String url, Listener<String> listener,ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }
    
    public GetHatenaXml(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    public GetHatenaXml(String url, String charset, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
        this.charset = charset;
    }

	@Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
		Log.d("TEST",response.toString());
	       String parsed;
	        try {
	            if(charset != null) {
	                parsed = new String(response.data, charset);
	            } else {
	                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
	            }
	        } catch (UnsupportedEncodingException e) {
	            parsed = new String(response.data);
	        }
	        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

	@Override
    protected void deliverResponse(String response) {
		mListener.onResponse(response);
    }
}

package com.example.androidday6contentprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
	GridView mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Cursor cursor = managedQuery(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				null,null,null,null);
		cursor.moveToFirst();
		
		
		do{
			Log.d("TEST",cursor.getString(0)); // フルパス
			Log.d("TEST",cursor.getString(1)); // 画像ファイル名
		}while(cursor.moveToNext());
		cursor.moveToFirst();
		
		int fieldIndex;
		Long id;
		int cnt = 0,VolMax = 0;
		HashMap<Integer, Uri>uriMap = new HashMap<Integer,Uri>();
		if (cursor.getCount() > 0){
			do {
				fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
				Log.d("TEST","fieldIndex : " + Integer.toString(fieldIndex));
				id = cursor.getLong(fieldIndex);
				
				Uri bmpUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
				Log.d("TEST","bmpUri : " + bmpUri.toString());
				uriMap.put(cnt, bmpUri);
				cnt++;
	        } while (cursor.moveToNext());
			VolMax = --cnt;
		}
		cnt = 0;
		
		mGrid = (GridView)findViewById(R.id.mGrid);
		Log.d("TEST","adapter value : " + uriMap.toString() + " VolMax : " + Integer.toString(VolMax));
		mGrid.setAdapter(new myAdapter(getContentResolver(),uriMap,VolMax));
	}
	
	public class myAdapter extends BaseAdapter {
		private ContentResolver cr;
		private HashMap<Integer, Uri> hm;
		private int MAX;
		private Bitmap tmpBmp;
		ImageView imageView;
		
		public myAdapter(ContentResolver _cr, HashMap<Integer, Uri> _hm,int max) {
	        cr = _cr;
	        hm = _hm;
	        Log.d("TEST","hm : " + hm.toString());
	        MAX = Math.min(max, 4);
        }
		
		public View getView(int position,View convertView,ViewGroup parent) {
			Log.d("TEST","position : " + Integer.toString(position));
			if (convertView == null){
				imageView = new ImageView(MainActivity.this);
				imageView.setLayoutParams(new GridView.LayoutParams(100,100));
			} else {
				imageView = (ImageView)convertView;
			}
	        
			try {
	            tmpBmp = MediaStore.Images.Media.getBitmap(cr, hm.get(position));
            } catch (FileNotFoundException e) {
	            Log.d("TEST","FILE NOT FOUND");
	            e.printStackTrace();
            } catch (IOException e) {
            	Log.d("TEST","IO EXECEPTION");
            	e.printStackTrace();
            }
			//imageView.setImageBitmap(tmpBmp);
			Log.d("TEST","setImageBitmap" + tmpBmp.toString());
			imageView.setImageBitmap(
				    decodeSampledBitmapFromResource(getResources(), R.id.mGrid, 100, 100));
			
			return imageView;
        }
		
		public final int getCount(){
			return MAX;
		}
		
		public final Object getItem(int position){
			return position;
		}
		
		public final long getItemId(int position){
			return position;
		}
		
		
		public int calculateInSampleSize(
	            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;

	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }

	    return inSampleSize;
	}
		
		public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
		        int reqWidth, int reqHeight) {

		    // First decode with inJustDecodeBounds=true to check dimensions
		    final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    BitmapFactory.decodeResource(res, resId, options);

		    // Calculate inSampleSize
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		    // Decode bitmap with inSampleSize set
		    options.inJustDecodeBounds = false;
		    return BitmapFactory.decodeResource(res, resId, options);
		}
    }
}

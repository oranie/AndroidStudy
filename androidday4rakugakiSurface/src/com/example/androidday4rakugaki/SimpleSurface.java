package com.example.androidday4rakugaki;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;

public class SimpleSurface extends SurfaceView{
	Paint p;
	Path path;
	int mBallX = 30;
	int mBallY = 30;
	int mScreenSizeHeight = 300;
	int mScreeSizewidth = 300;
	
	public SimpleSurface(Context context,AttributeSet attrs,int defStyle) {
	    super(context,attrs,defStyle);
	    init();
	}
	
	public SimpleSurface(Context context,AttributeSet attrs) {
	    super(context,attrs);
	    init();
	}
	
	public SimpleSurface(Context context) {
	    super(context);
	    init();
	}
	
	private void init() {
		/*
		int mScreenSizeHeight = getHeight();
		int mScreeSizeWidth = getWidth();
		Log.d("TEST","‚‚³" +Integer.toString(mScreenSizeHeight));
		Log.d("TEST","•" +Integer.toString(mScreeSizeWidth));
		*/
	
		p = new Paint();
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeJoin(Paint.Join.ROUND);
		path = new Path();
		getHolder().addCallback(
			new SurfaceHolder.Callback() {
				@Override
				public void surfaceDestroyed(SurfaceHolder holder) {
				}
				
				@Override
				public void surfaceCreated(SurfaceHolder holder) {
					draw(holder);
				}
				@Override
				public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				}
			}
		);
    }

	/*‚¨ŠG‚©‚«—p
	private void draw(SurfaceHolder holder){
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawPath(path, p);
		holder.unlockCanvasAndPost(canvas);
	}
	*/
	
	//‰~‚Ì•`‰æ
	private  void draw(SurfaceHolder holder) {
	  final SurfaceHolder h = holder;
	  Thread thread = new Thread(new Runnable() {
		public void run() {
			float x = 1f;
			float y = 1f;
			float nowX = 0;
			float nowY = 0;
			while (true) {
				Canvas canvas = h.lockCanvas();
				canvas.drawColor(Color.WHITE);
				nowX += x;
				nowY += y;
				canvas.drawCircle(nowX , nowY, 20, p);
				if (nowX  > mScreenSizeHeight ){
					x = -1f;
				}
				if (nowY > mScreeSizewidth) {
	                y = -1f;
                }
				if (nowX  < 0 ){
					x = 1f;
				}
				if (nowY < 0) {
	                y = 1f;
                }
				h.unlockCanvasAndPost(canvas);
				try {
	                Thread.sleep(1);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
            }
		}
	  });
	  thread.start();
    }
	
	/*
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(event.getX(), event.getY());
			break;
		}
		draw(getHolder());
		return true;
	}
	*/
}

package com.example.androidday4rakugaki;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleSurface extends SurfaceView{
	Paint p;
	Path path;
	
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
	
	public Paint makeBall() {
		p = new Paint();
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeJoin(Paint.Join.ROUND);
		return p;
    }
	
	private void init() {
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

	//‚¨ŠG‚©‚«—p
	private void drawLine(SurfaceHolder holder){
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawPath(path, p);
		holder.unlockCanvasAndPost(canvas);
	}
	
	
	
	private void ballMove(SurfaceHolder h,int startX,int startY){
		//int startX = 50;
		//int startY = 50;
		int ballXNow = startX ;
		int ballYNow = startY;
		int x = 8;
		int y = 8;
		while (true){
			Canvas canvas = h.lockCanvas();
			canvas.drawColor(Color.WHITE);
			ballXNow = ballXNow + x;
			ballYNow = ballYNow + y;
			if (ballXNow > 540){
				x = (x * -1);
			}
			if (ballXNow < 0){
				x = (x * -1);
			}
			if (ballYNow > 920){
				y = (y * -1);
			}
			if (ballYNow < 0){
				y = (y * -1);
			}
			canvas.drawCircle(ballXNow , ballYNow, 20, p);
			h.unlockCanvasAndPost(canvas);
			try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
		}
    }
	
	//‰~‚Ì•`‰æ
	private  void draw(SurfaceHolder holder) {
	  final SurfaceHolder h = holder;
	  Thread thread1 = new Thread(new Runnable() {
		public void run() {
			ballMove(h,50,50);
		}
	  });
	  thread1.start();
	}
	
	
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
	
}

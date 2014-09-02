package com.example.androidday4surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleSurface extends SurfaceView {
	Paint p;
	
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
		p = new Paint();
		p.setColor(Color.CYAN);
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

	private void draw(SurfaceHolder holder){
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawRect(50, 50,200,200,p);
		holder.unlockCanvasAndPost(canvas);
	}
}

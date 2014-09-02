package com.example.androidday7appcamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View{
	private Path path;
	private Drawable stamp;
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
	    super(context, attrs, defStyleAttr);
	    init();
	 
	}
	public CanvasView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    init();
	}
	public CanvasView(Context context) {
	    super(context);
	    init();
	}
	
	private void init() {
		stamp = (Drawable)findViewById(R.drawable.ic_launcher);
		path = new Path();
    }
	    
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawPicture(stamp);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:	
			path.moveTo(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(event.getX(), event.getY());
		case MotionEvent.ACTION_UP:
			path.lineTo(event.getX(), event.getY());
		}
		invalidate();
		return true;
	}
}

package com.example.androidday4simpleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SimpleView extends View {
	
	public SimpleView(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
	}

	public SimpleView(Context context, AttributeSet attrs) {
	    super(context, attrs);
    }

	public SimpleView(Context context){
		super(context);
	}	
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.CYAN);
		p.setStrokeWidth(5);
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawRect(50, 50,200,200,p);
		/*
		p.setStrokeWidth(30);
		canvas.drawColor(Color.WHITE);
		canvas.drawPoint(100, 100, p);
		*/
	}


}

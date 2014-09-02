package com.example.androidday4textdraw;

import java.net.PasswordAuthentication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class SimpleView extends View {
	private Bitmap bmp;
	
	public SimpleView(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
		initialize();
	}

	public SimpleView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    initialize();
    }

	public SimpleView(Context context){
		super(context);
	    initialize();
	}
	
	public void initialize() {
		bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ham);
    }
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint p = new Paint();
		p.setStrokeWidth(5);
		p.setStyle(Paint.Style.STROKE);
		Path path = new Path();
		path.addOval(new RectF(10,10,300,200),Path.Direction.CW);
		canvas.clipPath(path);
		canvas.drawBitmap(bmp, 0,0,p);
		/*
		path.moveTo(50, 100);
		path.lineTo(250, 250);
		path.lineTo(100, 50);
		canvas.drawPath(path, p);
		//canvas.drawBitmap(bmp, 0, 10,p);
		//canvas.drawBitmap(bmp, new Rect(150,100,250,200),new RectF(50,50,250,250),p);
		//canvas©‘Ì‚ğˆÚ“®A‰ñ“]‚³‚¹‚Ä‚»‚±‚É•`‰æ‚³‚¹‚éB•`‰æ‚µ‚Ä‚¢‚é‚à‚Ì‚ğ‰ñ“]‚µ‚Ä‚¢‚é–ó‚Å‚Í‚È‚¢B
		//‚È‚Ì‚ÅAˆê“xcanvas‚ğ•ÏX‚µ‚Ä‚©‚ç•`‰æ‚·‚é‚à‚Ì‚Í‚·‚×‚Ä‚»‚Ì‰e‹¿‚ğó‚¯‚éB
		//canvas.translate(100, 100);
		//canvas.scale(1.5f, 1.5f);
		//canvas.rotate(60);
		//canvas.drawRect(0, 0,100,100,p);
		 * 
		 */
	}


}

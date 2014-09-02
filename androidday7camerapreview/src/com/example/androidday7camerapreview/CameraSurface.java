package com.example.androidday7camerapreview;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
//import android.graphics.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;

public class CameraSurface extends SurfaceView{
	private Camera camera;
	
	public CameraSurface(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    init();
    }

	public CameraSurface(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    init();
    }

	public CameraSurface(Context context) {
	    super(context);
	    init();
    }

	public void init() {
		SurfaceHolder holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				camera.release();
				camera = null;
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				camera = Camera.open(0);
				try {
					camera.setPreviewDisplay(holder);
                } catch (IOException e) {
                	e.printStackTrace();
                }
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, 
					int format, int width,int height) {
				camera.stopPreview();
				Parameters params = camera.getParameters();
				params.setPreviewSize(width, height);
				camera.setParameters(params);
				camera.startPreview();
			}
		});
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			camera.autoFocus(mAutoFocusListener);
			/*
	        camera.takePicture(new ShutterCallback() {
				@Override
				public void onShutter() {}
	        },
	        null,
	        new PictureCallback() {
				@Override
				public void onPictureTaken(byte[] data, Camera camera) {
					try {
	                    FileOutputStream fos = new FileOutputStream("/mnt/sdcard/sample.jpg");
                    } catch (FileNotFoundException e) {
                    	e.printStackTrace();
                    } catch (IOException e) {
                    	e.printStackTrace();
                    }
					camera.startPreview();
				}
			});
			*/
	        }
		return true;
	}
	
	private Camera.AutoFocusCallback mAutoFocusListener = new Camera.AutoFocusCallback() {
	    public void onAutoFocus(boolean success, Camera camera) {

	        camera.takePicture(new ShutterCallback() {
				@Override
				public void onShutter() {
			    	Log.d("CAMERA","AUTO FOCUS OK!!");
				}
	        },
	        null,
	        new PictureCallback() {
				@Override
				public void onPictureTaken(byte[] data, Camera camera) {
					try {
	                    FileOutputStream fos = new FileOutputStream("/mnt/sdcard/sample.jpg");
                    } catch (FileNotFoundException e) {
                    	e.printStackTrace();
                    } catch (IOException e) {
                    	e.printStackTrace();
                    }
					camera.startPreview();
				}
			});
	    }
	};
}

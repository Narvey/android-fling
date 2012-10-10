package com.example.android_fling;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class GuestureFunActivity extends Activity {
	public static final String DEBUG_TAG="GuestureFunActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guesture_fun);
		FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
		PlayAreaView image = new PlayAreaView(this);
		frame.addView(image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_guesture_fun, menu);
		return true;
	}

	public class PlayAreaView extends View {
		private Matrix translate;  
		private Bitmap droid;
		private GestureDetector gestures;
		protected void onDraw(Canvas canvas) {  
			canvas.drawBitmap(droid, translate, null);  
			Matrix m = canvas.getMatrix();  
			Log.d(GuestureFunActivity.DEBUG_TAG, "Matrix: "+translate.toShortString());  
			Log.d(GuestureFunActivity.DEBUG_TAG, "Canvas: "+m.toShortString());
		}  
		public PlayAreaView(Context context) {
			super(context);
			translate = new Matrix();
			gestures = new GestureDetector(GuestureFunActivity.this,
					new GestureListener(this));
			droid = BitmapFactory.decodeResource(getResources(),
					R.drawable.droid_g);
		}
	}
	private class GestureListener implements GestureDetector.OnGestureListener,  
	GestureDetector.OnDoubleTapListener {  
		PlayAreaView view;  
		public GestureListener(PlayAreaView view) {  
			this.view = view;  
		}  
	    @Override  
	    public boolean onDown(MotionEvent e) {  
	        Log.v(DEBUG_TAG, "onDown");  
	        return true;  
	    }  
	    @Override  
	    public boolean onSingleTapUp(MotionEvent e) {  
	        Log.v(DEBUG_TAG, "onDown");  
	        return true;  
	    }
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}  
	}  

}

package com.example.android_fling;

import javax.xml.datatype.Duration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

public class GestureFunActivity extends Activity {
	public static final String DEBUG_TAG="GestureFunActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_fun);
		FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
		PlayAreaView image = new PlayAreaView(this);
		frame.addView(image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_gesture_fun, menu);
		return true;
	}
	
	public class PlayAreaView extends View {
		private Matrix translate;  
		private Bitmap droid;
		private GestureDetector gestures;
		private Matrix animateStart;  
		private Interpolator animateInterpolator;  
		private long startTime;  
		private long endTime;  
		private float totalAnimDx;  
		private float totalAnimDy;  
		protected void onDraw(Canvas canvas) {  
			canvas.drawBitmap(droid, translate, null);  
			Matrix m = canvas.getMatrix();  
			Log.d(GestureFunActivity.DEBUG_TAG, "Matrix: "+translate.toShortString());  
			Log.d(GestureFunActivity.DEBUG_TAG, "Canvas: "+m.toShortString());
		}  
		public PlayAreaView(Context context) {
			super(context);
			translate = new Matrix();
			gestures = new GestureDetector(GestureFunActivity.this,
					new GestureListener(this));
			droid = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_android);
		}
		@Override  
		public boolean onTouchEvent(MotionEvent event) {  
			return gestures.onTouchEvent(event);  
		}  

		public void move(float dx, float dy) {  
			translate.postTranslate(dx, dy);  
			invalidate();  
		}  

		public void resetLocation(){
			translate=new Matrix();//go back to identity matrix
			invalidate();
		}

		public void animateMove(float dx, float dy, long duration) { 
			animateStart = new Matrix(translate);  
		    animateInterpolator = new OvershootInterpolator();  
		    startTime = System.currentTimeMillis();  
		    endTime = startTime + duration;  
		    totalAnimDx = dx;  
		    totalAnimDy = dy;  
		    post(new Runnable() {  
		        @Override  
		        public void run() {  
		            animateStep();  
		        }
		    });  
		}
		protected void animateStep() {
		    long curTime = System.currentTimeMillis();  
		    float percentTime = (float) (curTime - startTime)  
		            / (float) (endTime - startTime);  
		    float percentDistance = animateInterpolator  
		            .getInterpolation(percentTime);  
		    float curDx = percentDistance * totalAnimDx;  
		    float curDy = percentDistance * totalAnimDy;  
		    translate.set(animateStart);  
		    move(curDx, curDy);  
		    Log.v(DEBUG_TAG, "We're " + percentDistance + " of the way there!");  
		    if (percentTime < 1.0f) {  
		        post(new Runnable() {  
		            @Override  
		            public void run() {  
		                animateStep();  
		            }  
		        });  
		    }  			
		} 
	}
	public class GestureListener implements OnGestureListener, OnDoubleTapListener {
		PlayAreaView view;
		public GestureListener(PlayAreaView playAreaView) {
			this.view=playAreaView;
		}

		@Override
		public boolean onDoubleTap(MotionEvent arg0) {
			Log.v(DEBUG_TAG, "onDoubleTap");  
			view.resetLocation();  
			return true;  
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDown(MotionEvent arg0) {
			Log.v(DEBUG_TAG, "onDown");  
			return true; 
		}

		@Override
		public boolean onFling(MotionEvent arg0, MotionEvent arg1, float velocityX,
				float velocityY) {
			Log.v(DEBUG_TAG, "onFling");  
			final float distanceTimeFactor = (float) 0.4;  
			final float totalDx = (distanceTimeFactor * velocityX/2);  
			final float totalDy = (distanceTimeFactor * velocityY/2);  
			view.animateMove(totalDx, totalDy,  
					(long) (1000 * distanceTimeFactor));  
			return true; 
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			Log.v(DEBUG_TAG, "onLongPress");
			Toast.makeText(GestureFunActivity.this,"What do you want?",Toast.LENGTH_LONG).show();
		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float distanceX,
				float distanceY) {
			Log.v(DEBUG_TAG, "onScroll");  
			view.move(-distanceX, -distanceY);
			return true;  
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

	}

}

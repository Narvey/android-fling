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
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.widget.FrameLayout;

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
    	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
    			float arg3) {
    		//view.move(, dy)
    		return true;
    	}

    	@Override
    	public void onLongPress(MotionEvent arg0) {
    		// TODO Auto-generated method stub

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

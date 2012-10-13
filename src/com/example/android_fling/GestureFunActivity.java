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
    				R.drawable.droid_g);
    	}
    	@Override  
    	public boolean onTouchEvent(MotionEvent event) {  
    	    return gestures.onTouchEvent(event);  
    	}  
    }
}

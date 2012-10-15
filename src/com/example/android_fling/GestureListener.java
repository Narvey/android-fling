package com.example.android_fling;

import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.example.android_fling.GestureFunActivity.PlayAreaView;

public class GestureListener implements OnGestureListener, OnDoubleTapListener {
	PlayAreaView view;
	public GestureListener(PlayAreaView playAreaView) {
		this.view=playAreaView;
	}

	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
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
		Log.v(GestureFunActivity.DEBUG_TAG, "onDown");  
		return true; 
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float distanceX,
			float distanceY) {
			Log.v(GestureFunActivity.DEBUG_TAG, "onScroll");  
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

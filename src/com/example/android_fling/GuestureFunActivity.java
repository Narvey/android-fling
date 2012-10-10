package com.example.android_fling;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.FrameLayout;

public class GuestureFunActivity extends Activity {
	public static final String DEBUG_TAG="GuestureFunActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesture_fun);
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        PlayAreaView image - new PlayAreaView(this);
        frame.addView(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_guesture_fun, menu);
        return true;
    }
}

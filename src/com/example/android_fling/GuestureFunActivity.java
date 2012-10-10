package com.example.android_fling;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GuestureFunActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesture_fun);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_guesture_fun, menu);
        return true;
    }
}

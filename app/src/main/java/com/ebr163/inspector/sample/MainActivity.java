package com.ebr163.inspector.sample;

import android.app.Activity;
import android.os.Bundle;

import com.ebr163.inspector.Inspector;

public class MainActivity extends Activity {

    private Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inspector = new Inspector();
    }

    @Override
    protected void onDestroy() {
        inspector.clear();
        super.onDestroy();
    }
}

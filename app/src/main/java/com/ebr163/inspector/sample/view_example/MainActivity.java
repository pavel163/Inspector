package com.ebr163.inspector.sample.view_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ebr163.inspector.sample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new BaseExampleFragment())
                    .commit();
        }
    }
}
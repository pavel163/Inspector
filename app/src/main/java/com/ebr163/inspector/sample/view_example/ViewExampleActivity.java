package com.ebr163.inspector.sample.view_example;

import android.os.Bundle;

import com.ebr163.inspector.sample.R;

import androidx.appcompat.app.AppCompatActivity;

public class ViewExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new ViewExampleFragment())
                    .commit();
        }
    }
}
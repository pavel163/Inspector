package com.ebr163.inspector.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ebr163.inspector.sample.pair.PairExampleActivity;
import com.ebr163.inspector.sample.value_example.ValueExampleActivity;
import com.ebr163.inspector.sample.view_example.ViewExampleActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.viewExample).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ViewExampleActivity.class)));
        findViewById(R.id.valueExample).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ValueExampleActivity.class)));
        findViewById(R.id.pairExample).setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, PairExampleActivity.class)));
    }
}
package com.ebr163.inspector.sample

import android.content.Intent
import android.os.Bundle

import com.ebr163.inspector.sample.pair_example.PairExampleActivity
import com.ebr163.inspector.sample.value_example.ValueExampleActivity
import com.ebr163.inspector.sample.view_example.ViewExampleActivity

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewExample.setOnClickListener { startActivity(Intent(this@HomeActivity, ViewExampleActivity::class.java)) }
        variableExample.setOnClickListener { startActivity(Intent(this@HomeActivity, ValueExampleActivity::class.java)) }
        pairExample.setOnClickListener { startActivity(Intent(this@HomeActivity, PairExampleActivity::class.java)) }
    }
}
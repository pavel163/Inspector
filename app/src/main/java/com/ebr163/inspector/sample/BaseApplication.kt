package com.ebr163.inspector.sample

import android.app.Application

import com.squareup.leakcanary.LeakCanary


/**
 * Created by Bakht
 * on 11.01.2018.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}

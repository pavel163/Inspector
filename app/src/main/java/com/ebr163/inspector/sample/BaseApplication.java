package com.ebr163.inspector.sample;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Bakht
 * on 11.01.2018.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}

package com.ebr163.inspector;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.ebr163.inspector.inspection.Inspection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class Inspector implements LifecycleObserver {

    private List<Inspection> inspectionList;

    public Inspector() {
        init();
    }

    public Inspector(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        init();
    }

    private void init() {
        inspectionList = new ArrayList<>();
    }

    public void inspect() {
        for (Inspection inspection : inspectionList) {
            inspection.inspect();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        for (Inspection inspection : inspectionList) {
            inspection.unbind();
        }
    }
}

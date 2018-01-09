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
        inspectionList = new ArrayList<>();
    }

    public Inspector(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        inspectionList = new ArrayList<>();
    }

    public boolean inspect() {
        boolean tmp = true;
        for (Inspection inspection : inspectionList) {
            if (tmp){
                tmp = inspection.inspect();
            } else {
                inspection.inspect();
            }
        }
        return tmp;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void clear() {
        for (Inspection inspection : inspectionList) {
            inspection.clear();
        }
    }

    public void addInspection(Inspection inspection) {
        inspectionList.add(inspection);
    }
}

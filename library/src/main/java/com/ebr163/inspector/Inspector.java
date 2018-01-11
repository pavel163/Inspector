package com.ebr163.inspector;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class Inspector implements LifecycleObserver {

    private List<InspectionWrap> inspectionList;
    private OnInspectListener inspectListener;

    public interface OnInspectListener {
        void onInspect(boolean isValid, Map<String, Object> values);
    }

    public Inspector() {
        inspectionList = new ArrayList<>();
    }

    public Inspector(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
        inspectionList = new ArrayList<>();
    }

    public boolean inspect() {
        boolean tmp = true;
        Map<String, Object> values = new HashMap<>();

        for (InspectionWrap inspectionWrap : inspectionList) {
            if (inspectionWrap.getKey() != null){
                values.put(inspectionWrap.getKey(), inspectionWrap.getInspection().getValue());
            }

            if (tmp) {
                tmp = inspectionWrap.getInspection().inspect();
            } else {
                inspectionWrap.getInspection().inspect();
            }
        }

        if (inspectListener != null){
            inspectListener.onInspect(tmp, values);
        }
        return tmp;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void clear() {
        for (InspectionWrap inspectionWrap : inspectionList) {
            inspectionWrap.getInspection().clear();
        }
        inspectionList.clear();
        inspectListener = null;
    }

    public void setInspectListener(OnInspectListener inspectListener) {
        this.inspectListener = inspectListener;
    }

    public void addInspection(Inspection inspection) {
        addInspection(null, inspection);
    }

    public void addInspection(String key, Inspection inspection) {
        inspectionList.add(new InspectionWrap(key, inspection));
    }

    private static class InspectionWrap {

        private final String key;
        private final Inspection inspection;

        private InspectionWrap(String key, Inspection inspection) {
            this.key = key;
            this.inspection = inspection;
        }

        Inspection getInspection() {
            return inspection;
        }

        String getKey() {
            return key;
        }
    }
}

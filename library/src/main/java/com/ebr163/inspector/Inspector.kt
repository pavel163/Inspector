package com.ebr163.inspector

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

/**
 * Created by Bakht
 * on 09.01.2018.
 */

class Inspector(lifecycle: Lifecycle) : LifecycleObserver {

    private var inspectionList: MutableList<InspectionWrap> = mutableListOf()
    private var inspectListener: ((isValid: Boolean, values: Map<String, Any?>) -> Unit)? = null

    init {
        lifecycle.addObserver(this)
    }

    fun inspect(): Boolean {
        var tmp = true
        val values = HashMap<String, Any?>()

        for (inspectionWrap in inspectionList) {
            if (inspectionWrap.key != null) {
                values[inspectionWrap.key] = inspectionWrap.inspection.value
            }

            if (tmp) {
                tmp = inspectionWrap.inspection.inspect()
            } else {
                inspectionWrap.inspection.inspect()
            }
        }

        inspectListener?.invoke(tmp, values)
        return tmp
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear() {
        for (inspectionWrap in inspectionList) {
            inspectionWrap.inspection.clear()
        }
        inspectionList.clear()
        inspectListener = null
    }

    fun setInspectListener(inspectListener: ((isValid: Boolean, values: Map<String, Any?>) -> Unit)) {
        this.inspectListener = inspectListener
    }

    fun addInspection(inspection: Inspection<*>) {
        addInspection(null, inspection)
    }

    fun addInspection(key: String?, inspection: Inspection<*>) {
        inspectionList.add(InspectionWrap(key, inspection))
    }

    private class InspectionWrap(val key: String?, val inspection: Inspection<*>)
}

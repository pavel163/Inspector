package com.ebr163.inspector.variable

fun <Type> inspectionVariable (init: InspectionVariableBuilderDsl<Type>.() -> Unit): InspectionVariable<Type>{
    val builder = InspectionVariableBuilderDsl<Type>()
    builder.init()
    return builder.build()
}
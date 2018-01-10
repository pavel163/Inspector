package com.ebr163.inspector;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public interface Inspection<Type> {

    void clear();

    boolean inspect();

    Type getValue();

    void setErrorEnabled(boolean enabled, String error);
}
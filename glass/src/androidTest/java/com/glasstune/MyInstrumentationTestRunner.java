package com.glasstune;

/**
 * Created by njackson on 16/01/15.
 */

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.test.InstrumentationTestRunner;

import com.glasstune.application.TestApplication;

/**
 * Created by server on 30/03/2014.
 */
public class MyInstrumentationTestRunner extends InstrumentationTestRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return Instrumentation.newApplication(TestApplication.class, context);
    }

}

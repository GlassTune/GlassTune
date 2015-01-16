package com.glasstune.application;

import android.util.Log;

import dagger.ObjectGraph;

/**
 * Created by njackson on 16/01/15.
 */
public class TestApplication extends GlassTuneApplication {

    private static final String TAG = "PB-TestApplication";

    public ObjectGraph getObjectGraph() {
        return _graph;
    }
    public void setObjectGraph(ObjectGraph graph) {
        Log.d(TAG, "set object graph");
        this._graph = graph;
    }

}

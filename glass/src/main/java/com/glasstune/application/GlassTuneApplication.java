package com.glasstune.application;

import android.app.Application;
import android.util.Log;

import com.glasstune.application.modules.AndroidModule;

import java.util.ArrayList;

import dagger.ObjectGraph;

/**
 * Created by njackson on 29/12/14.
 */
public class GlassTuneApplication extends Application {

    private ObjectGraph _graph;

    @Override public void onCreate() {
        super.onCreate();
    }

    public void setObjectGraph(ObjectGraph graph) {
        _graph = graph;
    }

    protected Object[] getModules() {
        ArrayList<Object> module = new ArrayList<Object>();
        module.add(new AndroidModule());

        return module.toArray();
    }

    public void inject(Object object) {
        if(_graph == null) {
            createObjectGraph();
        }
        _graph.inject(object);
    }

    private void createObjectGraph() {
        Log.d("MAINTEST", "Create object graph");
        _graph = ObjectGraph.create(getModules());
    }

}

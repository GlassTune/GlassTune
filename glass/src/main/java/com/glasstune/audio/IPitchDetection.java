package com.glasstune.audio;

/**
 * Created by njackson on 29/12/14.
 */
public interface IPitchDetection {

    void setUpdateInterval(int time_milliseconds);
    void setDelegate(IPitchDetectionHandler handler);
    void start();
    void stop();

}

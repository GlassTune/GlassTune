package com.glasstune.utils;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;

/*
    Smooths a list of values returning the average which are within 2% of the median
 */
public class FrequencySmoother {

    private final double DEVIATION = 0.02;

    private ArrayList<Double> _freqs;

    public FrequencySmoother() {
        _freqs = new ArrayList<Double>();
    }

    public void add(double frequency) {
        _freqs.add(frequency);
    }

    public void clear() {
        _freqs.clear();
    }

    public double getSmoothedAverage() {
        if(_freqs.size() == 0)
            return -1;

        DescriptiveStatistics stats = new DescriptiveStatistics();

        for(double freq : _freqs) {
            stats.addValue(freq);
        }

        double median = stats.getPercentile(50);

        stats = new DescriptiveStatistics();
        for(double freq : _freqs) {
            if(Math.abs((1 - (freq/median))) <= DEVIATION) {
                stats.addValue(freq);
            }
        }

        return stats.getMean();
    }

}

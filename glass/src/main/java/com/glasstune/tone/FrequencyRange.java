package com.glasstune.tone;

/**
 * Created by njackson on 28/12/14.
 */
public class FrequencyRange {

    private final double _lowerFrequency;
    private final double _upperFrequency;

    public double getLowerFrequency() {
        return _lowerFrequency;
    }

    public double getUpperFrequency() {
        return _upperFrequency;
    }

    public FrequencyRange(double lowerFrequency, double upperFrequency) {
        _lowerFrequency = lowerFrequency;
        _upperFrequency = upperFrequency;
    }

}

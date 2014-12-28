package com.glasstune.utils;

import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by server on 27/07/14.
 */
public class FrequencySmootherTest extends TestCase {

    FrequencySmoother _smoother;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        _smoother = new FrequencySmoother();
    }

    public void testSmootherReturnsBGivenAListOfFrequencies() {
        _smoother.add(488.89);
        _smoother.add(489.59);
        _smoother.add(489.38);
        _smoother.add(489.10);
        _smoother.add(505.15);

        assertEquals(489.24,_smoother.getSmoothedAverage());
    }
}

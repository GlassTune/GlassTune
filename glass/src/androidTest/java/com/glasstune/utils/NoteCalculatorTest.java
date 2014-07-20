package com.glasstune.utils;

import android.test.suitebuilder.annotation.SmallTest;

import com.glasstune.pitch.Pitch;
import com.glasstune.tone.Note;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculatorTest extends TestCase {

    @SmallTest
    public void testNoteCalcualtorReturns50WhenNoteC() {
        double percent = NoteCalculator.getPitchBarPercentage(64);
        assertEquals(0.5,percent);
    }

}

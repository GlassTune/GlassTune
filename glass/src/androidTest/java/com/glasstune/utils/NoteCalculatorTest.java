package com.glasstune.utils;

import android.test.suitebuilder.annotation.SmallTest;

import com.glasstune.tone.Note;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculatorTest extends TestCase {

    @SmallTest
    public void testNoteCalcualtorReturns50WhenNoteC() {
        double percent = NoteCalculator.getPitchBarPercentage(Note.C2.frequency);
        assertEquals(0.5,percent);
    }

    public void testNoteCalcualtorReturns25WhenNoteHalfwayBetweenCAndB() {
        double percent = NoteCalculator.getPitchBarPercentage(63.575);
        assertEquals(0.25,percent);
    }

    public void testNoteCalcualtorReturns75WhenNoteHalfwayBetweenCAndCSharp() {
        double percent = NoteCalculator.getPitchBarPercentage(66.188);
        assertEquals(0.70,percent);
    }

    public void testWhenNoNoteFoundReturns0() {
        double percent = NoteCalculator.getPitchBarPercentage(600000);
        assertEquals(0.0,percent);
    }

    //156.40

}

package com.glasstune.utils;

import android.test.suitebuilder.annotation.SmallTest;

import com.glasstune.tone.FrequencyRange;
import com.glasstune.tone.Note;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculatorTest extends TestCase {

    @SmallTest
    public void testGetFrequencyRangeReturnsCorrectValue() {
        FrequencyRange range = NoteCalculator.getFrequencyRange(Note.B4, Note.A4S, Note.C5);
        assertEquals(480.02, range.getLowerFrequency());
        assertEquals(508.565, range.getUpperFrequency());
    }

    @SmallTest
    public void testNoteCalcualtorReturns320WhenNoteC() {
        double pos = NoteCalculator.getPitchBarPosition(Note.C2.frequency,640);
        assertEquals(320.0,pos);
    }

    @SmallTest
    public void testNoteCalcualtorReturns160WhenNoteHalfwayBetweenBflatAndB() {
        double pos = NoteCalculator.getPitchBarPosition(60.8725, 640);
        assertEquals(160.0,pos);
    }

    @SmallTest
    public void testNoteCalcualtorReturns480WhenNoteHalfwayBetweenBAndC() {
        double pos = NoteCalculator.getPitchBarPosition(62.6575, 640);
        assertEquals(480.0,pos);
    }

    @SmallTest
    public void testWhenNoNoteFoundReturns0() {
        double pos = NoteCalculator.getPitchBarPosition(600000, 640);
        assertEquals(0.0,pos);
    }

    //156.40

}

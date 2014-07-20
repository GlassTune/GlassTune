package com.glasstune.utils;

import android.test.suitebuilder.annotation.SmallTest;

import com.glasstune.pitch.Pitch;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculatorTest extends TestCase {

    @SmallTest
    public void testNoteCalcualtorReturnsMainNoteCWhenPitch22220() {

        Pitch pitch = null;
        assertEquals("C",pitch.get_mainNote());

    }

}

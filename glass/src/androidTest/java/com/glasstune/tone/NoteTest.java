package com.glasstune.tone;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteTest extends TestCase {

    @SmallTest
    public void testNoteIsCWhenFrequencyis64k() {
        Note note = Note.getNearestNote(64.453125);
        assertEquals('C',note.letter);
    }

    @SmallTest
    public void testPreviousNoteIsBWhenCurrentNoteisC() {
        Note note = Note.getPreviousNote(Note.C2);
        assertEquals(note,Note.B1);
    }

    @SmallTest
    public void testPreviousNoteIsCSharpWhenCurrentNoteisC() {
        Note note = Note.getNextNote(Note.C2);
        assertEquals(note,Note.C2S);
    }

}

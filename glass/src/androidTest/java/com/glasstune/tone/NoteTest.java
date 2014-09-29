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
    public void testNextNoteIsCSharpWhenCurrentNoteisC() {
        Note note = Note.getNextNote(Note.C2);
        assertEquals(note,Note.C2S);
    }

    @SmallTest
    public void testNextNoteIsUnknownWhenCurrentNoteisB8() {
        Note note = Note.getNextNote(Note.B8);
        assertEquals(note,Note.UNKNOWN);
    }

    @SmallTest
    public void testPreviousNoteIsUnknownWhenCurrentNoteisA0() {
        Note note = Note.getPreviousNote(Note.A0);
        assertEquals(note,Note.UNKNOWN);
    }

    @SmallTest
    public void testPreviousNoteIsUnknownWhenCurrentNoteisUnknown() {
        Note note = Note.getPreviousNote(Note.UNKNOWN);
        assertEquals(note,Note.UNKNOWN);
    }

    @SmallTest
    public void testNextNoteIsUnknownWhenCurrentNoteisUnknown() {
        Note note = Note.getPreviousNote(Note.UNKNOWN);
        assertEquals(note,Note.UNKNOWN);
    }

}

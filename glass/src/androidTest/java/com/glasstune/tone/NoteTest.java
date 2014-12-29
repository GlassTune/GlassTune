package com.glasstune.tone;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

/**
 * Created by server on 20/07/14.
 */
public class NoteTest extends TestCase {

    @SmallTest
    public void testWhenFrequencyisOverMaxFrequencyReturnNoNote() {
        Note note = Note.getNearestNote(800000);
        assertEquals(Note.UNKNOWN,note);
    }

    @SmallTest
    public void testNoteIsGSharpWhenFrequencyis406k() {
        Note note = Note.getNearestNote(406);
        assertEquals('G',note.letter);
        assertEquals(true,note.sharp);
    }

    @SmallTest
    public void testNoteIsGWhenFrequencyis403k() {
        Note note = Note.getNearestNote(403);
        assertEquals('G',note.letter);
        assertEquals(false,note.sharp);
    }

    @SmallTest
    public void testNoteIsGWhenFrequencyis398k() {
        Note note = Note.getNearestNote(398);
        assertEquals('G',note.letter);
        assertEquals(false,note.sharp);
    }

    @SmallTest
    public void testNoteIsFSharpWhenFrequencyis376k() {
        Note note = Note.getNearestNote(376);
        assertEquals('F',note.letter);
        assertEquals(true,note.sharp);
    }

    @SmallTest
    public void testNoteIsFWhenFrequencyis355k() {
        Note note = Note.getNearestNote(355);
        assertEquals('F',note.letter);
        assertEquals(false,note.sharp);
    }

    @SmallTest
    public void testNoteIsBbWhenFrequencyis466k() {
        Note note = Note.getNearestNote(466.271);
        assertEquals('A',note.letter);
        assertEquals(true,note.sharp);
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

    @SmallTest
    public void testNoteToStringIsCWhenNoteIsC() {
        assertEquals("C",Note.C4.toString());
    }

    @SmallTest
    public void testNoteToStringIsCSharpWhenNoteIsC() {
        assertEquals("C#",Note.C4S.toString());
    }

    @SmallTest
    public void testNoteToStringIsBbWhenNoteIsASharp() {
        assertEquals("Bb",Note.A4S.toString());
    }

    @SmallTest
    public void testNoteToStringIsEbWhenNoteIsDSharp() {
        assertEquals("Eb",Note.D4S.toString());
    }

}

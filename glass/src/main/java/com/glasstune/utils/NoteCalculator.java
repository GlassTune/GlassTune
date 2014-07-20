package com.glasstune.utils;

import com.glasstune.tone.Note;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculator {

    public static double getPitchBarPercentage(double frequency) {
        Note currentNote = Note.getNearestNote(frequency);
        Note flatNote = Note.getPreviousNote(currentNote);
        Note sharpNote = Note.getNextNote(currentNote);

        return (frequency - flatNote.frequency) / (sharpNote.frequency - flatNote.frequency);
    }

}

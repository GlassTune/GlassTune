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

        if(flatNote == Note.UNKNOWN || sharpNote == Note.UNKNOWN)
            return 0.0;

        if(currentNote.frequency == frequency) {
            return 0.5;
        }else if(currentNote.frequency > frequency) {
            double value = (currentNote.frequency - frequency) / (currentNote.frequency - flatNote.frequency);
            return round2DP(value/2);
        }else if(currentNote.frequency < frequency) {
            double value = (currentNote.frequency - frequency) / (currentNote.frequency - sharpNote.frequency);
            return round2DP((value + 0.5));
        }

        return 0.0;
    }

    private static double round2DP(double value) {
        return Math.round(value * 100) / 100D;
    }

}

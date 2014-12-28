package com.glasstune.utils;

import android.util.Log;

import com.glasstune.tone.FrequencyRange;
import com.glasstune.tone.Note;

/**
 * Created by server on 20/07/14.
 */
public class NoteCalculator {

    /*
     * Returns the position in pixels to place the pitch bar
     */
    public static int getPitchBarPosition(double frequency, double width) {
        Note currentNote = Note.getNearestNote(frequency);
        Note flatNote = Note.getPreviousNote(currentNote);
        Note sharpNote = Note.getNextNote(currentNote);

        if(flatNote == Note.UNKNOWN || sharpNote == Note.UNKNOWN)
            return 0;

        FrequencyRange range = getFrequencyRange(currentNote,flatNote,sharpNote);

        double halfWidth = width / 2.0;
        double w = width;
        double pos = 0.0;

        if(currentNote.frequency - frequency == 0) {
            pos = halfWidth;
        } else if(frequency > currentNote.frequency) {
            double rangeSize = range.getUpperFrequency() - currentNote.frequency;
            double dif = range.getUpperFrequency() - frequency;
            pos =  Math.round(width - ((dif/rangeSize) * halfWidth));
        } else {
            double rangeSize = currentNote.frequency - range.getLowerFrequency();
            double dif = currentNote.frequency - frequency;
            pos =  Math.round(halfWidth - ((dif/rangeSize) * halfWidth));
        }

        return (int)pos;
    }

    protected static double getRangePerPixel(double upper, double lower, double frequency) {
        return (frequency - lower) / (upper - lower);
    }

    protected static FrequencyRange getFrequencyRange(Note note, Note flatNote, Note sharpNote) {
        double lowerFreq = note.frequency - ((note.frequency - flatNote.frequency) / 2);
        double upperFreq = note.frequency + ((sharpNote.frequency - note.frequency) / 2);

        return new FrequencyRange(lowerFreq,upperFreq);
    }

}

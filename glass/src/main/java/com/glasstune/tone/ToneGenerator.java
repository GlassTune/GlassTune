package com.glasstune.tone;

/**
 * This is a class responsible for generating tones within the application.
 *
 * @author Carl Jokl
 * @since 19th of July 2014
 */
public class ToneGenerator {

    /**
     * Create a new instance of a ToneGenerator.
     */
    public ToneGenerator() {

    }

    /**
     * Start playing the specified note.
     * Any existing playing note will be stopped in favour
     * of playing this note.
     *
     * @param note The note to be played.
     */
    public void startPlayingTone(Note note) {
        final double frequency = note.frequency;
    }

    /**
     * Stop playing any currently playing note if any.
     */
    public void stopPlayingTone() {

    }
}
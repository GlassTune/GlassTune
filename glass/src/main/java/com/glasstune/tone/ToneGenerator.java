package com.glasstune.tone;

/**
 * This is a class responsible for generating tones within the application.
 *
 * @author Carl Jokl
 * @since 19th of July 2014
 */
public class ToneGenerator {

    private ToneGeneratorRunner toneRunner;

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
    public synchronized void startPlayingTone(Note note) {
        stopPlayingTone();
        toneRunner = new ToneGeneratorRunner(note.frequency, 1, 8000);
        new Thread(toneRunner).start();
    }

    /**
     * Stop playing any currently playing note if any.
     */
    public synchronized void stopPlayingTone() {
        if (toneRunner != null) {
            toneRunner.stop();
            toneRunner = null;
        }
    }
}
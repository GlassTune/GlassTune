package com.glasstune.tone;

import android.media.AudioTrack;

/**
 * This is a class responsible for generating tones within the application.
 *
 * @author Carl Jokl
 * @since 19th of July 2014
 */
public class ToneGenerator {

    private final int SAMPLE_LENGTH = 1;
    private final int SAMPLE_RATE = 8000;
    private final AudioTrack tone;

    /**
     * Create a new instance of a ToneGenerator.
     */
    public ToneGenerator(final Note defaultNote) {
        tone = ToneGeneratorUtil.createSample(defaultNote.frequency, SAMPLE_LENGTH, SAMPLE_RATE);
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
        final byte[] pcm = ToneGeneratorUtil.toPCM(ToneGeneratorUtil.toAmplitudeValues(note.frequency, SAMPLE_LENGTH, SAMPLE_RATE));
        tone.write(pcm, 0, pcm.length);
        tone.play();
    }

    public synchronized void playFrequency(Double frequency) {
        stopPlayingTone();
        final byte[] pcm = ToneGeneratorUtil.toPCM(ToneGeneratorUtil.toAmplitudeValues(frequency, SAMPLE_LENGTH, SAMPLE_RATE));
        tone.write(pcm, 0, pcm.length);
        tone.play();
    }

    public synchronized void pause() {
        if (tone.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            tone.pause();
        }
    }

    /**
     * Stop playing any currently playing note if any.
     */
    public synchronized void stopPlayingTone() {
        if (tone.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            tone.stop();
        }
    }
}
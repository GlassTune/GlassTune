package com.glasstune.tone;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * This is a runnable that will generate a tone until receiving notification to stop.
 *
 * @author Carl Jokl
 * @since 20/07/2014.
 */
public class ToneGeneratorUtil {

    /**
     * Method to convert an amplitude value into Pulse Code Modulation bytes.
     *
     * @param samples The raw amplitude value.
     * @return An array of Pulse Code Modulation audio bytes.
     */
    public static final byte[] toPCM(final double[] samples) {
        final byte[] pcm = new byte[samples.length * 2];
        int destination = 0;
        for (double sample : samples) {
            final short val = (short) (sample * 32767);
            // in 16 bit wav PCM, first byte is the low order byte
            pcm[destination++] = (byte) (val & 0x00ff);
            pcm[destination++] = (byte) ((val & 0xff00) >>> 8);
        }
        return pcm;
    }

    /**
     * Create waveform amplitude values calculated for the frequency, sample rate and duration.
     *
     * @param frequency The frequency in hertz of the tone to be generated.
     * @param duration The duration of the tone.
     * @param sampleRate The number of samples per second.
     * @return An array of amplitude values.
     */
    public static final double[] toAmplitudeValues(final double frequency, final int duration, final int sampleRate) {
        final double[] amplitudes = new double[sampleRate * duration];
        final double wavePerSample = sampleRate / frequency;
        final double twoPi = Math.PI * 2;
        for (int index = 0; index < amplitudes.length; ++index) {
            amplitudes[index] = Math.sin((twoPi * index) / wavePerSample);
        }
        return amplitudes;
    }

    /**
     * Create a new instance of an AudioTrack to generate
     * a tone at a given frequency
     *
     * @param frequency The frequency in hertz of the tone to be generated.
     * @param cycleDuration The duration of an individual cycle of a tone before looping.
     * @param sampleRate The rate at which the sample is generated.
     */
    public static AudioTrack createSample(final double frequency, final int cycleDuration, final int sampleRate) {
        final byte[] pcm = toPCM(toAmplitudeValues(frequency, cycleDuration, sampleRate));
        final AudioTrack sample = new AudioTrack(AudioManager.STREAM_MUSIC,
                                sampleRate,
                                AudioFormat.CHANNEL_OUT_DEFAULT,
                                AudioFormat.ENCODING_PCM_16BIT,
                                pcm.length,
                                AudioTrack.MODE_STATIC);
        sample.write(pcm, 0, pcm.length);
        return sample;
    }
}

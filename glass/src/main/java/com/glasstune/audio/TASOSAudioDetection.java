package com.glasstune.audio;

import android.os.AsyncTask;
import android.util.Log;

import com.glasstune.utils.FrequencySmoother;

import be.hogent.tarsos.dsp.AudioEvent;
import be.hogent.tarsos.dsp.MicrophoneAudioDispatcher;
import be.hogent.tarsos.dsp.pitch.PitchDetectionHandler;
import be.hogent.tarsos.dsp.pitch.PitchDetectionResult;
import be.hogent.tarsos.dsp.pitch.PitchProcessor;

/**
 * Created by njackson on 29/12/14.
 */
public class TASOSAudioDetection implements IPitchDetection, PitchDetectionHandler {

    private Thread _pitchThread;
    private MicrophoneAudioDispatcher _dispatcher;

    private final int SAMPLE_RATE = 22050;
    private final int BUFFER_SIZE = 1024;
    private final int OVERLAP = 512;

    private int _updateInterval = 300;

    private FrequencySmoother _smoother;

    private IPitchDetectionHandler _handler;
    private Updater _updater;

    public void setUpdateInterval(int time_milliseconds) {
        _updateInterval = time_milliseconds;
    }

    public TASOSAudioDetection() {
        _dispatcher = new MicrophoneAudioDispatcher(SAMPLE_RATE,BUFFER_SIZE,OVERLAP);
        _smoother = new FrequencySmoother();
    }

    @Override
    public void setDelegate(IPitchDetectionHandler handler) {
        _handler = handler;
    }

    @Override
    public void start() {
        _updater = new Updater();
        _updater.execute(_updateInterval);

        _dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, SAMPLE_RATE, BUFFER_SIZE, this));
        _pitchThread = new Thread(_dispatcher,"Audio dispatching");
        _pitchThread.start();
    }

    @Override
    public void stop() {
        _dispatcher.stop();
        _pitchThread.interrupt();

        _updater.cancel(false);
    }

    /**
     * Callback for the pitch detection thread, called on new pitch detected
     */
    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {

        final float pitch = pitchDetectionResult.getPitch();

        if(pitch != -1 && audioEvent.getRMS() > 0.005){
            _smoother.add(pitch);
        }

    }

    private class Updater extends AsyncTask<Integer, Double, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            while(!isCancelled()) {
                final double average = _smoother.getSmoothedAverage();
                _smoother.clear();
                publishProgress(average);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Double... frequency) {
            _handler.handlePitch(frequency[0]);
        }

    }
}

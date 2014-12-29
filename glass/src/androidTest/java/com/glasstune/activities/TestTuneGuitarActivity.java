package com.glasstune.activities;

import android.location.LocationManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.transition.Visibility;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glasstune.R;
import com.glasstune.application.GlassTuneApplication;
import com.glasstune.application.modules.AndroidModule;
import com.glasstune.audio.IPitchDetection;
import com.glasstune.audio.IPitchDetectionHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Created by server on 19/07/14.
 */
public class TestTuneGuitarActivity extends ActivityInstrumentationTestCase2<TuneGuitarActivity> {

    private TuneGuitarActivity _activity;
    private TextView _subNoteFlat;
    private TextView _subNoteSharp;
    private TextView _mainNote;
    private View _pitchBar;
    private RelativeLayout _overlay;
    private TextView _overlay_text;
    private GlassTuneApplication _app;

    @Inject IPitchDetection _pitchDetection;

    @Module(
            includes = AndroidModule.class,
            injects = TestTuneGuitarActivity.class,
            overrides = true,
            complete = false
    )
    static class TestModule {
        @Provides
        @Singleton
        IPitchDetection providePitchDetection() {
            return mock(IPitchDetection.class);
        }
    }

    public TestTuneGuitarActivity() {
        super(TuneGuitarActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
        _app = (GlassTuneApplication)getInstrumentation().getTargetContext().getApplicationContext();
        _app.setObjectGraph(ObjectGraph.create(TestModule.class));
        _app.inject(this);

        _activity = getActivity();

        _subNoteFlat = (TextView)_activity.findViewById(R.id.tune_view_flat_note);
        assertNotNull(_subNoteFlat);

        _subNoteSharp = (TextView)_activity.findViewById(R.id.tune_view_sharp_note);
        assertNotNull(_subNoteSharp);

        _mainNote = (TextView)_activity.findViewById(R.id.tune_view_main_note);
        assertNotNull(_mainNote);

        _pitchBar = _activity.findViewById(R.id.tune_view_current_pitch);
        assertNotNull(_pitchBar);

        _overlay = (RelativeLayout) _activity.findViewById(R.id.tune_view_overlay);
        assertNotNull(_overlay);

        _overlay_text = (TextView) _activity.findViewById(R.id.tune_view_overlay_text);
        assertNotNull(_overlay_text);
    }

    @SmallTest
    public void testPitchDetectionRegistered() {
        verify(_pitchDetection,timeout(500).times(1)).setDelegate(any(IPitchDetectionHandler.class));
    }

    @SmallTest
    public void testPitchDetectionStarted() {
        verify(_pitchDetection,timeout(500).times(1)).start();
    }

    @UiThreadTest
    public void testOverlayisPresentOnStart() {
        assertEquals(View.VISIBLE,_overlay.getVisibility());
    }

    @UiThreadTest
    public void testOverlayInitialTextSetToPlayANoteOnStart() {
        String text = getActivity().getString(R.string.initial_overlay_text);
        assertEquals(text,_overlay_text.getText());
    }

    @UiThreadTest
    public void testOverlayHiddenWhenNoteReceived() {
        _activity.setDisplayForFrequency(398);

        assertEquals(View.INVISIBLE,_overlay.getVisibility());
    }

    @UiThreadTest
    public void testOverlayTextWhenNoNoteRecognised10Times() {
        String text = getActivity().getString(R.string.no_note_text);

        _activity.setDisplayForFrequency(398);

        for (int n=0; n < 10; n++)
            _activity.setDisplayForFrequency(60000);

        assertEquals(text,_overlay_text.getText());
        assertEquals(View.VISIBLE,_overlay.getVisibility());
    }

    @UiThreadTest
    public void testOverlayTextDoesNotShowWhenNoNoteRecognisedLessThan10Times() {
        _activity.setDisplayForFrequency(398);

        for (int n=0; n < 9; n++)
            _activity.setDisplayForFrequency(60000);

        assertEquals(View.INVISIBLE,_overlay.getVisibility());
    }

    @UiThreadTest
    public void testMainNoteTextIsCWhenFrequencyIs398k() {
        _activity.setDisplayForFrequency(398);
        assertEquals("G",_mainNote.getText());
    }

    @UiThreadTest
    public void testNoNoteDetectedDoesNotUpdateDisplay() {
        _activity.setDisplayForFrequency(0);
        assertEquals("",_mainNote.getText());
    }

}

package com.glasstune.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.TextView;

import com.glasstune.R;
import com.glasstune.application.TestApplication;
import com.glasstune.application.modules.AndroidModule;
import com.glasstune.audio.IPitchDetection;
import com.glasstune.audio.IPitchDetectionHandler;
import com.glasstune.utils.IAlertDialogBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by server on 19/07/14.
 */
public class TestTuneGuitarActivity extends ActivityInstrumentationTestCase2<TuneGuitarActivity> {

    private TuneGuitarActivity _activity;
    private TextView _subNoteFlat;
    private TextView _subNoteSharp;
    private TextView _mainNote;
    private View _pitchBar;
    private TestApplication _app;
    private static IAlertDialogBuilder _mockAlertDialogBuilder;
    private static AlertDialog _mockAlertDialog;

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

        @Provides
        IAlertDialogBuilder provideAlertDialogBuilder() {
            return _mockAlertDialogBuilder;
        }
    }

    public TestTuneGuitarActivity() {
        super(TuneGuitarActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
        _app = (TestApplication)getInstrumentation().getTargetContext().getApplicationContext();
        _app.setObjectGraph(ObjectGraph.create(TestModule.class));
        _app.inject(this);

        setUpMocks();

        _activity = getActivity();

        _subNoteFlat = (TextView)_activity.findViewById(R.id.tune_view_flat_note);
        assertNotNull(_subNoteFlat);

        _subNoteSharp = (TextView)_activity.findViewById(R.id.tune_view_sharp_note);
        assertNotNull(_subNoteSharp);

        _mainNote = (TextView)_activity.findViewById(R.id.tune_view_main_note);
        assertNotNull(_mainNote);

        _pitchBar = _activity.findViewById(R.id.tune_view_current_pitch);
        assertNotNull(_pitchBar);
    }

    private void setUpMocks() {
        _mockAlertDialogBuilder = mock(IAlertDialogBuilder.class);
        _mockAlertDialog = mock(AlertDialog.class);

        when(_mockAlertDialogBuilder.setContext(any(Context.class))).thenReturn(_mockAlertDialogBuilder);
        when(_mockAlertDialogBuilder.setFootnote(anyInt())).thenReturn(_mockAlertDialogBuilder);
        when(_mockAlertDialogBuilder.setIcon(anyInt())).thenReturn(_mockAlertDialogBuilder);
        when(_mockAlertDialogBuilder.setOnClickListener(any(DialogInterface.OnClickListener.class))).thenReturn(_mockAlertDialogBuilder);
        when(_mockAlertDialogBuilder.setText(anyInt())).thenReturn(_mockAlertDialogBuilder);
        when(_mockAlertDialogBuilder.build()).thenReturn(_mockAlertDialog);
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
    public void testAlertDialogisPresentOnStart() {
        verify(_mockAlertDialog,times(1)).show();
    }

    @UiThreadTest
    public void testAlertDialogIsSetupOnStart() {
        verify(_mockAlertDialogBuilder,times(1)).setContext(_activity);
        verify(_mockAlertDialogBuilder,times(1)).setIcon(R.drawable.ic_action_guitar_150);
        verify(_mockAlertDialogBuilder,times(1)).setText(R.string.initial_overlay_text);
        verify(_mockAlertDialogBuilder,times(1)).setFootnote(R.string.initial_overlay_footnote);
        verify(_mockAlertDialogBuilder,times(1)).build();
    }

    @UiThreadTest
    public void testOverlayHiddenWhenNoteReceived() {
        _activity.setDisplayForFrequency(398);

        verify(_mockAlertDialog, times(1)).dismiss();
    }

    @UiThreadTest
    public void testShowsAlertDialogWhenNoNoteRecognised10Times() {
        String text = getActivity().getString(R.string.no_note_text);

        _activity.setDisplayForFrequency(398);

        for (int n=0; n < 10; n++)
            _activity.setDisplayForFrequency(60000);

        verify(_mockAlertDialogBuilder,times(2)).setContext(_activity);
        verify(_mockAlertDialogBuilder,times(1)).setIcon(R.drawable.ic_warning_150);
        verify(_mockAlertDialogBuilder,times(1)).setText(R.string.no_note_text);
        verify(_mockAlertDialogBuilder,times(1)).setFootnote(R.string.no_note_footnote);
        verify(_mockAlertDialogBuilder,times(2)).build();
        verify(_mockAlertDialog,times(2)).show();
    }

    @UiThreadTest
    public void testDoesNotShowsAlertDialogWhenNoNoteRecognised10TimesIfNoInitialNoteDetected() {
        String text = getActivity().getString(R.string.no_note_text);

        for (int n=0; n < 10; n++)
            _activity.setDisplayForFrequency(60000);

        verify(_mockAlertDialogBuilder,times(1)).setContext(_activity);
        verify(_mockAlertDialogBuilder,times(0)).setIcon(R.drawable.ic_warning_150);
        verify(_mockAlertDialogBuilder,times(0)).setText(R.string.no_note_text);
        verify(_mockAlertDialogBuilder,times(0)).setFootnote(R.string.no_note_footnote);
        verify(_mockAlertDialogBuilder,times(1)).build();
        verify(_mockAlertDialog,times(1)).show();
    }

    @UiThreadTest
    public void testDoesNotShowAlertDialogWhenNoNoteRecognisedLessThan10Times() {
        _activity.setDisplayForFrequency(398);

        for (int n=0; n < 9; n++)
            _activity.setDisplayForFrequency(60000);

        verify(_mockAlertDialog, times(1)).show();
    }

    @UiThreadTest
    public void testMainNoteTextIsCWhenFrequencyIs398k() {
        _activity.setDisplayForFrequency(398);
        assertEquals("G",_mainNote.getText());
    }

    @UiThreadTest
    public void testNoNoteDetectedDoesNotUpdateDisplay() {
        _activity.setDisplayForFrequency(398);
        _activity.setDisplayForFrequency(0);
        assertEquals("G",_mainNote.getText());
    }

}

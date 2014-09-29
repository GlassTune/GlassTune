package com.glasstune.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.TextView;

import com.glasstune.R;

/**
 * Created by server on 19/07/14.
 */
public class TestTuneGuitarActivity extends ActivityInstrumentationTestCase2<TuneGuitarActivity> {

    private TuneGuitarActivity _activity;
    private TextView _subNoteFlat;
    private TextView _subNoteSharp;
    private TextView _mainNote;
    private View _pitchBar;

    public TestTuneGuitarActivity() {
        super(TuneGuitarActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _activity = getActivity();

        _subNoteFlat = (TextView)_activity.findViewById(R.id.tune_view_flat_note);
        assertNotNull(_subNoteFlat);

        _subNoteSharp = (TextView)_activity.findViewById(R.id.tune_view_sharp_note);
        assertNotNull(_subNoteSharp);

        _mainNote = (TextView)_activity.findViewById(R.id.tune_view_main_note);
        assertNotNull(_mainNote);

        _pitchBar = (View)_activity.findViewById(R.id.tune_view_current_pitch);
        assertNotNull(_pitchBar);
    }

    @UiThreadTest
    public void testMainNoteTextIsCWhenFrequencyIs64k() {
        _activity.setDisplayForFrequency(64.453125);
        assertEquals("C",_mainNote.getText());
    }

    @UiThreadTest
    public void testFlatNoteTextIsBWhenFrequencyIs64k() {
        _activity.setDisplayForFrequency(64.453125);
        assertEquals("B",_subNoteFlat.getText());
    }

    @UiThreadTest
    public void testSharpNoteTextIsCSharpWhenFrequencyIs64k() {
        _activity.setDisplayForFrequency(64.453125);
        assertEquals("C#",_subNoteSharp.getText());
    }

    @UiThreadTest
    public void testNoNoteDetectedDoesNotUpdateDisplay() {
        _activity.setDisplayForFrequency(0);
        assertEquals("J#",_subNoteSharp.getText());
    }

}

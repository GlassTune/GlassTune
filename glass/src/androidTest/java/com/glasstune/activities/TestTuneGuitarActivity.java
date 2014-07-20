package com.glasstune.activities;

import android.test.ActivityInstrumentationTestCase2;
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

}

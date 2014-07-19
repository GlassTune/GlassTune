package com.glasstune.cards;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.glasstune.R;
import com.glasstune.TestActivity;

/**
 * Created by server on 19/07/14.
 */
public class TuneViewTest extends ActivityInstrumentationTestCase2<TestActivity> {

    private TestActivity _activity;

    public TuneViewTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent startIntent =  new Intent();
        startIntent.putExtra("CARD_CLASS", TuneView.class.getCanonicalName());
        startIntent.putExtra("CARD_LAYOUT", R.layout.tune_view);
        setActivityIntent(startIntent);
        _activity = getActivity();
    }

    public void testSomething() {
        assertEquals(false,true);
    }

}

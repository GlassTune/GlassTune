package com.glasstune.activities;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.glasstune.R;
import com.glasstune.tone.Note;
import com.glasstune.tone.ToneGenerator;
import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link android.app.Activity} showing a tuggable "Hello World!" card.
 * <p>
 * The main content view is composed of a one-card {@link com.google.android.glass.widget.CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class PlayPitchActivity extends Activity {

    /** {@link com.google.android.glass.widget.CardScrollView} to use as the main content view. */
    private CardScrollView mCardScroller;
    private List<Card> mCards;

    /** "Hello World!" {@link android.view.View} generated by {@link #buildView()}. */
    private static final String TAG = "GlassTune";
    private View mView;
    private ToneGenerator _toneGenerator;
    private Note[] _notes = Note.values();
    private Note _pitch = Note.C4S;
    private Integer _pitchStart = 0;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        //mView = buildView();
        createCards();

        mCardScroller = new CardScrollView(this);
        NoteScrollAdapter adapter = new NoteScrollAdapter();
        mCardScroller.setAdapter(adapter);
        // Handle the TAP event.
        mCardScroller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _pitch = _notes[position+_pitchStart];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                _pitch = Note.C4;
            }
        });
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Plays disallowed sound to indicate that TAP actions are not supported.
//                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                am.playSoundEffect(Sounds.DISALLOWED);
                // Tap to play sound
                Note[] notes = Note.values();
                for (int i = 0; i < notes.length; i++) {
                    if(i == position){
                        final Note n = notes[i];
                        _toneGenerator = new ToneGenerator(n);
                        String message = String.format("%.3f", notes[i].frequency);
                        Log.d(TAG, message);
                        _toneGenerator.playFrequency(_pitch.frequency);
                    }
                }
            }
        });
        mCardScroller.activate();
        setContentView(mCardScroller);
        //setDisplayForNote(_pitch);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        _toneGenerator.stopPlayingTone();
        super.onDestroy();
    }

    public void setDisplayForNote(final Note note) {
        TextView noteText = (TextView) mView.findViewById(R.id.pitch_view_note);
        if (noteText != null && note != null) {
            noteText.setText(note.toString());
        }
    }

    /**
     * Builds a Glass styled "Hello World!" view using the {@link com.google.android.glass.app.Card} class.
     */
    private View buildView() {
        return getLayoutInflater().inflate(R.layout.pitch_view,null);
    }

    /**
     * Create the Cards for each available note
     */
    private void createCards() {
        mCards = new ArrayList<Card>();
        Card card;

        for (int i = 0; i < _notes.length; i++) {
            // Some of the lower frequency notes are in audible. (At least to my 29 year old ear
            if(_notes[i].frequency > 230) {
                card = new Card(this);
                _pitchStart = (_pitchStart > 0) ? _pitchStart : i;
                card.setText("Tap to play a "+_notes[i].toString());
                card.setFootnote("Swipe left/right for notes; down to cancel");
                mCards.add(card);
            }
        }
    }


    private class NoteScrollAdapter extends CardScrollAdapter {

        @Override
        public int getPosition(Object item) {
            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {
            return mCards.size();
        }

        @Override
        public Object getItem(int position) {
            return mCards.get(position);
        }

        @Override
        public int getViewTypeCount() {
            return Card.getViewTypeCount();
        }

        @Override
        public int getItemViewType(int position){
            return mCards.get(position).getItemViewType();
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            return  mCards.get(position).getView(convertView, parent);
        }


    }

}

package com.glasstune.pitch;

/**
 * Created by server on 20/07/14.
 */
public class Pitch {

    private String _mainNote;
    private String _flatNote;
    private String _sharpNote;
    private String _currentPitch;

    public Pitch(String mainNote,String flatNote, String sharpNote, String currentPitch) {
        _mainNote = mainNote;
        _flatNote = flatNote;
        _sharpNote = sharpNote;
        _currentPitch = currentPitch;
    }

    public String get_mainNote() {
        return _mainNote;
    }

    public String get_flatNote() {
        return _flatNote;
    }

    public String get_sharpNote() {
        return _sharpNote;
    }

    public String get_currentPitch() {
        return _currentPitch;
    }
}

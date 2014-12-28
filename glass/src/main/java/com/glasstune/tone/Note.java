package com.glasstune.tone;

import java.lang.Character;
import java.util.Arrays;

/**
 * This is an enumeration to represent different musical notes
 *
 * This encapsulates the note and any extra information about it.
 *
 * @author Carl Jokl
 * @since 19th of July 2014
 */
public enum Note {

    C0('C', 0, false, 16.35),

    C0S('C', 0, true, 17.32),

    D0('D', 0, false, 18.35),

    D0S('D', 0, true, 19.45),

    E0('E', 0, false, 20.60),

    F0('F', 0, false, 21.83),

    F0S('F', 0, true, 23.12),

    G0('G', 0, false, 24.50),

    G0S('G', 0, true, 25.96),

    A0('A', 0, false, 27.50),

    A0S('A', 0, true, 29.14),

    B0('B', 0, false, 30.87),

    C1('C', 1, false, 32.70),

    C1S('C', 1, true, 34.65),

    D1('D', 1, false, 36.71),

    D1S('D', 1, true, 38.89),

    E1('E', 1, false, 41.20),

    F1('F', 1, false, 43.65),

    F1S('F', 1, true, 46.25),

    G1('G', 1, false, 49.00),

    G1S('G', 1, true, 51.91),

    A1('A', 1, false, 55.00),

    A1S('A', 1, true, 58.27),

    B1('B', 1, false, 61.74),

    C2('C', 2, false, 65.41),

    C2S('C', 2, true, 69.30),

    D2('D', 2, false, 73.42),

    D2S('D', 2, true, 77.78),

    E2('E', 2, false, 82.41), // 79.27 .961897828%

    F2('F', 2, false, 87.31),

    F2S('F', 2, true, 92.50),

    G2('G', 2, false, 98.00),

    G2S('G', 2, true, 103.83),

    A2('A', 2, false, 110.00),

    A2S('A', 2, true, 116.54),

    B2('B', 2, false, 123.47),

    C3('C', 3, false, 130.81),

    C3S('C', 3, true, 138.59),

    D3('D', 3, false, 146.83),

    D3S('D', 3, true, 155.56),

    E3('E', 3, false, 164.81),

    F3('F', 3, false, 174.61),

    F3S('F', 3, true, 185.00),

    G3('G', 3, false, 196.00),

    G3S('G', 3, true, 207.65),

    A3('A', 3, false, 220.00),

    A3S('A', 3, true, 233.08),

    B3('B', 3, false, 246.94),

    C4('C', 4, false, 261.63),

    C4S('C', 4, true, 277.18),

    D4('D', 4, false, 293.66),

    D4S('D', 4, true, 311.13),

    E4('E', 4, false, 329.63), //317.98 11.65 .964657343

    F4('F', 4, false, 349.23),

    F4S('F', 4, true, 369.99),

    G4('G', 4, false, 392.00),

    G4S('G', 4, true, 415.30),

    A4('A', 4, false, 440.00),

    A4S('A', 4, true, 466.16),

    B4('B', 4, false, 493.88),

    C5('C', 5, false, 523.25),

    C5S('C', 5, true, 554.37),

    D5('D', 5, false, 587.33),

    D5S('D', 5, true, 622.25),

    E5('E', 5, false, 659.25),

    F5('F', 5, false, 698.46),

    F5S('F', 5, true, 739.99),

    G5('G', 5, false, 783.99),

    G5S('G', 5, true, 830.61),

    A5('A', 5, false, 880.00),

    A5S('A', 5, true, 932.33),

    B5('B', 5, false, 987.77),

    C6('C', 6, false, 1046.50),

    C6S('C', 6, true, 1108.73),

    D6('D', 6, false, 1174.66),

    D6S('D', 6, true, 1244.51),

    E6('E', 6, false, 1318.51),

    F6('F', 6, false, 1396.91),

    F6S('F', 6, true, 1479.98),

    G6('G', 6, false, 1567.98),

    G6S('G', 6, true, 1661.22),

    A6('A', 6, false, 1760.00),

    A6S('A', 6, true, 1864.66),

    B6('B', 6, false, 1975.53),

    C7('C', 7, false, 2093.00),

    C7S('C', 7, true, 2217.46),

    D7('D', 7, false, 2349.32),

    D7S('D', 7, true, 2489.02),

    E7('E', 7, false, 2637.02),

    F7('F', 7, false, 2793.83),

    F7S('F', 7, true, 2959.96),

    G7('G', 7, false, 3135.96),

    G7S('G', 7, true, 3322.44),

    A7('A', 7, false, 3520.00),

    A7S('A', 7, true, 3729.31),

    B7('B', 7, false, 3951.07),

    C8('C', 8, false, 4186.01),

    C8S('C', 8, true, 4434.92),

    D8('D', 8, false, 4698.63),

    D8S('D', 8, true, 4978.03),

    E8('E', 8, false, 5274.04),

    F8('F', 8, false, 5587.65),

    F8S('F', 8, true, 5919.91),

    G8('G', 8, false, 6271.93),

    G8S('G', 8, true, 6644.88),

    A8('A', 8, false, 7040.00),

    A8S('A', 8, true, 7458.62),

    B8('B', 8, false, 7902.13),

    UNKNOWN('U', 0, false, 0.0);

    /**
     * The number of notes in an octave.
     */
    public static final int OCTAVE_SIZE = 12;

    /**
     * The maximum octave index (0 based).
     */
    public static final int MAX_OCTAVE = 8;

    /**
     * The default octave to use.
     */
    public static final int DEFAULT_OCTIVE = 4;

    /**
     * The letter denoting the note.
     */
    public final char letter;

    /**
     * The octave of the note.
     */
    public final int octave;

    /**
     * True if the note is a sharp (which is also flat of the note above).
     */
    public final boolean sharp;

    /**
     * The frequency in herz of the note.
     */
    public final double frequency;

    /**
     * Get the notes contained within a given musical octave.
     *
     * @param octave The octave for which to get notes.
     * @return An array of the notes within the specified octave.
     */
    public static Note[] getOctave(final int octave) {
        if (octave >= 0 && octave <= MAX_OCTAVE) {
            final int offset = octave * OCTAVE_SIZE;
            return Arrays.copyOfRange(values(), offset, offset + OCTAVE_SIZE);
        }
        else {
            throw new IndexOutOfBoundsException(String.format("The octave: %d is outside the valid range!", octave));
        }
    }

    /**
     * Get the default Octave.
     *
     * @return An array containing the notes of the default Octave.
     */
    public static Note[] getDefaultOctave() {
        return getOctave(DEFAULT_OCTIVE);
    }

    /*
     * Returns the nearest note to this frequency
     */
    public static Note getNearestNote(double frequency) {
        Note returnNote = null;

        double minDif = 99999999;

        for(Note note: Note.values()) {
            double absDif = Math.abs(note.frequency - frequency);
            if(absDif < minDif) {
                minDif = absDif;
                returnNote = note;
            }
        }

        return returnNote;
    }

    /*
     * Returns the nearest whole note to this frequency, i.e will never return a sharp
     */
    public static Note getNearestWholeToneNote(double frequency) {

        Note returnNote = getNearestNote(frequency);

        // if this is a sharp return the closest whole note
        if(returnNote.sharp) {
            Note previousNote = getPreviousNote(returnNote);
            Note nextNote = getNextNote(returnNote);
            double dif = returnNote.frequency - frequency;

            if(dif < 0) {
                return previousNote;
            } else {
                return nextNote;
            }
        }

        return returnNote;

    }



    public static Note getPreviousNote(Note currentNote) {

        if(currentNote == Note.A0 || currentNote == Note.UNKNOWN)
            return Note.UNKNOWN;

        Note prevNote = currentNote;

        for(Note note: Note.values()) {

            if(note == currentNote)
                break;

            prevNote = note;
        }

        return prevNote;
    }

    public static Note getNextNote(Note currentNote) {

        if(currentNote == Note.UNKNOWN)
            return Note.UNKNOWN;

        Note[] notes = Note.values();
        for(int n=0; n < notes.length - 1;n ++) {
            if(notes[n] == currentNote) {
                return notes[n + 1];
            }
        }
        return Note.UNKNOWN;
    }

    /**
     * A musical note.
     *
     * @param letter The letter for this note.
     * @param octave The octave of the note.
     * @param sharp Whether this note is sharp.
     * @param frequency The frequency in herz of the tone of this note.
     */
    private Note(char letter, int octave, boolean sharp, double frequency) {
        this.letter = letter;
        this.octave = octave;
        this.sharp = sharp;
        this.frequency = frequency;
    }

    /**
     * Create a String representation of the note.
     *
     * @return A String representation of the note.
     */
    public String toString() {

        if(letter == 'A' && sharp) {
            return "Bb";
        } else if(letter == 'D' && sharp) {
            return "Eb";
        } else {
            return sharp ? letter + "#" : Character.toString(letter);
        }

    }

}
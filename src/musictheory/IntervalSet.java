package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NoteType.*;

/**
 * Created by Joseph on 1/13/16.
 */
abstract class IntervalSet {
    protected final NoteType[] noteTypes;
    protected final NoteType rootNoteType;
    protected final Note[] notes;
    protected String name;
    protected final Octave octaveRange;
    protected Octave octave;

    IntervalSet(NoteType rootNoteType, NashvilleNumber[] nashvilleNumbers, Octave octave, String name) throws Exception {

        // If the IntervalSet constructor was called with a NoteType containing
        // a natural accidental, just convert the NoteType to its non-accidental equivalent.
        if (rootNoteType.isNatural()) this.rootNoteType = getNoteType(rootNoteType.letter, NONE);
        else this.rootNoteType = rootNoteType;

        this.noteTypes = setNoteTypes(nashvilleNumbers);

        // IntervalSets have an octave range that is limited by the highest base relative pitch in the set
        this.octaveRange = getNoteTypeWithHighestPotential().maxOctave;

        this.octave = octave;

        this.notes = setNotes(this.octave, this.octaveRange);

        this.name = name;
    }

    /**
     * Raises or lowers all Notes in the IntervalSet to a specified octave, if it is within the octave range of the IntervalSet.
     * Otherwise, this method does nothing.
     * @param octave the octave number to set the IntervalSet at
     */
    protected abstract void setNoteOctaves(Octave octave);

    /**
     * Dynamically builds the list of diatonic NoteTypes for this IntervalSet, based on the root NoteType and NashvilleNumbers.
     * This function operates in Θ(n) time.
     * @param nashvilleNumbers the list of NashvilleNumber elements, which will be used for deducing NoteTypes
     * @return a list of NoteTypes that are diatonic to this IntervalSet
     */
    private NoteType[] setNoteTypes(NashvilleNumber[] nashvilleNumbers) throws Exception {
        int numNotes = nashvilleNumbers.length;
        NoteType[] returnedNoteTypes = new NoteType[numNotes];

        returnedNoteTypes[0] = rootNoteType;

        Accidental rootAccidental = rootNoteType.accidental;
        for (int i = 1; i < numNotes; i++) {
            char nextNoteLetter = Theory.getNoteLetterForNashvilleNumber(rootNoteType, nashvilleNumbers[i]);
            NoteType candidate;

            if (!rootNoteType.isNatural()) candidate = getNoteType(nextNoteLetter, rootAccidental);
            else candidate = Theory.applyAccidentalTo(getNoteType(nextNoteLetter, rootAccidental), nashvilleNumbers[i].accidental);

            int candidateRelativePitch = candidate.relativePitch;
            int comparisonRelativePitch = (rootNoteType.relativePitch + nashvilleNumbers[i].relativePitchDistance) % 12;
            int offset = comparisonRelativePitch - candidateRelativePitch;

            if (offset == 0) {
                returnedNoteTypes[i] = candidate; // they match, so we're done
            }
            else {
                Accidental newAccidental;
                switch (offset) {
                    case -2: newAccidental = DOUBLE_FLAT; break;
                    case -1: newAccidental = FLAT; break;
                    case 1: newAccidental = SHARP; break;
                    case 2: newAccidental = DOUBLE_SHARP; break;
                    default: newAccidental = NONE;
                }

                candidate = Theory.applyAccidentalTo(candidate, newAccidental);
                if (candidate.isNatural() && Theory.noteLettersFollow(returnedNoteTypes[i-1], candidate)) {
                    candidate = getNoteType(candidate.letter, NONE);
                }

                if (candidate.relativePitch == comparisonRelativePitch) {
                    returnedNoteTypes[i] = candidate; // match on second attempt (done)
                }
                else {
                    candidate = getNoteType(candidate.letter, newAccidental);

                    if (candidate.relativePitch == comparisonRelativePitch) {
                        returnedNoteTypes[i] = candidate; // match on third attempt (done)
                    }
                    else {
                        candidateRelativePitch = candidate.relativePitch;
                        offset = comparisonRelativePitch - candidateRelativePitch;

                        if (rootNoteType.accidental.equals(DOUBLE_FLAT)) {
                            switch (offset) {
                                case 9: {
                                    newAccidental = FLAT;
                                    candidate = getNoteType(Theory.getPreviousNoteLetter(candidate), newAccidental);
                                    break;
                                }
                                case -1: {
                                    newAccidental = DOUBLE_FLAT;
                                    nextNoteLetter = Theory.getNoteLetterForNashvilleNumber(rootNoteType, nashvilleNumbers[i]);
                                    candidate = getNoteType(nextNoteLetter, newAccidental);
                                    break;
                                }
                                default: throw new Exception("uncaught value of " + offset + " on " + nashvilleNumbers[i]);
                            }
                        }
                        else if (rootNoteType.accidental.equals(FLAT)) {
                            switch (offset) {
                                case -1: newAccidental = SHARP; break;
                                case 1: newAccidental = SHARP; break;
                                default: throw new Exception("uncaught value of " + offset + " on " + nashvilleNumbers[i]);
                            }

                            candidate = getNoteType(candidate.letter, newAccidental);
                        }
                        else if (rootNoteType.accidental.equals(SHARP)) {
                            switch (offset) {
                                case -1: newAccidental = FLAT; break;
                                case 1: newAccidental = FLAT; break;
                                default: throw new Exception("uncaught value of " + offset + " on " + nashvilleNumbers[i]);
                            }

                            candidate = getNoteType(candidate.letter, newAccidental);
                        }
                        else if (rootNoteType.accidental.equals(DOUBLE_SHARP)) {
                            switch (offset) {
                                case -9: newAccidental = SHARP; break;
                                default: throw new Exception("uncaught value of " + offset + " on " + nashvilleNumbers[i]);
                            }

                            candidate = getNoteType(Theory.getNextNoteLetter(candidate), newAccidental);
                        }
                        else if (rootNoteType.accidental.equals(NONE)) {
                            switch (offset) {
                                case -11: newAccidental = SHARP; break;
                                case 11: newAccidental = FLAT; break;
                                default: throw new Exception("uncaught value of " + offset + " on " + nashvilleNumbers[i]);
                            }

                            candidate = getNoteType(candidate.letter, newAccidental);
                        }

                        returnedNoteTypes[i] = candidate;

                    }
                }
            }
        }
        return returnedNoteTypes;
    }

    /**
     * Finds the NoteType in the IntervalSet that has the potential for being the
     * highest relative pitch value, depending on how the IntervalSet is manipulated
     * (e.g., via inversion, transposition, or raising the Octave).
     * @return the NoteType in the IntervalSet containing the highest relative pitch value
     */
    protected abstract NoteType getNoteTypeWithHighestPotential();


    /**
     * A public wrapper method for raising or lowering the IntervalSet by the specified octave.
     * @param octave the octave to set the IntervalSet at
     */
    public void setOctave(Octave octave) {

        this.octave = octave;

        setNoteOctaves(octave);
    }

    /**
     * A private method designed for setting the private IntervalSet member notes.
     * This is called exactly one time, in the constructor method.
     * This function operates in Θ(n) time.
     * @param octave this.octave
     * @param octaveRange this.octaveRange
     * @return the list of Notes for the IntervalSet
     */
    private Note[] setNotes(Octave octave, Octave octaveRange) {
        Note[] returnedNotes = new Note[this.noteTypes.length];

        /*
         * If the desired Octave is too high,
         * we'll make the executive decision
         * to use the highest possible Octave instead.
         */
        if (octave.height > octaveRange.height) {
            octave = octaveRange;
        }

        /*
         * Create the array of Notes for this IntervalSet.
         * Since this method is only called via constructor,
         * the Octaves chosen are just the "initial" ones for each Note.
         * After the IntervalSet is created, we can collectively
         * raise / lower each Octave in the IntervalSet by using setOctave().
         */
        returnedNotes[0] = new Note(this.noteTypes[0], octave);
        if (returnedNotes[0].getPitch() >= octaveRange.height) {
            octave = octave.lowerBy(1);
            returnedNotes[0].setOctave(octave);
        }
        if (octave.equals(Octave.OCTAVE_MIN)) {
            for (int i = 1; i < returnedNotes.length; i++) {
                Note n = new Note(this.noteTypes[i], octave);

                if (this.noteTypes[i].relativePitch == C.relativePitch) {
                    n.setOctave(returnedNotes[i - 1].getOctave().raiseBy(1));
                }
                else if (n.getPitch() < returnedNotes[i - 1].getPitch()) {
                    n.setOctave(returnedNotes[i - 1].getOctave());
                }

                returnedNotes[i] = n;
            }
        }
        else {
            for (int i = 1; i < returnedNotes.length; i++) {
                Note n = new Note(this.noteTypes[i], octave);

                if (this.noteTypes[i].relativePitch < this.noteTypes[i - 1].relativePitch) {
                    n.setOctave(returnedNotes[i - 1].getOctave().raiseBy(1));
                }
                else if (n.getPitch() < returnedNotes[i - 1].getPitch()) {
                    n.setOctave(returnedNotes[i - 1].getOctave());
                }

                returnedNotes[i] = n;
            }
        }

        return returnedNotes;
    }

    /**
     *
     * @return the list of NoteTypes in this IntervalSet
     */
    NoteType[] getNoteTypes() {
        return noteTypes;
    }

    /**
     *
     * @return the NoteType designated as the root for this IntervalSet
     */
    NoteType getRootNoteType() {
        return rootNoteType;
    }

    /**
     *
     * @return the list of Notes in this IntervalSet
     */
    Note[] getNotes() {
        return notes;
    }

    /**
     *
     * @return the Note designated as the root for this IntervalSet
     */
    public Note getRootNote() {
        return notes[0];
    }

    /**
     *
     * @return the name of this IntervalSet
     */
    public String getName() {
        return name;
    }
}

package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;
    private KeySignature keySignature;

    Scale(Note root, ScaleType scaleType) throws Exception {
        /*
         * TODO Maybe convert the natural Notes into their non-accidental enharmonic equivalent,
         *      instead of throwing an Exception? Would reduce the number of try-catches.
         */
        if (root.isNatural()) {
            throw new Exception("Scale constructor called with Natural Note root.");
        }
        this.root = root;
        this.scaleType = scaleType;
        setKeySignature(root);
        if (keySignature == null) {
            throw new Exception("Scale is not Enharmonically correct.");
        }
    }

    private void setKeySignature(Note note) {
        switch(scaleType.tonality) {
            case MAJOR: this.keySignature = getMajorKeySignature(note); break;
            case MINOR: this.keySignature = getMinorKeySignature(note); break;
            default:
        }
    }

    private KeySignature getMajorKeySignature(Note note) {
        switch(note) {
            // Flat key signatures
            case C_FLAT: return KeySignature.C_FLAT_MAJOR;
            case G_FLAT: return KeySignature.G_FLAT_MAJOR;
            case D_FLAT: return KeySignature.D_FLAT_MAJOR;
            case A_FLAT: return KeySignature.A_FLAT_MAJOR;
            case E_FLAT: return KeySignature.E_FLAT_MAJOR;
            case B_FLAT: return KeySignature.B_FLAT_MAJOR;
            case F: return KeySignature.F_MAJOR;

            //Non-accidental key signatures
            case C: return KeySignature.C_MAJOR;

            // Sharp key signatures
            case G: return KeySignature.G_MAJOR;
            case D: return KeySignature.D_MAJOR;
            case A: return KeySignature.A_MAJOR;
            case E: return KeySignature.E_MAJOR;
            case B: return KeySignature.B_MAJOR;
            case F_SHARP: return KeySignature.F_SHARP_MAJOR;
            case C_SHARP: return KeySignature.C_SHARP_MAJOR;
            default: return null;
        }
    }

    private KeySignature getMinorKeySignature(Note note) {
        switch(note) {
            // Flat key signatures
            case A_FLAT: return KeySignature.A_FLAT_MINOR;
            case E_FLAT: return KeySignature.E_FLAT_MINOR;
            case B_FLAT: return KeySignature.B_FLAT_MINOR;
            case F: return KeySignature.F_MINOR;
            case C: return KeySignature.C_MINOR;
            case G: return KeySignature.G_MINOR;
            case D: return KeySignature.D_MINOR;

            //Non-accidental key signatures
            case A: return KeySignature.A_MINOR;

            // Sharp key signatures
            case E: return KeySignature.E_MINOR;
            case B: return KeySignature.B_MINOR;
            case F_SHARP: return KeySignature.F_SHARP_MINOR;
            case C_SHARP: return KeySignature.C_SHARP_MINOR;
            case G_SHARP: return KeySignature.G_SHARP_MINOR;
            case D_SHARP: return KeySignature.D_SHARP_MINOR;
            case A_SHARP: return KeySignature.A_SHARP_MINOR;
            default: return null;
        }
    }

    public String getName() {
        return root.getName() + " " + scaleType.name;
    }

    public Note getRoot() {
        return root;
    }

    public KeySignature getKeySignature() {
        return keySignature;
    }

    public Note[] getAscendingNotes() {
        int scaleLength = scaleType.sequence.length;
        Note[] notes = new Note[scaleLength];
        notes[0] = root;

        for (int i = 1; i < scaleLength; i++) {
            switch(scaleType.sequence[i]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                default:
            }
        }

        return notes;
    }



    public Note[] getDescendingNotes() {
        int scaleLength = scaleType.sequence.length;
        Note[] notes = new Note[scaleLength];
        notes[scaleLength-1] = root;

        for (int i = scaleLength-2; i >= 0; i--) {
            switch(scaleType.sequence[i]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                default:
            }
        }

        return notes;
    }
}

package musictheory;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 12/29/15.
 */
public class TESTMUSICTHEORY {

    // Toggle for hearing Midi playback
    static final boolean PLAYBACK = true;

    // All possible note types that this program can play
    static final Note[] ALL_NOTES = Note.values();

    // Variable for testing scales
    static Scale scale;

    // Variable for testing key signatures
    static KeySignature keySignature;

    static MidiChannel[] channels;

    public static void main(String[] args) {

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            // TODO: Tests performed here

            testScale(PLAYBACK);

//            testAscendingNotes(PLAYBACK);
//
//            testDescendingNotes(PLAYBACK);
//
//            testIntervalNotes(PLAYBACK);

//            testNextPreviousNotes(PLAYBACK);

//            testEnharmonicNotes(PLAYBACK);

            synthesizer.close();
        }
        catch (Exception ex) {}
    }

    private static void soundNote(int midiValue, int volume) {
        try {
            Thread.sleep(100);
            channels[0].noteOn(midiValue, volume);
            Thread.sleep(200);
            channels[0].noteOff(midiValue, volume);
        }
        catch (Exception x) {}
    }

    private static void testAscendingNotes(boolean soundNotes) {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = 0; j < ALL_NOTES[i].getOctaveRange(); j++) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                if (soundNotes) soundNote(midiValue, 127);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testDescendingNotes(boolean soundNotes) {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = ALL_NOTES[i].getOctaveRange(); j >= 0; j--) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                if (soundNotes) soundNote(midiValue, 127);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testIntervalNotes(boolean soundNotes) {
        for (Interval interval: Interval.values()) {
            System.out.println(interval.toString());
            System.out.println("Short Name: " + interval.quality.identifier + interval.intervalNumber);
            System.out.println("RomanNumeral: " + interval.romanNumeral.identifier);
            System.out.println("=======================================");

            if (soundNotes) {
                soundNote(60, 127);
                soundNote(60 + interval.relativePitchDistance, 127);
            }
        }
    }

    private static void testScale(boolean soundNotes) {
        for (ScaleType scaleType: ScaleType.values()) {
            for (Note note : Note.values()) {
                try {
                    scale = new Scale(note, scaleType);

                    System.out.print(scale.getName() + ": ");

                    Note root = scale.getRoot();

                    System.out.println(root.getName() + " " + root.name());

                    keySignature = scale.getKeySignature();

                    System.out.print("Key Signature: " + keySignature + "( ");
                    for (Note n : keySignature.notes) {
                        System.out.print(n.getName() + " ");
                    }

                    System.out.println(")");

                    // TODO print the scale notes out, and sound them
                    System.out.print("Notes: ");
                    Note[] notes = scale.getAscendingNotes();
                    for (Note n : notes) {
                        if (n != null) System.out.print(n.getName() + " ");
                        else System.out.print("_ ");
                    }

                    System.out.println();

                    System.out.print("Steps: ");
                    for (Step s : scale.getSteps()) {
                        System.out.print(s + " ");
                    }

                    System.out.println();

                    System.out.println("\n==========================================");

                } catch (Exception e) {
                    // Skip the natural notes (don't generate scales from them)
                }
            }
        }
    }

    private static void testNextPreviousNotes(boolean soundNotes) {
        for (Note note : Note.values()) {
            System.out.println("Note: " + note);
            System.out.println("Next: " + note.getNext());
            System.out.println("Previous: " + note.getPrevious());
            System.out.println("\n==========================================");
        }
    }

    private static void testEnharmonicNotes(boolean soundNotes) {
        for (Note note : Note.values()) {
            System.out.println("Note: " + note);
            for (Note n : note.getEnharmonicEquivalents(true, true)) {
                System.out.print(n.getName() + " ");
            }
            System.out.println();
            for (Note n : note.getEnharmonicEquivalents(false, true)) {
                System.out.print(n.getName() + " ");
            }
            System.out.println("\n==========================================");
        }
    }
}

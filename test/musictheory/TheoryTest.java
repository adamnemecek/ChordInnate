package musictheory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joseph on 3/13/16.
 */
public class TheoryTest {

    @Test
    public void testGetEnharmonicEquivalents() throws Exception {

    }

    @Test
    public void testGetNoteLetterForNashvilleNumber() throws Exception {

    }

    @Test
    public void testGetPreviousNoteLetter() throws Exception {

    }

    @Test
    public void testGetNextNoteLetter() throws Exception {

    }

    @Test
    public void testNoteLettersFollow() throws Exception {

    }

    @Test
    public void testApplyAccidentalTo() throws Exception {

        // TODO: this test currently requires visual cross-referencing

        for (NoteType nt : NoteType.values()) {
            System.out.println(nt.name + ":\n===================");
            for (Accidental a : Accidental.values()) {
                NoteType result = Theory.applyAccidentalTo(nt, a);
                assertNotNull(result);
                System.out.println(a.name() + ": " + result.name);
            }
            System.out.println("\n===================");
        }
    }

    @Test
    public void testTransposeScale() throws Exception {
        for (Octave o : Octave.values()) {
            Scale original = new Scale(NoteType.C, ScaleType.MAJOR, o);
            Note[] originalNotes = original.getNotes();
            for (NoteType nt : NoteType.values()) {
                Scale transposed = new Scale(original);
                Note[] transposedNotesBefore = transposed.getNotes();

                System.out.println("Transpose " + originalNotes[0].getName() + " -> " + nt.name + ":");
                System.out.print("Before: ");
                for (int i = 0; i < originalNotes.length; i++) {
                    System.out.print(originalNotes[i].getName() + "\t");

                    // Sanity check before transposition occurs
                    assertEquals(originalNotes[i].getNoteType(), transposedNotesBefore[i].getNoteType());
                    assertEquals(originalNotes[i].getPitch(), transposedNotesBefore[i].getPitch());
                }

                transposed = Theory.transpose(transposed, nt);
                Note[] transposedNotesAfter = transposed.getNotes();

                Scale expected = new Scale(nt, original.getScaleType());
                expected.setOctave(o);
                Note[] expectedNotes = expected.getNotes();

                System.out.print("\nAfter:  ");
                for (int i = 0; i < transposedNotesAfter.length; i++) {
                    System.out.print(transposedNotesAfter[i].getName() + "\t");

                    // Actual assertion to test
                    assertEquals(expectedNotes[i].getNoteType(), transposedNotesAfter[i].getNoteType());
                    assertEquals(expectedNotes[i].getPitch(), transposedNotesAfter[i].getPitch());
                }
                System.out.println("\n====================================");
            }
        }
    }

    @Test
    public void testTransposeChord() throws Exception {
        for (Octave o : Octave.values()) {
            Chord original = new Chord(NoteType.C, ChordType.MAJOR, o);
            Note[] originalNotes = original.getNotes();
            for (NoteType nt : NoteType.values()) {
                Chord transposed = new Chord(original);
                Note[] transposedNotesBefore = transposed.getNotes();

                System.out.println("Transpose " + originalNotes[0].getName() + " -> " + nt.name + ":");
                System.out.print("Before: ");
                for (int i = 0; i < originalNotes.length; i++) {
                    System.out.print(originalNotes[i].getName() + "\t");

                    // Sanity check before transposition occurs
                    assertEquals(originalNotes[i].getNoteType(), transposedNotesBefore[i].getNoteType());
                    assertEquals(originalNotes[i].getPitch(), transposedNotesBefore[i].getPitch());
                }

                transposed = Theory.transpose(transposed, nt);
                Note[] transposedNotesAfter = transposed.getNotes();

                Chord expected = new Chord(nt, original.getChordType());
                expected.setOctave(o);
                Note[] expectedNotes = expected.getNotes();

                System.out.print("\nAfter:  ");
                for (int i = 0; i < transposedNotesAfter.length; i++) {
                    System.out.print(transposedNotesAfter[i].getName() + "\t");

                    // Actual assertion to test
                    assertEquals(expectedNotes[i].getNoteType(), transposedNotesAfter[i].getNoteType());
                    assertEquals(expectedNotes[i].getPitch(), transposedNotesAfter[i].getPitch());
                }
                System.out.println("\n====================================");
            }
        }
    }
}
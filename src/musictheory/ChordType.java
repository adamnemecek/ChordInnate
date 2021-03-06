package musictheory;

import static musictheory.Accidental.*;
import static musictheory.NashvilleNumber.*;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
public enum ChordType {
    MAJOR(
            "maj",
            new NashvilleNumber[] {ONE, THREE, FIVE}
    ),
    MAJOR_SEVEN(
            "maj7",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SEVEN}
    ),
    SEVEN(
            "7",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN}
    ),
    ADD_NINE(
            "add9",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.NINE}
    ),
    MINOR(
            "m",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE}
    ),
    MINOR_SEVEN(
            "m7",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN}
    ),
    DIMINISHED(
            "dim",
            new NashvilleNumber[] {ONE, FLAT_THREE, NashvilleNumber.FLAT_FIVE}
    ),
    DIMINISHED_SEVEN(
            "dim7",
            new NashvilleNumber[] {ONE, FLAT_THREE, NashvilleNumber.FLAT_FIVE, NashvilleNumber.SIX}
    ),
    AUGMENTED(
            "aug",
            new NashvilleNumber[] {ONE, THREE, SHARP_FIVE}
    ),
    SUSPENDED_FOUR(
            "sus4",
            new NashvilleNumber[] {ONE, FOUR, FIVE}
    ),
    SUSPENDED_TWO(
            "sus2",
            new NashvilleNumber[] {ONE, TWO, FIVE}
    ),
    MAJOR_NINE(
            "maj9",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SEVEN, NashvilleNumber.NINE}
    ),
    MAJOR_THIRTEEN(
            "maj13",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SEVEN,
                    NashvilleNumber.NINE, NashvilleNumber.ELEVEN, NashvilleNumber.THIRTEEN}
    ),
    MAJOR_NINE_SHARP_ELEVEN(
            "maj9"+SHARP.indicator+"11",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SEVEN,
                    NashvilleNumber.NINE, SHARP_ELEVEN}
    ),
    MAJOR_THIRTEEN_SHARP_ELEVEN(
            "maj13"+SHARP.indicator+"11",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SEVEN,
                    NashvilleNumber.NINE, SHARP_ELEVEN, NashvilleNumber.THIRTEEN}
    ),
    SIX(
            "6",
            new NashvilleNumber[] {ONE, THREE, FIVE,  NashvilleNumber.SIX}
    ),
    SIX_ADD_NINE(
            "6add9",
            new NashvilleNumber[] {ONE, THREE, FIVE, NashvilleNumber.SIX,
                    NashvilleNumber.NINE}
    ),
    MAJOR_SEVEN_SHARP_FIVE(
            "maj7"+SHARP.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.SHARP_FIVE, NashvilleNumber.SEVEN}
    ),
    MAJOR_SEVEN_FLAT_FIVE(
            "maj7"+FLAT.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE, NashvilleNumber.SEVEN}
    ),
    MINOR_NINE(
            "m9",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE}
    ),
    MINOR_ELEVEN(
            "m11",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE, NashvilleNumber.ELEVEN}
    ),
    MINOR_THIRTEEN(
            "m13",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE, NashvilleNumber.ELEVEN, NashvilleNumber.THIRTEEN}
    ),
    MINOR_SIX(
            "m6",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, NashvilleNumber.SIX}
    ),
    MINOR_ADD_NINE(
            "madd9",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE,
                    NashvilleNumber.NINE}
    ),
    MINOR_SIX_ADD_NINE(
            "m6add9",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, NashvilleNumber.SIX,
                    NashvilleNumber.NINE}
    ),
    MINOR_MAJOR_SEVEN(
            "mM7",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, NashvilleNumber.SEVEN}
    ),
    MINOR_MAJOR_NINE(
            "mM9",
            new NashvilleNumber[] {ONE, FLAT_THREE, FIVE, NashvilleNumber.SEVEN,
                    NashvilleNumber.NINE}
    ),
    MINOR_SEVEN_FLAT_FIVE(
            "m7"+FLAT.indicator+"5",
            new NashvilleNumber[] {ONE, FLAT_THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN}
    ),
    HALF_DIMINISHED(
            "ø",
            new NashvilleNumber[] {ONE, FLAT_THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN} // TODO identical to m7b5 but has different symbol
    ),
    MINOR_SEVEN_SHARP_FIVE(
            "m7"+SHARP.indicator+"5",
            new NashvilleNumber[] {ONE, FLAT_THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    NINE(
            "9",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE}
    ),
    ELEVEN(
            "11",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE, NashvilleNumber.ELEVEN}
    ),
    THIRTEEN(
            "13",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE, NashvilleNumber.ELEVEN, NashvilleNumber.THIRTEEN}
    ),
    SEVEN_SUSPENDED_FOUR(
            "7sus4",
            new NashvilleNumber[] {ONE, FOUR, FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_FIVE(
            "7"+FLAT.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN}
    ),
    SEVEN_SHARP_FIVE(
            "7"+SHARP.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN}
    ),
    SEVEN_FLAT_NINE(
            "7"+FLAT.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.FLAT_NINE}
    ),
    SEVEN_SHARP_NINE(
            "7"+SHARP.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.SHARP_NINE}
    ),
    SEVEN_FLAT_FIVE_FLAT_NINE(
            "7"+FLAT.indicator+"5"+FLAT.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleNumber.FLAT_NINE}
    ),
    SEVEN_FLAT_FIVE_SHARP_NINE(
            "7"+FLAT.indicator+"5"+SHARP.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleNumber.SHARP_NINE}
    ),
    SEVEN_SHARP_FIVE_FLAT_NINE(
            "7"+SHARP.indicator+"5"+FLAT.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleNumber.FLAT_NINE}
    ),
    SEVEN_SHARP_FIVE_SHARP_NINE(
            "7"+SHARP.indicator+"5"+SHARP.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleNumber.SHARP_NINE}
    ),
    NINE_FLAT_FIVE(
            "9"+FLAT.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE}
    ),
    NINE_SHARP_FIVE(
            "9"+SHARP.indicator+"5",
            new NashvilleNumber[] {ONE, THREE, SHARP_FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE}
    ),
    THIRTEEN_SHARP_ELEVEN(
            "13"+SHARP.indicator+"11",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.NINE, SHARP_ELEVEN, NashvilleNumber.THIRTEEN}
    ),
    THIRTEEN_FLAT_NINE(
            "13"+FLAT.indicator+"9",
            new NashvilleNumber[] {ONE, THREE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.FLAT_NINE, NashvilleNumber.THIRTEEN}
    ),
    ELEVEN_FLAT_NINE(
            "11"+FLAT.indicator+"9",
            new NashvilleNumber[] {ONE, FIVE, FLAT_SEVEN,
                    NashvilleNumber.FLAT_NINE, NashvilleNumber.ELEVEN}
    ),
    POWER_CHORD(
            "5",
            new NashvilleNumber[] {ONE, FIVE}
    ),
    SUSPENDED_TWO_SUSPENDED_FOUR(
            "sus2sus4",
            new NashvilleNumber[] {ONE, TWO, FOUR, FIVE}
    ),
    FLAT_FIVE(
            "-5",
            new NashvilleNumber[] {ONE, THREE, NashvilleNumber.FLAT_FIVE}
    );

    final String chordSymbol;
    final NashvilleNumber[] nashvilleNumbers;

    ChordType(String chordSymbol, NashvilleNumber[] nashvilleNumbers) {
        this.chordSymbol = chordSymbol;
        this.nashvilleNumbers = nashvilleNumbers;
    }
}

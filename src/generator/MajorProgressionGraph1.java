package generator;

import musictheory.NashvilleNumber;

import static musictheory.NashvilleNumber.*;

/**
 * Created by Joseph on 1/12/16.
 *
 * References: http://www.people.vcu.edu/~bhammel/theory/resources/flash/interactive_chord_chart.swf
 *             "Chord Chart" from Techniques and Materials of Music (2013),
 *             by Thomas Benjamin, Michael Horvit, Timothy Koozin, Robert Nelson
 *             (p. 254)
 */
public class MajorProgressionGraph1 extends ChordProgression {

    public MajorProgressionGraph1() {
        NashvilleNumber[] intervals = {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN};

        for (NashvilleNumber i : intervals) {
            this.addVertex(i);
        }

        this.addEdge(ONE, ONE);
        this.addEdge(ONE, TWO);
        this.addEdge(ONE, THREE);
        this.addEdge(ONE, FOUR);
        this.addEdge(ONE, FIVE);
        this.addEdge(ONE, SIX);

        this.addEdge(TWO, FIVE);
        this.addEdge(TWO, SEVEN);

        this.addEdge(THREE, FOUR);
        this.addEdge(THREE, SIX);

        this.addEdge(FOUR, ONE);
        this.addEdge(FOUR, TWO);
        this.addEdge(FOUR, FIVE);
        this.addEdge(FOUR, SEVEN);

        this.addEdge(FIVE, ONE);
        this.addEdge(FIVE, SIX);
        this.addEdge(FIVE, SEVEN);

        this.addEdge(SIX, TWO);
        this.addEdge(SIX, FOUR);
        this.addEdge(SIX, FIVE);

        this.addEdge(SEVEN, ONE);
        this.addEdge(SEVEN, THREE);
    }
}

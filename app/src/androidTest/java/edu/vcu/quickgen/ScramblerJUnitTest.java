package edu.vcu.quickgen;

import org.junit.Test;
import static  org.junit.Assert.*;

import edu.vcu.quickgen.interfaces.Scrambler;
import edu.vcu.quickgen.components.scramblers.base.RandomGenerator;

/**
 * Created by Chase on 11/15/2015.
 */
public class ScramblerJUnitTest {

    private Scrambler scrambler;

    /**
     * Test to determine if the random scramble outputs the correct number of moves
     */
    @Test
    public void testRndScramblerLength(){

        scrambler = new RandomGenerator() {
            @Override
            public String getName() {
                return null;
            }
        };

        int numberOfMoves = 21;

        String scramble = scrambler.getScramble(numberOfMoves);
        String[] split = scramble.split(" ");
        assertEquals(numberOfMoves, split.length);
    }

    /**
     * Test to determine if the random scramble handles invalid input
     */
    @Test
    public void testRndScramblerInvalid(){
        scrambler = new RandomGenerator() {
            @Override
            public String getName() {
                return null;
            }
        };

        int numberOfMoves = -1;

        String scramble = scrambler.getScramble(numberOfMoves);
        assertEquals(0, scramble.length());
    }

    /**
     * Test to determine if the random scramble is generating different scrambles
     *
     * NOTE: There is a small probability that this test will fail if the generator happens to
     * generate two identical scrambles in a row
     */
    @Test
    public void testRndScramblerDuplicate(){
        scrambler = new RandomGenerator() {
            @Override
            public String getName() {
                return null;
            }
        };

        int numberOfMoves = 21;

        String scramble1 = scrambler.getScramble(numberOfMoves);
        String scramble2 = scrambler.getScramble(numberOfMoves);
        assertNotEquals(scramble1, scramble2);
    }
}

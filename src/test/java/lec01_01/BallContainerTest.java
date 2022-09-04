/**
 * This is part of Lec01_01: Environment Setup and Java Introduction.
 */
package lec01_01;

import java.util.Set;
import java.awt.Color;
import java.util.HashSet;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 * BallContainerTest - это тестовая площадка класса BallContainer.
 *
 * Напомним, что BallContainer - это контейнер для шариков. Однако вы можете только
 * поместите файл lec01_01.Ball в контейнер для шариков один раз. После того, как вы поместите файл lec01_01.Ball в контейнер для шариков,
 * дальнейшие попытки сделать это завершатся неудачей, так как файл lec01_01.Ball уже находится в
 * BallContainer! Аналогично, вы не можете ожидать удаления файла lec01_01.Ball из BallContainer
 * если он не находится внутри шарикоподшипника.
 *
 * @see Ball
 * @see lec01_01.BallContainer
 */
public class BallContainerTest {

    private static BallContainer ballcontainer = null;
    private static Ball[] b = null;

    private static final int NUM_BALLS_TO_TEST = 3;
    private static final double BALL_UNIT_VOLUME = 20.0;
    private static final double JUNIT_DOUBLE_DELTA = 0.0001;
    private static final Color[] COLORS = {Color.BLACK, Color.BLUE,
            Color.CYAN, new Color(255, 0, 0)};
    private static final int COLOR_COUNT = COLORS.length;

    @BeforeClass
    public static void setupForTests() throws Exception {

        assertTrue("Test case error, you must test at least 1 lec01_01.Ball!", NUM_BALLS_TO_TEST > 0);

        ballcontainer = new BallContainer();

        // Let's create the balls we need.
        b = new Ball[NUM_BALLS_TO_TEST];
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            b[i] = new Ball((i+1)*BALL_UNIT_VOLUME, COLORS[i % COLOR_COUNT]);
        }
    }


    /** Test to check that BallContainer.add(lec01_01.Ball) is implemented correctly */
    @Test
    public void testAdd() {
        double containerVolume;
        ballcontainer.clear();
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            assertTrue("BallContainer.add(lec01_01.Ball) failed to add a new lec01_01.Ball!", ballcontainer.add(b[i]));
            containerVolume = ballcontainer.getVolume();
            assertFalse("BallContainer.add(lec01_01.Ball) seems to allow the same lec01_01.Ball to be added twice!", ballcontainer.add(b[i]));
            assertEquals("BallContainer's volume has changed, but its contents have not!",
                    containerVolume, ballcontainer.getVolume(),
                    JUNIT_DOUBLE_DELTA);
            assertTrue("BallContainer does not contain a ball after it is supposed to have been added!",
                    ballcontainer.contains(b[i]));
        }
    }

    /** Test to check that BallContainer.remove(lec01_01.Ball) is implemented correctly */
    @Test
    public void testRemove() {
        ballcontainer.clear();
        double containerVolume;
        assertFalse("BallContainer.remove(lec01_01.Ball) should fail because ballcontainer is empty, but it didn't!", ballcontainer.remove(b[0]));
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.clear();
            for (int j=0; j<i; j++) {
                ballcontainer.add(b[j]);
            }
            for (int j=0; j<i; j++) {
                assertTrue("BallContainer.remove(lec01_01.Ball) failed to remove a lec01_01.Ball that is supposed to be inside",
                        ballcontainer.remove(b[j]));
                containerVolume = ballcontainer.getVolume();
                assertFalse("BallContainer still contains a ball after it is supposed to have been removed!",
                        ballcontainer.contains(b[j]));
                assertEquals("BallContainer's volume has changed, but its contents have not!",
                        containerVolume, ballcontainer.getVolume(),
                        JUNIT_DOUBLE_DELTA);
            }
            for (int j=i; j<NUM_BALLS_TO_TEST; j++) {
                assertFalse("BallContainer.remove(lec01_01.Ball) did not fail for a lec01_01.Ball that is not inside", ballcontainer.remove(b[j]));
            }
        }
    }

    /**
     * Test to check that BallContainer.iterator() is implemented
     * correctly.
     */
    @Test
    public void testIterator() {
        Set<Ball> allBalls = new HashSet<Ball>();
        Set<Ball> seenBalls = new HashSet<Ball>();
        ballcontainer.clear();
        assertEquals("BallContainer is not empty after being cleared!",
                0, ballcontainer.size());
        for (Ball aBall: b) {
            ballcontainer.add(aBall);
            allBalls.add(aBall);
        }
        int i=0;
        for (Ball aBall: ballcontainer) {
            assertTrue("Iterator returned a ball that isn't in the container: " + aBall,
                    allBalls.contains(aBall));
            assertFalse("Iterator returned the same ball twice: " + aBall,
                    seenBalls.contains(aBall));
            seenBalls.add(aBall);
            i++;
        }
        assertEquals("BallContainer iterator did not return enough items!",
                i, b.length);
    }

    /**
     * Test that BallContainer.clear() is implemented correctly.
     */
    @Test
    public void testClear() {
        ballcontainer.clear();
        assertEquals("BallContainer is not empty after being cleared!",
                0, ballcontainer.size());
        ballcontainer.add(b[0]);
        ballcontainer.clear();
        assertEquals("BallContainer is not empty after being cleared!",
                0, ballcontainer.size());
    }

    /** Test that BallContainer.getVolume() is implemented correctly */
    @Test
    public void testVolume() {
        ballcontainer.clear();
        assertEquals("Volume of an empty BallContainer is not zero!",
                0, ballcontainer.getVolume(), JUNIT_DOUBLE_DELTA);
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.add(b[i]);
            assertEquals("Volume of BallContainer with "+(i+1)+" ball(s)",
                    (i+1)*(i+2)*BALL_UNIT_VOLUME/2,
                    ballcontainer.getVolume(),
                    JUNIT_DOUBLE_DELTA);
        }

    }

    /** Test that BallContainer.differentColors() is implemented correctly */
    @Test
    public void testDifferentColors() {
        ballcontainer.clear();
        assertEquals("The number of different colors of an empty "
                        + "BallContainer is not zero!",
                0, ballcontainer.differentColors(), JUNIT_DOUBLE_DELTA);
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.add(b[i]);
            assertEquals("The number of different colors of a "
                            + "BallContainer with "+(i+1)+" ball(s)",
                    ballcontainer.size() > COLOR_COUNT ?
                            COLOR_COUNT : ballcontainer.size(),
                    ballcontainer.differentColors(),
                    JUNIT_DOUBLE_DELTA);
        }
    }

    /** Test that BallContainer.areSameColor() is implemented correctly */
    @Test
    public void testAreSameColor() {
        ballcontainer.clear();
        assertTrue("Balls in an empty "
                        + "BallContainer seem to have different colors!",
                ballcontainer.areSameColor());
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.add(b[i]);
            assertEquals("All balls in a "
                            + "BallContainer with "+(i+1)+" ball(s)"
                            + "seem to have the same color",
                    ballcontainer.size() > 1 ?
                            false : true,
                    ballcontainer.areSameColor());
        }
    }

    /** Test that size() returns the correct number. */
    @Test
    public void testSize() {
        ballcontainer.clear();
        assertEquals("size() of empty BallContainer is not zero!",
                0, ballcontainer.size());
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.add(b[i]);
            assertEquals("size() of BallContainer with "+(i+1)+" ball(s)",
                    i+1, ballcontainer.size());
        }
    }

    /** Test that contains() returns true if and only if the ball
     * is contained in the container. */
    @Test
    public void testContains() {
        ballcontainer.clear();
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            assertFalse("Empty BallContainer seems to contain a ball!", ballcontainer.contains(b[i]));
        }
        for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
            ballcontainer.add(b[i]);
            assertTrue("BallContainer does not contain a lec01_01.Ball that is supposed to be inside!", ballcontainer.contains(b[i]));
            for (int j=i+1; j<NUM_BALLS_TO_TEST; j++) {
                assertFalse("BallContainer seems to contain a lec01_01.Ball that is not inside!", ballcontainer.contains(b[j]));
            }
        }
    }

    /** Test that clear removes all balls. **/
    @Test
    public void testVolumeAfterClear() {
        ballcontainer.add(b[0]);
        ballcontainer.clear();
        assertEquals("The volume of BallContainer after being cleared is not reset to 0!",
                0, ballcontainer.getVolume(), JUNIT_DOUBLE_DELTA);
    }

}
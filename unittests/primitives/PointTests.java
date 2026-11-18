package primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for class {@link Point}.
 * The tests verify:
 * <ul>
 * <li>{@link Point#subtract(Point)}</li>
 * <li>{@link Point#add(Vector)}</li>
 * <li>{@link Point#distanceSquared(Point)}</li>
 * <li>{@link Point#distance(Point)}</li>
 * </ul>
 */
class PointTests {

    /** Constant delta value for accuracy when comparing double values */
    private static final double DELTA = 1e-6;

    /** Point (1, 2, 3) used in point tests */
    private static final Point P1 = new Point(1, 2, 3);

    /** Point (2, 4, 6) used in point tests */
    private static final Point P2 = new Point(2, 4, 6);

    /** Vector (1, 2, 3) used in point tests */
    private static final Vector V1 = new Vector(1, 2, 3);

    /** Error message when a valid action throws an unexpected exception */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /** Error message for incorrect addition result */
    private static final String ERROR_ADD = "ERROR: Point + Vector does not work correctly";

    /** Error message for incorrect subtraction result */
    private static final String ERROR_SUBTRACT = "ERROR: Point - Point does not work correctly";

    /** Error message for incorrect squared distance result */
    private static final String ERROR_DIST_SQUARED = "ERROR: distanceSquared() calculates incorrect value";

    /** Error message for incorrect distance result */
    private static final String ERROR_DIST = "ERROR: distance() calculates incorrect value";

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Subtract two distinct points to get a valid vector
        assertDoesNotThrow(() -> P2.subtract(P1), ERROR_EXCEPTION_THROWN);
        Vector result = P2.subtract(P1);
        assertEquals(V1, result, ERROR_SUBTRACT);

        // =============== Boundary Values Tests ==================

        // BV01: Subtract a point from itself (should throw exception as it results in zero vector)
        assertThrows(IllegalArgumentException.class, () -> P1.subtract(P1),
                "ERROR: Subtracting a point from itself did not throw exception for zero vector");
    }

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Add a vector to a point
        assertDoesNotThrow(() -> P1.add(V1), ERROR_EXCEPTION_THROWN);
        Point result = P1.add(V1);
        assertEquals(P2, result, ERROR_ADD);

        // =============== Boundary Values Tests ==================

        // BV01: Add an opposite vector to a point resulting in Point.ZERO (0,0,0)
        Vector oppositeV = new Vector(-1, -2, -3);
        assertDoesNotThrow(() -> P1.add(oppositeV), ERROR_EXCEPTION_THROWN);
        Point resultZero = P1.add(oppositeV);
        assertEquals(Point.ZERO, resultZero, ERROR_ADD);
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Calculate squared distance between two distinct points
        // Distance between (1,2,3) and (2,4,6) is: 1^2 + 2^2 + 3^2 = 1 + 4 + 9 = 14
        double result = P1.distanceSquared(P2);
        assertEquals(14d, result, DELTA, ERROR_DIST_SQUARED);

        // =============== Boundary Values Tests ==================

        // BV01: Squared distance from a point to itself
        double resultSelf = P1.distanceSquared(P1);
        assertEquals(0d, resultSelf, DELTA, ERROR_DIST_SQUARED);
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Calculate distance between two distinct points
        // Distance between (1,2,3) and (2,4,6) is sqrt(14)
        double result = P1.distance(P2);
        assertEquals(Math.sqrt(14), result, DELTA, ERROR_DIST);

        // =============== Boundary Values Tests ==================

        // BV01: Distance from a point to itself
        double resultSelf = P1.distance(P1);
        assertEquals(0d, resultSelf, DELTA, ERROR_DIST);
    }
}
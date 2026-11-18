package geometries.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Unit tests for class {@link Tube}.
 * The tests verify:
 * <ul>
 * <li>{@link Tube#getNormal(Point)}</li>
 * </ul>
 */
class TubeTests {

    /** Constant delta value for accuracy when comparing double values */
    private static final double DELTA = 1e-6;

    /** Error message for incorrect normal value */
    private static final String ERROR_WRONG_NORMAL = "ERROR: getNormal() returned a wrong vector";

    /** Error message for non-normalized vector */
    private static final String ERROR_NOT_NORMALIZED = "ERROR: Normal vector is not a unit vector";

    /** Error message when a valid action throws an unexpected exception */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Ray axis = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        Tube tube = new Tube(1d, axis);

        // ============ Equivalence Partitions Tests ==============

        // EP01: Point on the tube surface where projection on axis ray is positive (t > 0)
        Point pointEP1 = new Point(1, 0, 2);
        assertDoesNotThrow(() -> tube.getNormal(pointEP1), ERROR_EXCEPTION_THROWN);
        Vector normalEP1 = tube.getNormal(pointEP1);
        assertEquals(1d, normalEP1.length(), DELTA, ERROR_NOT_NORMALIZED);
        assertEquals(new Vector(1, 0, 0), normalEP1, ERROR_WRONG_NORMAL);

        // EP02: Point on the tube surface where projection on axis ray is negative (t < 0)
        Point pointEP2 = new Point(1, 0, -1);
        assertDoesNotThrow(() -> tube.getNormal(pointEP2), ERROR_EXCEPTION_THROWN);
        Vector normalEP2 = tube.getNormal(pointEP2);
        assertEquals(1d, normalEP2.length(), DELTA, ERROR_NOT_NORMALIZED);
        assertEquals(new Vector(1, 0, 0), normalEP2, ERROR_WRONG_NORMAL);

        // =============== Boundary Values Tests ==================

        // BV01: Point on the tube surface against the ray head (projection t = 0)
        Point pointBV = new Point(1, 0, 1);
        assertDoesNotThrow(() -> tube.getNormal(pointBV), ERROR_EXCEPTION_THROWN);
        Vector normalBV = tube.getNormal(pointBV);
        assertEquals(1d, normalBV.length(), DELTA, ERROR_NOT_NORMALIZED);
        assertEquals(new Vector(1, 0, 0), normalBV, ERROR_WRONG_NORMAL);
    }
}

package geometries.impl;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for class {@link Sphere}.
 * The tests verify:
 * <ul>
 * <li>{@link Sphere#getNormal(Point)}</li>
 * </ul>
 */
class SphereTests {

    /**
     * Constant delta value for accuracy when comparing double values
     */
    private static final double DELTA = 1e-6;

    /**
     * Error message for incorrect normal value
     */
    private static final String ERROR_WRONG_NORMAL = "ERROR: getNormal() returned a wrong vector";

    /**
     * Error message when a valid action throws an unexpected exception
     */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Test method for {@link Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // Sphere centered at (1, 1, 1) with radius 5
        // Point (1, 5, 4) is on the sphere since: (1-1)^2 + (5-1)^2 + (4-1)^2 = 0 + 16 + 9 = 25
        Point center = new Point(1, 1, 1);
        Sphere sphere = new Sphere(center, 5);

        // ============ Equivalence Partitions Tests ==============

        // EP01: Point on the sphere surface
        Point pointOnSphere = new Point(1, 5, 4);
        assertDoesNotThrow(() -> sphere.getNormal(pointOnSphere), ERROR_EXCEPTION_THROWN);

        Vector normal = sphere.getNormal(pointOnSphere);

        // Calculated manually: Normal vector should be normalized of (pointOnSphere - center)
        // (1, 5, 4) - (1, 1, 1) = (0, 4, 3). Length of (0,4,3) is 5.
        // Normalized: (0, 0.8, 0.6)
        Vector expectedNormal = new Vector(0, 0.8, 0.6);

        assertEquals(expectedNormal, normal, ERROR_WRONG_NORMAL);
    }
}
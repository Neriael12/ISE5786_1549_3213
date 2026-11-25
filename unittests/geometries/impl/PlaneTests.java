package geometries.impl;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for class {@link Plane}.
 * The tests verify:
 * <ul>
 * <li>Constructor {@link Plane#Plane(Point, Point, Point)}</li>
 * <li>Constructor {@link Plane#Plane(Point, Vector)}</li>
 * <li>{@link Plane#getNormal(Point)}</li>
 * </ul>
 */
class PlaneTests {
    /**
     * Default constructor for documentation tools
     */
    public PlaneTests() {
    }

    /**
     * Constant delta value for accuracy when comparing double values
     */
    private static final double DELTA = 1e-6;

    /**
     * Error message for non-normalized vector
     */
    private static final String ERROR_NOT_NORMALIZED = "ERROR: Normal vector is not a unit vector";

    /**
     * Error message for incorrect orthogonal calculation
     */
    private static final String ERROR_NOT_ORTHOGONAL = "ERROR: Normal is not orthogonal to the plane";

    /**
     * Error message when a constructor doesn't throw on invalid points
     */
    private static final String ERROR_CONSTRUCTOR_FAIL = "ERROR: Constructor failed to throw exception for invalid points";

    /**
     * Error message when a valid action throws an unexpected exception
     */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructorThreePoints() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Three distinct, non-colinear points
        assertDoesNotThrow(() -> new Plane(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        ), ERROR_EXCEPTION_THROWN);

        // =============== Boundary Values Tests ==================

        // BV01: Points 1 and 2 are co-located (coincident)
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 2, 3),
                new Point(1, 2, 3),
                new Point(4, 5, 6)
        ), ERROR_CONSTRUCTOR_FAIL);

        // BV02: Points 1 and 3 are co-located (coincident)
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 2, 3),
                new Point(4, 5, 6),
                new Point(1, 2, 3)
        ), ERROR_CONSTRUCTOR_FAIL);

        // BV03: Points 2 and 3 are co-located (coincident)
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(4, 5, 6),
                new Point(1, 2, 3),
                new Point(1, 2, 3)
        ), ERROR_CONSTRUCTOR_FAIL);

        // BV04: All three points are co-located (coincident)
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 2, 3),
                new Point(1, 2, 3),
                new Point(1, 2, 3)
        ), ERROR_CONSTRUCTOR_FAIL);

        // BV05: Three points are on the same line (colinear)
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                new Point(1, 1, 1),
                new Point(2, 2, 2),
                new Point(3, 3, 3)
        ), ERROR_CONSTRUCTOR_FAIL);
    }

    /**
     * Test method for {@link Plane#Plane(Point, Vector)}.
     */
    @Test
    void testConstructorPointVector() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Verify normal vector is normalized
        Vector nonNormalized = new Vector(1, 2, 2); // Length: 3
        Plane plane = new Plane(new Point(0, 0, 1), nonNormalized);
        assertEquals(1d, plane.getNormal(new Point(0, 0, 1)).length(), DELTA, ERROR_NOT_NORMALIZED);
    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p0 = new Point(0, 0, 1);
        Point p1 = new Point(1, 0, 1);
        Point p2 = new Point(0, 1, 1);
        Plane plane = new Plane(p0, p1, p2);

        // ============ Equivalence Partitions Tests ==============

        // EP01: Point on the plane that is NOT the reference point
        Point testPointEP = new Point(0.5, 0.5, 1);
        assertDoesNotThrow(() -> plane.getNormal(testPointEP), ERROR_EXCEPTION_THROWN);
        Vector normalEP = plane.getNormal(testPointEP);

        // Ensure normal is of unit length
        assertEquals(1d, normalEP.length(), DELTA, ERROR_NOT_NORMALIZED);

        // Ensure normal is orthogonal to plane vectors (e.g., p1 - p0)
        Vector v1 = p1.subtract(p0);
        assertEquals(0d, normalEP.dotProduct(v1), DELTA, ERROR_NOT_ORTHOGONAL);

        // =============== Boundary Values Tests ==================

        // BV01: Point is exactly the reference point of the plane
        assertDoesNotThrow(() -> plane.getNormal(p0), ERROR_EXCEPTION_THROWN);
        Vector normalBV = plane.getNormal(p0);

        // Ensure normal is of unit length
        assertEquals(1d, normalBV.length(), DELTA, ERROR_NOT_NORMALIZED);

        // Ensure normal is orthogonal to plane vectors
        assertEquals(0d, normalBV.dotProduct(v1), DELTA, ERROR_NOT_ORTHOGONAL);
    }
}

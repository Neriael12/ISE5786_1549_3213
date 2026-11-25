package geometries.impl;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for class {@link Triangle}.
 * The tests verify:
 * <ul>
 * <li>{@link Triangle#getNormal(Point)}</li>
 * </ul>
 */
class TriangleTests {

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
    private static final String ERROR_NOT_ORTHOGONAL = "ERROR: Normal is not orthogonal to triangle edge";

    /**
     * Error message when a valid action throws an unexpected exception
     */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p0 = new Point(0, 0, 1);
        Point p1 = new Point(2, 0, 1);
        Point p2 = new Point(0, 2, 1);
        Triangle triangle = new Triangle(p0, p1, p2);

        // ============ Equivalence Partitions Tests ==============

        // EP01: Point is inside the triangle
        Point insidePoint = new Point(0.5, 0.5, 1);
        assertDoesNotThrow(() -> triangle.getNormal(insidePoint), ERROR_EXCEPTION_THROWN);
        Vector normal = triangle.getNormal(insidePoint);

        // Ensure normal is of unit length
        assertEquals(1d, normal.length(), DELTA, ERROR_NOT_NORMALIZED);

        // Ensure normal is orthogonal to one of the edges
        Vector edge = p1.subtract(p0);
        assertEquals(0d, normal.dotProduct(edge), DELTA, ERROR_NOT_ORTHOGONAL);
    }
}
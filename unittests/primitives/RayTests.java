package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for class {@link Ray}.
 * The tests verify:
 * <ul>
 * <li>Constructor {@link Ray#Ray(Point, Vector)}</li>
 * </ul>
 */
class RayTests {

    /**
     * Constant delta value for accuracy when comparing double values
     */
    private static final double DELTA = 1e-6;

    /**
     * Point (1, 2, 3) used in ray tests
     */
    private static final Point P0 = new Point(1, 2, 3);

    /**
     * Error message when a valid action throws an unexpected exception
     */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Error message for non-normalized ray direction
     */
    private static final String ERROR_NOT_NORMALIZED = "ERROR: Ray direction is not normalized";

    /**
     * Error message for wrong ray origin
     */
    private static final String ERROR_WRONG_ORIGIN = "ERROR: Ray origin is incorrect";

    /**
     * Error message for wrong ray direction
     */
    private static final String ERROR_WRONG_DIRECTION = "ERROR: Ray direction is incorrect";

    /**
     * Test method for {@link Ray#Ray(Point, Vector)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Construct a ray with a non-normalized direction vector
        Vector v = new Vector(2, 4, 4); // Length: sqrt(4+16+16) = 6
        assertDoesNotThrow(() -> new Ray(P0, v), ERROR_EXCEPTION_THROWN);
        Ray ray = new Ray(P0, v);

        // Ensure origin is unchanged
        assertEquals(P0, ray.origin(), ERROR_WRONG_ORIGIN);

        // Ensure direction vector is normalized (length = 1)
        assertEquals(1d, ray.direction().length(), DELTA, ERROR_NOT_NORMALIZED);

        // Ensure direction vector matches expected normalized vector (2/6, 4/6, 4/6) = (1/3, 2/3, 2/3)
        Vector expectedDirection = new Vector(1.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0);
        assertEquals(expectedDirection, ray.direction(), ERROR_WRONG_DIRECTION);
    }
}

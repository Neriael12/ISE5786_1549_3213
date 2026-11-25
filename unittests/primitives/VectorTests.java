package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for class {@link Vector}.
 * The tests verify:
 * <ul>
 * <li>Constructor {@link Vector#Vector(double, double, double)}</li>
 * <li>{@link Vector#add(Vector)}</li>
 * <li>{@link Vector#subtract(Point)} (tested via Vector subtraction)</li>
 * <li>{@link Vector#scale(double)}</li>
 * <li>{@link Vector#dotProduct(Vector)}</li>
 * <li>{@link Vector#crossProduct(Vector)}</li>
 * <li>{@link Vector#lengthSquared()}</li>
 * <li>{@link Vector#length()}</li>
 * <li>{@link Vector#normalize()}</li>
 * </ul>
 */
class VectorTests {

    /**
     * Constant delta value for accuracy when comparing double values
     */
    private static final double DELTA = 1e-6;

    /**
     * Vector (1, 2, 3) used in vector tests
     */
    private static final Vector V1 = new Vector(1, 2, 3);

    /**
     * Vector (2, 4, 6) used in vector tests
     */
    private static final Vector V2 = new Vector(2, 4, 6);

    /**
     * Vector (0, 3, -2) orthogonal to V1
     */
    private static final Vector V3 = new Vector(0, 3, -2);

    /**
     * Error message when a valid action throws an unexpected exception
     */
    private static final String ERROR_EXCEPTION_THROWN = "ERROR: Exception thrown unexpectedly";

    /**
     * Error message when constructor fails to throw on zero vector
     */
    private static final String ERROR_ZERO_VECTOR = "ERROR: Zero vector construction did not throw exception";

    /**
     * Error message for incorrect calculation results
     */
    private static final String ERROR_WRONG_RESULT = "ERROR: Method calculated wrong result";

    /**
     * Test method for {@link Vector#Vector(double, double, double)} and {@link Vector#Vector(Double3)}.
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================

        // BV01: Constructor with double coordinates resulting in zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), ERROR_ZERO_VECTOR);

        // BV02: Constructor with Double3 object resulting in zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), ERROR_ZERO_VECTOR);

        assertDoesNotThrow(() -> new Vector(1, 2, 3), "constracting not work");

        assertDoesNotThrow(() -> new Vector(new Double3(1, 2, 1)), "constracting not work");
    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Add two non-parallel vectors
        assertDoesNotThrow(() -> V1.add(V3), ERROR_EXCEPTION_THROWN);
        Vector result = V1.add(V3);
        assertEquals(new Vector(1, 5, 1), result, ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        // BV01: Add opposite vectors (should throw since result is a zero vector)
        Vector oppositeV = new Vector(-1, -2, -3);
        assertThrows(IllegalArgumentException.class, () -> V1.add(oppositeV),
                "ERROR: Adding opposite vectors did not throw exception for zero vector");
    }

    /**
     * Test method for {@link Vector#subtract(Point)} (Vector subtraction).
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Subtract two non-parallel vectors
        assertDoesNotThrow(() -> V2.subtract(V1), ERROR_EXCEPTION_THROWN);
        Vector result = V2.subtract(V1);
        assertEquals(V1, result, ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        // BV01: Subtract a vector from itself (should throw since result is a zero vector)
        assertThrows(IllegalArgumentException.class, () -> V1.subtract(V1),
                "ERROR: Subtracting a vector from itself did not throw exception for zero vector");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Scale by a positive scalar
        assertDoesNotThrow(() -> V1.scale(2), ERROR_EXCEPTION_THROWN);
        assertEquals(V2, V1.scale(2), ERROR_WRONG_RESULT);

        // EP02: Scale by a negative scalar
        assertDoesNotThrow(() -> V1.scale(-1), ERROR_EXCEPTION_THROWN);
        assertEquals(new Vector(-1, -2, -3), V1.scale(-1), ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        // BV01: Scale by zero (should throw since result is a zero vector)
        assertThrows(IllegalArgumentException.class, () -> V1.scale(0),
                "ERROR: Scaling by zero did not throw exception for zero vector");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Dot product of acute angle vectors (result should be positive)
        // (1,2,3) * (2,4,6) = 2 + 8 + 18 = 28
        double resultEP1 = V1.dotProduct(V2);
        assertEquals(28d, resultEP1, DELTA, ERROR_WRONG_RESULT);

        // EP02: Dot product of obtuse angle vectors (result should be negative)
        Vector opposite = new Vector(-1, -1, -1);
        double resultEP2 = V1.dotProduct(opposite);
        assertEquals(-6d, resultEP2, DELTA, ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        // BV01: Dot product of orthogonal vectors (result must be zero)
        // (1,2,3) * (0,3,-2) = 0 + 6 - 6 = 0
        double resultBV = V1.dotProduct(V3);
        assertEquals(0d, resultBV, DELTA, ERROR_WRONG_RESULT);
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Cross product of two non-parallel vectors
        Vector vr = V1.crossProduct(V3);

        // Ensure result is orthogonal to both vectors
        assertEquals(0d, vr.dotProduct(V1), DELTA, "ERROR: crossProduct() result is not orthogonal to first vector");
        assertEquals(0d, vr.dotProduct(V3), DELTA, "ERROR: crossProduct() result is not orthogonal to second vector");

        // Ensure length of cross product is |v1| * |v3| * sin(alpha) (V1 and V3 are orthogonal, so sin(alpha) = 1)
        assertEquals(V1.length() * V3.length(), vr.length(), DELTA, ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        // BV01: Cross product of parallel vectors (should throw since result is a zero vector)
        assertThrows(IllegalArgumentException.class, () -> V1.crossProduct(V2),
                "ERROR: crossProduct() of parallel vectors did not throw exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Squared length of a vector
        // |(1,2,3)|^2 = 1^2 + 2^2 + 3^2 = 14
        double result = V1.lengthSquared();
        assertEquals(14d, result, DELTA, ERROR_WRONG_RESULT);
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Length of a vector using pythagorean values
        // |(3,4,0)| = sqrt(9 + 16 + 0) = 5
        Vector pythagoreanV = new Vector(3, 4, 0);
        double result = pythagoreanV.length();
        assertEquals(5d, result, DELTA, ERROR_WRONG_RESULT);

        // =============== Boundary Values Tests ==================

        Vector negetive = new Vector(-3, -4, 0);
        double result1 = negetive.length();
        assertEquals(5d, result1, DELTA, ERROR_WRONG_RESULT);
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============

        // EP01: Normalize a vector
        assertDoesNotThrow(V1::normalize, ERROR_EXCEPTION_THROWN);
        Vector normalized = V1.normalize();

        // Ensure the returned vector is unit length
        assertEquals(1d, normalized.length(), DELTA, "ERROR: normalized vector is not a unit vector");

        // Ensure the normalized vector is co-directional with the original vector (cross product is zero, throws exception)
        assertThrows(IllegalArgumentException.class, () -> V1.crossProduct(normalized),
                "ERROR: normalized vector is not co-directional with the original vector");

        // Ensure it is in the same direction, not opposite (dot product should be positive)
        assertTrue(V1.dotProduct(normalized) > 0, "ERROR: normalized vector is in opposite direction");
    }
}
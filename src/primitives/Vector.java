package primitives;

/**
 * Class Vector is the basic object in geometry, representing a direction and
 * magnitude. It inherits from Point and uses its coordinates.
 */
public class Vector extends Point {

	/**
	 * Constant vector for X axis (1,0,0).
	 */
	public static final Vector AXIS_X = new Vector(1, 0, 0);

	/**
	 * Constant vector for Y axis (0,1,0).
	 */
	public static final Vector AXIS_Y = new Vector(0, 1, 0);

	/**
	 * Constant vector for Z axis (0,0,1).
	 */
	public static final Vector AXIS_Z = new Vector(0, 0, 1);

	/**
	 * Constructor to initialize Vector based on three double values. Throws
	 * IllegalArgumentException if the vector is a zero vector. * @param x the x
	 * coordinate
	 * 
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public Vector(double x, double y, double z) {
		this(new Double3(x, y, z));
	}

	/**
	 * Constructor to initialize Vector based on Double3 object. Throws
	 * IllegalArgumentException if the vector is a zero vector. * @param xyz
	 * coordinates of the vector
	 */
	public Vector(Double3 xyz) {
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
		super(xyz);
	}

	/**
	 * Adds another vector to this vector. * @param vector the vector to add
	 * 
	 * @return a new Vector representing the sum
	 */
	public Vector add(Vector vector) {
		return new Vector(this._xyz.add(vector._xyz));
	}

	/**
	 * Multiplies the vector by a scalar. * @param scalar the scalar value
	 * 
	 * @return a new Vector representing the scaled vector
	 */
	public Vector scale(double scalar) {
		return new Vector(this._xyz.scale(scalar));
	}

	/**
	 * Calculates the dot product of this vector with another vector.
	 * 
	 * @param other the other vector
	 * @return the scalar result of the dot product
	 */
	public double dotProduct(Vector other) {
		return this._xyz._d1() * other._xyz._d1() + this._xyz._d2() * other._xyz._d2()
				+ this._xyz._d3() * other._xyz._d3();
	}

	/**
	 * Calculates the cross product of this vector with another vector.
	 * 
	 * @param other the other vector
	 * @return a new Vector representing the cross product result
	 */
	public Vector crossProduct(Vector other) {
		double x = this._xyz._d2() * other._xyz._d3() - this._xyz._d3() * other._xyz._d2();
		double y = this._xyz._d3() * other._xyz._d1() - this._xyz._d1() * other._xyz._d3();
		double z = this._xyz._d1() * other._xyz._d2() - this._xyz._d2() * other._xyz._d1();
		return new Vector(x, y, z);
	}

	/**
	 * Calculates the squared length of the vector. * @return the squared length
	 */
	public double lengthSquared() {
		return dotProduct(this);
	}

	/**
	 * Calculates the length of the vector. * @return the magnitude (length)
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * Returns a normalized version of this vector (length = 1). * @return a new
	 * normalized Vector
	 */
	public Vector normalize() {
		return scale(1d / length());
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Vector: " + super.toString();
	}
}

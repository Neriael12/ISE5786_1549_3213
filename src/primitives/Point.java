package primitives;

/**
 * This class represents a 3D point in space. It serves as the base class for
 * Vector.
 */
public class Point {
	/**
	 * The coordinates of the point.
	 */
	protected final Double3 _xyz;

	/**
	 *  The origin point (0,0,0).
	 */
	public static final Point ZERO = new Point(0, 0, 0);

	/**
	 * Constructor that takes three double values. * @param x the x coordinate
	 * 
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public Point(double x, double y, double z) {
		this._xyz = new Double3(x, y, z);
	}

	/**
	 * Constructor that takes a Double3 object. * @param xyz the Double3 object
	 * containing the coordinates
	 */
	public Point(Double3 xyz) {
		this._xyz = xyz;
	}

	/**
	 * Subtracts a point from this point to create a vector. * @param other the
	 * point to subtract from this point
	 * 
	 * @return a new Vector from the other point to this point
	 */
	public Vector subtract(Point other) {
		return new Vector(this._xyz.subtract(other._xyz));
	}

	/**
	 * Adds a vector to this point. * @param vector the vector to add
	 * 
	 * @return a new Point after the addition
	 */
	public Point add(Vector vector) {
		return new Point(this._xyz.add(vector._xyz));
	}

	/**
	 * Calculates the squared distance between this point and another point.
	 * 
	 * @param other the other point
	 * @return the squared distance
	 */
	public double distanceSquared(Point other) {
		double dx = this._xyz._d1() - other._xyz._d1();
		double dy = this._xyz._d2() - other._xyz._d2();
		double dz = this._xyz._d3() - other._xyz._d3();

		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * Calculates the distance between this point and another point. * @param other
	 * the other point
	 * 
	 * @return the distance
	 */
	public double distance(Point other) {
		return Math.sqrt(distanceSquared(other));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Point other)
			return this._xyz.equals(other._xyz);
		return false;
	}

	@Override
	public String toString() {
		return "Point: " + _xyz;
	}
}
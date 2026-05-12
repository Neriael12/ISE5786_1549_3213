package geometries.impl;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class representing an infinite tube in 3D space. Inherits from
 * RadialGeometry.
 */
public class Tube extends RadialGeometry {

	/**
	 * The central axis of the tube.
	 */
	protected final Ray _axis;

	/**
	 * Constructor that initializes a tube with a radius and an axis ray.
	 * 
	 * @param radius the radius of the tube
	 * @param axis   the central axis ray of the tube
	 */
	public Tube(double radius, Ray axis) {
		super(radius);
		this._axis = axis;
	}

	/**
	 * Getter for the axis ray.
	 * 
	 * @return the axis ray
	 */
	public Ray getAxis() {
		return _axis;
	}

	/**
	 * Calculates the normal to the tube at a given point on its surface. In step 1,
	 * this method returns null.
	 * 
	 * @param point the point on the tube's surface
	 * @return null (to be implemented in later stages)
	 */
	@Override
	public Vector getNormal(Point point) {
		return null; // Will be implemented in the next stage
	}

	@Override
	public String toString() {
		return "Tube: axis=" + _axis + ", radius=" + radius;
	}
}
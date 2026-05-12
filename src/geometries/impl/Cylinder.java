package geometries.impl;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class representing a finite cylinder in 3D space. Inherits from Tube.
 */
public class Cylinder extends Tube {

	/**
	 * The height of the cylinder.
	 */
	private final double height;

	/**
	 * Constructor that initializes a cylinder with a radius, an axis ray, and a
	 * height.
	 * 
	 * @param radius the radius of the cylinder
	 * @param axis   the central axis ray of the cylinder
	 * @param height the height of the cylinder
	 */
	public Cylinder(double radius, Ray axis, double height) {
		super(radius, axis);
		this.height = height;
	}

	/**
	 * Calculates the normal to the cylinder at a given point on its surface. In
	 * step 1, this method returns null.
	 * 
	 * @param point the point on the cylinder's surface
	 * @return null (to be implemented in later stages)
	 */
	@Override
	public Vector getNormal(Point point) {
		return null; // Will be implemented in the next stage
	}

	@Override
	public String toString() {
		return "Cylinder: height=" + height + ", " + super.toString();
	}
}
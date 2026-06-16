package geometries.impl;

import primitives.Point;
import primitives.Vector;

/**
 * Class representing a sphere in 3D space. Inherits from RadialGeometry and
 * implements the normal calculation.
 */
public class Sphere extends RadialGeometry {
	/**
	 * The center point of the sphere.
	 */
	private final Point _center;

	/**
	 * Constructor that initializes a sphere with a center point and a radius.
	 * 
	 * @param center the center point of the sphere
	 * @param radius the radius of the sphere
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this._center = center;
	}

	/**
	 * Calculates the normal to the sphere at a given point on its surface. * @param
	 * point the point on the sphere's surface
	 * 
	 * @return the normalized normal vector: (point - center).normalize()
	 */
	@Override
	public Vector getNormal(Point point) {
		// Normal to a sphere at point P is (P - O).normalize()
		return point.subtract(_center).normalize();
	}

	@Override
	public String toString() {
		return "Sphere: center=" + _center + ", radius=" + _radius;
	}
}
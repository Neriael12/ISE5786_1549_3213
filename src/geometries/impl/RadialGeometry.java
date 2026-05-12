package geometries.impl;

import geometries.api.Geometry;

/**
 * Abstract class serving as a base for all radial geometries (e.g., Sphere,
 * Tube, Cylinder).
 */
public abstract class RadialGeometry extends Geometry {

	/**
	 * The radius of the geometry.
	 */
	protected final double radius;

	/**
	 * The squared radius of the geometry (calculated once for performance).
	 */
	protected final double _radiusSquared;

	/**
	 * Constructor for RadialGeometry. Initializes the radius and calculates the
	 * squared radius. * @param radius the radius of the geometry
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
		this._radiusSquared = radius * radius;
	}
}
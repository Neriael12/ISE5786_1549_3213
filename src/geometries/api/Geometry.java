package geometries.api;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry abstract class serving as the base for all geometric shapes.
 */
public abstract class Geometry {
	/**
	 * Calculates the normal vector to the geometry surface at a given point.
	 * * @param point the point on the geometry surface
	 * 
	 * @return the normal vector at the given point
	 */
	public abstract Vector getNormal(Point point);
}
package geometries.impl;

import primitives.Point;

/**
 * Class representing a triangle in 3D space. It inherits from the Polygon
 * class.
 */
public class Triangle extends Polygon {

	/**
	 * Constructor that takes 3 vertices to create a triangle. Calls the superclass
	 * Polygon constructor. * @param p1 first vertex
	 * 
	 * @param p2 second vertex
	 * @param p3 third vertex
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}
}
package geometries.impl;

import geometries.api.Geometry;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a plane in 3D space.
 */
public class Plane extends Geometry {

    /**
     * A reference point on the plane.
     */
    private final Point _point;

    /**
     * The normal vector to the plane.
     */
    private final Vector _normal;

    /**
     * Constructor that takes 3 points on the plane and calculates the normal.
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        _point = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        _normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructor that takes a point and a normal vector. The vector is normalized
     * upon creation.
     *
     * @param point  a reference point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        _point = point;
        _normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return _normal;
    }
}
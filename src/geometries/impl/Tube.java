package geometries.impl;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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

    @Override
    public Vector getNormal(Point point) {
        Point p0 = _axis.origin();
        Vector v = _axis.direction();

        double t = point.subtract(p0).dotProduct(v);
        Point o = isZero(t) ? p0 : p0.add(v.scale(t));

        return point.subtract(o).normalize();
    }

    @Override
    public String toString() {
        return "Tube: axis=" + _axis + ", radius=" + _radius;
    }
}
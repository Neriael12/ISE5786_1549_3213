package primitives;

/**
 * This class represents a ray in 3D space, defined by an origin point and a
 * direction vector. The direction vector is always normalized.
 */
public class Ray {
	/**
	 * The origin point of the ray.
	 */
	private final Point _origin;

	/**
	 * The direction vector of the ray (always normalized).
	 */
	private final Vector _direction;

	/**
	 * Constructor to initialize a ray with an origin point and a direction vector.
	 * The direction vector is normalized automatically. * @param origin the
	 * starting point of the ray
	 * 
	 * @param direction the direction of the ray
	 */
	public Ray(Point origin, Vector direction) {
		_origin = origin;
		_direction = direction.normalize();
	}

	/**
	 * Getter for the origin point of the ray. * @return the origin point
	 */
	public Point origin() {
		return _origin;
	}

	/**
	 * Getter for the direction vector of the ray. * @return the normalized
	 * direction vector
	 */
	public Vector direction() {
		return _direction;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Ray other)
			return this._origin.equals(other._origin) && this._direction.equals(other._direction);
		return false;
	}

	@Override
	public String toString() {
		return "Ray: origin=" + _origin + ", direction=" + _direction;
	}
}
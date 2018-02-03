package uk.co.computerdreams.madpong;

/**
 * Created by BRIAN on 03/02/2018.
 */

public class Vector2d
{
    // Members
    public double x;
    public double y;

    // Constructors
    public Vector2d()
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public static Vector2d Of(double x, double y)
    {
        return new Vector2d(x, y);
    }

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // Compare two vectors
    public boolean Equals(Vector2d other)
    {
        return (this.x == other.x && this.y == other.y);
    }

    public Vector2d Add(Vector2d other)
    {
        return Of(x + other.x, y + other.y);
    }

    public Vector2d Subtract(Vector2d other)
    {
        return Of(x - other.x, y - other.y);
    }

    public Vector2d Multiply(double scalar)
    {
        return Of(x * scalar, y * scalar);
    }

    public double DotProduct(Vector2d other)
    {
        return (other.x * x) + (other.y * y);
    }
    public Vector2d InvertSign()
    {
        return Of(-x, -y);
    }
}

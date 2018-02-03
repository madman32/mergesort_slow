package uk.co.computerdreams.madpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by BRIAN on 27/01/2018.
 */

public abstract class GameObject {
    GameObject(Bitmap sprite, PointF position, Paint paint) {
        m_sprite = sprite;
        m_position = position;
        m_paint = paint;
        m_speed = new Vector2d(0, 0);
    }

    GameObject(Bitmap sprite, PointF position, Vector2d speed, float mass) {
        m_sprite = sprite;
        m_position = position;
        m_paint = new Paint();
        m_speed = speed;
        m_mass = mass;
        if (mass == 0) {
            m_invMass = 0;
        } else {
            m_invMass = 1 / m_mass;
        }
    }

    /*
    * Ball rectangle intersection
    */
    public abstract boolean CheckBallCollision(Ball ball);

    static void Collide(GameObject obj1, GameObject obj2)
    {
        Vector2d normal = new Vector2d(obj1.m_position.x - obj2.m_position.x, obj1.m_position.y - obj2.m_position.y).Normalise();
        // Calculate relative velocity
        Vector2d rv = obj1.m_speed.Subtract(obj2.m_speed);

        // Calculate relative velocity in terms of the normal direction / DotProduct
        double velAlongNormal = rv.DotProduct(normal);

        // Do not resolve if velocities are separating
        if (velAlongNormal > 0)
            return;

        // Calculate restitution
        float e = Math.min(obj1.m_restitution, obj2.m_restitution);

        // Calculate impulse scalar
        double j = -(1 + e) * velAlongNormal;
        j /= (obj1.m_invMass + obj2.m_invMass);

        // Apply impulse
        Vector2d impulse = normal.Multiply(j);
        obj1.m_speed = obj1.m_speed.Subtract(impulse.Multiply(obj1.m_invMass));
        obj2.m_speed = obj2.m_speed.Add(impulse.Multiply(obj2.m_invMass));
    }

    public abstract void Update(ArrayList<GameObject> levelObjects);

    public abstract void Draw(Canvas canvas);

    protected Bitmap m_sprite;
    protected PointF m_position;
    protected Paint m_paint;
    protected Vector2d m_speed;

    protected float m_mass;
    protected float m_invMass;

    // the ratio of the relative velocity after impact to the relative velocity before the impact of two colliding bodies
    // 1 for elastic, 0 for inelastic, more than one for speed up?
    protected float m_restitution = 1;
}
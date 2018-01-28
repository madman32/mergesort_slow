package uk.co.computerdreams.madpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by BRIAN on 28/01/2018.
 */

public class Ball extends GameObject
{
    Ball(Bitmap sprite, PointF position, Point screenSize)
    {
        super(sprite, position);
        m_speed = new PointF(10,10);
        m_screenSize = screenSize;
        m_radius = m_sprite.getWidth() / 2;
    }

    @Override
    public void Update(ArrayList<GameObject> levelObjects)
    {
        CheckScreenBounds();
        CheckCollision(levelObjects);
        m_position.x += m_speed.x;
        m_position.y += m_speed.y;
    }

    private void CheckScreenBounds()
    {
        if (m_position.x + m_speed.x < 0)
        {
            m_speed.x = abs(m_speed.x);
        }
        if (m_position.x + m_speed.x > m_screenSize.x - m_sprite.getWidth())
        {
            m_speed.x = -abs(m_speed.x);
        }
        if (m_position.y + m_speed.y < 0)
        {
            m_speed.y = abs(m_speed.y);
        }
        if (m_position.y + m_speed.y > m_screenSize.y - m_sprite.getHeight())
        {
            m_speed.y = -abs(m_speed.y);
        }
    }

    private void CheckCollision(ArrayList<GameObject> levelObjects)
    {
        for (GameObject object : levelObjects)
        {
            if (object != this)
            {
                if (object.CheckBallCollision(m_position,
                                          m_radius,
                                          m_speed))
                {
                    break;
                }
            }
        }
    }

    @Override
    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(m_sprite, m_position.x, m_position.y, m_paint);
    }

    @Override
    public boolean CheckBallCollision(PointF position, float radius, PointF speed)
    {
        double distance = Math.sqrt(
                ((position.x - m_position.x) * (position.x - m_position.x)) +
                ((position.y - m_position.y) * (position.y - m_position.y)));
        // Two balls colliding
        if (distance < radius + m_radius)
        {
            //balls have collided
            // Collision point
            /*double collisionPointX =
            ((position.x * m_radius) + (m_position.x * radius))
                    / (radius + m_radius);

            double collisionPointY =
                    ((position.y * m_radius) + (m_position.y * radius))
                            / (radius + m_radius);*/

            float ball1Mass = radius / MASS_DIVISOR;
            float ball2Mass = m_radius  / MASS_DIVISOR;
            float newVelX1 = (speed.x * (ball1Mass - ball2Mass) + (2 * ball2Mass * m_speed.x)) / (ball1Mass + ball2Mass);
            float newVelY1 = (speed.y * (ball1Mass - ball2Mass) + (2 * ball2Mass * m_speed.y)) / (ball1Mass + ball2Mass);
            float newVelX2 = (m_speed.x * (ball2Mass - ball1Mass) + (2 * ball1Mass * speed.x)) / (ball1Mass + ball2Mass);
            float newVelY2 = (m_speed.y * (ball2Mass - ball1Mass) + (2 * ball1Mass * speed.y)) / (ball1Mass + ball2Mass);

            speed.x = newVelX1;
            speed.y = newVelY1;
            m_speed.x = newVelX2;
            m_speed.y = newVelY2;

            m_position.x += m_speed.x;
            m_position.y += m_speed.y;
            return true;
        }
        return false;
    }

    private float m_radius;
    private PointF m_speed;
    private Point m_screenSize;
    private float MASS_DIVISOR = 5;
}

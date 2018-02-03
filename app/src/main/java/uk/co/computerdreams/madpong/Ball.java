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
        super(sprite, position, new Vector2d(10,10), 5);
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
                if (object.CheckBallCollision(this))
                {
                    Collide(this, object);
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
    public boolean CheckBallCollision(Ball ball)
    {
        double distance = Math.sqrt(
                ((ball.m_position.x - m_position.x) * (ball.m_position.x - m_position.x)) +
                ((ball.m_position.y - m_position.y) * (ball.m_position.y - m_position.y)));
        // Two balls colliding
        if (distance < (ball.m_radius + m_radius))
        {
            return true;
        }
        return false;
    }

    public float m_radius;
    private Point m_screenSize;
}

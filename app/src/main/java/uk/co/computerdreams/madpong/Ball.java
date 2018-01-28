package uk.co.computerdreams.madpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by BRIAN on 28/01/2018.
 */

public class Ball extends GameObject
{
    public Ball(Bitmap sprite, PointF position, Point screenSize)
    {
        super(sprite, position);
        m_speed = new PointF(5,5);
        m_screenSize = screenSize;
    }

    @Override
    public void Update()
    {
        CheckScreenBounds();
        CheckCollision();
        m_position.x += m_speed.x;
        m_position.y += m_speed.y;
    }

    private void CheckScreenBounds()
    {
        if (m_position.x + m_speed.x < 0)
        {
            m_speed.x *= -1;
        }
        if (m_position.x + m_speed.x > m_screenSize.x - m_sprite.getWidth())
        {
            m_speed.x *= -1;
        }
        if (m_position.y + m_speed.y < 0)
        {
            m_speed.y *= -1;
        }
        if (m_position.y + m_speed.y > m_screenSize.y - m_sprite.getHeight())
        {
            m_speed.y *= -1;
        }
    }

    private void CheckCollision()
    {

    }

    @Override
    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(m_sprite, m_position.x, m_position.y, m_paint);
    }

    PointF m_speed;
    Point m_screenSize;
}

package uk.co.computerdreams.madpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by BRIAN on 28/01/2018.
 */

public class Player extends GameObject
{
    Player(Bitmap sprite, PointF position, Point screenSize)
    {
        super(sprite, position, new Vector2d(0,1), 0);
        m_screenSize = screenSize;
    }

    @Override
    public void Update(ArrayList<GameObject> levelObjects)
    {
        m_speed.x = 0;
        m_speed.y = 1;
    }

    @Override
    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(m_sprite, m_position.x, m_position.y, m_paint);
    }

    @Override
    public boolean CheckBallCollision(Ball ball)
    {
        float circleX = ball.m_position.x + (ball.m_sprite.getWidth() / 2);
        float circleY = ball.m_position.y + (ball.m_sprite.getHeight() / 2);
        float deltaX = circleX - Math.max(m_position.x, Math.min(circleX, m_position.x + m_sprite.getWidth()));
        float deltaY = circleY - Math.max(m_position.y, Math.min(circleY, m_position.y + m_sprite.getHeight()));
        return ((deltaX * deltaX) + (deltaY * deltaY)) < (ball.m_radius * ball.m_radius);
    }

    private Point m_screenSize;
    private float PLAYER_MASS = 0;
}

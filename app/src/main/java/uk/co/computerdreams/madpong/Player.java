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
        super(sprite, position);
        m_speed = new PointF(5,5);
        m_screenSize = screenSize;
    }

    @Override
    public void Update(ArrayList<GameObject> levelObjects)
    {

    }

    @Override
    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(m_sprite, m_position.x, m_position.y, m_paint);
    }

    @Override
    public boolean CheckBallCollision(Ball ball)
    {

        return false;
    }

    private PointF m_speed;
    private Point m_screenSize;
}

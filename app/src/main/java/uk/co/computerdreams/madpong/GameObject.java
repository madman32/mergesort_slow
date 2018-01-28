package uk.co.computerdreams.madpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by BRIAN on 27/01/2018.
 */

public abstract class GameObject
{
    GameObject(Bitmap sprite, PointF position, Paint paint)
    {
        m_sprite = sprite;
        m_position = position;
        m_paint = paint;
    }

    GameObject(Bitmap sprite, PointF position)
    {
        m_sprite = sprite;
        m_position = position;
        m_paint = new Paint();
    }

    public abstract void Update();
    public abstract void Draw(Canvas canvas);
    protected Bitmap m_sprite;
    protected PointF m_position;
    protected Paint m_paint;
}

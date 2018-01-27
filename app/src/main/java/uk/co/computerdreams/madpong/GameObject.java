package uk.co.computerdreams.madpong;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by BRIAN on 27/01/2018.
 */

public abstract class GameObject
{
    public abstract void Update();
    public abstract void Draw(Canvas canvas);

    protected Point m_position;
}

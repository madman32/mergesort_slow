package uk.co.computerdreams.madpong;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by BRIAN on 28/01/2018.
 */

public class LevelLoader
{
    private Context m_context;
    public LevelLoader(Context context)
    {
        m_context = context;
    }
    public ArrayList<GameObject> LoadLevel(int level, Point screenSize)
    {
        ArrayList<GameObject> levelObjects = new  ArrayList<>();
        LoadBalls(levelObjects, screenSize);
        LoadPlayers(levelObjects);
        LoadObjects(levelObjects);
        return levelObjects;
    }

    public void LoadBalls(ArrayList<GameObject> levelObjects, Point screenSize)
    {
        levelObjects.add(new Ball(BitmapFactory.decodeResource(m_context.getResources(), R.drawable.ball1), new PointF(screenSize.x /2,screenSize.y /2), screenSize));
    }

    public void LoadPlayers(ArrayList<GameObject> levelObjects)
    {

    }

    public void LoadObjects(ArrayList<GameObject> levelObjects)
    {

    }
}

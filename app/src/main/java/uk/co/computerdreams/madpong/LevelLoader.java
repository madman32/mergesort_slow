package uk.co.computerdreams.madpong;

import android.content.Context;
import android.graphics.Bitmap;
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
    LevelLoader(Context context)
    {
        m_context = context;
    }
    ArrayList<GameObject> LoadLevel(int level, Point screenSize)
    {
        ArrayList<GameObject> levelObjects = new  ArrayList<>();
        LoadBalls(levelObjects, screenSize);
        LoadPlayers(levelObjects, screenSize);
        LoadObjects(levelObjects);
        return levelObjects;
    }

    void LoadBalls(ArrayList<GameObject> levelObjects, Point screenSize)
    {
        levelObjects.add(new Ball(BitmapFactory.decodeResource(m_context.getResources(), R.drawable.ball1), new PointF(screenSize.x /2,screenSize.y /2), screenSize));
        levelObjects.add(new Ball(BitmapFactory.decodeResource(m_context.getResources(), R.drawable.ball1), new PointF(0,0), screenSize));
    }

    void LoadPlayers(ArrayList<GameObject> levelObjects, Point screenSize)
    {
        Bitmap sprite = BitmapFactory.decodeResource(m_context.getResources(), R.drawable.test_player);
        levelObjects.add(new Player(sprite, new PointF(screenSize.x /2, screenSize.y - sprite.getHeight()), screenSize));
    }

    void LoadObjects(ArrayList<GameObject> levelObjects)
    {

    }
}

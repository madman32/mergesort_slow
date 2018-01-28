package uk.co.computerdreams.madpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by BRIAN on 27/01/2018.
 */

public class GameEngine extends SurfaceView implements Runnable
{
    private Thread m_thread = null;

    // To hold a reference to the Activity
    private Context m_context;
    private Point m_screenSize;

    private final long MILLIS_PER_SECOND = 1000;
    private final long TARGET_UPDATE_RATE = 30;

    private volatile boolean isPlaying = false;

    // A canvas for our paint
    private Canvas m_canvas;

    // Required to use canvas
    private SurfaceHolder m_surfaceHolder;

    // Some paint for our canvas
    private Paint m_paint;

    // for plaing sound effects
    private SoundPool m_soundPool;

    private ArrayList<GameObject> m_gameObjects;
    private long m_nextFrameTime;

    GameEngine(Context context, Point size)
    {
        super(context);
        m_context = context;
        m_screenSize = size;

        // create sound manager
        m_soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        LoadSounds();

        m_surfaceHolder = getHolder();
        m_paint = new Paint();

        m_gameObjects = new LevelLoader(context).LoadLevel(0, size);

        // Possibly should be located in newGame (would need a restart method)
        isPlaying = true;
        m_thread = new Thread(this);
        m_thread.start();

        NewGame();
    }

    public void NewGame()
    {
        m_nextFrameTime = System.currentTimeMillis();
    }

    public void run()
    {
        while (isPlaying)
        {
            // Update 10 times a second
            if(UpdateRequired())
            {
                Update();
                Draw();
            }
        }
    }

    public void Pause()
    {
        isPlaying = false;
        try
        {
            m_thread.join();
        }
        catch (InterruptedException e)
        {
            // Error
        }
    }

    public void Resume()
    {
        isPlaying = true;
        m_thread = new Thread(this);
        m_thread.start();
    }

    private void LoadSounds()
    {

    }

    private void Draw()
    {
        if (m_surfaceHolder.getSurface().isValid())
        {
            m_canvas = m_surfaceHolder.lockCanvas();

            m_canvas.drawColor(Color.argb(255, 0, 0, 0));

            for (GameObject object : m_gameObjects)
            {
                object.Draw(m_canvas);
            }

            m_surfaceHolder.unlockCanvasAndPost(m_canvas);
        }
    }

    private void Update()
    {
        for (GameObject object : m_gameObjects)
        {
            object.Update();
        }
    }

    private boolean UpdateRequired()
    {
        // Are we due to update the frame
        if (m_nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            m_nextFrameTime = System.currentTimeMillis() + MILLIS_PER_SECOND / TARGET_UPDATE_RATE;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }
}

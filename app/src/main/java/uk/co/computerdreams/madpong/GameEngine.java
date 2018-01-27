package uk.co.computerdreams.madpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by BRIAN on 27/01/2018.
 */

public class GameEngine extends SurfaceView implements Runnable
{
    private Thread thread = null;

    // To hold a reference to the Activity
    private Context m_context;
    private Point m_screenSize;

    private final long TARGET_UPDATE_RATE = 45;

    private volatile boolean isPlaying;

    // A canvas for our paint
    private Canvas m_canvas;

    // Required to use canvas
    private SurfaceHolder m_surfaceHolder;

    // Some paint for our canvas
    private Paint m_paint;

    // for plaing sound effects
    private SoundPool m_soundPool;

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
            thread.join();
        }
        catch (InterruptedException e)
        {
            // Error
        }
    }

    public void Resume()
    {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    private void LoadSounds()
    {

    }

    private void Draw()
    {
        if (m_surfaceHolder.getSurface().isValid())
        {
            m_canvas = m_surfaceHolder.lockCanvas();

            m_surfaceHolder.unlockCanvasAndPost(m_canvas);
        }
    }

    private void Update()
    {

    }

    private boolean UpdateRequired()
    {
        return true;
    }
}

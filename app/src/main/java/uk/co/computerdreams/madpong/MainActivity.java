package uk.co.computerdreams.madpong;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends Activity {

    GameEngine engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        engine = new GameEngine(this, size);
        setContentView(R.layout.activity_main);
    }

    // Start the thread in snakeEngine
    @Override
    protected void onResume() {
        super.onResume();
        engine.Resume();
    }

    // Stop the thread in snakeEngine
    @Override
    protected void onPause() {
        super.onPause();
        engine.Pause();
    }
}

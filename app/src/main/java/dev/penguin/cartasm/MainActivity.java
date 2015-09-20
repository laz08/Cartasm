package dev.penguin.cartasm;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Main activity.
 */
public class MainActivity extends Activity {

    private RelativeLayout mLayout;

    private BackgroundTimer mTimer;
    private MediaPlayer mPlayer;
    private int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initializeView();
        initializeTimer();
        initializePlayer();

    }

    /**
     * Resumes player.
     */
    private void resumePlayer() {

        mPlayer.seekTo(mPosition);
        mPlayer.start();
    }

    /**
     * Initializes player.
     */
    private void initializePlayer() {

        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.rock_over_japan_8bit);
        mPlayer.setLooping(true);

        mPlayer.start();
    }

    /**
     * Initializes view.
     */
    private void initializeView() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        mLayout = (RelativeLayout) findViewById(R.id.root_view);
    }

    @Override
    protected void onResume() {

        super.onResume();
        mTimer.start();
        resumePlayer();
    }

    @Override
    protected void onPause() {

        super.onPause();
        mPosition = mPlayer.getCurrentPosition();
        mTimer.cancel();

        mPlayer.pause();
    }

    /**
     * Initializes timer.
     */
    private void initializeTimer() {

        mTimer = new BackgroundTimer(100, 50, mLayout);
        mTimer.start();
    }

}

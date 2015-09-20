package dev.penguin.cartasm;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Main activity.
 */
public class MainActivity extends Activity {

    private RelativeLayout mLayout;
    private TextView mIsPlayingText;

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
        mIsPlayingText.setText(getResources().getString(R.string.on));
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

        mIsPlayingText = (TextView) findViewById(R.id.on_off);
        mLayout = (RelativeLayout) findViewById(R.id.root_view);
        mLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                toggleMusicPlaying();
            }
        });
    }

    /**
     * Toggles music playing.
     */
    private void toggleMusicPlaying() {

        if (mPlayer.isPlaying()) {

            pausePlayer();
        } else {

           resumePlayer();

        }
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
        mTimer.cancel();

        pausePlayer();
    }

    /**
     * Pauses player.
     */
    private void pausePlayer() {

        mPosition = mPlayer.getCurrentPosition();
        mPlayer.pause();

        mIsPlayingText.setText(getResources().getString(R.string.off));
    }

    /**
     * Initializes timer.
     */
    private void initializeTimer() {

        mTimer = new BackgroundTimer(100, 50, mLayout);
        mTimer.start();
    }

}

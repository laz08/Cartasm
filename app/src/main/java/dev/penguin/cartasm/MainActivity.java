package dev.penguin.cartasm;

import android.app.Activity;
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
    private boolean mIsPlaying;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initializeView();
        initializeTimer();
        initializePlayer();
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
     * Initializes timer.
     */
    private void initializeTimer() {

        mTimer = new BackgroundTimer(100, 50, mLayout);
        mTimer.start();
    }

    /**
     * Initializes player.
     */
    private void initializePlayer() {

        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.rock_over_japan_8bit);
        mPlayer.setLooping(true);

        mPlayer.start();
        mIsPlaying = true;
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
        mIsPlaying = !mIsPlaying;
        toggleTextView();
    }

    /**
     * Toggles text to be shown.
     */
    private void toggleTextView() {

        if (mIsPlaying) {

            mIsPlayingText.setText(getResources().getString(R.string.on));
        } else {

            mIsPlayingText.setText(getResources().getString(R.string.off));
        }
    }


    /**
     * Resumes player.
     */
    private void resumePlayer() {

        mPlayer.seekTo(mPosition);
        mPlayer.start();
    }

    /**
     * Pauses player.
     */
    private void pausePlayer() {

        mPosition = mPlayer.getCurrentPosition();
        mPlayer.pause();
    }


    @Override
    protected void onResume() {

        super.onResume();
        mTimer.start();
        toggleTextView();
        if (mIsPlaying) {

            resumePlayer();
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        mTimer.cancel();
        mIsPlaying = mPlayer.isPlaying();
        pausePlayer();
    }


}

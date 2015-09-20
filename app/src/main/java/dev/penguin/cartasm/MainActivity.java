package dev.penguin.cartasm;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Main activity.
 */
public class MainActivity extends Activity {

    private RelativeLayout mLayout;
    private BackgroundTimer mTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initializeView();
        initializeTimer();
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        mTimer.cancel();
        onDestroy();
    }

    /**
     * Initializes timer.
     */
    private void initializeTimer() {

        mTimer = new BackgroundTimer(100, 50, mLayout);
        mTimer.start();
    }
}

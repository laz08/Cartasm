package dev.penguin.cartasm;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Background timer.
 */
public class BackgroundTimer extends CountDownTimer {

    private int mCounter;
    private int[] mColors;
    private View mRootView;

    public BackgroundTimer(long millisInFuture, long countDownInterval, View view) {

        super(millisInFuture, countDownInterval);
        mColors = view.getResources().getIntArray(R.array.rainbow);
        mRootView = view;
    }

    @Override
    public void onFinish() {

        mRootView.setBackgroundColor(mColors[mCounter]);
        ++mCounter;

        if(mCounter > 11){

            mCounter = mCounter % 12;
        }

        start();
    }

    @Override
    public void onTick(long millisUntilFinished) {

        //do Nothing
    }
}

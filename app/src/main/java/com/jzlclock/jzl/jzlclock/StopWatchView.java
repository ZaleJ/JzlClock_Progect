package com.jzlclock.jzl.jzlclock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class StopWatchView extends LinearLayout{

    public StopWatchView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private TextView tvHour, tvMin, tvSec, tvMSec;
    private Button btnStart, btnPause, btnResume, btnLap, btnReset;

}

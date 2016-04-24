package com.jzlclock.jzl.jzlclock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Timer;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
public class TimerView extends LinearLayout{

    public TimerView(Context context, AttributeSet attrs){     // xml使用构造方法
        super(context, attrs);
    }

    public TimerView(Context context){     // 程序使用构造方法
        super(context);
    }
}

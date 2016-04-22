package com.jzlclock.jzl.jzlclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("The Clock has worked<<<<<<>>>>>>>>>>>>>");
    }
}

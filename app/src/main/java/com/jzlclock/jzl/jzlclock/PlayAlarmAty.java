package com.jzlclock.jzl.jzlclock;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/4/23 0023.
 */
public class PlayAlarmAty extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_player_aty);

        mp = MediaPlayer.create(this, R.raw.adjn);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mp.stop();
        mp.release();
    }

    private MediaPlayer mp;

}

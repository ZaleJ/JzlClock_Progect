package com.jzlclock.jzl.jzlclock;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import java.util.logging.LogRecord;

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

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                stopTimer();

                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });
        btnResume = (Button) findViewById(R.id.btnResume);
        btnResume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startTimer();

                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stopTimer();
                etHour.setText("0");
                etMin.setText("0");
                etSec.setText("0");

                btnStart.setVisibility(View.VISIBLE);     // 可见
                btnPause.setVisibility(View.GONE);
                btnReset.setVisibility(View.GONE);
                btnResume.setVisibility(View.GONE);
            }
        });
        etHour = (EditText) findViewById(R.id.etHour);
        etMin = (EditText) findViewById(R.id.etMin);
        etSec = (EditText) findViewById(R.id.etSec);


        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                 startTimer();

                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                //btnResume.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }
        });

        etHour.setText("00");                                    // 初始化文本
        etHour.addTextChangedListener(new TextWatcher() {        // 初始化事件
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(s)) {

                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etHour.setText("59");
                    } else if (value < 0) {
                        etHour.setText("0");
                    }

                }
                checkToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        etMin.setText("00");
        etMin.addTextChangedListener(new TextWatcher() {        // 初始化事件
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etMin.setText("59");
                    } else if (value < 0) {
                        etMin.setText("0");
                    }

                }
                checkToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        etSec.setText("00");
        etSec.addTextChangedListener(new TextWatcher() {        // 初始化事件
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etSec.setText("59");
                    } else if (value < 0) {
                        etSec.setText("0");
                    }

                }
                checkToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnStart.setVisibility(View.VISIBLE);     // 可见
        btnStart.setEnabled(false);               // 不可点（需设置文本数据）
        btnPause.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
    }

    private void checkToEnableBtnStart(){
        btnStart.setEnabled((!TextUtils.isEmpty(etHour.getText())&&Integer.parseInt(etHour.getText().toString()) > 0) ||
                (!TextUtils.isEmpty(etMin.getText())&&Integer.parseInt(etMin.getText().toString()) > 0) ||
                ((!TextUtils.isEmpty(etSec.getText()))&&Integer.parseInt(etSec.getText().toString()) > 0));
    }


    private void startTimer(){
        if (timerTask == null){
            allTimerCount =   Integer.parseInt(etHour.getText().toString()) * 60 * 60 +
                                Integer.parseInt(etMin.getText().toString()) * 60 +
                                Integer.parseInt(etSec.getText().toString());       // 计算总时间
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    allTimerCount--;

                    handler.sendEmptyMessage(MSG_WHAT_TIME_IS_TICK);


                    if (allTimerCount<=0){
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                        startTimer();
                    }
                }
            };

            timer.schedule(timerTask, 1000, 1000);     //延迟一秒执行

        }
    }

    private void stopTimer(){
        if (timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case MSG_WHAT_TIME_IS_TICK:

                    int hour = allTimerCount/60/60;
                    int min = (allTimerCount/60)%60;
                    int sec = allTimerCount%60;

                    etHour.setText(hour+"");
                    etMin.setText(min+"");
                    etSec.setText(sec+"");
                    break;
                case MSG_WHAT_TIME_IS_UP:
                    if (TIME_IS_UP_BUG_FIXER == 0 || TIME_IS_UP_BUG_FIXER > 1) {
                        TIME_IS_UP_BUG_FIXER = 1;
                        new AlertDialog.Builder(getContext()).setTitle("Time is up").setMessage("Time is up").setNegativeButton("Cancel", null).show();

                        btnPause.setVisibility(View.GONE);
                        btnResume.setVisibility(View.GONE);
                        btnReset.setVisibility(View.GONE);
                        btnStart.setVisibility(View.VISIBLE);

                        break;
                    }else{TIME_IS_UP_BUG_FIXER++;stopTimer();break;}
                default:
                    break;
            }

        };
    };

    private static final int MSG_WHAT_TIME_IS_UP = 1;
    private static final int MSG_WHAT_TIME_IS_TICK = 2;
    private int TIME_IS_UP_BUG_FIXER = 0;
    private int allTimerCount = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private Button btnStart, btnPause, btnResume, btnReset;
    private EditText etHour, etMin, etSec;
}

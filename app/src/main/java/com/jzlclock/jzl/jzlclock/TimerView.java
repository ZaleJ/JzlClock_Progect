package com.jzlclock.jzl.jzlclock;

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


        btnPause = (Button) findViewById(R.id.btnPause);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnReset = (Button) findViewById(R.id.btnReset);
        etHour = (EditText) findViewById(R.id.etHour);
        etMin = (EditText) findViewById(R.id.etMin);
        etSec = (EditText) findViewById(R.id.etSec);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // startTimer();
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

    /* TODO
    private void startTimer(){
        if (timerTask == null){
            timerTask = new TimerTask() {
                @Override
                public void run() {

                }
            }
        }
    }
    */

    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private Button btnStart, btnPause, btnResume, btnReset;
    private EditText etHour, etMin, etSec;
}

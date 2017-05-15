package com.example.tung.lesson5_thread_asyntask_handler;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tung.lesson5_thread_asyntask_handler.asynctask.MyAsyncTask;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Handler handler;
    AtomicBoolean isRunning = new AtomicBoolean(false);

    ProgressBar progressBar;
    TextView tvPercent;
    Button btnStartThread;
    Button btnStartAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setHandler();
    }

    private void setHandler(){
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressBar.setProgress(msg.arg1);
                tvPercent.setText(msg.arg1 + "%");
            }
        };
    }

    private void startThread(){
        progressBar.setProgress(0);
        isRunning.set(false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100 && isRunning.get(); i++) {
                    // tam dung 50 ms
                    SystemClock.sleep(50);
                    // lay message tu main thread
                    Message msg = handler.obtainMessage();
                    // luu gia tri percent vao message
                    msg.arg1 = i;
                    // gui message lai cho main thread
                    handler.sendMessage(msg);
                }
            }
        });
        isRunning.set(true);
        t1.start();
    }

    private void startAsyncTask(){
        MyAsyncTask asyncTask = new MyAsyncTask(this);
        asyncTask.execute();
    }

    private void initWidget(){
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
        btnStartThread = (Button) findViewById(R.id.btn_start_thread);
        btnStartAsyncTask = (Button) findViewById(R.id.btn_start_asynctask);
        btnStartThread.setOnClickListener(this);
        btnStartAsyncTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_thread:
                startThread();
                break;
            case R.id.btn_start_asynctask:
                startAsyncTask();
        }
    }
}

package com.example.tung.lesson7_service_broadcast_receiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tung.lesson7_service_broadcast_receiver.service.MediaBoundService;
import com.example.tung.lesson7_service_broadcast_receiver.service.MediaUnboundService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String LOG_TAG = "--MainThread--";
    private Button btnStartService, btnStopService, btnBindService, btnUnbinService, btnPrevious, btnNext;
    private TextView tvStatus, tvStatus2;

    MediaBoundService mediaBoundService;
    boolean isBinded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "in onCreate" + isBinded);
        setContentView(R.layout.activity_main);
        initWidget();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaBoundService.MediaBinder mediaBinder = (MediaBoundService.MediaBinder) service;
            mediaBoundService = mediaBinder.getService();
            isBinded = true;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_start_service:
                // chay MediaUnboundService
                tvStatus.setText("Started");
                startService(new Intent(this, MediaUnboundService.class));
                break;

            case R.id.btn_stop_service:
                // dung MediaUnboundService
                tvStatus.setText("Stopted");
                stopService(new Intent(this, MediaUnboundService.class));
                break;

            case R.id.btn_bind_service:
                // tao rang buoc MediaBoundService vao UI neu chua co
                if (!isBinded) {
                    Intent intent = new Intent(this, MediaBoundService.class);
                    bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                    isBinded = true;
                    tvStatus2.setText("Binded");
                }
                break;

            case R.id.btn_unbin_service:
                // Huy rang buoc MediaBoundService neu dang co
                if (isBinded) {
                    unbindService(serviceConnection);
                    isBinded = false;
                    tvStatus2.setText("Unbinded");
                }
                break;

            case R.id.btn_previous:
                //  tua lui 2 giay
                if (isBinded) mediaBoundService.previous(2);
                break;

            case R.id.btn_next:
                // tua toi 2 giay
                if (isBinded) mediaBoundService.next(2);
        }
    }

    public MainActivity() {
        super();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBinded) {
            unbindService(serviceConnection);
            isBinded = false;
            tvStatus2.setText("Unbinded");
        }
        Log.v(LOG_TAG, "in onStop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.page2:
                startActivity(new Intent(this, IntentServiceActivity.class));
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initWidget() {

        btnStartService = (Button) findViewById(R.id.btn_start_service);
        btnStopService = (Button) findViewById(R.id.btn_stop_service);
        btnBindService = (Button) findViewById(R.id.btn_bind_service);
        btnUnbinService = (Button) findViewById(R.id.btn_unbin_service);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        btnNext = (Button) findViewById(R.id.btn_next);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        tvStatus2 = (TextView) findViewById(R.id.tv_status2);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbinService.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }
}

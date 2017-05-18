package com.example.tung.lesson7_service_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tung.lesson7_service_broadcast_receiver.service.MyIntentService;

public class IntentServiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnStop;
    ProgressBar progressBar;
    TextView tvPercent;

    Intent serviceIntent;

    ResponseReceiver receiver = new ResponseReceiver();

    // tao class thu tin hieu tu service
    public class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MyIntentService.ACTION_1)) {
                int percent = intent.getIntExtra("percent", -1);
                new SetProgressBarTask().execute(percent);
            }
        }
    }

    private class SetProgressBarTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... args) {

            return args[0];
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressBar.setProgress(result);
            tvPercent.setText(result + "%");
            if (result == 100) {
                tvPercent.setText("Completed");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service);
        initWidget();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(MyIntentService.ACTION_1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                serviceIntent = new Intent(this, MyIntentService.class);
                startService(serviceIntent);
                break;
            case R.id.btn_stop:
                if (serviceIntent != null) stopService(serviceIntent);
        }
    }

    private void initWidget() {
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        tvPercent = (TextView) findViewById(R.id.tv_percent);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
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
        return super.onOptionsItemSelected(item);
    }
}

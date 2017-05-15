package com.example.tung.lesson5_thread_asyntask_handler.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tung.lesson5_thread_asyntask_handler.R;

/**
 * Created by tung on 5/15/17.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    // AsyncTask<Params, Progress, Result>
    // Params se truyen vao ham doInBackground()
    // Progress se truyen vao ham onProgressUpdate()
    // Result se truyen vao ham onPostExecute()

    private Activity mActivity;

    public MyAsyncTask(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mActivity, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i <= 100; i++) {
            // tam nghi 100 ms
            SystemClock.sleep(50);
            // goi tin hieu de chay ham onProgressUpdate()
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // vi progress truyen vao 1 doi so (kieu Integer) nen mang values chi co 1 phan tu
        int percent = values[0];
        // set gia tri phan tram cho progressbar
        ((ProgressBar) mActivity.findViewById(R.id.progress_bar)).setProgress(percent);
        // set gia tri phan tram cho TextView
        ((TextView) mActivity.findViewById(R.id.tv_percent)).setText(percent + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mActivity, "Finish Task", Toast.LENGTH_SHORT).show();
    }

}

package com.example.tung.lesson7_service_broadcast_receiver.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by tung on 5/17/17.
 */

public class MyIntentService extends IntentService {

    public static final String ACTION_1 ="SET_PROGRESSBAR";

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent broadcastIntent = new Intent();

        broadcastIntent.setAction(MyIntentService.ACTION_1);

        for (int i = 0; i <= 100; i++) {

            broadcastIntent.putExtra("percent", i);

            sendBroadcast(broadcastIntent);

            SystemClock.sleep(100);
        }

    }
}

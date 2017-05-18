package com.example.tung.lesson7_service_broadcast_receiver.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tung.lesson7_service_broadcast_receiver.R;

/**
 * Created by tung on 5/17/17.
 */

// Unbound service, se duoc goi bang ham startService()
public class MediaUnboundService extends Service{

    private static String LOG_TAG = "--UnboundService--";
    MediaPlayer mediaPlayer;

    public MediaUnboundService(){}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        // Tao doi tuong mediaplay de phat nhac
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shape_of_you);
    }

    // Service nay khong rang buoc nen ham nay khong chay
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(LOG_TAG, "in onStartCommand");
        // phat nhac
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(LOG_TAG, "in onDestroy");
        // truoc khi huy service thi cung giai phong doi tuong mediaplay
        mediaPlayer.release();
        super.onDestroy();
    }

}

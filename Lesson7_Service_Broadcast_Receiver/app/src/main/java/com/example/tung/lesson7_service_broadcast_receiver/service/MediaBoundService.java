package com.example.tung.lesson7_service_broadcast_receiver.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tung.lesson7_service_broadcast_receiver.R;


/**
 * Created by tung on 5/17/17.
 */

// Bound service, se duoc goi bang ham bindService()
public class MediaBoundService extends Service {

    private static String LOG_TAG = "--BoundService--";
    private IBinder iBinder = new MediaBinder();

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.we_dont_talk_anymore);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        mediaPlayer.start();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        mediaPlayer.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        mediaPlayer.release();

    }

    public void previous(int seconds){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-1000*seconds);
    }

    public void next(int seconds){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+1000*seconds);
    }

    public class MediaBinder extends Binder{
        public MediaBoundService getService(){
            return MediaBoundService.this;
        }
    }
}

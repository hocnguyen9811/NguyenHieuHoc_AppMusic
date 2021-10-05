package com.example.appmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicPlayerService extends Service {

    private MediaPlayer mPlayer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.cuoithoi);
    }


    public boolean play(){
        mPlayer.start();
        return mPlayer.isPlaying();
    }

    public boolean pause(){
        mPlayer.pause();
        return !mPlayer.isPlaying();
    }

    public class MyServiceBinder extends Binder {
        public MusicPlayerService getService(){
            return MusicPlayerService.this;
        };
    }
}

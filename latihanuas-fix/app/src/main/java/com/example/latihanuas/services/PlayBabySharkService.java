package com.example.latihanuas.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.latihanuas.R;

public class PlayBabySharkService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mediaPlayer == null){

            mediaPlayer = MediaPlayer.create(this, R.raw.mybaby_shark);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

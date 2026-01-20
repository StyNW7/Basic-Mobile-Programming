package com.example.musicinfoplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    MediaPlayer mp;

    public int onStartCommand(Intent i, int f, int id) {

        String url = i.getStringExtra("preview");

        try {
            if (mp != null) {
                mp.stop();
                mp.release();
            }

            mp = new MediaPlayer();
            mp.setDataSource(url);
            mp.prepare();
            mp.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    public void onDestroy() {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
    }

    public IBinder onBind(Intent i) { return null; }
}

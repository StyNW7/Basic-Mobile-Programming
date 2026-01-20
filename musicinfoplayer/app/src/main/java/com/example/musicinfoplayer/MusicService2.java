package com.example.musicinfoplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService2 extends Service {
    public MusicService2() {
    }

    MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String url = intent.getStringExtra("preview");

        try {

            if (mp != null){
                mp.stop();
                mp.release();
            }

            mp = new MediaPlayer();
            mp.setDataSource(url);
            mp.prepare();
            mp.start();

        }

        catch (Exception e){
            e.printStackTrace();
        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
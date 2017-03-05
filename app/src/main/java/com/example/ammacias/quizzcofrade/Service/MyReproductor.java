package com.example.ammacias.quizzcofrade.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

public class MyReproductor extends Service {
    MediaPlayer reproductorMusica;
    public MyReproductor() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String cancion = intent.getStringExtra("cancion");
        reproductorMusica = MediaPlayer.create(this, Uri.parse(cancion));

       /* try {
            reproductorMusica.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if (reproductorMusica.isPlaying()){
            reproductorMusica.stop();
        }
        reproductorMusica.start();

        return START_REDELIVER_INTENT;
    }

    public void stopAudio(){
        if (reproductorMusica.isPlaying()){
            reproductorMusica.stop();
        }
        if (reproductorMusica != null) {
            reproductorMusica.stop();
            reproductorMusica.release();
            reproductorMusica = null;
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (reproductorMusica.isPlaying()){
            reproductorMusica.stop();
        }
    }
}

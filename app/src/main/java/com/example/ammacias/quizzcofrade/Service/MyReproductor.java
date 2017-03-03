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
        reproductorMusica = MediaPlayer.create(this, Uri.parse("http://juegomarcas.esy.es/SS/music/"+cancion));

       /* try {
            reproductorMusica.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        reproductorMusica.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        reproductorMusica.stop();
    }
}

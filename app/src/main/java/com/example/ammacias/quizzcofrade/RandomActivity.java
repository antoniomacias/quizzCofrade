package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RandomActivity extends AppCompatActivity {

    List<HermandadDB> listaH;
    List<PasosDB> listaP;

    ImageView imageView;
    EditText respuesta;

    String nombreRespuesta;

    //Control aciertos/vidas
    int var_global = 16;
    int numVidas, numAciertos;
    boolean bandera;
    ImageView vida1;
    ImageView vida2;
    ImageView vida3;
    ImageView vida4;
    ImageView vida5;

    com.bcgdv.asia.lib.ticktock.TickTockView mCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        numVidas = 5;
        numAciertos = 0;
        vida1= (ImageView)findViewById(R.id.vida1);
        vida2= (ImageView)findViewById(R.id.vida2);
        vida3= (ImageView)findViewById(R.id.vida3);
        vida4= (ImageView)findViewById(R.id.vida4);
        vida5= (ImageView)findViewById(R.id.vida5);



        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        listaH = hermandadDBDao.loadAll();

        PasosDBDao PasosDBDao = DatabaseConnection.getPasosDBDao(this);
        listaP = PasosDBDao.loadAll();

        //Check si acierta
        respuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (respuesta.getText().toString().equalsIgnoreCase(nombreRespuesta)){
                    muestradialogo();
                    //TODO: Inserto en la tabla intermedia
                    System.out.println("Acertaste y guardo");
/*                  marcaDB.setAcertado(true);
                    marcaDBDao.update(marcaDB);*/

                }
            }
        });


        mCountDown = (TickTockView) findViewById(R.id.view_ticktock_countdown);
        if (mCountDown != null) {
            mCountDown.setOnTickListener(new TickTockView.OnTickListener() {
                @Override
                public String getText(long timeRemaining) {
                    int seconds = (int) (timeRemaining / 1000) % 60;
                    if (timeRemaining==0) next_escudo(findViewById(R.id.activity_detalle));
                    return String.format("%1$02d",
                            seconds,
                            "s");
                }
            });
        }


        imageView =(ImageView)findViewById(R.id.fotoDetalle);

        //Si es 1 -> Hermandad
        if ((int) (Math.random() * 1)== 1 ){
            Picasso.with(this)
                    .load(listaH.get(0).getEscudo())
                    .resize(250, 200)
                    .into(imageView);
        }else{
            Picasso.with(this)
                    .load(listaP.get(0).getFoto())
                    .resize(250, 200)
                    .into(imageView);
        }
    }

    private void muestradialogo() {
        //guardarAcierto(hermandadDBDao.load(id_aux).getId());
        mCountDown.stop();
        numAciertos++;
        bandera = true;
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡FLAMA HERMANO!")
                .setContentText("¿Quieres pasar al siguiente nivel?")
                .setConfirmText("Pasar al siguiente nivel")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        next_escudo(findViewById(R.id.activity_detalle));
                    }
                })
                .show();
    }

    public void next_escudo(View view) {
        //Reseteanis el campo
        respuesta.setText("");

        if (bandera == true);
        else numVidas--;

        if (numVidas==0){
            finJuego();
        }
        else {
            cambiarImg();

            //Toast.makeText(this, "B: "+bandera, Toast.LENGTH_SHORT).show();
            //Si es 0 -> Hermandad
            //Si es 1 -> Paso
            //Si es 2 -> Tunica
            int aux = (int) (Math.random() * 3);
            if (aux == 0) {
                int randomInt = (int) (Math.random() * listaH.size());
                Picasso.with(this)
                        .load(listaH.get(randomInt).getEscudo())
                        .resize(250, 200)
                        .into(imageView);
                nombreRespuesta = listaH.get(randomInt).getNombre();
                System.out.println(nombreRespuesta);
            } else if (aux == 1) {
                int randomInt = (int) (Math.random() * listaP.size());
                Picasso.with(this)
                        .load(listaP.get(randomInt).getFoto())
                        .resize(250, 200)
                        .into(imageView);
                nombreRespuesta = listaP.get(randomInt).getNombreTitular();
                System.out.println(nombreRespuesta);
            } else {
                Picasso.with(this)
                        .load("http://juegomarcas.esy.es/SS/images/ncage.jpg")
                        .resize(250, 200)
                        .into(imageView);
                nombreRespuesta = listaH.get(0).getNombre();
                System.out.println(nombreRespuesta);
            }
            bandera = false;
            cuentaAtras();
        }
    }

    private void cambiarImg() {
        if (numVidas==4){
            vida1.setImageResource(R.drawable.star);
            vida2.setImageResource(R.drawable.star);
            vida3.setImageResource(R.drawable.star);
            vida4.setImageResource(R.drawable.star);
            vida5.setImageResource(R.drawable.emptystar);
        }else if(numVidas==3){
            vida1.setImageResource(R.drawable.star);
            vida2.setImageResource(R.drawable.star);
            vida3.setImageResource(R.drawable.star);
            vida4.setImageResource(R.drawable.emptystar);
            vida5.setImageResource(R.drawable.emptystar);
        }else if(numVidas==2){
            vida1.setImageResource(R.drawable.star);
            vida2.setImageResource(R.drawable.star);
            vida3.setImageResource(R.drawable.emptystar);
            vida4.setImageResource(R.drawable.emptystar);
            vida5.setImageResource(R.drawable.emptystar);
        }else if(numVidas==1) {
            vida1.setImageResource(R.drawable.star);
            vida2.setImageResource(R.drawable.emptystar);
            vida3.setImageResource(R.drawable.emptystar);
            vida4.setImageResource(R.drawable.emptystar);
            vida5.setImageResource(R.drawable.emptystar);
        }else if(numVidas==0) {
            vida1.setImageResource(R.drawable.emptystar);
            vida2.setImageResource(R.drawable.emptystar);
            vida3.setImageResource(R.drawable.emptystar);
            vida4.setImageResource(R.drawable.emptystar);
            vida5.setImageResource(R.drawable.emptystar);
        }
    }

    private void finJuego() {
        // Poner el reloj a 0
        mCountDown.stop();
        cambiarImg();
        //TODO: Cambiar img SweetAlertDialog
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("¡SE HAN ACABADO LAS VIDAS!")
                .setConfirmText("Volver a jugar")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        redirect(findViewById(R.id.activity_detalle));
                    }
                })
                .show();

        //TODO: Insertar los datos


    }

    private void redirect(View viewById) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    void cuentaAtras(){
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, 0);
        end.add(Calendar.SECOND, 16);

        Calendar start = Calendar.getInstance();
        start.add(Calendar.SECOND, -1);
        if (mCountDown != null) {
            mCountDown.start(start, end);
        }
    }

    //https://github.com/CardinalNow/Android-CountdownTimer
    //https://github.com/BCGDV-ASIA/android-widget-ticktock


    @Override
    protected void onStart() {
        super.onStart();
        cuentaAtras();
    }

}
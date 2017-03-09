package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos.DynamicFragmentFotos;
import com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos.DynamicFragmentMarcha;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RandomActivity extends AppCompatActivity {

    List<HermandadDB> listaH;
    List<PasosDB> listaP;
    List<MarchaDB> listaM;

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
    TextView aciertosImg;

    //CountDown
    com.bcgdv.asia.lib.ticktock.TickTockView mCountDown;

    //Control repeticiones
    List<String> listAux = new ArrayList<>();

    //Fragment
    Fragment f;
    Long arg;           //Posicion
    String arg1;        //Categoria

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        vida1= (ImageView)findViewById(R.id.vida1);
        vida2= (ImageView)findViewById(R.id.vida2);
        vida3= (ImageView)findViewById(R.id.vida3);
        vida4= (ImageView)findViewById(R.id.vida4);
        vida5= (ImageView)findViewById(R.id.vida5);
        aciertosImg= (TextView) findViewById(R.id.numAciertos);


        numVidas = 5;
        numAciertos = 0;
        aciertosImg.setText("0");


        respuesta =(EditText)findViewById(R.id.respuesta_escudo);

        HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        listaH = hermandadDBDao.loadAll();

        PasosDBDao PasosDBDao = DatabaseConnection.getPasosDBDao(this);
        listaP = PasosDBDao.loadAll();

        MarchaDBDao marchaDBDao= DatabaseConnection.getMarchasDBDao(this);
        listaM = marchaDBDao.loadAll();


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



        //TODO: ¿Esto qué es? No se carga nada al iniciar el activity
        //imageView =(ImageView)findViewById(R.id.fotoDetalle);

        //Si es 1 -> Hermandad
        /*if ((int) (Math.random() * 1)== 1 ){
            Picasso.with(this)
                    .load(listaH.get(0).getEscudo())
                    .resize(250, 200)
                    .into(imageView);
        }else{
            Picasso.with(this)
                    .load(listaP.get(0).getFoto())
                    .resize(250, 200)
                    .into(imageView);
        }*/
    }

    private void muestradialogo() {
        //guardarAcierto(hermandadDBDao.load(id_aux).getId());
        //Paramos contador
        mCountDown.stop();

        //Aumentamos aciertos y seteamos el contador de Aciertos
        numAciertos++;
        aciertosImg.setText(""+numAciertos);

        //Guardamos el nombre del Objeto acertado para que no vuelva a repetirse
        listAux.add(nombreRespuesta);

        //Asignamos que ha sido un acierto para el metodo @next_escudo
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

        if(f instanceof DynamicFragmentMarcha) {
            ((DynamicFragmentMarcha)f).pararMarcha();
        }

        //Reseteamos el campo
        respuesta.setText("");

        if (bandera == true);
        else numVidas--;

        if (numVidas==0){
            finJuego();
        }
        else {
            cambiarImg();

            //Si es 0 -> Escudo
            //Si es 1 -> Paso
            //Si es 2 -> Tunica
            //Si es 3 -> Llamador
            //Si es 4 -> Marcha
            int aux = (int) (Math.random() * 5);



            //Configuramos el fragment a cargar
            if (aux >3){
                f = new DynamicFragmentMarcha();
            }else{
                f = new DynamicFragmentFotos();
            }

            //Cargamos los datos
            if (aux == 0) {
                //Generamos random
                int randomInt = (int) (Math.random() * listaH.size());

                //Cercioramos que no se haya acertado anteriormente
                while (listAux.contains(listaH.get(randomInt).getNombre())){
                    randomInt = (int) (Math.random() * listaH.size());
                }
                /*Picasso.with(this)
                        .load(listaH.get(randomInt).getEscudo())
                        .resize(250, 200)
                        .into(imageView);*/
                nombreRespuesta = listaH.get(randomInt).getNombre();
                arg = Long.valueOf(listaH.get(randomInt).getId());
                arg1 = "Escudo";
                System.out.println("Escudo Respuesta: "+nombreRespuesta);
            } else if (aux == 1) {
                int randomInt = (int) (Math.random() * listaP.size());
                while (listAux.contains(listaP.get(randomInt).getNombreTitular())){
                    randomInt = (int) (Math.random() * listaP.size());
                }
                /*Picasso.with(this)
                        .load(listaP.get(randomInt).getFoto())
                        .resize(250, 200)
                        .into(imageView);*/
                nombreRespuesta = listaP.get(randomInt).getNombreTitular();
                arg = Long.valueOf(listaP.get(randomInt).getId());
                arg1 = "Paso";
                System.out.println("Paso Respuesta: "+nombreRespuesta);
            } else if (aux == 2){
                int randomInt = (int) (Math.random() * listaH.size());
                while (listAux.contains(listaH.get(randomInt).getNombre())){
                    randomInt = (int) (Math.random() * listaH.size());
                }
                /*Picasso.with(this)
                        .load(listaH.get(randomInt).getFotoTunica())
                        .resize(250, 200)
                        .into(imageView);*/
                nombreRespuesta = listaH.get(randomInt).getNombre();
                arg = Long.valueOf(listaH.get(randomInt).getId());
                arg1 = "Tunica";
                System.out.println("Túnica Respuesta: "+nombreRespuesta);
            }else if(aux == 3){
                int randomInt = (int) (Math.random() * listaP.size());
                while (listAux.contains(listaP.get(randomInt).getLlamador())){
                    randomInt = (int) (Math.random() * listaP.size());
                }
                /*Picasso.with(this)
                        .load(listaP.get(randomInt).getLlamador())
                        .resize(250, 200)
                        .into(imageView);*/
                nombreRespuesta = listaP.get(randomInt).getNombreTitular();
                arg = Long.valueOf(listaP.get(randomInt).getId());
                arg1 = "Llamador";
                System.out.println("Llamador Respuesta: "+nombreRespuesta);
            }else if(aux == 4){
                int randomInt = (int) (Math.random() * listaM.size());
                while (listAux.contains(listaM.get(randomInt).getNombre())){
                    randomInt = (int) (Math.random() * listaM.size());
                }

                nombreRespuesta = listaM.get(randomInt).getNombre();
                arg = Long.valueOf(listaM.get(randomInt).getId());
                System.out.println("Marcha Respuesta: "+nombreRespuesta);
            }
            bandera = false;

            //Pasamos parámetros al fragmento
            Bundle args = new Bundle();
            args.putLong("arg", arg);
            args.putString("arg1", arg1);
            f.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, f)
                    .commit();

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
        end.add(Calendar.SECOND, 21);

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

    @Override
    protected void onStop() {
        super.onStop();
        mCountDown.stop();
    }
}
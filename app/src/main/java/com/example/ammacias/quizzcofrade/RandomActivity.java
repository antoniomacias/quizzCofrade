package com.example.ammacias.quizzcofrade;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.example.ammacias.quizzcofrade.Clases.Ranking;
import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Pojos_API.Hermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Marchas;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Pojos_API.Rankings;
import com.example.ammacias.quizzcofrade.Pojos_API.Usuarios;
import com.example.ammacias.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;
import com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos.DynamicFragmentFotos;
import com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos.DynamicFragmentMarcha;
import com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos.RankingFragment;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.example.ammacias.quizzcofrade.localdb.RankingDB;
import com.example.ammacias.quizzcofrade.localdb.RankingDBDao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;

public class RandomActivity extends AppCompatActivity{

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

    ListView listviu;
    List<RankingDB>l;
    Retrofit retrofit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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
    }// Fin onCreate

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
            vida1.setImageResource(R.drawable.vida1);
            vida2.setImageResource(R.drawable.vida2);
            vida3.setImageResource(R.drawable.vida3);
            vida4.setImageResource(R.drawable.vida4);
            vida5.setImageResource(0);
        }else if(numVidas==3){
            vida1.setImageResource(R.drawable.vida1);
            vida2.setImageResource(R.drawable.vida2);
            vida3.setImageResource(R.drawable.vida3);
            vida4.setImageResource(0);
            vida5.setImageResource(0);
        }else if(numVidas==2){
            vida1.setImageResource(R.drawable.vida1);
            vida2.setImageResource(R.drawable.vida2);
            vida3.setImageResource(0);
            vida4.setImageResource(0);
            vida5.setImageResource(0);
        }else if(numVidas==1) {
            vida1.setImageResource(R.drawable.vida1);
            vida2.setImageResource(0);
            vida3.setImageResource(0);
            vida4.setImageResource(0);
            vida5.setImageResource(0);
        }else if(numVidas==0) {
            vida1.setImageResource(0);
            vida2.setImageResource(0);
            vida3.setImageResource(0);
            vida4.setImageResource(0);
            vida5.setImageResource(0);
        }
    }

    private void finJuego() {
        // Poner el reloj a 0
        mCountDown.stop();
        cambiarImg();
        getRankingActual();
        //TODO: Cambiar img SweetAlertDialog => liquid button?
/*        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("¡SE HAN ACABADO LAS VIDAS!")
                .setConfirmText("Volver a jugar")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        redirect(findViewById(R.id.activity_detalle));
                    }
                })
                .show();
*/
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("¡SE HAN ACABADO LAS VIDAS!")
                .setContentText("¿Quieres ver el ranking?")
                .setCancelText("No, Volver a jugar")
                .setConfirmText("Sí, Ver ranking")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        getRankingActual();
                        sDialog.dismissWithAnimation();
                        redirect(findViewById(R.id.activity_detalle));
                    }
                }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        mostrarDialogoRanking();
                    }
                })
                .show();


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

    private void mostrarDialogoRanking() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RandomActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_ranking, null);
        // Editar los elementos: editText...
        RankingDBDao rankingDBDao = DatabaseConnection.getRankingDBDao(RandomActivity.this);
        List<RankingDB> ran = rankingDBDao.loadAll();

        // Paso 1
        listviu = (ListView) mView.findViewById(R.id.list_view_ranking);
        // Paso 2
        l = new ArrayList<>();
        for (RankingDB r:ran) {
            l.add(r);
        }
        // Paso 3
        RankingAdapter adapter = new RankingAdapter(this, R.layout.ranking_item, l);
        // Paso 4
        listviu.setAdapter(adapter);



        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();



        // Cuando cierre el diálogo, redireccionar a las categorías
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                redirect(findViewById(R.id.activity_detalle));
            }
        });
    }

    private void updateRanking(Long idUsuario, String nombre, String apellidos, String idSharedPreferences, int numAciertos, String fecha) {
        retrofit1.create(IRetrofit.class).updateRanking(idUsuario,
                nombre, apellidos, idSharedPreferences, numAciertos, fecha).enqueue(new Callback<Ranking>() {

            @Override
            public void onResponse(Response<Ranking> response, Retrofit retrofit) {
                Toast.makeText(RandomActivity.this, "EXITO al actualizar", Toast.LENGTH_SHORT).show();
                haztumagia();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RandomActivity.this, "ERROR al actualizar", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void insertRanking(Long idUsuario, String nombre, String apellidos, String idSharedPreferences, int numAciertos, String fecha) {
        retrofit1.create(IRetrofit.class).createRanking(idUsuario,
                nombre, apellidos, idSharedPreferences, this.numAciertos, fecha).enqueue(new Callback<Ranking>() {

            @Override
            public void onResponse(Response<Ranking> response, Retrofit retrofit) {
                Toast.makeText(RandomActivity.this, "¡Felicidades por tu primera partida!", Toast.LENGTH_SHORT).show();
                haztumagia();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RandomActivity.this, "ERROR en tu primera partida", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getRankingActual() {
        //haztumagia();
        Long idUsuario = 0L;
        String nombre = "", apellidos = "", fecha = "";
        // idface = idSharedPreferences, numAciertos

        // LO PRIMERO ES COMPARAR LA PUNTUACIÓN ACTUAL CON MI MEJOR REGISTRO LOCAL
        String idSharedPreferences = "";
        SharedPreferences settings = getSharedPreferences("PREFS_FACEBOOK", 0);
        idSharedPreferences = settings.getString("FIRST_LOGIN", "N");
        // La prefs de face id es: idSharedPreferences);

        RankingDBDao rankingDBDao = DatabaseConnection.getRankingDBDao(RandomActivity.this);
        List<RankingDB> ran = rankingDBDao.loadAll();

        for (RankingDB r:ran) {

            // Si existe registro
            System.out.println("Comparando "+r.getIdface()+" con "+idSharedPreferences);
            if(r.getIdface().equals(idSharedPreferences)){
                System.out.println("Son iguales");
                java.util.Date juDate = new Date();
                // Fri Mar 17 19:11:01 GMT+01:00 2017
                String[] parts = juDate.toString().split(" ");
                String dia = parts[0];          // Fri
                String mes = parts[1];          // Mar
                String numdia = parts[2];       // 17
                String hora = parts[3];         // 19:11:01
                String zonahoraria = parts[4];  // GMT+01:00
                String year = parts[5];         // 2017

                idUsuario = r.getIdUsuario();
                nombre = r.getNombre();
                apellidos = r.getApellidos();
                // idface = idSharedPreferences
                // numAciertos
                fecha = "El "+numdia+" / "+mes+" / "+year+" a las "+hora;

                System.out.println("Estos son los datos: \n"+idUsuario+" \n "+ nombre+" \n "+ apellidos+" \n "
                        + idSharedPreferences+" \n "+ numAciertos+" \n "+ fecha);

                System.out.println("Comparo ahora el número de aciertos: "+numAciertos+" - "+r.getAciertos());
                // Si ese registro es mayor estricto q el anterior => UPDATE en la BD
                if(numAciertos > r.getAciertos()){
                    System.out.println("Existe registro y acabas de batir récord");
                    // ¿Lanzar diálogo animación con felicitación?
                    updateRanking(idUsuario, nombre, apellidos, idSharedPreferences, numAciertos, fecha);
                }else{ // TODO: No es récord y muestro su posición
                    System.out.println("No has batido récord y te muestro tu posición");

                }



            }else{ // No existe registro y hago INSERT
                System.out.println("No existes en el ranking");
                insertRanking(idUsuario, nombre, apellidos, idSharedPreferences, numAciertos, fecha);
            }
        }
        //
        //
        // ¿Si está en el ranking, mostrar alguna animación? => liquid button?


        System.out.println("AHORA ME TRAIGO LOS DATOS, UNA VEZ INSERTADO O ACTUALIZADO");





        }

    private void haztumagia() {
        //RETROFIT Ranking

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<Rankings> autocompleteList5 =
                retrofit1.create(IRetrofit.class).getRankingRetrofit();

        autocompleteList5.enqueue(new Callback<Rankings>() {
            @Override
            public void onResponse(Response<Rankings> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Rankings r = response.body();
                    RankingDBDao rankingDBDao = DatabaseConnection.getRankingDBDao(RandomActivity.this);
                    rankingDBDao.deleteAll();
                    for (Ranking a: r.getData()) {
                        // System.out.println("RANKING: "+a);
                        //"id, nombre, banda, fecha, ruta"
                        RankingDB m = new RankingDB();
                        m.setIdUsuario(a.getIdUsuario());
                        m.setNombre(a.getNombre());
                        m.setApellidos(a.getApellidos());
                        m.setIdface(a.getIdface());
                        m.setFecha(a.getFecha());
                        m.setAciertos(a.getAciertos());

                        rankingDBDao.insertOrReplace(m);
                    }

                    //Toast.makeText(getApplicationContext(), "Para ver si carga antes la API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }


}

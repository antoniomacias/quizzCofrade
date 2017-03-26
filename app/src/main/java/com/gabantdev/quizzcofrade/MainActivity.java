package com.gabantdev.quizzcofrade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gabantdev.quizzcofrade.Clases.Hermandad;
import com.gabantdev.quizzcofrade.Clases.Marcha;
import com.gabantdev.quizzcofrade.Clases.Paso;
import com.gabantdev.quizzcofrade.Clases.Ranking;
import com.gabantdev.quizzcofrade.Clases.Usuario;
import com.gabantdev.quizzcofrade.Clases.UsuariosHermandades;
import com.gabantdev.quizzcofrade.Interfaces.IRetrofit;
import com.gabantdev.quizzcofrade.Pojos_API.Hermandades;
import com.gabantdev.quizzcofrade.Pojos_API.Marchas;
import com.gabantdev.quizzcofrade.Pojos_API.Pasos;
import com.gabantdev.quizzcofrade.Pojos_API.Rankings;
import com.gabantdev.quizzcofrade.Pojos_API.Usuarios;
import com.gabantdev.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.Interfaces.ICofrade;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.HermandadDBDao;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDBDao;
import com.gabantdev.quizzcofrade.localdb.PasosDB;
import com.gabantdev.quizzcofrade.localdb.PasosDBDao;
import com.gabantdev.quizzcofrade.localdb.RankingDB;
import com.gabantdev.quizzcofrade.localdb.RankingDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuarioDB;
import com.gabantdev.quizzcofrade.localdb.UsuarioDBDao;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.gabantdev.quizzcofrade.localdb.UsuariosHermandadesDBDao;


import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements ICofrade{
    Intent i;
    //ImageView escudos, tunicas, pasos, marchas, aleatorio;

    static IRetrofit service1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //escudos = (ImageView)findViewById(R.id.escudo_img);
        //tunicas = (ImageView)findViewById(R.id.tunica_img);
        //pasos = (ImageView)findViewById(R.id.paso_img);
        //marchas = (ImageView)findViewById(R.id.marcha_img);
        //aleatorio = (ImageView)findViewById(R.id.aleatorio_img);


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service1 = retrofit1.create(IRetrofit.class);

        boolean mboolean = false;
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) { // do the thing for the first time


            getUsuarios();
            getHermandades();
            getPasos();
            getUsuarios_Hermandades();
            getMarchas();
            getRanking();


            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
        }

    }

    //RETROFIT Ranking
    public void getRanking() {
        Call<Rankings> autocompleteList5 = service1.getRankingRetrofit();

        autocompleteList5.enqueue(new Callback<Rankings>() {
            @Override
            public void onResponse(Response<Rankings> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Rankings r = response.body();
                    RankingDBDao rankingDBDao = DatabaseConnection.getRankingDBDao(MainActivity.this);

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
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }



    //RETROFIT MARCHAS
    private void getMarchas() {

        Call<Marchas> autocompleteList5 = service1.getMarchas();

        autocompleteList5.enqueue(new Callback<Marchas>() {
            @Override
            public void onResponse(Response<Marchas> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Marchas r = response.body();
                    MarchaDBDao marchaDBDao = DatabaseConnection.getMarchasDBDao(MainActivity.this);

                    for (Marcha a: r.getData()) {
                        //"id, nombre, banda, fecha, ruta"
                        MarchaDB m = new MarchaDB();
                        m.setNombre(a.getNombre());
                        m.setBanda(a.getBanda());
                        m.setFecha(a.getFecha());
                        m.setRuta(a.getRuta());

                        marchaDBDao.insertOrReplace(m);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    // RETROFIT USUARIOS_HERMANDADESCall<UsuariosHermandadesAPI> autocompleteList1 = service1.getUsuariosHermandadesRetrofit();
    private void getUsuarios_Hermandades() {

        Call<UsuariosHermandadesAPI> autocompleteList4 = service1.getUsuariosHermandadesRetrofit();

        autocompleteList4.enqueue(new Callback<UsuariosHermandadesAPI>() {
            @Override
            public void onResponse(Response<UsuariosHermandadesAPI> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    UsuariosHermandadesAPI result= response.body();
                    UsuariosHermandadesDBDao usuariosHermandadesDBDao = DatabaseConnection.getUsuariosHermandadesDBDao(MainActivity.this);

                    for (UsuariosHermandades h:result.getData()) {
                        // " id, idUsuario, idHermandad, categoria "
                        UsuariosHermandadesDB usuarioDB = new UsuariosHermandadesDB();
                        usuarioDB.setId(h.getId());
                        usuarioDB.setIdUsuario(h.getIdUsuario());
                        usuarioDB.setIdHermandad(h.getIdHermandad());
                        usuarioDB.setCategoria(h.getCategoria());

                        usuariosHermandadesDBDao.insertOrReplace(usuarioDB);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    // RETROFIT PASOS
    private void getPasos() {

        Call<Pasos> autocompleteList3 = service1.getPasosRetrofit();

        autocompleteList3.enqueue(new Callback<Pasos>() {
            @Override
            public void onResponse(Response<Pasos> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Pasos result= response.body();

                    //Array auxiliar con indices -> ID's
                    List<Long> listNumeros = new ArrayList<Long>();
                    List<Long> listNumerosL = new ArrayList<Long>();
                    for (int i = 0; i<=result.getData().size();i++){
                        listNumeros.add(Long.valueOf(i));
                        listNumerosL.add(Long.valueOf(i));
                    }

                    int numeroRandom =0;
                    //Generamos nuevos ID's para cada Paso.
                    for (Paso p: result.getData()){
                        numeroRandom = (int)(Math.random() * listNumeros.size()-1);
                        p.setId(Long.valueOf(listNumeros.get(numeroRandom)));
                        listNumeros.remove(listNumeros.get(numeroRandom));
                    }


                    int numeroRandomLlamadores =0;

                    PasosDBDao pasosDBDao = DatabaseConnection.getPasosDBDao(MainActivity.this);
                    PasosDBDao pasosDBDaoLlamadores = DatabaseConnection.getPasosDBLDao(MainActivity.this);

                    for (Paso p:result.getData()) {
                        //"id, idHermandad, nombreTitular, foto, colorCirio, banda, capataz, numCostalero, llamador"
                        PasosDB pasoDB = new PasosDB();
                        pasoDB.setId(p.getId());
                        pasoDB.setNombreHermandad(p.getNombreHermandad());
                        pasoDB.setNombreTitular(p.getNombreTitular());
                        pasoDB.setFotoPaso(p.getFotoPaso());
                        pasoDB.setColorCirio(p.getColorCirio());
                        pasoDB.setBanda(p.getBanda());
                        pasoDB.setCapataz(p.getCapataz());
                        pasoDB.setLlamador(p.getLlamador());
                        pasoDB.setNumCostaleros(p.getNumCostaleros());

                        System.out.println("Paso: "+pasoDB);
                        pasosDBDao.insertOrReplace(pasoDB);

                        numeroRandomLlamadores = (int)(Math.random() * listNumerosL.size()-1);
                        pasoDB.setId(Long.valueOf(listNumerosL.get(numeroRandomLlamadores)));
                        listNumerosL.remove(listNumerosL.get(numeroRandomLlamadores));


                        System.out.println("Llamadores: "+pasoDB);
                        //System.out.println("Numero random llamadores: "+numeroRandomLlamadores);
                        pasosDBDaoLlamadores.insertOrReplace(pasoDB);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "Error al cargar los pasos. Por favor, reinstala", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }

    // RETROFIT HERMANDADES
    private void getHermandades() {

         Call<Hermandades> autocompleteList2 = service1.getHermandadesRetrofit();

        autocompleteList2.enqueue(new Callback<Hermandades>() {
            @Override
            public void onResponse(Response<Hermandades> response, Retrofit retrofit) {

                if (response.isSuccess()){
                    Hermandades result= response.body();

                    //Array auxiliar con indices -> ID's
                    List<Long> listNumeros = new ArrayList<Long>();
                    List<Long> listNumerosT = new ArrayList<Long>();
                    for (int i = 0; i<=result.getData().size();i++){
                        listNumeros.add(Long.valueOf(i));
                        listNumerosT.add(Long.valueOf(i));
                    }

                    int numeroRandom =0;
                    //Generamos nuevos ID's para cada Hermandad.
                    for (Hermandad h: result.getData()){
                        numeroRandom = (int)(Math.random() * listNumeros.size()-1);
                        h.setId(Long.valueOf(listNumeros.get(numeroRandom)));
                        listNumeros.remove(listNumeros.get(numeroRandom));
                    }

                    int numeroRandomTunica =0;

                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(MainActivity.this);
                    HermandadDBDao hermandadDBDaoTunicas = DatabaseConnection.getHermandadDBTDao(MainActivity.this);

                    for (Hermandad h:result.getData()) {

                        HermandadDB hermandadDB = new HermandadDB();
                        hermandadDB.setId(h.getId());
                        hermandadDB.setNombre(h.getNombre());
                        hermandadDB.setEscudo(h.getEscudo());
                        hermandadDB.setTunica(h.getTunica());
                        hermandadDB.setFotoTunica(h.getFoto_tunica());
                        hermandadDB.setDia(h.getDia());
                        hermandadDB.setNumNazarenos(h.getNumNazarenos());
                        hermandadDB.setAnyoFundacion(h.getAnyoFundacion());

                        hermandadDBDao.insertOrReplace(hermandadDB);

                        //Cambio la MISMA hermandad con el nuevo ID RANDOM
                        numeroRandomTunica = (int)(Math.random() * listNumerosT.size()-1);
                        hermandadDB.setId(Long.valueOf(listNumerosT.get(numeroRandomTunica)));
                        listNumerosT.remove(listNumerosT.get(numeroRandomTunica));

                        hermandadDBDaoTunicas.insertOrReplace(hermandadDB);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FALLO AL CARGAR LAS HERMANDADES: "+t.getMessage());
            }
        });
    }

    // RETROFIT USUARIOS
    private void getUsuarios() {

        Call<Usuarios> autocompleteList1 = service1.getUsuariosRetrofit();

        autocompleteList1.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Usuarios result= response.body();
                    UsuarioDBDao hermandadDBDao = DatabaseConnection.getUsuarioDBDao(MainActivity.this);

                    for (Usuario h:result.getData()) {
                        // " id, nick, email "
                        UsuarioDB usuarioDB = new UsuarioDB();
                        usuarioDB.setId(h.getId());
                        usuarioDB.setNombre(h.getNombre());
                        usuarioDB.setApellidos(h.getApellidos());
                        usuarioDB.setEmail(h.getEmail());
                        usuarioDB.setIdface(h.getIdface());
                        usuarioDB.setAuthToken(h.getAuthToken());

                        hermandadDBDao.insertOrReplace(usuarioDB);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    @Override
    public void onClickHermandadDB(HermandadDB h) {

    }

    @Override
    public void onClickPasosDB(PasosDB p) {
    }

    @Override
    public void onClickMarcha(MarchaDB m) {

    }


    public void click_escudos(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Escudos");
        i = new Intent(MainActivity.this, EscudosActivity.class);
        startActivity(i);
    }

    public void click_tunicas(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Tunicas");
        i = new Intent(MainActivity.this, EscudosActivity.class);
        startActivity(i);
    }

    public void click_pasos(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Pasos");
        i = new Intent(MainActivity.this, PasosActivity.class);
        startActivity(i);
    }

    public void click_marchas(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Marchas");
        i = new Intent(MainActivity.this, MarchaActivity.class);
        startActivity(i);
    }

    public void click_random(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Random");
        i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void click_llamadores(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Llamadores");
        i = new Intent(MainActivity.this, PasosActivity.class);
        startActivity(i);
    }

    public void click_login(View view) {
        ((Application_vars) this.getApplication()).setCategoriaElegida("Login");
        i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}

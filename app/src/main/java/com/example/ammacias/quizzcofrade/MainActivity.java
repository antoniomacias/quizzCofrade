package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.Clases.Hermandad;
import com.example.ammacias.quizzcofrade.Clases.Marcha;
import com.example.ammacias.quizzcofrade.Clases.Paso;
import com.example.ammacias.quizzcofrade.Clases.Usuario;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Pojos_API.Hermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Marchas;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Pojos_API.Usuarios;
import com.example.ammacias.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;

import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements ICofrade{
    Intent i;
    TextView escudos, tunicas, pasos, marchas, aleatorio;

    IRetrofit service1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escudos = (TextView)findViewById(R.id.escudos);
        tunicas = (TextView)findViewById(R.id.tunicas);
        pasos = (TextView)findViewById(R.id.pasos);
        marchas = (TextView)findViewById(R.id.marchas);
        aleatorio = (TextView)findViewById(R.id.random);


        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service1 = retrofit1.create(IRetrofit.class);

        boolean mboolean = false;
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) { // do the thing for the first time


            System.out.println("*****************************\n****************************\nENTRO POR PRIMERA VEZ");
            getUsuarios();
            //getHermandades();
            //getPasos();
            getUsuarios_Hermandades();
            //getMarchas();


            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
        } else { // other time your app loads
            getHermandades();
            getPasos();
            getMarchas();
        }

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

                    /*for(UsuariosHermandades uh:result.getData()){
                        if(!categorias.contains(uh.getCategoria())){
                            categorias.add(uh.getCategoria());
                            System.out.println("Categoria a añadir: "+uh.getCategoria());
                        }
                    }*/

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
                    PasosDBDao pasosDBDao = DatabaseConnection.getPasosDBDao(MainActivity.this);

                    for (Paso p:result.getData()) {
                        //"id, idHermandad, nombreTitular, foto, colorCirio, banda, capataz, numCostalero, llamador"
                        PasosDB pasoDB = new PasosDB();
                        pasoDB.setId(p.getId());
                        pasoDB.setIdHermandad(p.getIdHermandad());
                        pasoDB.setNombreTitular(p.getNombreTitular());
                        pasoDB.setFoto(p.getFoto());
                        pasoDB.setColorCirio(p.getColorCirio());
                        pasoDB.setBanda(p.getBanda());
                        pasoDB.setCapataz(p.getCapataz());
                        pasoDB.setLlamador(p.getLlamador());
                        pasoDB.setNumCostaleros(p.getNumCostaleros());

                        pasosDBDao.insertOrReplace(pasoDB);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
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

                    //TODO: Foreach que cambie los ID's result de forma random e insertar en BBDD GreenDao
                    System.out.println("TODAS LAS HERMANDADES RETROFIT1: "+result.getData().get(result.getData().size()-2));
                    System.out.println("TODAS LAS HERMANDADES RETROFIT2: "+result.getData().get(result.getData().size()-3));
                    //System.out.println("ANTES DE DESORDENAR: "+result.getData());
                    Collections.shuffle(result.getData());

                    System.out.println("TODAS LAS HERMANDADES SHUFFLE1: "+result.getData().get(1));
                    System.out.println("TODAS LAS HERMANDADES SHUFFLE2: "+result.getData().get(2));
                    //System.out.println("TRAS DESORDENAR: "+result.getData());
                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(MainActivity.this);

                    for (Hermandad h:result.getData()) {
                        //"id, nombre, escudo, tunica, foto_tunica, dia, numNazarenos, anyoFundacion"
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
                    }
                    List<HermandadDB> joes = hermandadDBDao.loadAll();
                    System.out.println("TODAS LAS HERMANDADES 1: "+joes.get(1));
                    System.out.println("TODAS LAS HERMANDADES 2: "+joes.get(2));
                    System.out.println("Fin insert hermandades");


                    // Desordeno las hermandades
                    /*System.out.println("ANTES DE DESORDENAR: "+result.getData());
                    Collections.shuffle(result.getData());
                    System.out.println("TRAS DESORDENAR: "+result.getData());*/

                    //"id, nombre, escudo, tunica, foto_tunica, dia, numNazarenos, anyoFundacion"
                     /*HermandadDB hermandadDB = new HermandadDB();
                    //hermandadDB.setId(h.getId());
                    for (Long i = 0L; i < result.getData().size(); i++) {
                        hermandadDB.setId(i);
                            int a = i.intValue();
                            Hermandad h = result.getData().get(a);
                        System.out.println("Insertando en la bd a \n"+h);
                        hermandadDB.setNombre(h.getNombre());
                        hermandadDB.setEscudo(h.getEscudo());
                        hermandadDB.setTunica(h.getTunica());
                        hermandadDB.setFotoTunica(h.getFoto_tunica());
                        hermandadDB.setDia(h.getDia());
                        hermandadDB.setNumNazarenos(h.getNumNazarenos());
                        hermandadDB.setAnyoFundacion(h.getAnyoFundacion());

                        hermandadDBDao.insertOrReplace(hermandadDB);
                    }

                    List<HermandadDB> joes = hermandadDBDao.queryBuilder()
                            .list();
                    System.out.println("TODAS LAS HERMANDADES: "+joes);*/
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
                        usuarioDB.setNick(h.getNick());
                        usuarioDB.setEmail(h.getEmail());

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
        i = new Intent(MainActivity.this, RandomActivity.class);
        startActivity(i);
    }
}

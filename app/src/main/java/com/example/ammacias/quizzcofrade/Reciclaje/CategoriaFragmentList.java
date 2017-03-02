package com.example.ammacias.quizzcofrade.Reciclaje;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.Clases.Hermandad;
import com.example.ammacias.quizzcofrade.Clases.Paso;
import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Clases.Usuario;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;
import com.example.ammacias.quizzcofrade.Pojos_API.Hermandades;
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
import com.example.ammacias.quizzcofrade.Pojos_API.Usuarios;
import com.example.ammacias.quizzcofrade.Pojos_API.UsuariosHermandadesAPI;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDB;
import com.example.ammacias.quizzcofrade.localdb.UsuarioDBDao;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;

import java.util.LinkedList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link ICofrade}
 * interface.
 */
public class CategoriaFragmentList extends Fragment {

    List<String> categorias =new LinkedList<>();
    RecyclerView recyclerView = null;

    // TODO: Customize parameters
    private int mColumnCount = 2;

    private ICofrade mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriaFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            getDatos();
            /*getPasos();
            getHermandades();
            getUsuarios();
            getUsuariosHermandades();
            //recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(categorias, mListener));
            recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(getActivity(),categorias, mListener));*/
        }

        return view;
    }

    private void getUsuariosHermandades() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        Call<UsuariosHermandadesAPI> autocompleteList1 = service1.getUsuariosHermandadesRetrofit();

        autocompleteList1.enqueue(new Callback<UsuariosHermandadesAPI>() {
            @Override
            public void onResponse(Response<UsuariosHermandadesAPI> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    UsuariosHermandadesAPI result= response.body();
                    UsuariosHermandadesDBDao usuariosHermandadesDBDao = DatabaseConnection.getUsuariosHermandadesDBDao(getActivity());

                    for (UsuariosHermandades h:result.getData()) {
                        // " id, idUsuario, idHermandad, categoria "
                        System.out.println("ITERANDO EL USUARIO_HERMANDAD "+h);
                        UsuariosHermandadesDB usuarioDB = new UsuariosHermandadesDB();
                        usuarioDB.setId(h.getId());
                        usuarioDB.setIdUsuario(h.getIdUsuario());
                        usuarioDB.setIdHermandad(h.getIdHermandad());
                        usuarioDB.setCategoria(h.getCategoria());

                        usuariosHermandadesDBDao.insertOrReplace(usuarioDB);
                    }

                    for(UsuariosHermandades uh:result.getData()){
                        if(!categorias.contains(uh.getCategoria())){
                            categorias.add(uh.getCategoria());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    // RETROFIT USUARIOS
    private void getUsuarios() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        Call<Usuarios> autocompleteList1 = service1.getUsuariosRetrofit();

        autocompleteList1.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Usuarios result= response.body();
                    UsuarioDBDao hermandadDBDao = DatabaseConnection.getUsuarioDBDao(getActivity());

                    for (Usuario h:result.getData()) {
                        // " id, nick, email "
                        System.out.println("ITERANDO EL USUARIO "+h);
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

    // RETROFIT HERMANDADES
    private void getHermandades() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        Call<Hermandades> autocompleteList1 = service1.getHermandadesRetrofit();

        autocompleteList1.enqueue(new Callback<Hermandades>() {
            @Override
            public void onResponse(Response<Hermandades> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Hermandades result= response.body();
                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());

                    for (Hermandad h:result.getData()) {
                        //"id, nombre, escudo, tunica, foto_tunica, dia, numNazarenos, anyoFundacion"
                        System.out.println("ITERANDO LA HERMANDAD "+h);
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

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        Call<Pasos> autocompleteList1 = service1.getPasosRetrofit();

        autocompleteList1.enqueue(new Callback<Pasos>() {
            @Override
            public void onResponse(Response<Pasos> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Pasos result= response.body();
                    PasosDBDao pasosDBDao = DatabaseConnection.getPasosDBDao(getActivity());

                    for (Paso p:result.getData()) {
                        //"id, idHermandad, nombreTitular, foto, colorCirio, banda, capataz, numCostalero, llamador"
                        System.out.println("ITERANDO EL PASO "+p);
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


    void getDatos(){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service1 = retrofit1.create(IRetrofit.class);

        // RETROFIT USUARIOS
        Call<Usuarios> autocompleteList1 = service1.getUsuariosRetrofit();

        autocompleteList1.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Usuarios result= response.body();
                    UsuarioDBDao hermandadDBDao = DatabaseConnection.getUsuarioDBDao(getActivity());

                    for (Usuario h:result.getData()) {
                        // " id, nick, email "
                        System.out.println("ITERANDO EL USUARIO "+h);
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



        // RETROFIT HERMANDADES

        Call<Hermandades> autocompleteList2 = service1.getHermandadesRetrofit();

        autocompleteList2.enqueue(new Callback<Hermandades>() {
            @Override
            public void onResponse(Response<Hermandades> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Hermandades result= response.body();
                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());

                    for (Hermandad h:result.getData()) {
                        //"id, nombre, escudo, tunica, foto_tunica, dia, numNazarenos, anyoFundacion"
                        System.out.println("ITERANDO LA HERMANDAD "+h);
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
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        // RETROFIT PASOS
        Call<Pasos> autocompleteList3 = service1.getPasosRetrofit();

        autocompleteList3.enqueue(new Callback<Pasos>() {
            @Override
            public void onResponse(Response<Pasos> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Pasos result= response.body();
                    PasosDBDao pasosDBDao = DatabaseConnection.getPasosDBDao(getActivity());

                    for (Paso p:result.getData()) {
                        //"id, idHermandad, nombreTitular, foto, colorCirio, banda, capataz, numCostalero, llamador"
                        System.out.println("ITERANDO EL PASO "+p);
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


        // RETROFIT USUARIOS_HERMANDADESCall<UsuariosHermandadesAPI> autocompleteList1 = service1.getUsuariosHermandadesRetrofit();
        Call<UsuariosHermandadesAPI> autocompleteList4 = service1.getUsuariosHermandadesRetrofit();
        autocompleteList4.enqueue(new Callback<UsuariosHermandadesAPI>() {
            @Override
            public void onResponse(Response<UsuariosHermandadesAPI> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    UsuariosHermandadesAPI result= response.body();
                    UsuariosHermandadesDBDao usuariosHermandadesDBDao = DatabaseConnection.getUsuariosHermandadesDBDao(getActivity());

                    for (UsuariosHermandades h:result.getData()) {
                        // " id, idUsuario, idHermandad, categoria "
                        System.out.println("ITERANDO EL USUARIO_HERMANDAD "+h);
                        UsuariosHermandadesDB usuarioDB = new UsuariosHermandadesDB();
                        usuarioDB.setId(h.getId());
                        usuarioDB.setIdUsuario(h.getIdUsuario());
                        usuarioDB.setIdHermandad(h.getIdHermandad());
                        usuarioDB.setCategoria(h.getCategoria());

                        usuariosHermandadesDBDao.insertOrReplace(usuarioDB);
                    }

                    for(UsuariosHermandades uh:result.getData()){
                        if(!categorias.contains(uh.getCategoria())){
                            categorias.add(uh.getCategoria());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });




        recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(getActivity(),categorias, mListener));

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ICofrade) {
            mListener = (ICofrade) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ICofrade");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}

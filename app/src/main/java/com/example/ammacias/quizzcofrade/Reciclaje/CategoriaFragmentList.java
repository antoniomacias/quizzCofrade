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
import com.example.ammacias.quizzcofrade.Pojos_API.Pasos;
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
            getPasos();
            //recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(categorias, mListener));
        }

        return view;
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

        //RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // TODOS LOS DATOS
        IRetrofit service = retrofit.create(IRetrofit.class);

        Call<Result> autocompleteList = service.getDatos();

        autocompleteList.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    Result r = response.body();

                    UsuarioDBDao usuarioDBDao = DatabaseConnection.getUsuarioDBDao(getActivity());

                    for (Usuario u: r.getUsuario()) {
                        UsuarioDB usuarioDB = new UsuarioDB();
                        usuarioDB.setId(u.getId());
                        usuarioDB.setNick(u.getNick());

                        usuarioDBDao.insertOrReplace(usuarioDB);
                    }


                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                    PasosDBDao pasosDBDao= DatabaseConnection.getPasosDBDao(getActivity());

                    for (Hermandad h: r.getHermandad()) {
                            HermandadDB hermandadDB= new HermandadDB();
                            hermandadDB.setId(h.getId());
                            hermandadDB.setNombre(h.getNombre());
                            hermandadDB.setEscudo(h.getEscudo());
                            hermandadDB.setTunica(h.getTunica());
                            hermandadDB.setDia(h.getDia());
                            hermandadDB.setNumNazarenos(h.getNumNazarenos());
                            hermandadDB.setAnyoFundacion(h.getAnyoFundacion());

                            hermandadDBDao.insertOrReplace(hermandadDB);

                            //Recorro los pasos para añadirlos
                            /*for (Paso p: h.getPaso()) {
                                PasosDB pasosDB = new PasosDB();

                                pasosDB.setId(p.getId());
                                pasosDB.setNombreTitular(p.getNombreTitular());
                                pasosDB.setFoto((String) p.getFoto());
                                pasosDB.setBanda(p.getBanda());
                                pasosDB.setIdHermandad(h.getId());

                                pasosDBDao.insertOrReplace(pasosDB);
                            }*/
                    }

                    UsuariosHermandadesDBDao usuariosHermandadesDBDao = DatabaseConnection.getUsuariosHermandadesDBDao(getActivity());

                    for (UsuariosHermandades uh: r.getUsuariosHermandades()) {

                        UsuariosHermandadesDB usuariosHermandadesDB= new UsuariosHermandadesDB();
                        usuariosHermandadesDB.setId(uh.getId());
                        usuariosHermandadesDB.setIdUsuario(uh.getIdUsuario());
                        usuariosHermandadesDB.setCategoria(uh.getCategoria());
                        usuariosHermandadesDB.setIdHermandad(uh.getIdHermandad());

                        usuariosHermandadesDBDao.insertOrReplace(usuariosHermandadesDB);
                    }
 /*                 categorias.add(usuariosHermandadesDBDao.queryBuilder()
                            .where(new WhereCondition.StringCondition("1 GROUP BY categoria")).list());

                    categorias.add(usuariosHermandadesDBDao.queryBuilder()
                            .where(WhereCondition.StringCondition.field.in(fieldValues)).list());
*/
                    //List<UsuariosHermandadesDB> a = usuariosHermandadesDBDao.queryBuilder()
                    //.where(new WhereCondition.StringCondition(UsuariosHermandadesDBDao.Properties.Id.gt(0).toString())).list();

                    /*return usuariosHermandadesDBDao.queryBuilder().where(
                            new WhereCondition.StringCondition(UsuariosHermandadesDBDao.Properties.Categoria.eq("") " IN "
                                    + "(SELECT " + UsuariosHermandadesDBDao.Properties.Categoria+ " FROM "
                                    + UsuariosHermandadesDBDao.TABLENAME)+ " group by categoria")
                            .list();*/

                    for(UsuariosHermandades uh:r.getUsuariosHermandades()){
                        if(!categorias.contains(uh.getCategoria())){
                            categorias.add(uh.getCategoria());
                        }
                    }
                    //System.out.println(categorias);

                    recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(getActivity(),categorias, mListener));

                } else {
                    System.out.println("Error: " + response.code());
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Throw: " + t.getMessage());
                Toast.makeText(getActivity(), "Throw: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // ESCUDOS
        // PASOS
        // HERMANDADES
        // ¿USUARIOS?
        // INTERMEDIA
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

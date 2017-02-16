package com.example.ammacias.quizzcofrade;

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

import com.example.ammacias.quizzcofrade.Clases.Result;
import com.example.ammacias.quizzcofrade.Clases.UsuariosHermandades;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.Interfaces.IRetrofit;

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
            //recyclerView.setAdapter(new MyCategoriaRecyclerViewAdapter(categorias, mListener));
        }

        return view;
    }


    void getDatos(){

        //RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IRetrofit.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit service = retrofit.create(IRetrofit.class);

        Call<Result> autocompleteList = service.getDatos();

        autocompleteList.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    Result r = response.body();
                    for(UsuariosHermandades uh:r.getUsuariosHermandades()){
                        if(!categorias.contains(uh.getCategoria())){
                            categorias.add(uh.getCategoria());
                        }
                    }
                    System.out.println(categorias);

                   /* MarcaDBDao marcaDBDao = DatabaseConnection.getMarcaDBDao(getActivity());
                    for (Marca m: r.getMarca()) {
                        //Si NO existe
                        if (marcaDBDao.load(m.getId())==null){
                            System.out.println("YEPA: "+marcaDBDao.load(m.getId()));

                            MarcaDB nuevaMarca = new MarcaDB();
                            nuevaMarca.setId(m.getId());
                            nuevaMarca.setNombre(m.getNombre());
                            nuevaMarca.setNivel(m.getNivel());
                            nuevaMarca.setFoto(m.getFoto());
                            nuevaMarca.setAcertado(m.getAcertado());

                            marcaDBDao.insert(nuevaMarca);
                        }else{
                            //TODO: Si hay algo diferente en alguna marca del JSON, hago update en local
                        /*for (MarcaDB a: marcaDBDao.loadAll()) {

                        } //
                        }


                        // SELECT * FROM marca WHERE
                        // MarcaDB marcaActual = marcadbDao.load(id);

                    }*/

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

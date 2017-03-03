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

import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;

import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link ICofrade}
 * interface.
 */
public class EscudosFragmentList extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 4;

    private ICofrade mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EscudosFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escudosdb_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            /*String cat_elegida = ((Application_vars) getActivity().getApplication()).getCategoriaElegida();
            switch (cat_elegida){
                case "Pasos":
                    PasosDBDao pasosDBDao = DatabaseConnection.getPasosDBDao(getActivity());
                    for(PasosDB p: pasosDBDao.loadAll()){
                        fotos.add(p.getFoto());
                    }
                    break;
                case "Escudos":
                    HermandadDBDao hermandadDBDao_escudos = DatabaseConnection.getHermandadDBDao(getActivity());
                    for(HermandadDB p: hermandadDBDao_escudos.loadAll()){
                        fotos.add(p.getEscudo());
                    }
                    break;
                case "Random":
                    break;
                case "Tunicas":
                    HermandadDBDao hermandadDBDao_tunicas = DatabaseConnection.getHermandadDBDao(getActivity());
                    for(HermandadDB p: hermandadDBDao_tunicas.loadAll()){
                        fotos.add(p.getTunica());
                    }
                    break;
                default:
                    break;
            }*/

            // Traigo las hermandades de Application. Si no hay, las cargo de la BD y seteo.
            List<HermandadDB> listHermandad = ((Application_vars) getActivity().getApplication()).getListHermandadEscudos();
            if(listHermandad==null){
                HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                listHermandad = hermandadDBDao.loadAll();
                // AQUÍ HAGO LA LISTA ALEATORIA
                //Collections.shuffle(listHermandad);
                ((Application_vars) getActivity().getApplication()).setListHermandadEscudos(listHermandad);
            }


            recyclerView.setAdapter(new MyEscudosDBRecyclerViewAdapter(getActivity(), listHermandad, mListener));
        }
        return view;
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

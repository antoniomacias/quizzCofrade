package com.example.ammacias.quizzcofrade.Recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;

import java.sql.SQLOutput;
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
    String cat_elegida;
    List<HermandadDB> hermandadDBs;
    List<HermandadDB> hermandadDBsT;
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
        cat_elegida = ((Application_vars) getActivity().getApplication()).getCategoriaElegida();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //Load Hermandades from DB
            if (cat_elegida.contains("Escudos")){
                HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                hermandadDBs = hermandadDBDao.loadAll();
                System.out.println("Fragment escudos: "+hermandadDBs);

                recyclerView.setAdapter(new MyEscudosDBRecyclerViewAdapter(getActivity(), hermandadDBs, mListener));
            }else{
                HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBTDao(getActivity());
                hermandadDBsT = hermandadDBDao.loadAll();

                System.out.println("Fragment tunicas: "+hermandadDBsT);

                recyclerView.setAdapter(new MyEscudosDBRecyclerViewAdapter(getActivity(), hermandadDBsT, mListener));
            }
            /*switch (cat_elegida){
                case "Escudos":
                    HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                   hermandadDBs = hermandadDBDao.loadAll();
                    HermandadDBDao hermandadDBDaoTunicas1 = DatabaseConnection.getHermandadDBTDao(getActivity());
                    hermandadDBs = hermandadDBDaoTunicas1.loadAll();
                    break;
                case "Túnicas":
                    HermandadDBDao hermandadDBDaoTunicas = DatabaseConnection.getHermandadDBTDao(getActivity());
                    hermandadDBs = hermandadDBDaoTunicas.loadAll();
                    break;
                default:
                    HermandadDBDao hermandadDBDaoTunicas2 = DatabaseConnection.getHermandadDBTDao(getActivity());
                    hermandadDBs = hermandadDBDaoTunicas2.loadAll();
                    break;

            }*/


            //Call recycler
            //Ctx -> Picasso
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

package com.gabantdev.quizzcofrade.Recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabantdev.quizzcofrade.Interfaces.ICofrade;

import com.gabantdev.quizzcofrade.R;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.HermandadDBDao;

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
            }else{
                HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBTDao(getActivity());
                hermandadDBs = hermandadDBDao.loadAll();
            }
            recyclerView.setAdapter(new MyEscudosDBRecyclerViewAdapter(getActivity(), hermandadDBs, mListener));
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

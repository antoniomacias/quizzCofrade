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
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDBDao;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link ICofrade}
 * interface.
 */
public class MarchaFragmentList extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 4;

    private ICofrade mListener;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarchaFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marcha_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            //Load Marchas from DB
            MarchaDBDao marchaDBDao = DatabaseConnection.getMarchasDBDao(getActivity());
            List<MarchaDB> listMarchas = marchaDBDao.loadAll();

            //Call recycler
            recyclerView.setAdapter(new MyMarchaRecyclerViewAdapter(listMarchas, mListener));

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

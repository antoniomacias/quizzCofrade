package com.gabantdev.quizzcofrade.Recycler.FragmentsDinamicos;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gabantdev.quizzcofrade.R;
import com.gabantdev.quizzcofrade.Service.MyReproductor;
import com.gabantdev.quizzcofrade.localdb.DatabaseConnection;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDBDao;

import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragmentMarcha extends Fragment {

    InteractivePlayerView ipv;
    Intent i;
    public DynamicFragmentMarcha() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dynamic_fragment_marcha, container, false);

        long index = getArguments().getLong("arg",0);
        ipv = (InteractivePlayerView) v.findViewById(R.id.ipv);

        MarchaDBDao marchaDBDao = DatabaseConnection.getMarchasDBDao(getActivity());
        MarchaDB marchaDB = marchaDBDao.load(index);

        i = new Intent(getActivity(), MyReproductor.class);
        i.putExtra("cancion", marchaDBDao.load(index).getRuta());
        getActivity().startService(i);

        //Inicio el reproductor
        iniciarMediaPlayer();

        return v;
    }

    void iniciarMediaPlayer(){
        //Skin reproductor
        ipv.stop();
        ipv.setProgress(-1);
        ipv.setMax(20);
        ipv.setProgressEmptyColor(Color.GRAY);
        ipv.setProgressEmptyColor(Color.BLACK);
        ipv.start();
        ipv.setOnActionClickedListener(new OnActionClickedListener() {
            @Override
            public void onActionClicked(int id) {
                switch (id){
                    case 1:
                       break;
                    case 2:
                        break;
                    case 3:
                         break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void pararMarcha(){
        getActivity().stopService(i);
    }
}

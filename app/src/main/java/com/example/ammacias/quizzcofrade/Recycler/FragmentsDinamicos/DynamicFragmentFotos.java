package com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDBDao;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragmentFotos extends Fragment {

    ImageView imageView;

    HermandadDBDao hermandadDBDao;
    HermandadDB hermandadDB;
    PasosDBDao pasosDBDao;
    PasosDB pasosDB;


    public DynamicFragmentFotos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dynamic_fragment_fotos, container, false);

        long index = getArguments().getLong("arg",0);
        String cat = getArguments().getString("arg1", "Escudo");

        imageView = (ImageView) v.findViewById(R.id.foto);

        //System.out.println("Fragmento con "+getArguments().getLong("arg"));

        switch (cat){
            case "Escudo":
                hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                hermandadDB = hermandadDBDao.load(index);
                Picasso.with(getActivity())
                        .load(hermandadDB.getEscudo())
                        .resize(250, 200)
                        .into(imageView);
                break;
            case "Tunica":
                hermandadDBDao = DatabaseConnection.getHermandadDBDao(getActivity());
                hermandadDB = hermandadDBDao.load(index);
                Picasso.with(getActivity())
                        .load(hermandadDB.getFotoTunica())
                        .resize(250, 200)
                        .into(imageView);
                break;
            case "Paso":
                pasosDBDao = DatabaseConnection.getPasosDBDao(getActivity());
                pasosDB = pasosDBDao.load(index);
                Picasso.with(getActivity())
                        .load(pasosDB.getFotoPaso())
                        .resize(250, 200)
                        .into(imageView);
                break;
            case "Llamador":
                pasosDBDao = DatabaseConnection.getPasosDBDao(getActivity());
                pasosDB = pasosDBDao.load(index);
                Picasso.with(getActivity())
                        .load(pasosDB.getLlamador())
                        .resize(250, 200)
                        .into(imageView);
                break;
        }
        return v;
    }

}

package com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ammacias.quizzcofrade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragmentFotos extends Fragment {


    public DynamicFragmentFotos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic_fragment_fotos, container, false);
    }

}

package com.example.ammacias.quizzcofrade.Recycler.FragmentsDinamicos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragmentMarcha extends Fragment {

    TextView t;
    public DynamicFragmentMarcha() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dynamic_fragment_marcha, container, false);

        Bundle args = getArguments();
        int index = args.getInt("index", 0);

        t = (TextView)v.findViewById(R.id.marchaText);

        t.setText("He pasado: "+index);
        return v;
    }

}

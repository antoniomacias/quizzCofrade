package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.DetalleActivity.DetalleEscudoActivity;
import com.example.ammacias.quizzcofrade.DetalleActivity.DetalleLlamadoresActivity;
import com.example.ammacias.quizzcofrade.DetalleActivity.DetallePasosActivity;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

import java.util.List;

public class PasosActivity extends AppCompatActivity implements ICofrade{
    String cat_elegida="";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasos);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        t = (TextView) findViewById(R.id.titulo);
        cat_elegida = ((Application_vars) getApplicationContext()).getCategoriaElegida();

        if (cat_elegida.contains("Pasos")) {
            t.setText("Pasos");
        }else{
            t.setText("Llamadores");
        }
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }



    @Override
    public void onClickHermandadDB(HermandadDB h) {

    }

    @Override
    public void onClickPasosDB(PasosDB p) {
        Intent i = new Intent(PasosActivity.this, DetallePasosActivity.class);
        i.putExtra("IDPaso", p.getId());
        startActivity(i);
    }

    @Override
    public void onClickMarcha(MarchaDB m) {

    }
}

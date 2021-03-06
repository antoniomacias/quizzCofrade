package com.gabantdev.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gabantdev.quizzcofrade.DetalleActivity.DetallePasosActivity;
import com.gabantdev.quizzcofrade.Interfaces.ICofrade;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.PasosDB;


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

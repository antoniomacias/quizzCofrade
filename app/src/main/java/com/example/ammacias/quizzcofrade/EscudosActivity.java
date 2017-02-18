package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

public class EscudosActivity extends AppCompatActivity implements ICofrade{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();
        //Toast.makeText(this, "Empezar a jugar con la categoría de "+cat_elegida, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClickCategoria(String categoria) {}

    @Override
    public void onClickHermandadDB(HermandadDB h, int pos) {
        Intent i = new Intent(EscudosActivity.this, DetalleActivity.class);
        i.putExtra("IDHermandad", h.getId());
        i.putExtra("posicion", pos);
        startActivity(i);
    }

    @Override
    public void onClickPasosDB(PasosDB p) {}


}

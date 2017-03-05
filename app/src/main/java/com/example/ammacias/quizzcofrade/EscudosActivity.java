package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ammacias.quizzcofrade.DetalleActivity.DetalleEscudoActivity;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.List;

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

   /* @Override
<<<<<<< HEAD
    public void onClickHermandadDB(HermandadDB h, List<HermandadDB> l, int pos) {
        Intent i = new Intent(EscudosActivity.this, DetalleActivity.class);
=======*/
    public void onClickHermandadDB(HermandadDB h, List<HermandadDB> l, int pos) {
        Intent i = new Intent(EscudosActivity.this, DetalleEscudoActivity.class);
//>>>>>>> refs/remotes/origin/master
        i.putExtra("IDHermandad", h.getId());
        i.putExtra("listaDesordenada", Parcels.wrap(l));
        i.putExtra("posicion", pos);
        startActivity(i);
    }

    @Override
    public void onClickPasosDB(PasosDB p) {}

    @Override
    public void onClickMarcha(MarchaDB m) {

    }
}

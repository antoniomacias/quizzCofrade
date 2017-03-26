package com.gabantdev.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.gabantdev.quizzcofrade.Clases.Usuario;
import com.gabantdev.quizzcofrade.DetalleActivity.DetalleEscudoActivity;
import com.gabantdev.quizzcofrade.Interfaces.ICofrade;
import com.gabantdev.quizzcofrade.Utils.Application_vars;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.PasosDB;


public class EscudosActivity extends AppCompatActivity implements ICofrade{
    Boolean recarga;
    TextView t;
    String cat_elegida;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        t = (TextView) findViewById(R.id.titulo);
        cat_elegida = ((Application_vars) getApplicationContext()).getCategoriaElegida();

        if (cat_elegida.contains("Escudos")) {
            t.setText("Escudos");
        }else{
            t.setText("Túnicas");
        }


        /*u = ((Application_vars) this.getApplication()).getU();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Usuario logueado: "+u.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //String cat_elegida = ((Application_vars) this.getApplication()).getCategoriaElegida();
        //Toast.makeText(this, "Empezar a jugar con la categoría de "+cat_elegida, Toast.LENGTH_SHORT).show();
        recarga = false;
    }



    @Override
    public void onClickHermandadDB(HermandadDB h/*, List<HermandadDB> l, int pos*/) {
        Toast.makeText(this, ""+h.getNombre(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(EscudosActivity.this, DetalleEscudoActivity.class);
        i.putExtra("IDHermandad", h.getId());
        /*i.putExtra("listaDesordenada", Parcels.wrap(l));
        i.putExtra("posicion", pos);*/
        startActivity(i);
    }

    @Override
    public void onClickPasosDB(PasosDB p) {}

    @Override
    public void onClickMarcha(MarchaDB m) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!recarga){
            //Toast.makeText(this, "Le diste patrás", Toast.LENGTH_SHORT).show();
            recarga=true;
        }else{
            //Toast.makeText(this, "Le diste patrás", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }
    }
}

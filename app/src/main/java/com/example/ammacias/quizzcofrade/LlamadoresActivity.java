package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ammacias.quizzcofrade.DetalleActivity.DetalleLlamadoresActivity;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.MarchaDB;
import com.example.ammacias.quizzcofrade.localdb.PasosDB;

public class LlamadoresActivity extends AppCompatActivity  implements ICofrade {
    Boolean recarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamadores);
        recarga = false;

    }
    @Override
    public void onClickPasosDB(PasosDB p) {
        Intent i = new Intent(LlamadoresActivity.this, DetalleLlamadoresActivity.class);
        i.putExtra("IDPaso", p.getId());
        startActivity(i);
    }
    @Override
    public void onClickHermandadDB(HermandadDB h) {

    }

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

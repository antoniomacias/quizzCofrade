package com.gabantdev.quizzcofrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gabantdev.quizzcofrade.DetalleActivity.DetalleMarchaActivity;
import com.gabantdev.quizzcofrade.Interfaces.ICofrade;
import com.gabantdev.quizzcofrade.localdb.HermandadDB;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;
import com.gabantdev.quizzcofrade.localdb.PasosDB;


public class MarchaActivity extends AppCompatActivity implements ICofrade{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcha);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    }

    @Override
    public void onClickMarcha(MarchaDB m) {
        Intent i = new Intent(MarchaActivity.this, DetalleMarchaActivity.class);
        i.putExtra("IDMarcha", m.getId());
        startActivity(i);
    }
}

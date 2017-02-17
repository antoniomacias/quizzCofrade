package com.example.ammacias.quizzcofrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.HermandadDBDao;
import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imageView =(ImageView)findViewById(R.id.fotoDetalle);
        Intent i = getIntent();
        Long id = i.getExtras().getLong("IDHermandad");

        HermandadDBDao hermandadDBDao = DatabaseConnection.getHermandadDBDao(this);
        System.out.println("ID: "+id);
        //System.out.println("id parse: "+Long.parseLong(id));
        System.out.println(hermandadDBDao.load(id).getEscudo());

        Picasso.with(this)
                .load(hermandadDBDao.load(id).getEscudo())
                .resize(250, 200)
                .into(imageView);
    }
}

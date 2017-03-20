package com.example.ammacias.quizzcofrade.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.localdb.RankingDB;

import java.util.List;

/**
 * Created by gabri_neno on 13/03/2017.
 */
public class RankingAdapter extends ArrayAdapter<RankingDB> {

    private Context ctx;
    private int layoutItem;
    private List<RankingDB> values;

    public RankingAdapter(Context context, int resource, List<RankingDB> objects) {
        super(context, resource, objects);
        ctx = context;
        layoutItem = resource;
        values = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(ctx).inflate(layoutItem, parent, false);

        // 1. Rescato el elemento actual que estoy dibujando
        // mediante el parámetro position que recibo en este método
        RankingDB current = values.get(position);

        // 2. Obtener del layout todos los componentes visuales
        // que tengo que modificar
        TextView nick =  (TextView)v.findViewById(R.id.nick_ranking);
        TextView puntuacion =  (TextView)v.findViewById(R.id.puntuacion_ranking);
        TextView fecha =  (TextView)v.findViewById(R.id.fecha_ranking);

        // 3. Aplico los datos del listado sobre los componentes visuales
        nick.setText(current.getNombre()+" "+current.getApellidos());
        puntuacion.setText(String.valueOf(current.getAciertos()));
        fecha.setText(current.getFecha());

        return v;
    }
}

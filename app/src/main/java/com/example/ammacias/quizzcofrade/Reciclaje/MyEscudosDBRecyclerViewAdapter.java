package com.example.ammacias.quizzcofrade.Reciclaje;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ammacias.quizzcofrade.EscudosActivity;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.Utils.Application_vars;
import com.example.ammacias.quizzcofrade.localdb.DatabaseConnection;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDB;
import com.example.ammacias.quizzcofrade.localdb.UsuariosHermandadesDBDao;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String} and makes a call to the
 * specified {@link ICofrade}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEscudosDBRecyclerViewAdapter extends RecyclerView.Adapter<MyEscudosDBRecyclerViewAdapter.ViewHolder> {

    private final List<HermandadDB> mValues;
    private final ICofrade mListener;
    private Context ctx;
    String cat_elegida;


    public MyEscudosDBRecyclerViewAdapter(Context ctx, List<HermandadDB> items, ICofrade listener) {
        this.ctx = ctx;
        mValues = items;
        mListener = listener;
        cat_elegida = ((Application_vars) ctx.getApplicationContext()).getCategoriaElegida();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_escudosdb_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        if (cat_elegida.contains("Escudos")) {
            if(checkAcertado(mValues.get(position))){
                Picasso.with(ctx)
                        .load((mValues.get(position).getEscudo()))
                        .placeholder(R.drawable.star)
                        .resize(250,200)
                        .transform(new BlurTransformation(ctx, 25, 1))
                        .into(holder.mIdView);
            }else{
                Picasso.with(ctx)
                        .load((mValues.get(position).getEscudo()))
                        .placeholder(R.drawable.star)
                        .resize(250, 200)
                        .into(holder.mIdView);
            }
        }else{ // if otra categoría q no sea Escudos
            if(checkAcertado(mValues.get(position))){
                Picasso.with(ctx)
                        .load(mValues.get(position).getFotoTunica())
                        .placeholder(R.drawable.loading)
                        .resize(250,200)
                        .transform(new BlurTransformation(ctx, 25, 1))
                        .into(holder.mIdView);

            }else {
                Picasso.with(ctx)
                        .load(mValues.get(position).getFotoTunica())
                        .placeholder(R.drawable.loading)
                        .resize(250, 200)
                        .into(holder.mIdView);
            }
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClickHermandadDB(holder.mItem, mValues, position);
                }
            }
        });
    }

    private Boolean checkAcertado(HermandadDB hermandadDB) {
        Boolean res = false;
        // if HermandadDB está en la tabla intermedia, es porque está acertado y devuelve True
        UsuariosHermandadesDBDao usuariosHermandadesDBDao =
                DatabaseConnection.getUsuariosHermandadesDBDao(ctx);
        List<UsuariosHermandadesDB> lista = usuariosHermandadesDBDao.loadAll();
        for(UsuariosHermandadesDB uh : lista){
            if(uh.getIdHermandad() == hermandadDB.getId() && uh.getCategoria().equals(cat_elegida)){
                res = true;
            }
        }
        return res;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public HermandadDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.foto);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}

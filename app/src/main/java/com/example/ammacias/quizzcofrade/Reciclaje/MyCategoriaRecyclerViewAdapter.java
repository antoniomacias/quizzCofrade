package com.example.ammacias.quizzcofrade.Reciclaje;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.R;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String} and makes a call to the
 * specified {@link ICofrade}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCategoriaRecyclerViewAdapter extends RecyclerView.Adapter<MyCategoriaRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final ICofrade mListener;
    private Context ctx;

    public MyCategoriaRecyclerViewAdapter(Context context, List<String> items, ICofrade listener) {
        ctx = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_categoria_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre.setText(mValues.get(position).toString()); //
        switch (mValues.get(position).toString()){
            case "Tunicas":
                Picasso.with(ctx)
                        .load("http://semanasantasegovia.com/wp-content/uploads/2014/08/soy_cofrade1.png")
                        .resize(250, 200)
                        .into(holder.imagenCat);
                break;
            case "Pasos":
                Picasso.with(ctx)
                        .load("http://4.bp.blogspot.com/-Ik1MjNdNMf8/VNpxme9ARqI/AAAAAAAALBc/CcorJJLi7Z4/s1600/logo.png")
                        .resize(250, 200)
                        .into(holder.imagenCat);
                break;
            case "Escudos":
                Picasso.with(ctx)
                        .load("http://www.nasbc.org/AsbccImages/Question.png")
                        .resize(250, 200)
                        .into(holder.imagenCat);
                break;
            case "Random":
                Picasso.with(ctx)
                        .load("http://www.nasbc.org/AsbccImages/Question.png")
                        .resize(250, 200)
                        .into(holder.imagenCat);
                break;

        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickCategoria(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre;
        public final ImageView imagenCat;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre = (TextView) view.findViewById(R.id.nombre);
            imagenCat = (ImageView) view.findViewById(R.id.imagenCat);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}

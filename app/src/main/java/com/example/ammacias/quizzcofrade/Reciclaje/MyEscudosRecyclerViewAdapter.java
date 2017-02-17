package com.example.ammacias.quizzcofrade.Reciclaje;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;
import com.example.ammacias.quizzcofrade.R;
import com.example.ammacias.quizzcofrade.localdb.HermandadDB;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String} and makes a call to the
 * specified {@link ICofrade}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEscudosRecyclerViewAdapter extends RecyclerView.Adapter<MyEscudosRecyclerViewAdapter.ViewHolder> {

    private final List<HermandadDB> mValues;
    private final ICofrade mListener;
    private Context ctx;

    public MyEscudosRecyclerViewAdapter(Context ctx, List<HermandadDB> items, ICofrade listener) {
        this.ctx = ctx;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_juego_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // TODO: Pintar con Picasso
        Picasso.with(ctx)
                .load((mValues.get(position).getEscudo()))
                .resize(250, 200)
                .into(holder.mIdView);
        //holder.mIdView.setImageResource(Integer.parseInt(mValues.get(position).toString()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickHermandadDB(holder.mItem);
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

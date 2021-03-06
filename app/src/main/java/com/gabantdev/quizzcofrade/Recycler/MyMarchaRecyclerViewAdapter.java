package com.gabantdev.quizzcofrade.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gabantdev.quizzcofrade.Clases.Marcha;
import com.gabantdev.quizzcofrade.Interfaces.ICofrade;

import com.gabantdev.quizzcofrade.R;
import com.gabantdev.quizzcofrade.localdb.MarchaDB;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Marcha} and makes a call to the
 * specified {@link ICofrade}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMarchaRecyclerViewAdapter extends RecyclerView.Adapter<MyMarchaRecyclerViewAdapter.ViewHolder> {

    private final List<MarchaDB> mValues;
    private final ICofrade mListener;

    String cat_elegida;

    public MyMarchaRecyclerViewAdapter(List<MarchaDB> items, ICofrade listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_marcha_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        int numero = (int) (Math.random() * 4) + 1;
        switch (numero){
            case 1:
                holder.imageViewAudio.setImageResource(R.drawable.marcha1);
                break;
            case 2:
                holder.imageViewAudio.setImageResource(R.drawable.marcha2);
                break;
            case 3:
                holder.imageViewAudio.setImageResource(R.drawable.marcha3);
                break;
            case 4:
                holder.imageViewAudio.setImageResource(R.drawable.marcha4);
                break;
            default:
                holder.imageViewAudio.setImageResource(R.drawable.marcha1);
                break;
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickMarcha(holder.mItem);
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
        public final ImageView imageViewAudio;
        public MarchaDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewAudio = (ImageView) view.findViewById(R.id.imageViewAudio);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

package com.example.ammacias.quizzcofrade;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ammacias.quizzcofrade.Interfaces.ICofrade;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String} and makes a call to the
 * specified {@link ICofrade}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStringRecyclerViewAdapter extends RecyclerView.Adapter<MyStringRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final ICofrade mListener;

    public MyStringRecyclerViewAdapter(List<String> items, ICofrade listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_string_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        System.out.println("1"+mValues.get(2));
        System.out.println(mValues.size());
        //System.out.println(holder.nom);
        holder.nombreMarcha.setText(mValues.get(position));
        holder.imageViewAudio.setImageResource(R.drawable.emptystar);

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
        public final TextView nombreMarcha;
        public final ImageView imageViewAudio;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombreMarcha = (TextView) view.findViewById(R.id.nombreMarcha);
            imageViewAudio = (ImageView) view.findViewById(R.id.imageViewAudio);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

package com.example.next_gen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {


    private List<Bitmap> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected_position = 0;


    ItemRecyclerViewAdapter(Context context, List<Bitmap> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cloth_object, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap clthimg = mData.get(position);
        holder.cloth_img.setImageBitmap(clthimg);
        holder.itemView.setBackgroundColor(selected_position == position ? Color.GRAY : Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cloth_img;

        ViewHolder(View itemView) {
            super(itemView);
            cloth_img = itemView.findViewById(R.id.cloth);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            view.isSelected();
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();

            notifyItemChanged(selected_position);
        }

    }

    Bitmap getItem(int id) {
        return mData.get(id);
    }

    void itemClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
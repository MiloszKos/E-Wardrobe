package com.example.next_gen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private List<String> mData;
    private List<String> mData2;
    private List<Bitmap> mData4;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected_position = 0;


    MyRecycleViewAdapter(Context context, List<String> data, List<String> data2, List<Bitmap> data4) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mData2 = data2;
        this.mData4 = data4;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position);
        String category = mData2.get(position);
        Bitmap clthimg = mData4.get(position);
        holder.myTextView.setText(name);
        holder.myTextView2.setText(category);
        holder.cloth_img.setImageBitmap(clthimg);

        holder.itemView.setBackgroundColor(selected_position == position ? Color.GRAY : Color.WHITE);


    }


    @Override
    public int getItemCount() {
        return mData.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myTextView2;
        ImageView cloth_img;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.cloth_name);
            myTextView2 = itemView.findViewById(R.id.category);
            cloth_img = itemView.findViewById(R.id.imageViewFlag);
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


    String getItem(int id) {
        return mData.get(id);
    }
    String getItem2(int id) {
        return mData2.get(id);
    }
    Bitmap getItem4(int id) {
        return mData4.get(id);
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
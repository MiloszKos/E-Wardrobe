package com.example.next_gen;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SetsRecycleViewAdapter extends RecyclerView.Adapter<SetsRecycleViewAdapter.ViewHolder> {


    private List<Bitmap> mData;
    private List<Bitmap> mData2;
    private List<Bitmap> mData3;
    private List<Bitmap> mData4;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected_position = 0;


    SetsRecycleViewAdapter(Context context, List<Bitmap> data, List<Bitmap> data2, List<Bitmap> data3, List<Bitmap> data4) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mData2 = data2;
        this.mData3 = data3;
        this.mData4 = data4;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_sets_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap clthimg = mData.get(position);
        Bitmap clthimg2 = mData2.get(position);
        Bitmap clthimg3 = mData3.get(position);
        Bitmap clthimg4 = mData4.get(position);
        holder.cloth_img.setImageBitmap(clthimg);
        holder.cloth_img2.setImageBitmap(clthimg2);
        holder.cloth_img3.setImageBitmap(clthimg3);
        holder.cloth_img4.setImageBitmap(clthimg4);



    }

    @Override
    public int getItemCount() {
        return mData.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cloth_img, cloth_img2, cloth_img3, cloth_img4;

        ViewHolder(View itemView) {
            super(itemView);
            cloth_img = itemView.findViewById(R.id.cloth1);
            cloth_img2 = itemView.findViewById(R.id.cloth2);
            cloth_img3 = itemView.findViewById(R.id.cloth3);
            cloth_img4 = itemView.findViewById(R.id.cloth4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            view.isSelected();
            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();

            notifyItemChanged(selected_position);
        }

    }

    Bitmap getItem(int id) {
        return mData.get(id);
    }
    Bitmap getItem2(int id) {
        return mData2.get(id);
    }
    Bitmap getItem3(int id) {
        return mData3.get(id);
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
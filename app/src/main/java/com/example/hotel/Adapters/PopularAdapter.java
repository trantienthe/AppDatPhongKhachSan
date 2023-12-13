package com.example.hotel.Adapters;

import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.hotel.Activities.DetailActivity;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> implements ListAdapter {
    ArrayList<PopularDomain> items;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.locationTxt.setText(items.get(position).getLocation());
        holder.scoreTxt.setText("" + items.get(position).getScore());

        int drawableResId = holder.itemView.getResources().getIdentifier(items.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(), new GranularRoundedCorners(40, 40, 40, 40))
                .into(holder.pic);

        // Kiểm tra điều kiện guide và cập nhật trạng thái phòng
        if ("Phòng đã được đặt".equals(items.get(position).getState())) {
            holder.trangthaiphongTxt.setText("Phòng đã được đặt");
        } else {
            holder.trangthaiphongTxt.setText("");
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object", items.get(position));
            holder.itemView.getContext().startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt, locationTxt, scoreTxt, trangthaiphongTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            locationTxt = itemView.findViewById(R.id.locationTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.picImg);
            trangthaiphongTxt = itemView.findViewById(R.id.trangthaiphongTxt);
        }
    }

}

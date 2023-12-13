package com.example.hotel.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Domains.CategoryDomain;
import com.example.hotel.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {
    ArrayList<CategoryDomain> items;


    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),
                "drawable", holder.itemView.getContext().getPackageName());



        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.picImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView picImg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            picImg = itemView.findViewById(R.id.catImg);
        }
    }
}
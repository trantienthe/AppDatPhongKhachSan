package com.example.hotel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Activities.DetailActivity;
import com.example.hotel.Domains.RoomDomain;
import com.example.hotel.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private List<RoomDomain> roomList;
    private Context context;

    public RoomAdapter(List<RoomDomain> roomList, Context context) {
        this.roomList = roomList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomDomain room = roomList.get(position);
        // Assuming that the imageResId is a String representing a resource name
        int imageResId = context.getResources().getIdentifier(
                room.getPic(), "drawable", context.getPackageName());

        holder.imageView.setImageResource(imageResId);
        holder.textPhong1.setText(room.getTitle());

        // Set click listener for the "Chi tiết phòng" button if needed
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                // Start the DetailActivity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textPhong1;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textPhong1 = itemView.findViewById(R.id.textPhong1);
            button = itemView.findViewById(R.id.btnChiTiet);
        }
    }
}


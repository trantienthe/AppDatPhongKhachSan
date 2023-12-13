package com.example.hotel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Domains.BookingDomain;
import com.example.hotel.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private List<BookingDomain> bookings;

    public BookingAdapter(List<BookingDomain> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingDomain booking = bookings.get(position);

        // Gán dữ liệu vào các TextView trong ViewHolder
        holder.tenphong.setText(booking.getTenChuPhong());
        holder.nhanphong.setText(booking.getCheckIn());
        holder.traphong.setText(booking.getCheckOut());
        holder.datphong.setText(booking.getNight());
        holder.nguoidat.setText(booking.getNguoiDat());
        holder.cho.setText(booking.getPeople());
        holder.txtEmail.setText(booking.getEmail());
        holder.sdt.setText(booking.getSdt());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tenphong, nhanphong, traphong, datphong, sdt, txtEmail, cho, nguoidat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenphong = itemView.findViewById(R.id.tenphong);
            nhanphong = itemView.findViewById(R.id.nhanphong);
            traphong = itemView.findViewById(R.id.traphong);
            datphong = itemView.findViewById(R.id.datphong);
            cho = itemView.findViewById(R.id.cho);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            sdt = itemView.findViewById(R.id.sdt);
            nguoidat = itemView.findViewById(R.id.nguoidat);
        }
    }
}

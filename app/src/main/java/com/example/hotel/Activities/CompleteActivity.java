package com.example.hotel.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Adapters.BookingAdapter;
import com.example.hotel.Adapters.PopularAdapter;
import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.Domains.BookingDomain;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.R;

import java.util.ArrayList;
import java.util.List;

public class CompleteActivity extends AppCompatActivity {
    private BookingDomain item;
    private TextView tenphong, nhanphong, traphong,datphong, sdt, txtEmail, cho, nguoidat;
    private ArrayList<BookingDomain> bookingList;
    private ImageView imgComplete;
    private CreateDatabase dbHelper;
    private RecyclerView.Adapter adapterBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        RecyclerView recyclerView = findViewById(R.id.viewBooking);
        initView();

        recyclerView = findViewById(R.id.viewBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new CreateDatabase(this);
        List<BookingDomain> bookingDomains = dbHelper.getBookingDataByEmail();

        adapterBooking = new BookingAdapter(bookingDomains);
        recyclerView.setAdapter(adapterBooking);

        Button btnBackHome = findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để trở về MainActivity
                backToHome();
            }
        });
    }

    public void setVariable2(){
        item = (BookingDomain) getIntent().getSerializableExtra("object");

        if (item != null) {
            tenphong.setText(item.getTenChuPhong());
            nhanphong.setText(item.getCheckIn());
            traphong.setText(item.getCheckOut());
            datphong.setText(item.getNight());
            nguoidat.setText(item.getNguoiDat());
            cho.setText(item.getPeople());
            txtEmail.setText(item.getEmail());
            sdt.setText(item.getSdt());
        } else {
            // Xử lý trường hợp nếu item là null, ví dụ: hiển thị thông báo lỗi hoặc ghi log
        }
    }

    private void backToHome() {
        Intent intent = new Intent(CompleteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void initView(){
        tenphong = findViewById(R.id.tenphong);
        nhanphong = findViewById(R.id.nhanphong);
        traphong = findViewById(R.id.traphong);
        datphong = findViewById(R.id.datphong);
        cho = findViewById(R.id.cho);
        txtEmail = findViewById(R.id.txtEmail);
        sdt = findViewById(R.id.sdt);
        nguoidat = findViewById(R.id.nguoidat);

    }

}
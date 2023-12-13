package com.example.hotel.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Adapters.CategoryAdapter;
import com.example.hotel.Adapters.PopularAdapter;
import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.Domains.CategoryDomain;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private ArrayList<PopularDomain> items;
    private ImageView imgComplete;
    private CreateDatabase dbHelper;
    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy Hoten từ Intent
        Intent intent = getIntent();
        if (intent.hasExtra("hoten")) {
            String hoten = intent.getStringExtra("hoten");

            // Hiển thị Hoten lên TextView HienThiHoten
            TextView textViewHienThiHoten = findViewById(R.id.HienThiHoten);
            textViewHienThiHoten.setText(hoten);
        }

        EditText searchTxt = findViewById(R.id.searchTxt);
        ImageView btnSearch = findViewById(R.id.btnSearch);

        //tim kiem ten phong
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchTxt.getText().toString().trim();
                PopularDomain matchedRoom = searchRoom(searchText);

                if (matchedRoom != null) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("roomDetails", matchedRoom);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy phòng với tên: " + searchText, Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbHelper = new CreateDatabase(this);
        dbHelper.addInitialData();
        List<PopularDomain> popularDomains = dbHelper.getAllDetailRooms();
        items = new ArrayList<>(popularDomains);

        // Chuyển đến RoomActivity khi kích vào see all
        TextView seeAll = findViewById(R.id.seeall);
        imgComplete = findViewById(R.id.complete);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                startActivity(intent);
            }
        });
        initRecyclerView();


        // Chuyển đến IntroActivity khi nhấn vào hình ảnh có id là home
        ImageView imgHome = findViewById(R.id.home);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        // Chuyển đến DangKyActivity khi nhấn vào hình ảnh có id là DangKy
        ImageView dangky = findViewById(R.id.DangNhap);
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        // Chuyển đến DangKyActivity khi nhấn vào hình ảnh có id là DangNhap
        ImageView dangnhap = findViewById(R.id.DangKy);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });


        // Nhấn exit để đăng xuất
        ImageView imgExit = findViewById(R.id.exit);
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa họ tên từ SharedPreferences
                clearHotenFromPreferences();

                // Tạo Intent để chuyển đến màn hình IntroActivity
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);

                // Xóa tất cả các activity khác khỏi stack và đặt IntroActivity làm activity mới nhất
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // Khởi chạy Intent
                startActivity(intent);

                // Kết thúc activity hiện tại
                finish();
            }
        });

        // Chuyển đến CompleteActivity khi nhấn vào hình ảnh có id là home
        ImageView history = findViewById(R.id.complete);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompleteActivity.class);
                startActivity(intent);
            }
        });

        imgComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CompleteActivity.class);
                startActivity(it);
            }
        });


    }

    private void clearHotenFromPreferences() {
        // Đặt trạng thái đăng nhập là false
        setLoggedInStatus(false);
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("hoten");
        editor.apply();
    }
    private void setLoggedInStatus(boolean isLoggedIn) {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
    private PopularDomain searchRoom(String searchText) {
        for (PopularDomain room : items) {
            if (room.getTitle() != null && room.getTitle().equalsIgnoreCase(searchText.trim())) {
                return room;
            }
        }
        return null;
    }
    private void initRecyclerView(){
        //danh sach phong
        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Tránh tạo một ArrayList mới ở đây
        //adapterPopular = new PopularAdapter(items);
        if (adapterPopular == null) {
            adapterPopular = new PopularAdapter(items);
            recyclerViewPopular.setAdapter(adapterPopular);
        } else {
            // Nếu adapter đã được tạo trước đó, chỉ cần cập nhật dữ liệu mới
            adapterPopular.notifyDataSetChanged();
        }

        ArrayList<CategoryDomain> catsList = new ArrayList<>();
        catsList.add(new CategoryDomain("Giới Thiệu", "gt"));
        catsList.add(new CategoryDomain("Nội Quy", "nq"));
        catsList.add(new CategoryDomain("Hỗ Trợ", "suport"));
        catsList.add(new CategoryDomain("Ghi chú", "note"));

        recyclerViewCategory = findViewById(R.id.view_cat);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterCat = new CategoryAdapter(catsList);
        recyclerViewCategory.setAdapter(adapterCat);
    }
}
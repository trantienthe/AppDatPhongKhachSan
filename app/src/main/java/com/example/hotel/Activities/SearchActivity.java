package com.example.hotel.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.Adapters.PopularAdapter;
import com.example.hotel.Adapters.RoomAdapter;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.Domains.RoomDomain;
import com.example.hotel.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        PopularDomain roomDetails = (PopularDomain) intent.getSerializableExtra("roomDetails");

        RecyclerView recyclerView = findViewById(R.id.viewSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Kiểm tra xem dữ liệu có tồn tại hay không
        if (roomDetails != null) {
            // Ánh xạ ListView và tạo danh sách kết quả tìm kiếm
//            RecyclerView recyclerView = findViewById(R.id.viewSearch);
            ArrayList<PopularDomain> searchResultsList = new ArrayList<>();

            // Thêm dữ liệu vào danh sách kết quả
            searchResultsList.add(roomDetails);

            // Khởi tạo adapter và đặt adapter cho ListView
            PopularAdapter popularAdapter = new PopularAdapter(searchResultsList);
            recyclerView.setAdapter(popularAdapter);
        } else {
            // Xử lý trường hợp dữ liệu không tồn tại, có thể hiển thị thông báo hoặc thực hiện xử lý khác
            Toast.makeText(this, "Dữ liệu không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

}

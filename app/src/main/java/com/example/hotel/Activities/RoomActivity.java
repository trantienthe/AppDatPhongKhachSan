package com.example.hotel.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Adapters.PopularAdapter;
import com.example.hotel.Adapters.RoomAdapter;
import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.Domains.RoomDomain;
import com.example.hotel.R;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular;
    private CreateDatabase dbHelper;
    private ArrayList<PopularDomain> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        dbHelper = new CreateDatabase(this);
        List<PopularDomain> popularDomains = dbHelper.getAllDetailRooms();
        items = new ArrayList<>(popularDomains);

        RecyclerView recyclerView = findViewById(R.id.viewRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Tránh tạo một ArrayList mới ở đây
        //adapterPopular = new PopularAdapter(items);
        if (adapterPopular == null) {
            adapterPopular = new PopularAdapter(items);
            recyclerView.setAdapter(adapterPopular);
        }
        else {
            // Nếu adapter đã được tạo trước đó, chỉ cần cập nhật dữ liệu mới
            adapterPopular.notifyDataSetChanged();
        }
    }

}
package com.example.hotel.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxt, locationTxt, bedTxt, wifiTxt, scoreTxt, descriptionTxt, priceTxt;
    private Button btnBookNow;
    private PopularDomain item;
    private ImageView picImg, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn()) {
                    Intent i = new Intent(DetailActivity.this, OptionActivity.class);
                    i.putExtra("roomName", titleTxt.getText().toString());
                    i.putExtra("price", priceTxt.getText().toString());
                    i.putExtra("picture", item.getPic());
                    startActivity(i);
                } else {
                    // Hiển thị thông báo đăng nhập
                    Toast.makeText(DetailActivity.this, "Vui lòng đăng nhập để đặt phòng.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    public void setVariable(){
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        priceTxt.setText("" + (int) item.getPrice()+ "Vnđ");
        scoreTxt.setText("" + (float) item.getScore());
        locationTxt.setText(item.getLocation());
        bedTxt.setText(item.getBed() + " " + "Bed");
        descriptionTxt.setText(item.getDescription());

        if(item.isWifi()){
            wifiTxt.setText("Wifi");
        }
        else
        {
            wifiTxt.setText("No-Wifi");
        }

        if ("PHÒNG ĐÃ ĐẶT".equalsIgnoreCase(item.getState())) {
            btnBookNow.setText("PHÒNG ĐÃ ĐẶT");
            btnBookNow.setBackgroundTintList(getResources().getColorStateList(R.color.black));
            btnBookNow.setEnabled(false);
        } else {
            btnBookNow.setText("ĐẶT PHÒNG");
            btnBookNow.setEnabled(true);
        }


        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtn.setOnClickListener(view -> finish());

    }
    public void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        locationTxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        btnBookNow = findViewById(R.id.btnBookNow);
    }
}
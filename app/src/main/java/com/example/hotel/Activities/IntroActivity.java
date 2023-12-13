package com.example.hotel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        TextView textView3 = findViewById(R.id.textView3);
        ImageView imageView2 = findViewById(R.id.imageView2);

        // Sử dụng onClickListener để bắt sự kiện khi người dùng nhấp vào textView3
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang MainActivity
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                // Kết thúc IntroActivity
                finish();
            }
        });

        // Sử dụng onClickListener để bắt sự kiện khi người dùng nhấp vào imageView2
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang MainActivity
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                // Kết thúc IntroActivity
                finish();
            }
        });
    }
}
package com.example.hotel.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.R;

public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        Button btnDangKy = findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDangKy();
            }
        });
    }

    private void xuLyDangKy() {
        EditText edtEmail = findViewById(R.id.NhapEmailTxt);
        EditText edtMatKhau = findViewById(R.id.NhapMatKhauTxt);
        EditText edtHoTen = findViewById(R.id.NhapHoTenTxt);
        EditText edtSoDienThoai = findViewById(R.id.NhapSdtTxt);

        String email = edtEmail.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        String hoTen = edtHoTen.getText().toString();
        String soDienThoai = edtSoDienThoai.getText().toString();

        themDuLieuVaoTBUser(email, matKhau, hoTen, soDienThoai);
    }

    private void themDuLieuVaoTBUser(String email, String matKhau, String hoTen, String soDienThoai) {
        CreateDatabase createDatabase = new CreateDatabase(this);
        SQLiteDatabase db = createDatabase.open();

        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TB_User_EmailUser, email);
        values.put(CreateDatabase.TB_User_Password, matKhau);
        values.put(CreateDatabase.TB_User_Hoten, hoTen);
        values.put(CreateDatabase.TB_User_SoDienThoai, soDienThoai);

        long newRowId = db.insert(CreateDatabase.TB_User, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            // Chuyển hướng đến MainActivity
            Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
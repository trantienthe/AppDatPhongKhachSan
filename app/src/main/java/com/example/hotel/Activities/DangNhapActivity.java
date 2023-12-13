package com.example.hotel.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.R;

public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        // Lấy reference đến các thành phần trên giao diện
        EditText edtEmail = findViewById(R.id.NhapEmailDNTxt);
        EditText edtMatKhau = findViewById(R.id.NhapMatKhauDNTxt);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);

        // Xử lý sự kiện khi nhấn nút Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String matKhau = edtMatKhau.getText().toString();

                if (isValidCredentials(email, matKhau)) {
                    // Gọi hàm để lấy Hoten từ cơ sở dữ liệu
                    String hoten = getHotenFromEmail(email);
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    // Đặt trạng thái đăng nhập là true
                    setLoggedInStatus(true);

                    Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                    intent.putExtra("hoten", hoten);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại. Kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLoggedInStatus(boolean isLoggedIn) {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
    // Phương thức kiểm tra tài khoản và mật khẩu từ cơ sở dữ liệu
    private boolean isValidCredentials(String email, String password) {
        CreateDatabase createDatabase = new CreateDatabase(this);
        SQLiteDatabase db = createDatabase.getReadableDatabase();

        // Truy vấn cơ sở dữ liệu để kiểm tra tài khoản và mật khẩu
        String query = "SELECT * FROM " + CreateDatabase.TB_User +
                " WHERE " + CreateDatabase.TB_User_EmailUser + " = ? AND " +
                CreateDatabase.TB_User_Password + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }

    private String getHotenFromEmail(String email) {
        CreateDatabase createDatabase = new CreateDatabase(this);
        SQLiteDatabase db = createDatabase.getReadableDatabase();

        // Truy vấn cơ sở dữ liệu để lấy Hoten từ email
        String query = "SELECT " + CreateDatabase.TB_User_Hoten +
                " FROM " + CreateDatabase.TB_User +
                " WHERE " + CreateDatabase.TB_User_EmailUser + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        String hoten = "";

        if (cursor.moveToFirst()) {
            hoten = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_User_Hoten));
        }

        cursor.close();
        db.close();

        return hoten;
    }
}
package com.example.hotel.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hotel.Database.CreateDatabase;
import com.example.hotel.Domains.PopularDomain;
import com.example.hotel.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private String checkIn,checkOut,people,night,roomName,Price,Picture;
    private TextView dayCheckIn, dayCheckOut,customer,room, price,demDp;
    private ImageView imgRoom;
    private Button Submit;
    private CreateDatabase dbHelper;
    private ArrayList<PopularDomain> items;
    private EditText Ten,Ho,email,sdt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new CreateDatabase(this);
        items = (ArrayList<PopularDomain>) dbHelper.getAllDetailRooms();
        intView();
        Intent i = getIntent();
        checkIn = i.getStringExtra("checkIn");
        checkOut = i.getStringExtra("checkOut");
        roomName = i.getStringExtra("roomName");
        Price = i.getStringExtra("price");
        Picture =i.getStringExtra("picturePath");
        ArrayList<String> selectedOptions = i.getStringArrayListExtra("selectedOptions");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Chuyển chuỗi thành LocalDate
        LocalDate ngayCheckIn = LocalDate.parse(checkIn, formatter);
        LocalDate ngayCheckOut = LocalDate.parse(checkOut, formatter);

        String people = TextUtils.join(", ", selectedOptions);
        // Tính số đêm đặt phòng
        long soDemDatPhong = ChronoUnit.DAYS.between(ngayCheckIn, ngayCheckOut);

        String cleanedPrice = Price.replaceAll("[^0-9]", "");
        int giatien = Integer.parseInt(cleanedPrice);
        int tongtien = (int) (soDemDatPhong * giatien);
        // Chuyển đổi kiểu long thành chuỗi
        String soDemDatPhongStr = String.valueOf(soDemDatPhong);
        demDp.setText(soDemDatPhongStr);
        dayCheckIn.setText(checkIn);
        dayCheckOut.setText(checkOut);
        customer.setText("Khách: "+people );
        room.setText(roomName);
        price.setText(String.valueOf(tongtien) +"vnđ");
        int drawableResId = getResources().getIdentifier(Picture, "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(imgRoom);

        Submit = findViewById(R.id.buttonSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();

            }
        });
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn gửi?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveBookingToDatabase(); // Lưu vào cơ sở dữ liệu

                        PopularDomain selectedRoom = getSelectedRoom();
                        if (selectedRoom != null) {
                            dbHelper.updateRoomState(selectedRoom.getMP(), "booking");
                            Intent i = new Intent(MainActivity2.this,MainActivity.class);
                            startActivity(i);
                        }
                        // Hiển thị thông báo thành công
                        Toast.makeText(MainActivity2.this, "Bạn đã đặt phòng thành công", Toast.LENGTH_SHORT).show();
                        // Quay lại MainActivity
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Người dùng nhấp vào nút Không, không làm gì hoặc quay lại MainActivity nếu cần
                    }
                })
                .show();
    }

    private void saveBookingToDatabase() {
        // Lấy thông tin từ các TextView và EditText
        String tenKhachHang = Ten.getText().toString();
        String emailKhachHang = email.getText().toString();
        String sdtKhachHang = sdt.getText().toString();
        TextView txtCustomor = findViewById(R.id.txtCustomor);
        String additionalInfo = txtCustomor.getText().toString();
        TextView txtPrice = findViewById(R.id.txtPrice);
        String giaPhong = txtPrice.getText().toString();
        TextView txtNameRoom = findViewById(R.id.txtNameRoom);
        String tenPhong = txtNameRoom.getText().toString();

        // Gọi phương thức chèn vào cơ sở dữ liệu
        CreateDatabase dbHelper = new CreateDatabase(this);
        dbHelper.insertBooking(emailKhachHang, tenKhachHang, night, checkIn, checkOut, giaPhong,
                additionalInfo, sdtKhachHang, tenPhong);
    }
    private  void intView(){
        dayCheckIn = findViewById(R.id.txtDateStart);
        dayCheckOut = findViewById(R.id.txtDateEnd);
        customer = findViewById(R.id.txtCustomor);
        room = findViewById(R.id.txtNameRoom);
        price =findViewById(R.id.txtPrice);
        imgRoom =findViewById(R.id.imgRoom);
        Ten = findViewById(R.id.editTextName);
        //Ho= findViewById(R.id.editTextLastName);
        email =findViewById(R.id.editTextTextEmailAddress);
        sdt = findViewById(R.id.editTextPhone);
        demDp = findViewById(R.id.sodemdp);
    }
    private PopularDomain getSelectedRoom() {
        if (items != null) {
            // Lặp qua danh sách items và xác định phòng được chọn dựa trên điều kiện
            for (PopularDomain detailRoom : items) {
                // Thực hiện logic để xác định phòng được chọn
                // Ví dụ: Giả sử phòng được chọn nếu state khác "booking"
                if (!"booking".equals(detailRoom.getState())) {
                    return detailRoom;
                }
            }
        }

        return null;// Trả về null nếu không có phòng nào được chọn
    }
}
package com.example.hotel.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hotel.Domains.BookingDomain;
import com.example.hotel.Domains.PopularDomain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class CreateDatabase extends SQLiteOpenHelper {


    public static String TB_User = "USER";
    public static String TB_DetailRoom = "DETAILROOM";
    public static String TB_BookRoom = "BOOKROOM";

    public static String TB_User_Id = "ID";
    public static String TB_User_EmailUser = "EmailUser";
    public static String TB_User_Password = "PASSWORD";
    public static String TB_User_Hoten = "Hoten";
    public static String TB_User_SoDienThoai = "SoDienThoai";

    public static String TB_DetailRoom_MP = "MaPhong";
    public static String TB_DetailRoom_Ten = "Ten";
    public static String TB_DetailRoom_Gia = "Gia";
    public static String TB_DetailRoom_Anh = "Anh";
    public static String TB_DetailRoom_DienTich = "DienTich";
    public static String TB_DetailRoom_DiaChi = "DiaChi";
    public static String TB_DetailRoom_MoTa = "MoTa";
    public static String TB_DetailRoom_Giuong = "Giuong";
    public static String TB_DetailRoom_State = "State";

    public static String TB_BookRoom_MaPhong = "MaPhong";
    public static String TB_BookRoom_Email = "Email";
    public static String TB_BookRoom_NguoiDat = "NguoiDat";
    public static String TB_BookRoom_Night = "Night";
    public static String TB_BookRoom_CheckIn = "CheckIn";
    public static String TB_BookRoom_CheckOut = "CheckOut";
    public static String TB_BookRoom_People = "People";
    public static String TB_BookRoom_Sdt = "sdt";
    public static String TB_BookRoom_TongTien = "Tongtien";
    public static String TB_BookRoom_TenChuPhong = "TenChuPhong";



    public CreateDatabase( Context context) {
        super(context, "AppHotel", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUser = "CREATE TABLE " + TB_User + "(" + TB_User_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_User_EmailUser + " TEXT, " + TB_User_Password + " TEXT, "
                + TB_User_Hoten + " TEXT, "
                + TB_User_SoDienThoai + " TEXT)";

        String tbDetailRoom = "CREATE TABLE " + TB_DetailRoom + " (" + TB_DetailRoom_MP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_DetailRoom_Ten + " TEXT, " + TB_DetailRoom_Gia + " TEXT, " + TB_DetailRoom_Anh + " TEXT, "
                + TB_DetailRoom_DienTich + " TEXT, " + TB_DetailRoom_DiaChi + " TEXT, " + TB_DetailRoom_MoTa + " TEXT, "
                + TB_DetailRoom_Giuong + " TEXT, " + TB_DetailRoom_State + " TEXT)";

        String tbBooking = "CREATE TABLE " + TB_BookRoom + "(" + TB_BookRoom_MaPhong + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BookRoom_Email + " TEXT, " + TB_BookRoom_NguoiDat + " TEXT, " + TB_BookRoom_Night + " TEXT, "
                + TB_BookRoom_CheckIn + " TEXT, " + TB_BookRoom_CheckOut + " TEXT, " + TB_BookRoom_People + " TEXT, "
                + TB_BookRoom_Sdt + " TEXT, " + TB_BookRoom_TongTien + " TEXT, " + TB_BookRoom_TenChuPhong + " TEXT)";

        String insertUser = "INSERT INTO " + TB_User + "(" + TB_User_EmailUser + ", " + TB_User_Password + ", "
                + TB_User_Hoten + ", " + TB_User_SoDienThoai + ") VALUES (?, ?, ?, ?)";

        String insertDetailRoom1 = "INSERT INTO " + TB_DetailRoom + " VALUES (null, 'Superior 2 giường', '100000', 'pic1', '50 sqm', 'Tp Đà Nẵng', 'Chính sách huỷ.\\nBất kỳ việc huỷ phòng nào ghi nhận được trong vòng 7 ngày trước ngày đến sẽ phải trả khoản tiền đêm đầu tiên.\\nKhông đến khách sạn hoặc chỗ nghỉ sẽ được giải quyết như là Vắng Mặt và sẽ phải trả một khoản tiền 100%% giá trị đặt phòng(Quy định của khách sạn', 'Double bed', 'Available')";
        String insertDetailRoom2 = "INSERT INTO " + TB_DetailRoom + " VALUES (null, 'Deluxe 4 người Hướng phố', '100000', 'pic2', '50 sqm', 'Tp Đà Nẵng', 'Chính sách huỷ.\\nBất kỳ việc huỷ phòng nào ghi nhận được trong vòng 7 ngày trước ngày đến sẽ phải trả khoản tiền đêm đầu tiên.\\nKhông đến khách sạn hoặc chỗ nghỉ sẽ được giải quyết như là Vắng Mặt và sẽ phải trả một khoản tiền 100%% giá trị đặt phòng(Quy định của khách sạn', 'Double bed', 'Available')";

        db.execSQL(tbUser);
        db.execSQL(tbDetailRoom);
        db.execSQL(tbBooking);
        db.execSQL(insertUser);
        db.execSQL(insertDetailRoom1);
        db.execSQL(insertDetailRoom2);
    }
    // Phương thức để chèn một người dùng vào bảng USER


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
    public void addInitialData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Thêm dữ liệu vào bảng phòng
//        insertDetailRoom(db, "Superior 2 giường", "350000", "pic1", "50 sqm", "Tp Đà Nẵng", "Chính sách huỷ.ất kỳ việc huỷ phòng nào ghi nhận được trong vòng 7 ngày trước ngày đến sẽ phải trả khoản tiền đêm đầu tiên.Không đến khách sạn hoặc chỗ nghỉ sẽ được giải quyết như là Vắng Mặt và sẽ phải trả một khoản tiền 100%% giá trị đặt phòng(Quy định của khách sạn", "Double bed", "Available");
//        insertDetailRoom(db, "Deluxe 4 người Hướng phố", "700000", "pic2", "50 sqm", "Tp Đà Nẵng", "Chính sách huỷ.Bất kỳ việc huỷ phòng nào ghi nhận được trong vòng 7 ngày trước ngày đến sẽ phải trả khoản tiền đêm đầu tiên.Không đến khách sạn hoặc chỗ nghỉ sẽ được giải quyết như là Vắng Mặt và sẽ phải trả một khoản tiền 100%% giá trị đặt phòng(Quy định của khách sạn", "Giường Đơn", "Available");
//        insertDetailRoom(db, "Phòng Deluxe có 2 giường King (Deluxe Room-2King Beds)", "9500000", "phong3", "2m5", "Tp Đà Nẵng", "Chính sách huỷ.Bất kỳ việc huỷ phòng nào ghi nhận được trong vòng 7 ngày trước ngày đến sẽ phải trả khoản tiền đêm đầu tiên.Không đến khách sạn hoặc chỗ nghỉ sẽ được giải quyết như là Vắng Mặt và sẽ phải trả một khoản tiền 100%% giá trị đặt phòng(Quy định của khách sạn", "Giường Lớn", "Available");

        db.close();
    }
    public long insertDetailRoom(SQLiteDatabase db, String ten, String gia, String anh, String dienTich, String diaChi, String moTa, String giuong, String state) {
        // SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TB_DetailRoom_Ten, ten);
        values.put(TB_DetailRoom_Gia, gia);
        values.put(TB_DetailRoom_Anh, anh);
        values.put(TB_DetailRoom_DienTich, dienTich);
        values.put(TB_DetailRoom_DiaChi, diaChi);
        values.put(TB_DetailRoom_MoTa, moTa);
        values.put(TB_DetailRoom_Giuong, giuong);
        values.put(TB_DetailRoom_State, state);

        long newRowId = db.insert(TB_DetailRoom, null, values);


        return newRowId;
    }
    public void updateRoomState(int roomId, String newState) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TB_DetailRoom_State, newState);

        // Cập nhật trạng thái của phòng với ID tương ứng
        db.update(TB_DetailRoom, values, TB_DetailRoom_MP + " = ?", new String[]{String.valueOf(roomId)});

        db.close();
    }
    // Phương thức để chèn dữ liệu vào bảng tbBooking
    public void insertBooking(String email, String name, String night,
                              String checkIn, String checkOut, String additionalInfo, String phone,
                              String giaPhong, String tenPhong) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Tính số đêm đặt phòng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate ngayCheckIn = LocalDate.parse(checkIn, formatter);
        LocalDate ngayCheckOut = LocalDate.parse(checkOut, formatter);
        long soDemDatPhong = ChronoUnit.DAYS.between(ngayCheckIn, ngayCheckOut);

        ContentValues values = new ContentValues();
        values.put(TB_BookRoom_Email, email);
        values.put(TB_BookRoom_NguoiDat, name);
        values.put(TB_BookRoom_Night, String.valueOf(soDemDatPhong));
        values.put(TB_BookRoom_CheckIn, checkIn);
        values.put(TB_BookRoom_CheckOut, checkOut);
        values.put(TB_BookRoom_People, additionalInfo);
        values.put(TB_BookRoom_TongTien, giaPhong);
        values.put(TB_BookRoom_TenChuPhong, tenPhong);
        values.put(TB_BookRoom_Sdt, phone);

        db.insert(TB_BookRoom, null, values);
        db.close();
    }

    // Phương thức để lấy danh sách chi tiết phòng từ bảng DETAILROOM
    @SuppressLint("Range")
    public List<PopularDomain> getAllDetailRooms() {
        List<PopularDomain> detailRooms = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TB_DetailRoom;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                PopularDomain popularDomain = new PopularDomain();

                popularDomain.setMP(cursor.getInt(cursor.getColumnIndex(TB_DetailRoom_MP)));
                popularDomain.setTitle(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_Ten)));
                popularDomain.setPrice(cursor.getInt(cursor.getColumnIndex(TB_DetailRoom_Gia)));
                popularDomain.setPic(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_Anh)));
                popularDomain.setBed(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_Giuong)));
                popularDomain.setDescription(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_MoTa)));
                popularDomain.setLocation(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_DiaChi)));
                popularDomain.setState(cursor.getString(cursor.getColumnIndex(TB_DetailRoom_State)));
                popularDomain.setScore(cursor.getDouble(cursor.getColumnIndex(TB_DetailRoom_DienTich)));

                // Thêm các trường khác
                detailRooms.add(popularDomain);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return detailRooms;
    }

    @SuppressLint("Range")
    public List<BookingDomain> getBookingDataByEmail()  {
        List<BookingDomain> bookings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TB_BookRoom;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                BookingDomain bookingDomain = new BookingDomain();

                bookingDomain.setMaPhong(cursor.getInt(cursor.getColumnIndex(TB_BookRoom_MaPhong)));
                bookingDomain.setEmail(cursor.getString(cursor.getColumnIndex(TB_BookRoom_Email)));
                bookingDomain.setNguoiDat(cursor.getString(cursor.getColumnIndex(TB_BookRoom_NguoiDat)));
                bookingDomain.setNight(cursor.getString(cursor.getColumnIndex(TB_BookRoom_Night)));
                bookingDomain.setCheckIn(cursor.getString(cursor.getColumnIndex(TB_BookRoom_CheckIn)));
                bookingDomain.setCheckOut(cursor.getString(cursor.getColumnIndex(TB_BookRoom_CheckOut)));
                bookingDomain.setPeople(cursor.getString(cursor.getColumnIndex(TB_BookRoom_People)));
                bookingDomain.setSdt(cursor.getString(cursor.getColumnIndex(TB_BookRoom_Sdt)));
                bookingDomain.setTongTien(cursor.getString(cursor.getColumnIndex(TB_BookRoom_TongTien)));
                bookingDomain.setTenChuPhong(cursor.getString(cursor.getColumnIndex(TB_BookRoom_TenChuPhong)));

                // Add other fields as needed

                bookings.add(bookingDomain);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookings;
    }


}

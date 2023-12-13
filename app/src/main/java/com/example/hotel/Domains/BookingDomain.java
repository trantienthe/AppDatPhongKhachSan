package com.example.hotel.Domains;

public class BookingDomain {
    private int maPhong;
    private String email;
    private String nguoiDat;
    private String night;
    private String checkIn;
    private String checkOut;
    private String people;
    private String sdt;
    private String tongTien;
    private String tenChuPhong;


    public BookingDomain() {
        this.maPhong = maPhong;
        this.email = email;
        this.nguoiDat = nguoiDat;
        this.night = night;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.people = people;
        this.sdt = sdt;
        this.tongTien = tongTien;
        this.tenChuPhong = tenChuPhong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNguoiDat() {
        return nguoiDat;
    }

    public void setNguoiDat(String nguoiDat) {
        this.nguoiDat = nguoiDat;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenChuPhong() {
        return tenChuPhong;
    }

    public void setTenChuPhong(String tenChuPhong) {
        this.tenChuPhong = tenChuPhong;
    }
}

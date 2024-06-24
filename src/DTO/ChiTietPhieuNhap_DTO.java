package DTO;

import java.util.Date;

public class ChiTietPhieuNhap_DTO {
    private String maPhieuNhap;
    private String maSach;
    private int soLuong;
    private double donGia; // Đơn giá từng cuốn sách
    private double tongTien; // Tổng tiền cho mỗi chi tiết phiếu nhập

    // Constructor
    public ChiTietPhieuNhap_DTO(String maPhieuNhap
    		, String maSach,  int soLuong, double donGia, double tongTien) {
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
 
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public ChiTietPhieuNhap_DTO() {
		// TODO Auto-generated constructor stub
	}


    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}

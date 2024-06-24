package DTO;

public class ChiTietHoaDon_DTO {
   
    private String maHoaDon;
    private String maSach;
    private String tenSach;
    private int soLuong;
    private double donGia;
    private double giamGia;
    private double thanhTien;

    // Constructor
    public ChiTietHoaDon_DTO(String maHoaDon, String maSach, String tenSach, int soLuong,
            double donGia, double giamGia, double thanhTien) {
       
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon_DTO() {
		// TODO Auto-generated constructor stub
	}

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
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

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}

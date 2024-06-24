package DTO;

import java.util.Date;

public class NhanVien_DTO {
    private String maNhanVien;
    private String ho;
    private String ten;
    private String soDienThoai;
    private Date ngaySinh;
    private String gioiTinh;
    private String maChucVu;
    private double luong;

    // Constructor
    public NhanVien_DTO(String maNhanVien, String ho, String ten, 
    		String soDienThoai, Date ngaySinh, String gioiTinh, 
    		String maChucVu, double luong) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maChucVu = maChucVu;
        this.luong = luong;
    }

    public NhanVien_DTO() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

	public String getMaChucVu() {
		return maChucVu;
	}

	public void setMaChucVu(String maChucVu) {
		this.maChucVu = maChucVu;
	}

	public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
    
    

}

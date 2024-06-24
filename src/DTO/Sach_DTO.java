package DTO;


public class Sach_DTO {
    private String maSach;
    private String maNXB;
    private String maTheLoai;
    private String maTacGia;
    private String tenSach;
    private int namXuatBan;
    private int soLuong;
    private double donGia;
    private String hinhAnh ; 

    // Constructors
    public Sach_DTO() {
    }

    public Sach_DTO(String maSach, String maNXB,
    		String maTheLoai, String maTacGia, String tenSach, int namXuatBan, 
    		int soLuong, double donGia,  String hinhAnh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maNXB = maNXB;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
        this.hinhAnh = hinhAnh ; 
    }

    // Getters and Setters
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

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
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

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
    
}

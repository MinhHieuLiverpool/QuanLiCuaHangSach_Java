package DTO;

import java.util.Date;

public class NhaCungCap_DTO {
    private String maNhaCungCap;
    private String ho;
    private String ten;
    private String soDienThoai;
    private String diaChi;
    

    // Constructor
    public NhaCungCap_DTO(String maNhaCungCap, String ho, String ten, 
    		String soDienThoai,  String diaChi) {
        this.maNhaCungCap = maNhaCungCap;
        this.ho = ho;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public NhaCungCap_DTO() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getmaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setmaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
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


    public String getdiaChi() {
        return diaChi;
    }

    public void setdiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

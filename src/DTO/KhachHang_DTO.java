package DTO;


import java.util.Date;

public class KhachHang_DTO {
    private String maKhachHang;
    private String hoKhachHang;
    private String tenKhachHang;
    private String soDienThoai_KH;
    private Date ngaySinh_KH;
    private String gioiTinh_KH;
    private float tongChi;


    // Constructor
    public KhachHang_DTO(String maKhachHang, String hoKhachHang, String tenKhachHang,
                        String soDienThoai_KH, Date ngaySinh_KH, String gioiTinh_KH,
                         float tongChi) {
        this.maKhachHang = maKhachHang;
        this.hoKhachHang = hoKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai_KH = soDienThoai_KH;
        this.ngaySinh_KH = ngaySinh_KH;
        this.gioiTinh_KH = gioiTinh_KH;
        this.tongChi = tongChi;
    }

    public KhachHang_DTO() {
        // TODO Auto-generated constructor stub
    }

    // Getters and setters


    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoKhachHang() {
        return hoKhachHang;
    }

    public void setHoKhachHang(String hoKhachHang) {
        this.hoKhachHang = hoKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai_KH() {
        return soDienThoai_KH;
    }

    public void setSoDienThoai_KH(String soDienThoai_KH) {
        this.soDienThoai_KH = soDienThoai_KH;
    }

    public Date getNgaySinh_KH() {
        return ngaySinh_KH;
    }

    public void setNgaySinh_KH(Date ngaySinh_KH) {
        this.ngaySinh_KH = ngaySinh_KH;
    }

    public String getGioiTinh_KH() {
        return gioiTinh_KH;
    }

    public void setGioiTinh_KH(String gioiTinh_KH) {
        this.gioiTinh_KH = gioiTinh_KH;
    }

    public float getTongChi() {
        return tongChi;
    }

    public void setTongChi(float tongChi) {
        this.tongChi = tongChi;
    }
}


package DTO;

import java.util.Date;

public class CTGG_DTO {
    private String maCTGG;
    private String tenCTGG; // Trường mới
    private Date thoiGianBatDau;
    private Date thoiGianKetThuc;

 
    public CTGG_DTO(String maCTGG, String tenCTGG, Date thoiGianBatDau, Date thoiGianKetThuc) {
        this.maCTGG = maCTGG;
        this.tenCTGG = tenCTGG;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    // Getter and Setter methods
    public String getMaCTGG() {
        return maCTGG;
    }

    public void setMaCTGG(String maCTGG) {
        this.maCTGG = maCTGG;
    }

    public String getTenCTGG() {
        return tenCTGG;
    }

    public void setTenCTGG(String tenCTGG) {
        this.tenCTGG = tenCTGG;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }
}

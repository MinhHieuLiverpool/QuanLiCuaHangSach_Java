package DTO;
public class ChiTietCTGG_DTO {
    private String maChiTietCTGG;
    private String maCTGG;
    private String maSach;
    private int phanTramGiamGia;

    public ChiTietCTGG_DTO(String maChiTietCTGG, String maCTGG, int phanTramGiamGia, String maSach) {
        this.maChiTietCTGG = maChiTietCTGG;
        this.maCTGG = maCTGG;
        this.maSach = maSach;
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public String getMaChiTietCTGG() {
        return maChiTietCTGG;
    }

    public void setMaChiTietCTGG(String maChiTietCTGG) {
        this.maChiTietCTGG = maChiTietCTGG;
    }

    public String getMaCTGG() {
        return maCTGG;
    }

    public void setMaCTGG(String maCTGG) {
        this.maCTGG = maCTGG;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

	public int getPhanTramGiamGia() {
		return phanTramGiamGia;
	}

	public void setPhanTramGiamGia(int phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}

  
}

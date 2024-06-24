package DTO;

public class ChucVu_DTO {
    private String tenChucVu;
    private String maChucVu ;
    
	/**
	 * 
	 */
	public ChucVu_DTO() {
	}
	public ChucVu_DTO(String tenChucVu, String maChucVu) {
		this.tenChucVu = tenChucVu;
		this.maChucVu = maChucVu;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public String getMaChucVu() {
		return maChucVu;
	}
	public void setMaChucVu(String maChucVu) {
		this.maChucVu = maChucVu;
	} 
  
}

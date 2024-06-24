package BUS;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.CTGG_DAO;
import DAO.ChiTietPhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;

public class ChiTietPhieuNhap_BUS {
	 private ChiTietPhieuNhap_DAO chitietphieunhapDAO = new ChiTietPhieuNhap_DAO();
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTietPhieuNhap) throws HeadlessException, SQLException {	
    	 
    	 if (chiTietPhieuNhap.getMaPhieuNhap().isEmpty() ) {
  		  JOptionPane.showMessageDialog(null, "Vui lòng chọn mã phiếu nhập");
         return false;
    	 }
    	 if (chiTietPhieuNhap.getDonGia()== 0) {
     		  JOptionPane.showMessageDialog(null, "Vui lòng nhập đơn giá");
            return false;
      	 }
    	 if (chiTietPhieuNhap.getSoLuong()== 0) {
    		  JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng" );
           return false;
     	 }
    	
        // Gọi phương thức từ lớp DAO để thêm chi tiết phiếu nhập
		boolean success = ChiTietPhieuNhap_DAO.addChiTietPhieuNhap(chiTietPhieuNhap);
		
		return success; 
    }
    public boolean isDuplicateMaCTGG(String maCTGG) throws SQLException {
        return chitietphieunhapDAO.countDuplicateMaCTGG(maCTGG) > 0;
    }
    
    public double getTongTienPhieuNhap(String maPhieuNhap) throws SQLException {
        return chitietphieunhapDAO.getTongTienPhieuNhap(maPhieuNhap);
    }
    public boolean updateTongTienPhieuNhap(String maPhieuNhap, double tongTienMoi) {
        boolean updateSuccess = false;
        try {
            updateSuccess = chitietphieunhapDAO.updateTongTienPhieuNhap(maPhieuNhap, tongTienMoi);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần thiết
        }
        return updateSuccess;
    }
    public static ArrayList<ChiTietPhieuNhap_DTO> getAllChiTietPhieuNhap() {
        ChiTietPhieuNhap_DAO phieuNhapDAO = new ChiTietPhieuNhap_DAO();
        return phieuNhapDAO.getAllChiTietPhieuNhap();
    }
   
    public static boolean updateChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTietPhieuNhap) {
        if (chiTietPhieuNhap == null) {
            throw new IllegalArgumentException("ChiTietPhieuNhap cannot be null");
        }

        return ChiTietPhieuNhap_DAO.updateChiTietPhieuNhap(chiTietPhieuNhap);
    }
    public static ChiTietPhieuNhap_DTO getChiTietPhieuNhap(String maPhieuNhap, String maSach) {
        ChiTietPhieuNhap_DTO chiTiet = null;
        try {
            chiTiet = ChiTietPhieuNhap_DAO.getChiTietPhieuNhap(maPhieuNhap, maSach);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return chiTiet;
    }
    public static boolean deleteChiTietPhieuNhap(String maPhieuNhap, String maSach) {
        return ChiTietPhieuNhap_DAO.deleteChiTietPhieuNhap(maPhieuNhap, maSach);
    }
    public static ChiTietPhieuNhap_DTO getChiTietPhieuNhapById(String maPhieuNhap, String maSach) {
        return ChiTietPhieuNhap_DAO.getChiTietPhieuNhapById(maPhieuNhap, maSach);
    }
    public static double calculateTotalBill(String maphieunhap) {
    	return ChiTietPhieuNhap_DAO.calculateTotalBill(maphieunhap);
    }
    public static int getTotalSoldQuantity() throws SQLException {
    return ChiTietPhieuNhap_DAO.getTotalSoldQuantity();
    }
    
    public static ArrayList<ChiTietPhieuNhap_DTO> layDanhSachChiTietPhieuNhap(String maPhieuNhap) throws SQLException {
        return ChiTietPhieuNhap_DAO.layDanhSachChiTietPhieuNhap(maPhieuNhap);
    }
    
    public static ResultSet searchAndDisplayFromDatabase(String maSach) throws SQLException {
       return ChiTietPhieuNhap_DAO.searchAndDisplayFromDatabase(maSach);
    }
    
 

}

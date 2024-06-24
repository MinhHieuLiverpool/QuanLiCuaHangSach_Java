package BUS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.ChiTietHoaDon_DAO;
import DTO.ChiTietHoaDon_DTO;

public class ChiTietHoaDon_BUS {
	 public static boolean xoaChiTietHoaDon(String maHoaDon, String maSach) throws SQLException {
	        return ChiTietHoaDon_DAO.xoaChiTietHoaDon(maHoaDon, maSach);
	    }
    
    public static boolean updateSoLuongChiTietHoaDon( int soLuongMoi, String maHoaDon, String maSach) throws SQLException {
        return ChiTietHoaDon_DAO.updateSoLuongChiTietHoaDon(soLuongMoi, maHoaDon, maSach);
    }
    
    public static boolean themChiTietHoaDon(ChiTietHoaDon_DTO chiTietHoaDon) throws SQLException {
      
        if (!isValidChiTietHoaDonData(chiTietHoaDon)) {
            JOptionPane.showMessageDialog(null, "Dữ liệu chi tiết hoá đơn không hợp lệ!");
            return false;
        }
        boolean check = ChiTietHoaDon_DAO.themChiTietHoaDon(chiTietHoaDon);
        if (check) {
            JOptionPane.showMessageDialog(null, "Thêm sách vào hóa đơn thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sách vào hóa đơn thất bại");
            return false;
        }
    }
    private static boolean isValidChiTietHoaDonData(ChiTietHoaDon_DTO chiTietHoaDon) {
        return chiTietHoaDon.getSoLuong() > 0 && chiTietHoaDon.getDonGia() >= 0 && chiTietHoaDon.getGiamGia() >= 0 && chiTietHoaDon.getThanhTien() >= 0;
    }
    public static ArrayList<ChiTietHoaDon_DTO> layDanhSachChiTietHoaDon() {
        // Gọi phương thức từ lớp DAO để lấy danh sách chi tiết hóa đơn
        return ChiTietHoaDon_DAO.getAlldetailBills();
    }
    public static int getTotalSoldQuantity() throws SQLException {
       return ChiTietHoaDon_DAO.getTotalSoldQuantity();
    }
    public static ResultSet searchAndDisplayFromDatabase(String maSach) throws SQLException {
        return ChiTietHoaDon_DAO.searchAndDisplayFromDatabase(maSach);
    }
    public static ArrayList<ChiTietHoaDon_DTO> layDanhSachChiTietHoaDon(String maHoaDon) throws SQLException {
    
        return ChiTietHoaDon_DAO.layDanhSachChiTietHoaDon(maHoaDon);
    }
}

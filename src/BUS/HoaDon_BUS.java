package BUS;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.HoaDon_DAO;
import DTO.HoaDon_DTO;

public class HoaDon_BUS {
    private static HoaDon_DAO hoaDonDAO;

    public HoaDon_BUS(Connection connection) {
        hoaDonDAO = new HoaDon_DAO(connection);
    }

    public boolean themHoaDon(HoaDon_DTO hoaDonDTO) throws HeadlessException, SQLException {
 
            if (isValidHoaDon(hoaDonDTO)) {
             
                return HoaDon_DAO.themHoaDon(hoaDonDTO);
            } else {
              
                JOptionPane.showMessageDialog(null, "Thông tin hóa đơn không hợp lệ!");
                return false;
            }
         
    }

    private static boolean isValidHoaDon(HoaDon_DTO hoaDonDTO) throws HeadlessException, SQLException {
        // Kiểm tra các trường thông tin hóa đơn có bị rỗng không
        if (hoaDonDTO.getMaHoaDon().isEmpty() || hoaDonDTO.getMaKhachHang().isEmpty() || 
            hoaDonDTO.getMaNhanVien().isEmpty() || hoaDonDTO.getMaGiamGia().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin hóa đơn!");
            return false;
        }
        if (hoaDonDAO.kiemTraHoaDonTrungLap(hoaDonDTO)) {
            JOptionPane.showMessageDialog(null, "Hóa đơn đã tồn tại!");
            return false;
        }
        
        return true;
    }
    public static boolean xoaHoaDon(String maHoaDon) throws SQLException {
        boolean hasDetails = HoaDon_DAO.hasDetails(maHoaDon);
        if (hasDetails) {
            JOptionPane.showMessageDialog(null, "Không thể xóa vì còn chi tiết hoá đơn liên quan!");
            return false;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Hoá đơn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = HoaDon_DAO.deleteHoaDon(maHoaDon);
            if (deleted) {
                JOptionPane.showMessageDialog(null, "Xóa hoá đơn thành công!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Xóa hoá đơn không thành công!");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Xóa hoá đơn đã được hủy!");
            return false;
        }
    }

    public static boolean updateHoaDon(HoaDon_DTO hoaDon) throws HeadlessException, SQLException {
       
        return HoaDon_DAO.updateHoaDon(hoaDon);
    }

    public static void loadDataIntoComboBox_maHoaDon(JComboBox<String> tenComboBox) throws SQLException {
		
	    ArrayList<HoaDon_DTO> publishers = HoaDon_DAO.layDanhSachHoaDon(); // Lấy danh sách nhà xuất bản từ cơ sở dữ liệu
	    for (HoaDon_DTO publisher : publishers) {
	    	tenComboBox.addItem(publisher.getMaHoaDon()); // Thêm tên nhà xuất bản vào combobox
	    }
}
    public static ArrayList<HoaDon_DTO> layDanhSachHoaDon() throws SQLException {
        // Gọi phương thức từ lớp DAO để lấy danh sách hóa đơn
        return HoaDon_DAO.layDanhSachHoaDon();
    }
    public static int getNextInvoiceNumber() {
    	return HoaDon_DAO.getNextInvoiceNumber();
    }
    

    // Add method to get total sales for the day from HoaDon
    public static double getTotalSalesForDay(Date currentDate) throws SQLException {
        return HoaDon_DAO.getTotalInputForDay(currentDate);
    }
    public static double calculateTotalInput() throws SQLException {
        return HoaDon_DAO.calculateTotalInput();
    }
    public static ResultSet searchAndDisplayFromDatabase
 	 (String maHoaDon, String maKhachHang, String maNhanVien,
 			 String maCTGG, String donGiaTu, String donGiaDen) {
 		    return HoaDon_DAO.searchAndDisplayFromDatabase(maHoaDon, maKhachHang, maNhanVien, maCTGG, donGiaTu, donGiaDen);
     }
    public static Double calculateTotalBill(String maHoaDon) throws SQLException {
      return HoaDon_DAO.calculateTotalBill(maHoaDon);
    }
    public static boolean updateTotalBill(String maHoaDon, Double tongTien) throws SQLException {    
        return HoaDon_DAO.updateTotalBill(maHoaDon, tongTien);
    }
    public static String timMaCTGGTuMaHoaDon(String maHoaDon) {
       return HoaDon_DAO.timMaCTGGTuMaHoaDon(maHoaDon);
    }
    
    public static Date layNgayLapTuMaHoaDon(String maHoaDon) {
       return HoaDon_DAO.layNgayLapTuMaHoaDon(maHoaDon);
    }
    
    public static void capNhatTongTienHoaDon(String maHoaDon, double tongTien) throws SQLException {
      HoaDon_DAO.capNhatTongTienHoaDon(maHoaDon, tongTien); 
    }
}

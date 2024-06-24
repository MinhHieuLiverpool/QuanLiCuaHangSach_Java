package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.HoaDon_DTO;
import DTO.Sach_DTO;
import Database.ConnectionManager;

public class HoaDon_DAO {
	  private static Connection connection = ConnectionManager.openConnection(); 
    public HoaDon_DAO(Connection connection) {
        this.connection = connection;
    }

    // Phương thức để thêm một hóa đơn vào cơ sở dữ liệu
    public static boolean themHoaDon(HoaDon_DTO hoaDon) throws SQLException {
        String query = "INSERT INTO hoadon (maHoaDon, maKhachHang,"
        		+ " maNhanVien, mactgg, ngayLap, tongTien) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getMaKhachHang());
            ps.setString(3, hoaDon.getMaNhanVien());
            ps.setString(4, hoaDon.getMaGiamGia());
            ps.setDate(5, new java.sql.Date(hoaDon.getNgayLap().getTime()));
            ps.setDouble(6, hoaDon.getTongTien());

            ps.executeUpdate();
            return true ; 
        }
    }

    public static ArrayList<HoaDon_DTO> layDanhSachHoaDon() throws SQLException {
        ArrayList<HoaDon_DTO> danhSachHoaDon = new ArrayList<>();
        String query = "SELECT * FROM hoadon";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maHoaDon = rs.getString("maHoaDon");
                String maKhachHang = rs.getString("maKhachHang");
                String maNhanVien = rs.getString("maNhanVien");
                String maGiamGia = rs.getString("mactgg");
                Date ngayLap = rs.getDate("ngayLap");
                double tongTien = rs.getDouble("tongTien");
                HoaDon_DTO hoaDon = new HoaDon_DTO(maHoaDon, maKhachHang, maNhanVien, maGiamGia, ngayLap, tongTien);
                danhSachHoaDon.add(hoaDon);
            }
        }

        return danhSachHoaDon;
    }
   
    public HoaDon_DTO timHoaDonTheoMa(String maHoaDon) {
        HoaDon_DTO hoaDon = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;   
        try {   
            String sql = "SELECT * FROM hoadon WHERE mahoadon= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maHoaDon);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                hoaDon = new HoaDon_DTO(              	
                    resultSet.getString("mahoadon"),
                    resultSet.getString("makhachhang"),
                    resultSet.getString("manhanvien"),
                    resultSet.getString("mactgg"),
                    resultSet.getDate("ngaylap"),
                    resultSet.getDouble("tongtien")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return hoaDon;
    }
    public static void capNhatTongTienHoaDon(String maHoaDon, double tongTien) throws SQLException {
        String sql = "UPDATE hoadon SET tongtien = ? WHERE mahoadon = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Thiết lập các tham số cho câu lệnh SQL
            statement.setDouble(1, tongTien);
            statement.setString(2, maHoaDon);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật tổng tiền của hóa đơn " + maHoaDon);
            } else {
                System.out.println("Không thể cập nhật tổng tiền của hóa đơn " + maHoaDon);
            }
        } 
    }

    public static boolean deleteHoaDon(String maSach) {
        boolean result = false;
        try {
            //openConnection();
            String sql = "DELETE FROM HoaDon WHERE maHoaDon=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, maSach);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        	//ConnectionManager.closeConnection();
        }
        return result;
    }
    public static boolean updateHoaDon(HoaDon_DTO hoaDon) {
        boolean result = false;
        try {
            //openConnection();
            String sql = "UPDATE HoaDon SET maKhachHang=?, maNhanVien=?, mactgg=?, ngayLap=?, tongTien=? WHERE maHoaDon=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, hoaDon.getMaKhachHang());
            stmt.setString(2, hoaDon.getMaNhanVien());
            stmt.setString(3, hoaDon.getMaGiamGia());
            
            // Chuyển đổi ngày từ kiểu java.util.Date sang kiểu java.sql.Date
            stmt.setDate(4, new java.sql.Date(hoaDon.getNgayLap().getTime())); // Chuyển đổi ngày thành kiểu java.sql.Date

            
            stmt.setDouble(5, hoaDon.getTongTien());
            stmt.setString(6, hoaDon.getMaHoaDon());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            //ConnectionManager.closeConnection();
        }
        return result;
    }
    public boolean kiemTraHoaDonTrungLap(HoaDon_DTO hoaDonDTO) throws SQLException {
        String query = "SELECT COUNT(*) FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hoaDonDTO.getMaHoaDon());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    // Nếu số lượng hóa đơn có mã trùng lặp là 1 hoặc nhiều, có hóa đơn trùng lặp
                    return count > 0;
                }
            }
        }
		return false;
    }
    public static double getTotalInputForDay(Date date) throws SQLException {
        double totalInput = 0.0;
            String sql = "SELECT SUM(tongtien) AS total_input FROM hoadon WHERE ngaylap = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDate(1, new java.sql.Date(date.getTime()));
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        totalInput = rs.getDouble("total_input");
                    }
                }
            }
 

        return totalInput;
    }
    public static double calculateTotalInput() throws SQLException {
        double totalInput = 0;

            String query = "SELECT SUM(tongTien) AS total_input FROM hoadon";
            PreparedStatement statement = connection.prepareStatement(query);

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery();

            // Xử lý kết quả
            if (resultSet.next()) {
                totalInput = resultSet.getDouble("total_input");
            }

        return totalInput;
    }
    public static String timMaCTGGTuMaHoaDon(String maHoaDon) {
        String maCTGG = null;
        try {
            String sql = "SELECT maCTGG FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                maCTGG = result.getString("maCTGG");
            }
      
        } catch (SQLException ex) {
            // Xử lý nếu có lỗi khi truy vấn cơ sở dữ liệu
            ex.printStackTrace();
        }
        return maCTGG;
    }

    public static Date layNgayLapTuMaHoaDon(String maHoaDon) {
        Date ngayLap = null;
        try {

            String sql = "SELECT ngayLap FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                ngayLap = result.getDate("ngayLap");
            }
          
        } catch (SQLException ex) {
            // Xử lý nếu có lỗi khi truy vấn cơ sở dữ liệu
            ex.printStackTrace();
        }
        return ngayLap;
    }
    public static boolean hasDetails(String maCTGG) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean hasDetails = false;

            // Chuẩn bị truy vấn SQL
            String query = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE MaHoaDon = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maCTGG);
            resultSet = statement.executeQuery();

            // Xử lý kết quả
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                hasDetails = count > 0;
            }
       
        return hasDetails;
    }
    public static ResultSet searchAndDisplayFromDatabase
  	 (String maHoaDon, String maKhachHang, String maNhanVien,
  			 String maCTGG, String donGiaTu, String donGiaDen) {
  		    
  		        // Bắt đầu truy vấn
  		        String query = "SELECT * FROM hoaDon WHERE";
  		        List<String> conditions = new ArrayList<>();

  		        // Thêm điều kiện vào danh sách nếu giá trị không rỗng
  		        if (!maHoaDon.isEmpty()) {
  		            conditions.add("MaHoaDon = '" + maHoaDon + "'");
  		        }
  		        if (!maKhachHang.isEmpty()) {
  		            conditions.add("MaKhachHang = '" + maKhachHang + "'");
  		        }
  		        if (!maNhanVien.isEmpty()) {
  		            conditions.add("maNhanVien = '" + maNhanVien + "'");
  		        }
  		        if (!maCTGG.isEmpty()) {
  		            conditions.add("maCTGG = '" + maCTGG + "'");
  		        }
  		  
  		        if (!donGiaTu.isEmpty()) {
  		            conditions.add("TongTien >= " + donGiaTu); // Giá từ
  		        }
  		        if (!donGiaDen.isEmpty()) {
  		            conditions.add("TongTien <= " + donGiaDen); // Giá đến
  		        }

  		        // Kết hợp các điều kiện bằng AND
  		        if (!conditions.isEmpty()) {
  		            query += " " + String.join(" AND ", conditions);
  		        } else {
  		            // Nếu không có điều kiện, trả về tất cả dữ liệu
  		            query = "SELECT * FROM hoaDon";
  		        }

  		        // Thực thi truy vấn
  		        Statement statement = null;
  				try {
  					statement = connection.createStatement();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  		        ResultSet resultSet = null;
  				try {
  					resultSet = statement.executeQuery(query);
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}

  			return resultSet ;
      }

    public static Double calculateTotalBill(String maHoaDon) throws SQLException {
        Double totalBill = (double) 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            String query = "SELECT SUM(thanhTien) AS total FROM chitiethoadon WHERE mahoadon = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maHoaDon);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                totalBill = resultSet.getDouble("total");
            }
        return totalBill;
    }
    public static boolean updateTotalBill(String maHoaDon, Double tongTien) throws SQLException {    
        PreparedStatement statement = null;
        boolean updated = false;
        String query = "UPDATE hoadon SET tongtien = ? WHERE mahoadon = ?";
           statement = connection.prepareStatement(query);
            statement.setDouble(1, tongTien);
            statement.setString(2, maHoaDon);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
            }
  
        return updated;
    }
    
    public static int getNextInvoiceNumber() {
        int nextInvoiceNumber = 1; // Default to 1 if there are no records
        try {    
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(maHoaDon) AS max_invoice_number FROM hoaDon";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int maxInvoiceNumber = resultSet.getInt("max_invoice_number");
                nextInvoiceNumber = maxInvoiceNumber + 1; // Increment the max value by 1
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextInvoiceNumber;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import DTO.KhachHang_DTO;
import Database.ConnectionManager;
public class KhachHang_DAO {
    private static Connection connection = ConnectionManager.openConnection();

    public KhachHang_DAO(Connection connection) {
        KhachHang_DAO.connection = connection ;
    }
    public boolean addSach(KhachHang_DTO khachHang) {
        boolean result = false;
        try {
            // openConnection();
            String sql = "INSERT INTO KhachHang (maKhachHang,"
            		+ " hoKhachhang, tenKhachHang, soDienThoai ,"
                    + " ngaySinh, gioiTinh, tongChi ) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, khachHang.getMaKhachHang());
            stmt.setString(2, khachHang.getHoKhachHang());
            stmt.setString(3, khachHang.getTenKhachHang());
            stmt.setString(4, khachHang.getSoDienThoai_KH());
            stmt.setDate(5, new java.sql.Date(khachHang.getNgaySinh_KH().getTime()));
            stmt.setString(6, khachHang.getGioiTinh_KH());
            stmt.setFloat(7, khachHang.getTongChi());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        }
        return result;
    }

    public static boolean deleteKhachHang(String maKhachHang) {
        boolean result = false;
        try {
            //openConnection();
            String sql = "DELETE FROM KhachHang WHERE maKhachHang=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, maKhachHang);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
       //     ConnectionManager.closeConnection();
        }
        return result;
    }
    public static boolean updateKhachHang(KhachHang_DTO khachHang) {
        boolean result = false;
        try {
            //openConnection();
        	String sql = "UPDATE KhachHang SET hoKhachHang=?, "
        	           + "tenKhachHang=?, soDienThoai=?, ngaySinh=?,"
        	           + " gioiTinh=?, tongChi=? WHERE maKhachHang=?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, khachHang.getHoKhachHang());
            stmt.setString(2, khachHang.getTenKhachHang());
            stmt.setString(3, khachHang.getSoDienThoai_KH());
            stmt.setDate(4, new java.sql.Date(khachHang.getNgaySinh_KH().getTime()));
            stmt.setString(5, khachHang.getGioiTinh_KH());
            stmt.setFloat(6, khachHang.getTongChi());
            stmt.setString(7, khachHang.getMaKhachHang()); // Assuming this method exists to get the maKhachHang value

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public static ArrayList<KhachHang_DTO> getAllEmployee() {
        ArrayList<KhachHang_DTO> khachHangList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KhachHang";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang_DTO khachHang = new KhachHang_DTO();
                khachHang.setMaKhachHang(rs.getString("maKhachHang"));
                khachHang.setHoKhachHang(rs.getString("hoKhachHang"));
                khachHang.setTenKhachHang(rs.getString("tenKhachHang"));
                khachHang.setSoDienThoai_KH(rs.getString("soDienThoai"));
                khachHang.setNgaySinh_KH(rs.getDate("ngaySinh"));
                khachHang.setGioiTinh_KH(rs.getString("gioiTinh"));
                khachHang.setTongChi(rs.getFloat("Tongchi"));
                khachHangList.add(khachHang);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        return khachHangList;
    }

  
    public static ResultSet searchAndDisplayFromDatabase(String maNhanVien,
    		String ho, String ten, 
    		String gioiTinh) {
        // Bắt đầu truy vấn
        String query = "SELECT * FROM khachHang WHERE";
        List<String> conditions = new ArrayList<>();

        // Thêm điều kiện vào danh sách nếu giá trị không rỗng
        if (!maNhanVien.isEmpty()) {
            conditions.add("MaKhachHang = '" + maNhanVien + "'");
        }
        if (!ho.isEmpty()) {
            conditions.add("hoKhachHang = '" + ho + "'");
        }
        if (!ten.isEmpty()) {
            conditions.add("tenKhachHang = '" + ten + "'");
        }
        if (!gioiTinh.isEmpty()) {
            conditions.add("gioiTinh = '" + gioiTinh + "'");
        }
        // Kết hợp các điều kiện bằng AND
        if (!conditions.isEmpty()) {
            query += " " + String.join(" AND ", conditions);
        } else {
            // Nếu không có điều kiện, trả về tất cả dữ liệu
            query = "SELECT * FROM khachhang";
        }

        // Thực thi truy vấn và trả về ResultSet
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }
    public static int countTotalKhachHang() throws SQLException {
        int totalEmployees = 0;
            String query = "SELECT COUNT(*) AS total_employees FROM khachhang";
            PreparedStatement statement = connection.prepareStatement(query);

            // Thực thi truy vấn
            ResultSet resultSet = statement.executeQuery();

            // Xử lý kết quả
            if (resultSet.next()) {
                totalEmployees = resultSet.getInt("total_employees");
            }
     
        return totalEmployees;
    }
    public static boolean kiemTraKhachHangTrongHoaDon(String maKhachHang) {
       
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean khachHangTrongHoaDon = false;

        try {
     
            String query = "SELECT COUNT(*) FROM HoaDon WHERE MaKhachHang = ?";
      
            statement = connection.prepareStatement(query);
            statement.setString(1, maKhachHang);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int soLuongHoaDon = resultSet.getInt(1);
                khachHangTrongHoaDon = (soLuongHoaDon > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return khachHangTrongHoaDon;
    }
    public static Map<String, Double> TongChiKhachHang() throws SQLException {
	    Map<String, Double> salaryData = new HashMap<>();
	    String sql = "SELECT makhachhang, tongchi FROM khachhang";
	    try (PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {
	        while (resultSet.next()) {
	            String employeeId = resultSet.getString("makhachhang");
	            double salary = resultSet.getDouble("tongchi");
	            salaryData.put(employeeId, salary);
	        }
	    }
	    return salaryData;
	}
}
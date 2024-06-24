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

import DTO.NhanVien_DTO;
import DTO.Sach_DTO;
import Database.ConnectionManager;
public class NhanVien_DAO {
	private static Connection connection = ConnectionManager.openConnection(); 

	public NhanVien_DAO(Connection connection) {
		NhanVien_DAO.connection = connection ;
	} 
	
	public boolean addSach(NhanVien_DTO nhanVien) {
		boolean result = false;
		try {
       // openConnection();
        String sql = "INSERT INTO NhanVien (maNhanVien, Ho, Ten, soDienThoai ,"
        		+ " ngaySinh, gioiTinh, machucvu,"
        		+ " Luong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nhanVien.getMaNhanVien());
        stmt.setString(2, nhanVien.getHo());
        stmt.setString(3, nhanVien.getTen());
        stmt.setString(4, nhanVien.getSoDienThoai());
        stmt.setDate(5, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
        stmt.setString(6, nhanVien.getGioiTinh());
        stmt.setString(7, nhanVien.getMaChucVu());
        stmt.setDouble(8, nhanVien.getLuong());
      
        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            result = true;
        }
		} catch (SQLException ex) {
        System.out.println(ex);
		} finally {
    //	ConnectionManager.closeConnection();
		}
    return result;
}
		public static boolean delete(String maNhanVien) {
			boolean result = false;
			try {
        //openConnection();
					String sql = "DELETE FROM NhanVien WHERE maNhanVien=?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, maNhanVien);

					int rowsDeleted = stmt.executeUpdate();
					if (rowsDeleted > 0) {
						result = true;
					}
			} catch (SQLException ex) {
					System.out.println(ex);
			} finally {
		//			ConnectionManager.closeConnection();
			}
    return result;
}
public static ArrayList<NhanVien_DTO> getAllEmployee() {
    ArrayList<NhanVien_DTO> nhanVienList = new ArrayList<>();
    try {
        //openConnection();
        String sql = "SELECT * FROM nhanVien";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Tạo đối tượng Sach_DTO từ dữ liệu trong ResultSet
        	NhanVien_DTO nhanVien = new NhanVien_DTO(); 
        	nhanVien.setMaNhanVien(rs.getString("maNhanVien"));
        	nhanVien.setHo(rs.getString("ho"));
        	nhanVien.setTen(rs.getString("ten"));
        	nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
        	nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
        	nhanVien.setGioiTinh(rs.getString("gioiTinh"));
        	nhanVien.setMaChucVu(rs.getString("machucvu"));
        	nhanVien.setLuong(rs.getDouble("luong"));
        	nhanVienList.add(nhanVien);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    } finally {
    //	ConnectionManager.closeConnection();
    }
    return nhanVienList;
}

public static boolean updateNhanVien(NhanVien_DTO nhanVien) {
    boolean result = false;
    try {
        String sql = "UPDATE NhanVien SET ho=?, ten=?, soDienThoai=?, "
                    + "ngaySinh=?, gioiTinh=?, machucvu=?, luong=? WHERE maNhanVien=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nhanVien.getHo());
        stmt.setString(2, nhanVien.getTen());
        stmt.setString(3, nhanVien.getSoDienThoai());
        stmt.setDate(4, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
        stmt.setString(5, nhanVien.getGioiTinh());
        stmt.setString(6, nhanVien.getMaChucVu());
        stmt.setDouble(7, nhanVien.getLuong());
        stmt.setString(8, nhanVien.getMaNhanVien());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            result = true;
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    } finally {
     // Đóng kết nối sau khi sử dụng xong
    }
    return result;
}

public static ResultSet searchAndDisplayFromDatabase(String maNhanVien, String ho, String ten, String machucvu, String gioiTinh, String sapXepTheo, String tangGiam) {
    // Bắt đầu truy vấn
    String query = "SELECT * FROM nhanvien WHERE";
    List<String> conditions = new ArrayList<>();

    // Thêm điều kiện vào danh sách nếu giá trị không rỗng
    if (!maNhanVien.isEmpty()) {
        conditions.add("MaNhanVien LIKE '" + maNhanVien + "%'");
    }
    if (!ho.isEmpty()) {
        conditions.add("ho LIKE '" + ho + "%'");
    }
    if (!ten.isEmpty()) {
        conditions.add("ten LIKE '" + ten + "%'");
    }
    if (!machucvu.isEmpty()) {
        conditions.add("machucvu = '" + machucvu + "'");
    }
    if (!gioiTinh.isEmpty()) {
        conditions.add("gioiTinh = '" + gioiTinh + "'");
    }
    if (!conditions.isEmpty()) {
        query += " " + String.join(" AND ", conditions);
    } else { 
        query = "SELECT * FROM nhanvien";
    }

    try {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    } catch (SQLException e) {
        e.printStackTrace();
        return null; // Trả về null nếu có lỗi xảy ra
    }
}


public static ResultSet getEmployeeByMaNhanVien(String maNhanVien) {
    // Bắt đầu truy vấn
    String query = "SELECT * FROM nhanvien WHERE MaNhanVien = '" + maNhanVien + "'";

    // Thực thi truy vấn và trả về ResultSet
    try {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    } catch (SQLException e) {
        e.printStackTrace();
        return null; // Trả về null nếu có lỗi xảy ra
    }
}

public static int countTotalEmployees() throws SQLException {
    int totalEmployees = 0;

 
        String query = "SELECT COUNT(*) AS total_employees FROM nhanvien";
        PreparedStatement statement = connection.prepareStatement(query);

        // Thực thi truy vấn
        ResultSet resultSet = statement.executeQuery();

        // Xử lý kết quả
        if (resultSet.next()) {
            totalEmployees = resultSet.getInt("total_employees");
        }
 
    return totalEmployees;
}

public static String getMaxTotalSalesByEmployee() throws SQLException {
    Statement stmt = null;
        stmt = connection.createStatement();
        String query = "SELECT manhanvien, SUM(tongtien) AS total_sales FROM hoadon GROUP BY manhanvien ORDER BY total_sales DESC LIMIT 1";
        ResultSet rs = stmt.executeQuery(query);
        String employeeId = "";
        if (rs.next()) {
            employeeId = rs.getString("manhanvien");
         
        }
        return employeeId;
 
}
public static Map<String, Double> retrieveSalaryData( ) throws SQLException {
    Map<String, Double> salaryData = new HashMap<>();
    String sql = "SELECT manhanvien, luong FROM nhanvien";
    try (PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            String employeeId = resultSet.getString("manhanvien");
            double salary = resultSet.getDouble("luong");
            salaryData.put(employeeId, salary);
        }
    }
    return salaryData;
}
}
package DAO;

import DTO.Sach_DTO;
import Database.ConnectionManager;

import java.awt.Image;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;


public class Sach_DAO {
	
	private static Connection connection = ConnectionManager.openConnection(); 

    public Sach_DAO(Connection connection) {
        Sach_DAO.connection = connection ;
    } 
    public boolean addSach(Sach_DTO sach) {
        boolean result = false;
        try {
           // openConnection();
            String sql = "INSERT INTO Sach (maSach, maNXB, maTheLoai, maTacGia ,"
            		+ " tenSach, namXuatBan, soLuong,"
            		+ " donGia, hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sach.getMaSach());
            stmt.setString(2, sach.getMaNXB());
            stmt.setString(3, sach.getMaTheLoai());
            stmt.setString(4, sach.getMaTacGia());
            stmt.setString(5, sach.getTenSach());
            stmt.setInt(6, sach.getNamXuatBan());
            stmt.setInt(7, sach.getSoLuong());
            stmt.setDouble(8, sach.getDonGia());
          
            stmt.setString(9, sach.getHinhAnh());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        	//ConnectionManager.closeConnection();
        }
        return result;
    }

    public static boolean deleteSach(String maSach) {
        boolean result = false;
        try {
            //openConnection();
            String sql = "DELETE FROM Sach WHERE maSach=?";
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
    public static boolean updateSach(Sach_DTO sach) {
        boolean result = false;
        try {
            //openConnection();
            String sql = "UPDATE Sach SET maNXB=?, maTheLoai=?, maTacGia=?, tenSach=?, namXuatBan=?, soLuong=?, donGia=?, hinhAnh=? WHERE maSach=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sach.getMaNXB());
            stmt.setString(2, sach.getMaTheLoai());
            stmt.setString(3, sach.getMaTacGia());
            stmt.setString(4, sach.getTenSach());
            stmt.setInt(5, sach.getNamXuatBan());
            stmt.setInt(6, sach.getSoLuong());
            stmt.setDouble(7, sach.getDonGia());
            stmt.setString(8, sach.getHinhAnh());
            stmt.setString(9, sach.getMaSach());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
    //    	ConnectionManager.closeConnection();
        }
        return result;
    }
    public static ArrayList<Sach_DTO> getAllBooks() {
        ArrayList<Sach_DTO> sachList = new ArrayList<>();
        try {
            //openConnection();
            String sql = "SELECT * FROM Sach";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sach_DTO sach = new Sach_DTO();
                sach.setMaSach(rs.getString("maSach"));
                sach.setMaNXB(rs.getString("maNXB"));
                sach.setMaTheLoai(rs.getString("maTheLoai"));
                sach.setMaTacGia(rs.getString("maTacGia"));
                sach.setTenSach(rs.getString("tenSach"));
                sach.setNamXuatBan(rs.getInt("namXuatBan"));
                sach.setSoLuong(rs.getInt("soLuong"));
                sach.setDonGia(rs.getDouble("donGia"));
                sach.setHinhAnh(rs.getString("hinhAnh"));
                // Thêm sách vào danh sách
                sachList.add(sach);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        //	ConnectionManager.closeConnection();
        }
        return sachList;
    }
    public static ResultSet searchAndDisplayFromDatabase(String maSach, String []maNXBs, String[] maTheLoais, String []maTacGias, String tenSach, String donGiaTu, String donGiaDen)  {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM sach WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (maSach != null && !maSach.isEmpty()) {
            queryBuilder.append(" AND MaSach = ?");
            parameters.add(maSach);
        }
       
        if (maNXBs != null && maNXBs.length > 0) {
            queryBuilder.append(" AND MaNXB IN (").append(String.join(",", Collections.nCopies(maNXBs.length, "?"))).append(")");
            parameters.addAll(Arrays.asList(maNXBs));
        }
        if (maTheLoais != null && maTheLoais.length > 0) {
            queryBuilder.append(" AND MaTheLoai IN (").append(String.join(",", Collections.nCopies(maTheLoais.length, "?"))).append(")");
            parameters.addAll(Arrays.asList(maTheLoais));
        }
        if (maTacGias != null && maTacGias.length > 0) {
            queryBuilder.append(" AND MaTacGia IN (").append(String.join(",", Collections.nCopies(maTacGias.length, "?"))).append(")");
            parameters.addAll(Arrays.asList(maTacGias));
        }
    
        if (tenSach != null && !tenSach.isEmpty()) {
            queryBuilder.append(" AND TenSach LIKE ?");
            parameters.add("%" + tenSach + "%");
        }
        if (donGiaTu != null && !donGiaTu.isEmpty()) {
            queryBuilder.append(" AND DonGia >= ?");
            parameters.add(Double.parseDouble(donGiaTu));
        }
        if (donGiaDen != null && !donGiaDen.isEmpty()) {
            queryBuilder.append(" AND DonGia <= ?");
            parameters.add(Double.parseDouble(donGiaDen));
        }

        String query = queryBuilder.toString();
        
        PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            for (int i = 0; i < parameters.size(); i++) {
                try {
					statement.setObject(i + 1, parameters.get(i));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            try {
				return statement.executeQuery();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
			return null;
        
    }
    public static ResultSet searchAndDisplayFromDatabase(String maSach, String maNXB, String maTheLoai, String maTacGia, String tenSach, String donGiaTu, String donGiaDen) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM sach WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (maSach != null && !maSach.isEmpty()) {
            queryBuilder.append(" AND MaSach = ?");
            parameters.add(maSach);
        }

        if (maNXB != null && !maNXB.isEmpty()) {
            queryBuilder.append(" AND MaNXB = ?");
            parameters.add(maNXB);
        }
        if (maTheLoai != null && !maTheLoai.isEmpty()) {
            queryBuilder.append(" AND MaTheLoai = ?");
            parameters.add(maTheLoai);
        }
        if (maTacGia != null && !maTacGia.isEmpty()) {
            queryBuilder.append(" AND MaTacGia = ?");
            parameters.add(maTacGia);
        }

        if (tenSach != null && !tenSach.isEmpty()) {
            queryBuilder.append(" AND TenSach LIKE ?");
            parameters.add("%" + tenSach + "%");
        }
        if (donGiaTu != null && !donGiaTu.isEmpty()) {
            queryBuilder.append(" AND DonGia >= ?");
            parameters.add(Double.parseDouble(donGiaTu));
        }
        if (donGiaDen != null && !donGiaDen.isEmpty()) {
            queryBuilder.append(" AND DonGia <= ?");
            parameters.add(Double.parseDouble(donGiaDen));
        }

        String query = queryBuilder.toString();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Sach_DTO getSachByMaSach(String maSach) {
        Sach_DTO sach = null;
        try {
            String sql = "SELECT * FROM Sach WHERE maSach = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, maSach);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sach = new Sach_DTO(
                    rs.getString("maSach"),
                    rs.getString("maNXB"),
                    rs.getString("maTheLoai"),
                    rs.getString("maTacGia"),
                    rs.getString("tenSach"),
                    rs.getInt("namXuatBan"),
                    rs.getInt("soLuong"),
                    rs.getDouble("donGia"),
                    rs.getString("hinhAnh")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        }
        return sach;
    }
    public static int getSoLuongByMaSach(String maSach) throws SQLException {
        int soLuong = 0;
        String query = "SELECT soLuong FROM Sach WHERE maSach = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maSach);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    soLuong = rs.getInt("soLuong");
                }
            }
        }
        return soLuong;
    }

    // Phương thức để cập nhật số lượng mới của sách trong cơ sở dữ liệu dựa trên mã sách
    public static void updateSoLuongByMaSach(String maSach, int soLuongMoi) throws SQLException {
        String query = "UPDATE Sach SET soLuong = ? WHERE maSach = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, soLuongMoi);
            ps.setString(2, maSach);
            ps.executeUpdate();
        }
    }
    public static int countTotalQuantityOfBooks() throws SQLException {
        int totalQuantity = 0;
            String query = "SELECT SUM(soLuong) AS total_quantity FROM sach";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            // Xử lý kết quả
            if (resultSet.next()) {
                totalQuantity = resultSet.getInt("total_quantity");
            }

        return totalQuantity;
    }

    public static List<String> getSimilarBookTitles(String inputTitle) throws SQLException {
        List<String> similarTitles = new ArrayList<>();
        String condition = "";
        if (!inputTitle.isEmpty()) {
            condition = "WHERE TenSach LIKE '" + inputTitle + "%'";
        }

        // Tạo câu truy vấn SQL với điều kiện đã xây dựng
        String sql = "SELECT TenSach FROM sach " + condition;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Lặp qua kết quả truy vấn và thêm các tên sách vào danh sách gợi ý
        while (resultSet.next()) {
            String title = resultSet.getString("TenSach");
            similarTitles.add(title);
        }

        return similarTitles;
    }

    public static String getBestSellingBookId() throws SQLException {
        Statement stmt = null;  
            stmt = connection.createStatement();     
            String query = "SELECT masach FROM chitiethoadon ORDER BY soluong DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            String bestSellingBookId = "";
            if (rs.next()) {
                bestSellingBookId = rs.getString("masach");
            }

            return bestSellingBookId;

    }
    public static int getBestSellingQuantity() throws SQLException {
        Statement stmt = null;
        
            stmt = connection.createStatement();
            String query = "SELECT soluong FROM chitiethoadon ORDER BY soluong DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            int bestSellingQuantity = 0;
            if (rs.next()) {
                bestSellingQuantity = rs.getInt("soluong");
            }

            return bestSellingQuantity;

    }
    
    public static double findGiaByMaSach(String maSach) {
        // Thực hiện truy vấn để tìm giá từ mã sách và trả về giá
        double gia = 0.0;
        try {
           // Connection connection = DriverManager.getConnection("your_database_connection_details");
            String query = "SELECT Dongia FROM Sach WHERE maSach = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maSach);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                gia = resultSet.getDouble("Dongia");
            }
            resultSet.close();
            preparedStatement.close();
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gia;
    }
    public static boolean isMaSachUnique(String maSach) {
        boolean unique = true;
        try {
            // Tạo câu truy vấn SQL để kiểm tra mã sách trong cơ sở dữ liệu
            String query = "SELECT COUNT(*) FROM Sach WHERE maSach = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSach);
            ResultSet resultSet = statement.executeQuery();

            // Nếu có bất kỳ dòng nào trả về từ truy vấn, mã sách đã tồn tại và không duy nhất
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    unique = false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return unique;
    }
    public static boolean kiemtraSachcotrongHoaDon(String maKhachHang) {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean khachHangTrongHoaDon = false;

        try {
     
            String query = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE MaSach = ?";
      
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
  public static boolean kiemtraSachcotrongCTGG(String maKhachHang) {
        
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean khachHangTrongHoaDon = false;

        try {
     
            String query = "SELECT COUNT(*) FROM ChiTietCTGG WHERE MaSach = ?";
      
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
  
    
}

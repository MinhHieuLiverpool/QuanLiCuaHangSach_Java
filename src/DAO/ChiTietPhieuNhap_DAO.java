package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.ChiTietHoaDon_DTO;
import DTO.ChiTietPhieuNhap_DTO;
import Database.ConnectionManager;

public class ChiTietPhieuNhap_DAO {
	
	private static Connection connection = ConnectionManager.openConnection(); 
	
    public ArrayList<ChiTietPhieuNhap_DTO> getAllChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhap_DTO> danhSachChiTietPhieuNhap = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM ChiTietPhieuNhap";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {  	
                String maPhieuNhap = resultSet.getString("maPhieuNhap");
                String maSach = resultSet.getString("maSach");
                int soLuong = resultSet.getInt("soLuong");
                double donGia = resultSet.getDouble("donGia");
                double tongTien = resultSet.getDouble("tongTienNhap");
                ChiTietPhieuNhap_DTO chiTietPhieuNhap = new ChiTietPhieuNhap_DTO
                (maPhieuNhap, maSach,  soLuong, donGia, tongTien);
                danhSachChiTietPhieuNhap.add(chiTietPhieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return danhSachChiTietPhieuNhap;
    }

    // Phương thức để thêm một chi tiết phiếu nhập vào cơ sở dữ liệu
    public static boolean addChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTietPhieuNhap) {
        PreparedStatement preparedStatement = null;
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO ChiTietPhieuNhap (maPhieuNhap, maSach,"
            		+ " soLuong, donGia, tongTienNhap) "
            		+ "VALUES ( ?, ?, ?, ?, ? )";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, chiTietPhieuNhap.getMaPhieuNhap());
            preparedStatement.setString(2, chiTietPhieuNhap.getMaSach());
          
            preparedStatement.setInt(3, chiTietPhieuNhap.getSoLuong());
            preparedStatement.setDouble(4, chiTietPhieuNhap.getDonGia());
            preparedStatement.setDouble(5, chiTietPhieuNhap.getTongTien());

            // Thực hiện câu lệnh SQL
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return isSuccess;
    }
    
    public static ArrayList<ChiTietPhieuNhap_DTO> layDanhSachChiTietPhieuNhap(String maPhieuNhap) throws SQLException {
        ArrayList<ChiTietPhieuNhap_DTO> danhSachChiTietPhieuNhap = new ArrayList<>();
        String query = "SELECT * FROM chitietphieunhap WHERE maphieunhap = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maPhieuNhap);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSach = rs.getString("maSach");
                    int soLuong = rs.getInt("soLuong");
                    double donGia = rs.getDouble("donGia");    
                    double thanhTien = rs.getDouble("tongTiennhap");

                    ChiTietPhieuNhap_DTO chiTietHoaDon = new ChiTietPhieuNhap_DTO(
                maPhieuNhap, maSach,
                    		 soLuong, donGia, thanhTien);
                    danhSachChiTietPhieuNhap.add(chiTietHoaDon);
                }
            }
        }

        return danhSachChiTietPhieuNhap;
    }
    public int countDuplicateMaCTGG(String maCTGG) throws SQLException {
        String query = "SELECT COUNT(*) FROM chitietphieunhap WHERE machitietphieunhap = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maCTGG);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }
    public double getTongTienPhieuNhap(String maPhieuNhap) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        double tongTien = 0;
     
            String query = "SELECT SUM(TongTienNhap) FROM ChiTietPhieuNhap WHERE MaPhieuNhap = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maPhieuNhap);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tongTien = resultSet.getDouble(1);
            }
        

        return tongTien;
    }
    public boolean updateTongTienPhieuNhap(String maPhieuNhap, double tongTienMoi) throws SQLException {
        PreparedStatement statement = null;
        boolean updateSuccess = false;

        try {
            String query = "UPDATE PhieuNhap SET TongTien = ? WHERE MaPhieuNhap = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, tongTienMoi);
            statement.setString(2, maPhieuNhap);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                updateSuccess = true;
            }
        } finally {
            // Đóng tài nguyên
            if (statement != null) {
                statement.close();
            }
        }

        return updateSuccess;
    }
    public static int getTotalSoldQuantity() throws SQLException {
        int totalSoldQuantity = 0;
        String query = "SELECT SUM(soLuong) AS totalSold FROM ChiTietPhieuNhap";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalSoldQuantity = rs.getInt("totalSold");
            }
        }
        return totalSoldQuantity;
    }
    public static boolean updateChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTietPhieuNhap) {
        PreparedStatement preparedStatement = null;
        boolean rowUpdated = false;
        try { 
            String query = "UPDATE ChiTietPhieuNhap SET soLuong = ?, tongTienNhap = ? WHERE maPhieuNhap = ? AND maSach = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chiTietPhieuNhap.getSoLuong());
            preparedStatement.setDouble(2, chiTietPhieuNhap.getTongTien());
            preparedStatement.setString(3, chiTietPhieuNhap.getMaPhieuNhap());
            preparedStatement.setString(4, chiTietPhieuNhap.getMaSach());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
    public static boolean deleteChiTietPhieuNhap(String maPhieuNhap, String maSach) {
        PreparedStatement preparedStatement = null;
        boolean rowDeleted = false;
        try {
            String query = "DELETE FROM ChiTietPhieuNhap WHERE maPhieuNhap = ? AND maSach = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maPhieuNhap);
            preparedStatement.setString(2, maSach);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }


    public static double calculateTotalBill(String maPhieuNhap) {
        double totalBill = 0.0;
        try {
            String query = "SELECT SUM(donGia * soLuong) AS total FROM ChiTietPhieuNhap WHERE maPhieuNhap = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maPhieuNhap);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalBill = resultSet.getDouble("total");
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBill;
    }
    public static ChiTietPhieuNhap_DTO getChiTietPhieuNhap(String maPhieuNhap, String maSach) throws SQLException {
        ChiTietPhieuNhap_DTO chiTiet = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
           
            String query = "SELECT * FROM chitietphieunhap WHERE MaPhieuNhap = ? AND MaSach = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maPhieuNhap);
            preparedStatement.setString(2, maSach);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                chiTiet = new ChiTietPhieuNhap_DTO(
                        resultSet.getString("MaPhieuNhap"),
                        resultSet.getString("MaSach"),
                        resultSet.getInt("SoLuong"),
                        resultSet.getDouble("DonGia"),
                        resultSet.getDouble("TongTienNhap")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return chiTiet;
    }
    public static ChiTietPhieuNhap_DTO getChiTietPhieuNhapById(String maPhieuNhap, String maSach) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ChiTietPhieuNhap_DTO chiTietPhieuNhap = null;

        try {
           
            String query = "SELECT * FROM ChiTietPhieuNhap WHERE maPhieuNhap = ? AND maSach = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maPhieuNhap);
            preparedStatement.setString(2, maSach);

            // Thực thi truy vấn và lấy kết quả
            resultSet = preparedStatement.executeQuery();

            // Nếu có kết quả, tạo đối tượng ChiTietPhieuNhap_DTO từ kết quả và trả về nó
            if (resultSet.next()) {
                chiTietPhieuNhap = new ChiTietPhieuNhap_DTO();
                chiTietPhieuNhap.setMaPhieuNhap(resultSet.getString("maPhieuNhap"));
                chiTietPhieuNhap.setMaSach(resultSet.getString("maSach"));
                chiTietPhieuNhap.setSoLuong(resultSet.getInt("soLuong"));
                chiTietPhieuNhap.setDonGia(resultSet.getDouble("donGia"));
                chiTietPhieuNhap.setTongTien(resultSet.getDouble("tongTienNhap"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chiTietPhieuNhap;
    }
    public static ResultSet searchAndDisplayFromDatabase(String maSach) throws SQLException {
        ResultSet resultSet = null;
        String query = "SELECT * FROM ChiTietPhieuNhap WHERE maSach = ?";
        try { 
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSach); 
            resultSet = statement.executeQuery();
        } catch (SQLException e) {      
            e.printStackTrace();
        }
 
        return resultSet;
    }

}


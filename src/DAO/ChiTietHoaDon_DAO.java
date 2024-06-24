package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ChiTietHoaDon_DTO;
import DTO.KhachHang_DTO;
import Database.ConnectionManager;

public class ChiTietHoaDon_DAO {
    private static Connection connection=ConnectionManager.openConnection();  // Đối tượng Connection để thực hiện các thao tác trên cơ sở dữ liệu

    public ChiTietHoaDon_DAO(Connection connection) {
        this.connection = connection;
    }
    public static boolean themChiTietHoaDon(ChiTietHoaDon_DTO chiTietHoaDon) throws SQLException {
        String query = "INSERT INTO chitiethoadon ("
        		+ " maHoaDon, maSach, tenSach,"
        		+ " soLuong, donGia, giamGia, thanhTien)"
        		+ " VALUES ( ?, ?, ?, ?, ?, ?, ? )";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, chiTietHoaDon.getMaHoaDon());
            ps.setString(2, chiTietHoaDon.getMaSach());
            ps.setString(3, chiTietHoaDon.getTenSach());
            ps.setInt(4, chiTietHoaDon.getSoLuong());
            ps.setDouble(5, chiTietHoaDon.getDonGia());
            ps.setDouble(6, chiTietHoaDon.getGiamGia());
            ps.setDouble(7, chiTietHoaDon.getThanhTien());

            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<ChiTietHoaDon_DTO> layDanhSachChiTietHoaDon(String maHoaDon) throws SQLException {
        ArrayList<ChiTietHoaDon_DTO> danhSachChiTietHoaDon = new ArrayList<>();
        String query = "SELECT * FROM chitiethoadon WHERE maHoaDon = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maHoaDon);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    int soLuong = rs.getInt("soLuong");
                    double donGia = rs.getDouble("donGia");
                    double giamGia = rs.getDouble("giamGia");
                    double thanhTien = rs.getDouble("thanhTien");
                    ChiTietHoaDon_DTO chiTietHoaDon = new ChiTietHoaDon_DTO( maHoaDon, maSach, tenSach, soLuong, donGia, giamGia, thanhTien);
                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
            }
        }
        return danhSachChiTietHoaDon;
    }
   

     
    public static ArrayList<ChiTietHoaDon_DTO> getAlldetailBills() {
        ArrayList<ChiTietHoaDon_DTO> chitietList = new ArrayList<>();
        try {
            //openConnection();
            String sql = "SELECT * FROM chitiethoadon";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {  
            	ChiTietHoaDon_DTO chitiet = new ChiTietHoaDon_DTO();
                chitiet.setMaHoaDon(rs.getString("mahoadon"));
                chitiet.setMaSach(rs.getString("masach"));
                chitiet.setTenSach(rs.getString("tensach"));
                chitiet.setSoLuong(rs.getInt("soluong"));
                chitiet.setDonGia(rs.getFloat("dongia"));
                chitiet.setGiamGia(rs.getFloat("giamgia"));
                chitiet.setThanhTien(rs.getFloat("thanhtien"));
                chitietList.add(chitiet);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
           // ConnectionManager.closeConnection();
        }
        return chitietList;
    }
    public float tinhTongGiamGia(ArrayList<ChiTietHoaDon_DTO> chiTietList) {
        float tongGiamGia = 0;
        for (ChiTietHoaDon_DTO chiTiet : chiTietList) {
            tongGiamGia += chiTiet.getGiamGia();
        }
        return tongGiamGia;
    }

    public float tinhTongDonGia(ArrayList<ChiTietHoaDon_DTO> chiTietList) {
        float tongDonGia = 0;
        for (ChiTietHoaDon_DTO chiTiet : chiTietList) {
            tongDonGia += chiTiet.getDonGia();
        }
        return tongDonGia;
    }
    public static int getTotalSoldQuantity() throws SQLException {
        int totalSoldQuantity = 0;  
        String query = "SELECT SUM(soLuong) AS totalSold FROM ChiTietHoaDon";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalSoldQuantity = rs.getInt("totalSold");
            }
        }

        return totalSoldQuantity;
    }
  
    public static boolean checkExistingMaChiTiet(String maChiTiet) throws SQLException {    
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exists = false; 
            String query = "SELECT COUNT(*) AS count FROM chitiethoadon WHERE machitiethoadon = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maChiTiet);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                exists = (count > 0);
            }
          
        return exists;
    }
    public static boolean xoaChiTietHoaDon(String maHoaDon, String maSach) throws SQLException {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaSach = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maHoaDon);
            statement.setString(2, maSach);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
    public static boolean updateSoLuongChiTietHoaDon(int soLuongMoi, String maHoaDon, String maSach) throws SQLException {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ? WHERE MaHoaDon = ? AND maSach = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, soLuongMoi);
            statement.setString(2, maHoaDon);
            statement.setString(3, maSach);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    public static ResultSet searchAndDisplayFromDatabase(String maSach) throws SQLException {
        ResultSet resultSet = null;
        String query = "SELECT * FROM ChiTietHoaDon WHERE maSach = ?";
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


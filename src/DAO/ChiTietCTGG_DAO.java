package DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.CTGG_DTO;
import DTO.ChiTietCTGG_DTO;
import DTO.ChiTietHoaDon_DTO;
import Database.ConnectionManager;

public class ChiTietCTGG_DAO {

    private List<ChiTietCTGG_DTO> danhSachChiTietCTGG;
    private static Connection connection = ConnectionManager.openConnection(); 

    public ChiTietCTGG_DAO() {
        this.danhSachChiTietCTGG = new ArrayList<>();
        // Khởi tạo dữ liệu mẫu nếu cần
    }

    public static boolean addChiTietCTGG(ChiTietCTGG_DTO chiTietCTGG) throws SQLException {
    	 boolean result = false;
         try {
             // openConnection();
             String sql = "INSERT INTO chitietctgg (maChiTietCTGG, maCTGG, phanTramGiamGia,"
             		+ " maSach ) VALUES (?, ?, ?, ?)";
             PreparedStatement stmt = connection.prepareStatement(sql);
             stmt.setString(1 , chiTietCTGG.getMaChiTietCTGG());
             stmt.setString(2,chiTietCTGG.getMaCTGG());
             stmt.setInt(3, chiTietCTGG.getPhanTramGiamGia());
             stmt.setString(4,chiTietCTGG.getMaSach());
        
             int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0) {
                 result = true;
             }
         } catch (SQLException ex) {
             System.out.println(ex);
         } finally {
             // ConnectionManager.closeConnection();
         }
         return result;
    }

    public static ArrayList<ChiTietCTGG_DTO> getAllChiTietCTGG() throws SQLException {
        ArrayList<ChiTietCTGG_DTO> ctggList = new ArrayList<>();
        String query = "SELECT * FROM ChiTietCTGG"; // Tên bảng được viết chính xác

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maChiTietCTGG = rs.getString("maChiTietCTGG");
                String maCTGG = rs.getString("maCTGG");
                int phanTramGiamGia = rs.getInt("phanTramGiamGia");
                String maSach = rs.getString("maSach");

                ChiTietCTGG_DTO ctgg = new ChiTietCTGG_DTO(maChiTietCTGG, maCTGG, phanTramGiamGia, maSach);
                ctggList.add(ctgg);
            }
        }

        return ctggList;
    }


    public static boolean deleteChiTietCTGG(String maCTGG) throws SQLException {
        String query = "DELETE FROM chitietctgg WHERE maChitietCTGG = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maCTGG);
            ps.executeUpdate();
            return true ;
        }
    }
    
    public void capNhatChiTietCTGG(ChiTietCTGG_DTO chiTietCTGG) {
        // Tìm chi tiết CTGG trong danh sách và cập nhật thông tin
        for (int i = 0; i < danhSachChiTietCTGG.size(); i++) {
            if (danhSachChiTietCTGG.get(i).getMaChiTietCTGG().equals(chiTietCTGG.getMaChiTietCTGG())) {
                danhSachChiTietCTGG.set(i, chiTietCTGG);
                break;
            }
        }
    }
    public static ArrayList<ChiTietCTGG_DTO> layDanhSachChiTietCTGG(String maHoaDon) throws SQLException {
        ArrayList<ChiTietCTGG_DTO> danhSachChiTietHoaDon = new ArrayList<>();
        String query = "SELECT * FROM chitietctgg WHERE mactgg = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maHoaDon);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String machiTietCTGG = rs.getString("machiTietCTGG");
                    String maCTGG = rs.getString("maCTGG");
                    int phanTramGiamGia = rs.getInt("phanTramGiamGia");
                    String maSach = rs.getString("maSach");
                    ChiTietCTGG_DTO chiTietHoaDon = new ChiTietCTGG_DTO(machiTietCTGG, maCTGG, phanTramGiamGia, maSach);
                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
            }
        }
        return danhSachChiTietHoaDon;
    }
   
    public static double getPhanTramGiamGia(String maSach, String maCTGG) throws SQLException {
        double phanTramGiamGia = 0.0;
        String query = "SELECT PhanTramGiamGia FROM chitietctgg WHERE MaSach = ? AND MaCTGG = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maSach);
            preparedStatement.setString(2, maCTGG);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    phanTramGiamGia = resultSet.getDouble("PhanTramGiamGia");
                }
      
        return phanTramGiamGia;
    }
}
    
    
    public static boolean sachCanGiamGia(String maSach, String maCTGG, Date ngayHienTai) throws SQLException {
        boolean canGiamGia = false;
        String query = "SELECT COUNT(*) AS Count FROM chitietctgg WHERE MaSach = ? AND MaCTGG = ? AND EXISTS (SELECT * FROM ctgg WHERE MaCTGG = ? AND ? BETWEEN NgayBatDau AND NgayKetThuc)";
     
             PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maSach);
            preparedStatement.setString(2, maCTGG);
            preparedStatement.setString(3, maCTGG);
            preparedStatement.setDate(4, new java.sql.Date(ngayHienTai.getTime()));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("Count");
                    canGiamGia = count > 0;
                }
            }
       
        return canGiamGia;
    }

    public int countDuplicateMaCTGG(String maCTGG) throws SQLException {
        String query = "SELECT COUNT(*) FROM ChiTietCTGG WHERE MaChiTietCTGG = ?";
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
    
        public static boolean updateChiTietCTGG(ChiTietCTGG_DTO chiTietCTGG) throws SQLException {
            String sql = "UPDATE ChiTietCTGG SET MaCTGG = ?, MaSach = ?, PhanTramGiamGia = ? WHERE MaChiTietCTGG = ?;";
          
                 PreparedStatement stmt = connection.prepareStatement(sql);
                
                stmt.setString(1, chiTietCTGG.getMaCTGG());
                stmt.setString(2, chiTietCTGG.getMaSach());
                stmt.setInt(3, chiTietCTGG.getPhanTramGiamGia());
                stmt.setString(4, chiTietCTGG.getMaChiTietCTGG());
                
                int rowsAffected = stmt.executeUpdate();
                
                return rowsAffected > 0;
            } 
        
        
       

}

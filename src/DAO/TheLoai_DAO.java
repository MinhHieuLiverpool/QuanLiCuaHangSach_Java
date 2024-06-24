package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.TheLoai_DTO;
import Database.ConnectionManager;

public class TheLoai_DAO {
    private static Connection connection = ConnectionManager.openConnection(); 

    // Constructor
    public TheLoai_DAO() {
        
    }

    public ArrayList<TheLoai_DTO> getAllGenres() {
        ArrayList<TheLoai_DTO> genres = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TheLoai"; // Tên bảng thể loại sách
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TheLoai_DTO genre = new TheLoai_DTO(rs.getString("maTheLoai") , rs.getString("tenTheLoai"));
                genres.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genres;
    }
    public static int demtheloai() throws SQLException {
        Statement stmt = null;  
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) AS total_suppliers FROM theloai";
            ResultSet rs = stmt.executeQuery(query);
            int totalSuppliers = 0;
            if (rs.next()) {
                totalSuppliers = rs.getInt("total_suppliers");
            }
            return totalSuppliers;
    }
}

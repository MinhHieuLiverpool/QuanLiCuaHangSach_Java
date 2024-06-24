package DAO;
import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.TacGia_DTO;

public class TacGia_DAO {
    private static Connection connection = ConnectionManager.openConnection(); 

    // Constructor
    public TacGia_DAO() {
       
    }

    public ArrayList<TacGia_DTO> getAllAuthors() {
        ArrayList<TacGia_DTO> authors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TacGia";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TacGia_DTO author = new TacGia_DTO(rs.getString("maTacGia"), rs.getString("tenTacGia"));
                authors.add(author);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authors;
    }
    public static int demtacgia() throws SQLException {
        Statement stmt = null;
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) AS total_suppliers FROM tacgia";
            ResultSet rs = stmt.executeQuery(query);
            int totalSuppliers = 0;
            if (rs.next()) {
                totalSuppliers = rs.getInt("total_suppliers");
            }
            return totalSuppliers;
    }

}

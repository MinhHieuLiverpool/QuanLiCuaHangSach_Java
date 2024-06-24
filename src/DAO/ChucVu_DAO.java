package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ChucVu_DTO;
import DTO.TacGia_DTO;
import Database.ConnectionManager;

public class ChucVu_DAO {
	 private static Connection connection = ConnectionManager.openConnection(); 

	    // Constructor
	    public ChucVu_DAO(Connection connection) {
	        this.connection = connection;
	    }

	    public static ArrayList<ChucVu_DTO> getAllAuthors() {
	        ArrayList<ChucVu_DTO> authors = new ArrayList<>();
	        try {
	            String sql = "SELECT * FROM ChucVu";
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	            	ChucVu_DTO author = new ChucVu_DTO(rs.getString("tenChucVu"),rs.getString("maChucVu"));
	                authors.add(author);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return authors;
	    }
}

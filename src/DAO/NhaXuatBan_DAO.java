package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.NhaXuatBan_DTO;

import Database.ConnectionManager;

public class NhaXuatBan_DAO {
	  private Connection connection = ConnectionManager.openConnection(); 

	    // Constructor
	    public NhaXuatBan_DAO() {
	      
	    }

	    public ArrayList<NhaXuatBan_DTO> getAllPublishers() {
	        ArrayList<NhaXuatBan_DTO> authors = new ArrayList<>();
	        try {
	            String sql = "SELECT * FROM NhaXuatban";
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	            	NhaXuatBan_DTO author = new NhaXuatBan_DTO(rs.getString("maNXB"), rs.getString("tenNXB"));
	                authors.add(author);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return authors;
	    }
}

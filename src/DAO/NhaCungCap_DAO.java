package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.NhaCungCap_DTO;
import Database.ConnectionManager;
public class NhaCungCap_DAO {
	private static Connection connection = ConnectionManager.openConnection(); 

	public NhaCungCap_DAO(Connection connection) {
		NhaCungCap_DAO.connection = connection ;
	} 
public static ArrayList<NhaCungCap_DTO> getAllEmployee() {
    ArrayList<NhaCungCap_DTO> nhaCungCapList = new ArrayList<>();
    try {
        //openConnection();
        String sql = "SELECT * FROM nhaCungCap";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Tạo đối tượng Sach_DTO từ dữ liệu trong ResultSet
        	NhaCungCap_DTO nhaCungCap = new NhaCungCap_DTO();
            nhaCungCap.setmaNhaCungCap(rs.getString("maNhaCungCap"));
            nhaCungCap.setHo(rs.getString("ho"));
            nhaCungCap.setTen(rs.getString("ten"));
            nhaCungCap.setSoDienThoai(rs.getString("soDienThoai"));
            nhaCungCap.setdiaChi(rs.getString("diaChi"));
            nhaCungCapList.add(nhaCungCap);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    } finally {
    //	ConnectionManager.closeConnection();
    }
    return nhaCungCapList;
}


public static int countSuppliers() throws SQLException {
    Statement stmt = null;
        stmt = connection.createStatement();
        String query = "SELECT COUNT(*) AS total_suppliers FROM nhacungcap";
        ResultSet rs = stmt.executeQuery(query);
        int totalSuppliers = 0;
        if (rs.next()) {
            totalSuppliers = rs.getInt("total_suppliers");
        }
        return totalSuppliers;
}


}
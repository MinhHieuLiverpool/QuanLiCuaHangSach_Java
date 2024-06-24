package BUS;

import java.sql.SQLException;

import DAO.TacGia_DAO;

public class TacGia_BUS {
	 public static int demtacgia() throws SQLException {
	        return TacGia_DAO.demtacgia();
	    }
}

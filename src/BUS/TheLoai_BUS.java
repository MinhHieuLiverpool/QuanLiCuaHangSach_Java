package BUS;

import java.sql.SQLException;

import DAO.TheLoai_DAO;

public class TheLoai_BUS {
	   public static int demtheloai() throws SQLException {
	       return TheLoai_DAO.demtheloai();
	   }
}

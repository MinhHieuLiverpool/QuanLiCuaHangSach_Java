package BUS;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.CTGG_DAO;
import DAO.ChiTietCTGG_DAO;
import DTO.CTGG_DTO;
import DTO.ChiTietCTGG_DTO;

public class ChiTietCTGG_BUS {
	
	  private ChiTietCTGG_DAO chiTietctggDAO = new ChiTietCTGG_DAO();
	 public boolean addCTGG(ChiTietCTGG_DTO chiTietctgg_DTO) throws ParseException, SQLException {
    	 
		 if (chiTietctgg_DTO.getMaChiTietCTGG().isEmpty() ) {
	   		  JOptionPane.showMessageDialog(null, "Vui lòng nhập mã chi tiết chương trình giảm giá");
	          return false;
	    	 }
		 if (isDuplicateMaCTGG(chiTietctgg_DTO.getMaChiTietCTGG())) {
	   		  JOptionPane.showMessageDialog(null, "Mã Chương Trình Giảm Giá Đã Tồn Tại");
		          return false;
	    	 }
		 
		 if (chiTietctgg_DTO.getMaCTGG().isEmpty() ) {
    		  JOptionPane.showMessageDialog(null, "Vui lòng nhập mã chương trình giảm giá");
	          return false;
         }
    	
    	 
    	 if (chiTietctgg_DTO.getPhanTramGiamGia() == 0 ) {
    		    JOptionPane.showMessageDialog(null, "Vui lòng nhập phần trăm giảm giá");
    		    return false;
    		}
    	return ChiTietCTGG_DAO.addChiTietCTGG(chiTietctgg_DTO);
    }
	 public boolean isDuplicateMaCTGG(String maChiTietCTGG) throws SQLException {
	        return chiTietctggDAO.countDuplicateMaCTGG(maChiTietCTGG) > 0;
	    }
	  public static ArrayList<ChiTietCTGG_DTO> getAllChiTietCTGG() throws SQLException {
	        return ChiTietCTGG_DAO.getAllChiTietCTGG();
	    }

	  public static boolean updateChiTietCTGG(ChiTietCTGG_DTO chiTietCTGG) throws SQLException {
	        return ChiTietCTGG_DAO.updateChiTietCTGG(chiTietCTGG);
	    }
	  public static boolean deleteChiTietCTGG(String maChiTietCTGG) throws SQLException {
		  
	        return ChiTietCTGG_DAO.deleteChiTietCTGG(maChiTietCTGG);
	    }
	  
	  public static ArrayList<ChiTietCTGG_DTO> layDanhSachChiTietCTGG(String maCTGG) throws SQLException {
	        // Gọi phương thức từ lớp DAO để lấy danh sách chi tiết Cơ Thể Giảm Giá với mã cụ thể
	        return ChiTietCTGG_DAO.layDanhSachChiTietCTGG(maCTGG);
	    }
}

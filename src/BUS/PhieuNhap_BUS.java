package BUS;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.PhieuNhap_DTO;

public class PhieuNhap_BUS {
	  private static PhieuNhap_DAO phieuNhapDAO = new PhieuNhap_DAO();
	    
	    public PhieuNhap_BUS() {
	    	phieuNhapDAO = new PhieuNhap_DAO();
	    }

	    public static boolean addPhieuNhap(PhieuNhap_DTO phieuNhap_DTO) throws ParseException, SQLException {
	    	 if (phieuNhap_DTO.getMaPhieuNhap().isEmpty() ) {
	    		  JOptionPane.showMessageDialog(null, "Vui lòng nhập mã phiếu nhập");
		          return false;
	         }
	    	 if (isDuplicateMaCTGG(phieuNhap_DTO.getMaPhieuNhap())) {
	   		  JOptionPane.showMessageDialog(null, "Mã Phiếu nhập Đã Tồn Tại");
		          return false;
	    	 }
	    	 if (phieuNhap_DTO.getMaNhanVien().isEmpty() ) {
	   		  JOptionPane.showMessageDialog(null, "Vui lòng chọn mã nhân viên");
	          return false;
	    	 }
	    	 if (phieuNhap_DTO.getMaNhaCungCap().isEmpty() ) {
		   		  JOptionPane.showMessageDialog(null, "Vui lòng chọn mã nhà cung cấp");
		          return false;
		    	 }
	    	
	    	 if (phieuNhap_DTO.getNgayNhap()== null ) {
	      		  JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày lập");
	             return false;
	       	 }
	    	 
	    	return phieuNhapDAO.addPhieuNhap(phieuNhap_DTO);
	    }

		private static boolean isDuplicateMaCTGG(String maPhieuNhap) throws SQLException {
			  return phieuNhapDAO.countDuplicateMaCTGG(maPhieuNhap) > 0;
		}
		public static boolean xoaNhapKho(String maCTGG) throws SQLException {
		    boolean hasDetails = PhieuNhap_DAO.hasDetails(maCTGG);
		    
		    if (hasDetails) {
		      
		        JOptionPane.showMessageDialog(null, "Mã chi tiết giảm giá này đang được liên kết với chi tiết phiếu nhập.");
		        return false;
		    }       
		    boolean deleted = PhieuNhap_DAO.deleteCTGG(maCTGG);
		   
		    return deleted;
		}
		   public static boolean updateDatabase(PhieuNhap_DTO phieuNhap) {
		        try {
		           
		            boolean updated = PhieuNhap_DAO.updatePhieuNhap(phieuNhap);
		            return updated;
		        } catch (Exception ex) {
		            ex.printStackTrace();
		          
		            return false;
		        }
		    }
		   public static ArrayList<PhieuNhap_DTO> getAllPhieuNhap() {
		     
		        return PhieuNhap_DAO.getAllPhieuNhap();
		    }
		   public static void loadDataIntoComboBox_maPhieuNhap(JComboBox<String> tenComboBox) throws SQLException {
			    tenComboBox.removeAllItems();
			    ArrayList<PhieuNhap_DTO> publishers = PhieuNhap_DAO.getAllPhieuNhap(); 
			    for (PhieuNhap_DTO publisher : publishers) {
			    	tenComboBox.addItem(publisher.getMaPhieuNhap()); 
			    }
		}
		   public static boolean updateTotalBill(String maPhieuNhap, double newTotalBill) {
		        return PhieuNhap_DAO.updateTotalBill(maPhieuNhap, newTotalBill);
		    }
		   public static double getTotalInputForDay(Date currentDate) throws SQLException {
		        return PhieuNhap_DAO.getTotalSalesForDay(currentDate);
		    }
		   public static double calculateTotalInput() throws SQLException {
		        return PhieuNhap_DAO.calculateTotalInput();
		    }
		   public static ResultSet searchAndDisplayFromDatabase(String maPhieuNhap, 
					 String maNhaCungCap, String maNhanVien,
					 Date ngayBatDau, Double giaTu, Double giaDen) {
			    return PhieuNhap_DAO.searchAndDisplayFromDatabase(maPhieuNhap, maNhaCungCap, maNhanVien, ngayBatDau, giaTu, giaDen);
			}
}

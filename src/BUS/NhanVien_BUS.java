package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.NhanVien_DAO;
import DAO.Sach_DAO;
import DTO.NhanVien_DTO;
import Database.ConnectionManager;

public class NhanVien_BUS {
	 private Connection connection = ConnectionManager.openConnection(); 
	private  NhanVien_DAO nhanVienDAO = new NhanVien_DAO(connection);
	 public boolean addNhanVien(NhanVien_DTO NhanVien) {
		 	
	        if (!isMaSachUnique(NhanVien.getMaNhanVien())) {
	            JOptionPane.showMessageDialog(null, "Mã Nhân Viên đã tồn tại");
	            return false;
	        }
	        if (NhanVien.getMaNhanVien().isEmpty() 
	        		) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Nhân Viên");
	            return false;
	        }  

	        if (NhanVien.getHo().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ nhân viên");
	            return false;
	        }     
	        if (NhanVien.getTen().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên");
	            return false;
	        }  
	     
	        if (NhanVien.getNgaySinh() == null) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh nhân viên");
	            return false;
	        }
		
	        if (!NhanVien.getSoDienThoai().startsWith("0") || NhanVien.getSoDienThoai().length() != 10) {
	            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng '0' và có đúng 10 số");
	            return false;
	        }
	        if (NhanVien.getGioiTinh() == null) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính nhân viên");
	            return false;
	        }
	        
	        return nhanVienDAO.addSach(NhanVien);
	    }

	    private boolean isMaSachUnique(String maSach) {
	        boolean unique = true;
	        try {
	            String query = "SELECT COUNT(*) FROM NhanVien WHERE maNhanVien = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, maSach);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                if (count > 0) {
	                    unique = false;
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return unique;
	    }
	    public static void loadDataIntoComboBox_maNhanVien(JComboBox<String> tenComboBox) throws SQLException {
		    ArrayList<NhanVien_DTO> publishers = NhanVien_DAO.getAllEmployee(); 
		    for (NhanVien_DTO publisher : publishers) {
		    	tenComboBox.addItem(publisher.getMaNhanVien()); 
		    }
		}
	    public static ArrayList<NhanVien_DTO> layDanhSachNhanVien() {
	        return NhanVien_DAO.getAllEmployee();
	    }
	    
	    public static int countTotalEmployees() throws SQLException {
	        return NhanVien_DAO.countTotalEmployees();
	    }
	    
	    public static Map<String, Double> retrieveSalaryData() throws SQLException {
	       return NhanVien_DAO.retrieveSalaryData();
	    }
	    
	    public static String getMaxTotalSalesByEmployee() throws SQLException {
	     return NhanVien_DAO.getMaxTotalSalesByEmployee();
	    }
	    
		public static boolean delete(String maNhanVien) {
		return NhanVien_DAO.delete(maNhanVien);
		}
		public static boolean updateNhanVien(NhanVien_DTO nhanVien) {
		   return NhanVien_DAO.updateNhanVien(nhanVien);
		}
		public static ResultSet searchAndDisplayFromDatabase(String maNhanVien, String ho, String ten, String machucvu, String gioiTinh, String sapXepTheo, String tangGiam) {
		  return NhanVien_DAO.searchAndDisplayFromDatabase(maNhanVien, ho, ten, machucvu, gioiTinh, sapXepTheo, tangGiam);
		}
}

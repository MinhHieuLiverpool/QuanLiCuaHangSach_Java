package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.KhachHang_DAO;
import DAO.NhanVien_DAO;
import DTO.KhachHang_DTO;
import Database.ConnectionManager;

public class KhachHang_BUS {
	 private Connection connection = ConnectionManager.openConnection(); 
	private  KhachHang_DAO khachHangDAO = new KhachHang_DAO(connection);
	 public boolean addKhachHang(KhachHang_DTO khachHang) { 	
	        if (!isMaSachUnique(khachHang.getMaKhachHang())) {
	            JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại");
	            return false;
	        }
	        if (khachHang.getMaKhachHang().isEmpty() 
	        		) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng");
	            return false;
	        }  

	        if (khachHang.getHoKhachHang().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ khách hàng");
	            return false;
	        }     
	        if (khachHang.getTenKhachHang().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
	            return false;
	        }  
	     
	        if (khachHang.getNgaySinh_KH() == null) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh khách hàng");
	            return false;
	        }
		
	        if (!khachHang.getSoDienThoai_KH().startsWith("0") || khachHang.getSoDienThoai_KH().length() != 10) {
	            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng '0' và có đúng 10 số");
	            return false;
	        }
	        if (khachHang.getGioiTinh_KH() == null) {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính khách hàng");
	            return false;
	        }
	        
	        return khachHangDAO.addSach(khachHang);
	    }

	    private boolean isMaSachUnique(String maSach) {
	        boolean unique = true;
	        try {
	            String query = "SELECT COUNT(*) FROM KhachHang WHERE maKhachHang = ?";
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
	    public static boolean xoaKhachHang(String maKhachHang) {
	        // Kiểm tra xem khách hàng có tồn tại trong bất kỳ hóa đơn nào hay không
	        if (KhachHang_DAO.kiemTraKhachHangTrongHoaDon(maKhachHang)) {
	            JOptionPane.showMessageDialog(null, "Không thể xóa khách hàng vì khách hàng đang tồn tại trong hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return false;
	        } else {
	            // Nếu không có hóa đơn nào liên kết với khách hàng, thực hiện xóa và nhận kết quả trả về
	            boolean deleted = KhachHang_DAO.deleteKhachHang(maKhachHang);
	            // Trả về kết quả từ phương thức DAO
	            return deleted;
	        }
	    }
	    public static boolean updateKhachHang(KhachHang_DTO khachHang) {
	
	        if (!isValidKhachHang(khachHang)) {
	            return false; 
	        }
	        return KhachHang_DAO.updateKhachHang(khachHang);
	    }

	    // Phương thức này kiểm tra tính hợp lệ của dữ liệu khách hàng
	    private static boolean isValidKhachHang(KhachHang_DTO khachHang) {
	    
	    	  if (khachHang.getMaKhachHang().isEmpty() 
		        		) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng");
		            return false;
		        }  

		        if (khachHang.getHoKhachHang().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ khách hàng");
		            return false;
		        }     
		        if (khachHang.getTenKhachHang().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng");
		            return false;
		        }  
		     
		        if (khachHang.getNgaySinh_KH() == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh khách hàng");
		            return false;
		        }
			
		        if (!khachHang.getSoDienThoai_KH().startsWith("0") || khachHang.getSoDienThoai_KH().length() != 10) {
		            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng '0' và có đúng 10 số");
		            return false;
		        }
		        if (khachHang.getGioiTinh_KH() == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính khách hàng");
		            return false;
		        }

	        return true; 
	    }
	    public static void loadDataIntoComboBox_maKhachHang(JComboBox<String> tenComboBox) throws SQLException {		
		    ArrayList<KhachHang_DTO> publishers = KhachHang_DAO.getAllEmployee(); // Lấy danh sách nhà xuất bản từ cơ sở dữ liệu
		    for (KhachHang_DTO publisher : publishers) {
		    	tenComboBox.addItem(publisher.getMaKhachHang()); // Thêm tên nhà xuất bản vào combobox
		    }
		}
	    public static ArrayList<KhachHang_DTO> getAllEmployee() {
	        // Gọi phương thức từ lớp DAO để lấy danh sách khách hàng
	        return KhachHang_DAO.getAllEmployee();
	    }
	    public static int countTotalKhachHang() throws SQLException {
	        return KhachHang_DAO.countTotalKhachHang();
	    }
	     public static Map<String, Double> TongChiKhachHang() throws SQLException {
	    	 return KhachHang_DAO.TongChiKhachHang();
	     }
	     public static ResultSet searchAndDisplayFromDatabase(String maNhanVien,
	     		String ho, String ten, 
	     		String gioiTinh) {
	         return KhachHang_DAO.searchAndDisplayFromDatabase(maNhanVien, ho, ten, gioiTinh);
	     }
	  
}

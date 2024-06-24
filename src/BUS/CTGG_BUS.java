package BUS;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import DAO.CTGG_DAO;
import DAO.ChiTietCTGG_DAO;
import DTO.CTGG_DTO;
import DTO.ChiTietCTGG_DTO;
import Database.ConnectionManager;


public class CTGG_BUS {
    private CTGG_DAO ctggDAO;
    private Connection connection = ConnectionManager.openConnection(); 
    public CTGG_BUS() {
        ctggDAO = new CTGG_DAO(connection);
    }

   
    public static ArrayList<CTGG_DTO> getAllCTGG() throws SQLException {
        return CTGG_DAO.getAllCTGG();
    }


    public boolean addCTGG(CTGG_DTO ctgg_DTO) throws ParseException, SQLException {
    	 if (ctgg_DTO.getMaCTGG().isEmpty() ) {
    		  JOptionPane.showMessageDialog(null, "Vui lòng nhập mã chương trình giảm giá");
	          return false;
         }
    	 if (isDuplicateMaCTGG(ctgg_DTO.getMaCTGG())) {
   		  JOptionPane.showMessageDialog(null, "Mã Chương Trình Giảm Giá Đã Tồn Tại");
	          return false;
    	 }
    	 if (ctgg_DTO.getTenCTGG().isEmpty() ) {
   		  JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình giảm giá");
          return false;
    	 }
    	 if (ctgg_DTO.getThoiGianBatDau()== null ) {
      		  JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu của  chương trình giảm giá");
             return false;
       	 }
    	 if (ctgg_DTO.getThoiGianKetThuc()== null ) {
      		  JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc chương trình giảm giá");
             return false;
       	 }
    	 if (!isValidDateRange(ctgg_DTO.getThoiGianBatDau(), ctgg_DTO.getThoiGianKetThuc())) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
        } 	
    	return CTGG_DAO.addCTGG(ctgg_DTO);
    }
 
    public static boolean isValidDateRange(Date startDate, Date endDate) {
        return endDate.after(startDate);
    }

    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }
    
    public boolean isDateEmpty(Date startDate, Date endDate) {
        return startDate == null && endDate == null;
    }
    
    public boolean isDuplicateMaCTGG(String maCTGG) throws SQLException {
        return ctggDAO.countDuplicateMaCTGG(maCTGG) > 0;
    }
    public static boolean updateCTGG(CTGG_DTO ctgg_DTO) throws HeadlessException {
    	 if (ctgg_DTO.getTenCTGG().isEmpty() ) {
      		  JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình giảm giá");
             return false;
       	 }
       	 if (ctgg_DTO.getThoiGianBatDau()== null ) {
         		  JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu của  chương trình giảm giá");
                return false;
          	 }
       	 if (ctgg_DTO.getThoiGianKetThuc()== null ) {
         		  JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc chương trình giảm giá");
                return false;
          	 }
       	 if (!isValidDateRange(ctgg_DTO.getThoiGianBatDau(), ctgg_DTO.getThoiGianKetThuc())) {
               JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
           } 	

        boolean updated = CTGG_DAO.updateCTGG(ctgg_DTO);
        return updated; 
    }
    public static void loadDataIntoComboBox_maCTGG(JComboBox<String> tenComboBox) throws SQLException {
	    ArrayList<CTGG_DTO> publishers = CTGG_DAO.getAllCTGG(); // Lấy danh sách nhà xuất bản từ cơ sở dữ liệu
	    for (CTGG_DTO publisher : publishers) {
	    	tenComboBox.addItem(publisher.getMaCTGG()); // Thêm tên nhà xuất bản vào combobox
	    }
}
    public static ArrayList<CTGG_DTO> getAllChiTietCTGG() throws SQLException {
        return CTGG_DAO.getAllCTGG();
    }
    public static boolean hasDetails(String maCTGG) throws SQLException {
        return CTGG_DAO.hasDetails(maCTGG);
    }


    public static boolean deleteCTGG(String maCTGG) throws SQLException {
        return CTGG_DAO.deleteCTGG(maCTGG);
    }
    public static boolean kiemTraNgayLapTrongKhoangCTGG(Date ngayLap, String maCTGG) {
        return CTGG_DAO.kiemTraNgayLapTrongKhoangCTGG(ngayLap, maCTGG);
    }
    
    public static ResultSet searchAndDisplayFromDatabase(String maCTGG, String tenCTGG, Date ngayBatDau, Date ngayKetThuc) {
        return CTGG_DAO.searchAndDisplayFromDatabase(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
    }
    

}

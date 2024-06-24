package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.NhaCungCap_DAO;
import DTO.NhaCungCap_DTO;

public class NhaCungCap_BUS {
	 public static void loadDataIntoComboBox_maNhaCungCap(JComboBox<String> tenComboBox) throws SQLException {
			
		    ArrayList<NhaCungCap_DTO> publishers = NhaCungCap_DAO.getAllEmployee(); // Lấy danh sách nhà xuất bản từ cơ sở dữ liệu
		    for (NhaCungCap_DTO publisher : publishers) {
		    	tenComboBox.addItem(publisher.getmaNhaCungCap()); // Thêm tên nhà xuất bản vào combobox
		    }
	}
	 public static int countSuppliers() throws SQLException {
		    return NhaCungCap_DAO.countSuppliers();
		}
}

package BUS;

import DAO.NhaXuatBan_DAO;
import DAO.Sach_DAO;
import DAO.TacGia_DAO;
import DAO.TheLoai_DAO;
import DTO.NhaXuatBan_DTO;
import DTO.Sach_DTO;
import DTO.TacGia_DTO;
import DTO.TheLoai_DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Sach_BUS {
    private Sach_DAO sachDAO;
    public Sach_BUS(Connection connection) {
        this.sachDAO = new Sach_DAO(connection);
    }

    public boolean addBook(Sach_DTO sach) {  
        if (!Sach_DAO.isMaSachUnique(sach.getMaSach())) {
        	 JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại");
            return false;
        }
        if (sach.getHinhAnh().equals("Hình Ảnh Sách")) {
        	JOptionPane.showMessageDialog(null, "Vui lòng thêm hình ảnh cho sách");
            return false;
        }
        return sachDAO.addSach(sach);
    }


    public static boolean deleteBook(String maSach) {
    	   boolean deleted;
		if (Sach_DAO.kiemtraSachcotrongHoaDon(maSach)) {
	            JOptionPane.showMessageDialog(null, "Không thể xóa sách vì sách đang tồn tại trong hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return false;
	        } else if (Sach_DAO.kiemtraSachcotrongCTGG(maSach)) {
	            JOptionPane.showMessageDialog(null, "Không thể xóa sách vì sách đang tồn tại trong chương trình giảm giá.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return false;
	        } 
		
		else {
	            deleted = Sach_DAO.deleteSach(maSach);
	         
	        
	        }
    	
			return deleted;
    }

    public static boolean updateBook(Sach_DTO sach) {
    	 if (sach.getHinhAnh().equals("Hình Ảnh Sách")) {
         	JOptionPane.showMessageDialog(null, "Vui lòng thêm hình ảnh cho sách");
             return false;
         }
        return Sach_DAO.updateSach(sach);
    }

    public static void loadDataIntoComboBox_maSach(JComboBox<String> tenComboBox) {
        ArrayList<Sach_DTO> sachList = Sach_DAO.getAllBooks(); // Lấy danh sách sách từ cơ sở dữ liệu
		for (Sach_DTO sach : sachList) {
		    tenComboBox.addItem(sach.getMaSach()); // Thêm mã sách vào combobox
		}
    }
    public static int getSoLuongByMaSach(String maSach) throws SQLException {
        return Sach_DAO.getSoLuongByMaSach(maSach);
    }
    public static void updateSoLuongByMaSach(String maSach, int soLuongMoi) throws SQLException {
        
        Sach_DAO.updateSoLuongByMaSach(maSach, soLuongMoi);
    }
    public static Sach_DTO laySachTheoMa(String maSach) {
        return Sach_DAO.getSachByMaSach(maSach);
    }
    public int laySoLuongSachTheoMa(String maSach) throws SQLException {
        return Sach_DAO.getSoLuongByMaSach(maSach);
    }
	 public static void loadDataIntoComboBox_maTheLoai(JComboBox<String> tenComboBox) {
		    //comboBox_maTheLoai.removeAllItems(); // Xóa tất cả các mục hiện có trong combobox
		    TheLoai_DAO theLoaiDAO = new TheLoai_DAO(); // Tạo một đối tượng DAO
		    ArrayList<TheLoai_DTO> genres = theLoaiDAO.getAllGenres(); // Lấy danh sách thể loại sách từ cơ sở dữ liệu
		    for (TheLoai_DTO genre : genres) {
		    	tenComboBox.addItem(genre.getMaTheLoai()); // Thêm tên thể loại sách vào combobox
		    }
		}
	 
	    public static void loadDataIntoList(DefaultListModel<String> listModel) {
	        TheLoai_DAO theLoaiDAO = new TheLoai_DAO(); // Tạo một đối tượng DAO
	        ArrayList<TheLoai_DTO> genres = theLoaiDAO.getAllGenres(); // Lấy danh sách thể loại sách từ cơ sở dữ liệu
	        for (TheLoai_DTO genre : genres) {
	            listModel.addElement(genre.getMaTheLoai()); // Thêm tên thể loại sách vào listModel
	        }
	    }
	    public static void loadDataIntoList_maNXB(DefaultListModel<String> listModel) {
	    	NhaXuatBan_DAO theLoaiDAO = new NhaXuatBan_DAO(); // Tạo một đối tượng DAO
	        ArrayList<NhaXuatBan_DTO> genres = theLoaiDAO.getAllPublishers(); // Lấy danh sách thể loại sách từ cơ sở dữ liệu
	        for (NhaXuatBan_DTO genre : genres) {
	            listModel.addElement(genre.getMaNXB()); // Thêm tên thể loại sách vào listModel
	        }
	    }
	    public static void loadDataIntoList_maNXB(JComboBox<String> tenComboBox) {
	    	NhaXuatBan_DAO theLoaiDAO = new NhaXuatBan_DAO(); // Tạo một đối tượng DAO
	        ArrayList<NhaXuatBan_DTO> genres = theLoaiDAO.getAllPublishers(); // Lấy danh sách thể loại sách từ cơ sở dữ liệu
	        for (NhaXuatBan_DTO genre : genres) {
	        	tenComboBox.addItem(genre.getMaNXB()); // Thêm tên thể loại sách vào listModel
	        }
	    }
	 
		 public static void loadDataIntoComboBox_maTacGia(JComboBox<String> tenComboBox) {
		        TacGia_DAO tacGiaDAO = new TacGia_DAO(); 
		        ArrayList<TacGia_DTO> authors = tacGiaDAO.getAllAuthors(); 
		        for (TacGia_DTO author : authors) {
		        	tenComboBox.addItem(author.getMaTacGia()); 
		        }
		 }
	    
	 public static void loadDataIntoComboBox_maTacGia(DefaultListModel<String> listModel) {
	        TacGia_DAO tacGiaDAO = new TacGia_DAO(); 
	        ArrayList<TacGia_DTO> authors = tacGiaDAO.getAllAuthors(); 
	        for (TacGia_DTO author : authors) {
	        	listModel.addElement(author.getMaTacGia()); 
	        }
	 }
	 public static double findGiaByMaSach(String maSach) {
	        return Sach_DAO.findGiaByMaSach(maSach);
	    }	 
	   public static int countTotalQuantityOfBooks() throws SQLException {
	        return Sach_DAO.countTotalQuantityOfBooks();
	    }
	   public static String getBestSellingBookId() throws SQLException {
	       return Sach_DAO.getBestSellingBookId();
	    }
	   public static int getBestSellingQuantity() throws SQLException {
	      return Sach_DAO.getBestSellingQuantity();

	    }
	   public static List<String> getSimilarBookTitles(String inputTitle) throws SQLException {
	       return Sach_DAO.getSimilarBookTitles(inputTitle);
	    }
	    public static ArrayList<Sach_DTO> getAllBooks() {
	       return Sach_DAO.getAllBooks();
	    }
	    public static ResultSet searchAndDisplayFromDatabase(String maSach, String []maNXBs, String[] maTheLoais, String []maTacGias, String tenSach, String donGiaTu, String donGiaDen)  {
	        return Sach_DAO.searchAndDisplayFromDatabase(maSach, maNXBs, maTheLoais, maTacGias, tenSach, donGiaTu, donGiaDen) ;
	    }
	    

	    public static Sach_DTO getSachByMaSach(String maSach) {
	        return Sach_DAO.getSachByMaSach(maSach);
	    }
	    
	    public static ResultSet searchAndDisplayFromDatabase(String maSach, String maNXB, String maTheLoai, String maTacGia, String tenSach, String donGiaTu, String donGiaDen) {
	      return searchAndDisplayFromDatabase(maSach, maNXB, maTheLoai, maTacGia, tenSach, donGiaTu, donGiaDen);
	    }
}

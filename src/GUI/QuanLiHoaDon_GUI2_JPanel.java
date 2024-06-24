package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import BUS.CTGG_BUS;
import BUS.ChiTietHoaDon_BUS;
import BUS.HoaDon_BUS;
import BUS.KhachHang_BUS;
import BUS.NhanVien_BUS;
import BUS.Sach_BUS;
import DTO.ChiTietHoaDon_DTO;
import DTO.HoaDon_DTO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;


import javax.swing.SwingConstants;

public class QuanLiHoaDon_GUI2_JPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_hoaDon;
	private JTable table_chiTietHoaDon;
	private JLabel lblSLngHa;
	private JLabel label_thanhTien;
	private JComboBox<String> comboBox_maGiamGia;
	private JComboBox<String> comboBox_maKhachHang;
	private JComboBox<String> comboBox_maNhanVien;
	private JDateChooser dateChooser_ngayLap;
	private JLabel label_tongTien;
	private JLabel label_giamGia;
	private JLabel label_demChiTiet;
	private JTextField textField_maHoaDonTimKiem;
	private JComboBox<String> comboBox_maKhachHang_timKiem;
	private JTextField textField_tu;
	private JTextField textField_den;
	private JComboBox<String> comboBox_maGiamGia_timKiem;
	private JComboBox<String> comboBox_maNhanVien_timKiem;
	private JComboBox<String> comboBox_maHoaDon;
	private JComboBox<String> comboBox_maSach;
	private JLabel label_giamGia_1;
	private JLabel label_tongTien_1;
	private JTextField textField_soLuong;
	private JLabel lblNewLabel_4;
	private JComboBox<String> combobox_maSach_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiHoaDon_GUI2_JPanel frame = new QuanLiHoaDon_GUI2_JPanel();
					frame.setVisible(true);
					frame.loadTableData();
					frame.hienThongKe();
					frame.loadTableData_chitiet();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public QuanLiHoaDon_GUI2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblNewLabel.setForeground(new Color(128, 64, 64));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 0, 1168, 27);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 40, 140, 23);
		add(lblNewLabel_1);
		
		JLabel label_maHoaDon = new JLabel("");
		label_maHoaDon.setBounds(143, 40, 140, 24);
		add(label_maHoaDon);
		label_maHoaDon.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_maHoaDon.setBackground(new Color(220, 220, 220));
		
		JLabel lblNewLabel_1_2 = new JLabel("Mã khách hàng");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(10, 84, 140, 28);
		add(lblNewLabel_1_2);
		
		comboBox_maKhachHang = new JComboBox<String>();
		comboBox_maKhachHang.setBounds(143, 82, 140, 30);
		add(comboBox_maKhachHang);
		KhachHang_BUS.loadDataIntoComboBox_maKhachHang(comboBox_maKhachHang);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mã nhân viên");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(308, 40, 126, 23);
		add(lblNewLabel_1_3);
		
		comboBox_maNhanVien = new JComboBox<String>();
		comboBox_maNhanVien.setBounds(428, 37, 147, 26);
		add(comboBox_maNhanVien);
		NhanVien_BUS.loadDataIntoComboBox_maNhanVien(comboBox_maNhanVien);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Mã giảm giá");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1.setBounds(308, 90, 126, 23);
		add(lblNewLabel_1_3_1);
		
		comboBox_maGiamGia = new JComboBox<String>();
		comboBox_maGiamGia.setBounds(428, 85, 147, 27);
		add(comboBox_maGiamGia);
		CTGG_BUS.loadDataIntoComboBox_maCTGG(comboBox_maGiamGia);
		
		JLabel lblNgyLp = new JLabel("Ngày lập");
		lblNgyLp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgyLp.setBounds(11, 130, 139, 23);
		add(lblNgyLp);
	
		dateChooser_ngayLap = new JDateChooser();
		dateChooser_ngayLap.setBounds(146, 130, 137, 25);
	    add(dateChooser_ngayLap);
		
		JLabel lblThnhTin = new JLabel("Thành tiền");
		lblThnhTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThnhTin.setBounds(11, 170, 139, 30);
		add(lblThnhTin);
		
		JLabel lblNewLabel_1_3_2_1 = new JLabel("Giảm giá");
		lblNewLabel_1_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_2_1.setBounds(308, 133, 126, 23);
		add(lblNewLabel_1_3_2_1);
		
		JLabel lblNewLabel_1_3_3_1 = new JLabel("Tổng tiền");
		lblNewLabel_1_3_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_3_1.setBounds(308, 177, 126, 23);
		add(lblNewLabel_1_3_3_1);
		
		label_tongTien = new JLabel("");
		label_tongTien.setBounds(428, 170, 147, 23);
		add(label_tongTien);
		label_tongTien.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_tongTien.setBackground(new Color(220, 220, 220));
			
		label_giamGia = new JLabel("");
		label_giamGia.setBounds(428, 129, 147, 24);
		add(label_giamGia);
		label_giamGia.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_giamGia.setBackground(new Color(220, 220, 220));
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm hóa đơn");
		lblNewLabel_2.setForeground(new Color(128, 128, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(675, 21, 542, 30);
		add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 324, 571, 364);
		add(scrollPane);
		
		table_hoaDon = new JTable();
		table_hoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã hóa đơn", "Mã khách hàng", "Mã nhân Viên",
				"Mã giảm giá", "Ngày lập","Tổng tiền"
			}
		));
		
		scrollPane.setViewportView(table_hoaDon);
		// Nhấn vào bảng
		table_hoaDon.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table_hoaDon.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table_hoaDon.getModel();
		           
		            // Lấy dữ liệu từ bảng đổ lên các textField và combobox 
		            String maHoaDon = model.getValueAt(selectedRow, 0).toString();
		            label_maHoaDon.setText(model.getValueAt(selectedRow, 0).toString());
		            comboBox_maKhachHang.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		            comboBox_maNhanVien.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
		            comboBox_maGiamGia.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
		            label_tongTien.setText(model.getValueAt(selectedRow, 5).toString());
		            try {
                        String dateString = model.getValueAt(selectedRow, 4).toString(); // Lấy ngày tháng năm dưới dạng chuỗi từ JTable
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng năm
                        Date ngaySinhDate = dateFormat.parse(dateString); // Chuyển đổi chuỗi sang Date
                        dateChooser_ngayLap.setDate(ngaySinhDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
		            
		            ArrayList<ChiTietHoaDon_DTO> chiTietList = null;
						try {
							// Lấy tất cả danh sách chi tiết hóa đơn có mã hóa đơn trùng nhau 
							// Nhấn 1 bảng hiện ra 2 bảng
							chiTietList = ChiTietHoaDon_BUS.layDanhSachChiTietHoaDon(maHoaDon);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
		            DefaultTableModel chiTietModel = (DefaultTableModel) table_chiTietHoaDon.getModel();
		            chiTietModel.setRowCount(0);	        
		            for (ChiTietHoaDon_DTO chiTiet : chiTietList) {
		                chiTietModel.addRow(new Object[] { 
		                	chiTiet.getMaHoaDon(),
		                    chiTiet.getMaSach(), 
		                    chiTiet.getTenSach(), 
		                    chiTiet.getSoLuong(), 
		                    chiTiet.getDonGia(),
		                    chiTiet.getGiamGia(),
		                    chiTiet.getThanhTien()
		                });
		            }
		            tinhThanhTien();
		            tinhGiamGia();     
		            demChiTiet();
		        }
		    }
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(619, 428, 611, 260);
		add(scrollPane_1);
		
		table_chiTietHoaDon = new JTable();
		table_chiTietHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] { 
				"Mã Hóa Đơn", "Mã Sách", "Tên Sách",
				"Số lượng", "Đơn giá",
				"Giảm giá", "Tổng tiền"
			}
		));
		
		table_chiTietHoaDon.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {	    	
		        int selectedRow = table_chiTietHoaDon.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table_chiTietHoaDon.getModel();         
		           
		            comboBox_maHoaDon.setSelectedItem(model.getValueAt(selectedRow, 0).toString());
		            comboBox_maSach.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		         
		            textField_soLuong.setText(model.getValueAt(selectedRow, 3).toString());
		    	    label_giamGia_1.setText(model.getValueAt(selectedRow, 5).toString());
		    	    label_tongTien_1.setText(model.getValueAt(selectedRow, 6).toString());
		        
		        }
		    }
		});
		
		scrollPane_1.setViewportView(table_chiTietHoaDon);
		 table_chiTietHoaDon.getModel().addTableModelListener(e -> {
	            int rowCount = table_chiTietHoaDon.getRowCount();
	            label_demChiTiet.setText("Số chi tiết hóa đơn: " + rowCount);
	        });
		
		lblSLngHa = new JLabel("Số lượng hóa đơn:");
		lblSLngHa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSLngHa.setBounds(18, 707, 184, 23);
		add(lblSLngHa);
		
		label_thanhTien = new JLabel("");
		
		label_thanhTien.setBounds(146, 170, 137, 30);
		add(label_thanhTien);
		label_thanhTien.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_thanhTien.setBackground(new Color(220, 220, 220));
		
		JButton btnTtCChi = new JButton("Tất cả chi tiết hóa đơn");
		btnTtCChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTtCChi.setForeground(new Color(0, 0, 255));
		btnTtCChi.setBounds(952, 700, 278, 36);
		ImageIcon icon9 = new ImageIcon("src\\icon\\eye.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image9 = icon9.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon9 = new ImageIcon(image9);
	    btnTtCChi.setIcon(scaledIcon9);
		add(btnTtCChi);
		
		label_demChiTiet = new JLabel();
		label_demChiTiet.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_demChiTiet.setBounds(619, 713, 299, 23);
		add(label_demChiTiet);
	    
		JLabel lblNewLabel_1_1 = new JLabel("Mã Hóa Đơn");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(619, 60, 106, 33);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Mã Khách Hàng");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(939, 61, 162, 30);
		add(lblNewLabel_1_2_1);
		
		comboBox_maKhachHang_timKiem = new JComboBox<String>();
		comboBox_maKhachHang_timKiem.setBounds(1092, 61, 140, 28);
		add(comboBox_maKhachHang_timKiem);
		comboBox_maKhachHang_timKiem.addItem("Tất cả");
		KhachHang_BUS.loadDataIntoComboBox_maKhachHang(comboBox_maKhachHang_timKiem);
			
		JLabel lblNewLabel_1_3_3 = new JLabel("Mã Nhân Viên");
		lblNewLabel_1_3_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_3.setBounds(619, 101, 126, 33);
		add(lblNewLabel_1_3_3);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Mã Giảm Giá");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1_1.setBounds(939, 101, 106, 32);
		add(lblNewLabel_1_3_1_1);
		
		comboBox_maGiamGia_timKiem = new JComboBox<String>();
		comboBox_maGiamGia_timKiem.setBounds(1092, 99, 140, 31);
		add(comboBox_maGiamGia_timKiem);
		comboBox_maGiamGia_timKiem.addItem("Tất cả");
		CTGG_BUS.loadDataIntoComboBox_maCTGG(comboBox_maGiamGia_timKiem);
		
		comboBox_maNhanVien_timKiem = new JComboBox<String>();
		comboBox_maNhanVien_timKiem.setBounds(746, 103, 162, 31);
		add(comboBox_maNhanVien_timKiem);
		comboBox_maNhanVien_timKiem.addItem("Tất cả");
		NhanVien_BUS.loadDataIntoComboBox_maNhanVien(comboBox_maNhanVien_timKiem);
		
		JButton button_sua = new JButton("Sửa");
		button_sua.setBounds(61, 247, 100, 36);
		ImageIcon icon3 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image3 = icon3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon3 = new ImageIcon(image3);
	    button_sua.setIcon(scaledIcon3);
		add(button_sua);
		button_sua.setBackground(Color.ORANGE); // Màu đỏ nhạt
		button_sua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_hoaDon.getSelectedRow();
		        if (selectedRow != -1) {
		            try {      
		                Object maNhanVien = comboBox_maNhanVien.getSelectedItem();
		                Object maKhachHang = comboBox_maKhachHang.getSelectedItem();
		                Object maGiamGia = comboBox_maGiamGia.getSelectedItem();
		                Date ngayLap = dateChooser_ngayLap.getDate();   
		                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày mới
		                String ngayLapFormatted = formatter.format(ngayLap);  // Chuyển đổi ngày thành chuỗi mới theo định dạng
		                String maHoaDon = (String) table_hoaDon.getValueAt(selectedRow, 0);
		                Double tongTien = (Double) table_hoaDon.getValueAt(selectedRow, 5);
		                HoaDon_DTO hoaDon = new HoaDon_DTO(maHoaDon, maKhachHang.toString(), maNhanVien.toString(),
		                        maGiamGia.toString(), ngayLap, tongTien);
		            
		                boolean updated = HoaDon_BUS.updateHoaDon(hoaDon);
		                if (updated) {
		                    DefaultTableModel model = (DefaultTableModel) table_hoaDon.getModel();
		                    model.setValueAt(maHoaDon, selectedRow, 0);
		                    model.setValueAt(maKhachHang, selectedRow, 1);
		                    model.setValueAt(maNhanVien, selectedRow, 2);
		                    model.setValueAt(maGiamGia, selectedRow, 3);
		                    model.setValueAt(ngayLapFormatted, selectedRow, 4); // Cập nhật giá trị ngày
		                    model.setValueAt(tongTien, selectedRow, 5); 
		                    JOptionPane.showMessageDialog(null, "Sửa thông tin hóa đơn thành công!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho tổng tiền!");
		            } catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
		        }
		    }
		});

		JButton button_xoa = new JButton("Xóa");
		button_xoa.setBounds(278, 247, 100, 39);
		button_xoa.setBackground(new Color(255, 128, 128)); // Màu đỏ nhạt
		add(button_xoa);
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon2);
	    button_xoa.addActionListener(new ActionListener() {	 
	        public void actionPerformed(ActionEvent e) {
	            String maHoaDon = label_maHoaDon.getText();       
	            boolean deleted = false;
				try {
					deleted = HoaDon_BUS.xoaHoaDon(maHoaDon);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
	            if (deleted) {
	                    try {
							loadTableData();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
	            }
	        }
	    });
   
	    JLabel lblNewLabel_1_3_2 = new JLabel("Mã Sách");
	    lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_1_3_2.setBounds(619, 288, 126, 23);
	    add(lblNewLabel_1_3_2);
	    
	   comboBox_maSach = new JComboBox<String>();
	    comboBox_maSach.setBounds(785, 288, 157, 26);
	    add(comboBox_maSach);
	    Sach_BUS.loadDataIntoComboBox_maSach(comboBox_maSach);
	    
	    JLabel lblNewLabel_1_3_2_1_1 = new JLabel("Số lượng");
	    lblNewLabel_1_3_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_1_3_2_1_1.setBounds(980, 288, 106, 23);
	    add(lblNewLabel_1_3_2_1_1);
	    
	    label_giamGia_1 = new JLabel("");
	    label_giamGia_1.setOpaque(true);
	    label_giamGia_1.setBackground(new Color(220, 220, 220));
	    label_giamGia_1.setBounds(785, 329, 157, 24);
	    add(label_giamGia_1);
	    
	    JLabel lblNewLabel_1_3_2_1_1_1 = new JLabel("Giảm Giá");
	    lblNewLabel_1_3_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_1_3_2_1_1_1.setBounds(619, 329, 106, 23);
	    add(lblNewLabel_1_3_2_1_1_1);
	    
	    textField_maHoaDonTimKiem = new JTextField();
	    textField_maHoaDonTimKiem.setBounds(746, 60, 162, 31);
	    add(textField_maHoaDonTimKiem);
	    textField_maHoaDonTimKiem.setColumns(10);
	    
	    JButton button_tim = new JButton("Tìm");
	    button_tim.setBounds(932, 219, 113, 23);
	    button_tim.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_tim.setBackground(Color.CYAN);
		ImageIcon icon12 = new ImageIcon("src\\icon\\loupe.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon12 = new ImageIcon(image12);
	    button_tim.setIcon(scaledIcon12);
	    add(button_tim);
	    button_tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        String maHoaDon = textField_maHoaDonTimKiem.getText();
		        String maKhachHang = comboBox_maKhachHang_timKiem.getSelectedItem().toString();
                String maNhanVien = comboBox_maNhanVien_timKiem.getSelectedItem().toString();
                String maGiamGia = comboBox_maGiamGia_timKiem.getSelectedItem().toString();
                String valueDonGiaTu = textField_tu.getText().trim();
    	        String valueDonGiaDen = textField_den.getText().trim();
    	        if (maKhachHang.equals("Tất cả")) {
    	        	maKhachHang = "";
    	        }
    	        if (maNhanVien.equals("Tất cả")) {
    	        	maNhanVien = "";
    	        }
    	        if (maGiamGia.equals("Tất cả")) {
    	        	maGiamGia = "";
    	        }
    	        double minDonGia = Double.MIN_VALUE;
    	        if (!valueDonGiaTu.isEmpty()) {
    	            minDonGia = Double.parseDouble(valueDonGiaTu);
    	        }

    	        // Kiểm tra nếu giá đến rỗng, thì lấy giá trị max
    	        double maxDonGia = Double.MAX_VALUE;
    	        if (!valueDonGiaDen.isEmpty()) {
    	            maxDonGia = Double.parseDouble(valueDonGiaDen);
    	        }

    	        // Nếu giá từ lớn hơn giá đến, báo lỗi và xóa giá trị trong text field
    	        if (minDonGia > maxDonGia) {
    	            JOptionPane.showMessageDialog(null, "Giá từ không được lớn hơn giá đến.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            textField_tu.setText("");
    	            textField_den.setText("");
    	        } else {
    	            // Gọi phương thức tìm kiếm với các giá trị từ các JTextField
    	        	searchAndDisplayFromDatabase(maHoaDon, maKhachHang,
    	            		maNhanVien, maGiamGia, 
    	            		valueDonGiaTu, valueDonGiaDen );
    	        }	      

		       
		    }

		    private void searchAndDisplayFromDatabase(String maHoaDon, String maKhachHang ,
		    		String maNhanVien , String maGiamGia , String giaTu , String giaDen) {
		        ResultSet resultSet = null;
		     
		        try {
		            resultSet = HoaDon_BUS.searchAndDisplayFromDatabase(maHoaDon, maKhachHang,
		            		maNhanVien, maGiamGia, giaTu , giaDen);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        DefaultTableModel model = (DefaultTableModel) table_hoaDon.getModel();
		        model.setRowCount(0);	      
		        try {
		            while (resultSet.next()) {
		                Object[] rowData = new Object[6];
		                rowData[0] = resultSet.getString("maHoaDon");
		                rowData[1] = resultSet.getString("maKhachHang");
		            
		                rowData[2] = resultSet.getString("maNhanVien"); // Lấy dữ liệu ngày dưới dạng Date
		                rowData[3] = resultSet.getString("maCTGG"); 
		                rowData[4] = resultSet.getString("ngayLap");
		                rowData[5] = resultSet.getDouble("tongTien");// Lấy dữ liệu ngày dưới dạng Date
		                model.addRow(rowData);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
		    }
		});
	  
	
	    JLabel lblGiT = new JLabel("Giá từ");
	    lblGiT.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblGiT.setBounds(619, 141, 106, 33);
	    add(lblGiT);
	    
	    textField_tu = new JTextField();
	    textField_tu.setColumns(10);
	    textField_tu.setBounds(746, 144, 210, 31);
	    add(textField_tu);
	    
	    textField_den = new JTextField();
	    textField_den.setColumns(10);
	    textField_den.setBounds(1077, 144, 155, 31);
	    add(textField_den);
	    
	    JLabel lblNewLabel_3 = new JLabel("Mã Hóa Đơn");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_3.setBounds(622, 247, 135, 33);
	    add(lblNewLabel_3);
	    
	    comboBox_maHoaDon = new JComboBox<String>();
	    comboBox_maHoaDon.setBounds(782, 252, 160, 26);
	    add(comboBox_maHoaDon);
	    HoaDon_BUS.loadDataIntoComboBox_maHoaDon(comboBox_maHoaDon);
	    
	    JLabel lblNewLabel_1_3_2_1_1_1_1 = new JLabel("Tổng Tiền");
	    lblNewLabel_1_3_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_1_3_2_1_1_1_1.setBounds(980, 329, 106, 23);
	    add(lblNewLabel_1_3_2_1_1_1_1);
	    
	    label_tongTien_1 = new JLabel("");
	    label_tongTien_1.setOpaque(true);
	    label_tongTien_1.setBackground(new Color(220, 220, 220));
	    label_tongTien_1.setBounds(1092, 329, 140, 24);
	    add(label_tongTien_1);
	    
	    JLabel label_end = new JLabel("đến");
	    label_end.setFont(new Font("Tahoma", Font.BOLD, 16));
	    label_end.setBounds(978, 151, 45, 13);
	    add(label_end);
	    
	    JButton button_xoa_1 = new JButton("Xóa");
	    button_xoa_1.setBackground(new Color(255, 128, 128));
	    button_xoa_1.setBounds(1126, 375, 106, 27);
	    add(button_xoa_1);
	    button_xoa_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String maSach = comboBox_maSach.getSelectedItem().toString(); // Lấy mã sách thay vì mã chi tiết hóa đơn
	            String maHoaDon = comboBox_maHoaDon.getSelectedItem().toString();
	            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chi tiết hóa đơn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
	            if (choice == JOptionPane.YES_OPTION) {
	                try {
	                    boolean deleted = ChiTietHoaDon_BUS.xoaChiTietHoaDon(maHoaDon, maSach); 
	                    if (deleted) {
	                        Double tongTienHoaDon = HoaDon_BUS.calculateTotalBill(maHoaDon);
	                        boolean updated = HoaDon_BUS.updateTotalBill(maHoaDon, tongTienHoaDon);
	                        if (updated) {
	                            JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thành công và cập nhật tổng tiền!");
	                            loadTableData();
	                            loadTableData_chitiet();
	                        } else {
	                            JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thành công nhưng cập nhật tổng tiền không thành công!");
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn không thành công!");
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Lỗi khi xóa chi tiết hóa đơn!");
	                }
	            }
	        }
	    });



	    
	    JButton button_sua_1 = new JButton("Sửa");
	    button_sua_1.setBackground(Color.ORANGE);
	    button_sua_1.setBounds(941, 371, 100, 36);
	    add(button_sua_1);
	    button_sua_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String maHoaDon = comboBox_maHoaDon.getSelectedItem().toString();
	            String maSach = comboBox_maSach.getSelectedItem().toString();
	            int soLuongMoi = Integer.parseInt(textField_soLuong.getText());
	            try {
	                boolean updated = ChiTietHoaDon_BUS.updateSoLuongChiTietHoaDon(soLuongMoi, maHoaDon , maSach);
	                if (updated) {
	                    JOptionPane.showMessageDialog(null, "Sửa chi tiết hóa đơn thành công và cập nhật tổng tiền!");
	                    loadTableData();
	                    loadTableData_chitiet();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Sửa số lượng không thành công!");
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Lỗi khi sửa số lượng!");
	            }
	        }
	    });


	    
	    textField_soLuong = new JTextField();
	    textField_soLuong.setColumns(10);
	    textField_soLuong.setBounds(1096, 287, 136, 23);
	    add(textField_soLuong);
	    
	    lblNewLabel_4 = new JLabel("");
	    lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNewLabel_4.setBounds(212, 710, 85, 20);
	    add(lblNewLabel_4);
	    
	    JLabel lblNewLabel_1_3_3_2 = new JLabel("Mã Sách");
	    lblNewLabel_1_3_3_2.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblNewLabel_1_3_3_2.setBounds(619, 183, 126, 33);
	    add(lblNewLabel_1_3_3_2);
	    
	   combobox_maSach_1 = new JComboBox<String>();
	    combobox_maSach_1.setBounds(746, 185, 162, 27);
	    add(combobox_maSach_1);
	    combobox_maSach_1.addItem("Tất cả");
	    Sach_BUS.loadDataIntoComboBox_maSach(combobox_maSach_1);
	    
	    JButton button_sua_2 = new JButton("Làm Mới");
	    button_sua_2.setBackground(Color.ORANGE);
	    button_sua_2.setBounds(484, 247, 100, 36);
	    add(button_sua_2);
	    button_sua_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					loadTableData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	try {
					loadTableData_chitiet();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
	    
	    
		button_tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					searchAndUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});

		btnTtCChi.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        try {
					loadTableData_chitiet();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});

}
	public void loadTableData() throws SQLException {
	    DefaultTableModel model = (DefaultTableModel) table_hoaDon.getModel();
	    model.setRowCount(0); 
	    ArrayList<HoaDon_DTO> hoaDonList = HoaDon_BUS.layDanhSachHoaDon();
	    for (HoaDon_DTO hoaDon : hoaDonList) {
	        model.addRow(new Object[] {
	            hoaDon.getMaHoaDon(),
	            hoaDon.getMaKhachHang(),
	            hoaDon.getMaNhanVien(),
	            hoaDon.getMaGiamGia(),
	            hoaDon.getNgayLap(),
	            hoaDon.getTongTien()
	        });
	    }
	}
	
	public void loadTableData_chitiet() throws SQLException {
	    DefaultTableModel model = (DefaultTableModel) table_chiTietHoaDon.getModel();
	    model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
	    ArrayList<ChiTietHoaDon_DTO> chitiethoaDonList = ChiTietHoaDon_BUS.layDanhSachChiTietHoaDon();
	    for (ChiTietHoaDon_DTO hoaDon : chitiethoaDonList) {
	        model.addRow(new Object[] {
	            hoaDon.getMaHoaDon(), hoaDon.getMaSach(), hoaDon.getTenSach(),
	            hoaDon.getSoLuong()
	            , hoaDon.getDonGia() , hoaDon.getGiamGia(), hoaDon.getThanhTien()
	        });
	    }
	}
	  private void hienThongKe() {
		  try {
	            DefaultTableModel model = (DefaultTableModel) table_hoaDon.getModel();
	            int rowCount = model.getRowCount();
	            lblNewLabel_4.setText(rowCount+"");
	            
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
	            ex.printStackTrace();
	        }
	    
	  }
	  private void tinhThanhTien() {
		  try {
	            DefaultTableModel model = (DefaultTableModel) table_chiTietHoaDon.getModel();
	            int rowCount = model.getRowCount();
	            double tongTien = 0 ; 
	            for (int i = 0; i < rowCount; i++) {     
	                double tien = (double) model.getValueAt(i, 6);
	                tongTien+= tien ; 
	            }
	            label_thanhTien.setText(tongTien+"");    
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
	            ex.printStackTrace();
	        }
	  }
	  private void demChiTiet() {
		  try {
	            DefaultTableModel model = (DefaultTableModel) table_chiTietHoaDon.getModel();
	            int rowCount = model.getRowCount(); 
	            if(label_demChiTiet!=null)
	            label_demChiTiet.setText("Số chi tiết hóa đơn: "+  rowCount);    
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
	            ex.printStackTrace();
	        }
	    
	  }
	  
	  private void tinhGiamGia() {
		  try {
	            DefaultTableModel model = (DefaultTableModel) table_chiTietHoaDon.getModel();
	            int rowCount = model.getRowCount();
	            double tongTien = 0 ; 
	            for (int i = 0; i < rowCount; i++) {     
	                double tien = (double) model.getValueAt(i, 6);
	                tongTien+= tien ;                 
	            }
	            label_giamGia.setText(tongTien+""); 
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
	            ex.printStackTrace();
	        }
	  }
	
		 private void searchAndUpdate() throws SQLException {
			    String valueMaSach = combobox_maSach_1.getSelectedItem().toString();
			    
			    if (valueMaSach=="Tất cả") {
			        valueMaSach="";
			    }
			    else {
			        searchAndDisplayFromDatabase(valueMaSach,table_chiTietHoaDon);
			    }
			}
		 public static void searchAndDisplayFromDatabase(String maSach,  JTable tabel) throws SQLException {
			
			   ResultSet resultSet = ChiTietHoaDon_BUS.searchAndDisplayFromDatabase(maSach);
			   DefaultTableModel model = (DefaultTableModel) tabel.getModel();
			   model.setRowCount(0);
			   try {
					while (resultSet.next()) {					
						    Object[] rowData = new Object[7];
						    rowData[0] = resultSet.getString("mahoadon");
						    rowData[1] = resultSet.getString("masach");
						    rowData[2] = resultSet.getString("tensach");
						    rowData[3] = resultSet.getString("soluong");
						    rowData[4] = resultSet.getString("dongia");
						    rowData[5] = resultSet.getString("giamgia");
						    rowData[6] = resultSet.getString("thanhtien");						    
						    model.addRow(rowData);
 
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
}

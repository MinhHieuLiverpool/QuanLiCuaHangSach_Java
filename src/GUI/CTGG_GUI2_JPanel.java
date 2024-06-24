package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BUS.CTGG_BUS;
import BUS.ChiTietCTGG_BUS;
import BUS.Sach_BUS;
import DAO.CTGG_DAO;
import DTO.CTGG_DTO;
import DTO.ChiTietCTGG_DTO;
import NhapXuatEXCEL.NhapXuatExcel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;

public class CTGG_GUI2_JPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String selectedBookId; 
	private JTextField textField_maCTGG;
	private JTextField textField_tenCTGG;
	private static JTextField textField_phanTramGiam;
	private static JComboBox<String> comboBox_sach;
	private JTextField textField_maCTCTGG;
	private JComboBox<String> comboBox_maCTGG;
	private JDateChooser dateChooser_ngaybatDau;
	private JDateChooser dateChooser_ngayketThuc;
	private JTextField textField_maGiamGiaTimKiem;
	private JTextField textField_tenChuongTrinhTimKiem;
	private static JTable table_giamGia;
	private JTable table_chiTietCTGG;
	private JDateChooser dateChooser_ngayBatDauTimKiem;
	private JDateChooser dateChooser_ngayKetThucTimKiem;
	private static JLabel label_soLuongGiamGia;
	private JLabel label_soLuongChiTiet;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CTGG_GUI2_JPanel frame = new CTGG_GUI2_JPanel();
					frame.loadTableData();
					frame.loadTableData_Chitiet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CTGG_GUI2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		

		JLabel lblTiu = new JLabel("Chương Trình Khuyến Mãi");
		lblTiu.setForeground(new Color(10, 227, 245));
		lblTiu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTiu.setBounds(444, 0, 361, 37);
		add(lblTiu);
		
		JLabel lblMGimGi = new JLabel("Mã giảm giá");
		lblMGimGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMGimGi.setBounds(26, 49, 200, 28);
		add(lblMGimGi);
		
		JLabel lblTnChngTrnh = new JLabel("Tên chương trình");
		lblTnChngTrnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnChngTrnh.setBounds(26, 87, 200, 28);
		add(lblTnChngTrnh);
		
		JLabel lblThiGianBt = new JLabel("Thời gian bắt đầu");
		lblThiGianBt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThiGianBt.setBounds(26, 125, 200, 28);
		add(lblThiGianBt);
		
		JLabel lblThiGianKt = new JLabel("Thời gian kết thúc");
		lblThiGianKt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThiGianKt.setBounds(26, 163, 200, 28);
		add(lblThiGianKt);
		
		textField_maCTGG = new JTextField();
		textField_maCTGG.setBounds(246, 49, 209, 28);
		add(textField_maCTGG);
		textField_maCTGG.setColumns(10);
		
		textField_tenCTGG = new JTextField();
		textField_tenCTGG.setColumns(10);
		textField_tenCTGG.setBounds(246, 87, 209, 28);
		add(textField_tenCTGG);
		
		dateChooser_ngaybatDau = new JDateChooser();
        dateChooser_ngaybatDau.setBounds(246, 125, 209, 28);
        add(dateChooser_ngaybatDau);
		
		dateChooser_ngayketThuc = new JDateChooser();
        dateChooser_ngayketThuc.setBounds(246, 163, 209, 28);
        add(dateChooser_ngayketThuc) ;

		JButton button_them = new JButton("Thêm");
		add(button_them);
		button_them.setBounds(26, 212, 124, 28);
		button_them.setBackground(new Color(144, 238, 144));
		ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon = new ImageIcon(image);
	    button_them.setIcon(scaledIcon);
		button_them.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_them.addActionListener(e -> 	them());
		
		JButton button_sua = new JButton("Sửa");
		button_sua.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_sua.setBounds(343, 212, 112, 28);
		add(button_sua);
		button_sua.setBackground(new Color(255, 255, 169));
		ImageIcon icon1 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon1 = new ImageIcon(image1);
	    button_sua.setIcon(scaledIcon1);
		button_sua.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_giamGia.getSelectedRow();
		        if (selectedRow != -1) {
		                String maCTGG = textField_maCTGG.getText();
		                String tenCTGG  =textField_tenCTGG.getText();
		                Date ngayBatDau = dateChooser_ngaybatDau.getDate();
		                Date ngayKetThuc = dateChooser_ngayketThuc.getDate();  
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		             // Định dạng ngày bắt đầu
		             String ngayBatDauFormatted = dateFormat.format(ngayBatDau);

		             // Định dạng ngày kết thúc
		             String ngayKetThucFormatted = dateFormat.format(ngayKetThuc);

		           

		                CTGG_DTO ctgg = new CTGG_DTO(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
		                boolean updated = CTGG_BUS.updateCTGG(ctgg);
		                if (updated) {  
		                    DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
		                    model.setValueAt(maCTGG, selectedRow, 0);
		                    model.setValueAt(tenCTGG, selectedRow, 1);
		                    model.setValueAt(ngayBatDauFormatted, selectedRow, 2);
		                    model.setValueAt(ngayKetThucFormatted, selectedRow, 3);
		                    JOptionPane.showMessageDialog(null, "Sửa thông tin Chương trình giảm giá thành công!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
		                }
		            
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
		        }
		    }
		});
		
		JLabel lblMSchGim = new JLabel("Mã sách giảm giá");
		lblMSchGim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMSchGim.setBounds(572, 349, 233, 28);
		add(lblMSchGim);
		
		comboBox_sach = new JComboBox<String>();
		comboBox_sach.setBounds(895, 351, 274, 28);
		add(comboBox_sach);
		Sach_BUS.loadDataIntoComboBox_maSach(comboBox_sach);
		
		JLabel lblPhnTrmGim = new JLabel("Phần trăm giảm giá (%)");
		lblPhnTrmGim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhnTrmGim.setBounds(572, 387, 250, 28);
		add(lblPhnTrmGim);
		
		textField_phanTramGiam = new JTextField();
		textField_phanTramGiam.setBounds(895, 389, 318, 28);
		add(textField_phanTramGiam);
		textField_phanTramGiam.setColumns(10);
		
		JLabel lblMChiTit = new JLabel("Mã chi tiết chương trình giảm giá");
		lblMChiTit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMChiTit.setBounds(573, 277, 312, 28);
		add(lblMChiTit);
		
		textField_maCTCTGG = new JTextField();
		textField_maCTCTGG.setColumns(10);
		textField_maCTCTGG.setBounds(895, 277, 318, 32);
		add(textField_maCTCTGG);
		
		JLabel lblMChngTrnh = new JLabel("Mã chương trình giảm giá");
		lblMChngTrnh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMChngTrnh.setBounds(573, 315, 286, 28);
		add(lblMChngTrnh);
		
		comboBox_maCTGG = new JComboBox<String>();
		CTGG_BUS.loadDataIntoComboBox_maCTGG(comboBox_maCTGG);
		comboBox_maCTGG.setBounds(895, 315, 318, 28);
		add(comboBox_maCTGG);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 262, 537, 403);
		add(scrollPane);
		
		table_giamGia = new JTable();
		//table_giamGia.setCellSelectionEnabled(true);
		//table_giamGia.setBorder(new CompoundBorder());
		table_giamGia.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã chương trình", "Tên chương trình",
				"Ngày bắt đầu", "Ngày kết thúc"
			}
		));
		scrollPane.setViewportView(table_giamGia);
	//	TableColumnModel columnModel1 = table_giamGia.getColumnModel();
//		columnModel1.getColumn(0).setPreferredWidth(200); // Adjust the width of the first column
//		columnModel1.getColumn(1).setPreferredWidth(200); // Adjust the width of the second column
//		columnModel1.getColumn(2).setPreferredWidth(100); // Adjust the width of the third column
//		columnModel1.getColumn(3).setPreferredWidth(100); 

		table_giamGia.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  int selectedRow = table_giamGia.getSelectedRow();
			        if (selectedRow != -1 && e.getClickCount() == 1) {
			            DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
			            String maCTGG = model.getValueAt(selectedRow, 0).toString(); // Lấy mã của chi tiết giảm giá
			            textField_maCTGG.setText(model.getValueAt(selectedRow, 0).toString());
			            textField_tenCTGG.setText(model.getValueAt(selectedRow, 1).toString());
			            try {
			                String dateString = model.getValueAt(selectedRow, 2).toString();
			                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			                Date ngayBatDau = dateFormat.parse(dateString);
			                dateChooser_ngaybatDau.setDate(ngayBatDau);
			            } catch (ParseException ex) {
			                ex.printStackTrace();
			            }
			            try {
			                String dateString = model.getValueAt(selectedRow, 3).toString();
			                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			                Date ngayKetThuc = dateFormat.parse(dateString);
			                dateChooser_ngayketThuc.setDate(ngayKetThuc);
			            } catch (ParseException ex) {
			                ex.printStackTrace();
			            }

			            // Load danh sách chi tiết tương ứng với mã chi tiết giảm giá
			            ArrayList<ChiTietCTGG_DTO> chiTietList = null;
			            try {
			                chiTietList = ChiTietCTGG_BUS.layDanhSachChiTietCTGG(maCTGG);
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }

			            // Hiển thị danh sách chi tiết trên table_chiTietCTGG
			            DefaultTableModel chiTietModel = (DefaultTableModel) table_chiTietCTGG.getModel();
			            chiTietModel.setRowCount(0);
			            int i = 0;
			            for (ChiTietCTGG_DTO chiTiet : chiTietList) {
			                chiTietModel.addRow(new Object[] { 
			                    chiTiet.getMaChiTietCTGG(),
			                    chiTiet.getMaCTGG(),
			                    chiTiet.getPhanTramGiamGia(),
			                    chiTiet.getMaSach()    
			                });
			                i++;
			            }
			            label_soLuongChiTiet.setText(String.valueOf(i));
			        }
			    }   	
		});
	
		JLabel lblNgyBtu = new JLabel("Ngày bắt đầu");
		lblNgyBtu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyBtu.setBounds(565, 108, 123, 28);
		add(lblNgyBtu);
		
		dateChooser_ngayBatDauTimKiem = new JDateChooser();
		dateChooser_ngayBatDauTimKiem.setBounds(698, 108, 174, 28);
		add(dateChooser_ngayBatDauTimKiem);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày kết thúc");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(888, 112, 112, 21);
		add(lblNewLabel_7);
		
		dateChooser_ngayKetThucTimKiem = new JDateChooser();
		dateChooser_ngayKetThucTimKiem.setBounds(1030, 112, 183, 24);
		add(dateChooser_ngayKetThucTimKiem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(572, 467, 638, 198);
		add(scrollPane_1);
		
		table_chiTietCTGG = new JTable();
		table_chiTietCTGG.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã chi tiết CTGG", "Mã chương trình giảm giá", "Phần trăm giảm (%)", "Mã sách"
			}
		));
		TableColumnModel columnModel11 = table_chiTietCTGG.getColumnModel();
		columnModel11.getColumn(0).setPreferredWidth(200); // Adjust the width of the first column
		columnModel11.getColumn(1).setPreferredWidth(200); // Adjust the width of the second column
		columnModel11.getColumn(2).setPreferredWidth(100); // Adjust the width of the third column
		columnModel11.getColumn(3).setPreferredWidth(100); 

		table_chiTietCTGG.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table_chiTietCTGG.getSelectedRow();
              
				if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_chiTietCTGG.getModel();
                    textField_maCTCTGG.setText(model.getValueAt(selectedRow, 0).toString());
                    comboBox_sach.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
		            comboBox_maCTGG.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
                    textField_phanTramGiam.setText(model.getValueAt(selectedRow, 2).toString());               
                }
            }
        });
		
		
		scrollPane_1.setViewportView(table_chiTietCTGG);
	  
		JButton btnNewButton_1 = new JButton("Xem tất cả chi tiết");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(1017, 683, 200, 31);
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadTableData_Chitiet();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNhp = new JButton("Nhập");
		
		btnNhp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhp.setBounds(309, 686, 123, 28);
		add(btnNhp);
		 ImageIcon icon_thongKe11 = new ImageIcon("src\\icon\\excel.png");
		 Image image_thongKe11 = icon_thongKe11.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		 ImageIcon scaledIcon2 = new ImageIcon(image_thongKe11);
		 btnNhp.setIcon(scaledIcon2);
	
		 btnNhp.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();   
	                    NhapXuatExcel.readExcelToTable(file, table_giamGia);
	                }
	            }
	        });
		 
		 
		JButton btnXut = new JButton("Xuất");
		btnXut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXut.setBounds(442, 686, 105, 28);
		add(btnXut);
		ImageIcon icon_thongKe2 = new ImageIcon("src\\icon\\excel.png");
		Image image_thongKe2 = icon_thongKe2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		 ImageIcon scaledIcon21 = new ImageIcon(image_thongKe2);
		 btnXut.setIcon(scaledIcon21);
		 btnXut.addActionListener(new ActionListener() {	        
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();
	                    String filePath = file.getAbsolutePath();
	                    // Gọi hàm xuất dữ liệu ra file Excel
	                    NhapXuatExcel.exportDataToExcel(table_giamGia, filePath, "Quản lý chương trình giảm giá Sheet");
	                 
	                    JOptionPane.showMessageDialog(contentPane, "Xuất dữ liệu ra file Excel thành công!");
	                }
	            }
	        });
		 
		 
		JButton button_xoa = new JButton("Xóa");
		button_xoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_xoa.setBounds(200, 212, 110, 28);
		add(button_xoa);
		button_xoa.setBackground(new Color(220, 20, 60));
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon211 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon211);
	    button_xoa.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String maCTGG = textField_maCTGG.getText();
	            if (maCTGG.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
	                return;
	            }

	            try {
	                boolean hasDetails = CTGG_BUS.hasDetails(maCTGG); // Gọi phương thức từ BUS để kiểm tra chi tiết CTGG
	                if (hasDetails) {
	                    JOptionPane.showMessageDialog(null, "Không thể xóa vì còn chi tiết CTGG liên quan!");
	                    return;
	                }

	                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa Chương trình giảm giá này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
	                if (confirm == JOptionPane.YES_OPTION) {
	                    boolean deleted = CTGG_BUS.deleteCTGG(maCTGG); // Gọi phương thức từ BUS để xóa CTGG
	                    if (deleted) {
	                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
	                        loadTableData(); // Gọi phương thức từ GUI để nạp lại dữ liệu
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Xóa không thành công!");
	                    }
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Lỗi khi thực hiện thao tác xóa CTGG!");
	            }
	        }
	    });

	   
		JButton button_Sua1 = new JButton("Sửa");
		button_Sua1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_chiTietCTGG.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        try {
		            String maCTGG = comboBox_maCTGG.getSelectedItem().toString();
		            String maChiTietCTGG = textField_maCTCTGG.getText();
		            String maSach = comboBox_sach.getSelectedItem().toString();
		            String PhanTramGiam = textField_phanTramGiam.getText();

		            ChiTietCTGG_DTO ChiTietCTGG = new ChiTietCTGG_DTO(maChiTietCTGG,
		            		maCTGG, 	Integer.parseInt(PhanTramGiam), maSach);

		          
		            boolean updated = ChiTietCTGG_BUS.updateChiTietCTGG(ChiTietCTGG);

		            if (updated) {	
		                JOptionPane.showMessageDialog(null, "Sửa thông tin chi tiết chương trình giảm giá thành công!");
		                loadTableData();
		                loadTableData_Chitiet();
		            } else {
		                JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho lương!");
		        } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		    }
		});
		
		
		button_Sua1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_Sua1.setBounds(905, 427, 115, 30);

		button_Sua1.setBackground(new Color(255, 255, 169));
		 ImageIcon icon4 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image4 = icon4.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon4 = new ImageIcon(image4);
	        button_Sua1.setIcon(scaledIcon4);
		add(button_Sua1);
		
		JButton button_xoa1 = new JButton("Xóa");
		button_xoa1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_xoa1.setBackground(new Color(220, 20, 60));
		ImageIcon icon21 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image21 = icon21.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2111 = new ImageIcon(image21);
	    button_xoa1.setIcon(scaledIcon2111);
	
	    button_xoa1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maKhachHang = textField_maCTCTGG.getText(); // Lấy mã khách hàng từ JTextField
		        if (maKhachHang.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
		            return;
		        }
		        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chi tiết CTGG này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean deleted = false;
					try {
						deleted = ChiTietCTGG_BUS.deleteChiTietCTGG(maKhachHang);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} 
		            if (deleted) {
		                JOptionPane.showMessageDialog(null, "Xóa thành công!");
		               try {
						loadTableData_Chitiet();
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa không thành công!");
		            }
		        }
		    }
		});
	
		button_xoa1.setBounds(1106, 427, 105, 30);
		add(button_xoa1);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(220, 20, 60));
		lblNewLabel.setBounds(802, 53, 174, 37);
		add(lblNewLabel);
		
		JLabel lblMGimGim = new JLabel("Mã giảm giá");
		lblMGimGim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMGimGim.setBounds(565, 153, 112, 28);
		add(lblMGimGim);
		
		textField_maGiamGiaTimKiem = new JTextField();
		textField_maGiamGiaTimKiem.setColumns(10);
		textField_maGiamGiaTimKiem.setBounds(698, 156, 173, 28);
		add(textField_maGiamGiaTimKiem);
		
		JLabel lblTnChngTrnh_1 = new JLabel("Tên chương trình");
		lblTnChngTrnh_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnChngTrnh_1.setBounds(888, 153, 144, 28);
		add(lblTnChngTrnh_1);
		
		textField_tenChuongTrinhTimKiem = new JTextField();
		textField_tenChuongTrinhTimKiem.setColumns(10);
		textField_tenChuongTrinhTimKiem.setBounds(1031, 156, 182, 28);
		add(textField_tenChuongTrinhTimKiem);
		
		JButton button_tim = new JButton("Tìm kiếm");
		button_tim.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_tim.setBackground(new Color(255, 255, 169));
		 ImageIcon icon12 = new ImageIcon("src\\icon\\loupe.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon12 = new ImageIcon(image12);
	        button_tim.setIcon(scaledIcon12);
		add(button_tim);
		button_tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        String maCTGG = textField_maGiamGiaTimKiem.getText();
		       
		        String tenCTGG = textField_tenChuongTrinhTimKiem.getText();
		        java.sql.Date ngayBatDau = null;
		        if (dateChooser_ngayBatDauTimKiem.getDate() != null) {
		            ngayBatDau = new java.sql.Date(dateChooser_ngayBatDauTimKiem.getDate().getTime());
		        }
		        java.sql.Date ngayKetThuc = null;
		        if (dateChooser_ngayKetThucTimKiem.getDate() != null) {
		            ngayKetThuc = new java.sql.Date(dateChooser_ngayKetThucTimKiem.getDate().getTime());
		        }

		        searchAndDisplayFromDatabase(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
		    }

		    private void searchAndDisplayFromDatabase(String maCTGG, String tenCTGG, java.sql.Date ngayBatDau,
		            java.sql.Date ngayKetThuc) {
		        ResultSet resultSet = null;
		        try {
		            resultSet = CTGG_BUS.searchAndDisplayFromDatabase(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
		        model.setRowCount(0);	      
		        try {
		            while (resultSet.next()) {
		                Object[] rowData = new Object[4];
		                rowData[0] = resultSet.getString("maCTGG");
		                rowData[1] = resultSet.getString("tenCTGG");	             
		                rowData[2] = resultSet.getDate("NgayBatDau"); // Lấy dữ liệu ngày dưới dạng Date
		                rowData[3] = resultSet.getDate("NgayKetThuc"); // Lấy dữ liệu ngày dưới dạng Date
		                model.addRow(rowData);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	            ArrayList<ChiTietCTGG_DTO> chiTietList = null;
				try {
					chiTietList = ChiTietCTGG_BUS.layDanhSachChiTietCTGG(maCTGG);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
                DefaultTableModel chiTietModel = (DefaultTableModel) table_chiTietCTGG.getModel();
	            chiTietModel.setRowCount(0);
	            
	            // Thêm các chi tiết sách vào bảng chi tiết sách
	            for (ChiTietCTGG_DTO chiTiet : chiTietList) {
	                chiTietModel.addRow(new Object[] { 
	                		chiTiet.getMaChiTietCTGG(),
	                		chiTiet.getMaCTGG(),
	                		chiTiet.getPhanTramGiamGia(),
	                		chiTiet.getMaSach()    
	                });	               
	            }
		    }
		});
		button_tim.setBounds(819, 194, 144, 28);
		add(button_tim);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(1179, 349, 34, 28);
		ImageIcon icon3 = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon3 = new ImageIcon(image3);
	    btnNewButton.setIcon(scaledIcon3);
		add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Create and display the new frame
	                TimKiemSach_GUI employeeListFrame = new TimKiemSach_GUI();
	                employeeListFrame.setVisible(true);
	                employeeListFrame.loadTableData();
	            }
	        });
		
		JButton button_themCT = new JButton("Thêm");
		button_themCT.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_themCT.setBounds(698, 427, 110, 30);
		add(button_themCT);
		 ImageIcon icon11 = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image11 = icon11.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon11 = new ImageIcon(image11);
	    button_themCT.setIcon(scaledIcon11);
	    button_themCT.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		button_themCT.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String maChiTietCTGG = textField_maCTCTGG.getText();
		        String maCTGG = comboBox_maCTGG.getSelectedItem().toString();
		        int donGia = 0 ; 
		        if (textField_phanTramGiam.getText().isEmpty()) {
		        	donGia = 0; 
		       
		        } 
		        else { 
		        	try { 
		        		donGia = Integer.parseInt(textField_phanTramGiam.getText()); 
		        	} 
		        	catch (NumberFormatException e1) {
		        
		        	JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ"); 
		        	}
		        }

		        String maSach = comboBox_sach.getSelectedItem().toString();
		          
		        ChiTietCTGG_DTO ctgg =new ChiTietCTGG_DTO(maChiTietCTGG, maCTGG, donGia, maSach);
		
		        ChiTietCTGG_BUS ChiTietctggBus = new ChiTietCTGG_BUS();
		      	boolean success= false;
				try {
					success = ChiTietctggBus.addCTGG(ctgg);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      	if (success == true) {
		            DefaultTableModel model = (DefaultTableModel) table_chiTietCTGG.getModel();
		            model.addRow(new Object[]{maChiTietCTGG, maCTGG, donGia, maSach});
		            JOptionPane.showMessageDialog(null, "Thêm chi tiết chương trình giảm giá thành công");
		            model.setRowCount(0);
		            try {
						loadTableData_Chitiet();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		      	else {
		      		 JOptionPane.showMessageDialog(null, "Thêm chi tiết chương trình giảm giá thất bại");
		      	}
		    }
		    
		});
		
		
		JLabel lblNewLabel_3 = new JLabel("Chi tiết chương trình khuyến mãi");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(128, 128, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(573, 232, 638, 28);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Số chi tiết chương trình giảm giá:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(582, 685, 265, 28);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số chương trình giảm giá:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 686, 200, 28);
		add(lblNewLabel_1_1);
		
		label_soLuongChiTiet = new JLabel("");
		label_soLuongChiTiet.setBounds(857, 686, 73, 28);
		add(label_soLuongChiTiet);
		label_soLuongChiTiet.setOpaque(true);
		label_soLuongChiTiet.setBackground(new Color(220, 220, 220));
		
		label_soLuongGiamGia = new JLabel("");
		label_soLuongGiamGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_soLuongGiamGia.setBounds(213, 686, 66, 28);
		add(label_soLuongGiamGia);
		label_soLuongGiamGia.setOpaque(true);
		label_soLuongGiamGia.setBackground(new Color(220, 220, 220));

		button_sua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_giamGia.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		      
		        String maCTGG = textField_maCTGG.getText();
		        String tenCTGG = textField_tenCTGG.getText();
		        Date batDauDate = dateChooser_ngaybatDau.getDate();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String batDauFormatted = dateFormat.format(batDauDate);
		     
		        Date ketThucDate = dateChooser_ngayketThuc.getDate();
		        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		        String ketThucFormatted = dateFormat1.format(ketThucDate);
		        
		        // Lấy mô hình của bảng và cập nhật hàng đã chọn với dữ liệu mới
		        DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
		        model.setValueAt(maCTGG, selectedRow, 0);
		        model.setValueAt(tenCTGG, selectedRow, 1);
		        model.setValueAt(batDauFormatted, selectedRow, 2);
		        model.setValueAt(ketThucFormatted, selectedRow, 3);

		    }
		});
		
	}
	
	private void them() {
		String maCTGG = textField_maCTGG.getText();
		String tenCTGG = textField_tenCTGG.getText();
		Date batDauDate = dateChooser_ngaybatDau.getDate();
		Date ketThucDate = dateChooser_ngayketThuc.getDate();

		CTGG_DTO ctgg = new CTGG_DTO(maCTGG, tenCTGG, batDauDate, ketThucDate);
    
		CTGG_BUS ctggBus = new CTGG_BUS();
		boolean success= false;
		try {
			success = ctggBus.addCTGG(ctgg);
		} catch (ParseException e1) {
			e1.printStackTrace();
			} catch (SQLException e1) {
		e1.printStackTrace();
			}
		if (success == true) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày mới
			String ngayLapFormatted = formatter.format(batDauDate);
			String ngayLapFormatted1 = formatter.format(ketThucDate);           
			DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
			model.addRow(new Object[]{maCTGG, tenCTGG, ngayLapFormatted, ngayLapFormatted1});
			comboBox_maCTGG.removeAllItems();
			try {
			CTGG_BUS.loadDataIntoComboBox_maCTGG(comboBox_maCTGG);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			JOptionPane.showMessageDialog(null, "Thêm chương trình giảm giá thành công");
			model.setRowCount(0);
			try {
			loadTableData();
			} catch (SQLException e1) {	
			e1.printStackTrace();
		}
		}
		else {
  		 JOptionPane.showMessageDialog(null, "Thêm chương trình giảm giá thất bại");
		}
	}
	 public static void loadTableData() throws SQLException {
	        DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
	        model.setRowCount(0); 
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<CTGG_DTO> cTGGList = CTGG_BUS.getAllCTGG();

	        for (CTGG_DTO ctgg : cTGGList) {
	            String ngaySinhFormatted_batDau = dateFormat.format(ctgg.getThoiGianBatDau().getTime());
	            String ngaySinhFormatted_ketThuc = dateFormat.format(ctgg.getThoiGianKetThuc().getTime());
	            model.addRow(new Object[] { ctgg.getMaCTGG() , ctgg.getTenCTGG() , 
	            		ngaySinhFormatted_batDau,ngaySinhFormatted_ketThuc	});
	        }
	        hienThongKe();
	  
	 }
	 public static void loadTableData(JTable tabel) throws SQLException {
	        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
	        model.setRowCount(0); 
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<CTGG_DTO> cTGGList = CTGG_BUS.getAllCTGG();

	        for (CTGG_DTO ctgg : cTGGList) {
	            String ngaySinhFormatted_batDau = dateFormat.format(ctgg.getThoiGianBatDau().getTime());
	            String ngaySinhFormatted_ketThuc = dateFormat.format(ctgg.getThoiGianKetThuc().getTime());
	            model.addRow(new Object[] { ctgg.getMaCTGG() , ctgg.getTenCTGG() , 
	            		ngaySinhFormatted_batDau,ngaySinhFormatted_ketThuc	});
	        }
	 }
	 public void loadTableData_Chitiet() throws SQLException {
	        DefaultTableModel model = (DefaultTableModel) table_chiTietCTGG.getModel();
	        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
	        ArrayList<ChiTietCTGG_DTO> chiTietCTGGList = ChiTietCTGG_BUS.getAllChiTietCTGG();
	        for (ChiTietCTGG_DTO ctgg : chiTietCTGGList) {
	            model.addRow(new Object[] { ctgg.getMaChiTietCTGG() , ctgg.getMaCTGG() , ctgg.getPhanTramGiamGia(), ctgg.getMaSach()});
	        }
	        int rowCount = model.getRowCount();    
	        label_soLuongChiTiet.setText(rowCount+"") ;    
	 }

	 public static void setSachChoCombobox(String maNhanVien) {
		 if(comboBox_sach == null)
			 return ; 
		 comboBox_sach.setSelectedItem(maNhanVien);
	  }
	  private static void hienThongKe() {
	            DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
	            int rowCount = model.getRowCount();    
	            label_soLuongGiamGia.setText(rowCount+"") ; 
	  } 
}


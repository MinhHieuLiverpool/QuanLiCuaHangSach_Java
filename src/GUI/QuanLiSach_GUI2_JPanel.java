package GUI  ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import BUS.Sach_BUS;
import DTO.Sach_DTO;
import Database.ConnectionManager;
import NhapXuatEXCEL.NhapXuatExcel;
import PDF.PDFiText;
import java.awt.Font;
public class QuanLiSach_GUI2_JPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maSach;
	private JTextField textField_tenSach;
	private JTextField textField_namXuatBan;
	private JTextField textField_soLuong;
	private JTextField textField_donGia;
	private static JTable table;
	private JTextField textField_maSach1;
	private JTextField textField_tenSach1;
	private JTextField textField_tu;
	private JTextField textField_den;
	private JComboBox<String> comboBox_maNXB;
	private JComboBox<String> comboBox_maTheLoai;
	private JComboBox<String> comboBox_maTacGia;
	private JLabel label_hinhAnh ;
	private DefaultListModel<String> suggestionListModel;
	private JComboBox<String> comboBoxSortBy;
	private JComboBox<String> comboBoxSortOrder;
	  private static Connection connection = ConnectionManager.openConnection(); 
	private DefaultListModel <String >listModel;
	
	private JList <String> list_maTheLoai;
	private JList<String> list_maNXB;
	private DefaultListModel<String> listModel1;
	private DefaultListModel <String>listModel12;
	private JList <String> list_maTacGia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                QuanLiSach_GUI2_JPanel frame = new QuanLiSach_GUI2_JPanel();
	                frame.setVisible(true);
	                // Load dữ liệu từ cơ sở dữ liệu khi ứng dụng khởi chạy
	                QuanLiSach_GUI2_JPanel.loadTableData(table);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	public QuanLiSach_GUI2_JPanel() {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		JLabel lblQunLSch = new JLabel("QUẢN LÍ SÁCH");
		lblQunLSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLSch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunLSch.setBounds(10, 0, 1166, 30);
		add(lblQunLSch);
		
		JLabel lblMScg = new JLabel("Mã sách");
		lblMScg.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMScg.setBounds(10, 40, 69, 30);
		add(lblMScg);
		
		textField_maSach = new JTextField();
		textField_maSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_maSach.setBounds(173, 40, 261, 30);
		add(textField_maSach);
		textField_maSach.setColumns(10);
		
		JLabel lblMNhXut = new JLabel("Mã nhà xuất bản");
		lblMNhXut.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMNhXut.setBounds(10, 81, 144, 28);
		add(lblMNhXut);
		
		comboBox_maNXB = new JComboBox<String>();
		comboBox_maNXB.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox_maNXB.setBounds(173, 80, 261, 30);
		add(comboBox_maNXB);
		Sach_BUS.loadDataIntoList_maNXB(comboBox_maNXB);
	
		JLabel lblMThLoi = new JLabel("Mã thể loại");
		lblMThLoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMThLoi.setBounds(10, 119, 108, 28);
		add(lblMThLoi);
		
		comboBox_maTheLoai = new JComboBox<String>();
		comboBox_maTheLoai.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox_maTheLoai.setBounds(173, 120, 261, 30);
		add(comboBox_maTheLoai);
		Sach_BUS.loadDataIntoComboBox_maTheLoai(comboBox_maTheLoai);
	
		JLabel lblMTcGi = new JLabel("Mã tác giả");
		lblMTcGi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMTcGi.setBounds(10, 157, 108, 28);
		add(lblMTcGi);
		
		comboBox_maTacGia = new JComboBox<String>();
		comboBox_maTacGia.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox_maTacGia.setBounds(173, 160, 261, 30);
		add(comboBox_maTacGia);
		Sach_BUS.loadDataIntoComboBox_maTacGia(comboBox_maTacGia);
		JLabel lblTnSch_1 = new JLabel("Tên sách");
		lblTnSch_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTnSch_1.setBounds(10, 201, 108, 28);
		add(lblTnSch_1);
		
		JLabel lblNmXutBn = new JLabel("Năm xuất bản");
		lblNmXutBn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNmXutBn.setBounds(10, 245, 141, 28);
		add(lblNmXutBn);
		
		JLabel label_soLuong = new JLabel("Số lượng");
		label_soLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_soLuong.setBounds(10, 283, 108, 28);
		add(label_soLuong);
		
		JLabel label_donGia = new JLabel("Đơn giá");
		label_donGia.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_donGia.setBounds(10, 321, 108, 28);
		add(label_donGia);
		
		textField_tenSach = new JTextField();
		textField_tenSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_tenSach.setBounds(173, 200, 261, 30);
		textField_tenSach.setColumns(10);
		add(textField_tenSach);
		
		textField_namXuatBan = new JTextField();
		textField_namXuatBan.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_namXuatBan.setBounds(173, 244, 261, 30);
		textField_namXuatBan.setColumns(10);
		add(textField_namXuatBan);
		
		textField_soLuong = new JTextField();
		textField_soLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_soLuong.setBounds(173, 282, 261, 30);
		textField_soLuong.setColumns(10);
		add(textField_soLuong);
		
		textField_donGia = new JTextField();
		textField_donGia.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_donGia.setBounds(173, 320, 261, 30);
		textField_donGia.setColumns(10);
		add(textField_donGia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 390, 745, 316);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "M\u00E3 NXB", 
				"M\u00E3 Th\u1EC3 Lo\u1EA1i", "M\u00E3 T\u00E1c Gi\u1EA3", 
				"T\u00EAn S\u00E1ch", "N\u0103m xu\u1EA5t b\u1EA3n", 
				"S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Hình ảnh"
			}
		));
		
		comboBoxSortBy = new JComboBox<>();
		comboBoxSortBy.setBounds(814,350,224,30);
		comboBoxSortBy.addItem("Sắp xếp theo: Mã Sách");
	    comboBoxSortBy.addItem("Sắp xếp theo: Đơn giá");
	    comboBoxSortBy.addItem("Sắp xếp theo: Năm xuất bản");
	        comboBoxSortBy.addItem("Sắp xếp theo: Số lượng");
	        add(comboBoxSortBy);

	        comboBoxSortOrder = new JComboBox<>();
	        comboBoxSortOrder.setBounds(1048,350,175,30);
	        comboBoxSortOrder.addItem("Tăng Dần");
	        comboBoxSortOrder.addItem("Giảm Dần");
	        add(comboBoxSortOrder);

	        comboBoxSortBy.addActionListener(e ->sortTable());

	        comboBoxSortOrder.addActionListener(e -> sortTable());

		
		TableColumn columnName = table.getColumnModel().getColumn(4); // Index 4 là cột "Tên Sách"

		// Đặt chiều rộng ưu tiên cho cột "Tên Sách"
		columnName.setPreferredWidth(300); // Thay đổi 300 thành số phù hợp với chiều rộng mong muốn của bạn

		scrollPane.setViewportView(table); // Đặt JTable trong JScrollPane
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) { 	
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            textField_maSach.setText(model.getValueAt(selectedRow, 0).toString());
		            comboBox_maNXB.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		            comboBox_maTheLoai.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
		            comboBox_maTacGia.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
		            textField_tenSach.setText(model.getValueAt(selectedRow, 4).toString());
		            textField_namXuatBan.setText(model.getValueAt(selectedRow, 5).toString());
		            textField_soLuong.setText(model.getValueAt(selectedRow, 6).toString());
		            textField_donGia.setText(model.getValueAt(selectedRow, 7).toString()); 
		            String imagePath = model.getValueAt(selectedRow, 8).toString();
		           showImageOnLabel(imagePath);
		        }
		    }
		});
				
		JLabel lblNewLabel_6 = new JLabel("Tìm Kiếm Sách");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_6.setBounds(763, 46, 251, 19);
		add(lblNewLabel_6);
		
		JLabel lblMSch = new JLabel("MÃ SÁCH");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMSch.setBounds(478, 82, 86, 29);
		add(lblMSch);
		
		textField_maSach1 = new JTextField();
		textField_maSach1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_maSach1.setBounds(620, 79, 203, 32);
		add(textField_maSach1);
		textField_maSach1.setColumns(10);
		
		JLabel label_maNXB1 = new JLabel("Mã NXB");
		label_maNXB1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maNXB1.setBounds(899, 79, 86, 19);
		add(label_maNXB1);
		
		
		JLabel label_maTheLoai1 = new JLabel("Mã Thể Loại");
		label_maTheLoai1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maTheLoai1.setBounds(478, 124, 116, 29);
		add(label_maTheLoai1);
		
		JLabel label_maTacGia1 = new JLabel("Mã Tác Giả");
		label_maTacGia1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maTacGia1.setBounds(899, 121, 108, 19);
		add(label_maTacGia1);
		
		JLabel lblTnSch = new JLabel("Tên Sách");
		lblTnSch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTnSch.setBounds(478, 173, 108, 25);
		add(lblTnSch);
		
		textField_tenSach1 = new JTextField();
		textField_tenSach1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_tenSach1.setBounds(623, 165, 586, 32);
		add(textField_tenSach1);
		textField_tenSach1.setColumns(10);
		
		label_hinhAnh = new JLabel("Hình Ảnh Sách");
		label_hinhAnh.setBounds(37, 359, 261, 400);
		label_hinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_hinhAnh);

		JLabel lblGi = new JLabel("Giá");
		lblGi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGi.setBounds(478, 250, 46, 23);
		add(lblGi);
		
		JLabel lblNewLabel_13 = new JLabel("Từ");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_13.setBounds(623, 249, 56, 19);
		add(lblNewLabel_13);
		
		textField_tu = new JTextField();
		textField_tu.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_tu.setBounds(733, 236, 167, 28);
		add(textField_tu);
		textField_tu.setColumns(10);
		
		JLabel lblNewLabel_den = new JLabel("Đến");
		lblNewLabel_den.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_den.setBounds(923, 236, 45, 28);
		add(lblNewLabel_den);
		
		textField_den = new JTextField();
		textField_den.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_den.setBounds(978, 235, 231, 30);
		add(textField_den);
		textField_den.setColumns(10);
		
		JButton button_tim = new JButton("Tìm");
		button_tim.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_tim.setBounds(1069, 275, 141, 39);
		add(button_tim);
		button_tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					searchAndUpdate();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		    }
		});

		button_tim.setBackground(Color.CYAN);
        ImageIcon icon3 = new ImageIcon("src\\icon\\find.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon3 = new ImageIcon(image3);
        button_tim.setIcon(scaledIcon3);
      
		JButton button_them = new JButton("Thêm");
		button_them.setBounds(322, 576, 125, 39);
		add(button_them);
		button_them.setBackground(new Color(153, 204, 153)); // Màu xanh lục nhạt

	        ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon = new ImageIcon(image);
	        button_them.setIcon(scaledIcon);
	        button_them.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String maSach = textField_maSach.getText();
	                    String maNXB = comboBox_maNXB.getSelectedItem().toString();
	                    String maTheLoai = comboBox_maTheLoai.getSelectedItem().toString();
	                    String maTacGia = comboBox_maTacGia.getSelectedItem().toString();
	                    String tenSach = textField_tenSach.getText();
	                    int namXuatBan = Integer.parseInt(textField_namXuatBan.getText());
	                    int soLuong = Integer.parseInt(textField_soLuong.getText());
	                    double donGia = Double.parseDouble(textField_donGia.getText());
	                    String imagePath = label_hinhAnh.getText();

	                    Sach_DTO sach = new Sach_DTO(maSach, maNXB, maTheLoai, maTacGia, 
	                    		tenSach, namXuatBan, soLuong, donGia, imagePath);

	                    Sach_BUS sachBUS = new Sach_BUS(connection);
	                    boolean success = sachBUS.addBook(sach);
	                    if (success) {
	                        JOptionPane.showMessageDialog(null, "Thêm sách thành công");
	                        refreshTable(); 
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Thêm sách thất bại");
	                    }
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho năm xuất bản, số lượng và đơn giá");
	                }
	            }
	        });
	    
		JButton button_sua = new JButton("Sửa");
		button_sua.setBounds(322, 636, 125, 39);
		add(button_sua);
		
		button_sua.setBackground(Color.ORANGE); // Màu đỏ nhạt
		button_sua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            try {
		                String maSach = textField_maSach.getText();
		                Object maNXB = comboBox_maNXB.getSelectedItem();
		                Object maTheLoai = comboBox_maTheLoai.getSelectedItem();
		                Object maTacGia = comboBox_maTacGia.getSelectedItem();
		                String tenSach = textField_tenSach.getText();
		                String namXuatBan = textField_namXuatBan.getText();
		                String soLuong = textField_soLuong.getText();
		                String donGia = textField_donGia.getText();
		                String hinhAnh = label_hinhAnh.getText();
		                Sach_DTO sach = new Sach_DTO(maSach, maNXB.toString(),
		                		maTheLoai.toString(), maTacGia.toString(),
		                		tenSach, Integer.parseInt(namXuatBan),
		                		Integer.parseInt(soLuong), Double.parseDouble(donGia), hinhAnh);
		                
		                boolean updated = Sach_BUS.updateBook(sach);
		                if (updated) {
		                    DefaultTableModel model = (DefaultTableModel) table.getModel();
		                    model.setValueAt(maNXB, selectedRow, 1);
		                    model.setValueAt(maTheLoai, selectedRow, 2);
		                    model.setValueAt(maTacGia, selectedRow, 3);
		                    model.setValueAt(tenSach, selectedRow, 4);
		                    model.setValueAt(namXuatBan, selectedRow, 5);
		                    model.setValueAt(soLuong, selectedRow, 6);
		                    model.setValueAt(donGia, selectedRow, 7);
		                    model.setValueAt(hinhAnh, selectedRow, 8);
		                    
		                    JOptionPane.showMessageDialog(null, "Sửa thông tin sách thành công!");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho năm xuất bản và số lượng!");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
		        }
		    }
		});
        ImageIcon icon1 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon1 = new ImageIcon(image1);
        button_sua.setIcon(scaledIcon1);
	
		
   

		JButton button_xoa = new JButton("Xóa");
		button_xoa.setBounds(322, 700, 125, 39);
		add(button_xoa);
		button_xoa.setBackground(Color.RED); // Màu đỏ nhạt
		button_xoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maSach = textField_maSach.getText(); // Lấy mã sách từ JTextField
		        boolean deleted = Sach_BUS.deleteBook(maSach); // Gọi phương thức deleteSach để xóa sách
		        if (deleted) {
		            JOptionPane.showMessageDialog(null, "Xóa thành công!");
		            // Reload dữ liệu từ cơ sở dữ liệu và đặt lại model cho bảng hiển thị
		            loadTableData(table); // Thay thế loadDataIntoTable bằng phương thức tương ứng của bạn để load lại dữ liệu
		        } else {
		            JOptionPane.showMessageDialog(null, "Xóa không thành công!");
		        }
		    }
		});


        ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon2 = new ImageIcon(image2);
        button_xoa.setIcon(scaledIcon2);
		// button_xoa.addActionListener(e -> xoaDuLieu());
		
		JButton button_lamMoi = new JButton("Làm Mới");
		
		button_lamMoi.setBounds(478, 716, 140, 43);
		add(button_lamMoi);
		button_lamMoi.addActionListener(e -> 	clearFields());
		button_lamMoi.setBackground(Color.LIGHT_GRAY); // Màu đỏ nhạt

        ImageIcon icon_lamMoi = new ImageIcon("src\\icon\\load.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image_lamMoi = icon_lamMoi.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon_lamMoi = new ImageIcon(image_lamMoi);
        button_lamMoi.setIcon(scaledIcon_lamMoi);
	
		
		JButton button_xuatExcel = new JButton("Xuất EXCEL");
		button_xuatExcel.setBounds(1090, 720, 144, 39);
		add(button_xuatExcel);
        ImageIcon icon11 = new ImageIcon("src\\icon\\excel.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image11 = icon11.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        button_xuatExcel.setIcon(new ImageIcon(image11));
        button_xuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int returnVal = jFileChooser.showSaveDialog(contentPane);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    // Gọi hàm xuất dữ liệu ra file Excel
                    NhapXuatExcel.exportDataToExcel(table, filePath, "Quản lí sách");
                    // Hiển thị thông báo sau khi xuất dữ liệu
                    JOptionPane.showMessageDialog(contentPane, "Xuất dữ liệu ra file Excel thành công!");
                }
            }
        });

		JButton button_nhapExcel = new JButton("Nhập EXCEL");
		button_nhapExcel.setBounds(927, 720, 142, 39);
		add(button_nhapExcel);
		 ImageIcon icon_thongKe1 = new ImageIcon("src\\icon\\excel.png");
		    Image image_thongKe1 = icon_thongKe1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		    button_nhapExcel.setIcon(new ImageIcon(image_thongKe1));
		    button_nhapExcel.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            JFileChooser jFileChooser = new JFileChooser();
		            int returnVal = jFileChooser.showSaveDialog(contentPane);
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                File file = jFileChooser.getSelectedFile();
		                
		                int confirmResult = JOptionPane.showConfirmDialog(contentPane, "Bạn chắc chắn muốn nhập từ tệp Excel?", "Xác nhận nhập Excel", JOptionPane.YES_NO_OPTION);
		                if (confirmResult == JOptionPane.YES_OPTION) {
		                    NhapXuatExcel.readExcelToTable(file, table);
		                }
		            }
		        }
		    });


		    JButton button_themAnh = new JButton("Thêm Ảnh");
		    button_themAnh.setFont(new Font("Tahoma", Font.BOLD, 10));
		    button_themAnh.setBounds(322, 468, 125, 39);

		    // Tạo một màu xanh nhạt bằng cách chỉ định giá trị RGB
		    Color lightGreen = new Color(173, 216, 230); // Chỉ số RGB cho màu xanh nhạt

		    button_themAnh.setBackground(lightGreen); // Đặt màu nền là xanh nhạt
		    add(button_themAnh);


		ImageIcon icon_themAnh = new ImageIcon("src\\icon\\down.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image_themAnh = icon_themAnh.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon_themAnh = new ImageIcon(image_themAnh);
        button_themAnh.setIcon(scaledIcon_themAnh);
        
        suggestionListModel = new DefaultListModel<>();
        JList<String> suggestionList = new JList<>(suggestionListModel);
        JScrollPane scrollPane_1 = new JScrollPane(suggestionList);
        scrollPane_1.setBounds(623, 196, 586, 53);
        add(scrollPane_1);
        
        JButton button_lamMoi_1 = new JButton("Xuất PDF");
        button_lamMoi_1.setBackground(new Color(255, 128, 0));
        button_lamMoi_1.setBounds(742, 720, 140, 39);
        add(button_lamMoi_1);
        listModel = new DefaultListModel<>();
        Sach_BUS.loadDataIntoList(listModel);
         list_maTheLoai = new JList<>(listModel);  
         add(list_maTheLoai);
         list_maTheLoai.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         list_maTheLoai.setVisibleRowCount(5);
        JScrollPane scrollPane_2 = new JScrollPane(list_maTheLoai);
        scrollPane_2.setBounds(620, 120, 203, 42);
        add(scrollPane_2);
        
        listModel1 = new DefaultListModel<>();
        Sach_BUS.loadDataIntoList_maNXB(listModel1);
        list_maNXB = new JList<>(listModel1);
        list_maNXB.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane_3 = new JScrollPane(list_maNXB);
        scrollPane_3.setBounds(1022, 68, 187, 41);
        add(scrollPane_3);
        scrollPane_3.setViewportView(list_maNXB);
        
        
        listModel12 = new DefaultListModel<>();
        Sach_BUS.loadDataIntoComboBox_maTacGia(listModel12);
        list_maTacGia = new JList<>(listModel12);
        list_maTacGia.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane_3_1 = new JScrollPane(list_maTacGia);
        scrollPane_3_1.setBounds(1022, 121, 187, 41);
        add(scrollPane_3_1);
       
        scrollPane_3_1.setViewportView(list_maTacGia);
        
        button_lamMoi_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                JFileChooser fileChooser = new JFileChooser();

                int returnValue = fileChooser.showSaveDialog(null);

               
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();

                    // Gọi phương thức savePDF với đường dẫn file đã chọn
                    PDFiText.savePDF(table, filePath);
                }
            }
        });

 
        scrollPane_1.setVisible(false);
        suggestionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Lấy gợi ý được chọn từ danh sách
                    String selectedSuggestion = suggestionList.getSelectedValue();
                    
                    // Kiểm tra nếu gợi ý không rỗng
                    if (selectedSuggestion != null) {
                        // Đặt giá trị của textField thành gợi ý được chọn
                        textField_tenSach1.setText(selectedSuggestion);
                        
                        // Xóa gợi ý cũ và ẩn scroll pane
                        suggestionListModel.clear();
                        scrollPane_1.setVisible(false);
                    }
                }
            }
        });



       
       
        textField_tenSach1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateSuggestions();
                    if (!textField_tenSach1.getText().isEmpty()) {
                        scrollPane_1.setVisible(true);
                    } else {
                        scrollPane_1.setVisible(false);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateSuggestions();
                    if (!textField_tenSach1.getText().isEmpty()) {
                        scrollPane_1.setVisible(true);
                    } else {
                        scrollPane_1.setVisible(false);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            public void changedUpdate(DocumentEvent e) {
                try {
    				updateSuggestions();
    				
    			} catch (SQLException e1) {
    			 
    				e1.printStackTrace();
    			}
            }
        });

        
        
       
   

		button_themAnh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mở hộp thoại chọn tập tin
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Hiển thị hình ảnh lên JLabel
                    showImageOnLabel(selectedFile.getName());
                }
            }
        });

//		textField_tenSach1.getDocument().addDocumentListener(new DocumentListener() {
//	            public void insertUpdate(DocumentEvent e) {
//	                searchAndUpdate();
//	            
//	            }
//
//	            @Override
//	            public void removeUpdate(DocumentEvent e) {
//	            	searchAndUpdate();
//	            }
//
//	            @Override
//	            public void changedUpdate(DocumentEvent e) {
//	            	searchAndUpdate();
//	            }
//	        });
//		
		
		textField_tu.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            try {
				searchAndUpdate();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
        
        }
        public void removeUpdate(DocumentEvent e) {
        	try {
				searchAndUpdate();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
        }
        public void changedUpdate(DocumentEvent e) {
        	try {
				searchAndUpdate();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
        }
    });
		textField_den.getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	            try {
					searchAndUpdate();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
	        
	        }
	        public void removeUpdate(DocumentEvent e) {
	        	try {
					searchAndUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }
	        public void changedUpdate(DocumentEvent e) {
	        	try {
					searchAndUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }
	    });

		
		
	}

	private void updateSuggestions() throws SQLException {
	    String input = textField_tenSach1.getText();
	    suggestionListModel.clear();
	    if (!input.isEmpty()) { 
	        List<String> suggestions = Sach_BUS.getSimilarBookTitles(input);
	        for (String suggestion : suggestions) {
	            suggestionListModel.addElement(suggestion);
	        }

	    }
	}

	private void searchAndUpdate() throws SQLException {
	    String valueMaSach = textField_maSach1.getText();
	    List<String> selectedNXB = list_maNXB.getSelectedValuesList();
	    List<String> selectedTheLoai = list_maTheLoai.getSelectedValuesList();
	    List<String> selectedTacGia = list_maTacGia.getSelectedValuesList();  
	    String[] valueMaTheLoaiArray = selectedTheLoai.toArray(new String[0]);
	    String[] valueMaNXBArray = selectedNXB.toArray(new String[0]);
	    String[] valueMaTacGiaArray = selectedTacGia.toArray(new String[0]);
	   
	    String valueTenSach = textField_tenSach1.getText();
	    String valueDonGiaTu = textField_tu.getText().trim();
	    String valueDonGiaDen = textField_den.getText().trim();
	    
	    // Kiểm tra nếu giá từ rỗng, thì lấy giá trị min
	    double minDonGia = Double.MIN_VALUE;
	    if (!valueDonGiaTu.isEmpty()) {
	        minDonGia = Double.parseDouble(valueDonGiaTu);
	    }
	    
	    double maxDonGia = Double.MAX_VALUE;
	    if (!valueDonGiaDen.isEmpty()) {
	        maxDonGia = Double.parseDouble(valueDonGiaDen);
	    }
	    
	    if (minDonGia > maxDonGia) {
	        JOptionPane.showMessageDialog(null, "Giá từ không được lớn hơn giá đến.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        textField_tu.setText("");
	        textField_den.setText("");
	    } else {
	        searchAndDisplayFromDatabase(valueMaSach, valueMaNXBArray,
	        		valueMaTheLoaiArray, valueMaTacGiaArray, valueTenSach,
	                valueDonGiaTu, valueDonGiaDen , table);
	    }
	}


	
	 private void refreshTable() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); 

	        loadTableData(table);
	  }
	 public static void loadTableData(JTable table) {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); 
	        ArrayList<Sach_DTO> sachList = Sach_BUS.getAllBooks();
	        for (Sach_DTO sach : sachList) {
	            model.addRow(new Object[] { sach.getMaSach(), sach.getMaNXB(), sach.getMaTheLoai(), sach.getMaTacGia(),
	                                         sach.getTenSach(), sach.getNamXuatBan(), sach.getSoLuong(), sach.getDonGia(),
	                                         sach.getHinhAnh() });
	        }
	    }
	 public static void loadTableData() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); 
	        ArrayList<Sach_DTO> sachList = Sach_BUS.getAllBooks();
	        for (Sach_DTO sach : sachList) {
	            model.addRow(new Object[] { sach.getMaSach(), sach.getMaNXB(), sach.getMaTheLoai(), sach.getMaTacGia(),
	                                         sach.getTenSach(), sach.getNamXuatBan(), sach.getSoLuong(), sach.getDonGia(),
	                                         sach.getHinhAnh() });
	        }
	    }
	 private void showImageOnLabel(String imagePath) {
		 String duongdan= "src\\image\\" ;
		 String linkdung = duongdan+imagePath;
		 ImageIcon icon = new ImageIcon(linkdung);
         Image image = icon.getImage();

         int newWidth = 270; // Thay đổi kích thước theo nhu cầu của bạn
         int newHeight = 350; // Thay đổi kích thước theo nhu cầu của bạn

         // Thay đổi kích thước của hình ảnh
         Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

         // Tạo một ImageIcon mới từ hình ảnh đã được thay đổi kích thước
         ImageIcon scaledIcon = new ImageIcon(scaledImage);

         // Đặt ImageIcon mới cho JLabel
         label_hinhAnh.setIcon(scaledIcon);
         label_hinhAnh.setText(imagePath); 
	}
		private void clearFields() {
	        textField_maSach.setText("");
	       
	        textField_tenSach.setText("");
	        textField_namXuatBan.setText("");
	        textField_soLuong.setText("");
	        textField_donGia.setText("");
	        textField_maSach1.setText("");
	        textField_tenSach1.setText("");
	        textField_tu.setText("");
	        textField_den.setText("");
	        loadTableData(table);
	    }
		 public static void searchAndDisplayFromDatabase(String maSach, 
				 String []maNXB, String []maTheLoai,
				 String []maTacGia, String tenSach, String 
				 donGiaTu, String donGiaDen , JTable tabel) throws SQLException {
			
			   ResultSet resultSet = Sach_BUS.searchAndDisplayFromDatabase(maSach, maNXB, maTheLoai,
			        		maTacGia, tenSach, donGiaTu, donGiaDen);

			        // Xóa dữ liệu hiện tại trong bảng
			   DefaultTableModel model = (DefaultTableModel) tabel.getModel();
			   model.setRowCount(0);
			   try {
					while (resultSet.next()) {
						    // Lấy dữ liệu từ ResultSet và thêm vào bảng
						    Object[] rowData = new Object[9];
						    rowData[0] = resultSet.getString("MaSach");
						    rowData[1] = resultSet.getString("MaNXB");
						    rowData[2] = resultSet.getString("MaTheLoai");
						    rowData[3] = resultSet.getString("MaTacGia");
						    rowData[4] = resultSet.getString("TenSach");
						    rowData[5] = resultSet.getString("NamXuatBan");
						    rowData[6] = resultSet.getString("SoLuong");
						    rowData[7] = resultSet.getString("DonGia");
						    rowData[8] = resultSet.getString("HinhAnh");
						    model.addRow(rowData);
						  
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			}
		 private void sortTable() {
			 String sortBy1 = (String) comboBoxSortBy.getSelectedItem();
			 String sortBy = sortBy1.substring("Sắp xếp theo: ".length());

			    boolean ascendingOrder = comboBoxSortOrder.getSelectedIndex() == 0;
		
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    Object[][] data = new Object[model.getRowCount()][model.getColumnCount()];
			    for (int row = 0; row < model.getRowCount(); row++) {
			        for (int col = 0; col < model.getColumnCount(); col++) {
			            data[row][col] = model.getValueAt(row, col);
			        }
			    }
			    Comparator<Object[]> comparator = (a, b) -> {
			        Object aValue = a[getColumnIndex(sortBy)];
			        Object bValue = b[getColumnIndex(sortBy)];
			        if (aValue instanceof Comparable && bValue instanceof Comparable) {
			            Comparable<Object> comparableA = (Comparable<Object>) aValue;
			            Comparable<Object> comparableB = (Comparable<Object>) bValue;

			            return ascendingOrder ? comparableA.compareTo(comparableB) : comparableB.compareTo(comparableA);
			        }
			        return 0;
			    };

			    Arrays.sort(data, comparator);

			    model.setRowCount(0);
			    for (Object[] row : data) {
			        model.addRow(row);
			    }
			}

		    private int getColumnIndex(String columnName) {
		        for (int i = 0; i < table.getColumnCount(); i++) {
		            if (columnName.equals(table.getColumnName(i))) {
		                return i;
		            }
		        }
		        return -1;
		    }

}

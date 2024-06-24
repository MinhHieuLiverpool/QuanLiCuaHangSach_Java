package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import BUS.KhachHang_BUS;
import DTO.KhachHang_DTO;
import NhapXuatEXCEL.NhapXuatExcel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;

public class KhachHang22_GUI2_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maKhachHang;
	private JTextField textField_soDienThoai;
	private JTextField textField_hoKhachHang;
	private JTextField textField_tenKhachHang;
	private JTextField textField_maKhachHang1;
	private JTextField textField_hoKhachHang1;
	private JTextField textField_tenKhachHang1;
	private JRadioButton radioButton_nu;
	private JRadioButton radioButton_nam;
	private JDateChooser dateChooser_ngaySinh;
	private JRadioButton radioButton_nu_1;
	private JRadioButton radioButton_nam_1;
	private JLabel jlabel_tongChi;
	 private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang22_GUI2_JPanel frame = new KhachHang22_GUI2_JPanel();
					frame.loadTableData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KhachHang22_GUI2_JPanel() {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ KHÁCH HÀNG");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(548, 10, 270, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 71, 181, 20);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(352, 71, 131, 20);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ khách hàng");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(64, 144, 131, 20);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(68, 296, 95, 20);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Tên khách hàng");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(352, 144, 131, 20);
		add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Tổng chi");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_5.setBounds(352, 217, 131, 20);
		add(lblNewLabel_1_5);
		
		textField_maKhachHang = new JTextField();
		textField_maKhachHang.setBounds(64, 101, 229, 30);
		add(textField_maKhachHang);
		textField_maKhachHang.setColumns(10);
		
		textField_soDienThoai = new JTextField();
		textField_soDienThoai.setColumns(10);
		textField_soDienThoai.setBounds(352, 101, 229, 30);
		add(textField_soDienThoai);
		
		textField_hoKhachHang = new JTextField();
		textField_hoKhachHang.setColumns(10);
		textField_hoKhachHang.setBounds(64, 174, 229, 30);
		add(textField_hoKhachHang);
		
		textField_tenKhachHang = new JTextField();
		textField_tenKhachHang.setColumns(10);
		textField_tenKhachHang.setBounds(352, 174, 229, 30);
		add(textField_tenKhachHang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 436, 1157, 278);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Số điện thoại",
					"Ngày sinh", "Giới tính", "Tổng chi"
				}
			));
		 table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    DefaultTableModel model = (DefaultTableModel) table.getModel();

	                    textField_maKhachHang.setText(model.getValueAt(selectedRow, 0).toString());
	                    textField_hoKhachHang.setText(model.getValueAt(selectedRow, 1).toString());
	                    textField_tenKhachHang.setText(model.getValueAt(selectedRow, 2).toString());
	                    textField_soDienThoai.setText(model.getValueAt(selectedRow, 3).toString());

	                    try {
	                        String dateString = model.getValueAt(selectedRow, 4).toString(); // Lấy ngày tháng năm dưới dạng chuỗi từ JTable
	                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng năm
	                        Date ngaySinhDate = dateFormat.parse(dateString); // Chuyển đổi chuỗi sang Date

	                        // Thiết lập ngày tháng năm cho JDateChooser
	                        dateChooser_ngaySinh.setDate(ngaySinhDate);
	                    } catch (ParseException e1) {
	                        e1.printStackTrace();
	                    }

	                    // Đặt giới tính từ cột GioiTinh
	                    String gioiTinh = model.getValueAt(selectedRow, 5).toString();
	                    if (gioiTinh.equals("Nam")) {
	                        radioButton_nam.setSelected(true);
	                        radioButton_nu.setSelected(false);
	                    } else {
	                        radioButton_nam.setSelected(false);
	                        radioButton_nu.setSelected(true);
	                    }
	                    jlabel_tongChi.setText(model.getValueAt(selectedRow, 6).toString());
	                }
	            }
	        });
		
	
	
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm khách hàng");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setBounds(731, 63, 477, 20);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_6 = new JLabel("Mã khách hàng");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_6.setBounds(736, 106, 131, 20);
		add(lblNewLabel_1_6);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(610, 38, 1, 296);
		add(separator_1);
		
		textField_maKhachHang1 = new JTextField();
		textField_maKhachHang1.setColumns(10);
		textField_maKhachHang1.setBounds(731, 132, 477, 30);
		add(textField_maKhachHang1);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Họ khách hàng");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_6_1.setBounds(736, 172, 131, 20);
		add(lblNewLabel_1_6_1);
		
		textField_hoKhachHang1 = new JTextField();
		textField_hoKhachHang1.setColumns(10);
		textField_hoKhachHang1.setBounds(731, 198, 217, 30);
		add(textField_hoKhachHang1);
		
		JLabel lblNewLabel_1_6_2 = new JLabel("Tên khách hàng");
		lblNewLabel_1_6_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_6_2.setBounds(977, 169, 131, 20);
		add(lblNewLabel_1_6_2);
		
		textField_tenKhachHang1 = new JTextField();
		textField_tenKhachHang1.setColumns(10);
		textField_tenKhachHang1.setBounds(977, 199, 231, 30);
		add(textField_tenKhachHang1);
		
		JLabel lblNewLabel_1_6_1_1 = new JLabel("Giới tính");
		lblNewLabel_1_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_6_1_1.setBounds(736, 255, 87, 20);
		add(lblNewLabel_1_6_1_1);
		
		JButton button_sua = new JButton("Sửa");
		button_sua.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_sua.setBackground(Color.ORANGE);
		 ImageIcon icon1 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon1 = new ImageIcon(image1);
	        button_sua.setIcon(scaledIcon1);
		
		button_sua.setBounds(464, 351, 117, 30);
		add(button_sua);
		button_sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int selectedRow = table.getSelectedRow();
			    if (selectedRow == -1) {
			        JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }
			    try {
			        String maKhachHang = textField_maKhachHang.getText();
			        String tenKhachHang = textField_tenKhachHang.getText();
			        String hoKhachHang = textField_hoKhachHang.getText();
			        String soDienThoai_KH = textField_soDienThoai.getText();
			        String gioiTinh_KH = ""; 
			        if (radioButton_nam.isSelected()) {
			            gioiTinh_KH = "Nam";
			        } else if (radioButton_nu.isSelected()) {
			            gioiTinh_KH = "Nữ";
			        } else {
			            gioiTinh_KH = ""; 
			        }            
    
			        Date ngaySinhDate = dateChooser_ngaySinh.getDate();
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        String ngaySinh_KH = dateFormat.format(ngaySinhDate);

			        String tongChi = jlabel_tongChi.getText();
			      
			        KhachHang_DTO khachHang = new KhachHang_DTO(maKhachHang, hoKhachHang, tenKhachHang,
			                soDienThoai_KH, ngaySinhDate, gioiTinh_KH, Float.parseFloat(tongChi));
		            boolean updated = KhachHang_BUS.updateKhachHang(khachHang);

			        if (updated) {
			            DefaultTableModel model = (DefaultTableModel) table.getModel();
			            model.setValueAt(maKhachHang, selectedRow, 0);
			            model.setValueAt(tenKhachHang, selectedRow, 2); // Giả sử cột 1 là cột hiển thị tên khách hàng
			            model.setValueAt(hoKhachHang, selectedRow, 1); // Giả sử cột 2 là cột hiển thị họ khách hàng
			            model.setValueAt(soDienThoai_KH, selectedRow, 3); // Giả sử cột 3 là cột hiển thị số điện thoại
			            model.setValueAt(gioiTinh_KH, selectedRow, 5); // Giả sử cột 4 là cột hiển thị giới tính
			            model.setValueAt(ngaySinh_KH, selectedRow, 4); // Giả sử cột 5 là cột hiển thị ngày sinh
			            model.setValueAt(tongChi, selectedRow, 6); // Giả sử cột 6 là cột hiển thị tổng chi

			            JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công!");
			        } else {
			            JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
			        }
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho tổng chi!");
			    }
			}

        });
			
		JButton button_xoa = new JButton("Xóa");
		button_xoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(button_xoa);
		button_xoa.setBounds(260, 351, 117, 30);
		button_xoa.setBackground(new Color(220, 20, 60));
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon2);

		button_xoa.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		        String maKhachHang = textField_maKhachHang.getText(); // Lấy mã khách hàng từ JTextField
		        if (maKhachHang.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
		            return;
		        }
		        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		        boolean deleted = KhachHang_BUS.xoaKhachHang(maKhachHang);// Gọi phương thức deleteSach để xóa khách hàng
		            if (deleted) {
		                JOptionPane.showMessageDialog(null, "Xóa thành công!");
		                loadTableData(); 
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa không thành công!");
		            }
		        }
		    }
		});
		
		
		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTmKim.setBounds(901, 304, 138, 30);
		add(btnTmKim);
		btnTmKim.setBackground(new Color(10, 227, 245));
		   ImageIcon icon3 = new ImageIcon("src\\icon\\find.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon3 = new ImageIcon(image3);
	        btnTmKim.setIcon(scaledIcon3);
		btnTmKim.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
		        String maNhanVien = textField_maKhachHang1.getText();
		        String hoNhanVien = textField_hoKhachHang1.getText();
		        String tenNhanVien = textField_tenKhachHang1.getText();
		        String gioiTinh_KH;
		        if (radioButton_nam_1.isSelected()) {
		            gioiTinh_KH = "Nam";
		        } else if (radioButton_nu_1.isSelected()) {
		            gioiTinh_KH = "Nữ";
		        } else {
		            gioiTinh_KH = ""; // hoặc một giá trị mặc định khác
		        }

		        searchAndDisplayFromDatabase(maNhanVien, hoNhanVien, tenNhanVien,gioiTinh_KH);
		    }
		});

		
		JButton button_them = new JButton("Thêm");
		button_them.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_them.setBounds(64, 351, 116, 30);
		button_them.setBackground(new Color(144, 238, 144));
		
		 ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon = new ImageIcon(image);
	    button_them.setIcon(scaledIcon);
		add(button_them);

		 button_them.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String maKhachHang = textField_maKhachHang.getText();
	                String hoKhachHang = textField_hoKhachHang.getText();
	                String tenKhachHang = textField_tenKhachHang.getText();
	                String soDienThoai_KH = textField_soDienThoai.getText();
	                Date ngaySinh_KH = dateChooser_ngaySinh.getDate();


	                String gioiTinh_KH = "";
	                if (radioButton_nam.isSelected()) {
	                    gioiTinh_KH = "Nam";
	                } else if (radioButton_nu.isSelected()) {
	                    gioiTinh_KH = "Nữ";
	                }

	                KhachHang_DTO khachHang = new KhachHang_DTO(maKhachHang, hoKhachHang, tenKhachHang,
	                		soDienThoai_KH, ngaySinh_KH, gioiTinh_KH,
	                        0);

	                KhachHang_BUS KhachHangBUS = new KhachHang_BUS();
                    boolean success = KhachHangBUS.addKhachHang(khachHang);
                    if (success) {
                        // Nếu thêm thành công, thông báo cho người dùng và làm mới bảng
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                        refreshTable(); // Gọi phương thức làm mới bảng
                    } else {
                        // Nếu thêm không thành công, hiển thị thông báo lỗi
                        JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại");
                    }
	                loadTableData();
	            }
	        });
		
	JButton btnNhp = new JButton("Nhập");
	btnNhp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNhp.setBounds(914, 724, 138, 30);
		add(btnNhp);
		 ImageIcon icon_thongKe1 = new ImageIcon("src\\icon\\excel.png");
		    Image image_thongKe1 = icon_thongKe1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		    btnNhp.setIcon(new ImageIcon(image_thongKe1));
		    btnNhp.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();   
	                    NhapXuatExcel.readExcelToTable(file, table);
	                }
	            }
	        });
		
		JButton btnXut = new JButton("Xuất");
		btnXut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXut.setBounds(1083, 724, 138, 30);
		add(btnXut);
		 ImageIcon icon_thongKe2 = new ImageIcon("src\\icon\\excel.png");
		    Image image_thongKe2 = icon_thongKe2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		    btnXut.setIcon(new ImageIcon(image_thongKe2));
		    btnXut.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();
	                    String filePath = file.getAbsolutePath();
	                    // Gọi hàm xuất dữ liệu ra file Excel
	                    NhapXuatExcel.exportDataToExcel(table, filePath, "Quản lí Khách Hàng Sheet");
	                    // Hiển thị thông báo sau khi xuất dữ liệu
	                    JOptionPane.showMessageDialog(contentPane, "Xuất dữ liệu ra file Excel thành công!");
	                }
	            }
	        });
        
        radioButton_nam = new JRadioButton("Nam");
        radioButton_nam.setFont(new Font("Tahoma", Font.PLAIN, 16));
        radioButton_nam.setBounds(169, 297, 95, 21);
        add(radioButton_nam);
        
        radioButton_nu = new JRadioButton("Nữ");
        radioButton_nu.setFont(new Font("Tahoma", Font.PLAIN, 16));
        radioButton_nu.setBounds(260, 296, 103, 21);
        add(radioButton_nu);
        
        dateChooser_ngaySinh = new JDateChooser();
        dateChooser_ngaySinh.setBounds(64, 247, 229, 30);
        add(dateChooser_ngaySinh);
        
        JLabel lblNewLabel_1_4_1 = new JLabel("Ngày sinh");
        lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_1_4_1.setBounds(64, 217, 131, 20);
        add(lblNewLabel_1_4_1);
        
    	ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton_nam);
        buttonGroup.add(radioButton_nu);

        radioButton_nam_1 = new JRadioButton("Nam");
        radioButton_nam_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        radioButton_nam_1.setBounds(890, 255, 95, 21);
        add(radioButton_nam_1);
        
        radioButton_nu_1 = new JRadioButton("Nữ");
        radioButton_nu_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        radioButton_nu_1.setBounds(1005, 255, 103, 21);
        add(radioButton_nu_1);
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton_nam_1);
        buttonGroup1.add(radioButton_nu_1);
        
        jlabel_tongChi = new JLabel("");
        jlabel_tongChi.setOpaque(true); // Đặt opaque thành true để hiển thị màu nền
        jlabel_tongChi.setBackground(new Color(220, 220, 220)); // Đặt màu nền thành màu xám nhạt
        jlabel_tongChi.setFont(new Font("Tahoma", Font.BOLD, 13));
        jlabel_tongChi.setBounds(352, 246, 229, 31); // Đặt kích thước và vị trí
     
        add(jlabel_tongChi);
        
        JLabel lblNewLabel_2_1 = new JLabel("Thông tin khách hàng");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setForeground(new Color(220, 20, 60));
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblNewLabel_2_1.setBounds(64, 34, 517, 20);
        add(lblNewLabel_2_1);
        
        JButton button_lamMoi = new JButton("Làm mới");
        button_lamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        button_lamMoi.setBackground(new Color(173, 216, 230));
        button_lamMoi.setBounds(64, 724, 124, 30);
        add(button_lamMoi);
        ImageIcon icon_lamMoi = new ImageIcon("src\\icon\\load.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image_lamMoi = icon_lamMoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon_lamMoi = new ImageIcon(image_lamMoi);
        button_lamMoi.setIcon(scaledIcon_lamMoi);
        button_lamMoi.addActionListener(e -> 	clearFields());
        
        textField_maKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_soDienThoai.requestFocusInWindow();
            }
        });

        textField_soDienThoai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_hoKhachHang.requestFocusInWindow();
            }
        });

        textField_hoKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_tenKhachHang.requestFocusInWindow();
            }
        });

	}
	public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<KhachHang_DTO> khachHangList = KhachHang_BUS.getAllEmployee();
        for (KhachHang_DTO khachHang : khachHangList) {
            String ngaySinh_KH_Formatted = dateFormat.format(khachHang.getNgaySinh_KH());
            model.addRow(new Object[] { khachHang.getMaKhachHang(),
                    khachHang.getHoKhachHang(), khachHang.getTenKhachHang(),
                    khachHang.getSoDienThoai_KH(), ngaySinh_KH_Formatted,
                    khachHang.getGioiTinh_KH(), khachHang.getTongChi() });
        }
    }
 	 private static void searchAndDisplayFromDatabase(String maNhanVien, 
			 String hoNhanVien, String tenNhanVien,
			 String gioiTinh) {	
		   ResultSet resultSet = null;
		try {
			resultSet = KhachHang_BUS.searchAndDisplayFromDatabase(maNhanVien, 
					   hoNhanVien, tenNhanVien,
		  gioiTinh);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		   DefaultTableModel model = (DefaultTableModel) table.getModel();
		   model.setRowCount(0);
		   try {
				while (resultSet.next()) {
				    Object[] rowData = new Object[8];
				    rowData[0] = resultSet.getString("maKhachHang");
				    rowData[1] = resultSet.getString("HoKhachHang");
				    rowData[2] = resultSet.getString("TenKhachhang");
				    rowData[3] = resultSet.getString("SoDienThoai");
				    rowData[4] = resultSet.getString("NgaySinh");
				    rowData[5] = resultSet.getString("gioiTinh");
				    rowData[6] = resultSet.getString("TongChi");
				    model.addRow(rowData);				
				    }
				} catch (SQLException e) {
					
					e.printStackTrace();
				}   
		}
 	 private void refreshTable() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); 
	        loadTableData();
	  }
 	private void clearFields() {
        textField_maKhachHang.setText("");
        textField_tenKhachHang.setText("");
        textField_hoKhachHang.setText("");
        textField_soDienThoai.setText("");
        jlabel_tongChi.setText("");
        textField_maKhachHang1.setText(""); 
        textField_tenKhachHang1.setText("");
        textField_hoKhachHang1.setText("");
        dateChooser_ngaySinh.setDate(null);
        loadTableData();
    }
}

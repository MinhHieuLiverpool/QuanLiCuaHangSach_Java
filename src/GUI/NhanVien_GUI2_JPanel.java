package GUI ; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import BUS.ChucVu_BUS;
import BUS.NhanVien_BUS;
import DTO.NhanVien_DTO;
import Database.ConnectionManager;
import NhapXuatEXCEL.NhapXuatExcel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
public class NhanVien_GUI2_JPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maNhanVien;
	private JTextField textField_hoNhanVien;
	private JTextField textField_tenNhanVien;
	private JTextField textField_soDienThoai;
	private JTextField textField_luong;
	private static JTable table;
	private JComboBox<String> comboBox_chucVu;
	private JTextField textField_maNhanVien1;
	private JTextField textField_hoNhanVien1;
	private JTextField textField_tenNhanVien1;
	private JRadioButton radiobutton_nam_1;
	private JRadioButton radiobutton_nu_1;
	private JButton btnTmKim;
	private JLabel lblMNhnVin;
	private JLabel lblTnNhnVin_1;
	private JLabel lblChcV_1;
	private JLabel label_gioiTinh1;
	private JComboBox<String> comboBox_chucVu_1;
	private JDateChooser dateChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					NhanVien_GUI2_JPanel frame = new NhanVien_GUI2_JPanel();
					frame.loadTableData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NhanVien_GUI2_JPanel() throws IOException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		JLabel label_tieuDe = new JLabel("QUẢN LÍ NHÂN VIÊN");
		label_tieuDe.setBackground(new Color(0, 255, 0));
		label_tieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_tieuDe.setBounds(451, 0, 242, 28);
		add(label_tieuDe);
		
		JLabel label_maNhanVien = new JLabel("Mã nhân viên");
		label_maNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maNhanVien.setBounds(49, 38, 219, 28);
		add(label_maNhanVien);
		
		JLabel lblHNhnVin = new JLabel("Họ nhân viên");
		lblHNhnVin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHNhnVin.setBounds(49, 117, 196, 28);
		add(lblHNhnVin);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTnNhnVin.setBounds(49, 187, 180, 28);
		add(lblTnNhnVin);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSinThoi.setBounds(49, 260, 196, 28);
		add(lblSinThoi);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgySinh.setBounds(310, 38, 180, 28);
		add(lblNgySinh);
		
		textField_maNhanVien = new JTextField();
		textField_maNhanVien.setBounds(47, 76, 198, 28);
		add(textField_maNhanVien);
		textField_maNhanVien.setColumns(10);
		
		textField_hoNhanVien = new JTextField();
		textField_hoNhanVien.setColumns(10);
		textField_hoNhanVien.setBounds(49, 149, 196, 28);
		add(textField_hoNhanVien);
		
		textField_tenNhanVien = new JTextField();
		textField_tenNhanVien.setColumns(10);
		textField_tenNhanVien.setBounds(49, 225, 196, 28);
		add(textField_tenNhanVien);
		
		textField_soDienThoai = new JTextField();
		textField_soDienThoai.setColumns(10);
		textField_soDienThoai.setBounds(49, 287, 196, 28);
		add(textField_soDienThoai);
	  
		dateChooser = new JDateChooser();
        dateChooser.setBounds(310, 78, 193, 28);
        add(dateChooser);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGiiTnh.setBounds(309, 117, 114, 28);
		add(lblGiiTnh);
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChcV.setBounds(309, 187, 114, 28);
		add(lblChcV);
		
		JRadioButton radiobutton_nam = new JRadioButton("Nam");
		radiobutton_nam.setFont(new Font("Tahoma", Font.BOLD, 16));
		radiobutton_nam.setBounds(306, 149, 86, 28);
		add(radiobutton_nam);

		JRadioButton radiobutton_nu = new JRadioButton("Nữ");
		radiobutton_nu.setFont(new Font("Tahoma", Font.BOLD, 16));
		radiobutton_nu.setBounds(399, 148, 104, 28);
		add(radiobutton_nu);

		ButtonGroup gioiTinhGroup = new ButtonGroup();
		gioiTinhGroup.add(radiobutton_nam);
		gioiTinhGroup.add(radiobutton_nu);
	

		comboBox_chucVu = new JComboBox<String>();
		comboBox_chucVu.setBounds(313, 225, 190, 28);
		add(comboBox_chucVu);
		ChucVu_BUS.loadDataIntoComboBox_tenChucVu(comboBox_chucVu);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLng.setBounds(309, 260, 114, 28);
		add(lblLng);
		
		textField_luong = new JTextField();
		textField_luong.setColumns(10);
		textField_luong.setBounds(312, 287, 191, 28);
		add(textField_luong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 411, 1157, 267);
		add(scrollPane);

		table = new JTable();
	
		DefaultTableModel model = new DefaultTableModel(
		    new Object[][] {},
		    new String[] {"Mã Nhân Viên", "Họ", "Tên", "Số điện thoại", 
		    		"Ngày sinh", "Giới tính", "Chức Vụ", "Lương"}
		);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            
		            textField_maNhanVien.setText(model.getValueAt(selectedRow, 0).toString());
		            textField_hoNhanVien.setText(model.getValueAt(selectedRow, 1).toString());
		            textField_tenNhanVien.setText(model.getValueAt(selectedRow, 2).toString());
		            textField_soDienThoai.setText(model.getValueAt(selectedRow, 3).toString());
		            
		            try {
		                String dateString = model.getValueAt(selectedRow, 4).toString(); // Lấy ngày tháng năm dưới dạng chuỗi từ JTable
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng năm
		                Date ngaySinhDate = dateFormat.parse(dateString); // Chuyển đổi chuỗi sang Date
		                
		                // Thiết lập ngày tháng năm cho JDateChooser
		                dateChooser.setDate(ngaySinhDate);
		            } catch (ParseException e1) {
		                e1.printStackTrace();
		            }

		            // Đặt giới tính từ cột GioiTinh
		            String gioiTinh = model.getValueAt(selectedRow, 5).toString();
		            if (gioiTinh.equals("Nam")) {
		                radiobutton_nam.setSelected(true);
		                radiobutton_nu.setSelected(false);
		            } else {
		                radiobutton_nam.setSelected(false);
		                radiobutton_nu.setSelected(true);
		            }
		            
		            comboBox_chucVu.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
		            textField_luong.setText(model.getValueAt(selectedRow, 7).toString());
		        }
		    }
		});


		scrollPane.setViewportView(table);

		
		
		JButton button_sua = new JButton("Sửa");
		button_sua.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_sua.setBounds(399, 352, 104, 36);
		add(button_sua);
		button_sua.setBackground(new Color(255, 255, 169));
		 ImageIcon icon1 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon1 = new ImageIcon(image1);
	        button_sua.setIcon(scaledIcon1);
		button_sua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        try {
		            String maNhanVien = textField_maNhanVien.getText();
		            String tenNhanVien = textField_tenNhanVien.getText();
		            String hoNhanVien = textField_hoNhanVien.getText();
		            String soDienThoai = textField_soDienThoai.getText();
		            
		            // Lấy giới tính từ JComboBox
		            String gioiTinh = ""; // Khởi tạo biến giới tính

		            // Kiểm tra xem RadioButton nào được chọn
		            if (radiobutton_nam.isSelected()) {
		                gioiTinh = "Nam";
		            } else if (radiobutton_nu.isSelected()) {
		                gioiTinh = "Nữ";
		            } else {
		                // Xử lý trường hợp không có RadioButton nào được chọn (nếu cần)
		                gioiTinh = ""; // hoặc một giá trị mặc định khác tùy vào yêu cầu của bạn
		            }
		            Object maChucVu = comboBox_chucVu.getSelectedItem();
		            
		            // Lấy ngày sinh từ JCalendarChooser
		            Date ngaySinh = dateChooser.getDate();
		            
		            // Định dạng ngày sinh theo format "yyyy-MM-dd"
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            String ngaySinhFormatted = dateFormat.format(ngaySinh);

		            String luong = textField_luong.getText();		           
		            NhanVien_DTO nhanVien = new NhanVien_DTO(maNhanVien, tenNhanVien, hoNhanVien, soDienThoai, ngaySinh, gioiTinh, maChucVu.toString(), Double.parseDouble(luong));
		            boolean updated = NhanVien_BUS.updateNhanVien(nhanVien);

		            if (updated) {
		                // Cập nhật dữ liệu trên bảng hiển thị
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.setValueAt(tenNhanVien, selectedRow, 1); // Giả sử cột 1 là cột hiển thị tên nhân viên
		                model.setValueAt(hoNhanVien, selectedRow, 2); // Giả sử cột 2 là cột hiển thị họ nhân viên
		                model.setValueAt(soDienThoai, selectedRow, 3); // Giả sử cột 3 là cột hiển thị số điện thoại
		                model.setValueAt(ngaySinhFormatted, selectedRow, 4); // Giả sử cột 4 là cột hiển thị giới tính
		                model.setValueAt(gioiTinh, selectedRow, 5); // Giả sử cột 5 là cột hiển thị ngày sinh định dạng
		                model.setValueAt(maChucVu, selectedRow, 6); // Giả sử cột 6 là cột hiển thị chức vụ
		                model.setValueAt(luong, selectedRow, 7); 

		                JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được sửa!");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho lương!");
		        }

		    }
		});

		
		JButton button_xoa = new JButton("Xóa");
		button_xoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_xoa.setBounds(229, 352, 114, 36);
		add(button_xoa);
		button_xoa.setBackground(new Color(220, 20, 60));
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon211 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon211);
	    button_xoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maKhachHang = textField_maNhanVien.getText(); // Lấy mã khách hàng từ JTextField
		        if (maKhachHang.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
		            return;
		        }
		        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean deleted =NhanVien_BUS.delete(maKhachHang); // Gọi phương thức deleteSach để xóa khách hàng
		            if (deleted) {
		                JOptionPane.showMessageDialog(null, "Xóa thành công!");
		                // Reload dữ liệu từ cơ sở dữ liệu và đặt lại model cho bảng hiển thị
		                loadTableData(); // Thay thế loadDataIntoTable bằng phương thức tương ứng của bạn để load lại dữ liệu
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa không thành công!");
		            }
		        }
		    }
		});
		
	     textField_maNhanVien.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                textField_hoNhanVien.requestFocusInWindow();
	            }
	        });

	     textField_hoNhanVien.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                textField_tenNhanVien.requestFocusInWindow();
	            }
	        });

	     textField_tenNhanVien.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                textField_soDienThoai.requestFocusInWindow();
	            }
	        });
	    
	 
		textField_maNhanVien1 = new JTextField();
		textField_maNhanVien1.setColumns(10);
		textField_maNhanVien1.setBounds(730, 149, 208, 28);
		add(textField_maNhanVien1);
		
		lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMNhnVin.setBounds(730, 111, 169, 28);
		add(lblMNhnVin);
		
		lblChcV_1 = new JLabel("Chức vụ");
		lblChcV_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChcV_1.setBounds(1012, 105, 156, 28);
		add(lblChcV_1);
		
		comboBox_chucVu_1 = new JComboBox<String>();
		comboBox_chucVu_1.setBounds(1004, 141, 203, 28);
		add(comboBox_chucVu_1);
		comboBox_chucVu_1.addItem("Tất cả");
		ChucVu_BUS.loadDataIntoComboBox_tenChucVu(comboBox_chucVu_1);
		
		label_gioiTinh1 = new JLabel("Giới tính");
		label_gioiTinh1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_gioiTinh1.setBounds(730, 275, 104, 28);
		add(label_gioiTinh1);
		
		JLabel lblHNhnVin_1 = new JLabel("Họ nhân viên");
		lblHNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHNhnVin_1.setBounds(730, 194, 140, 25);
		add(lblHNhnVin_1);
		
		ButtonGroup gioiTinhGroup1 = new ButtonGroup();
		radiobutton_nam_1 = new JRadioButton("Nam");
		radiobutton_nam_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		radiobutton_nam_1.setBounds(878, 283, 123, 21);
		add(radiobutton_nam_1);
		gioiTinhGroup1.add(radiobutton_nam_1); // Thêm radio_nam vào nhóm

		radiobutton_nu_1 = new JRadioButton("Nữ");
		radiobutton_nu_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		radiobutton_nu_1.setBounds(1014, 283, 110, 21);
		add(radiobutton_nu_1);
		gioiTinhGroup1.add(radiobutton_nu_1); // Thêm radio_nu vào nhóm

		textField_hoNhanVien1 = new JTextField();
		textField_hoNhanVien1.setColumns(10);
		textField_hoNhanVien1.setBounds(730, 224, 208, 29);
		add(textField_hoNhanVien1);
		
		lblTnNhnVin_1 = new JLabel("Tên nhân viên");
		lblTnNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTnNhnVin_1.setBounds(1010, 189, 134, 22);
		add(lblTnNhnVin_1);
		
		textField_tenNhanVien1 = new JTextField();
		textField_tenNhanVien1.setColumns(10);
		textField_tenNhanVien1.setBounds(1009, 225, 198, 29);
		add(textField_tenNhanVien1);
		
		btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTmKim.setBounds(943, 350, 148, 36);
		add(btnTmKim);
		
		
		btnTmKim.setBackground(new Color(10, 227, 245));
		   ImageIcon icon3 = new ImageIcon("src\\icon\\find.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon3 = new ImageIcon(image3);
	        btnTmKim.setIcon(scaledIcon3);
		
		
		
		
		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLmMi.setBounds(49, 688, 148, 41);
		  Image img = ImageIO.read(new File("src\\icon\\load.png"));
	        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledImg);
	        // Đặt hình ảnh lên JButton
	        btnLmMi.setIcon(scaledIcon);
		
	        btnLmMi.setBackground(new Color(255, 204, 153)); // Màu cam nhạt
		 btnLmMi.addActionListener(e -> 	clearFields());
		 add(btnLmMi);
		
		JButton button_them = new JButton("Thêm");
		button_them.setBounds(49, 350, 134, 36);
		add(button_them);
		button_them.setBackground(new Color(144, 238, 144));
		
		 ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon11 = new ImageIcon(image);
	    button_them.setIcon(scaledIcon11);
		add(button_them);
	
		 button_them.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String maNhanVien = textField_maNhanVien.getText();
	                String hoNhanVien = textField_hoNhanVien.getText();
	                String tenNhanVien = textField_tenNhanVien.getText();
	                String soDienThoai_NhanVien = textField_soDienThoai.getText();
	                Date ngaySinh_NhanVien = dateChooser.getDate();
	                String chucVu = (String) comboBox_chucVu.getSelectedItem();

	                String gioiTinh_KH = "";
	                if (radiobutton_nam.isSelected()) {
	                    gioiTinh_KH = "Nam";
	                } else if (radiobutton_nu.isSelected()) {
	                    gioiTinh_KH = "Nữ";
	                }   
	                int luong = 0 ; 
			        if (textField_luong.getText().isEmpty()) {
			        	luong = 0;
			        } 
			        else { 
			        	try { 
			        		luong = Integer.parseInt(textField_luong.getText()); 
			        	} 
			        	catch (NumberFormatException e1) {
			        
			        	JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ"); 
			        	}
			        }
	                NhanVien_DTO NhanVien = new NhanVien_DTO(maNhanVien, hoNhanVien, tenNhanVien,
	                		soDienThoai_NhanVien, ngaySinh_NhanVien, gioiTinh_KH,chucVu,
	                		luong);

	                NhanVien_BUS NhanVienBUS = new NhanVien_BUS();
                   boolean success = NhanVienBUS.addNhanVien(NhanVien);

                   if (success) {
                       // Nếu thêm thành công, thông báo cho người dùng và làm mới bảng
                       JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                     //  refreshTable(); // Gọi phương thức làm mới bảng
                   } else {
                      
                       JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại");
                   }
	                loadTableData();
	            }
	        });
		
		 
		 textField_maNhanVien1.getDocument().addDocumentListener(new DocumentListener() {
			   
			    public void insertUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

			    public void removeUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}

			  
			});
		 textField_hoNhanVien1.getDocument().addDocumentListener(new DocumentListener() {
			   
			    public void insertUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

			    public void removeUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

				
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}

			    
			});
		 textField_tenNhanVien1.getDocument().addDocumentListener(new DocumentListener() {
			   
			    public void insertUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

			    public void removeUpdate(DocumentEvent e) {
			        searchAndUpdate();
			    }

				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}
			    
			});

		button_them.setBackground(new Color(144, 238, 144));
		ImageIcon icon1111 = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image111 = icon1111.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon12 = new ImageIcon(image111);
	    button_them.setIcon(scaledIcon12);
		button_them.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblXxxx = new JLabel("Tìm kiếm");
		lblXxxx.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXxxx.setBounds(846, 51, 114, 28);
		add(lblXxxx);
		
		
		JButton button_nhapExcel = new JButton("Nhập");
		button_nhapExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_nhapExcel.setBounds(824, 688, 148, 41);
		add(button_nhapExcel);
		 ImageIcon icon_thongKe11 = new ImageIcon("src\\icon\\excel.png");
		 Image image_thongKe11 = icon_thongKe11.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		 ImageIcon scaledIcon2 = new ImageIcon(image_thongKe11);
		 button_nhapExcel.setIcon(scaledIcon2);
		    button_nhapExcel.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();   
	                    NhapXuatExcel.readExcelToTable(file, table);
	                }
	            }
	        });
		
		 JButton button_xuatExcel = new JButton("Xuất");
		 button_xuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		 button_xuatExcel.setBounds(1066, 688, 140, 41);
		 add(button_xuatExcel);
			ImageIcon icon_thongKe2 = new ImageIcon("src\\icon\\excel.png");
			Image image_thongKe2 = icon_thongKe2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			 ImageIcon scaledIcon21 = new ImageIcon(image_thongKe2);
			 button_xuatExcel.setIcon(scaledIcon21);
			  button_xuatExcel.addActionListener(new ActionListener() {	        
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
			  
			 
			 btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAndUpdate();
				
			}
		});
	}
	 public void loadTableData() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
	    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<NhanVien_DTO> nhanVienList = NhanVien_BUS.layDanhSachNhanVien();

	        for (NhanVien_DTO nhanVien : nhanVienList) {
	            String ngaySinhFormatted = dateFormat.format(nhanVien.getNgaySinh());
	            model.addRow(new Object[] { nhanVien.getMaNhanVien(), 
	                nhanVien.getHo(), nhanVien.getTen(), 
	                nhanVien.getSoDienThoai(), ngaySinhFormatted, 
	                nhanVien.getGioiTinh(), nhanVien.getMaChucVu(),
	                nhanVien.getLuong() });
	        }
	 }
	 private static void searchAndDisplayFromDatabase(String maNhanVien, 
			 String hoNhanVien, String tenNhanVien,
			 String chucVu , String gioiTinh, String 
			 sapXepTheo, String tangGiam) {
		
		   ResultSet resultSet = null;
		try {
			resultSet = NhanVien_BUS.searchAndDisplayFromDatabase(maNhanVien, 
					   hoNhanVien, tenNhanVien,
					   chucVu, gioiTinh, sapXepTheo, tangGiam);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		   DefaultTableModel model = (DefaultTableModel) table.getModel();
		   model.setRowCount(0);
		   try {
				while (resultSet.next()) {				   
					    Object[] rowData = new Object[9];
					    rowData[0] = resultSet.getString("MaNhanVien");
					    rowData[1] = resultSet.getString("Ho");
					    rowData[2] = resultSet.getString("Ten");
					    rowData[3] = resultSet.getString("SoDienThoai");
					    rowData[4] = resultSet.getString("NgaySinh");
					    rowData[5] = resultSet.getString("gioiTinh");
					    rowData[6] = resultSet.getString("MaChucVu");
					    rowData[7] = resultSet.getString("Luong");	
					    model.addRow(rowData);
				  
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 private void searchAndUpdate() {
		 String maNhanVien = textField_maNhanVien1.getText();
	        String hoNhanVien = textField_hoNhanVien1.getText();
	        String tenNhanVien = textField_tenNhanVien1.getText();
	        String chucVu = (String) comboBox_chucVu_1.getSelectedItem();
	        if (chucVu.equals("Tất cả")) {
	            chucVu = ""; // Đặt chucVu thành chuỗi rỗng
	        }
	        String gioiTinh_KH;
	        if (radiobutton_nam_1.isSelected()) {
	            gioiTinh_KH = "Nam";
	        } else if (radiobutton_nu_1.isSelected()) {
	            gioiTinh_KH = "Nữ";
	        } else {
	            gioiTinh_KH = ""; // hoặc một giá trị mặc định khác
	        }
	        searchAndDisplayFromDatabase(maNhanVien, hoNhanVien, tenNhanVien, chucVu, gioiTinh_KH, null , null);
		}
	 private void clearFields() {	 
	        textField_maNhanVien.setText("");  
	        textField_tenNhanVien.setText("");
	        textField_hoNhanVien.setText("");
	        textField_soDienThoai.setText("");
	        comboBox_chucVu.setSelectedIndex(0);
	        textField_luong.setText("");
	        comboBox_chucVu_1.setSelectedIndex(0);
	        textField_maNhanVien1.setText("");     
	        textField_tenNhanVien1.setText("");
	        textField_hoNhanVien1.setText("");
	        dateChooser.setDate(null);
	        loadTableData();
	    }
}

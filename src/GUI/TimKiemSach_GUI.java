package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.Sach_BUS;
import DTO.Sach_DTO;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;


public class TimKiemSach_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JTextField textField_tenSach;
	private JTextField textField_maSach;
	private JComboBox <String>comboBox_nhaXuatBan;
	//private static Connection connection = ConnectionManager.openConnection();
	private JTextField textField_giaTu;
	private JTextField textField_giaDen;
	private JComboBox <String>comboBox_maTheLoai;
	private JComboBox<String> comboBox_maTacGia;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemSach_GUI frame = new TimKiemSach_GUI();
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
	public TimKiemSach_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 123, 833, 227);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã sách", "Mã NXB", 
					"Mã thể loại", "Mã tác giả", 
					"Tên sách", "Năm xuất bản", 
					"Số lượng", "Đơn giá", "Hình ảnh"
			}
		));
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        String maNhanVien = table.getValueAt(row, 0).toString(); // Giả sử cột đầu tiên chứa mã nhân viên
		        HoaDon_GUI2_JPanel.setSachChoCombobox(maNhanVien);
		        CTGG_GUI2_JPanel.setSachChoCombobox(maNhanVien);
		        NhapKho_GUI2_JPanel.setSachChoCombobox(maNhanVien);
		    }
		});

		textField_tenSach = new JTextField();
		textField_tenSach.setBounds(570, 46, 169, 19);
		contentPane.add(textField_tenSach);
		textField_tenSach.setColumns(10);
		
		JButton button_tim = new JButton("Tìm");
	
		button_tim.setBounds(761, 42, 85, 26);
		ImageIcon icon2 = new ImageIcon("C:\\Users\\ASUS Vivobook\\Desktop\\icon\\loupe.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2 = new ImageIcon(image2);
	    button_tim.setIcon(scaledIcon2);
		contentPane.add(button_tim);
		button_tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String valueMaSach = textField_maSach.getText().trim(); // Lấy giá trị từ JTextField và loại bỏ các khoảng trắng
		        String valueMaNXB = (String) comboBox_nhaXuatBan.getSelectedItem();
		        String valueMaTheLoai = (String) comboBox_maTheLoai.getSelectedItem();
		        String valueMaTacGia = (String) comboBox_maTacGia.getSelectedItem();
		        String valueTenSach = textField_tenSach.getText().trim();
		        String valueDonGiaTu = textField_giaTu.getText().trim();
		        String valueDonGiaDen = textField_giaDen.getText().trim();
		        if (valueMaNXB.equals("Tất cả")) {
		            valueMaNXB = "";
		        }
		        if (valueMaTheLoai.equals("Tất cả")) {
		            valueMaTheLoai = "";
		        }
		        if (valueMaTacGia.equals("Tất cả")) {
		            valueMaTacGia = "";
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
		            textField_giaTu.setText("");
		            textField_giaDen.setText("");
		        } else {
		        	searchAndDisplayFromDatabase(valueMaSach,
		        			valueMaNXB, valueMaTheLoai, 
		        			valueMaTacGia, valueTenSach,
		        			valueDonGiaTu, valueDonGiaDen , table);    
		        }
		    }

			private void searchAndDisplayFromDatabase(String valueMaSach, String valueMaNXB, String valueMaTheLoai,
					String valueMaTacGia, String valueTenSach, String valueDonGiaTu, String valueDonGiaDen,
					JTable table) {
				  ResultSet resultSet = Sach_BUS.searchAndDisplayFromDatabase(valueMaSach, valueMaNXB,  valueMaTheLoai,
							 valueMaTacGia,valueTenSach,  valueDonGiaTu, valueDonGiaDen);

			        // Xóa dữ liệu hiện tại trong bảng
			   DefaultTableModel model = (DefaultTableModel) table.getModel();
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});

		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setBounds(23, 46, 85, 16);
		contentPane.add(lblNewLabel);
		
		textField_maSach = new JTextField();
		textField_maSach.setBounds(118, 46, 96, 19);
		contentPane.add(textField_maSach);
		textField_maSach.setColumns(10);
		
		JLabel lblTnNhnVin = new JLabel("Tên sách");
		lblTnNhnVin.setBounds(483, 47, 77, 16);
		contentPane.add(lblTnNhnVin);
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhà xuất bản");
		lblNewLabel_2.setBounds(23, 80, 85, 16);
		contentPane.add(lblNewLabel_2);
	
		
		comboBox_nhaXuatBan = new JComboBox<String>();
		comboBox_nhaXuatBan.setBounds(118, 75, 96, 21);
		contentPane.add(comboBox_nhaXuatBan);
		comboBox_nhaXuatBan.addItem("Tất cả");
		comboBox_nhaXuatBan.addItem("Tất cả");
		Sach_BUS.loadDataIntoList_maNXB(comboBox_nhaXuatBan);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Sách");
		lblNewLabel_1.setBounds(314, 10, 200, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblHNhnVin = new JLabel("Mã thể loại");
		lblHNhnVin.setBounds(224, 49, 85, 16);
		contentPane.add(lblHNhnVin);
		
		JButton button_lamMoi = new JButton("Làm mới");
		button_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_lamMoi.setBounds(761, 75, 85, 26);
		contentPane.add(button_lamMoi);
		
		comboBox_maTheLoai = new JComboBox<String>();
		comboBox_maTheLoai.setBounds(314, 45, 127, 21);
		contentPane.add(comboBox_maTheLoai);
		comboBox_maTheLoai.addItem("Tất cả");
		Sach_BUS.loadDataIntoComboBox_maTheLoai(comboBox_maTheLoai);
		
		JLabel lblMTcGi = new JLabel("Mã tác giả");
		lblMTcGi.setBounds(224, 79, 85, 16);
		contentPane.add(lblMTcGi);
		
		comboBox_maTacGia = new JComboBox<String>();
		comboBox_maTacGia.setBounds(314, 75, 127, 21);
		contentPane.add(comboBox_maTacGia);
		comboBox_maTacGia.addItem("Tất cả");
		Sach_BUS.loadDataIntoComboBox_maTacGia(comboBox_maTacGia);
		
		JLabel lblNewLabel_3 = new JLabel("Giá từ");
		lblNewLabel_3.setBounds(483, 82, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_giaTu = new JTextField();
		textField_giaTu.setBounds(570, 79, 61, 19);
		contentPane.add(textField_giaTu);
		textField_giaTu.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("đến");
		lblNewLabel_4.setBounds(641, 78, 45, 21);
		contentPane.add(lblNewLabel_4);
		
		textField_giaDen = new JTextField();
		textField_giaDen.setColumns(10);
		textField_giaDen.setBounds(695, 79, 44, 19);
		contentPane.add(textField_giaDen);
		button_lamMoi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	textField_maSach.setText("");	
		    	textField_giaTu.setText("");	
		    	textField_giaDen.setText("");	
		        textField_tenSach.setText("");
		        comboBox_maTacGia.setSelectedIndex(0); 
		        comboBox_maTheLoai.setSelectedIndex(0); 
		        comboBox_nhaXuatBan.setSelectedIndex(0); 
		    	QuanLiSach_GUI2_JPanel.loadTableData(table);
		    }
		});
	}
		 public void loadTableData() {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); 
		        ArrayList<Sach_DTO> sachList = Sach_BUS.getAllBooks();
		        for (Sach_DTO sach : sachList) {
		            model.addRow(new Object[] { sach.getMaSach(), sach.getMaNXB(), sach.getMaTheLoai(), sach.getMaTacGia(),
		                                         sach.getTenSach(), sach.getNamXuatBan(), sach.getSoLuong(), sach.getDonGia(),
		                                         sach.getHinhAnh() });
		        }
		    }
}

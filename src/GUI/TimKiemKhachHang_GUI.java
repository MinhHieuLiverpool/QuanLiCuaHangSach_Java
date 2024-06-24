package GUI;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHang_BUS;
import DTO.KhachHang_DTO;


import Database.ConnectionManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class TimKiemKhachHang_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JTextField textField_maNhanVien;
	private static Connection connection = ConnectionManager.openConnection();
	private JTextField textField_hoNhanVien;
	private ButtonGroup buttonGroup;
	private JTextField textField_tenNhanVien;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemKhachHang_GUI frame = new TimKiemKhachHang_GUI();
					frame.setVisible(true);
					frame.loadTableData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimKiemKhachHang_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 833, 227);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã khách hàng", "Họ", "Tên", "Số điện thoại",
                    "Ngày sinh", "Giới tính", "Tổng chi"
			}
		));
		scrollPane.setViewportView(table);
		
		// Thêm ActionListener cho bảng (table)
		// Khi một dòng được chọn trong bảng nhân viên
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.getSelectedRow();
		        String maNhanVien = table.getValueAt(row, 0).toString(); // Giả sử cột đầu tiên chứa mã nhân viên
		        // Đặt mã nhân viên vào combobox trên cửa sổ `HoaDon_GUI` hiện tại
		        HoaDon_GUI2_JPanel.setKhachHangChoCombobox(maNhanVien);
		    }
		});
		
		JButton button_tim = new JButton("Tìm");
		button_tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_tim.setBounds(761, 42, 85, 26);
		contentPane.add(button_tim);
		
		JLabel lblNewLabel = new JLabel("Mã Khách Hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 46, 134, 16);
		contentPane.add(lblNewLabel);
		
		textField_maNhanVien = new JTextField();
		textField_maNhanVien.setBounds(167, 42, 176, 26);
		contentPane.add(textField_maNhanVien);
		textField_maNhanVien.setColumns(10);
		
		// Tạo button group
		buttonGroup = new ButtonGroup();
		
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Khách Hàng");
		lblNewLabel_1.setForeground(new Color(139, 69, 19));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(314, 10, 285, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblHNhnVin = new JLabel("Họ");
		lblHNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHNhnVin.setBounds(23, 91, 85, 19);
		contentPane.add(lblHNhnVin);
		
		textField_hoNhanVien = new JTextField();
		textField_hoNhanVien.setBounds(164, 88, 179, 26);
		contentPane.add(textField_hoNhanVien);
		textField_hoNhanVien.setColumns(10);
		
		JButton button_lamMoi = new JButton("Làm mới");
		button_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_lamMoi.setBounds(761, 75, 85, 26);
		contentPane.add(button_lamMoi);
		
		JLabel lblNewLabel_2_1 = new JLabel("Giới tính");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(388, 91, 134, 20);
		contentPane.add(lblNewLabel_2_1);
		
		JRadioButton radio_nam = new JRadioButton("Nam");
		radio_nam.setFont(new Font("Tahoma", Font.BOLD, 14));
		radio_nam.setBounds(562, 90, 85, 21);
		contentPane.add(radio_nam);
		
		JRadioButton radio_nu = new JRadioButton("Nữ");
		radio_nu.setFont(new Font("Tahoma", Font.BOLD, 14));
		radio_nu.setBounds(673, 90, 55, 21);
		contentPane.add(radio_nu);
		ButtonGroup gioiTinhGroup = new ButtonGroup();
		gioiTinhGroup.add(radio_nam);
		gioiTinhGroup.add(radio_nu);
		
		textField_tenNhanVien = new JTextField();
		textField_tenNhanVien.setColumns(10);
		textField_tenNhanVien.setBounds(572, 42, 156, 23);
		contentPane.add(textField_tenNhanVien);
		
		JLabel lblTnNhnVin = new JLabel("Tên Khách Hàng");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setBounds(388, 42, 156, 26);
		contentPane.add(lblTnNhnVin);
		
		button_lamMoi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xóa nội dung của các text field
		        textField_maNhanVien.setText("");
		        textField_hoNhanVien.setText("");
		        textField_tenNhanVien.setText("");
		        radio_nam.setSelected(false);
		        radio_nu.setSelected(false);
		        loadTableData();
		    }
		});

		button_tim.addActionListener(new ActionListener() {
			
			    public void actionPerformed(ActionEvent e) {
			        String maNhanVien = textField_maNhanVien.getText();
			        String hoNhanVien = textField_hoNhanVien.getText();
			        String tenNhanVien = textField_tenNhanVien.getText();
			        String gioiTinh_KH;
			        if (radio_nam.isSelected()) {
			            gioiTinh_KH = "Nam";
			        } else if (radio_nu.isSelected()) {
			            gioiTinh_KH = "Nữ";
			        } else {
			            gioiTinh_KH = ""; // hoặc một giá trị mặc định khác
			        }

			        searchAndDisplayFromDatabase(maNhanVien, hoNhanVien, tenNhanVien,gioiTinh_KH);
			    }
			});


	}
		
	 public void loadTableData() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<KhachHang_DTO> nhanVienList = KhachHang_BUS.getAllEmployee();
	        for (KhachHang_DTO nhanVien : nhanVienList) {
	            String ngaySinhFormatted = dateFormat.format(nhanVien.getNgaySinh_KH());
	            model.addRow(new Object[] { nhanVien.getMaKhachHang(), 
	                nhanVien.getHoKhachHang(), nhanVien.getTenKhachHang(), 
	                nhanVien.getSoDienThoai_KH(), ngaySinhFormatted, 
	                nhanVien.getGioiTinh_KH(),
	                nhanVien.getTongChi() });
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
				// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			   
			}

}

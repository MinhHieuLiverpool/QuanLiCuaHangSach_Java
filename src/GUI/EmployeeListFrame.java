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

import DAO.ChucVu_DAO;
import DAO.NhanVien_DAO;
import DTO.ChucVu_DTO;
import DTO.NhanVien_DTO;

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


public class EmployeeListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	private JTextField textField_tenNhanVien;
	private JTextField textField_maNhanVien;
	private JComboBox <String>comboBox_chucVu;
	private static Connection connection = ConnectionManager.openConnection();
	private JTextField textField_hoNhanVien;
	private ButtonGroup buttonGroup;
	private JRadioButton radio_nam;
	private JRadioButton radio_nu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeListFrame frame = new EmployeeListFrame();
					frame.setVisible(true);
					frame.loadTableData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeListFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 172, 952, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã Nhân Viên", "Họ", "Tên", "Số điện thoại", 
		    		"Ngày sinh", "Giới tính", "Chức Vụ", "Lương"
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
		        HoaDon_GUI2_JPanel.setMaNhanVienChoCombobox(maNhanVien);
		    }
		});

		
		textField_tenNhanVien = new JTextField();
		textField_tenNhanVien.setBounds(692, 49, 143, 30);
		contentPane.add(textField_tenNhanVien);
		textField_tenNhanVien.setColumns(10);
		
		JButton button_tim = new JButton("Tìm");
		button_tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_tim.setBounds(845, 53, 130, 32);
		contentPane.add(button_tim);
		
	
		
		JLabel lblNewLabel = new JLabel("Mã Nhân Viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 49, 107, 30);
		contentPane.add(lblNewLabel);
		
		textField_maNhanVien = new JTextField();
		textField_maNhanVien.setBounds(140, 49, 130, 30);
		contentPane.add(textField_maNhanVien);
		textField_maNhanVien.setColumns(10);
		
		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setBounds(557, 46, 134, 32);
		contentPane.add(lblTnNhnVin);
		
		JLabel lblNewLabel_2 = new JLabel("Chức Vụ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(28, 111, 85, 30);
		contentPane.add(lblNewLabel_2);
		
		comboBox_chucVu = new JComboBox<String>();
		comboBox_chucVu.setBounds(140, 111, 130, 30);
		contentPane.add(comboBox_chucVu);
		comboBox_chucVu.addItem("Tất cả");
		loadDataIntoComboBox_tenChucVu(comboBox_chucVu);
		
		JLabel lblNewLabel_2_1 = new JLabel("Giới tính");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(285, 109, 67, 30);
		contentPane.add(lblNewLabel_2_1);
		
		radio_nam = new JRadioButton("Nam");
		radio_nam.setFont(new Font("Tahoma", Font.BOLD, 14));
		radio_nam.setBounds(381, 108, 85, 32);
		contentPane.add(radio_nam);
		
		radio_nu = new JRadioButton("Nữ");
		radio_nu.setFont(new Font("Tahoma", Font.BOLD, 14));
		radio_nu.setBounds(492, 107, 55, 34);
		contentPane.add(radio_nu);
		
		// Tạo button group
		buttonGroup = new ButtonGroup();

		// Thêm các radio button vào button group

		buttonGroup.add(radio_nam);
		buttonGroup.add(radio_nu);
		
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm Nhân Viên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(314, 10, 200, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblHNhnVin = new JLabel("Họ Nhân Viên");
		lblHNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHNhnVin.setBounds(280, 50, 123, 30);
		contentPane.add(lblHNhnVin);
		
		textField_hoNhanVien = new JTextField();
		textField_hoNhanVien.setBounds(413, 50, 134, 30);
		contentPane.add(textField_hoNhanVien);
		textField_hoNhanVien.setColumns(10);
		
		JButton button_lamMoi = new JButton("Làm mới");
		button_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_lamMoi.setBounds(845, 115, 130, 32);
		contentPane.add(button_lamMoi);
		button_lamMoi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xóa nội dung của các text field
		        textField_maNhanVien.setText("");
		        textField_hoNhanVien.setText("");
		        textField_tenNhanVien.setText("");

		        // Đặt lại combobox "Chức Vụ" về giá trị mặc định
		        comboBox_chucVu.setSelectedIndex(0); // Giả sử giá trị mặc định có index là 0

		        // Tắt chọn tất cả các radio button
		  
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
			        String chucVu = (String) comboBox_chucVu.getSelectedItem();
			        if (chucVu.equals("Tất cả")) {
			            chucVu = ""; // Đặt chucVu thành chuỗi rỗng
			        }

			        String gioiTinh_KH;
			        if (radio_nam.isSelected()) {
			            gioiTinh_KH = "Nam";
			        } else if (radio_nu.isSelected()) {
			            gioiTinh_KH = "Nữ";
			        } else {
			            gioiTinh_KH = ""; // hoặc một giá trị mặc định khác
			        }

			        searchAndDisplayFromDatabase(maNhanVien, hoNhanVien, tenNhanVien, chucVu, gioiTinh_KH, null, null);
			    }
			});


	}
		
	 public void loadTableData() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng

	        // Gọi phương thức của DAO để lấy dữ liệu sách từ cơ sở dữ liệu
	       // Sach_DAO sachDAO = new Sach_DAO(connection);
	        NhanVien_DAO nhanVienDAO = new NhanVien_DAO(connection);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<NhanVien_DTO> nhanVienList = nhanVienDAO.getAllEmployee();

	        for (NhanVien_DTO nhanVien : nhanVienList) {
	            String ngaySinhFormatted = dateFormat.format(nhanVien.getNgaySinh());
	            model.addRow(new Object[] { nhanVien.getMaNhanVien(), 
	                nhanVien.getHo(), nhanVien.getTen(), 
	                nhanVien.getSoDienThoai(), ngaySinhFormatted, 
	                nhanVien.getGioiTinh(), nhanVien.getMaChucVu(),
	                nhanVien.getLuong() });
	        }
	 }
	 
	   	 private void loadDataIntoComboBox_tenChucVu(JComboBox<String> tenComboBox) {
	 	    //    comboBox_maTacGia.removeAllItems(); // Xóa tất cả các mục hiện có trong combobox
	 	        ChucVu_DAO chucVuDAO = new ChucVu_DAO(connection); // Tạo một đối tượng DAO
	 	        ArrayList<ChucVu_DTO> authors = chucVuDAO.getAllAuthors(); // Lấy danh sách tác giả từ cơ sở dữ liệu
	 	        for (ChucVu_DTO author : authors) {
	 	        	tenComboBox.addItem(author.getMaChucVu());
	 	        	//System.out.println(author.getMaChucVu());// Thêm tên tác giả vào combobox
	 	        }
	 	   }
	   	 
	   	 private static void searchAndDisplayFromDatabase(String maNhanVien, 
				 String hoNhanVien, String tenNhanVien,
				 String chucVu , String gioiTinh, String 
				 sapXepTheo, String tangGiam) {
			
			   ResultSet resultSet = null;
			try {
				resultSet = NhanVien_DAO.searchAndDisplayFromDatabase(maNhanVien, 
						   hoNhanVien, tenNhanVien,
						   chucVu, gioiTinh, sapXepTheo, tangGiam);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			        // Xóa dữ liệu hiện tại trong bảng
			   DefaultTableModel model = (DefaultTableModel) table.getModel();
			   model.setRowCount(0);
			   try {
					while (resultSet.next()) {
						    // Lấy dữ liệu từ ResultSet và thêm vào bảng
						    Object[] rowData = new Object[9];
						    rowData[0] = resultSet.getString("MaNhanVien");
						    rowData[1] = resultSet.getString("Ho");
						    rowData[2] = resultSet.getString("Ten");
						    rowData[3] = resultSet.getString("SoDienThoai");
						    rowData[4] = resultSet.getString("NgaySinh");
						    rowData[5] = resultSet.getString("gioiTinh");
						    rowData[6] = resultSet.getString("MaChucVu");
						    rowData[7] = resultSet.getString("Luong");
					//	    rowData[8] = resultSet.getString("HinhAnh");
						    model.addRow(rowData);
						  
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			   
			}
		 private static void checkEmployeeExists(String maNhanVien
			) {
			
			   ResultSet resultSet = null;
				resultSet = NhanVien_DAO.getEmployeeByMaNhanVien(maNhanVien
						  );

			        // Xóa dữ liệu hiện tại trong bảng
			   DefaultTableModel model = (DefaultTableModel) table.getModel();
			   model.setRowCount(0);
			   try {
					while (resultSet.next()) {
						    // Lấy dữ liệu từ ResultSet và thêm vào bảng
						    Object[] rowData = new Object[9];
						    rowData[0] = resultSet.getString("MaNhanVien");
						    rowData[1] = resultSet.getString("Ho");
						    rowData[2] = resultSet.getString("Ten");
						    rowData[3] = resultSet.getString("SoDienThoai");
						    rowData[4] = resultSet.getString("NgaySinh");
						    rowData[5] = resultSet.getString("gioiTinh");
						    rowData[6] = resultSet.getString("MaChucVu");
						    rowData[7] = resultSet.getString("Luong");
					//	    rowData[8] = resultSet.getString("HinhAnh");
						    model.addRow(rowData);
						  
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			   
			}

		
}

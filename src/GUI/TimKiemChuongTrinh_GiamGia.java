package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import BUS.CTGG_BUS;
import DTO.CTGG_DTO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TimKiemChuongTrinh_GiamGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maGiamGiaTimKiem;
	private JTextField textField_tenChuongTrinhTimKiem;
	private static JTable table_giamGia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemChuongTrinh_GiamGia frame = new TimKiemChuongTrinh_GiamGia();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TimKiemChuongTrinh_GiamGia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(220, 20, 60));
		lblNewLabel.setBounds(348, 10, 174, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNgyBtu = new JLabel("Ngày bắt đầu");
		lblNgyBtu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyBtu.setBounds(26, 65, 123, 28);
		contentPane.add(lblNgyBtu);
		
		JLabel lblMGimGim = new JLabel("Mã giảm giá");
		lblMGimGim.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMGimGim.setBounds(26, 110, 112, 28);
		contentPane.add(lblMGimGim);
		
		textField_maGiamGiaTimKiem = new JTextField();
		textField_maGiamGiaTimKiem.setColumns(10);
		textField_maGiamGiaTimKiem.setBounds(159, 113, 173, 28);
		contentPane.add(textField_maGiamGiaTimKiem);
		
		JDateChooser dateChooser_ngayBatDauTimKiem = new JDateChooser();
		dateChooser_ngayBatDauTimKiem.setBounds(159, 65, 174, 28);
		contentPane.add(dateChooser_ngayBatDauTimKiem);
		
		textField_tenChuongTrinhTimKiem = new JTextField();
		textField_tenChuongTrinhTimKiem.setColumns(10);
		textField_tenChuongTrinhTimKiem.setBounds(530, 109, 182, 28);
		contentPane.add(textField_tenChuongTrinhTimKiem);
		
		JDateChooser dateChooser_ngayKetThucTimKiem = new JDateChooser();
		dateChooser_ngayKetThucTimKiem.setBounds(529, 65, 183, 24);
		contentPane.add(dateChooser_ngayKetThucTimKiem);
		
		JLabel lblTnChngTrnh_1 = new JLabel("Tên chương trình");
		lblTnChngTrnh_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnChngTrnh_1.setBounds(375, 110, 144, 28);
		contentPane.add(lblTnChngTrnh_1);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày kết thúc");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(375, 69, 112, 21);
		contentPane.add(lblNewLabel_7);
		
		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTmKim.setBackground(new Color(255, 255, 169));
		btnTmKim.setBounds(739, 65, 144, 28);
		contentPane.add(btnTmKim);
		 ImageIcon icon12 = new ImageIcon("src\\icon\\loupe.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon12 = new ImageIcon(image12);
	        btnTmKim.setIcon(scaledIcon12);
		contentPane.add(btnTmKim);
		btnTmKim.addActionListener(new ActionListener() {
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

		        try {
					searchAndDisplayFromDatabase(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }

		    private void searchAndDisplayFromDatabase(String maCTGG, String tenCTGG, java.sql.Date ngayBatDau,
		            java.sql.Date ngayKetThuc) throws SQLException {
		        ResultSet resultSet = null;
		     
		        try {
		            resultSet = CTGG_BUS.searchAndDisplayFromDatabase(maCTGG, tenCTGG, ngayBatDau, ngayKetThuc);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        DefaultTableModel model = (DefaultTableModel) table_giamGia.getModel();
		        model.setRowCount(0);	      
		            while (resultSet.next()) {
		                Object[] rowData = new Object[4];
		                rowData[0] = resultSet.getString("maCTGG");
		                rowData[1] = resultSet.getString("tenCTGG");
		                rowData[2] = resultSet.getDate("NgayBatDau"); 
		                rowData[3] = resultSet.getDate("NgayKetThuc"); 
		                model.addRow(rowData);
		            }
		    }	
		});
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 176, 833, 251);
		contentPane.add(scrollPane);
		
	
	
		table_giamGia = new JTable();
		table_giamGia.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã chương trình", "Tên chương trình",
				"Ngày bắt đầu", "Ngày kết thúc"
			}
		));
	
	scrollPane.setViewportView(table_giamGia);
	table_giamGia.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        int row = table_giamGia.getSelectedRow();
	        String maNhanVien = table_giamGia.getValueAt(row, 0).toString(); // Giả sử cột đầu tiên chứa mã nhân viên
	        // Đặt mã nhân viên vào combobox trên cửa sổ `HoaDon_GUI` hiện tại
	        HoaDon_GUI2_JPanel.setCTGGChoCombobox(maNhanVien);
	    }
	});
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
	      
	  
	 }
}

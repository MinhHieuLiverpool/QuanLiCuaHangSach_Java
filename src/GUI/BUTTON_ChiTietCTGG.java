package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.ChiTietCTGG_BUS;
import DTO.ChiTietCTGG_DTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BUTTON_ChiTietCTGG extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public BUTTON_ChiTietCTGG(String ma) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chi Tiết Về Chương Trình Giảm Giá");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(273, 0, 361, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Chương Trình Giảm Giá");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(20, 41, 253, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(283, 41, 108, 31);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setText(ma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 867, 193);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã chi tiết CTGG", "Mã chương trình giảm giá", "Phần trăm giảm (%)", "Mã sách"
				}
			));
		scrollPane.setViewportView(table);
		loadTableData_Chitiet(ma);
	}
	 public void loadTableData_Chitiet(String maCTGG) throws SQLException {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
	        ArrayList<ChiTietCTGG_DTO> chiTietCTGGList = ChiTietCTGG_BUS.layDanhSachChiTietCTGG(maCTGG);

	        for (ChiTietCTGG_DTO ctgg : chiTietCTGGList) {
	           
	            model.addRow(new Object[] { ctgg.getMaChiTietCTGG() , ctgg.getMaCTGG() , ctgg.getPhanTramGiamGia(), ctgg.getMaSach()});
	        }
	         
	      
	 }
}

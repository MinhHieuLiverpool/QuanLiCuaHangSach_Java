package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import BUS.ChiTietPhieuNhap_BUS;
import BUS.NhaCungCap_BUS;
import BUS.NhanVien_BUS;
import BUS.Sach_BUS;
import NhapXuatEXCEL.NhapXuatExcel;
import DTO.ChiTietPhieuNhap_DTO;
import BUS.PhieuNhap_BUS;
import DTO.PhieuNhap_DTO;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

public class NhapKho_GUI2_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maPhieuNhap;
	private JLabel textField_donGia;
	private JComboBox <String>comboBox_maNhaCungCap;
	private JComboBox <String>comboBox_maNhanVien;
	private static JTable table;
	private static JTable table_chiTiet;
	private JTextField textField_soLuong;
	private JTextField textField_tu;
	private JTextField textField_den;
	private JLabel label_tongTien;
	private JComboBox<String> comboBox_maNhaCungCap_TimKiem;
	private JComboBox<String> comboBox_maNhanVien_TimKiem;
	private static JComboBox<String> comboBox_maSach;
	private JLabel jlabel_tongTien1;
	private JComboBox<String> comboBox_maPhieuNhap;
	
	private JTextField textField;
	private JLabel lblSPhiuNhp;
	private JLabel label_soPhieuNhap;
	private JLabel label_soChiTietPhieuNhap;
	private JComboBox<String> comboBox_maSach1;
	
	// private Connection connection = ConnectionManager.openConnection(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhapKho_GUI2_JPanel frame = new NhapKho_GUI2_JPanel();
					frame.setVisible(true);
					frame.loadTableData();
					frame.loadTableData_chiTiet();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public NhapKho_GUI2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		JLabel lblQunLNhp = new JLabel("QUẢN LÝ NHẬP KHO");
		lblQunLNhp.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLNhp.setBackground(Color.LIGHT_GRAY);
		lblQunLNhp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunLNhp.setBounds(23, 6, 1157, 23);
		add(lblQunLNhp);
		
		JLabel lblMPhiuNhp_1 = new JLabel("Mã phiếu nhập");
		lblMPhiuNhp_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMPhiuNhp_1.setBounds(23, 39, 178, 28);
		add(lblMPhiuNhp_1);
		
		textField_maPhieuNhap = new JTextField();
		textField_maPhieuNhap.setBounds(211, 39, 269, 28);
		add(textField_maPhieuNhap);
		textField_maPhieuNhap.setColumns(10);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMNhnVin.setBounds(23, 78, 178, 30);
		add(lblMNhnVin);
		
		JLabel lblMNhCung = new JLabel("Mã nhà cung cấp");
		lblMNhCung.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMNhCung.setBounds(23, 118, 178, 30);
		add(lblMNhCung);
		
		JLabel lblNgyLp = new JLabel("Ngày lập");
		lblNgyLp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgyLp.setBounds(23, 158, 178, 28);
		add(lblNgyLp);
	  JDateChooser dateChooser = new JDateChooser();
	        dateChooser.setBounds(211, 158, 269, 28);
	        add(dateChooser);
		
		JLabel lblTngTin_1 = new JLabel("Tổng tiền");
		lblTngTin_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTngTin_1.setBounds(23, 196, 178, 30);
		add(lblTngTin_1);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 282, 493, 412);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên",
				"Ngày lập", "Tổng tiền"
			}
		));
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            String maHoaDon = model.getValueAt(selectedRow, 0).toString();
		            for (int i = 0; i < comboBox_maPhieuNhap.getItemCount(); i++) {
		                if (comboBox_maPhieuNhap.getItemAt(i).equals(maHoaDon)) {
		                    comboBox_maPhieuNhap.setSelectedIndex(i);
		                    break; 
		                }
		            }
		            textField_maPhieuNhap.setText(model.getValueAt(selectedRow, 0).toString());
		            comboBox_maNhanVien.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		            comboBox_maNhaCungCap.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
		         

                    try {
                        String dateString = model.getValueAt(selectedRow, 3).toString(); // Lấy ngày tháng năm dưới dạng chuỗi từ JTable
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng năm
                        Date ngaySinhDate = dateFormat.parse(dateString); // Chuyển đổi chuỗi sang Date

                        // Thiết lập ngày tháng năm cho JDateChooser
                        dateChooser.setDate(ngaySinhDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }   
                    label_tongTien.setText(model.getValueAt(selectedRow, 4).toString());            
		            ArrayList<ChiTietPhieuNhap_DTO> chiTietList = null;
					try {
						chiTietList = ChiTietPhieuNhap_BUS.layDanhSachChiTietPhieuNhap(maHoaDon);
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					}
		            DefaultTableModel chiTietModel = (DefaultTableModel) table_chiTiet.getModel();
		            chiTietModel.setRowCount(0);           
		            for (ChiTietPhieuNhap_DTO chiTiet : chiTietList) {
		                chiTietModel.addRow(new Object[] { 
		                		chiTiet.getMaPhieuNhap() ,
		                		chiTiet.getMaSach(),
		                		chiTiet.getSoLuong(),
		                		chiTiet.getDonGia(),
		                		chiTiet.getTongTien()
		                });
		            }
		            updateRowCountLabel(table_chiTiet,label_soChiTietPhieuNhap);

		           // loadTableData_chiTiet();
		        }
		    }
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(584, 507, 623, 187);
		add(scrollPane_1);
		
		table_chiTiet = new JTable();
		table_chiTiet.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			"Mã phiếu nhập", "Mã sách",
				"Số lượng", "Đơn giá" , "Tổng tiền nhập"
			}
		));
		scrollPane_1.setViewportView(table_chiTiet);
		table_chiTiet.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table_chiTiet.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table_chiTiet.getModel();
		 
		            comboBox_maPhieuNhap.setSelectedItem(model.getValueAt(selectedRow, 0).toString());
		            comboBox_maSach.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		         
		            textField_soLuong.setText(model.getValueAt(selectedRow, 2).toString());
		            textField_donGia.setText(model.getValueAt(selectedRow, 3).toString());
		            jlabel_tongTien1.setText(model.getValueAt(selectedRow, 4).toString());
		         
		        
		        }
		    }
		});

		
		JLabel lblnGi = new JLabel("Đơn giá");
		lblnGi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnGi.setBounds(810, 385, 94, 23);
		add(lblnGi);
		
		
		textField_donGia = new JLabel();
		textField_donGia.setBounds(810, 418, 186, 23);
		add(textField_donGia);
		textField_donGia.setOpaque(true); 
		textField_donGia.setBackground(Color.LIGHT_GRAY); 
		
		JLabel lblMSch = new JLabel("Mã sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMSch.setBounds(584, 319, 189, 23);
		add(lblMSch);
		
		JLabel lblMPhiuNhp = new JLabel("Mã phiếu nhập");
		lblMPhiuNhp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMPhiuNhp.setBounds(810, 319, 186, 23);
		add(lblMPhiuNhp);
		
		comboBox_maNhanVien = new JComboBox<String>();
		comboBox_maNhanVien.setBounds(211, 78, 269, 30);
		add(comboBox_maNhanVien);
		NhanVien_BUS.loadDataIntoComboBox_maNhanVien(comboBox_maNhanVien);
		
		comboBox_maNhaCungCap = new JComboBox<String>();
		comboBox_maNhaCungCap.setBounds(211, 119, 269, 29);
		add(comboBox_maNhaCungCap);
		NhaCungCap_BUS.loadDataIntoComboBox_maNhaCungCap(comboBox_maNhaCungCap);
		
		JLabel lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTngTin.setBounds(1037, 385, 94, 23);
		add(lblTngTin);
		
	
		
		JButton button_them = new JButton("Thêm");
		button_them.setBounds(141, 244, 109, 28);
		add(button_them);
		button_them.setBackground(new Color(144, 238, 144));
		
		 ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon = new ImageIcon(image);
	    button_them.setIcon(scaledIcon);
		add(button_them);
		 button_them.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String maPhieuNhap = textField_maPhieuNhap.getText();
	                String maNhanVien = comboBox_maNhanVien.getSelectedItem().toString();
	                String maNhaCungCap = comboBox_maNhaCungCap.getSelectedItem().toString();	              
	                Date ngayLap = dateChooser.getDate();
	                PhieuNhap_DTO phieunhap = new PhieuNhap_DTO(maPhieuNhap, maNhaCungCap, maNhanVien, ngayLap, 0);
	                
	                boolean success = false;
				try {
					success = PhieuNhap_BUS.addPhieuNhap(phieunhap);
				} catch (ParseException e1) {
			
					e1.printStackTrace();
				} catch (SQLException e1) {
			
					e1.printStackTrace();
				}

                 if (success) {           
                     JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thành công");
                     try {
 						PhieuNhap_BUS.loadDataIntoComboBox_maPhieuNhap(comboBox_maPhieuNhap);
 					} catch (SQLException e1) {
 					
 						e1.printStackTrace();
 					}				
                 } else {
                    
                     JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thất bại");
                 }
	                loadTableData();
	            }
	        });
		
		JButton button_xoa = new JButton("Xóa");
		add(button_xoa);
		button_xoa.setBounds(407, 244, 109, 28);
		button_xoa.setBackground(new Color(220, 20, 60));
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon2);
	
	    button_xoa.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String maCTGG = textField_maPhieuNhap.getText(); // Lấy mã CTGG từ JTextField
	            if (maCTGG.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để xóa!");
	                return;
	            }   
	            try {
	                boolean deleted = PhieuNhap_BUS.xoaNhapKho(maCTGG);
	                if (deleted) {
	                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
	                    loadTableData();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Xóa không thành công!");
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Lỗi khi xóa CTGG!");
	            }
	        }
	    });


		
		JButton btnSa = new JButton("Sửa");
		btnSa.setBackground(Color.ORANGE);
		btnSa.setBounds(290, 244, 92, 28);
		ImageIcon icon9 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image9 = icon9.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon9 = new ImageIcon(image9);
	    btnSa.setIcon(scaledIcon9);
		add(btnSa);
		btnSa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {    
		        try {
		        	String maPhieuNhap = textField_maPhieuNhap.getText(); // Lấy giá trị từ trường textField_maPhieuNhap
		        	String maNhanVien = comboBox_maNhanVien.getSelectedItem().toString(); // Giả sử textField_maNhanVien là trường nhập mã nhân viên
		        	String maNhaCungCap = comboBox_maNhaCungCap.getSelectedItem().toString(); // Giả sử textField_maNhaCungCap là trường nhập mã nhà cung cấp
		        	Date ngayLap = dateChooser.getDate(); 
		        	double tongTien = 0;
					try {
		        	    String tongTienString = label_tongTien.getText(); // Lấy giá trị từ label_tongTien
		        	    tongTien = Double.parseDouble(tongTienString); // Chuyển đổi chuỗi thành kiểu double
		        	   
		        	} catch (NumberFormatException ex) {
		        	  
		        	    ex.printStackTrace();
		        	    JOptionPane.showMessageDialog(null, "Lỗi: Không thể chuyển đổi thành số!");
		        	}

		        	if (ngayLap == null) {   	  
		        	    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày lập!");
		        	    return; 
		        	}

		       // 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        	PhieuNhap_DTO phieunhap = new PhieuNhap_DTO(maPhieuNhap, maNhaCungCap, maNhanVien, ngayLap, tongTien);
		        
		            boolean updated = PhieuNhap_BUS.updateDatabase(phieunhap); 
		            if (updated) {
		                JOptionPane.showMessageDialog(null, "Cập nhật cơ sở dữ liệu thành công!");
		                loadTableData();
		                loadTableData_chiTiet();
		            } else {
		                JOptionPane.showMessageDialog(null, "Không thể cập nhật cơ sở dữ liệu!");
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật cơ sở dữ liệu: " + ex.getMessage());
		        }
		    }
		});

		
		JButton button_them_1 = new JButton("Thêm");
		button_them_1.setBackground(new Color(144, 238, 144));
		
		 ImageIcon icon1 = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon1 = new ImageIcon(image1);
	    button_them_1.setIcon(scaledIcon1);
		
		button_them_1.setBounds(839, 468, 115, 30);
		add(button_them_1);
		button_them_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String maPhieuNhap = comboBox_maPhieuNhap.getSelectedItem().toString();
			    String soLuongText = textField_soLuong.getText();
			    String donGiaText = textField_donGia.getText();
			    int soLuong;
			    double donGia; 
			    try {
			        soLuong = Integer.parseInt(soLuongText);
			        donGia = Double.parseDouble(donGiaText);
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Số lượng và đơn giá phải là số!");
			        return;
			    }
			    String maSach = (String) comboBox_maSach.getSelectedItem();
			    Double tongTien = soLuong * donGia;	    
			    // Kiểm tra xem chi tiết phiếu nhập đã tồn tại chưa
			    ChiTietPhieuNhap_BUS chiTietPhieuNhap_BUS = new ChiTietPhieuNhap_BUS();
			    ChiTietPhieuNhap_DTO existingDetail = ChiTietPhieuNhap_BUS.getChiTietPhieuNhap(maPhieuNhap, maSach);
			    if (existingDetail != null) {
			        int newQuantity = existingDetail.getSoLuong() + soLuong;
			        double newTotalPrice = existingDetail.getDonGia() * newQuantity;
			        System.out.println(newQuantity+"");
			        System.out.println(newTotalPrice+"");
			        existingDetail.setSoLuong(newQuantity);
			        existingDetail.setTongTien(newTotalPrice);
			        try {
			            boolean updateSuccess = ChiTietPhieuNhap_BUS.updateChiTietPhieuNhap(existingDetail);
			            if (updateSuccess) {
			                JOptionPane.showMessageDialog(null, "Cập nhật chi tiết phiếu nhập thành công");
			                // Cập nhật lại số lượng sách trong kho
			                int soLuongMoi = Sach_BUS.getSoLuongByMaSach(maSach) + soLuong;
			                Sach_BUS.updateSoLuongByMaSach(maSach, soLuongMoi);
			                // Cập nhật lại tổng tiền của phiếu nhập
			                double tongTienPhieuNhapHienTai = chiTietPhieuNhap_BUS.getTongTienPhieuNhap(maPhieuNhap);
			                boolean updateTotalSuccess = chiTietPhieuNhap_BUS.updateTongTienPhieuNhap(maPhieuNhap, tongTienPhieuNhapHienTai);
			                if (updateTotalSuccess) {
			                    JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền của phiếu nhập thành công");
			                } else {
			                    JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền của phiếu nhập thất bại");
			                }
			                loadTableData_chiTiet();
			            } else {
			                JOptionPane.showMessageDialog(null, "Cập nhật chi tiết phiếu nhập thất bại");
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật chi tiết phiếu nhập");
			        }
			    } else {
			        // Nếu chưa tồn tại, thêm mới vào cơ sở dữ liệu
			        ChiTietPhieuNhap_DTO chiTietPhieuNhapDTO = new ChiTietPhieuNhap_DTO(maPhieuNhap,
			                maSach, soLuong, donGia, tongTien);
			        
			        try {
			            boolean success = chiTietPhieuNhap_BUS.addChiTietPhieuNhap(chiTietPhieuNhapDTO);
			            if (success) {
			                JOptionPane.showMessageDialog(null, "Thêm Chi Tiết phiếu nhập thành công");
			                // Cập nhật lại số lượng sách trong kho
			                int soLuongMoi = Sach_BUS.getSoLuongByMaSach(maSach) + soLuong;
			                Sach_BUS.updateSoLuongByMaSach(maSach, soLuongMoi);
			                // Cập nhật lại tổng tiền của phiếu nhập
			                double tongTienPhieuNhapHienTai = chiTietPhieuNhap_BUS.getTongTienPhieuNhap(maPhieuNhap);
			                boolean updateTotalSuccess = chiTietPhieuNhap_BUS.updateTongTienPhieuNhap(maPhieuNhap, tongTienPhieuNhapHienTai);
			                if (updateTotalSuccess) {
			                    JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền của phiếu nhập thành công");
			                } else {
			                    JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền của phiếu nhập thất bại");
			                }
			                loadTableData_chiTiet();
			            } else {
			                JOptionPane.showMessageDialog(null, "Thêm Chi Tiết phiếu nhập thất bại");
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm Chi Tiết phiếu nhập");
			        }
			    }
			    
			    loadTableData();
			}

		});

		JButton btnXa_1 = new JButton("Xóa");
		btnXa_1.setBackground(Color.RED);
		btnXa_1.setBounds(1092, 469, 115, 28);
		ImageIcon icon15 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image15 = icon15.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon15 = new ImageIcon(image15);
	    btnXa_1.setIcon(scaledIcon15);
		add(btnXa_1);
		btnXa_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
		        String maPhieuNhap = comboBox_maPhieuNhap.getSelectedItem().toString();
                String maSach = comboBox_maSach.getSelectedItem().toString();; // Lấy mã sách từ dữ liệu hiện tại hoặc bảng
		        if (confirm == JOptionPane.YES_OPTION) { // Nếu người dùng chọn YES
		        	boolean success = ChiTietPhieuNhap_BUS.deleteChiTietPhieuNhap(maPhieuNhap, maSach);
		            if (success) {  
		                double newTotalBill = ChiTietPhieuNhap_BUS.calculateTotalBill(maPhieuNhap);
		                boolean tongTienUpdated = PhieuNhap_BUS.updateTotalBill(maPhieuNhap, newTotalBill);

		                if (tongTienUpdated) {
		                    loadTableData();
		                    loadTableData_chiTiet();
		                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tổng tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});

		JButton btnSa_1 = new JButton("Sửa");
		btnSa_1.setBackground(Color.ORANGE);
		btnSa_1.setBounds(969, 469, 115, 28);
		ImageIcon icon12 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image12 = icon12.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon12 = new ImageIcon(image12);
	    btnSa_1.setIcon(scaledIcon12);
		add(btnSa_1);
		btnSa_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table_chiTiet.getSelectedRow();
		        if (selectedRow != -1) {
		            try {            
		                String soLuongStr = textField_soLuong.getText();
		                int soLuong = Integer.parseInt(soLuongStr);
		                String maPhieuNhap = comboBox_maPhieuNhap.getSelectedItem().toString();
		                String maSach = comboBox_maSach.getSelectedItem().toString();; // Lấy mã sách từ dữ liệu hiện tại hoặc bảng
		                ChiTietPhieuNhap_DTO chiTietPhieuNhap = ChiTietPhieuNhap_BUS.getChiTietPhieuNhapById(maPhieuNhap, maSach);
		                
		                if (chiTietPhieuNhap != null) {
		                    ChiTietPhieuNhap_DTO updatedChiTietPhieuNhap = new ChiTietPhieuNhap_DTO(
		                        chiTietPhieuNhap.getMaPhieuNhap(),
		                        chiTietPhieuNhap.getMaSach(),
		                        soLuong,
		                        chiTietPhieuNhap.getDonGia(),
		                        soLuong* chiTietPhieuNhap.getDonGia()
		                    );
		                   
		                    boolean updated = ChiTietPhieuNhap_BUS.updateChiTietPhieuNhap(updatedChiTietPhieuNhap);
		                    double newTotalBill = ChiTietPhieuNhap_BUS.calculateTotalBill(maPhieuNhap);
			            	boolean tongTienUpdated = PhieuNhap_BUS.updateTotalBill(maPhieuNhap, newTotalBill);
		                    if (updated==true && tongTienUpdated==true) {
		                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
		                        loadTableData_chiTiet();
		                        loadTableData();
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
		                    }
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên.");
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
		        }
		    }
		});

		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(584, 418, 189, 23);
		add(textField_soLuong);
		
	

		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSLng.setBounds(584, 385, 94, 23);
		add(lblSLng);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(602, 39, 623, 23);
		add(lblNewLabel);
		
		JLabel lblMPhiuNhp_2 = new JLabel("Mã phiếu nhập");
		lblMPhiuNhp_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMPhiuNhp_2.setBounds(584, 78, 135, 28);
		add(lblMPhiuNhp_2);
		
		JLabel lblMNhnVin_1 = new JLabel("Mã nhân viên");
		lblMNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMNhnVin_1.setBounds(906, 80, 116, 30);
		add(lblMNhnVin_1);
		
		comboBox_maNhanVien_TimKiem = new JComboBox<String>();
		comboBox_maNhanVien_TimKiem.setBounds(1020, 79, 187, 30);
	
		comboBox_maNhanVien_TimKiem.addItem("Tất cả");
		NhanVien_BUS.loadDataIntoComboBox_maNhanVien(comboBox_maNhanVien_TimKiem);
		add(comboBox_maNhanVien_TimKiem);
		
		comboBox_maNhaCungCap_TimKiem = new JComboBox<String>();
		comboBox_maNhaCungCap_TimKiem.setBounds(724, 120, 172, 29);
		
		add(comboBox_maNhaCungCap_TimKiem);
		comboBox_maNhaCungCap_TimKiem.addItem("Tất cả");
		NhaCungCap_BUS.loadDataIntoComboBox_maNhaCungCap(comboBox_maNhaCungCap_TimKiem);
		
		
		
		
		JLabel lblMNhCung_1 = new JLabel("Mã nhà cung cấp");
		lblMNhCung_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMNhCung_1.setBounds(584, 119, 145, 30);
		add(lblMNhCung_1);
		
		JLabel lblNgyLp_1 = new JLabel("Ngày lập");
		lblNgyLp_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgyLp_1.setBounds(906, 118, 117, 28);
		add(lblNgyLp_1);
		
		JDateChooser dateChooser_timKiem = new JDateChooser();
		dateChooser_timKiem.setBounds(1020, 117, 187, 28);
		add(dateChooser_timKiem);
		
		textField_tu = new JTextField();
		textField_tu.setColumns(10);
		textField_tu.setBounds(724, 159, 170, 30);
		add(textField_tu);
		
		JLabel lblTngTin_2 = new JLabel("Tổng tiền");
		lblTngTin_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTngTin_2.setBounds(584, 156, 102, 30);
		add(lblTngTin_2);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(744, 352, 38, 21);
		ImageIcon icon19 = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image19 = icon19.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon19 = new ImageIcon(image19);
	    btnNewButton_2.setIcon(scaledIcon19);
		add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the new frame
                TimKiemSach_GUI employeeListFrame = new TimKiemSach_GUI();
                employeeListFrame.setVisible(true);
                employeeListFrame.loadTableData();
            }
        });
		
		label_tongTien = new JLabel("");
		
		label_tongTien.setBounds(211, 196, 269, 30);
		add(label_tongTien);
		label_tongTien.setOpaque(true); 
		label_tongTien.setBackground(Color.LIGHT_GRAY); 
		
		JLabel label_tongTien_1_1 = new JLabel("Từ");
		label_tongTien_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tongTien_1_1.setBounds(677, 156, 38, 30);
		add(label_tongTien_1_1);
		
		JLabel label_tongTien_1_2 = new JLabel("Đến");
		label_tongTien_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tongTien_1_2.setBounds(929, 156, 38, 30);
		add(label_tongTien_1_2);
		
		textField_den = new JTextField();
		textField_den.setColumns(10);
		textField_den.setBounds(1020, 159, 187, 30);
		add(textField_den);
		
		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					searchAndUpdate();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		    	 
		    }
		    private void searchAndUpdate() throws SQLException {
			    String valueMaSach = comboBox_maSach1.getSelectedItem().toString();
			    
			    if (valueMaSach=="Tất cả") {
			        valueMaSach="";
			    }
			    else {
			        searchAndDisplayFromDatabase(valueMaSach,table_chiTiet);
			    }
			}
		    public static void searchAndDisplayFromDatabase(String maSach,  JTable tabel) throws SQLException {
				
				   ResultSet resultSet = ChiTietPhieuNhap_BUS.searchAndDisplayFromDatabase(maSach);
				   DefaultTableModel model = (DefaultTableModel) table_chiTiet.getModel();
				   model.setRowCount(0);
				   try {
						while (resultSet.next()) {					
							    Object[] rowData = new Object[7];
							    rowData[0] = resultSet.getString("maphieunhap");
							    rowData[1] = resultSet.getString("masach");
							    rowData[2] = resultSet.getString("soluong");
							    rowData[3] = resultSet.getString("dongia");
							    rowData[4] = resultSet.getString("tongtiennhap");					    
							    model.addRow(rowData);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
				}
		});
		
		btnTmKim.setBounds(1079, 199, 128, 28);
		ImageIcon icon13 = new ImageIcon("src\\icon\\loupe.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image13 = icon13.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon13 = new ImageIcon(image13);
	    btnTmKim.setIcon(scaledIcon13);
		add(btnTmKim);
		
		JLabel lblNewLabel_2 = new JLabel("Chi tiết phiếu nhập kho");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(584, 277, 623, 28);
		add(lblNewLabel_2);
		
		
		
		comboBox_maSach = new JComboBox<String>();
		comboBox_maSach.setBounds(584, 352, 150, 23);
		add(comboBox_maSach);
		
		Sach_BUS.loadDataIntoComboBox_maSach(comboBox_maSach);
		  String selectedMaSach = (String) comboBox_maSach.getSelectedItem();
          double gia = Sach_BUS.findGiaByMaSach(selectedMaSach);
          textField_donGia.setText(""+gia);
		
		 comboBox_maSach.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	                String selectedMaSach = (String) comboBox_maSach.getSelectedItem();
	                double gia = Sach_BUS.findGiaByMaSach(selectedMaSach);

	               
	                textField_donGia.setText(""+gia);
	            }
	        });
		jlabel_tongTien1 = new JLabel("");
		jlabel_tongTien1.setBounds(1037, 418, 170, 23);
		add(jlabel_tongTien1);
		jlabel_tongTien1.setOpaque(true); 
		jlabel_tongTien1.setBackground(Color.LIGHT_GRAY); 
		
		comboBox_maPhieuNhap = new JComboBox<String>();
		comboBox_maPhieuNhap.setBounds(811, 352, 185, 23);
		add(comboBox_maPhieuNhap);
		PhieuNhap_BUS.loadDataIntoComboBox_maPhieuNhap(comboBox_maPhieuNhap);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(724, 80, 172, 30);
		add(textField);
		
		lblSPhiuNhp = new JLabel("Số phiếu nhập");
		lblSPhiuNhp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSPhiuNhp.setBounds(23, 712, 135, 31);
		add(lblSPhiuNhp);
		
		label_soPhieuNhap = new JLabel("");
		label_soPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_soPhieuNhap.setBounds(181, 712, 45, 31);
		add(label_soPhieuNhap);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số chi tiết phiếu nhập:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(584, 711, 233, 31);
		add(lblNewLabel_1_1);
		
		JButton btnNewButton_3 = new JButton("Tất cả chi tiết phiếu nhập");
		btnNewButton_3.setBounds(1003, 712, 205, 30);
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       loadTableData_chiTiet();
		    }
		});

		 ImageIcon icon8 = new ImageIcon("src\\icon\\eye.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image8 = icon8.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon8 = new ImageIcon(image8);
	    btnNewButton_3.setIcon(scaledIcon8);
		
		add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(255, 204, 153));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTableData_chiTiet();
			}
		});
		
		label_soChiTietPhieuNhap = new JLabel("");
		label_soChiTietPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_soChiTietPhieuNhap.setBounds(827, 712, 45, 31);
		add(label_soChiTietPhieuNhap);
	
	
		
		
		JButton button_them_2 = new JButton("Xuất");
	
		button_them_2.setBounds(409, 712, 109, 28);
		ImageIcon icon_thongKe11 = new ImageIcon("src\\icon\\excel.png");
		 Image image_thongKe11 = icon_thongKe11.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		 ImageIcon scaledIcon21 = new ImageIcon(image_thongKe11);
		 button_them_2.setIcon(scaledIcon21);
		add(button_them_2);
		button_them_2.addActionListener(new ActionListener() {	        
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int returnVal = jFileChooser.showSaveDialog(contentPane);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();         
                    NhapXuatExcel.exportDataToExcel(table, filePath, "Quản lí Nhập Kho Sheet");
                    JOptionPane.showMessageDialog(contentPane, "Xuất dữ liệu ra file Excel thành công!");
                }
            }
        });
	
		JButton button_xoa_1 = new JButton("Nhập");
	
		button_xoa_1.setBounds(290, 712, 109, 28);
		add(button_xoa_1);
		ImageIcon icon_thongKe111 = new ImageIcon("src\\icon\\excel.png");
		 Image image_thongKe111 = icon_thongKe111.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		 ImageIcon scaledIcon211 = new ImageIcon(image_thongKe111);
		 button_xoa_1.setIcon(scaledIcon211);
		 
		 JLabel lblMNhnVin_1_1 = new JLabel("Mã Sách");
		 lblMNhnVin_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblMNhnVin_1_1.setBounds(584, 196, 116, 30);
		 add(lblMNhnVin_1_1);
		 
		 comboBox_maSach1 = new JComboBox<String>();
		 comboBox_maSach1.setBounds(724, 198, 180, 30);
		 comboBox_maSach1.addItem("Tất cả");
		 Sach_BUS.loadDataIntoComboBox_maSach(comboBox_maSach1);
		 add(comboBox_maSach1);
		 
		 button_xoa_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();   
	                    NhapXuatExcel.readExcelToTable(file, table);
	                }
	            }
	        });
		 
		textField_soLuong.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        calculateTotal();
		    }
		});

		textField_donGia.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        calculateTotal();
		    }
		});
		
		btnTmKim.addActionListener(new ActionListener() {	
		    public void actionPerformed(ActionEvent e) {
		        String maPhieuNhap = textField.getText();
		        String maNhaCungCap = comboBox_maNhaCungCap_TimKiem.getSelectedItem().toString();
		        if ("Tất cả".equals(maNhaCungCap)) {
		            maNhaCungCap = "";
		        }
		        String maNhanVien = comboBox_maNhanVien_TimKiem.getSelectedItem().toString();
		        if ("Tất cả".equals(maNhanVien)) {
		            maNhanVien = "";
		        }
		        java.sql.Date ngayBatDau = null;
		        if (dateChooser_timKiem.getDate() != null) {
		            ngayBatDau = new java.sql.Date(dateChooser_timKiem.getDate().getTime());
		        }
		        String giaTuStr = textField_tu.getText();
		        String giaDenStr = textField_den.getText();

		        // Chuyển đổi giá trị từ String sang Double
		        Double giaTu = null;
		        Double giaDen = null;
		        try {
		            if (!giaTuStr.isEmpty()) {
		                giaTu = Double.parseDouble(giaTuStr);
		            }
		            if (!giaDenStr.isEmpty()) {
		                giaDen = Double.parseDouble(giaDenStr);
		            }
		        } catch (NumberFormatException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ!");
		            return;
		        }
		        searchAndDisplayFromDatabase(maPhieuNhap, maNhaCungCap, maNhanVien, ngayBatDau , giaTu  , giaDen );
		    }

		    private static void searchAndDisplayFromDatabase(String maPhieuNhap, 
					 String maNhaCungCap, String maNhanVien,
					 Date ngayBatDau , Double giaTu , Double giaDen) {
				
				   ResultSet resultSet = null;
				try {
					resultSet = PhieuNhap_BUS.searchAndDisplayFromDatabase(maPhieuNhap, 
							maNhaCungCap, maNhanVien,
							ngayBatDau, giaTu , giaDen);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				   DefaultTableModel model = (DefaultTableModel) table.getModel();
				   model.setRowCount(0);
				   try {
						while (resultSet.next()) {
						    Object[] rowData = new Object[8];
						    rowData[0] = resultSet.getString("maPhieuNhap");
						    rowData[1] = resultSet.getString("maNhaCungCap");
						    rowData[2] = resultSet.getString("maNhanVien");
						    rowData[3] = resultSet.getString("ngayLap");
						    rowData[4] = resultSet.getString("TongTien");
						   
						    model.addRow(rowData);
							  
							}
						} catch (SQLException e) {
							
							e.printStackTrace();
						}   
				}
		});	
		textField_maPhieuNhap.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Kiểm tra nếu phím nhấn là phím Enter            
		        	textField_soLuong.requestFocusInWindow();
		        }
		    }
		});

		textField_soLuong.addKeyListener((KeyListener) new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Kiểm tra nếu phím nhấn là phím mũi tên qua phải
		            comboBox_maSach.requestFocusInWindow(); // Chuyển focus sang combobox
		        }
		    }
		});
		comboBox_maSach.addKeyListener((KeyListener) new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Kiểm tra nếu phím nhấn là phím mũi tên qua phải
		        	comboBox_maPhieuNhap.requestFocusInWindow(); // Chuyển focus sang combobox
		        }
		    }
		});
		comboBox_maPhieuNhap.addKeyListener(new KeyAdapter() {
			   public void keyPressed(KeyEvent e) {
			        if (Character.toUpperCase(e.getKeyChar()) == 'F') {
			            button_them_1.doClick();
			        }
			    }
		});
	


	}

	 public void loadTableData() {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); 
	        ArrayList<PhieuNhap_DTO> phieuNhapList = PhieuNhap_BUS.getAllPhieuNhap();
	        for (PhieuNhap_DTO phieuNhap : phieuNhapList) {
	            model.addRow(new Object[] { 
	            phieuNhap.getMaPhieuNhap(), phieuNhap.getMaNhaCungCap(),
	            phieuNhap.getMaNhanVien() , phieuNhap.getNgayNhap() , phieuNhap.getTongTien()
 });
	            updateRowCountLabel(table,label_soPhieuNhap);
	        }
	 }
	 public void loadTableData_chiTiet() {
	        DefaultTableModel model = (DefaultTableModel) table_chiTiet.getModel();
	        model.setRowCount(0); 
	        ArrayList<ChiTietPhieuNhap_DTO> phieuNhapList = ChiTietPhieuNhap_BUS.getAllChiTietPhieuNhap();

	        for (ChiTietPhieuNhap_DTO chiTietphieuNhap : phieuNhapList) {
	            model.addRow(new Object[] { 
	            		 chiTietphieuNhap.getMaPhieuNhap(),
	            		chiTietphieuNhap.getMaSach() , chiTietphieuNhap.getSoLuong(),
	            		chiTietphieuNhap.getDonGia(), chiTietphieuNhap.getTongTien()
	            });
	        }
	        updateRowCountLabel(table_chiTiet,label_soChiTietPhieuNhap);
	 }
	 private void calculateTotal() {
		    try {
		        int soLuong = Integer.parseInt(textField_soLuong.getText());
		        double donGia = Double.parseDouble(textField_donGia.getText());
		        double tongTien = soLuong * donGia;
		        jlabel_tongTien1.setText(String.valueOf(tongTien));
		    } catch (NumberFormatException ex) {
		       
		        jlabel_tongTien1.setText("0"); // Đặt tổng tiền là 0 nếu dữ liệu không hợp lệ
		    }
		}
	 public static void setSachChoCombobox(String maNhanVien) {
		 if(comboBox_maSach == null)
			 return ; 
		 comboBox_maSach.setSelectedItem(maNhanVien);
	  }
	 public void updateRowCountLabel(JTable table1, JLabel label) {
		    DefaultTableModel model = (DefaultTableModel) table1.getModel();
		    int rowCount = model.getRowCount();
		    label.setText(Integer.toString(rowCount)); // Hiển thị số dòng trên JLabel
		}

}

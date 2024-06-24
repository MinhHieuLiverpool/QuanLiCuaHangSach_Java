package GUI  ;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.toedter.calendar.JDateChooser;

import BUS.CTGG_BUS;
import BUS.ChiTietCTGG_BUS;
import BUS.ChiTietHoaDon_BUS;
import BUS.HoaDon_BUS;
import BUS.KhachHang_BUS;
import BUS.NhanVien_BUS;
import BUS.Sach_BUS;
import DTO.ChiTietCTGG_DTO;
import DTO.Sach_DTO;
import DTO.HoaDon_DTO;
import DTO.ChiTietHoaDon_DTO;
import Database.ConnectionManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class HoaDon_GUI2_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_maHoaDon;
	private JTable table;
	private JTextField textField_soLuong;
	private JDateChooser dateChooser_ngayLap;
	private static JComboBox <String>comboBox_maNhanVien;
	private static JComboBox <String>comboBox_maGiamGia;
	private static Connection connection = ConnectionManager.openConnection();
	private static JComboBox<String> combobox_maSach;
	private static JComboBox<String> comboBox_maKhachHang;
	private JLabel label_tenSachHien;
	private JLabel label_giaTienHien;
	private JLabel label_hinhAnh;
	private JLabel label_thanhTien;
	private JLabel label_tongGiam;
	private JLabel label_tongTien;
	private JComboBox<String> combobox_maHoaDon;
	private JButton button_xacNhan;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoaDon_GUI2_JPanel frame = new HoaDon_GUI2_JPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HoaDon_GUI2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		JLabel label_maHoaDon = new JLabel("Mã Hóa Đơn");
		label_maHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maHoaDon.setBounds(25, 35, 133, 32);
		add(label_maHoaDon);
		
		textField_maHoaDon = new JTextField();
		textField_maHoaDon.setBounds(179, 35, 171, 32);
		add(textField_maHoaDon);
		textField_maHoaDon.setColumns(10);
		textField_maHoaDon.setText(String.valueOf(HoaDon_BUS.getNextInvoiceNumber()));
		PlainDocument doc1 = (PlainDocument) textField_maHoaDon.getDocument();
		doc1.setDocumentFilter(new DocumentFilter() {
		    public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
		        if (string == null) return;
		        if (string.matches("\\d+")) {
		            super.insertString(fb, offset, string, attr);
		        }
		    }
		    public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
		        if (text == null) return;
		        if (text.matches("\\d+")) {
		            super.replace(fb, offset, length, text, attrs);
		        }
		    }
		});
		
		JLabel label_maNhanVien = new JLabel("Mã Nhân Viên");
		label_maNhanVien.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_maNhanVien.setBounds(384, 35, 149, 32);
		add(label_maNhanVien);
		
		JLabel lblMKhchHng = new JLabel("Mã Khách Hàng");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMKhchHng.setBounds(383, 87, 150, 32);
		add(lblMKhchHng);
		
		JLabel lblMGimGi = new JLabel("Mã Giảm Giá");
		lblMGimGi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMGimGi.setBounds(808, 35, 133, 32);
		add(lblMGimGi);
		
		JLabel lblLabelngaylap = new JLabel("Ngày Lập");
		lblLabelngaylap.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLabelngaylap.setBounds(25, 87, 133, 32);
		add(lblLabelngaylap);
		
	
		 Calendar cal = Calendar.getInstance();
        
		dateChooser_ngayLap = new JDateChooser();
		dateChooser_ngayLap.setBounds(179, 87, 171, 32);
        add(dateChooser_ngayLap);
        dateChooser_ngayLap.setDate(cal.getTime());
		
		JButton button_taoHoaDon = new JButton("Tạo Hóa Đơn");
		button_taoHoaDon.setBackground(new Color(153, 255, 153)); 

		button_taoHoaDon.setBounds(878, 92, 229, 27);
		add(button_taoHoaDon);
		add(button_taoHoaDon);
		button_taoHoaDon.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    
		            String mahoaDon = textField_maHoaDon.getText();
		            String maKhachHang = comboBox_maKhachHang.getSelectedItem().toString();
		            String maNhanVien = comboBox_maNhanVien.getSelectedItem().toString();
		            String maCTGG = comboBox_maGiamGia.getSelectedItem().toString();
		            Date ngayLap = dateChooser_ngayLap.getDate();
		            HoaDon_BUS hoaDonBUS = new HoaDon_BUS(connection);
		            HoaDon_DTO hoaDonDTO = new HoaDon_DTO(mahoaDon, maKhachHang, maNhanVien, maCTGG, ngayLap, 0);
		            boolean success = false;
					try {
						success = hoaDonBUS.themHoaDon(hoaDonDTO);
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            if (success) {
		             JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");
		                try {
							HoaDon_BUS.loadDataIntoComboBox_maHoaDon(combobox_maHoaDon);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                combobox_maHoaDon.setSelectedItem(mahoaDon);
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại!");     
		            }         
		    }
		});
		textField_maHoaDon.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		            button_taoHoaDon.doClick();
		        }
		    }
		});
	

		label_hinhAnh = new JLabel("");
		label_hinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		label_hinhAnh.setBounds(64, 141, 254, 308);
		add(label_hinhAnh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 340, 856, 298);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Hóa Đơn" , "Mã sách", "Tên sách", "Số lượng",
				"Đơn giá", "Giảm giá", "Tổng tiền"
			}
		));
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            
		            combobox_maSach.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
		            String maSach = model.getValueAt(selectedRow, 1).toString();
		            Sach_DTO sach = Sach_BUS.laySachTheoMa(maSach);
		    				    label_tenSachHien.setText(sach.getTenSach()); 
		    				    label_giaTienHien.setText(sach.getDonGia()+"");
		    				    String imagePath = sach.getHinhAnh();
		    				    showImageOnLabel1(imagePath) ;
		    			
		            textField_soLuong.setText(model.getValueAt(selectedRow, 3).toString());
		   
		        }
		    }
		});
		
		JLabel label_maSach = new JLabel("Mã Sách");
		label_maSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_maSach.setBounds(11, 516, 147, 26);
		add(label_maSach);
		
		JLabel label_tenSach = new JLabel("Tên Sách");
		label_tenSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tenSach.setBounds(10, 552, 148, 31);
		add(label_tenSach);
		
		JLabel label_soLuong = new JLabel("Số lượng");
		label_soLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_soLuong.setBounds(10, 598, 148, 32);
		add(label_soLuong);
		
		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(178, 606, 172, 31);
		add(textField_soLuong);
		
		PlainDocument doc = (PlainDocument) textField_soLuong.getDocument();
		doc.setDocumentFilter(new DocumentFilter() {
		    public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
		        if (string == null) return;
		        if (string.matches("\\d+")) {
		            super.insertString(fb, offset, string, attr);
		        }
		    }
		    public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
		        if (text == null) return;
		        if (text.matches("\\d+")) {
		            super.replace(fb, offset, length, text, attrs);
		        }
		    }
		});
		JButton button_them = new JButton("Thêm");
		button_them.setBackground(Color.GREEN);
		button_them.setBounds(11, 694, 99, 21);
		ImageIcon icon = new ImageIcon("src\\icon\\icon.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon = new ImageIcon(image);
	    button_them.setIcon(scaledIcon);
		add(button_them);
		
		textField_maHoaDon.addActionListener(new ActionListener() {  
		    public void actionPerformed(ActionEvent e) {
		        textField_soLuong.requestFocusInWindow();
		    }
		});
		textField_soLuong.addKeyListener(new KeyAdapter() {
	
			   public void keyPressed(KeyEvent e) {
			        if (Character.toUpperCase(e.getKeyChar()) == 'D') {
			            button_them.doClick();
			        }
			    }
		});
		textField_soLuong.addKeyListener(new KeyAdapter() {
			   public void keyPressed(KeyEvent e) {
			        if (Character.toUpperCase(e.getKeyChar()) == 'F') {
			            button_xacNhan.doClick();
			        }
			    }
		});
	
		
		JLabel label_giaBan = new JLabel("Giá Bán");
		label_giaBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_giaBan.setBounds(10, 640, 148, 33);
		add(label_giaBan);
		
		
		button_them.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {  	
		            String maSach = combobox_maSach.getSelectedItem().toString();
		            String maHoaDon = combobox_maHoaDon.getSelectedItem().toString();
		            int soLuong = Integer.parseInt(textField_soLuong.getText());
		            Sach_DTO sach = Sach_BUS.laySachTheoMa(maSach);    
		            if (soLuong > sach.getSoLuong()) {
		            	   JOptionPane.showMessageDialog(null, "Số lượng sách trong kho không đủ");
		            }
		            else {
		            double giamGia = 0.0;
		            
		            String maCTGG = HoaDon_BUS.timMaCTGGTuMaHoaDon(maHoaDon);
		            Date NgayLapKiemTra = HoaDon_BUS.layNgayLapTuMaHoaDon(maHoaDon);
		            boolean he = CTGG_BUS.kiemTraNgayLapTrongKhoangCTGG(NgayLapKiemTra,maCTGG);
		            if (he==true)
		            {
		                List<ChiTietCTGG_DTO> danhSachChiTiet = null;
						try {
							danhSachChiTiet = ChiTietCTGG_BUS.layDanhSachChiTietCTGG(maCTGG);
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
		                for (ChiTietCTGG_DTO chiTiet : danhSachChiTiet) {
		                    System.out.println("Mã sách trong danh sách chi tiết của chương trình giảm giá: " + chiTiet.getMaSach());
		                    if (chiTiet.getMaSach().equals(maSach)) {
		                    	  double phanTramGiam = chiTiet.getPhanTramGiamGia();
		                          giamGia = sach.getDonGia() * phanTramGiam / 100.0 ; 
		                        break;
		                }
		                }		                
		            }          
		            double thanhTien = soLuong * (sach.getDonGia() - giamGia);
		            ChiTietHoaDon_DTO chiTietHoaDon = new ChiTietHoaDon_DTO(maHoaDon,
		                    sach.getMaSach(), sach.getTenSach(),
		                    soLuong, sach.getDonGia(), giamGia,
		                    thanhTien);
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            Object[] row = {
		                chiTietHoaDon.getMaHoaDon(),
		                chiTietHoaDon.getMaSach(),
		                chiTietHoaDon.getTenSach(),
		                chiTietHoaDon.getSoLuong(),
		                chiTietHoaDon.getDonGia(),
		                chiTietHoaDon.getGiamGia()*soLuong,
		                chiTietHoaDon.getThanhTien()
		            };
		            model.addRow(row);
		          //  JOptionPane.showMessageDialog(null, "Thêm sách vào bảng thành công");
		            hienThongKe(); // Cập nhật thông tin thống kê sau khi thêm sách vào giỏ hàng
		        } 
		        }catch (NumberFormatException | HeadlessException ex) {    
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số lượng");
		        }
		        
		    }
		});

		
		JButton button_lamMoi = new JButton("Làm Mới");
		button_lamMoi.setBackground(new Color(173, 216, 230));
        ImageIcon icon_lamMoi = new ImageIcon("src\\icon\\load.png"); // Thay đổi đường dẫn tới icon của bạn
        Image image_lamMoi = icon_lamMoi.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
        ImageIcon scaledIcon_lamMoi = new ImageIcon(image_lamMoi);
        button_lamMoi.setIcon(scaledIcon_lamMoi);
		button_lamMoi.setBounds(371, 694, 125, 21);
		add(button_lamMoi);
		button_lamMoi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xóa dữ liệu trong text field "soLuong"
		        textField_soLuong.setText(""); // Giả sử soLuongTextField là JTextField
		        
		        // Đặt giá trị mặc định cho combobox "maSachComboBox"
		        combobox_maSach.setSelectedIndex(0); // Chọn mặc định index 0, có thể thay đổi tùy theo yêu cầu
		    }
		});

		
		label_thanhTien = new JLabel("0 ");
		label_thanhTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_thanhTien.setBounds(496, 637, 83, 36);
		label_thanhTien.setOpaque(true);
		label_thanhTien.setBackground(new Color(220, 220, 220)); // Xám nhạt

		
		add(label_thanhTien);
		
		JButton button_sua = new JButton("Sửa");
		button_sua.setBounds(140, 694, 85, 21);
		button_sua.setBackground(Color.ORANGE);
		 ImageIcon icon1 = new ImageIcon("src\\icon\\edit.png"); // Thay đổi đường dẫn tới icon của bạn
	        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	        ImageIcon scaledIcon1 = new ImageIcon(image1);
	        button_sua.setIcon(scaledIcon1);
		
		add(button_sua);
		button_sua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            try {
		                int soLuong = 0;
		                String maSach1 = combobox_maSach.getSelectedItem().toString();
		                Sach_DTO sachDTO = Sach_BUS.getSachByMaSach(maSach1);
		                String maHoaDon = combobox_maHoaDon.getSelectedItem().toString();
		                String soLuongText = textField_soLuong.getText();

		                if (!soLuongText.isEmpty()) {
		                    soLuong = Integer.parseInt(soLuongText);
		                    if (soLuong > sachDTO.getSoLuong()) {
		                        JOptionPane.showMessageDialog(null, "Số lượng sách trong kho không đủ");
		                        return;
		                    }
		                }

		                double giamGia = 0.0;
		                String maCTGG = HoaDon_BUS.timMaCTGGTuMaHoaDon(maHoaDon);
		                Date NgayLapKiemTra = HoaDon_BUS.layNgayLapTuMaHoaDon(maHoaDon);
		                boolean he = CTGG_BUS.kiemTraNgayLapTrongKhoangCTGG(NgayLapKiemTra, maCTGG);
		                if (he) {
		                    List<ChiTietCTGG_DTO> danhSachChiTiet = null;
		                    try {
		                        danhSachChiTiet = ChiTietCTGG_BUS.layDanhSachChiTietCTGG(maCTGG);
		                    } catch (SQLException e1) {
		                        e1.printStackTrace();
		                    }
		                    for (ChiTietCTGG_DTO chiTiet : danhSachChiTiet) {
		                        if (chiTiet.getMaSach().equals(maSach1)) {
		                            double phanTramGiam = chiTiet.getPhanTramGiamGia();
		                            giamGia = sachDTO.getDonGia() * phanTramGiam / 100.0;
		                            break;
		                        }
		                    }
		                }

		                double thanhTien = soLuong * (sachDTO.getDonGia() - giamGia);
		                ChiTietHoaDon_DTO chiTietHoaDon = new ChiTietHoaDon_DTO(maHoaDon,
		                        maSach1, sachDTO.getTenSach(),
		                        soLuong, sachDTO.getDonGia(), giamGia,
		                        thanhTien);

		                DefaultTableModel model = (DefaultTableModel) table.getModel();

		                // Update the existing row
		                model.setValueAt(chiTietHoaDon.getMaHoaDon(), selectedRow, 0);
		                model.setValueAt(chiTietHoaDon.getMaSach(), selectedRow, 1);
		                model.setValueAt(chiTietHoaDon.getTenSach(), selectedRow, 2);
		                model.setValueAt(chiTietHoaDon.getSoLuong(), selectedRow, 3);
		                model.setValueAt(chiTietHoaDon.getDonGia(), selectedRow, 4);
		                model.setValueAt(chiTietHoaDon.getGiamGia() * soLuong, selectedRow, 5);
		                model.setValueAt(chiTietHoaDon.getThanhTien(), selectedRow, 6);

		                JOptionPane.showMessageDialog(null, "Cập nhật thông tin sách thành công");
		                hienThongKe();

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên cho số lượng!");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!");
		        }
		    }
		});


		
		
		JButton button_xoa = new JButton("Xóa");
		button_xoa.setBounds(265, 694, 85, 21);
		button_xoa.setBackground(Color.RED);
		ImageIcon icon2 = new ImageIcon("src\\icon\\delete.png"); // Thay đổi đường dẫn tới icon của bạn
	    Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon2 = new ImageIcon(image2);
	    button_xoa.setIcon(scaledIcon2);
		add(button_xoa);
		button_xoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();  
		        if (selectedRow != -1) {
		            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
		            hienThongKe();
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
		        }
		    }
		});

		comboBox_maNhanVien = new JComboBox<String>();
		comboBox_maNhanVien.setBounds(543, 43, 153, 32);
		add(comboBox_maNhanVien);
		NhanVien_BUS.loadDataIntoComboBox_maNhanVien(comboBox_maNhanVien);
		
		comboBox_maGiamGia = new JComboBox<String>();
		comboBox_maGiamGia.setBounds(948, 37, 171, 32);
		add(comboBox_maGiamGia);
		//comboBox_maGiamGia.addItem("Không");
		CTGG_BUS.loadDataIntoComboBox_maCTGG(comboBox_maGiamGia);
		
		button_xacNhan = new JButton("Xác Nhận");
		button_xacNhan.setBounds(543, 138, 133, 36);
		ImageIcon icon7 = new ImageIcon("src\\icon\\accept.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon7 = new ImageIcon(image7);
	    button_xacNhan.setIcon(scaledIcon7);
		add(button_xacNhan);
		button_xacNhan.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	   
		        	
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            int rowCount = model.getRowCount();
		            if (rowCount == 0) {
		                JOptionPane.showMessageDialog(null, "Chưa có dữ liệu mua.");
		                return; // Không làm gì nữa nếu không có dữ liệu
		            }
		            double totalCost = 0;
		            double tongGiam = 0;
		            String maHoaDon = combobox_maHoaDon.getSelectedItem().toString();
		           

		            for (int i = 0; i < rowCount; i++) {
		             
		                String maHoaDon1 = (String) model.getValueAt(i, 0);
		                String maSach = (String) model.getValueAt(i, 1);
		                String tenSach = (String) model.getValueAt(i, 2);
		                int soLuong = (int) model.getValueAt(i, 3);
		                double donGia = (double) model.getValueAt(i, 4);
		                double giamGia = (double) model.getValueAt(i, 5);
		                double thanhTien = (double) model.getValueAt(i, 6);
		                ChiTietHoaDon_DTO chiTietHoaDon = new ChiTietHoaDon_DTO( maHoaDon1, maSach, tenSach, soLuong, donGia, giamGia, thanhTien);
		                boolean check =  ChiTietHoaDon_BUS.themChiTietHoaDon(chiTietHoaDon);
		                if (check == true) {
		                    //JOptionPane.showMessageDialog(null, "Thêm sách vào hóa đơn thành công");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Thêm sách vào hóa đơn thất bại");
		                    return ;
		                }
		                int soLuongMua = (int) model.getValueAt(i, 3);
		                int soLuongHienCo = Sach_BUS.getSoLuongByMaSach(maSach);
		                int soLuongConLai = soLuongHienCo - soLuongMua;
		                double giaTien = (double) model.getValueAt(i, 4);
		                double giamGiaSach = (double) model.getValueAt(i, 5);
		                double giaTienSach = soLuong * giaTien;
		                totalCost += giaTienSach;
		                tongGiam += giamGiaSach;

		                Sach_BUS.updateSoLuongByMaSach(maSach, soLuongConLai);
		            }

		            double tongTien = totalCost - tongGiam;
		            HoaDon_BUS.capNhatTongTienHoaDon(maHoaDon, tongTien);

		            //JOptionPane.showMessageDialog(null, "Đã lưu dữ liệu vào cơ sở dữ liệu và cập nhật số lượng sách thành công");
		            model.setRowCount(0);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
		            ex.printStackTrace();
		        }
		    }
		});

		JButton button_huy = new JButton("Hủy");
		button_huy.setBounds(843, 138, 115, 36);
		ImageIcon icon8 = new ImageIcon("src\\icon\\x-button.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon8 = new ImageIcon(image8);
	    button_huy.setIcon(scaledIcon8);
		add(button_huy);
		button_huy.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn hủy không?", "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
		        if (confirm == JOptionPane.YES_OPTION) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.setRowCount(0); // Xóa tất cả các dòng trong bảng
		            JOptionPane.showMessageDialog(null, "Đã hủy thành công");
		            hienThongKe();
		        }
		    }
		});
		
		combobox_maSach = new JComboBox<String>();
		combobox_maSach.setBounds(179, 523, 102, 26);
		add(combobox_maSach);
		Sach_BUS.loadDataIntoComboBox_maSach(combobox_maSach);
		
		combobox_maSach.addItemListener((ItemListener) new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            textField_soLuong.requestFocusInWindow();
		        }
		    }
		});

		comboBox_maKhachHang = new JComboBox<String>();
		comboBox_maKhachHang.setBounds(543, 87, 153, 29);
		add(comboBox_maKhachHang);
		KhachHang_BUS.loadDataIntoComboBox_maKhachHang(comboBox_maKhachHang);
		
		label_tenSachHien = new JLabel("");
		label_tenSachHien.setBounds(176, 564, 174, 26);
		add(label_tenSachHien);
		
		label_tenSachHien.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_tenSachHien.setBackground(new Color(220, 220, 220));
		
		label_giaTienHien = new JLabel("");
		label_giaTienHien.setBounds(177, 653, 173, 27);
		add(label_giaTienHien);
		label_giaTienHien.setOpaque(true); // Đảm bảo JLabel là có khả năng tô màu
		label_giaTienHien.setBackground(new Color(220, 220, 220));
		
		label_tongGiam = new JLabel("0");
		label_tongGiam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_tongGiam.setBounds(835, 637, 76, 36);
		add(label_tongGiam);
		label_tongGiam.setOpaque(true);
		label_tongGiam.setBackground(new Color(220, 220, 220)); // Xám nhạt
		
		label_tongTien = new JLabel("0");
		label_tongTien.setFont(new Font("daoahoma", Font.PLAIN, 16));
		label_tongTien.setBounds(1142, 637, 85, 36);
		add(label_tongTien);
		label_tongTien.setOpaque(true);
		label_tongTien.setBackground(new Color(220, 220, 220)); // Xám nhạt
		
		JButton button_chiTietNhanVien = new JButton("");
		button_chiTietNhanVien.setBounds(718, 46, 29, 27);
		ImageIcon icon4 = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon4 = new ImageIcon(image4);
	    button_chiTietNhanVien.setIcon(scaledIcon4);
		add(button_chiTietNhanVien);
		
		button_chiTietNhanVien.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	             
	                EmployeeListFrame employeeListFrame = new EmployeeListFrame();
	                employeeListFrame.setVisible(true);
	                employeeListFrame.loadTableData();
	            }
	        });
	   
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(291, 523, 59, 26);
		ImageIcon icon_info = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image_info = icon_info.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIconInfo = new ImageIcon(image_info);
	    btnNewButton.setIcon(scaledIconInfo);
		add(btnNewButton);
		
		JButton button_chiTietKhachHang = new JButton("");
		button_chiTietKhachHang.setBounds(718, 87, 29, 29);
		ImageIcon icon5 = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon5= new ImageIcon(image5);
	    button_chiTietKhachHang.setIcon(scaledIcon5);
		add(button_chiTietKhachHang);
		
		JLabel lblNewLabel = new JLabel("Bán Hàng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 0, 1166, 32);
		add(lblNewLabel);
		
		JLabel label_maSach_1 = new JLabel("Mã Hóa Đơn");
		label_maSach_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_maSach_1.setBounds(11, 474, 148, 23);
		add(label_maSach_1);
		
		combobox_maHoaDon = new JComboBox<String>();
		combobox_maHoaDon.setBounds(178, 476, 172, 23);
		add(combobox_maHoaDon);
		HoaDon_BUS.loadDataIntoComboBox_maHoaDon(combobox_maHoaDon);
		
		JButton button_chiTietNhanVien_1 = new JButton("");
		button_chiTietNhanVien_1.setBounds(1129, 35, 29, 32);
		ImageIcon icon6 = new ImageIcon("src\\icon\\info.png"); // Thay đổi đường dẫn tới icon của bạn
	     Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Đặt kích thước cho icon
	    ImageIcon scaledIcon6= new ImageIcon(image6);
	    button_chiTietNhanVien_1.setIcon(scaledIcon6);
	    button_chiTietNhanVien_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the new frame
                TimKiemChuongTrinh_GiamGia employeeListFrame = new TimKiemChuongTrinh_GiamGia();
                employeeListFrame.setVisible(true);
                try {
					TimKiemChuongTrinh_GiamGia.loadTableData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
           
            }
        });
		
		add(button_chiTietNhanVien_1);
		
		JButton button_chitietCTGG = new JButton("...");
		button_chitietCTGG.setBounds(1162, 35, 29, 33);
		add(button_chitietCTGG);
		
		JLabel label_thanhTien_1 = new JLabel("Thành Tiền :");
		label_thanhTien_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_thanhTien_1.setBounds(371, 637, 115, 36);
		add(label_thanhTien_1);
		
		JLabel lblGimGi = new JLabel("Giảm giá :  ");
		lblGimGi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGimGi.setBounds(681, 637, 115, 36);
		add(lblGimGi);
		
		JLabel lblTngTin = new JLabel("Tổng Tiền : ");
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTngTin.setBounds(1002, 637, 123, 36);
		add(lblTngTin);

		button_chitietCTGG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {          
            	String maCTGG = comboBox_maGiamGia.getSelectedItem().toString();
            	BUTTON_ChiTietCTGG employeeListFrame = null;
				try {
					employeeListFrame = new BUTTON_ChiTietCTGG(maCTGG);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                employeeListFrame.setVisible(true);
                try {
					employeeListFrame.loadTableData_Chitiet(maCTGG);
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
            }
        });
		
		button_chiTietKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TimKiemKhachHang_GUI employeeListFrame = new TimKiemKhachHang_GUI();
                employeeListFrame.setVisible(true);
                employeeListFrame.loadTableData();
            }
        });
		
		
		btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {              
	                TimKiemSach_GUI employeeListFrame = new TimKiemSach_GUI();
	                employeeListFrame.setVisible(true);
	                employeeListFrame.loadTableData();
	            }
	        });

		String maSach = combobox_maSach.getSelectedItem().toString();
		 loadinfo(maSach);
		combobox_maSach.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maSach = combobox_maSach.getSelectedItem().toString();
		        loadinfo(maSach);
		    }
		});

	}
	  
	  public void loadinfo(String maSach) {
		  Sach_DTO sach = Sach_BUS.getSachByMaSach(maSach);
		  if (sach!= null) {
			label_tenSachHien.setText(sach.getTenSach());
			label_giaTienHien.setText(String.valueOf(sach.getDonGia()));
			String imagePath =sach.getHinhAnh();
			showImageOnLabel1(imagePath) ;
		  }
		  else 
			  System.out.println("Không bik sao mà bị null rồi");
	       
	    } 
	  private void showImageOnLabel1(String imagePath) {
			 String duongdan= "src\\image\\";
			 String linkdung = duongdan + imagePath;

			 try {
			     ImageIcon icon = new ImageIcon(linkdung);
			     Image image = icon.getImage();

			     int newWidth = 200; // Thay đổi kích thước theo nhu cầu của bạn
			     int newHeight = 300; // Thay đổi kích thước theo nhu cầu của bạn

			     // Thay đổi kích thước của hình ảnh
			     Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			     // Tạo một ImageIcon mới từ hình ảnh đã được thay đổi kích thước
			     ImageIcon scaledIcon = new ImageIcon(scaledImage);

			
			     if (label_hinhAnh!= null) {
			         label_hinhAnh.setIcon(scaledIcon);
			     } else {
			         System.out.println("label_hinhAnh1 is null. Make sure it's properly initialized.");
			     }
			 } catch (Exception e) {
			     e.printStackTrace();
			     System.out.println("Error loading or resizing image: " + e.getMessage());
			 }
		 }
	  private void hienThongKe() {
		    try {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int rowCount = model.getRowCount();
		        double totalCost = 0; // Biến để lưu tổng giá tiền
		        double tongGiam = 0;
		        double tongTien = 0;
		        for (int i = 0; i < rowCount; i++) {
		            // String maSach = (String) model.getValueAt(i, 1);
		            double giaTien = (double) model.getValueAt(i, 4);
		            int soLuong = (int) model.getValueAt(i, 3);
		            double giamGia = (double) model.getValueAt(i, 5);
		            
		            double giaTienSach = soLuong * giaTien;
		            totalCost += giaTienSach;
		            tongGiam += giamGia;
		        }
	            tongTien = totalCost-tongGiam;
	         
	            label_thanhTien.setText("" + totalCost);
	            label_tongGiam.setText("" + tongGiam);
	            label_tongTien.setText("" + tongTien);
	            JOptionPane.showMessageDialog(null, "Đã lưu dữ liệu vào cơ sở dữ liệu và cập nhật số lượng sách thành công");
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu dữ liệu vào cơ sở dữ liệu hoặc cập nhật số lượng sách");
	            ex.printStackTrace();
	        }
	    
	  }
	// Phương thức để đặt mã nhân viên vào combobox
	  public static void setMaNhanVienChoCombobox(String maNhanVien) {
		  if (comboBox_maNhanVien==null) {
			  return;
		  }
		  else {
	      comboBox_maNhanVien.setSelectedItem(maNhanVien);
	  }
	  }
	  public static void setSachChoCombobox(String maNhanVien) {
		  if (combobox_maSach==null) {
			  return;
		  }
		  combobox_maSach.setSelectedItem(maNhanVien);
	  }
	  public static void setKhachHangChoCombobox(String maNhanVien) {
		  if(comboBox_maKhachHang == null) {
			  return ;
		  }
		  comboBox_maKhachHang.setSelectedItem(maNhanVien);
	  }
	  public static void setCTGGChoCombobox(String maNhanVien) {
		  if (comboBox_maGiamGia==null) {
			  return ;
		  }
		  comboBox_maGiamGia.setSelectedItem(maNhanVien);
	  }
}

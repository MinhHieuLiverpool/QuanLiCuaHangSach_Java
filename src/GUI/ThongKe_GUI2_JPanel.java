package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import com.toedter.calendar.JDateChooser;

import BUS.HoaDon_BUS;
import BUS.KhachHang_BUS;
import BUS.NhanVien_BUS;
import BUS.PhieuNhap_BUS;
import BUS.Sach_BUS;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;

public class ThongKe_GUI2_JPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel panel_1;
	private JDateChooser dateChooserStart;
	private JDateChooser dateChooserEnd;
	private JLabel label_soSach;
	private JLabel label_soNhanVien;
	private JLabel label_soKhachHang;
	private JLabel label_tienNhap;
	private JLabel label_tienBan;
	private double numberOfBooks;
	private double numberOfBooks1;
	private JLabel label_doanhThu;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_4_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe_GUI2_JPanel frame = new ThongKe_GUI2_JPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ThongKe_GUI2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(424, 54, 360, 126);
		add(panel_1);
		panel_1.setLayout(null); // Vì bạn đã sử dụng layout null
		panel_1.setBackground(new Color(255, 255, 255)); // Màu cam nhạt
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ TỔNG QUÁT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(453, 14, 277, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nhân viên hoạt động");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(137, 82, 157, 30);
		panel_1.add(lblNewLabel_3_1);
		
		label_soNhanVien = new JLabel("");
		label_soNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_soNhanVien.setBounds(137, 35, 184, 37);
		panel_1.add(label_soNhanVien);
		label_soNhanVien.setOpaque(true); // Kích hoạt tính năng vẽ màu nền
		label_soNhanVien.setBackground(new Color(220, 220, 220));
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		ImageIcon iconã = new ImageIcon("src\\icon\\nhanvienthongkethongke.png");
		Image imageã = iconã.getImage();
		Image scaledImageã = imageã.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconã = new ImageIcon(scaledImageã);
		panel_1.setLayout(null);
		lblNewLabel_1_1.setIcon(scaledIconã);
		lblNewLabel_1_1.setBounds(10, 10, 110, 92);
		panel_1.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 390, 383, 266);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 54, 339, 126);
		panel.setLayout(null); // Vì bạn đã sử dụng layout null
		panel.setBackground(Color.WHITE); // Màu trắng
		add(panel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(0, 13, 116, 102);
		ImageIcon iconãã123 = new ImageIcon("src\\icon\\bookthongkethongke.png");
		Image imageãã123 = iconãã123.getImage();
		Image scaledImageãã123 = imageãã123.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconãã123 = new ImageIcon(scaledImageãã123);
		lblNewLabel_1.setIcon(scaledIconãã123);
		panel.add(lblNewLabel_1);
		
		label_soSach = new JLabel("");
		label_soSach.setBounds(126, 34, 177, 40);
		label_soSach.setOpaque(true);
		label_soSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_soSach.setBackground(new Color(220, 220, 220));
		panel.add(label_soSach);
		
		JLabel lblNewLabel_3 = new JLabel("Số sách còn trong kho");
		lblNewLabel_3.setBounds(126, 84, 177, 25);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_3);
		
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(271, 676, 819, 30);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nhận Xét");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(58, 692, 169, 30);
		add(lblNewLabel_5);
		
		lblNewLabel_4_1 = new JLabel("New label");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(271, 714, 837, 30);
		add(lblNewLabel_4_1);
		
		dateChooserStart = new JDateChooser();
		dateChooserStart.setBounds(498, 350, 150, 30);
		dateChooserEnd = new JDateChooser();
		dateChooserEnd.setBounds(772, 350, 150, 30);
		add(dateChooserStart);
		add(dateChooserEnd);
		
		Calendar today = Calendar.getInstance();
		dateChooserEnd.setDate(today.getTime());
		today.add(Calendar.DATE, -6);

		dateChooserStart.setDate(today.getTime());
		 dateChooserStart.addPropertyChangeListener(new PropertyChangeListener() {
	            public void propertyChange(PropertyChangeEvent evt) {
	                if ("date".equals(evt.getPropertyName())) {
	                  Ve();
	                }
	            }
	        });
		 dateChooserEnd.addPropertyChangeListener(new PropertyChangeListener() {
	            public void propertyChange(PropertyChangeEvent evt) {
	                if ("date".equals(evt.getPropertyName())) {
	                  Ve();
	                }
	            }
	        });
	    

	
		JFreeChart chart = createLineChart();
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(453, 390, 750, 266);
		add(chartPanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(849, 54, 360, 126);
		panel_2.setBackground(Color.WHITE); // Màu trắng
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(10, 10, 93, 92);
		panel_2.add(lblNewLabel_1_2);
		ImageIcon iconãã123ê12 = new ImageIcon("src\\icon\\customerthongke.png");
		Image imageãã123ê12 = iconãã123ê12.getImage();
		Image scaledImageãã123ê12 = imageãã123ê12.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconãã123ê12 = new ImageIcon(scaledImageãã123ê12);
		lblNewLabel_1_2.setIcon(scaledIconãã123ê12);
		
		JLabel lblNewLabel_3_2 = new JLabel("Khách hàng Mua Hàng");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(134, 86, 216, 30);
		panel_2.add(lblNewLabel_3_2);
		
		label_soKhachHang = new JLabel("");
		label_soKhachHang.setOpaque(true);
		label_soKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_soKhachHang.setBackground(new Color(220, 220, 220));
		label_soKhachHang.setBounds(134, 40, 187, 36);
		panel_2.add(label_soKhachHang);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(21, 205, 339, 126);
		panel_3.setBackground(Color.WHITE); // Đặt màu nền là trắng
		add(panel_3);
		panel_3.setLayout(null); // Thiết lập layout cho panel

		
		JLabel lblNewLabel_1_3 = new JLabel("");
		lblNewLabel_1_3.setBounds(10, 19, 109, 92);
		panel_3.add(lblNewLabel_1_3);
		ImageIcon iconãã123ê = new ImageIcon("src\\icon\\money_nhap.png");
		Image imageãã123ê = iconãã123ê.getImage();
		Image scaledImageãã123ê = imageãã123ê.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconãã123ê = new ImageIcon(scaledImageãã123ê);
		lblNewLabel_1_3.setIcon(scaledIconãã123ê);
		
		JLabel lblNewLabel_3_3 = new JLabel("Tiền nhập sách");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_3.setBounds(129, 81, 210, 30);
		panel_3.add(lblNewLabel_3_3);
		
		label_tienNhap = new JLabel("");
		label_tienNhap.setOpaque(true);
		label_tienNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_tienNhap.setBackground(new Color(220, 220, 220));
		label_tienNhap.setBounds(129, 29, 189, 40);
		panel_3.add(label_tienNhap);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(Color.WHITE); 
		panel_2_1.setBounds(424, 205, 360, 126);
		add(panel_2_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("New label");
		lblNewLabel_1_2_1.setBounds(10, 10, 114, 106);
		panel_2_1.add(lblNewLabel_1_2_1);
		ImageIcon iconãã123ê1 = new ImageIcon("src\\icon\\money_ban.png");
		Image imageãã123ê1 = iconãã123ê1.getImage();
		Image scaledImageãã123ê1 = imageãã123ê1.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconãã123ê1 = new ImageIcon(scaledImageãã123ê1);
		lblNewLabel_1_2_1.setIcon(scaledIconãã123ê1);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Tiền bán sách");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_1.setBounds(134, 86, 216, 30);
		panel_2_1.add(lblNewLabel_3_2_1);
		
		label_tienBan = new JLabel("");
		label_tienBan.setOpaque(true);
		label_tienBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_tienBan.setBackground(new Color(220, 220, 220));
		label_tienBan.setBounds(134, 33, 191, 37);
		panel_2_1.add(label_tienBan);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(Color.WHITE); 
		panel_2_2.setBounds(849, 202, 360, 126);
		add(panel_2_2);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("New label");
		lblNewLabel_1_2_2.setBounds(10, 10, 114, 92);
		panel_2_2.add(lblNewLabel_1_2_2);
		ImageIcon iconãã123ê11 = new ImageIcon("src\\icon\\doanhthu.png");
		Image imageãã123ê11 = iconãã123ê11.getImage();
		Image scaledImageãã123ê11 = imageãã123ê11.getScaledInstance(110, 92, Image.SCALE_SMOOTH);
		ImageIcon scaledIconãã123ê11 = new ImageIcon(scaledImageãã123ê11);
		lblNewLabel_1_2_2.setIcon(scaledIconãã123ê11);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Tổng Doanh Thu");
		lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_2_2.setBounds(134, 86, 216, 30);
		panel_2_2.add(lblNewLabel_3_2_2);
		
		label_doanhThu = new JLabel("");
		label_doanhThu.setOpaque(true);
		label_doanhThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_doanhThu.setBackground(new Color(220, 220, 220));
		label_doanhThu.setBounds(135, 38, 188, 38);
		panel_2_2.add(label_doanhThu);
		
		JLabel lblNewLabel_2 = new JLabel("Doanh Thu Từ Ngày");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(282, 350, 177, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Đến Ngày");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(674, 350, 88, 30);
		add(lblNewLabel_2_1);

		// Cập nhật giao diện
		revalidate();
		repaint();
		
		updateBookCount();
		updateNhanVien();
		updateKhachHang();
		TienNhap()	;
		TienBan();
		double doanhthu = numberOfBooks - numberOfBooks1;
		label_doanhThu.setText(doanhthu+"");
		
		JButton btnNewButton = new JButton("Tổng Quát");
		btnNewButton.setBounds(21, 0, 109, 30);
		add(btnNewButton);
		
		JButton btnTngQut = new JButton("Chi Tiết");
		btnTngQut.setBounds(178, 0, 125, 30);
		add(btnTngQut);
		btnTngQut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {      
		        HomePage.panelCustomer.removeAll();
				ThongKeSach2_JPanel thongKePanel = null;
				try {
					thongKePanel = new ThongKeSach2_JPanel();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				HomePage.panelCustomer.add(thongKePanel);
		        // Yêu cầu panel cập nhật lại giao diện
		        HomePage.panelCustomer.revalidate();
		        HomePage.panelCustomer.repaint();
		    }
		});
		
}
	public DefaultTableModel thongKeBang() throws SQLException {
		  java.util.Date startDate = dateChooserStart.getDate();
		    java.util.Date endDate = dateChooserEnd.getDate();
		    DefaultTableModel model = new DefaultTableModel(new Object[]{"Ngày", "Tiền Nhập", "Tiền Bán", "Doanh Thu"}, 0);
		    double totalInputSum = 0;
		    double totalSalesSum = 0;
		    double totalRevenueSum = 0;
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(startDate);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    while (!calendar.getTime().after(endDate)) {
		    java.util.Date currentDate = calendar.getTime();
		    double totalInput = PhieuNhap_BUS.getTotalInputForDay(currentDate);
		    double totalSales = HoaDon_BUS.getTotalSalesForDay(currentDate);
		    double totalRevenue = totalSales - totalInput;   
		    model.addRow(new Object[]{dateFormat.format(currentDate), totalInput, totalSales, totalRevenue});
		    totalInputSum += totalInput;
		    totalSalesSum += totalSales;
		    totalRevenueSum += totalRevenue;
		    calendar.add(Calendar.DATE, 1);
		}
		model.addRow(new Object[]{"Tổng", totalInputSum, totalSalesSum, totalRevenueSum});
		table.setModel(model);
		return model;
	}
	

	private JFreeChart createLineChart() throws SQLException {
	DefaultTableModel model = thongKeBang();
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String date = (String) model.getValueAt(i, 0);
	        double totalInput = (double) model.getValueAt(i, 1);
	        double totalSales = (double) model.getValueAt(i, 2);
	        double totalRevenue = (double) model.getValueAt(i, 3);
	        dataset.addValue(totalInput, "Tiền Nhập", date);
	        dataset.addValue(totalSales, "Tiền Bán", date);
	        dataset.addValue(totalRevenue, "Doanh Thu", date);
	    }
	    double threshold = 3000; // Ngưỡng thay đổi
	    String trend = ""; // Biến lưu trữ xu hướng
	    String significantChange = ""; // Biến lưu trữ thông tin về sự thay đổi đáng chú ý
	    double initialRevenue = (double) model.getValueAt(0, 3); // Doanh thu ban đầu
	    double finalRevenue = (double) model.getValueAt(model.getRowCount() - 1, 3); // Doanh thu cuối cùng
	    double revenueChange = finalRevenue - initialRevenue;
	    if (revenueChange == 0 ) {
	    	trend = "Không thay đổi";
	    }
	    // Xác định xu hướng
	    else if (revenueChange > 0) {
	        trend = "Tăng";
	        if (revenueChange > threshold) {
	            trend += " mạnh";
	        }
	    } else {
	        trend = "Giảm";
	        if (Math.abs(revenueChange) > threshold) {
	            trend += " mạnh";
	        }
	    }
	    significantChange = "Từ ngày " + model.getValueAt(0, 0) + " đến ngày " + model.getValueAt(model.getRowCount() - 1, 0) 
	                        + ", ";
	    if (revenueChange > 0) {
	        significantChange += "doanh thu tăng ";
	    } else {
	        significantChange += "doanh thu giảm ";
	    }
	    significantChange += "đã đạt " + Math.abs(revenueChange) + " đơn vị";
	    String startDate1 = (String) model.getValueAt(0, 0); // Ngày đầu tiên
	    String endDate1 = (String) model.getValueAt(model.getRowCount() - 1, 0); // Ngày cuối cùng

	    lblNewLabel_4.setText("Trend của doanh thu từ ngày " + startDate1 + " đến ngày " + endDate1 + ": " + trend);

	    lblNewLabel_4_1.setText("Sự thay đổi: " + significantChange);

	    return ChartFactory.createLineChart("Biểu Đồ", "Ngày", "Số Tiền", dataset);
	}

	 private void updateBookCount() throws SQLException {
	        int numberOfBooks = Sach_BUS.countTotalQuantityOfBooks();
	        label_soSach.setText(numberOfBooks+"");
	}
	 private void updateNhanVien() throws SQLException {	               
	        int numberOfBooks = NhanVien_BUS.countTotalEmployees();
	        label_soNhanVien.setText(numberOfBooks+"");
	}
	 private void updateKhachHang() throws SQLException {	       	 	
	        int numberOfBooks = KhachHang_BUS.countTotalKhachHang();
	        label_soKhachHang.setText(numberOfBooks+"");
	}
	 private void TienNhap() throws SQLException {	       
	        numberOfBooks1 = PhieuNhap_BUS.calculateTotalInput();
	        label_tienNhap.setText(numberOfBooks1+"");
	}
	 private void TienBan() throws SQLException {	       
	        numberOfBooks = HoaDon_BUS.calculateTotalInput();
	        label_tienBan.setText(numberOfBooks+"");
	}
	 public void Ve() {
		  java.util.Date startDate = dateChooserStart.getDate();
          java.util.Date endDate = dateChooserEnd.getDate();
          if (endDate != null && startDate != null && endDate.before(startDate)) {
              JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
              dateChooserEnd.setDate(startDate);
          } else {
              try {
                  Component[] components = getComponents();
                  for (Component component : components) {
                      if (component instanceof ChartPanel) {
                          remove(component);
                      }
                  }
                  JFreeChart chart = createLineChart();
                  ChartPanel chartPanel = new ChartPanel(chart);
                  chartPanel.setBounds(453, 390, 750, 266);
                  add(chartPanel);
                  revalidate();
                  repaint();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
	 }
	 
	 
	 
	 public void thongke(String year) {
		 double []tongtienhoadon ;
		 double []tongtienphieuxuat;
		 String sql = "Select Quarter(ngaylap) as quy , SUm(tongtien) as tongtienhoadon from hoadon "
		 		+ "where year(ngaylap) = ? Group by Quarter(ngaylap) ";
		 PreparedStatement stmt = connection.PreparedStatement(sql);
		 stmt.setString(1, year);
		 ResultSet rs = stmt.executeQuery();
		 while(rs.next()) {
			 int quy = rs.getInt("quy");
			 tongtienhoadon[quy-1]= rs.getDouble("tongtienhoadon");
		 }
		 String sql1 = "Select Quarter(ngaylap) as quy , SUm(tongtien) as tongtienphieunhap from phieunhap "
			 		+ "where year(ngaylap) = ? Group by Quarter(ngaylap) ";
			 PreparedStatement stmt1 = connection.PreparedStatement(sql1);
			 stmt.setString(1, year);
			 ResultSet rs1 = stmt1.executeQuery();
			 while(rs1.next()) {
				 int quy = rs1.getInt("quy");
				 tongtienhoadon[quy-1]= rs1.getDouble("tongtienphieunhap");
			 }
				 
			 Object[][] data = new Object[4][4];
			 for (int i = 0 ; i < 4 ; i++) {
				 data[i][0]= "Quý"+(i+1);
				 data[i][1]= tongtienhoadon[i];
				 data[i][2]= tongtienphieuxuat[i];
				 data[i][3]=  tongtienhoadon[i]-tongtienphieuxuat[i];
				 
			 }
			 String colum[]= {"Quý", "Tổng tiền hóa Đơn", "Tổng tiền phiếu nhập ", "Doanh thu"};
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

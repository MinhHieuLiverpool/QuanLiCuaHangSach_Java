package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import BUS.ChiTietHoaDon_BUS;
import BUS.ChiTietPhieuNhap_BUS;
import BUS.KhachHang_BUS;
import BUS.NhaCungCap_BUS;
import BUS.NhanVien_BUS;
import BUS.Sach_BUS;
import BUS.TacGia_BUS;
import BUS.TheLoai_BUS;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ThongKeSach2_JPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeSach2_JPanel frame = new ThongKeSach2_JPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ThongKeSach2_JPanel() throws SQLException {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Thống Kê Chi Tiết");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(505, 10, 289, 54);
		add(lblNewLabel);
		
		JFreeChart chart = createBarChart();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 66, 597, 314);
		add(panel);
		panel.setLayout(null);
		 Color backgroundColor = new Color(255, 204, 153); // Màu cam nhạt
	        panel.setBackground(backgroundColor);
		
		ChartPanel chartPanel_1 = new ChartPanel(chart);
		chartPanel_1.setBounds(64, 58, 451, 215);
		panel.add(chartPanel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Thống Kê Chi Tiết Sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 7, 403, 28);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		//Color backgroundColor1 = new Color(255, 204, 153); // Màu cam nhạt
		panel_1.setBackground(new Color(77, 179, 176));
		panel_1.setBounds(675, 66, 607, 314);
		add(panel_1);
		panel_1.setLayout(null);
		
		 Map<String, Double> salaryData = null;
	        try {
	            salaryData = NhanVien_BUS.retrieveSalaryData();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        JFreeChart pieChart = createPieChart(salaryData);
			ChartPanel chartPanel = new ChartPanel(pieChart);
	
		    chartPanel.setPreferredSize(new Dimension(500, 300)); // Thiết lập kích thước của chartPanel
		    chartPanel.setBounds(97, 48, 500, 259); // Thiết lập vị trí và kích thước của chartPanel trong panel_1

		    // Thêm chartPanel vào panel_1
		    panel_1.add(chartPanel);
		    
		    JLabel lblNewLabel_1_1 = new JLabel("Thống Kê Chi Tiết Nhân Viên");
		    lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_1_1.setBounds(10, 10, 403, 28);
		    panel_1.add(lblNewLabel_1_1);
		    
		    
			JButton btnNewButton = new JButton("Tổng Quát");
			btnNewButton.setBounds(21, 0, 109, 30);
			add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {      
			        HomePage.panelCustomer.removeAll();
			        
					ThongKe_GUI2_JPanel thongKePanel = null;
					try {
						thongKePanel = new ThongKe_GUI2_JPanel();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					HomePage.panelCustomer.add(thongKePanel);
			      
			        HomePage.panelCustomer.revalidate();
			        HomePage.panelCustomer.repaint();
			    }
			});
		    
		    
		    
		    
		    JButton btnNewButton_1 = new JButton("Chi Tiết");
		    btnNewButton_1.setBounds(178, 0, 125, 30);
		    add(btnNewButton_1);
		    
		    
		    Map<String, Double> tongchikhachahng = null;
	        try {
	        	tongchikhachahng = KhachHang_BUS.TongChiKhachHang();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	       
	        JPanel panel_khachHang = new JPanel();
	        panel_khachHang.setBackground(new Color(128, 128, 0));
		    panel_khachHang.setLayout(null);
		    panel_khachHang.setBounds(0, 412, 607, 318);
		    add(panel_khachHang);
		    
	        JFreeChart pieChart1 = createPieChartKhachHang(tongchikhachahng);
			ChartPanel chartPanel_khachHang = new ChartPanel(pieChart1);
		    chartPanel_khachHang.setPreferredSize(new Dimension(500, 300));
		    chartPanel_khachHang.setBounds(97, 48, 500, 259);
		    panel_khachHang.add(chartPanel_khachHang);
		    
		    chartPanel_khachHang.setPreferredSize(new Dimension(500, 300));
		    chartPanel_khachHang.setBounds(97, 48, 500, 259);
		    panel_khachHang.add(chartPanel_khachHang);
		    
		    JLabel lblNewLabel_1_1_1 = new JLabel("Thống Kê Chi Tiết Khách Hàng");
		    lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_1_1_1.setBounds(10, 10, 403, 28);
		    panel_khachHang.add(lblNewLabel_1_1_1);
		    
		    JPanel panel_2 = new JPanel();
		    panel_2.setBounds(675, 406, 607, 324);
		    add(panel_2);
		    panel_2.setLayout(null);
		    Color backgroundColor11 = new Color(255, 204, 153); // Màu cam nhạt
		    panel_2.setBackground(backgroundColor11);
		    
		    
		    JLabel lblNewLabel_1_1_1_1 = new JLabel("Thống Kê Một Số Thông Tin Của Cửa Hàng Sách");
		    lblNewLabel_1_1_1_1.setBounds(10, 20, 452, 28);
		    lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    panel_2.add(lblNewLabel_1_1_1_1);
		    
		    JLabel lblNewLabel_2 = new JLabel("Mã Sách Bán Chạy Nhất");
		    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2.setBounds(62, 75, 198, 34);
		    panel_2.add(lblNewLabel_2);
		    
		    JLabel lblNewLabel_3 = new JLabel("");
		    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		    lblNewLabel_3.setBounds(267, 75, 57, 39);
		    panel_2.add(lblNewLabel_3);
		    lblNewLabel_3.setOpaque(true);
		    lblNewLabel_3.setBackground(new Color(220, 220, 220)); 
		    lblNewLabel_3.setText(Sach_BUS.getBestSellingBookId());
		  	   
		    JLabel lblNewLabel_2_1 = new JLabel("Mã Nhân Viên Bán Được Nhiều Doanh Thu Nhất:");
		    lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2_1.setBounds(62, 129, 426, 34);
		    panel_2.add(lblNewLabel_2_1);
		    
		    JLabel lblNewLabel_2_1_1 = new JLabel("Số Lượng Nhà Cung Cấp");
		    lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2_1_1.setBounds(62, 178, 400, 34);
		    panel_2.add(lblNewLabel_2_1_1);
		    
		    JLabel lblNewLabel_3_1 = new JLabel("");
		    lblNewLabel_3_1.setOpaque(true);
		    lblNewLabel_3_1.setBackground(new Color(220, 220, 220));
		    lblNewLabel_3_1.setBounds(482, 124, 96, 39);
		    panel_2.add(lblNewLabel_3_1);
		   
		    lblNewLabel_3_1.setText(NhanVien_BUS.getMaxTotalSalesByEmployee());
		    
		    
		    JLabel label_soNCC = new JLabel("");
		    label_soNCC.setOpaque(true);
		    label_soNCC.setBackground(new Color(220, 220, 220));
		    label_soNCC.setBounds(482, 173, 96, 39);
		    panel_2.add(label_soNCC);
		    label_soNCC.setText(NhaCungCap_BUS.countSuppliers()+"");
		    
		    JLabel lblNewLabel_3_3 = new JLabel("0001");
		    lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		    lblNewLabel_3_3.setOpaque(true);
		    lblNewLabel_3_3.setBackground(new Color(220, 220, 220));
		    lblNewLabel_3_3.setBounds(482, 75, 96, 39);
		    panel_2.add(lblNewLabel_3_3);
		    lblNewLabel_3_3.setText(Sach_BUS.getBestSellingQuantity()+"");
		    
		    JLabel lblNewLabel_2_2 = new JLabel("Số Lượng Bán");
		    lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2_2.setBounds(334, 75, 138, 34);
		    panel_2.add(lblNewLabel_2_2);
		    
		    JLabel lblNewLabel_2_1_1_1 = new JLabel("Số Lượng Tác Giả Sách");
		    lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2_1_1_1.setBounds(62, 233, 400, 34);
		    panel_2.add(lblNewLabel_2_1_1_1);
		    
		    JLabel label_soTacGia = new JLabel("");
		    label_soTacGia.setOpaque(true);
		    label_soTacGia.setBackground(new Color(220, 220, 220));
		    label_soTacGia.setBounds(482, 228, 96, 39);
		    panel_2.add(label_soTacGia);
		    label_soTacGia.setText(TacGia_BUS.demtacgia()+"");
		    
		    JLabel lblNewLabel_2_1_1_2 = new JLabel("Số Lượng Thể Loại Sách");
		    lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		    lblNewLabel_2_1_1_2.setBounds(62, 280, 400, 34);
		    panel_2.add(lblNewLabel_2_1_1_2);
		    
		    JLabel label_soTheLoai = new JLabel("");
		    label_soTheLoai.setOpaque(true);
		    label_soTheLoai.setBackground(new Color(220, 220, 220));
		    label_soTheLoai.setBounds(482, 275, 96, 39);
		    panel_2.add(label_soTheLoai);
		    label_soTheLoai.setText(TheLoai_BUS.demtheloai()+"");
		}	
	   public static JFreeChart createPieChartKhachHang(Map<String, Double> data) {
	       
	        DefaultPieDataset dataset = new DefaultPieDataset();
	        for (Map.Entry<String, Double> entry : data.entrySet()) {
	            String employeeId = entry.getKey();
	            double salary = entry.getValue();
	            String label = String.format("%s - %.2f", employeeId, salary); // Kết hợp mã nhân viên và lương
	            dataset.setValue(label, salary);
	        }

	        // Tạo biểu đồ tròn từ dataset và trả về
	        JFreeChart chart = ChartFactory.createPieChart(
	                "Biểu Đồ Tổng Chi Khách Hàng",  // Tiêu đề biểu đồ
	                dataset,  // Dataset chứa dữ liệu
	                true,     // Có hiển thị hướng giải thích không
	                true,     // Có hiển thị nhãn dữ liệu không
	                false);   // Có hiển thị URL không (chỉ dành cho biểu đồ web)

	        return chart;
	    }

	   public static JFreeChart createPieChart(Map<String, Double> data) {
	        // Tạo một dataset cho biểu đồ tròn
	        DefaultPieDataset dataset = new DefaultPieDataset();

	        // Thêm dữ liệu lương và mã nhân viên từ map vào dataset
	        for (Map.Entry<String, Double> entry : data.entrySet()) {
	            String employeeId = entry.getKey();
	            double salary = entry.getValue();
	            String label = String.format("%s - %.2f", employeeId, salary); // Kết hợp mã nhân viên và lương
	            dataset.setValue(label, salary);
	        }

	        // Tạo biểu đồ tròn từ dataset và trả về
	        JFreeChart chart = ChartFactory.createPieChart(
	                "Biểu Đồ Lương Nhân Viên",  // Tiêu đề biểu đồ
	                dataset,  // Dataset chứa dữ liệu
	                true,     // Có hiển thị hướng giải thích không
	                true,     // Có hiển thị nhãn dữ liệu không
	                false);   // Có hiển thị URL không (chỉ dành cho biểu đồ web)

	        return chart;
	    }

	private JFreeChart createBarChart() throws SQLException {
	    // Tạo mô hình dữ liệu mới
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    // Lấy tổng số lượng sách đã bán và tổng số lượng sách trong kho cho toàn bộ thời gian
	    int totalSoldQuantity = ChiTietHoaDon_BUS.getTotalSoldQuantity();
	    int totalAvailableQuantity = ChiTietPhieuNhap_BUS.getTotalSoldQuantity();
	    dataset.addValue(totalSoldQuantity, "Số lượng sách đã bán", "Tổng");
	    dataset.addValue(totalAvailableQuantity, "Số lượng sách đã nhập", "Tổng");

	    // Tạo biểu đồ cột từ dataset
	    JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ Số Lượng Sách Đã Bán/Đã Nhập", "Thời Gian", "Số Lượng Sách", dataset, PlotOrientation.VERTICAL, true, true, false);

	    // Thiết lập màu sắc cho các cột
	    CategoryPlot plot = chart.getCategoryPlot();
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setSeriesPaint(0, new Color(0, 0, 255).brighter()); // Màu xanh dương nhạt cho cột "Số lượng sách đã bán"
// Màu xanh nhạt cho cột "Số lượng sách đã bán"
	    renderer.setSeriesPaint(1, new Color(255, 102, 102)); // Màu đỏ nhạt cho cột "Số lượng sách trong kho"

	    return chart;
	}
}

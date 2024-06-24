package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JPanel panelButtons;
    static JPanel panelCustomer;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage frame = new HomePage();
                	frame.setSize(1500, 850);
                    frame.setVisible(true);
                    frame.panelCustomer.add(new TrangChu2_Panel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HomePage() throws IOException {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    	contentPane = new JPanel();

        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelButtons = new JPanel();
        panelButtons.setBounds(5, 43, 193, 850);
        panelButtons.setBackground(Color.LIGHT_GRAY);
        panelButtons.setPreferredSize(new Dimension(350, 0));
        contentPane.add(panelButtons);

        panelCustomer = new JPanel();
        panelCustomer.setBounds(198, 35, 1338, 858);
        panelCustomer.setBackground(Color.WHITE);
        contentPane.add(panelCustomer);
        panelButtons.setLayout(null);

        JButton banHang = new JButton("Bán Hàng");
        banHang.setHorizontalAlignment(SwingConstants.LEFT);
        banHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        banHang.setBounds(0, 248, 193, 50);
        Image img = ImageIO.read(new File("src\\icon\\giohang.png"));
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        // Đặt hình ảnh lên JButton
        banHang.setIcon(scaledIcon);
       
        
        panelButtons.add(banHang);
        
        JButton quanLiSach = new JButton("Quản Lí Sách");
        quanLiSach.setHorizontalAlignment(SwingConstants.LEFT);
        quanLiSach.setFont(new Font("Tahoma", Font.BOLD, 12));
        quanLiSach.setBounds(0, 308, 193, 50);
        Image imgã = ImageIO.read(new File("src\\icon\\bookbook.png"));
        Image scaledImgã = imgã.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã = new ImageIcon(scaledImgã);
        quanLiSach.setIcon(scaledIconã);
        
        panelButtons.add(quanLiSach);
        
        JButton btnCustomer = new JButton("Quản Lí Khách Hàng");
        btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
        btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCustomer.setBounds(0, 428, 193, 50);
        
        Image imgã11 = ImageIO.read(new File("src\\icon\\customers.png"));
        Image scaledImgã11 = imgã11.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã11 = new ImageIcon(scaledImgã11);
        btnCustomer.setIcon(scaledIconã11);
        
        panelButtons.add(btnCustomer);
        
        
        JButton btnStatistics = new JButton("Thống kê");
        btnStatistics.setHorizontalAlignment(SwingConstants.LEFT);
        btnStatistics.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStatistics.setBounds(0, 676, 193, 50);
        
        panelButtons.add(btnStatistics);
        Image imgã1 = ImageIO.read(new File("src\\icon\\charticon.png"));
        Image scaledImgã1 = imgã1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã1 = new ImageIcon(scaledImgã1);
        btnStatistics.setIcon(scaledIconã1);
        
     
  
        ImageIcon imageIcon = new ImageIcon("src\\image\\sachspeare.png");
        Image image = imageIcon.getImage();

        Image scaledImage = image.getScaledInstance(149, 160, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh thành 100x100

        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        
        JLabel lblImage = new JLabel(scaledImageIcon);
        lblImage.setBounds(0, 0, 198, 208); // Đặt vị trí và kích thước cho JLabel
        panelButtons.add(lblImage); // Thêm JLabel vào panelButtons
        
        JButton btnChngTrnhGim = new JButton("Giảm Giá");
        btnChngTrnhGim.setHorizontalAlignment(SwingConstants.LEFT);
        btnChngTrnhGim.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnChngTrnhGim.setBounds(0, 615, 193, 51);
        Image imgã111 = ImageIO.read(new File("src\\icon\\sale.png"));
        Image scaledImgã111 = imgã111.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã111 = new ImageIcon(scaledImgã111);
        btnChngTrnhGim.setIcon(scaledIconã111);
        panelButtons.add(btnChngTrnhGim);
        
        
        JButton btnQunLNhn = new JButton("Quản Lí Nhân Sự");
        btnQunLNhn.setHorizontalAlignment(SwingConstants.LEFT);
        btnQunLNhn.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnQunLNhn.setBounds(0, 368, 193, 50);
        Image imgã112 = ImageIO.read(new File("src\\icon\\humans.png"));
        Image scaledImgã112 = imgã112.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã112 = new ImageIcon(scaledImgã112);
        btnQunLNhn.setIcon(scaledIconã112);
        panelButtons.add(btnQunLNhn);
        
        JButton btnNhpKho = new JButton("Quản Lí Nhập Hàng");
        btnNhpKho.setHorizontalAlignment(SwingConstants.LEFT);
        btnNhpKho.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNhpKho.setBounds(0, 555, 193, 50);
        Image imgã1122 = ImageIO.read(new File("src\\icon\\nhapkho.png"));
        Image scaledImgã1122 = imgã1122.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã1122 = new ImageIcon(scaledImgã1122);
        btnNhpKho.setIcon(scaledIconã1122);
        panelButtons.add(btnNhpKho);
        
        JButton btnChngTrnhGim_1 = new JButton("Quản Lí Hóa Đơn");
        btnChngTrnhGim_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnChngTrnhGim_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnChngTrnhGim_1.setBounds(0, 488, 193, 50);
        
        Image imgã11221 = ImageIO.read(new File("src\\icon\\money.png"));
        Image scaledImgã11221 = imgã11221.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã11221 = new ImageIcon(scaledImgã11221);
        btnChngTrnhGim_1.setIcon(scaledIconã11221);
  
        panelButtons.add(btnChngTrnhGim_1);
        
        JButton btnTrangCh = new JButton("Trang Chủ");
        btnTrangCh.setHorizontalAlignment(SwingConstants.LEFT);
        btnTrangCh.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnTrangCh.setBounds(0, 195, 193, 43);
        panelButtons.add(btnTrangCh);
        btnTrangCh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      
                panelCustomer.removeAll();
                TrangChu2_Panel nhapKhoPanel = new TrangChu2_Panel();
             
                    panelCustomer.add(nhapKhoPanel);
                revalidate();
                repaint();
            }
        });
        
        Image imgã11221ã = ImageIO.read(new File("src\\icon\\home.png"));
        Image scaledImgã11221ã = imgã11221ã.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIconã11221ã = new ImageIcon(scaledImgã11221ã);
        btnTrangCh.setIcon(scaledIconã11221ã);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 1500, 23);
        panel.setBackground(Color.DARK_GRAY); // Đặt màu nền tối
        contentPane.add(panel);

        // Tạo một JLabel chứa văn bản "Cửa hàng bán sách"
        JLabel label = new JLabel("Cửa hàng bán sách");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setForeground(Color.WHITE); // Đặt màu văn bản trắng
        panel.add(label);
        
        btnStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      
                panelCustomer.removeAll();
                try {
                   ThongKe_GUI2_JPanel nhapKhoPanel = new ThongKe_GUI2_JPanel();
             
                    panelCustomer.add(nhapKhoPanel);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                revalidate();
                repaint();
            }
        });
        btnNhpKho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      
                panelCustomer.removeAll();
                try {
                    NhapKho_GUI2_JPanel nhapKhoPanel = new NhapKho_GUI2_JPanel();
                    nhapKhoPanel.loadTableData(); 
                    nhapKhoPanel.loadTableData_chiTiet();
                    panelCustomer.add(nhapKhoPanel);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                revalidate();
                repaint();
            }
        });
        btnChngTrnhGim_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      
                panelCustomer.removeAll();
						try {
							QuanLiHoaDon_GUI2_JPanel quanLiHoaDon = new QuanLiHoaDon_GUI2_JPanel();
							quanLiHoaDon.loadTableData();
							quanLiHoaDon.loadTableData_chitiet();
							   panelCustomer.add(quanLiHoaDon);
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
                revalidate();
                repaint();
            }
        });

        btnQunLNhn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {      
                panelCustomer.removeAll();
						try {
						
							NhanVien_GUI2_JPanel quanLiHoaDon = new NhanVien_GUI2_JPanel();
							quanLiHoaDon.loadTableData();
							
							   panelCustomer.add(quanLiHoaDon);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                revalidate();
                repaint();
            }
        });
        banHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelCustomer.removeAll();
					try {
						HoaDon_GUI2_JPanel quanLiHoaDon = new HoaDon_GUI2_JPanel();	
						   panelCustomer.add(quanLiHoaDon);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                revalidate();
                repaint();
            }
        });
        
        btnChngTrnhGim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelCustomer.removeAll();
					try {
						CTGG_GUI2_JPanel quanLiHoaDon = new CTGG_GUI2_JPanel();
						quanLiHoaDon.loadTableData();
						quanLiHoaDon.loadTableData_Chitiet();
						   panelCustomer.add(quanLiHoaDon);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                revalidate();
                repaint();
            }
        });
        
        btnCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xóa nội dung cũ của panel khách hàng (nếu có)
                panelCustomer.removeAll();
       
                KhachHang22_GUI2_JPanel quanLiHoaDon = new KhachHang22_GUI2_JPanel();
				quanLiHoaDon.loadTableData();
	
				   panelCustomer.add(quanLiHoaDon);
                // Cập nhật hiển thị
                revalidate();
                repaint();
            }
        });
        quanLiSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xóa nội dung cũ của panel khách hàng (nếu có)
                panelCustomer.removeAll();
                QuanLiSach_GUI2_JPanel quanLiHoaDon = new QuanLiSach_GUI2_JPanel();         
                QuanLiSach_GUI2_JPanel.loadTableData();
				panelCustomer.add(quanLiHoaDon);
                // Cập nhật hiển thị
                revalidate();
                repaint();
            }
        });
    }
}

package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
public class TrangChu2_Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu2_Panel frame = new TrangChu2_Panel();
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
	public TrangChu2_Panel() {
		setPreferredSize(new Dimension(1300, 770));
		setLayout(null);
		  String imagePath = "src//image//lologo.jpg";

	        // Tạo một JLabel để hiển thị hình ảnh
	        JLabel imageLabel = new JLabel();
	        ImageIcon icon = new ImageIcon(imagePath);
	        imageLabel.setIcon(icon);

	        // Tạo một JPanel để chứa JLabel
	        JPanel panel_1 = new JPanel();
	        panel_1.setBounds(10,  10, 1195, 250);
	        panel_1.add(imageLabel); // Thêm JLabel chứa hình ảnh vào JPanel
	        add(panel_1);
	

	        JPanel panel_duoi = new JPanel();
	        panel_duoi.setBounds(10, 317, 1195, 398);
	        panel_duoi.setBackground(new Color(209, 227, 231)); // Màu nền xanh dương nhạt
	        panel_duoi.setLayout(null);

	        // Tạo JLabel cho hình ảnh 1 và đưa vào JPanel chính
	        JLabel label_1 = createLabel("src//image//chotoixinmotvedituoitho.jpg", 267, 405);
	        panel_duoi.add(label_1);

	        // Tạo JLabel cho hình ảnh 2 và đưa vào JPanel chính
	        JLabel label_2 = createLabel("src//image//dora12.jpg", 267, 405);
	        panel_duoi.add(label_2);

	        // Tạo JLabel cho hình ảnh 3 và đưa vào JPanel chính
	        JLabel label_3 = createLabel("src//image//toithayhoavangtrencoxanh.jpg", 267, 405);
	        panel_duoi.add(label_3);
	        
	        JLabel label_4 = createLabel("src//image//conchutgidenho.jpg", 267, 405);
	        panel_duoi.add(label_4);

	        // Thêm JPanel chính vào contentPane của JFrame
	        add(panel_duoi);
	        
	        JLabel lblNewLabel = new JLabel("Sách Bán Chạy Nhất");
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        lblNewLabel.setBounds(10, 270, 455, 39);
	        add(lblNewLabel);

	        // Tạo Timer để tự động di chuyển các hình ảnh từ trái qua phải
	        Timer timer = new Timer(20, new ActionListener() {
	            int x1 = 0;
	            int x2 = 355; // Khoảng cách giữa hình ảnh 1 và 2
	            int x3 = 710; // Khoảng cách giữa hình ảnh 2 và 3
	            int x4 = 1100;
	            public void actionPerformed(ActionEvent e) {
	                x1 += 5; // Tốc độ di chuyển của hình ảnh 1
	                x2 += 5; // Tốc độ di chuyển của hình ảnh 2
	                x3 += 5; // Tốc độ di chuyển của hình ảnh 3
	                x4+= 5; 
	                label_1.setLocation(x1, label_1.getY());
	                label_2.setLocation(x2, label_2.getY());
	                label_3.setLocation(x3, label_3.getY());
	                label_4.setLocation(x4, label_4.getY());

	                // Khi hình ảnh di chuyển ra khỏi JPanel, đặt lại vị trí của nó
	                if (x1 >= panel_duoi.getWidth()) {
	                    x1 = -label_1.getWidth();
	                }
	                if (x2 >= panel_duoi.getWidth()) {
	                    x2 = -label_2.getWidth();
	                }
	                if (x3 >= panel_duoi.getWidth()) {
	                    x3 = -label_3.getWidth();
	                }
	                if (x4 >= panel_duoi.getWidth()) {
	                    x4 = -label_4.getWidth();
	                }
	            }
	        });
	        timer.start();

	}
	   private static JLabel createLabel(String imagePath, int width, int height) {
	        ImageIcon icon = new ImageIcon(imagePath);
	        Image image = icon.getImage();
	        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledImage);
	        JLabel label = new JLabel(scaledIcon);
	        label.setSize(width, height); // Kích thước của JLabel
	        return label;
	    }
}

package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Database.ConnectionManager;
public class au extends JFrame {
    private JPanel contentPane;
    private Connection con = ConnectionManager.openConnection();
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {  
           
                try {
                    au frame = new au();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
  
    }

    public au() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 735, 739);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(74, 132, 590, 137);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        textField = new JTextField();
        textField.setBounds(180, 103, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton fetchButton = new JButton("Fetch Data");
        fetchButton.setBounds(300, 102, 120, 21);
        contentPane.add(fetchButton);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(227, 405, 96, 19);
        contentPane.add(textField_1);
        
        JButton fetchButton_1 = new JButton("Fetch Data");
        fetchButton_1.setBounds(347, 404, 120, 21);
        contentPane.add(fetchButton_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(121, 434, 590, 137);
        contentPane.add(scrollPane_1);
        
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fetchDataAndDisplay1();
            	System.out.println("What");
            }
        });
    }

//    private void fetchDataAndDisplay() {
//        String year = textField.getText();
//        if (year.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter a year.");
//            return;
//        }
//        
//      
//    
//        
//            String query = "SELECT QUARTER(ngaylap) AS quarter, SUM(tongTien) AS total_in FROM phieunhap WHERE YEAR(ngaylap) = ? GROUP BY QUARTER(ngaylap)";
//            double[] totalIn = null;
//			double[] totalOut = null;
//			try {
//				PreparedStatement stmt = con.prepareStatement(query);
//				stmt.setString(1, year);
//				ResultSet rsIn = stmt.executeQuery();
//    
//				String queryOut = "SELECT QUARTER(ngaylap) AS quarter, SUM(tongtien) AS total_out FROM hoadon WHERE YEAR(ngaylap) = ? GROUP BY QUARTER(ngaylap)";
//				PreparedStatement stmtOut = con.prepareStatement(queryOut);
//				stmtOut.setString(1, year);
//				ResultSet rsOut = stmtOut.executeQuery();
//
//				totalIn = new double[4];
//				totalOut = new double[4];
//
//				while (rsIn.next()) {
//				    int quarter = rsIn.getInt("quarter");
//				    totalIn[quarter - 1] = rsIn.getDouble("total_in");
//				}
//
//				while (rsOut.next()) {
//				    int quarter = rsOut.getInt("quarter");
//				    totalOut[quarter - 1] = rsOut.getDouble("total_out");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//            Object[][] data = new Object[4][4];
//            for (int i = 0; i < 4; i++) {
//                data[i][0] = "Q" + (i + 1);
//                data[i][1] = totalIn[i];
//                data[i][2] = totalOut[i];
//                data[i][3] = totalOut[i] - totalIn[i];
//            }
//
//            String[] columnNames = {"Quý", "Tiền Nhập", "Tiền Bán", "Lợi nhuận"};
//            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
//        
//    }
    private void fetchDataAndDisplay1() {
        String year = textField.getText();
        if (year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a year.");
            return;
        }
        
            String query = "SELECT MONTH(ngaylap) AS quarter, SUM(tongTien) AS total_in FROM phieunhap WHERE YEAR(ngaylap) = ? GROUP BY MONTH(ngaylap)";
            double[] totalIn = null;
			double[] totalOut = null;
			try {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, year);
				ResultSet rsIn = stmt.executeQuery();
    
				String queryOut = "SELECT MONTH(ngaylap) AS quarter, SUM(tongtien) AS total_out FROM hoadon WHERE YEAR(ngaylap) = ? GROUP BY MONTH(ngaylap)";
				PreparedStatement stmtOut = con.prepareStatement(queryOut);
				stmtOut.setString(1, year);
				ResultSet rsOut = stmtOut.executeQuery();

				totalIn = new double[12];
				totalOut = new double[12];

				while (rsIn.next()) {
				    int quarter = rsIn.getInt("quarter");
				    totalIn[quarter - 1] = rsIn.getDouble("total_in");
				}

				while (rsOut.next()) {
				    int quarter = rsOut.getInt("quarter");
				    totalOut[quarter - 1] = rsOut.getDouble("total_out");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
System.out.println("Ủa");
            Object[][] data = new Object[12][12];
            for (int i = 0; i < 12; i++) {
                data[i][0] = "Tháng" + (i + 1);
                data[i][1] = totalIn[i];
                data[i][2] = totalOut[i];
                data[i][3] = totalOut[i] - totalIn[i];
            }

            String[] columnNames = {"Tháng", "Tiền Nhập", "Tiền Bán", "Lợi nhuận"};
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        
    }
}

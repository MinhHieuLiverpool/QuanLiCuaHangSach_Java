package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.CTGG_DTO;
import DTO.Sach_DTO;
import Database.ConnectionManager;

public class CTGG_DAO {
    private static Connection connection = ConnectionManager.openConnection(); 

    // Constructor
    public CTGG_DAO(Connection connection) {
        CTGG_DAO.connection = connection;
    }

    // Phương thức để lấy tất cả CTGG từ cơ sở dữ liệu
    public static ArrayList<CTGG_DTO> getAllCTGG() throws SQLException {
        ArrayList<CTGG_DTO> ctggList = new ArrayList<>();
        String query = "SELECT * FROM ctgg";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maCTGG = rs.getString("maCTGG");
                String tenCTGG = rs.getString("tenCTGG");
                Date thoiGianBatDau = rs.getDate("ngayBatDau");
                Date thoiGianKetThuc = rs.getDate("ngayKetThuc");

                CTGG_DTO ctgg = new CTGG_DTO(maCTGG, tenCTGG, thoiGianBatDau, thoiGianKetThuc);
                ctggList.add(ctgg);
            }
        }

        return ctggList;
    }

    // Phương thức để thêm một CTGG vào cơ sở dữ liệu
    public static boolean addCTGG(CTGG_DTO ctgg) throws SQLException {
        boolean result = false;
        try {
            // openConnection();
            String sql = "INSERT INTO ctgg (maCTGG, tenCTGG, ngayBatDau, ngayKetThuc ) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,ctgg.getMaCTGG());
            stmt.setString(2, ctgg.getTenCTGG());    
            stmt.setDate(3, new java.sql.Date(ctgg.getThoiGianBatDau().getTime()));
            stmt.setDate(4, new java.sql.Date(ctgg.getThoiGianKetThuc().getTime()));
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            // ConnectionManager.closeConnection();
        }
        return result;
    }

    // Phương thức để xóa một CTGG khỏi cơ sở dữ liệu
    public static boolean deleteCTGG(String maCTGG) throws SQLException {
        String query = "DELETE FROM ctgg WHERE maCTGG = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maCTGG);
            ps.executeUpdate();
            return true ;
        }
    }
    
    public static boolean hasDetails(String maCTGG) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean hasDetails = false;

            // Chuẩn bị truy vấn SQL
            String query = "SELECT COUNT(*) FROM ChiTietCTGG WHERE MaCTGG = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, maCTGG);

            // Thực thi truy vấn
            resultSet = statement.executeQuery();

            // Xử lý kết quả
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                hasDetails = count > 0;
            }
       
        return hasDetails;
    }
    
    
    public static boolean updateCTGG(CTGG_DTO ctgg) {
        boolean result = false;
        PreparedStatement stmt = null;
        try {  
            String sql = "UPDATE CTGG SET tenCTGG=?, ngayBatDau=?, ngayKetThuc=? WHERE maCTGG=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, ctgg.getTenCTGG());
            stmt.setDate(2, new java.sql.Date(ctgg.getThoiGianBatDau().getTime()));
            stmt.setDate(3, new java.sql.Date(ctgg.getThoiGianKetThuc().getTime()));
            stmt.setString(4, ctgg.getMaCTGG());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            // Đóng PreparedStatement trước
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing statement: " + ex.getMessage());
                }
            }
            // Sau đó đóng Connection
           
        }
        return result;
    }
    public int countDuplicateMaCTGG(String maCTGG) throws SQLException {
        String query = "SELECT COUNT(*) FROM CTGG WHERE MaCTGG = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maCTGG);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }
    
    public static List<CTGG_DTO> search(Date startDate, Date endDate) throws SQLException {
        List<CTGG_DTO> resultList = new ArrayList<>();
        String query = "SELECT * FROM ctgg WHERE ngaybatDau >= ? AND ngayKetThuc <= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Đọc dữ liệu từ ResultSet và tạo đối tượng CTGG_DTO
                    String maCTGG = resultSet.getString("MaCTGG");
                    String tenCTGG = resultSet.getString("tenCTGG");
                    Date ngaybatDau = resultSet.getDate("ngaybatDau");
                    Date ngayKetThuc = resultSet.getDate("ngayKetThuc");

                    CTGG_DTO ctgg = new CTGG_DTO(maCTGG, tenCTGG, ngaybatDau, ngayKetThuc);
                    resultList.add(ctgg);
                }
            }
        }
        return resultList;
    }
    public static ResultSet searchAndDisplayFromDatabase(String maCTGG, String tenCTGG, Date ngayBatDau, Date ngayKetThuc) {
        // Bắt đầu truy vấn
        String query = "SELECT * FROM ctgg WHERE";
        List<String> conditions = new ArrayList<>();

        // Thêm điều kiện vào danh sách nếu giá trị không rỗng
        if (maCTGG != null && !maCTGG.isEmpty()) {
            conditions.add("MaCTGG = '" + maCTGG + "'");
        }
        if (tenCTGG != null && !tenCTGG.isEmpty()) {
            conditions.add("TenCTGG = '" + tenCTGG + "'");
        }
        if (ngayBatDau != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strNgayBatDau = dateFormat.format(ngayBatDau);
            conditions.add("NgayBatDau >= '" + strNgayBatDau + "'");
        }
        if (ngayKetThuc != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strNgayKetThuc = dateFormat.format(ngayKetThuc);
            conditions.add("NgayKetThuc <= '" + strNgayKetThuc + "'");
        }
        // Kết hợp các điều kiện bằng AND
        if (!conditions.isEmpty()) {
            query += " " + String.join(" AND ", conditions);
        } else {
            // Nếu không có điều kiện, trả về tất cả dữ liệu
            query = "SELECT * FROM ctgg";
        }

        // Thực thi truy vấn và trả về ResultSet
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }
    
    public static boolean kiemTraNgayLapTrongKhoangCTGG(Date ngayLap, String maCTGG) {
        try { 
            String sql = "SELECT ngayBatDau, ngayKetThuc FROM ctgg WHERE maCTGG = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maCTGG);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                Date ngayBatDau = result.getDate("ngayBatDau");
                Date ngayKetThuc = result.getDate("ngayKetThuc"); 
                // Kiểm tra xem ngày lập hóa đơn có nằm trong khoảng thời gian bắt đầu và kết thúc của CTGG hay không
                if (ngayLap.after(ngayBatDau) && ngayLap.before(ngayKetThuc)) {
                    return true;
                }
            }
           
        } catch (SQLException ex) {
            // Xử lý nếu có lỗi khi truy vấn cơ sở dữ liệu
            ex.printStackTrace();
        }
        return false;
    }

  
	
}

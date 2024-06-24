package BUS;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAO.ChucVu_DAO;
import DTO.ChucVu_DTO;

public class ChucVu_BUS {
    private ChucVu_DAO chucVuDAO;

    public ChucVu_BUS(Connection connection) {
        this.chucVuDAO = new ChucVu_DAO(connection);
    }

    public static void loadDataIntoComboBox_tenChucVu(JComboBox<String> tenComboBox) {
        ArrayList<ChucVu_DTO> chucVus = ChucVu_DAO.getAllAuthors();
        for (ChucVu_DTO chucVu : chucVus) {
            tenComboBox.addItem(chucVu.getMaChucVu());
        }
    }
}

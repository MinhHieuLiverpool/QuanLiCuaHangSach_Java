package PDF;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;

public class PDFiText {
    public static void savePDF(JTable table, String filePath) {
        try {
            // Tạo một đối tượng Document
            Document document = new Document(PageSize.A4.rotate());

            // Tạo một đối tượng PdfWriter để viết vào tập tin PDF
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Mở Document để viết vào
            document.open();

            // Sử dụng font Arial với mã Unicode "Identity-H"
            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf);
            
            // Tạo một đối tượng PdfPTable từ JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(new Paragraph(table.getColumnName(i), font));
            }

            // Thêm dữ liệu từ JTable vào PdfPTable
            for (int rows = 0; rows < table.getRowCount(); rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    pdfTable.addCell(new Paragraph(table.getModel().getValueAt(rows, cols).toString(), font));
                }
            }

            // Thêm PdfPTable vào Document
            document.add(pdfTable);

            // Đóng Document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

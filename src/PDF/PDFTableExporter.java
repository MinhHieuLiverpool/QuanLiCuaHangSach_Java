package PDF;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFTableExporter extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btnNewButton;
	private int margin;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PDFTableExporter frame = new PDFTableExporter();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PDFTableExporter() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 414, 241);
        contentPane.add(scrollPane);

        Object[][] data = {
                {"John", "25", "Male","1","2","3","4"},
                {"Amy", "30", "Female","1","2","3","4"},
                {"David", "35", "Male","1","2","3","4"}
        };

        String[] columnNames = {"Name", "Age", "Gender","1","2","3","4"};

        table = new JTable(data, columnNames);
        scrollPane.setViewportView(table);

        btnNewButton = new JButton("Save PDF");
        btnNewButton.setBounds(142, 331, 85, 21);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePDF();
            }
        });
    }

    private void savePDF() {
        String filePath = chooseFilePath();
        if (filePath != null) {
            generatePDF(filePath);
        }
    }

    private String chooseFilePath() {
        String filePath = null;
        try {
            File selectedFile;
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                filePath = selectedFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private void drawCellBorder(PDPageContentStream contentStream, float x, float y, float width, float height) throws IOException {
        contentStream.moveTo(x, y);
        contentStream.lineTo(x + width, y);
        contentStream.lineTo(x + width, y - height);
        contentStream.lineTo(x, y - height);
        contentStream.lineTo(x, y);
        contentStream.stroke();
    }

    private void drawRow(PDPageContentStream contentStream, float y, float rowHeight, float tableWidth) throws IOException {
        contentStream.moveTo(margin, y);
        contentStream.lineTo(margin + tableWidth, y);
        contentStream.stroke();
    }

    private void drawCell(PDPageContentStream contentStream, float x, float y, float rowHeight, String text) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y - rowHeight / 2);
        contentStream.showText(text != null ? text : "");
        contentStream.endText();
    }

    private void generatePDF(String filePath) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setNonStrokingColor(Color.BLACK);

            margin = 20;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = 500; // Adjust width to accommodate all columns
            float yPosition = yStart;
            float rowHeight = 20;
            float cellWidth = tableWidth / table.getColumnCount();
            // Draw top border
            contentStream.moveTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.stroke();

            // Draw column headers and surrounding borders
            float xPosition = margin;
            for (int i = 0; i < table.getColumnCount(); i++) {
                drawCellBorder(contentStream, xPosition, yStart, cellWidth, rowHeight);
                drawCell(contentStream, xPosition + 5, yStart + 3, 0, table.getColumnName(i));
                xPosition += cellWidth;
            }

            // Draw lines below headers
            for (int i = 0; i <= table.getRowCount(); i++) {
                drawRow(contentStream, yStart - (i * rowHeight), rowHeight, tableWidth);
            }

            // Draw data cells and surrounding borders
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    String text = (String) table.getValueAt(i, j);
                    drawCellBorder(contentStream, margin + j * cellWidth, yPosition, cellWidth, rowHeight);
                    drawCell(contentStream, margin + j * cellWidth + 5, yPosition - 3, rowHeight, text);
                }
                yPosition -= rowHeight;
            }

            contentStream.close();
            doc.save(filePath);
            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    

}

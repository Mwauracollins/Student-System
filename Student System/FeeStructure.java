import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class FeeStructure extends JFrame {
    private File pdfFile;
    private PDDocument pdfDocument;
    private PDFRenderer pdfRenderer;

    public FeeStructure(File pdfFile) throws IOException {
        this.pdfFile = pdfFile;
        pdfDocument = PDDocument.load(pdfFile);
        pdfRenderer = new PDFRenderer(pdfDocument);

        setTitle("Fee Structure");
        setSize(800, 600);
        setLocationRelativeTo(null);

        add(new PDFRendererComponent(), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private class PDFRendererComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                // Render the PDF page
                BufferedImage image = pdfRenderer.renderImage(0);

                // Scale the image to fit the window
                double scaleX = (double) getWidth() / image.getWidth();
                double scaleY = (double) getHeight() / image.getHeight();
                double scale = Math.min(scaleX, scaleY);
                int width = (int) (image.getWidth() * scale);
                int height = (int) (image.getHeight() * scale);

                // Draw the image on the component
                g.drawImage(image, 0, 0, width, height, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

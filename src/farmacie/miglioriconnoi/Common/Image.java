package farmacie.miglioriconnoi.Common;

import farmacie.miglioriconnoi.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Image extends JLabel {
    private InputStream imageStream;
    private BufferedImage image;
    private BufferedImage resizedImage;
    private int targetWidth;
    private int targetHeight;

    public Image(String imageUrl, int targetWidth, int targetHeight ) throws FileNotFoundException {
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
        this.imageStream = this.getClass().getResourceAsStream("../resources/"+imageUrl);

        try {
            assert this.imageStream != null;
            this.image = ImageIO.read(this.imageStream);
            this.resizedImage = resizeImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setIcon(new ImageIcon(resizedImage));
        //this.setPreferredSize(new Dimension(125, 63));
    }

    BufferedImage resizeImage() throws IOException {
        BufferedImage resizedImage = new BufferedImage(this.targetWidth, this.targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(this.image, 0, 0, this.targetWidth, this.targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}

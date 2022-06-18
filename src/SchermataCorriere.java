import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SchermataCorriere {
    public SchermataCorriere(){
        GridBagConstraints c = new GridBagConstraints();

        InputStream imageStream = this.getClass().getResourceAsStream("res/logo.jpg");
        BufferedImage image;
        BufferedImage resizedImage;
        try {
            assert imageStream != null;
            image = ImageIO.read(imageStream);
            resizedImage = resizeImage(image, 125, 63);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
        picLabel.setSize(125, 63);




        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 720 - picLabel.getWidth(), 1080 - picLabel.getHeight());
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        Main.schermataCorrierePanel.add(picLabel, c);

    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}

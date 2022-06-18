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

        Image image = new Image("res/logo.jpg",125,63);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 855);
        c.anchor = GridBagConstraints.FIRST_LINE_START;


        Main.schermataCorrierePanel.add(image, c);

        JLabel label = new JLabel("ciao");
        label.setSize(100,100);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        Main.schermataCorrierePanel.add(label, c);

    }

}

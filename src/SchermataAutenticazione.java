import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SchermataAutenticazione {
    public SchermataAutenticazione()  {

        //CREA I COSTRAINTS, OVVERO I DETTAGLI E LE CARATTERISTICHE CHE DOVREMO POI IMPOSTARE
        //PER OGNI ELEMENTO CHE AGGIUNGEREMO NELLA GRIDBAG, IN PRATICA NELLA SCHERMATA
        GridBagConstraints c = new GridBagConstraints();

        InputStream imageStream = this.getClass().getResourceAsStream("res/logo.jpg");
        BufferedImage image;
        try {
            assert imageStream != null;
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        Main.schermataAutenticazionePanel.add(picLabel, c);



    }


}

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

        //immagine
        InputStream imageStream = this.getClass().getResourceAsStream("res/logo.jpg");

        BufferedImage image;

        try {
            assert imageStream != null;
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setSize(320, 320);

        //LOGO
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets= new Insets(50, 200, 110, 200);
        c.anchor= GridBagConstraints.PAGE_START;
        c.weightx=0.5;
        c.weighty=0.5;

        Main.schermataAutenticazionePanel.add(picLabel, c);

        //LOGIN
        c.fill= GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.insets= new Insets(50, 285, 50, 285);
        c.anchor= GridBagConstraints.CENTER;
        c.weightx=0.5;
        c.weighty=0.5;

        Button.createButtonChangeView("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataLogin", "Login", 150,150, c);

        //REGISTRAZIONE
        c.fill= GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.insets= new Insets(50, 285, 50, 285);
        c.anchor= GridBagConstraints.CENTER;
        c.weightx=0.5;
        c.weighty=0.5;

        Button.createButtonChangeView("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataRegistrazione", "Registrazione", 150,150, c);

        //RECUPERO CREDENZIALI
        c.fill= GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.insets= new Insets(50, 285, 50, 285);
        c.anchor= GridBagConstraints.PAGE_END;
        c.weightx=0.5;
        c.weighty=0.5;

        Button.createButtonChangeView("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataRecuperoCredenziali", "Recupero Credenziali", 150,150, c);







    }


}

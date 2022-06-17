import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class SchermataAutenticazione {
    public SchermataAutenticazione()  {

        //CREA I COSTRAINTS, OVVERO I DETTAGLI E LE CARATTERISTICHE CHE DOVREMO POI IMPOSTARE
        //PER OGNI ELEMENTO CHE AGGIUNGEREMO NELLA GRIDBAG, IN PRATICA NELLA SCHERMATA
        GridBagConstraints c = new GridBagConstraints();


        ImageIcon logoImageFile = new ImageIcon("res/logo.jpg");
        JLabel logoImage = new JLabel(logoImageFile);
        logoImage.setSize(100,100);

        JButton button = new JButton();
        Main.schermataAutenticazionePanel.add(button, c);

        //c.insets = new Insets(40,500,40,500);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;
        c.ipadx = 80;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        Main.schermataAutenticazionePanel.add(logoImage, c);
    }


}

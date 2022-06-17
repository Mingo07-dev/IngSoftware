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

    public static Button buttonSchermataLogin;
    public static Button buttonSchermataRegistrazione;
    public static Button buttonSchermataRecuperoCredenziali;

    public SchermataAutenticazione()  {
        //dichiara i constraints, ovvero i dettagli e le caratteristiche che dovremo poi impostare
        //per ogni elemento che aggiungeremo nella gridbag, in pratica nella schermata
        GridBagConstraints c = new GridBagConstraints();

        //CREA L'IMMAGINE LOGO
        InputStream imageStream = this.getClass().getResourceAsStream("res/logo.jpg");
        BufferedImage image;
        try {
            assert imageStream != null;
            image = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setSize(400, 200);

        //seleziona i constraints per il l'immagine logo e la aggiunge alla schermata
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets= new Insets(40, 340, 60, 340);
        c.anchor= GridBagConstraints.PAGE_START;
        Main.schermataAutenticazionePanel.add(picLabel, c);
        //FINE IMMAGINE LOGO

        // BOTTONE LOGIN
        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets= new Insets(0, 440, 40, 440);
        c.anchor= GridBagConstraints.CENTER;
        Button buttonSchermataLogin = new Button("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataLogin", "Login", 200,100, c);
        buttonSchermataLogin.changeFontButton("Arial",1, 25);
        buttonSchermataLogin.createListenerButtonChangeView();
        //FINE BOTTONE LOGIN



        //BOTTONE REGISTRAZIONE
        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets= new Insets(0, 440, 40, 440);
        c.anchor= GridBagConstraints.CENTER;
        buttonSchermataRegistrazione = new Button("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataRegistrazione", "Registrazione", 200,100, c);
        buttonSchermataRegistrazione.changeFontButton("Arial",1, 25);
        buttonSchermataRegistrazione.createListenerButtonChangeView();
        //FINE BOTTONE REGISTRAZIONE



        //BOTTONE RECUPERO CREDENZIALI
        c.fill= GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets= new Insets(0, 440, 40, 440);
        c.anchor= GridBagConstraints.PAGE_END;
        buttonSchermataRecuperoCredenziali = new Button("SchermataAutenticazione", Main.schermataAutenticazionePanel, "SchermataRecuperoCredenziali", "Recupero Credenziali", 200,100, c);
        buttonSchermataRecuperoCredenziali.changeFontButton("Arial",1, 25);
        buttonSchermataRecuperoCredenziali.createListenerButtonChangeView();
        //FINE BOTTONE RECUPERO CREDENZIALI



    }


}

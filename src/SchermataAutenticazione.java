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

        JPanel border = new JPanel(new BorderLayout());
        JPanel flowImage = new JPanel(new FlowLayout());
        flowImage.add(picLabel);
        border.add(flowImage, BorderLayout.NORTH);

        Main.schermataAutenticazionePanel.add(border, BorderLayout.NORTH);
        //FINE IMMAGINE LOGO

        // BOTTONE LOGIN
        Button buttonSchermataLogin = new Button("SchermataAutenticazione",  "Login", 300,50);
        buttonSchermataLogin.changeFontButton("Arial",1, 25);
        buttonSchermataLogin.createListenerButtonChangeView("SchermataLogin");
        JPanel border1 = new JPanel(new BorderLayout(440,40));
        border1.setSize(200,100);
        JPanel flowLogin = new JPanel(new FlowLayout());
        flowLogin.add(buttonSchermataLogin);
        border1.add(flowLogin, BorderLayout.NORTH);
        //FINE BOTTONE LOGIN



        //BOTTONE REGISTRAZIONE
        buttonSchermataRegistrazione = new Button("SchermataAutenticazione",  "Registrazione", 300,50);
        buttonSchermataRegistrazione.changeFontButton("Arial",1, 25);
        buttonSchermataRegistrazione.createListenerButtonChangeView("SchermataRegistrazione");
        JPanel flowRegistrazione = new JPanel(new FlowLayout());
        flowRegistrazione.add(buttonSchermataRegistrazione);
        border1.add(flowRegistrazione, BorderLayout.CENTER);
        //FINE BOTTONE REGISTRAZIONE



        //BOTTONE RECUPERO CREDENZIALI
        buttonSchermataRecuperoCredenziali = new Button("SchermataAutenticazione",  "Recupero Credenziali", 300,50);
        buttonSchermataRecuperoCredenziali.changeFontButton("Arial",1, 25);
        buttonSchermataRecuperoCredenziali.createListenerButtonChangeView("SchermataRecuperoCredenziali");

        JPanel flowRecupero = new JPanel(new FlowLayout());
        flowRecupero.add(buttonSchermataRecuperoCredenziali);
        border1.add(flowRecupero, BorderLayout.SOUTH);

        Main.schermataAutenticazionePanel.add(border1, BorderLayout.CENTER);
        //FINE BOTTONE RECUPERO CREDENZIALI



    }


}

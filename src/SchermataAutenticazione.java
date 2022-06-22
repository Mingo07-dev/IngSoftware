import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SchermataAutenticazione {

    public static JPanel schermataRegistrazionePanel;
    public static JPanel schermataLoginPanel;
    public static JPanel schermataRecuperoCredenzialiPanel;

    public static Button buttonSchermataLogin;
    public static Button buttonSchermataRegistrazione;
    public static Button buttonSchermataRecuperoCredenziali;

    public SchermataAutenticazione() throws FileNotFoundException {
        schermataRegistrazionePanel = new JPanel(new BorderLayout());
        schermataRegistrazionePanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        schermataLoginPanel = new JPanel(new BorderLayout());
        schermataLoginPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        schermataRecuperoCredenzialiPanel = new JPanel(new BorderLayout());
        schermataRecuperoCredenzialiPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        //dichiara i constraints, ovvero i dettagli e le caratteristiche che dovremo poi impostare
        //per ogni elemento che aggiungeremo nella gridbag, in pratica nella schermata

        JPanel mainPanel = new JPanel(new BorderLayout(0,125));

        //CREA L'IMMAGINE LOGO
        Image picLabel = new Image("logo.png",200,200);
        //FINE IMMAGINE LOGO
        JPanel mainNorthPanel = new JPanel(new FlowLayout());
        mainNorthPanel.add(picLabel);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.PAGE_AXIS));


        // BOTTONE LOGIN
        buttonSchermataLogin = new Button("SchermataAutenticazione",  "Login", 300,50);
        buttonSchermataLogin.changeFontButton("Arial",1, 25);
        buttonSchermataLogin.createListenerButtonsAutenticazioneLogin("SchermataLogin");
        //FINE BOTTONE LOGIN

        buttonSchermataLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataLogin);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));

        //BOTTONE REGISTRAZIONE
        buttonSchermataRegistrazione = new Button("SchermataAutenticazione",  "Registrazione", 300,50);
        buttonSchermataRegistrazione.changeFontButton("Arial",1, 25);
        buttonSchermataRegistrazione.createListenerButtonsAutenticazioneRegistrazione("SchermataRegistrazione");
        //FINE BOTTONE REGISTRAZIONE

        buttonSchermataRegistrazione.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataRegistrazione);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));

        //BOTTONE RECUPERO CREDENZIALI
        buttonSchermataRecuperoCredenziali = new Button("SchermataAutenticazione",  "Recupero Credenziali", 300,50);
        buttonSchermataRecuperoCredenziali.changeFontButton("Arial",1, 25);
        buttonSchermataRecuperoCredenziali.createListenerButtonsAutenticazioneRecuperoCredenziali("SchermataRecuperoCredenziali");
        //FINE BOTTONE RECUPERO CREDENZIALI

        buttonSchermataRecuperoCredenziali.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataRecuperoCredenziali);

        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        Main.schermataAutenticazionePanel.add(mainPanel, BorderLayout.CENTER);

    }


}

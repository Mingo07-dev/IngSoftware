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

        //CREA L'IMMAGINE LOGO
        Image picLabel = new Image("res/logo.jpg",400,200);

        JPanel borderView = new JPanel(new BorderLayout(0,100));
        JPanel border = new JPanel(new BorderLayout(315,0));
        JPanel flowImage = new JPanel(new FlowLayout());
        flowImage.add(picLabel);
        border.add(flowImage, BorderLayout.NORTH);

        //FINE IMMAGINE LOGO

        // BOTTONE LOGIN
        buttonSchermataLogin = new Button("SchermataAutenticazione",  "Login", 300,50);
        buttonSchermataLogin.changeFontButton("Arial",1, 25);
        buttonSchermataLogin.createListenerButtonChangeView("SchermataLogin");
        JPanel border1 = new JPanel(new BorderLayout(365,56));
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

        borderView.add(border, BorderLayout.NORTH);
        borderView.add(border1, BorderLayout.SOUTH);

        JPanel sopra = new JPanel((new BorderLayout()));
        JPanel sotto = new JPanel((new BorderLayout()));
        Main.schermataAutenticazionePanel.add(sopra, BorderLayout.NORTH);
        Main.schermataAutenticazionePanel.add(borderView, BorderLayout.CENTER);
        Main.schermataAutenticazionePanel.add(sotto, BorderLayout.SOUTH);
        //FINE BOTTONE RECUPERO CREDENZIALI



    }


}

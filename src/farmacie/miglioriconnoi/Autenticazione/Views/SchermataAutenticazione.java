package farmacie.miglioriconnoi.Autenticazione.Views;

import farmacie.miglioriconnoi.Autenticazione.Control.ControlAutenticazione;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataAutenticazione {

    public static Button buttonSchermataLogin;
    public static Button buttonSchermataRegistrazione;
    public static Button buttonSchermataRecuperoCredenziali;

    public SchermataAutenticazione() throws FileNotFoundException {
        //PANNELLO MAINPANEL CHE CONTIENE TUTTI GLI ELEMENTI DELLA SCHERMATA
        JPanel mainPanel = new JPanel(new BorderLayout(0,125));

        //CREA L'IMMAGINE LOGO
        Image picLabel = new Image("logo.png",200,200);

        //PANNELLO NORD DEL MAINPANEL AL QUALE VIENE AGGIUNTO IL LOGO
        JPanel mainNorthPanel = new JPanel(new FlowLayout());
        mainNorthPanel.add(picLabel);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        //PANNELLO CENTRALE DEL MAINPANEL AL QUALE VENGONO AGGIUNTI I BOTTONI LOGIN,REGISTRAZIONE E RECUPERO CREDENZIALI
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        // BOTTONE LOGIN
        buttonSchermataLogin = new Button("Login", 300,50);
        buttonSchermataLogin.changeFontButton("Arial",1, 25);
        buttonSchermataLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataLogin);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));
        //FINE BOTTONE LOGIN

        //BOTTONE REGISTRAZIONE
        buttonSchermataRegistrazione = new Button("Registrazione", 300,50);
        buttonSchermataRegistrazione.changeFontButton("Arial",1, 25);
        buttonSchermataRegistrazione.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataRegistrazione);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));
        //FINE BOTTONE REGISTRAZIONE

        //BOTTONE RECUPERO CREDENZIALI
        buttonSchermataRecuperoCredenziali = new Button("Recupero Credenziali", 300,50);
        buttonSchermataRecuperoCredenziali.changeFontButton("Arial",1, 25);
        buttonSchermataRecuperoCredenziali.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonSchermataRecuperoCredenziali);
        //FINE BOTTONE RECUPERO CREDENZIALI

        //AGGIUNGE I LISTENERS AI BOTTONI
        ControlAutenticazione.setListenersAutenticazione();
        //AGGIUNGE IL MAINPANEL ALLA SCHERMATA
        Main.schermataAutenticazionePanel.add(mainPanel, BorderLayout.CENTER);

    }


}

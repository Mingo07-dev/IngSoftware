package farmacie.miglioriconnoi.Autenticazione.Views;

import farmacie.miglioriconnoi.Autenticazione.Control.ControlLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.PasswordField;
import farmacie.miglioriconnoi.Common.TextField;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataLogin {

    public static TextField emailField;
    public static PasswordField passwordField;
    public static Button buttonLogin;
    public static Button buttonTornaIndietro;
    public static String email = "";
    public static String nomeFarmacia = "";
    public static String mansione = "";
    public static JCheckBox mostraPassword;


    public SchermataLogin() throws FileNotFoundException {
        //PANNELLO MAINPANEL CHE CONTIENE TUTTI GLI ELEMENTI DELLA SCHERMATA
        JPanel mainPanel = new JPanel(new BorderLayout());

        //PANNELLO NORD DEL MAINPANEL AL QUALE VIENE AGGIUNTA IL LOGO AD EST E IL PANNELLO OPZIONI UTENTE A OVEST
        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        //CREA L'IMMAGINE
        Image picLabel = new Image("logo.png",100,100);
        mainNorthPanel.add(picLabel, BorderLayout.WEST);
        //FINE IMMAGINE

        //PANNELLO OPZIONI UTENTE CHE CONTIENE IL BOTTONE TORNA INDIETRO
        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        //CREA IL BOTTONE TORNA INDIETRO
        buttonTornaIndietro = new Button("Torna Indietro", 150,50);
        buttonTornaIndietro.changeFontButton("Arial", 1,15);
        mainUserOptionsPanel.add(buttonTornaIndietro);
        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);
        //FINE BOTTONE TORNA INDIETRO

        //PANNELLO CENTRALE DEL MAINPANEL AL QUALE VIENE AGGIUNTO IL BOX VERTICALE
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));


        //BOX VERTICALE CHE CONTIENE IL LOGO, I CAMPI DA INSERIRE E IL BOTTONE LOGIN
        JPanel centerFlow = new JPanel(new FlowLayout());

        //CREA IL LOGO
        Image utente = new Image("utente.png", 200,200);
        utente.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(utente);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));
        //FINE LOGO

        //CREA IL CAMPO EMAIL
        emailField = new TextField(35, "Mail", 150,30);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(emailField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,40)));
        //FINE CAMPO EMAIL

        //CREA IL CAMPO PASSWORD
        passwordField = new PasswordField(35, 150,30);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(passwordField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,20)));
        //FINE CAMPO PASSWORD

        //CREA IL CAMPO MOSTRA PASSWORD
        mostraPassword = new JCheckBox("Mostra Password");
        mostraPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(mostraPassword);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));
        //FINE MOSTRA PASSWORD

        //CREA IL BOTTONE LOGIN
        buttonLogin = new Button("Login",150,50);
        buttonLogin.changeFontButton("Arial", 1, 25);
        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonLogin);
        //FINE BOTTONE LOGIN

        //AGGIUNGE IL BOX VERTICALE AL PANNELLO CENTRALE
        centerFlow.add(mainCenterPanel);


        //AGGIUNGE IL PANNELLO NORD AL MAINPANEL
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        //AGGIUNGE IL PANNELLO CENTRALE AL MAINPANEL
        mainPanel.add(centerFlow, BorderLayout.CENTER);

        //AGGIUNGE IL MAIN PANEL ALLA SCHERMATA
        Main.schermataLoginPanel.add(mainPanel, BorderLayout.CENTER);

        //CREA E INIZIALIZZA I LISTENERS
        ControlLogin.setListeners();
    }

    
}

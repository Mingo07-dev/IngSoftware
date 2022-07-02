package farmacie.miglioriconnoi.Autenticazione.Views;


import farmacie.miglioriconnoi.Autenticazione.Control.ControlRegistrazione;
import farmacie.miglioriconnoi.Common.*;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.TextField;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchermataRegistrazione {

    public static String mansione= "";
    public static JMenu mansioneButtonMenu;
    public static Button registrazioneButton;

    public static JTextArea campoErrato;

    public static Button GoBack;

    public static TextField emailText;
    public static PasswordField passwordText;
    public static PasswordField confirmPasswordText;
    public static JCheckBox mostraPassword;

    public static final Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9._-]{1,}[@]{1}[a-z.]{2,}[.]{1}+[a-z]{1,4})");
    public static Matcher emailMatcher;

    public static final Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.@#$%^&+=])(?=\\S+$).{6,}$");
    public static Matcher passwordMatcher;

    public static String mail;
    public static boolean mansioneSelected = false;

    public static String mansioneString;


    public SchermataRegistrazione() throws FileNotFoundException {
        mansioneSelected = false;

        //PANNELLO MAIN CHE CONTIENE TUTTI GLI ELEMENTI DELLA SCHERMATA, DIVISO IN NORD, CENTRO E SUD
        JPanel fullView = new JPanel(new BorderLayout(0,20));
        JPanel northView = new JPanel(new BorderLayout());
        JPanel centerView = new JPanel(new BorderLayout());
        JPanel sudView = new JPanel(new BorderLayout());

        //PARTE NORD DELLA SCHERMATA
        Image logo = new Image("logo.png",100,100);
        GoBack = new Button("Torna Indietro",150,50);
        GoBack.changeFontButton("Arial",1,15);
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(GoBack);
        northView.add(logo,BorderLayout.WEST);
        northView.add(buttonsPanel,BorderLayout.EAST);
        //FINE PARTE NORD DELLA SCHERMATA


        //PARTE CENTRALE DELLA SCHERMATA
        JPanel boxCenterPanel = new JPanel();
        boxCenterPanel.setLayout(new BoxLayout(boxCenterPanel, BoxLayout.PAGE_AXIS));


        Image utenteImage = new Image("utente.png",100,100);
        utenteImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(utenteImage);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        emailText = new TextField(35, "Mail", 150, 30);
        emailText.setPreferredSize(new Dimension(150,30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(emailText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        JPanel menuMansione = createMansioneMenu();
        menuMansione.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(menuMansione);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        passwordText = new PasswordField(35, 150, 30);
        passwordText.setPreferredSize(new Dimension(150,30));
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(passwordText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        confirmPasswordText = new PasswordField(35, 150, 30);
        confirmPasswordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(confirmPasswordText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));

        mostraPassword = new JCheckBox("Mostra Password");
        mostraPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(mostraPassword);

        JPanel boxFlowLayout = new JPanel(new FlowLayout());
        boxFlowLayout.add(boxCenterPanel);
        centerView.add(boxFlowLayout,BorderLayout.CENTER);
        //FINE PARTE CENTRALE DELLA SCHERMATA


        //PARTE SUD DELLA SCHERMATA
        registrazioneButton = new Button("REGISTRATI", 200,50);
        registrazioneButton.changeFontButton("Arial",1,20);
        JPanel registrazioneButtonPanel = new JPanel(new FlowLayout());
        registrazioneButtonPanel.add(registrazioneButton);
        campoErrato = new JTextArea();
        campoErrato.setEnabled(false);
        campoErrato.setForeground(Color.red);
        campoErrato.setDisabledTextColor(Color.red);
        campoErrato.setSelectionColor(Color.red);
        JPanel sudCenterView = new JPanel(new FlowLayout());
        sudCenterView.add(campoErrato, BorderLayout.CENTER);
        sudView.add(sudCenterView, BorderLayout.NORTH);
        sudView.add(registrazioneButtonPanel,BorderLayout.SOUTH);
        //FINE PARTE SUD DELLA SCHERMATA

        fullView.add(northView,BorderLayout.NORTH);
        fullView.add(centerView,BorderLayout.CENTER);
        fullView.add(sudView,BorderLayout.SOUTH);
        Main.schermataRegistrazionePanel.add(fullView,BorderLayout.CENTER);

        ControlRegistrazione.setListeners();
    }



    private static JPanel createMansioneMenu(){

        //MENU A CASCATA PER SELEZIONARE LA MANSIONE
        JMenuBar menuBar = new JMenuBar();
        mansioneButtonMenu = new JMenu("Mansione");
        JRadioButtonMenuItem buttonFarmacista = new JRadioButtonMenuItem("Farmacista");
        JRadioButtonMenuItem buttonCorriere = new JRadioButtonMenuItem("Corriere");
        JRadioButtonMenuItem buttonImpiegatoAzienda = new JRadioButtonMenuItem("Impiegato Azienda");
        ButtonGroup group = new ButtonGroup();
        group.add(buttonFarmacista);
        group.add(buttonCorriere);
        group.add(buttonImpiegatoAzienda);
        mansioneButtonMenu.add(buttonFarmacista);
        mansioneButtonMenu.add(buttonCorriere);
        mansioneButtonMenu.add(buttonImpiegatoAzienda);
        menuBar.add(mansioneButtonMenu);
        JPanel pannello = new JPanel(new FlowLayout());
        pannello.add(menuBar);
        buttonFarmacista.addActionListener(ActionListeners.MenuMansioneButton("SchermataFarmacista", "Farmacista"));
        buttonCorriere.addActionListener(ActionListeners.MenuMansioneButton("SchermataCorriere","Corriere"));
        buttonImpiegatoAzienda.addActionListener(ActionListeners.MenuMansioneButton("SchermataImpiegatoAzienda","ImpiegatoAzienda"));
        //FINE MENU A CASCATA

        return pannello;
    }

}


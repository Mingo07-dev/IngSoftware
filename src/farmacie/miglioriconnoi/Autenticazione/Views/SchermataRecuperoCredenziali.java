package farmacie.miglioriconnoi.Autenticazione.Views;

import farmacie.miglioriconnoi.Autenticazione.Control.*;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.TextField;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataRecuperoCredenziali {

    public static TextField emailText;
    public static Button recuperaCredenziali_Button;
    public static String mail = "";
    public static String password;

    public static Button GoBack;

    public SchermataRecuperoCredenziali() throws FileNotFoundException {
        JPanel fullView = new JPanel(new BorderLayout(0,200));
        JPanel northView = new JPanel(new BorderLayout());
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
        JPanel logoPanel = new JPanel(new FlowLayout());
        logoPanel.add(utenteImage);
        boxCenterPanel.add(logoPanel);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        emailText = new TextField(40, "Mail", 150, 30);
        emailText.setPreferredSize(new Dimension(150,30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(emailText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));

        recuperaCredenziali_Button = new Button("RECUPERA CREDENZIALI", 300,50);
        recuperaCredenziali_Button.changeFontButton("Arial",1,20);

        JPanel buttonRecupera_Panel = new JPanel(new FlowLayout());
        buttonRecupera_Panel.add(recuperaCredenziali_Button);
        boxCenterPanel.add(buttonRecupera_Panel);

        JPanel boxFlowLayout = new JPanel(new FlowLayout());
        boxFlowLayout.add(boxCenterPanel);
        sudView.add(boxFlowLayout,BorderLayout.CENTER);
        //FINE PARTE CENTRALE DELLA SCHERMATA


        fullView.add(northView,BorderLayout.NORTH);
        fullView.add(sudView,BorderLayout.CENTER);
        Main.schermataRecuperoCredenzialiPanel.add(fullView,BorderLayout.CENTER);

        ControlRecuperoCredenziali.setListeners();
    }

}

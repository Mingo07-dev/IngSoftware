import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SchermataRegistrazione {

    public static String mansione;
    private static JMenu mansioneButtonMenu;

    public SchermataRegistrazione() throws FileNotFoundException {
        JPanel fullView = new JPanel(new BorderLayout(0,20));
        JPanel northView = new JPanel(new BorderLayout());
        JPanel centerView = new JPanel(new BorderLayout());
        JPanel sudView = new JPanel(new BorderLayout());

        //PARTE NORD DELLA SCHERMATA
        Image logo = new Image("logo.png",100,100);
        Button GoBack = new Button("SchermataRegistrazione","TornaIndietro",150,50);
        GoBack.changeFontButton("Arial",1,15);
        GoBack.createListenerButtonGoBack();
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


        TextField emailText = new TextField(30, "Email", 150, 30);
        emailText.setPreferredSize(new Dimension(150,30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(emailText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        JPanel menuMansione = createMansioneMenu();
        menuMansione.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(menuMansione);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        TextField passwordText = new TextField(30, "Password", 150, 30);
        passwordText.setPreferredSize(new Dimension(150,30));
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(passwordText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));



        TextField confirmPasswordText = new TextField(30, "Confirm Password", 150, 30);
        confirmPasswordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(confirmPasswordText);

        JPanel boxFlowLayout = new JPanel(new FlowLayout());
        boxFlowLayout.add(boxCenterPanel);
        centerView.add(boxFlowLayout,BorderLayout.CENTER);
        //FINE PARTE CENTRALE DELLA SCHERMATA


        //PARTE SUD DELLA SCHERMATA
        Button registrazioneButton = new Button("SchermataRegistrazione", "REGISTRATI", 200,50);
        registrazioneButton.changeFontButton("Arial",1,20);
        JPanel registrazioneButtonPanel = new JPanel(new FlowLayout());
        registrazioneButtonPanel.add(registrazioneButton);

        JLabel campoErrato = new JLabel("");
        campoErrato.setForeground(Color.red);
        sudView.add(campoErrato, BorderLayout.NORTH);
        sudView.add(registrazioneButtonPanel,BorderLayout.SOUTH);
        //FINE PARTE SUD DELLA SCHERMATA

        fullView.add(northView,BorderLayout.NORTH);
        fullView.add(centerView,BorderLayout.CENTER);
        fullView.add(sudView,BorderLayout.SOUTH);
        Main.schermataRegistrazionePanel.add(fullView,BorderLayout.CENTER);
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
        buttonFarmacista.addActionListener(new SchermataRegistrazione.FarmacistaButtonActionListener());
        buttonCorriere.addActionListener(new SchermataRegistrazione.CorriereButtonActionListener());
        buttonImpiegatoAzienda.addActionListener(new SchermataRegistrazione.ImpiegatoAziendaActionListener());
        //FINE MENU A CASCATA

        return pannello;
    }

    private static class FarmacistaButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataFarmacista";
            mansioneButtonMenu.setText("Farmacista");
        }
    }
    private static class CorriereButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataCorriere";
            mansioneButtonMenu.setText("Corriere");
        }
    }
    private static class ImpiegatoAziendaActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataImpiegatoAzienda";
            mansioneButtonMenu.setText("Impiegato");
        }
    }
}

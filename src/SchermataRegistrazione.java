import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchermataRegistrazione {

    public static String mansione= "";
    private static JMenu mansioneButtonMenu;
    private static Button registrazioneButton;

    public static JLabel campoErrato;

    public static TextField emailText;
    public static PasswordField passwordText;
    public static PasswordField confirmPasswordText;

    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9]+@(.+)$");
    private static Matcher emailMatcher;

    private static final Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9](.+)$");
    private static Matcher passwordMatcher;

    private static String mail;
    private static boolean mansioneSelected = false;
    //public  static ResultSet mailResultSet;

    public static TextField nomeFarmaciaField;
    public static TextField indirizzoFarmaciaField;
    public static TextField recapitoTelefonicoField;
    public static String nomeFarmacia;
    public static String indirizzoFarmacia;
    public static String recapitoTelefonico;
    public static String mansioneString;


    public SchermataRegistrazione() throws FileNotFoundException {
        JPanel fullView = new JPanel(new BorderLayout(0,20));
        JPanel northView = new JPanel(new BorderLayout());
        JPanel centerView = new JPanel(new BorderLayout());
        JPanel sudView = new JPanel(new BorderLayout());

        //PARTE NORD DELLA SCHERMATA
        Image logo = new Image("logo.png",100,100);
        Button GoBack = new Button("SchermataRegistrazione","Torna Indietro",150,50);
        GoBack.changeFontButton("Arial",1,15);
        GoBack.createListenerButtonGoBackAutentication();
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


        emailText = new TextField(30, "Mail", 150, 30);
        emailText.setPreferredSize(new Dimension(150,30));
        emailText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(emailText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        JPanel menuMansione = createMansioneMenu();
        menuMansione.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(menuMansione);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));


        passwordText = new PasswordField(30, 150, 30);
        passwordText.setPreferredSize(new Dimension(150,30));
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(passwordText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));



        confirmPasswordText = new PasswordField(30, 150, 30);
        confirmPasswordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxCenterPanel.add(confirmPasswordText);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JCheckBox mostraPassword = new JCheckBox("Mostra Password");
        PasswordField.addListenerCheckboxMostraPasswordEConfirmPassword(mostraPassword,passwordText,confirmPasswordText);
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
        createListenerButtonRegistrati();
        campoErrato = new JLabel("");
        campoErrato.setForeground(Color.red);
        JPanel sudCenterView = new JPanel(new FlowLayout());
        sudCenterView.add(campoErrato, BorderLayout.CENTER);
        sudView.add(sudCenterView, BorderLayout.NORTH);
        sudView.add(registrazioneButtonPanel,BorderLayout.SOUTH);
        //FINE PARTE SUD DELLA SCHERMATA

        fullView.add(northView,BorderLayout.NORTH);
        fullView.add(centerView,BorderLayout.CENTER);
        fullView.add(sudView,BorderLayout.SOUTH);
        Main.schermataRegistrazionePanel.add(fullView,BorderLayout.CENTER);
    }

    public void createListenerButtonRegistrati( ){
        registrazioneButton.addActionListener(e -> {
            //CONTROLLA SE LA MANSIONE è STATA SELEZIONATA O NO
            if(!mansioneSelected){
                campoErrato.setText("");
                campoErrato.setText("NON è STATA SELEZIONATA NESSUNA MANSIONE");
            }
            else{
                //PRENDE I DATI DAI CAMPI DI TESTO E CONTROLLA CHE SIANO IDONEI E CHE LE PASSWORD COINCIDANO
                if(!passwordText.getText().equals(confirmPasswordText.getText())) {
                    campoErrato.setText("");
                    campoErrato.setText("LE PASSWORD NON COINCIDONO");
                }
                else
                {
                    emailMatcher = emailPattern.matcher(emailText.getText());
                    passwordMatcher = passwordPattern.matcher(passwordText.getText());
                    //SE IL CAMPO EMAIL NON è IDONEO MOSTRA LA SCRITTA IN ROSSO
                    if(!emailMatcher.matches()){
                        campoErrato.setText("");
                        campoErrato.setText("CAMPO EMAIL NON VALIDO, SONO AMMESSI SOLO CARATTERI E NUMERI PRIMA DELLA CHIOCCIOLA");
                    }
                    else{
                        //SE IL CAMPO PASSWORD NON è IDONEO MOSTRA LA SCRITTA IN ROSSO
                        if(!passwordMatcher.matches()){
                            campoErrato.setText("");
                            campoErrato.setText("CAMPO PASSWORD NON VALIDO, SONO AMMESSI SOLO CARATTERI E NUMERI");
                        }
                        else{
                            //SE I CAMPI EMAIL E PASSWORD SONO IDONEI CERCA NEL DATABASE SE ESISTE GIà UN UTENTE CON LA STESSA MAIL
                            //PRENDE LA MAIL DAL DATABASE, SE è NULL L'UTENTE NON SI è ANCORA REGISTRATO
                            campoErrato.setText("");
                            ResultSet mailResultSet = null;
                            try {
                                mailResultSet = Main.dbms_Azienda.getData("SELECT email from dbms_azienda.utente WHERE email = '" + SchermataRegistrazione.emailText.getText() + "';");
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                ex.printStackTrace();
                            }

                            try {
                                if (mailResultSet.next() != false) {
                                    try {
                                        mailResultSet.first();
                                        mail = mailResultSet.getString(1);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }


                            //FINE RICERCA NEL DATABASE
                            if(emailText.getText().equals(mail)){
                                //SE L'UTENTE è GIà REGISTRATO ALLORA MOSTRA UN ALLERT
                                JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE GIà REGISTRATO");
                            }
                            else{
                                //SE L'UTENTE NON è REGISTRATO LO REGISTRA
                                if(mansioneString.equals("Farmacista"))
                                {
                                    nomeFarmaciaField = new TextField(15, "Nome Farmacia", 50, 30);
                                    indirizzoFarmaciaField = new TextField(20, "Indirizzo, 0", 50, 30);
                                    recapitoTelefonicoField = new TextField(7, "Telefono", 50, 30);
                                    AlertMessage datiAggiuntivi = new AlertMessage();
                                    try {
                                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Indirizzo_farmacia`, `Nome_farmacia`, `Recapito_telefonico`, `Stato`) VALUES ('"+ emailText.getText() +"', '"+ passwordText.getText() +"', 'Farmacista', '"+ indirizzoFarmacia +"', '"+ nomeFarmacia +"', '"+ recapitoTelefonico +"', '0');");
                                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                    emailText.setText("Mail");
                                    passwordText.setText("Password");
                                    confirmPasswordText.setText("Conferma Password");
                                }
                                else{
                                    try {
                                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Stato`) VALUES ('" + emailText.getText() + "', '" + passwordText.getText() + "', '"+ mansioneString +"', '0');");
                                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                    JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE REGISTRATO CON SUCCESSO");
                                    emailText.setText("Mail");
                                    passwordText.setText("Password");
                                    confirmPasswordText.setText("Conferma Password");
                                }
                            }
                        }
                    }
                }
            }
        });
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
            mansioneString = "Farmacista";
            mansioneButtonMenu.setText("Farmacista");
            mansioneSelected = true;
        }
    }
    private static class CorriereButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataCorriere";
            mansioneString = "Corriere";
            mansioneButtonMenu.setText(mansioneString);
            mansioneSelected = true;
        }
    }
    private static class ImpiegatoAziendaActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataImpiegatoAzienda";
            mansioneString = "Impiegato";
            mansioneButtonMenu.setText(mansioneString);
            mansioneSelected = true;
        }
    }
}


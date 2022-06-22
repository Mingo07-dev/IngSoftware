import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataLogin {

    public static TextField emailField;
    public static PasswordField passwordField;
    private static Button buttonLogin;
    public static String email;
    public static String mansione;

    public SchermataLogin() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        //CREA L'IMMAGINE
        Image picLabel = new Image("logo.png",100,100);
        //FINE

        mainNorthPanel.add(picLabel, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        //CREA IL BOTTONE TORNA INDIETRO
        Button buttonTornaIndietro = new Button("SchermataLogin", "Torna Indietro", 150,50);
        buttonTornaIndietro.changeFontButton("Arial", 1,15);
        buttonTornaIndietro.createListenerButtonGoBack();
        //FINE

        mainUserOptionsPanel.add(buttonTornaIndietro);


        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        JPanel centerBox = new JPanel(new FlowLayout());
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        Image utente = new Image("utente.png", 200,200);

        utente.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(utente);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));

        emailField = new TextField(30, "Mail", 150,30);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(emailField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,40)));

        passwordField = new PasswordField(20, 150,30);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(passwordField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JCheckBox mostraPassword = new JCheckBox("Mostra Password");
        PasswordField.addListenerCheckboxMostraPassword(mostraPassword,passwordField);

        mostraPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(mostraPassword);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));


        //CREA IL BOTTONE LOGIN
        buttonLogin = new Button("SchermataLogin","Login",150,50);
        buttonLogin.changeFontButton("Arial", 1, 25);
        createListenerButtonLogin();
        //FINE

        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonLogin);

        centerBox.add(mainCenterPanel);
        mainPanel.add(centerBox, BorderLayout.CENTER);

        SchermataAutenticazione.schermataLoginPanel.add(mainPanel, BorderLayout.CENTER);
    }

    public void createListenerButtonLogin(){
        buttonLogin.addActionListener(e -> {
            //PRENDE I DATI DAI CAMPI DI TESTO

            //PRENDE I DATI DAL DATABASE
            ResultSet queryResult1 = null;
            ResultSet queryResult2 = null;
            try {
                queryResult1 = Main.dbms_Azienda.getData("SELECT email from dbms_azienda.utente WHERE email = '" + SchermataLogin.emailField.getText() + "';");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }

            try {
                if(queryResult1.next() != false){
                    email = null;
                    try {
                        queryResult1.first();
                        email = queryResult1.getString(1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        queryResult2 = Main.dbms_Azienda.getData("SELECT dbms_azienda.utente.Password FROM dbms_azienda.utente WHERE dbms_azienda.utente.Email = '" + email + "' AND dbms_azienda.utente.Password = '" + SchermataLogin.passwordField.getText() + "';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    if(queryResult2.next() != false){
                        String password = null;
                        try {
                            queryResult2.first();
                            password = queryResult2.getString(1);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        ResultSet result = null;

                        try {
                            result = Main.dbms_Azienda.getData("SELECT Mansione FROM dbms_azienda.utente WHERE Email = '" + email + "' AND password = '" + password + "';");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            ex.printStackTrace();
                        }

                        mansione = "";

                        try {
                            result.first();
                            mansione = result.getString(1);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        //SE COMBACIA CAMBIA SCHERMATA
                        try {
                            Main.dbms_Azienda.setData("UPDATE `dbms_azienda`.`utente` SET `Stato` = '1' WHERE (`Email` = '"+email+"');;");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            ex.printStackTrace();
                        }
                        Main.cardLayout.show(Main.mainPanel, "Schermata" + mansione);
                        SchermataLogin.emailField.setText("Mail");
                        SchermataLogin.passwordField.setText("Password");

                        //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
                        //tornare indietro tramite apposito bottone
                        Button.lastView = "SchermataLogin";
                    } else {
                        JOptionPane.showMessageDialog(SchermataAutenticazione.schermataLoginPanel, "Password errata");
                    }
                } else {
                    JOptionPane.showMessageDialog(SchermataAutenticazione.schermataLoginPanel, "Non esiste alcun account associato a questa Email");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }
    
}

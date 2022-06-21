import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private final int width;
    private final int height;

    //lastView SERVE PER TENERE TRACCIA DELL'ULTIMA SCHERMATA VISITATA COSì DA POTER TORNARE INDIETRO
    public static String lastView = "SchermataAutenticazione";

    //COSTRUTTORE
    Button(String currentView, String buttonName, int width, int height){
        this.currentView = currentView;
        this.width = width;
        this.height = height;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };

    Button(String buttonName, int width, int height){
        this.width = width;
        this.height = height;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };



    //METODO PER CAMBIARE FONT E GRANDEZZA
    //ATTENZIONE: FONT STYLE --> 0 = PLAIN, 1 = BOLD, 2 = ITALIC
    public void changeFontButton(String fontName,int style, int size){

        this.setFont(new Font(fontName, style, size));

    }



    //CREAZIONE DEI LISTENERS

    public void createListenerButtonChangeView(String viewToShow ){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, viewToShow);


            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }


    public void createListenerButtonGoBack(){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, Button.lastView);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }

    public void createListenerButtonLogin(){
        this.addActionListener(e -> {
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
                    String email = null;
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

                        String mansione = "";

                        try {
                            result.first();
                            mansione = result.getString(1);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        //SE COMBACIA CAMBIA SCHERMATA
                        Main.cardLayout.show(Main.mainPanel, "Schermata" + mansione);
                        SchermataLogin.emailField.setText("Email");
                        SchermataLogin.passwordField.setText("Password");

                        //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
                        //tornare indietro tramite apposito bottone
                        Button.lastView = "" + this.currentView;
                    } else {
                        JOptionPane.showMessageDialog(Main.schermataLoginPanel, "Password errata");
                    }
                } else {
                    JOptionPane.showMessageDialog(Main.schermataLoginPanel, "Non esiste alcun account associato a questa Email");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }

    public void createListenerButtonHome(){
        this.addActionListener(e -> {
            ResultSet queryResult = null;
            try {
                queryResult = Main.dbms_Azienda.getData("SELECT dbms_azienda.utente.mansione FROM dbms_azienda.utente WHERE dbms_azienda.utente.email = '" + SchermataLogin.emailField.getText() + "';");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
            String mansione = "";

            try {
                queryResult.first();
                mansione = queryResult.getString(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            Main.cardLayout.show(Main.mainPanel, "Schermata" + mansione);

            Button.lastView = "" + this.currentView;
        });
    }

    public void createListenerButtonLogOut(){
        this.addActionListener(e -> {
            AlertMessage confirm = new AlertMessage("Conferma", "Annulla", "Sei sicuro di voler effettuare il Log out?");
            confirm.createListenerButtonConfermaLogOut();
        });
    }

}

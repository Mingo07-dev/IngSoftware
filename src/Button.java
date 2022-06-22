import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.validation.Schema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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

    public void createListenerButtonsAutenticazioneLogin(String viewToShow ) throws FileNotFoundException{
        this.addActionListener(e -> {
            Main.mainPanel.add(SchermataAutenticazione.schermataLoginPanel, "SchermataLogin");
            try {
                SchermataLogin schermataLogin = new SchermataLogin();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, viewToShow);
            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }
    public void createListenerButtonsAutenticazioneRegistrazione(String viewToShow ) throws FileNotFoundException{
        this.addActionListener(e -> {
            Main.mainPanel.add(SchermataAutenticazione.schermataRegistrazionePanel, "SchermataRegistrazione");
            try {
                SchermataRegistrazione schermataRegistrazione= new SchermataRegistrazione();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, viewToShow);
            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }
    public void createListenerButtonsAutenticazioneRecuperoCredenziali(String viewToShow ) throws FileNotFoundException{
        this.addActionListener(e -> {
        Main.mainPanel.add(SchermataAutenticazione.schermataRecuperoCredenzialiPanel,"SchermataRecuperoCredenziali");
        try {
            SchermataAutenticazione schermataAutenticazione = new SchermataAutenticazione();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        //mostra la nuova schermata
        Main.cardLayout.show(Main.mainPanel, viewToShow);
        //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
        //tornare indietro tramite apposito bottone
        Button.lastView = "" + this.currentView;
    });
        }



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

    public void createListenerButtonHome(){
        this.addActionListener(e -> {
            Main.cardLayout.show(Main.mainPanel, "Schermata" + SchermataLogin.mansione);
            Button.lastView = "" + this.currentView;
        });
    }

    public void createListenerButtonLogOut(){
        this.addActionListener(e -> {
            Frame frame = new Frame();
            Object[] options = {"Conferma",
                    "No, grazie"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Sei sicuro di voler effettuare il Logout?",
                    "LogOut",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(n == 0){
                try {
                    Main.dbms_Azienda.setData("UPDATE `dbms_azienda`.`utente` SET `Stato` = '0' WHERE (`Email` = '"+SchermataLogin.email+"');;");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         SQLException ex) {
                    ex.printStackTrace();
                }
                Main.cardLayout.show(Main.mainPanel, "SchermataAutenticazione");
                JOptionPane.showMessageDialog(Main.mainFrame, "Logout Effettuato");
            }
            else {
                frame.dispose();
            }
        });
    }

}

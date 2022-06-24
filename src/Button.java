import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.validation.Schema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private final int width;
    private final int height;

    public int Id_ordine;

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

    Button(String buttonName, int width, int height, int Id_ordine){
        this.width = width;
        this.height = height;
        this.Id_ordine = Id_ordine;

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

    public void createListenerButtonChangeViewSchermataScorte(String viewToShow ){
        this.addActionListener(e -> {
            Main.schermataScortePanel.removeAll();
            try {
                SchermataScorte schermataScorte = new SchermataScorte();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataScortePanel.repaint();
            Main.mainFrame.setVisible(true);
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, viewToShow);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }

    public void createListenerButtonConsegne(String viewToShow ){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            if(SchermataLogin.mansione.equals("Farmacista")){
                Main.schermataConsegnePanel.removeAll();
                try {
                    SchermataConsegne.aggiornaTabellaFarmacista();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.schermataConsegnePanel.repaint();
                Main.mainFrame.setVisible(true);
            }
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

    public void createListenerButtonGoBackAutenticazione(){
        this.addActionListener(e -> {
            //RICARICA LOGIN
            Main.schermataLoginPanel.removeAll();
            try {
                SchermataLogin schermataLogin = new SchermataLogin();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataLoginPanel.repaint();
            Main.mainFrame.setVisible(true);

            //RICARICA REGISTRAZIONE
            Main.schermataRegistrazionePanel.removeAll();
            try {
                SchermataRegistrazione schermataRegistrazione = new SchermataRegistrazione();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataRegistrazionePanel.repaint();
            Main.mainFrame.setVisible(true);

            //RICARICA RECUPERO CREDENZIALI
            Main.schermataRecuperoCredenzialiPanel.removeAll();
            try {
                SchermataRecuperoCredenziali schermataRecuperoCredenziali = new SchermataRecuperoCredenziali();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            Main.schermataRecuperoCredenzialiPanel.repaint();
            Main.mainFrame.setVisible(true);
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

    public void createListenerButtonAggiorna(String viewToShow, int n){
        this.addActionListener(e -> {

            int[] intarray = Table.getIntArray();
            int[] intArrayOld = Table.getIntArrayOldData();
            String[] stringNome = Table.getStringNome();
            Date[] stringDate = Table.getStringData();
            try {
                for(int i = 0; i < n; i++){
                    Main.dbms_Azienda.setData("UPDATE dbms_azienda.dettaglio_ordine SET Quantita = REPLACE(Quantita, '"+intArrayOld[i]+"', '"+intarray[i]+"') WHERE Id_ordine = '"+ SchermataModificaOrdine.Id_Ordine +"' AND Nome_farmaco = '"+stringNome[i]+"' AND Data_scadenza = '"+stringDate[i]+"';");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }


            Main.schermataModificaOrdinePanel.removeAll();
            try {
                SchermataModificaOrdine schermataModificaOrdine = new SchermataModificaOrdine(SchermataModificaOrdine.Id_Ordine);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataModificaOrdinePanel.repaint();
            Main.mainFrame.setVisible(true);

            Main.cardLayout.show(Main.mainPanel, viewToShow);
        });
    }

    public void createListenerButtonAggiornaPrenotazioneAutomatica(String viewToShow, int n){
        this.addActionListener(e -> {

            int[] intarray = Table.getIntArray();
            int[] intArrayOld = Table.getIntArrayOldData();
            String[] stringNome = Table.getStringNome();
            try {
                for(int i = 0; i < n; i++){
                    Main.dbms_Azienda.setData("UPDATE dbms_azienda.prenotazione_automatica SET Quantita = REPLACE(Quantita, '"+intArrayOld[i]+"', '"+intarray[i]+"') WHERE nome_farmaco = '"+stringNome[i]+"';");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }


            Main.schermataModificaPrenotazioneAutomaticaPanel.removeAll();
            try {
                SchermataModificaPrenotazioneAutomatica schermataModificaPrenotazioneAutomatica = new SchermataModificaPrenotazioneAutomatica();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataModificaPrenotazioneAutomaticaPanel.repaint();
            Main.mainFrame.setVisible(true);

            Main.cardLayout.show(Main.mainPanel, viewToShow);
        });
    }

    public void createListenerButtonAggiornaCaricoScorte(String viewToShow, int n){
        this.addActionListener(e -> {

            int[] intarray = Table.getIntArray();
            int[] intArrayOld = Table.getIntArrayOldData();
            String[] stringNome = Table.getStringNome();
            Frame frame = new Frame();
            Object[] options = {"Conferma",
                    "No, grazie"};
            String strings[] = {"hello", "whats'up", "how are you", "hi again", "poieroew", "aweiorèpaw", "èwoerèpwa", "èpwoaerèapweo", "èeaworèw", "èqwprawe"};
            int b = JOptionPane.showOptionDialog(frame,
                    "Sei sicuro di voler effettuare il caricamento delle seguenti scorte?",
                    "Carico scorte",  //titolo
                    JOptionPane.YES_NO_OPTION, //da cambiare se si vogliono più opzioni o meno
                    JOptionPane.QUESTION_MESSAGE, //per cambiare l'iconcina
                    null, //lasciare sempre cosi
                    strings,
                    options[0]); //puntatore alla prima opzione
            if(b == 0){
                //se è 0 significa che è stato premuto il primo bottone

                try {
                    for(int i = 0; i < n; i++){
                        Main.dbms_Farmacia.setData("UPDATE dbms_farmacia.elenco_scorte SET quantita_disponibile = REPLACE(quantita_disponibile, '" + intArrayOld[i] + "', '" + intarray[i] + "') WHERE nome_farmaco = '" + stringNome[i] + "';");
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                Main.schermataCaricoScortePanel.removeAll();
                try {
                    SchermataCaricoScorte schermataCaricoScorte = new SchermataCaricoScorte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.schermataCaricoScortePanel.repaint();
                Main.mainFrame.setVisible(true);

                Main.cardLayout.show(Main.mainPanel, viewToShow);
            }
            else {
                //se è 1 significa che è stato premuto il secondo bottone e cosi via
                frame.dispose();
            }
        });
    }


    public int getId_ordine() {
        return Id_ordine;
    }
}

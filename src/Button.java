import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.Result;
import javax.xml.validation.Schema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private final int width;
    private final int height;

    public int Id_ordine;
    public Date data_consegna;

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

    Button(String buttonName, int width, int height, int Id_ordine, Date data_consegna){
        this.width = width;
        this.height = height;
        this.Id_ordine = Id_ordine;
        this.data_consegna = data_consegna;

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
    public void createListenerButtonChangeViewListaOrdini(String viewToShow ){
        this.addActionListener(e -> {
            Main.schermataListaOrdiniPanel.removeAll();
            try {
                SchermataListaOrdini schermataListaOrdini = new SchermataListaOrdini();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataListaOrdiniPanel.repaint();
            Main.mainFrame.setVisible(true);
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


    public void createListenerButtonGoBack(String viewToShow){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, Button.lastView);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
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

    public void createListenerButtonAggiornaCaricoScorte(String viewToShow, int n, int id_ordine){
        this.addActionListener(e -> {
            AlertMessage riepilogo = new AlertMessage(id_ordine, n, Table.getIntArray(), Table.getIntArrayOldData(), Table.getStringNome(), Table.getPrincipioAttivo(), Table.getStringData());
        });
    }

    public void createListenerButtonAggiornaScaricoScorte(String viewToShow, int n){
        this.addActionListener(e -> {

            int[] intarray = Table.getIntArray();
            int[] intArrayOld = Table.getIntArrayOldData();
            String[] stringNome = Table.getStringNome();
            Date[] stringDate = Table.getStringData();
            try {
                for(int i = 0; i < n; i++){
                    Main.dbms_Azienda.setData("UPDATE dbms_farmacia.elenco_scorte SET quantita_disponibile = REPLACE(quantita_disponibile, '" + intArrayOld[i] + "', '" + (intArrayOld[i] - intarray[i]) + "') WHERE nome_Farmacia = '" + SchermataLogin.nomeFarmacia + "' AND nome_farmaco = '" + stringNome[i] + "' AND scadenza_farmaco = '" + stringDate[i] + "';");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }


            Main.schermataScortePanel.removeAll();
            try {
                SchermataScorte schermataScorte = new SchermataScorte();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataScortePanel.repaint();
            Main.mainFrame.setVisible(true);

            Main.cardLayout.show(Main.mainPanel, viewToShow);
        });
    }

    public void createListenerButtonPrenota(String viewToShow, int n){
        this.addActionListener(e -> {


            int[] preQuantitaOrdinate = Table.getIntArray();
            int[] preQuantitaDisponibili = Table.getIntArrayOldData();
            String[] preStringNome = Table.getStringNome();
            String[] prePrincipioAttivo = Table.getPrincipioAttivo();
            Date[] preDateScadenza = Table.getStringData();

            //TOGLI ZERI
            int[] zeros = trovaZeri(preQuantitaOrdinate);
            int[] quantitaOrdinate = removeZerosFromArrayInt(preQuantitaOrdinate);
            int[] quantitaDisponibili = removeZerosFromArrayInt(preQuantitaDisponibili);
            String[] stringNome = removeZerosFromArrayString(zeros, preStringNome);
            String[] principioAttivo = removeZerosFromArrayString(zeros, prePrincipioAttivo);
            Date[] dateScadenza = removeZerosFromArrayDate(zeros, preDateScadenza);

            //CONTROLLO DATA DI SCADENZA INFERIORE A 2 MESI
            boolean scadenza = false;

            for(int i = 0; i < quantitaOrdinate.length; i++){
                if(dateScadenza[i].toLocalDate().compareTo(LocalDate.now().plusMonths(2)) < 0){
                    scadenza = true;
                }
            }

            if(scadenza){
                JOptionPane.showMessageDialog(Main.mainFrame, "Attenzione: Nell'ordine corrente sono presenti farmaci con data di scadenza inferiore a 2 mesi.");
                scadenza = false;
            }
            // FINE

            //CONTROLLO DISPONIBILITà FARMACI

                // INIZIALIZZO UN ARRAY CHE REGISTRERà L'INDICE DEI FARMACI NON INTERAMENTE DISPONIBILI
            int qualeFarmacoNonEDisponibile[] = new int[quantitaOrdinate.length];
            for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                qualeFarmacoNonEDisponibile[i] = 0;
            }
                //IDENTIFICO SE CI SONO E QUALI SONO I FARMACI CHE NON SONO INTERAMENTE DISPONIBILI
            boolean nonDisponibile = false;
            for(int i = 0; i < quantitaOrdinate.length; i++){
                if(quantitaDisponibili[i] < quantitaOrdinate[i]){
                    qualeFarmacoNonEDisponibile[i] = 1;
                    nonDisponibile = true;
                }
            }

            if(nonDisponibile == false){
                AlertMessage tuttoDisponibile = new AlertMessage(quantitaOrdinate.length, quantitaOrdinate, quantitaDisponibili, stringNome, principioAttivo, dateScadenza);
            } else {
                int counter = 0;
                for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                    if(qualeFarmacoNonEDisponibile[i] == 1){
                        counter++;
                    }
                }
                int[] nuoveQuantitaOrdinate = new int[quantitaOrdinate.length];
                for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                    if(qualeFarmacoNonEDisponibile[i] == 1){
                        nuoveQuantitaOrdinate[i] = quantitaDisponibili[i];
                    } else {
                        nuoveQuantitaOrdinate[i] = quantitaOrdinate[i];
                    }
                }


                int k = 0;
                String[] nomeFarmaciResidui = new String[counter];
                for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                    if(qualeFarmacoNonEDisponibile[i] == 1){
                        nomeFarmaciResidui[k] = stringNome[i];
                        k++;
                    }
                }
                k = 0;
                String[] nomePrincipioAttivoFarmaciResidui = new String[counter];
                for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                    if(qualeFarmacoNonEDisponibile[i] == 1){
                        nomePrincipioAttivoFarmaciResidui[k] = principioAttivo[i];
                        k++;
                    }
                }
                k = 0;
                int[] quantitaResidue = new int[counter];
                for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                    if(qualeFarmacoNonEDisponibile[i] == 1){
                        quantitaResidue[k] = quantitaOrdinate[i] - quantitaDisponibili[i];
                        k++;
                    }
                }
                // MOSTRARE UN RIEPILOGO DIVISO IN FARMACI DISPONIBILI E FARMACI NON DISPONIBILI INTERAMENTE
                AlertMessage tuttoQuasiDisponibile = new AlertMessage(quantitaOrdinate.length, quantitaResidue.length, nuoveQuantitaOrdinate, quantitaDisponibili, stringNome, principioAttivo, dateScadenza, quantitaResidue, nomeFarmaciResidui, nomePrincipioAttivoFarmaciResidui);
            }
        });
    }

    public int getId_ordine() {
        return Id_ordine;
    }

    public int[] trovaZeri(int[] array){
        int[] zeri = new int[array.length];
        for(int i = 0; i < zeri.length; i++){
            if(array[i] == 0){
                zeri[i] = 1;
            }
        }
        return zeri;
    }

    public int[] removeZerosFromArrayInt(int[] arrayPartenza){
        int[] zeri = trovaZeri(arrayPartenza);
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        int[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new int[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else {
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

    public String[] removeZerosFromArrayString(int[] zeros, String[] arrayPartenza){
        int[] zeri = zeros;
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        String[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new String[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else{
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

    public Date[] removeZerosFromArrayDate(int[] zeros, Date[] arrayPartenza){
        int[] zeri = zeros;
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        Date[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new Date[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else{
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

}

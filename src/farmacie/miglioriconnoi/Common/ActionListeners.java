package farmacie.miglioriconnoi.Common;

import farmacie.miglioriconnoi.Autenticazione.Control.ControlLogin;
import farmacie.miglioriconnoi.Autenticazione.Control.ControlRecuperoCredenziali;
import farmacie.miglioriconnoi.Autenticazione.Control.ControlRegistrazione;
import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Autenticazione.Views.SchermataRegistrazione;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataModificaOrdine;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataModificaPrenotazioneAutomatica;
import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.ReloadPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public abstract class ActionListeners implements ActionListener {


    //LISTENER BOTTONE HOME
    public static ActionListener createActionListenerButtonHome(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MOSTRA LA HOME RELATIVA ALLA MANSIONE DELL'UTENTE
                Main.cardLayout.show(Main.mainPanel, "Schermata" + SchermataLogin.mansione);
            }
        };
        return AC;
    }

    //LISTENER BOTTONE TORNA INDIETRO
    public static ActionListener createActionListenerButtonGoBack(String viewToShow){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MOSTRA LA SCHERMATA PRECEDENTE
                Main.mainFrame.setVisible(true);
                Main.cardLayout.show(Main.mainPanel, viewToShow);
            }
        };
        return AC;
    }

    //LISTENER BOTTONE LOGOUT
    public static ActionListener createActionListenerButtonLogout(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        };
        return AC;
    }

    //LISTENER MOSTRA REGISTRAZIONE
    public static ActionListener createListenerShowRegistrazione(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA REGISTRAZIONE
                try {
                    ReloadPage.reloadRegistrazione();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                //MOSTRA REGISTRAZIONE
                Main.cardLayout.show(Main.mainPanel, "SchermataRegistrazione");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA LOGIN
    public static ActionListener createListenerShowLogin(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA LOGIN
                try {
                    ReloadPage.reloadLogin();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                //MOSTRA LOGIN
                Main.cardLayout.show(Main.mainPanel, "SchermataLogin");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA RECUPERO CREDENZIALI
    public static ActionListener createListenerShowRecuperoCredenziali(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA RECUPERO CREDENZIALI
                try {
                    ReloadPage.reloadRecuperoCredenziali();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                //MOSTRA RECUPERO CREDENZIALI
                Main.cardLayout.show(Main.mainPanel, "SchermataRecuperoCredenziali");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE LOGIN DELLA SCHERMATA LOGIN
    public static ActionListener createListenerButtonLogin(){
        ActionListener AC_Login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PRENDE I DATI DALLA SCHERMATA
                String email_Insert = ControlLogin.getFormLogin();

                //VERIFICA I DATI INSERITI CON I DATI PRESENTI NEL DATABASE ED EFFETTUA L'EVENTUALE LOGIN
                ControlLogin.inviaVerificaDati(email_Insert);
            }
        } ;

        return AC_Login;
    }

    //LISTENER BOTTONE REGISTRATI DELLA SCHERMATA REGISTRAZIONE
    public static ActionListener createListenerButtonRegistrati(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlRegistrazione.registraUtente();
            }
        };
        return AC;
    }

    //LISTENER BOTTONI MENU MANSIONE
    public static ActionListener MenuMansioneButton(String schermataMansione, String mansioneS){
        ActionListeners AC = new ActionListeners() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchermataRegistrazione.mansione = schermataMansione;
                SchermataRegistrazione.mansioneString = mansioneS;
                SchermataRegistrazione.mansioneButtonMenu.setText(mansioneS);
                SchermataRegistrazione.mansioneSelected = true;
            }
        };
        return  AC;
    }

    //LISTENER BOTTONE RECUPERA CREDENZIALI
    public static ActionListener createListenerButtonRecuperaCredenziali(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlRecuperoCredenziali.recuperaCredenziali();
            }
        };
        return AC;
    }

    //LISTENER BOTTONE MOSTRA PRENOTAZIONI
    public static ActionListener createListenerShowPrenotazione(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSchermataPrenotazione();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataPrenotazione");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA MODIFICA PRENOTAZIONI AUTOMATICA
    public static ActionListener createListenerShowModificaPrenotazioneAutomatica(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSchermataModificaPrenotazioneAutomatica();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataModificaPrenotazioneAutomatica");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA CARICO SCORTE
    public static ActionListener createListenerShowCaricoScorte(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSchermataConsegneFarmacistaPerCaricoScorte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA CONTROLLO SCORTE
    public static ActionListener createListenerShowControlloScorte(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadScorte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataScorte");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA VISUALIZZA LISTA ORDINI
    public static ActionListener createListenerShowVisualizzaListaOrdini(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSchermataVisualizzaListaOrdini();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataListaOrdini");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA CONSEGNE
    public static ActionListener createListenerShowConsegne(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadElencoConsegne();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA SEGNALAZIONI RISOLTE
    public static ActionListener createListenerShowSegnalazioniRisolte(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSegnalazioniRisolte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataSegnalazioniRisolte");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE MOSTRA SEGNALAZIONI RISOLTE
    public static ActionListener createListenerShowSegnalazioniIrrisolte(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReloadPage.reloadSegnalazioniIrrisolte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataSegnalazioniIrrisolte");
            }
        };

        return AC;
    }

    //LISTENER BOTTONE CARICA SCORTE
    public static ActionListener createListenerCaricoScorte(int n, int id_ordine, Table table){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlertMessage riepilogo = new AlertMessage(id_ordine, n, table.intArray, table.intArrayOldData, table.stringNome, table.principioAttivo, table.stringData);
            }
        };

        return AC;
    }

    //LISTENER BOTTONE SCARICA SCORTE
    public static ActionListener createListenerButtonScaricoScorte(int n, Table table){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] intarray = table.intArray;
                int[] intArrayOld = table.intArrayOldData;
                String[] stringNome = table.stringNome;
                Date[] stringDate = table.stringData;

                boolean flag = false;

                try {
                    for (int i = 0; i < n; i++) {
                        if (intarray[i] >= intArrayOld[i]) {
                            flag = true;
                            }
                    }
                    if(flag){
                        JOptionPane.showMessageDialog(Main.mainFrame, "Stai cercando di rimuovere più scorte di quelle che hai, stai attento e ricontrolla");
                        flag = false;
                    }
                    else{
                        for (int i = 0; i < n; i++) {
                            Main.dbms_Azienda.setData("UPDATE dbms_farmacia.elenco_scorte SET quantita_disponibile = REPLACE(quantita_disponibile, '" + intArrayOld[i] + "', '" + (intArrayOld[i] - intarray[i]) + "') WHERE nome_Farmacia = '" + SchermataLogin.nomeFarmacia + "' AND nome_farmaco = '" + stringNome[i] + "' AND scadenza_farmaco = '" + stringDate[i] + "';");
                        }
                    }
                    Main.dbms_Azienda.setData("DELETE FROM `dbms_farmacia`.`elenco_scorte` WHERE (`quantita_disponibile` = '0');");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }


                try {
                    ReloadPage.reloadScorte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataScorte");
            }
        };

        return AC;
    }

    public static ActionListener createListenerButtonAggiorna(String viewToShow, int n, Table table){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] intarray = table.intArray;
                int[] intArrayOld = table.intArrayOldData;
                String[] stringNome = table.stringNome;
                Date[] stringDate = table.stringData;
                try {
                    for(int i = 0; i < n; i++){
                        if(intarray[i] != -1)
                            Main.dbms_Azienda.setData("UPDATE dbms_azienda.dettaglio_ordine SET Quantita = REPLACE(Quantita, '"+intArrayOld[i]+"', '"+intarray[i]+"') WHERE Id_ordine = '"+ SchermataModificaOrdine.Id_Ordine +"' AND Nome_farmaco = '"+stringNome[i]+"' AND Data_scadenza = '"+stringDate[i]+"';");
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    ReloadPage.reloadModificaOrdine(SchermataModificaOrdine.Id_Ordine);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.cardLayout.show(Main.mainPanel, viewToShow);
            }
        };
        return AC;
    }

    public static ActionListener createListenerButtonAggiornaPrenotazioneAutomatica(String viewToShow, int n, Table table){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] intarray = table.intArray;
                int[] intArrayOld = table.intArrayOldData;
                String[] stringNome = table.stringNome;
                try {
                    for(int i = 0; i < n; i++){
                        if(intarray[i] != -1) {
                            Main.dbms_Azienda.setData("UPDATE dbms_azienda.prenotazione_automatica SET Quantita = REPLACE(Quantita, '" + intArrayOld[i] + "', '0') WHERE nome_farmaco = '" + stringNome[i] + "' AND nome_farmacia = '" + SchermataLogin.nomeFarmacia + "';");
                            Main.dbms_Azienda.setData("UPDATE dbms_azienda.prenotazione_automatica SET Quantita = REPLACE(Quantita, '0', '" + intarray[i] + "') WHERE nome_farmaco = '" + stringNome[i] + "' AND nome_farmacia = '" + SchermataLogin.nomeFarmacia + "';");
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    ReloadPage.reloadSchermataModificaPrenotazioneAutomatica();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, viewToShow);
            }
        };
        return AC;
    }

    public static ActionListener createListenerButtonPrenota(String viewToShow, int n, Table table, Button button){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] preQuantitaOrdinate = table.intArray;
                int[] preQuantitaDisponibili = table.intArrayOldData;
                String[] preStringNome = table.stringNome;
                String[] prePrincipioAttivo = table.principioAttivo;
                Date[] preDateScadenza = table.stringData;
                //TOGLI ZERI
                int[] zeros = button.trovaZeri(preQuantitaOrdinate);
                int[] quantitaOrdinate = button.removeZerosFromArrayInt2(zeros, preQuantitaOrdinate);
                int[] quantitaDisponibili = button.removeZerosFromArrayInt2(zeros, preQuantitaDisponibili);
                String[] stringNome = button.removeZerosFromArrayString(zeros, preStringNome);
                String[] principioAttivo = button.removeZerosFromArrayString(zeros, prePrincipioAttivo);
                Date[] dateScadenza = button.removeZerosFromArrayDate(zeros, preDateScadenza);

                if(quantitaOrdinate.length != 0){
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

                    //CONTROLLO DISPONIBILITÀ FARMACI

                    // INIZIALIZZO UN ARRAY CHE REGISTRERÀ L'INDICE DEI FARMACI NON INTERAMENTE DISPONIBILI
                    int[] qualeFarmacoNonEDisponibile = new int[quantitaOrdinate.length];
                    //IDENTIFICO SE CI SONO E QUALI SONO I FARMACI CHE NON SONO INTERAMENTE DISPONIBILI
                    boolean nonDisponibile = false;
                    int disponibilitàZero = 0;
                    for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                        if(quantitaDisponibili[i] < quantitaOrdinate[i]){
                            qualeFarmacoNonEDisponibile[i] = 1;
                            nonDisponibile = true;
                        }
                        if(quantitaDisponibili[i] == 0){
                            qualeFarmacoNonEDisponibile[i] = 2;
                            disponibilitàZero++;
                        }
                    }

                    if(nonDisponibile == false){
                        LocalDate dataConsegna = LocalDate.now().plusDays(3);
                        AlertMessage tuttoDisponibile = new AlertMessage(quantitaOrdinate.length, quantitaOrdinate, quantitaDisponibili, stringNome, principioAttivo, dateScadenza, dataConsegna);
                    } else {
                        int counter = 0;
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] == 1 || qualeFarmacoNonEDisponibile[i] == 2){
                                counter++;
                            }
                        }

                        int[] nuoveQuantitaOrdinate = new int[quantitaOrdinate.length-disponibilitàZero];

                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] == 0){
                                nuoveQuantitaOrdinate[i] = quantitaOrdinate[i];
                            }
                            else if (qualeFarmacoNonEDisponibile[i] == 1){
                                nuoveQuantitaOrdinate[i] = quantitaOrdinate[i] - (quantitaOrdinate[i] - quantitaDisponibili[i]);
                            }
                        }

                        int k = 0;
                        String[] nomeFarmaciResidui = new String[counter];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] == 1 || qualeFarmacoNonEDisponibile[i] == 2){
                                nomeFarmaciResidui[k] = stringNome[i];
                                k++;
                            }
                        }
                        k = 0;
                        String[] nomePrincipioAttivoFarmaciResidui = new String[counter];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] == 1 || qualeFarmacoNonEDisponibile[i] == 2){
                                nomePrincipioAttivoFarmaciResidui[k] = principioAttivo[i];
                                k++;
                            }
                        }
                        k = 0;
                        int[] quantitaResidue = new int[counter];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] == 1 || qualeFarmacoNonEDisponibile[i] == 2){
                                quantitaResidue[k] = quantitaOrdinate[i] - quantitaDisponibili[i];
                                k++;
                            }
                        }

                        String[] nuoveStringNome = new String[quantitaOrdinate.length-disponibilitàZero];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] != 2)
                                nuoveStringNome[i] = stringNome[i];
                        }
                        String[] nuovoPrincipioAttivo = new String[quantitaOrdinate.length-disponibilitàZero];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] != 2)
                                nuovoPrincipioAttivo[i] = principioAttivo[i];
                        }
                        Date[] nuovoDateScadenza = new Date[quantitaOrdinate.length-disponibilitàZero];
                        for(int i = 0; i < qualeFarmacoNonEDisponibile.length; i++){
                            if(qualeFarmacoNonEDisponibile[i] != 2)
                                nuovoDateScadenza[i] = dateScadenza[i];
                        }


                        LocalDate nuovaDataConsegna = LocalDate.now().plusDays(3);
                        LocalDate nuovaDataConsegnaResidua = LocalDate.now().plusDays(3).plusMonths(1);
                        LocalDate nuovaDataScadenzaResidua = LocalDate.now().plusMonths(1).plusYears(1);

                        // MOSTRARE UN RIEPILOGO DIVISO IN FARMACI DISPONIBILI E FARMACI NON DISPONIBILI INTERAMENTE
                        AlertMessage tuttoQuasiDisponibile = new AlertMessage(nuoveQuantitaOrdinate.length, quantitaResidue.length, nuoveQuantitaOrdinate, quantitaDisponibili, nuoveStringNome, nuovoPrincipioAttivo, nuovoDateScadenza, quantitaResidue, nomeFarmaciResidui, nomePrincipioAttivoFarmaciResidui, nuovaDataConsegna,nuovaDataConsegnaResidua, nuovaDataScadenzaResidua);
                    }
                }
            }
        };
        return AC;
    }
}

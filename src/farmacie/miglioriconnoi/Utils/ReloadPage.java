package farmacie.miglioriconnoi.Utils;

import farmacie.miglioriconnoi.Autenticazione.Views.*;
import farmacie.miglioriconnoi.GestioneConsegne.Control.ControlConsegne;
import farmacie.miglioriconnoi.GestioneConsegne.Views.SchermataCaricoScorte;
import farmacie.miglioriconnoi.GestioneConsegne.Views.SchermataConsegne;
import farmacie.miglioriconnoi.GestioneMagazzini.Views.SchermataScorte;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.*;
import farmacie.miglioriconnoi.GestioneSegnalazioni.View.SchermataSegnalazioniIrrisolte;
import farmacie.miglioriconnoi.GestioneSegnalazioni.View.SchermataSegnalazioniRisolte;
import farmacie.miglioriconnoi.Main;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ReloadPage {


    //AUTENTICAZIONE
    public static void reloadRegistrazione() throws FileNotFoundException {
        Main.schermataRegistrazionePanel.removeAll();
        Main.schermataRegistrazione = new SchermataRegistrazione();
        Main.schermataRegistrazionePanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadLogin() throws FileNotFoundException {
        Main.schermataLoginPanel.removeAll();
        Main.schermataLogin = new SchermataLogin();
        Main.schermataLoginPanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadRecuperoCredenziali() throws FileNotFoundException {
        Main.schermataRecuperoCredenzialiPanel.removeAll();
        Main.schermataRecuperoCredenziali = new SchermataRecuperoCredenziali();
        Main.schermataRecuperoCredenzialiPanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadSchermataFarmacista() throws FileNotFoundException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Main.schermataFarmacistaPanel.removeAll();
        Main.schermataFarmacista = new SchermataFarmacista();
        Main.schermataFarmacistaPanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadSchermataCorriere() throws FileNotFoundException {
        Main.schermataCorrierePanel.removeAll();
        Main.schermataCorriere = new SchermataCorriere();
        Main.schermataCorrierePanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadSchermataImpiegatoAzienda() throws FileNotFoundException {
        Main.schermataImpiegatoAziendaPanel.removeAll();
        Main.schermataImpiegatoAzienda = new SchermataImpiegatoAzienda();
        Main.schermataImpiegatoAziendaPanel.repaint();
        Main.mainFrame.setVisible(true);
    }


    //FINE AUTENTICAZIONE

    //SCHERMATA FARMACISTA
    public static void reloadSchermataPrenotazione() throws FileNotFoundException {
        Main.schermataPrenotazionePanel.removeAll();
        Main.schermataPrenotazione = new SchermataPrenotazione();
        Main.schermataPrenotazionePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadSchermataModificaPrenotazioneAutomatica() throws FileNotFoundException {
        Main.schermataModificaPrenotazioneAutomaticaPanel.removeAll();
        Main.schermataModificaPrenotazioneAutomatica = new SchermataModificaPrenotazioneAutomatica();
        Main.schermataModificaPrenotazioneAutomaticaPanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadSchermataConsegneFarmacistaPerCaricoScorte() throws FileNotFoundException {
        Main.schermataConsegnePanel.removeAll();
        ControlConsegne.aggiornaTabellaFarmacista();
        Main.schermataConsegnePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadSchermataVisualizzaListaOrdini() throws FileNotFoundException {
        Main.schermataListaOrdiniPanel.removeAll();
        Main.schermataListaOrdini = new SchermataListaOrdini();
        Main.schermataListaOrdiniPanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadModificaOrdine(int id_ordine) throws FileNotFoundException {
        Main.schermataModificaOrdinePanel.removeAll();
        Main.schermataModificaOrdine = new SchermataModificaOrdine(id_ordine);
        Main.schermataModificaOrdinePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadDettaglioOrdine(int id_ordine) throws FileNotFoundException {
        Main.schermataVisualizzaDettaglioOrdinePanel.removeAll();
        Main.schermataVisualizzaDettaglioOrdine = new SchermataVisualizzaDettaglioOrdine(id_ordine);
        Main.schermataVisualizzaDettaglioOrdinePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadCaricoScorte(int id_ordine) throws FileNotFoundException {
        Main.schermataCaricoScortePanel.removeAll();
        Main.schermataCaricoScorte = new SchermataCaricoScorte(id_ordine);
        Main.schermataCaricoScortePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    public static void reloadScorte() throws FileNotFoundException {
        Main.schermataScortePanel.removeAll();
        Main.schermataScorte = new SchermataScorte();
        Main.schermataScortePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    //FINE FARMACISTA

    //CORRIERE
    public static void reloadElencoConsegne() throws FileNotFoundException {
        Main.schermataConsegnePanel.removeAll();
        Main.schermataConsegne = new SchermataConsegne();
        Main.schermataConsegnePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    //FINE CORRIERE

    //IMPIEGATO AZIENDA
    public static void reloadSegnalazioniRisolte() throws FileNotFoundException {
        Main.schermataSegnalazioniRisoltePanel.removeAll();
        Main.schermataSegnalazioniRisolte = new SchermataSegnalazioniRisolte();
        Main.schermataSegnalazioniRisoltePanel.repaint();
        Main.mainFrame.setVisible(true);
    }

    public static void reloadSegnalazioniIrrisolte() throws FileNotFoundException {
        Main.schermataSegnalazioniIrrisoltePanel.removeAll();
        Main.schermataSegnalazioniIrrisolte = new SchermataSegnalazioniIrrisolte();
        Main.schermataSegnalazioniIrrisoltePanel.repaint();
        Main.mainFrame.setVisible(true);
    }
    //FINE IMPIEGATO

}

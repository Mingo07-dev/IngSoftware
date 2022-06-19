import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class Main {
    public static DBMS dbms_Azienda;

    //CREAZIONE DEL FRAME(CIò CHE VERRà MOSTRATO A VIDEO)
    public static JFrame mainFrame = new JFrame("FarmacieMiglioriConNoi");

    //DICHIARAZIONI DELLE SCHERMATE RELATIVE ALL'UTENTE GENERICO
    public static JPanel schermataAutenticazionePanel;
    public static JPanel schermataRegistrazionePanel;
    public static JPanel schermataLoginPanel;
    public static JPanel schermataRecuperoCredenzialiPanel;
    //FINE


    //DICHIARAZIONI DELLE SCHERMATE RELATIVE AL FARMACISTA
    public static JPanel schermataFarmacistaPanel;
    public static JPanel schermataListaOrdiniPanel;
    public static JPanel schermataModificaOrdinePanel;
    public static JPanel schermataModificaPrenotazioneAutomaticaPanel;
    public static JPanel schermataPrenotazionePanel;
    public static JPanel schermataRiepilogoOrdinePanel;
    public static JPanel schermataScortePanel;
    public static JPanel schermataVisualizzaDettaglioOrdinePanel;
    //FINE


    //DICHIARAZIONI DELLE SCHERMATE RELATIVE AL CORRIERE
    public static JPanel schermataCorrierePanel;
    public static JPanel schermataConsegnePanel;
    //FINE


    //DICHIARAZIONI DELLE SCHERMATE RELATIVE ALL'IMPEGATO
    public static JPanel schermataImpiegatoAziendaPanel;
    public static JPanel schermataSegnalazioniPanel;
    //FINE


    //PANNELLO CHE VERRà INIZIALIZZATO COME CARD LAYOUT PER CONTENERE TUTTE LE SCHERMATE
    public static JPanel mainPanel;

    // CARD LAYOUT CHE CONTERRà LE SCHERMATE
    public static CardLayout cardLayout = new CardLayout();


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dbms_Azienda = new DBMS("test","root", "booba123");

        //INIZIALIZZAZIONE DEL CARD LAYOUT ALL'INTERNO DEL MAIN PANEL
        mainPanel = new JPanel(cardLayout);

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE ALL'UTENTE GENERICO
        schermataAutenticazionePanel = new JPanel(new BorderLayout(0,50));
        schermataRegistrazionePanel = new JPanel(new BorderLayout());
        schermataLoginPanel = new JPanel(new GridBagLayout());
        schermataRecuperoCredenzialiPanel = new JPanel(new BorderLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE AL FARMACISTA
        schermataFarmacistaPanel= new JPanel(new BorderLayout());
        schermataListaOrdiniPanel = new JPanel(new BorderLayout());
        schermataModificaOrdinePanel = new JPanel(new BorderLayout());
        schermataModificaPrenotazioneAutomaticaPanel = new JPanel(new BorderLayout());
        schermataPrenotazionePanel = new JPanel(new BorderLayout());
        schermataRiepilogoOrdinePanel = new JPanel(new BorderLayout());
        schermataScortePanel = new JPanel(new BorderLayout());
        schermataVisualizzaDettaglioOrdinePanel = new JPanel(new BorderLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE AL CORRIERE
        schermataCorrierePanel = new JPanel(new BorderLayout());
        schermataConsegnePanel = new JPanel(new BorderLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE ALL'IMPIEGATO
        schermataImpiegatoAziendaPanel = new JPanel(new BorderLayout());
        schermataSegnalazioniPanel = new JPanel(new BorderLayout());
        //FINE



        //AGGIUNTA DELLE SCHERMATE RELATIVE ALL'UTENTE GENERICO NEL CARD LAYOUT
        mainPanel.add(schermataAutenticazionePanel,"SchermataAutenticazione");
        mainPanel.add(schermataRegistrazionePanel, "SchermataRegistrazione");
        mainPanel.add(schermataLoginPanel, "SchermataLogin");
        mainPanel.add(schermataRecuperoCredenzialiPanel,"SchermataRecuperoCredenziali");
        //FINE

        //AGGIUNTA DELLE SCHERMATE RELATIVE AL FARMACISTA NEL CARD LAYOUT
        mainPanel.add(schermataFarmacistaPanel,"SchermataFarmacista");
        mainPanel.add(schermataListaOrdiniPanel,"SchermataListaOrdini");
        mainPanel.add(schermataModificaOrdinePanel,"SchermataModificaOrdine");
        mainPanel.add(schermataModificaPrenotazioneAutomaticaPanel, "SchermataModificaPrenotazioneAutomatica");
        mainPanel.add(schermataPrenotazionePanel,"SchermataPrenotazione");
        mainPanel.add(schermataRiepilogoOrdinePanel,"SchermataRiepilogoOrdine");
        mainPanel.add(schermataScortePanel,"SchermataScorte");
        mainPanel.add(schermataVisualizzaDettaglioOrdinePanel,"SchermataVisualizzaDettaglioOrdine");
        //FINE

        //AGGIUNTA DELLE SCHERMATE RELATIVE AL CORRIERE NEL CARD LAYOUT
        mainPanel.add(schermataCorrierePanel, "SchermataCorriere");
        mainPanel.add(schermataConsegnePanel,"SchermataConsegne");
        //FINE

        //AGGIUNTA DELLE SCHERMATE RELATIVE ALL'IMPIEGATO NEL CARD LAYOUT
        mainPanel.add(schermataImpiegatoAziendaPanel, "SchermataImpiegatoAzienda");
        mainPanel.add(schermataSegnalazioniPanel,"SchermataSegnalazioni");
        //FINE



        //CREAZIONE DELLE SCHERMATE RELATIVE ALL'UTENTE GENERICO
        SchermataAutenticazione schermataAutenticazione = new SchermataAutenticazione();
        SchermataRegistrazione schermataRegistrazione= new SchermataRegistrazione();
        SchermataLogin schermataLogin = new SchermataLogin();
        SchermataRecuperoCredenziali schermataRecuperoCredenziali = new SchermataRecuperoCredenziali();
        //FINE

        //CREAZIONE DELLE SCHERMATE RELATIVE AL FARMACISTA
        SchermataFarmacista schermataFarmacista = new SchermataFarmacista();
        SchermataListaOrdini schermataListaOrdini = new SchermataListaOrdini();
        SchermataModificaOrdine schermataModificaOrdine = new SchermataModificaOrdine();
        SchermataModificaPrenotazioneAutomatica schermataModificaPrenotazioneAutomatica = new SchermataModificaPrenotazioneAutomatica();
        SchermataPrenotazione schermataPrenotazione = new SchermataPrenotazione();
        SchermataRiepilogoOrdine schermataRiepilogoOrdine = new SchermataRiepilogoOrdine();
        SchermataScorte schermataScorte = new SchermataScorte();
        SchermataVisualizzaDettaglioOrdine schermataVisualizzaDettaglioOrdine = new SchermataVisualizzaDettaglioOrdine();
        //FINE

        //CREAZIONE DELLE SCHERMATE RELATIVE AL CORRIERE
        SchermataCorriere schermataCorriere = new SchermataCorriere();
        SchermataConsegne schermataConsegne = new SchermataConsegne();
        //FINE

        //CREAZIONE DELLE SCHERMATE RELATIVE ALL'IMPIEGATO
        SchermataImpiegatoAzienda schermataImpiegatoAzienda = new SchermataImpiegatoAzienda();
        SchermataSegnalazioni schermataSegnalazioni = new SchermataSegnalazioni();
        //FINE




        //VENGONO IMPOSTATE LE CARATTERISTICHE DEL FRAME:
        //QUANDO VIENE PREMUTA LA "X" DELLA SCHEDA IL PROGRAMMA DOVRà ESSERE TERMINATO
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //IMPOSTA IL LAYOUT INIZIALE COME BORDER
        mainFrame.setLayout(new BorderLayout());
        //AGGIUNGE AL FRAME LA CARD LAYOUT CONTENENTE TUTTE LE SCHERMATE
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        //IMPOSTA CHE QUANDO VIENE APERTO IL PROGRAMMA, VIENE SUBITO MOSTRATO A FINESTRA INTERA
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //IMPOSTA LA MINIMA GRANDEZZA A CUI SI PUò RIDIMENSIONARE LA FINESTRA A 1080x720
        mainFrame.setMinimumSize(new Dimension(1080, 720));
        //RENDE VISIBILE IL FRAME, LA PRIMA SCHERMATA MOSTRATA SARà LA SCHERMATA AUTENTICAZIONE
        mainFrame.setVisible(true);
    }

}
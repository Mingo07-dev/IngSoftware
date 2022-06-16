import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

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


    public static void main(String[] args){
        //INIZIALIZZAZIONE DEL CARD LAYOUT ALL'INTERNO DEL MAIN PANEL
        mainPanel = new JPanel(cardLayout);

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE ALL'UTENTE GENERICO
        schermataAutenticazionePanel = new JPanel(new GridBagLayout());
        schermataRegistrazionePanel = new JPanel(new GridBagLayout());
        schermataLoginPanel = new JPanel(new GridBagLayout());
        schermataRecuperoCredenzialiPanel = new JPanel(new GridBagLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE AL FARMACISTA
        schermataFarmacistaPanel= new JPanel(new GridBagLayout());
        schermataListaOrdiniPanel = new JPanel(new GridBagLayout());
        schermataModificaOrdinePanel = new JPanel(new GridBagLayout());
        schermataModificaPrenotazioneAutomaticaPanel = new JPanel(new GridBagLayout());
        schermataPrenotazionePanel = new JPanel(new GridBagLayout());
        schermataRiepilogoOrdinePanel = new JPanel(new GridBagLayout());
        schermataScortePanel = new JPanel(new GridBagLayout());
        schermataVisualizzaDettaglioOrdinePanel = new JPanel(new GridBagLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE AL CORRIERE
        schermataCorrierePanel = new JPanel(new GridBagLayout());
        schermataConsegnePanel = new JPanel(new GridBagLayout());
        //FINE

        //INIZIALIZZAZIONE DELLE SCHERMATE RELATIVE ALL'IMPIEGATO
        schermataImpiegatoAziendaPanel = new JPanel(new GridBagLayout());
        schermataSegnalazioniPanel = new JPanel(new GridBagLayout());
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



        //CREAZIONE DEL FRAME(CIò CHE VERRà MOSTRATO A VIDEO)
        JFrame mainFrame = new JFrame("FarmaciePiùBelleConNoi");

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
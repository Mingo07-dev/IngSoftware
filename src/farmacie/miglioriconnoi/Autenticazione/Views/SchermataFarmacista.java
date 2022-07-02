package farmacie.miglioriconnoi.Autenticazione.Views;


import farmacie.miglioriconnoi.Autenticazione.Control.ControlFarmacista;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class SchermataFarmacista  {
    public static Button bottoneLogOut;
    public static Button prenotaFarmaci;
    public static Button modificaPrenotazioneAutomatica;
    public static Button caricoScorte;
    public static Button controlloScorte;
    public static Button visualizzaListaOrdini;
    public SchermataFarmacista() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        // BOTTONE LOG OUT E IMMAGINE DEL LOGO HANNO LA STESSA DIMENSIONE PER ORA
        bottoneLogOut = new Button("Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        mainUserOptionsPanel.add(bottoneLogOut);
        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        //FLOW CENTRALE
        JPanel mainCenterBorder = new JPanel(new BorderLayout(0,0));
        JPanel mainCenterFlow = new JPanel(new FlowLayout());

        //BOX DI SINISTRA
        JPanel boxLeft = new JPanel();
        boxLeft.setLayout(new BoxLayout(boxLeft, BoxLayout.PAGE_AXIS));
        //BOX AL CENTRO
        JPanel boxCenter = new JPanel();
        boxCenter.setLayout(new BoxLayout(boxCenter, BoxLayout.PAGE_AXIS));
        //FLOW A DESTRA
        JPanel mainCenterEASTFlow = new JPanel(new FlowLayout());

        //BOTTONI SINISTRA
        prenotaFarmaci = new Button("Prenota Farmaci", 300,50);
        prenotaFarmaci.changeFontButton("Arial",1, 25);

        visualizzaListaOrdini = new Button("Visualizza Lista Ordini", 300,50);
        visualizzaListaOrdini.changeFontButton("Arial",1, 25);

        boxLeft.add(prenotaFarmaci);
        boxLeft.add(visualizzaListaOrdini);
        //BOTTONI CENTRALI
        caricoScorte = new Button("Carico Scorte", 300,50);
        caricoScorte.changeFontButton("Arial",1, 25);

        controlloScorte = new Button("Controllo Scorte", 300,50);
        controlloScorte.changeFontButton("Arial",1, 25);

        boxCenter.add(caricoScorte);
        boxCenter.add(controlloScorte);

        //BOTTONE DI DESTRA
        modificaPrenotazioneAutomatica = new Button("Modifica Prenotazione Automatica", 300,50);
        modificaPrenotazioneAutomatica.changeFontButton("Arial",1, 15);
        mainCenterEASTFlow.add(modificaPrenotazioneAutomatica);

        mainCenterBorder.add(boxLeft,BorderLayout.WEST);
        mainCenterBorder.add(boxCenter,BorderLayout.CENTER);
        mainCenterBorder.add(mainCenterEASTFlow,BorderLayout.EAST);
        mainCenterFlow.add(mainCenterBorder);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);


        Main.schermataFarmacistaPanel.add(mainPanel,BorderLayout.CENTER);

        ControlFarmacista.setListeners();
    }
}

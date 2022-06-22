import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class SchermataFarmacista  {
    public SchermataFarmacista() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        // BOTTONE LOG OUT E IMMAGINE DEL LOGO HANNO LA STESSA DIMENSIONE PER ORA
        Button bottoneLogOut = new Button("SchermataFarmacista","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();
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
        JPanel mainCenterEASTFlow = new JPanel();

        //BOTTONI SINISTRA
        Button prenotaFarmaci = new Button("SchermataFarmacista",  "Prenota Farmaci", 300,50);
        prenotaFarmaci.changeFontButton("Arial",1, 25);
        Button modificaPrenotazioneAutomatica = new Button("SchermataFarmacista",  "Modifica Prenotazione Automatica", 300,50);
        modificaPrenotazioneAutomatica.changeFontButton("Arial",1, 25);
        boxLeft.add(prenotaFarmaci);
        boxLeft.add(modificaPrenotazioneAutomatica);
        //BOTTONI CENTRALI
        Button caricoScorte = new Button("SchermataFarmacista",  "Carico Scorte", 300,50);
        caricoScorte.changeFontButton("Arial",1, 25);
        Button controlloScorte = new Button("SchermataFarmacista",  "Controllo Scorte", 300,50);
        controlloScorte.changeFontButton("Arial",1, 25);
        boxCenter.add(caricoScorte);
        boxCenter.add(controlloScorte);

        //BOTTONE DI DESTRA
        Button visualizzaListaOrdini = new Button("SchermataFarmacista",  "Visualizza Lista Ordini", 300,50);
        visualizzaListaOrdini.changeFontButton("Arial",1, 25);
        mainCenterEASTFlow.add(visualizzaListaOrdini);

        mainCenterBorder.add(boxLeft,BorderLayout.WEST);
        mainCenterBorder.add(boxCenter,BorderLayout.CENTER);
        mainCenterBorder.add(mainCenterEASTFlow,BorderLayout.EAST);
        mainCenterFlow.add(mainCenterBorder);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);



        Main.schermataFarmacistaPanel.add(mainPanel,BorderLayout.CENTER);
    }
}

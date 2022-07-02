package farmacie.miglioriconnoi.GestionePrenotazioni.Views;


import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.GestionePrenotazioni.Control.ControlModificaOrdine;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataModificaOrdine {
    public static int Id_Ordine;
    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;
    public static Button buttonAggiorna;
    public static JScrollPane sp;
    public static Table table = null;
    public SchermataModificaOrdine(int Id_ordine) throws FileNotFoundException {
        this.Id_Ordine = Id_ordine;
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        bottoneTornaIndietro = new Button("Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        bottoneTornaAllaHome = new Button("Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        bottoneLogOut = new Button("Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        sp = new JScrollPane();

        ControlModificaOrdine.fillTable();

        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        buttonAggiorna = new Button("Aggiorna", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);

        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        ControlModificaOrdine.setListeners();
        Main.schermataModificaOrdinePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

package farmacie.miglioriconnoi.GestioneConsegne.Views;


import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.GestioneConsegne.Control.ControlCaricoScorte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataCaricoScorte {

    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;
    public static Button buttonAggiorna;
    public static Table tableCarico;
    public static int Id_Ordine;
    public static JScrollPane sp;

    public SchermataCaricoScorte(int Id_ordine) throws FileNotFoundException {
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

        JPanel mainNorthLabelPanel = new JPanel(new FlowLayout());
        JLabel orderLabel = new JLabel("Ordine numero: " + this.Id_Ordine);

        mainNorthLabelPanel.add(orderLabel);

        mainNorthPanel.add(mainNorthLabelPanel, BorderLayout.CENTER);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        sp = new JScrollPane();

        tableCarico = null;
        ControlCaricoScorte.fillTable();
        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        buttonAggiorna = new Button("Carica Scorte", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);

        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        ControlCaricoScorte.setListeners();

        Main.schermataCaricoScortePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

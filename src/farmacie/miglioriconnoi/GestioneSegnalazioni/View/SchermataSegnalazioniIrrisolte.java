package farmacie.miglioriconnoi.GestioneSegnalazioni.View;


import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.GestioneSegnalazioni.Control.ControlSegnalazioniIrrisolte;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataSegnalazioniIrrisolte {
    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;
    public static JScrollPane sp;
    public SchermataSegnalazioniIrrisolte() throws FileNotFoundException {
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
        ControlSegnalazioniIrrisolte.fillTable();

        mainPanel.add(sp, BorderLayout.CENTER);

        ControlSegnalazioniIrrisolte.setListeners();
        Main.schermataSegnalazioniIrrisoltePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

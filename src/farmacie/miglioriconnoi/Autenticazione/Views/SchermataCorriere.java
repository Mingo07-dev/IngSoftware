package farmacie.miglioriconnoi.Autenticazione.Views;


import farmacie.miglioriconnoi.Autenticazione.Control.ControlCorriere;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;


import java.io.FileNotFoundException;

public class SchermataCorriere {
    public static Button bottoneLogOut;
    public static Button buttonVisualizzaElencoConsegne;
    public SchermataCorriere() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        bottoneLogOut = new Button("Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());
        mainUserOptionsPanel.add(bottoneLogOut);
        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        JPanel mainCenterFlow = new JPanel(new FlowLayout());


        buttonVisualizzaElencoConsegne = new Button("Visualizza Elenco Consegne", 400,50);
        buttonVisualizzaElencoConsegne.changeFontButton("Arial", 1,15);

        mainCenterFlow.add(buttonVisualizzaElencoConsegne);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);

        Main.schermataCorrierePanel.add(mainPanel, BorderLayout.CENTER);

        ControlCorriere.setListeners();
    }

}

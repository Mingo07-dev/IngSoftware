package farmacie.miglioriconnoi.Autenticazione;


import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;


import java.io.FileNotFoundException;

public class SchermataCorriere {
    public SchermataCorriere() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        Button bottoneLogOut = new Button("SchermataCorriere","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainNorthPanel.add(bottoneLogOut, BorderLayout.EAST);

        JPanel mainCenterFlow = new JPanel(new FlowLayout());


        Button buttonVisualizzaElencoConsegne = new Button("SchermataCorriere", "Visualizza Elenco Consegne", 400,50);
        buttonVisualizzaElencoConsegne.changeFontButton("Arial", 1,15);
        buttonVisualizzaElencoConsegne.createListenerButtonChangeViewConsegneCorriere("SchermataConsegne");

        mainCenterFlow.add(buttonVisualizzaElencoConsegne);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);

        Main.schermataCorrierePanel.add(mainPanel, BorderLayout.CENTER);
    }

}

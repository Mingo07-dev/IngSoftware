import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SchermataImpiegatoAzienda {
    public SchermataImpiegatoAzienda() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        Button bottoneLogOut = new Button("SchermataImpiegatoAzienda","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainNorthPanel.add(bottoneLogOut, BorderLayout.EAST);

        JPanel mainCenterFlow = new JPanel(new FlowLayout());


        Button buttonVisualizzaElencoSegnalazioni = new Button("SchermataCorriere", "Visualizza Elenco Segnalazioni", 400,50);
        buttonVisualizzaElencoSegnalazioni.changeFontButton("Arial", 1,15);
        buttonVisualizzaElencoSegnalazioni.createListenerButtonChangeView("SchermataSegnalazioni");

        mainCenterFlow.add(buttonVisualizzaElencoSegnalazioni);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);

        Main.schermataImpiegatoAziendaPanel.add(mainPanel, BorderLayout.CENTER);
        

    }
}

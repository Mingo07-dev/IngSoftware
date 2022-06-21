import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataConsegne {
    public SchermataConsegne() throws FileNotFoundException {

        JPanel schermataConsegneNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",150,70);

        schermataConsegneNorthPanel.add(image, BorderLayout.WEST);

        JPanel schermataConsegneNorthEastPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataConsegne","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack();

        schermataConsegneNorthEastPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataConsegne","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        schermataConsegneNorthEastPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataConsegne","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);

        schermataConsegneNorthEastPanel.add(bottoneLogOut);

        schermataConsegneNorthPanel.add(schermataConsegneNorthEastPanel, BorderLayout.EAST);

        Main.schermataConsegnePanel.add(schermataConsegneNorthPanel, BorderLayout.NORTH);

    }
}

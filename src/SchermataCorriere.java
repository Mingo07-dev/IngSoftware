import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SchermataCorriere {
    public SchermataCorriere(){

        JPanel schermataCorriereNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("res/logo.jpg",150,70);

        schermataCorriereNorthPanel.add(image, BorderLayout.WEST);

        // BOTTONE LOG OUT E IMMAGINE DEL LOGO HANNO LA STESSA DIMENSIONE PER ORA
        Button bottoneLogOut = new Button("SchermataCorriere","Log out", 150, 70);

        schermataCorriereNorthPanel.add(bottoneLogOut, BorderLayout.EAST);

        //DA CAMBIARE IN BOX LAYOUT QUANDO SI DOVRANNO INTRODURRE GLI ALTRI PULSANTI
        JPanel schermataCorriereCenterPanel = new JPanel(new FlowLayout());
        Button bottoneVisualizzaElencoConsegne = new Button("SchermataCorriere", "Visualizza Elenco Consegne", 400,50);
        bottoneVisualizzaElencoConsegne.changeFontButton("Arial", 1,25);
        bottoneVisualizzaElencoConsegne.createListenerButtonChangeView("SchermataConsegne");

        schermataCorriereCenterPanel.add(bottoneVisualizzaElencoConsegne);

        Main.schermataCorrierePanel.add(schermataCorriereNorthPanel, BorderLayout.NORTH);
        Main.schermataCorrierePanel.add(schermataCorriereCenterPanel, BorderLayout.CENTER);


    }

}

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
        GridBagConstraints c = new GridBagConstraints();

        Image image = new Image("res/logo.jpg",150,70);

        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;


        Main.schermataCorrierePanel.add(image, c);

        Button bottoneTornaIndietro = new Button("SchermataCorriere","Torna Indietro", 150, 70);
        bottoneTornaIndietro.createListenerButtonGoBack();
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridx = 10;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        Main.schermataCorrierePanel.add(bottoneTornaIndietro, c);

        Button bottoneVisualizzaElencoConsegne = new Button("SchermataCorriere", "Visualizza Elenco Consegne", 400,100);
        bottoneVisualizzaElencoConsegne.changeFontButton("Arial", 1,25);
        bottoneVisualizzaElencoConsegne.createListenerButtonChangeView("SchermataConsegne");
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridx = 4;
        c.gridy = 8;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.CENTER;
        Main.schermataCorrierePanel.add(bottoneVisualizzaElencoConsegne, c);


    }

}

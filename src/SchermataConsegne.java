import java.awt.*;

public class SchermataConsegne {
    public SchermataConsegne(){
        GridBagConstraints c = new GridBagConstraints();

        Image image = new Image("res/logo.jpg",125,63);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 805);
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        Main.schermataConsegnePanel.add(image, c);

        Button bottoneTornaIndietro = new Button("SchermataConsegne","Torna Indietro", 150, 70);
        bottoneTornaIndietro.createListenerButtonGoBack();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 0);

        Main.schermataConsegnePanel.add(bottoneTornaIndietro, c);

        Button bottoneTornaAllaHome = new Button("SchermataConsegne","Home", 150, 70);
        bottoneTornaAllaHome.createListenerButtonHome();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 0);

        Main.schermataConsegnePanel.add(bottoneTornaAllaHome, c);

        Button bottoneLogOut = new Button("SchermataConsegne","Log out", 150, 70);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 657, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        Main.schermataConsegnePanel.add(bottoneLogOut, c);

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SchermataAutenticazione {
    public SchermataAutenticazione(){


        GridBagConstraints c = new GridBagConstraints();

        JButton button1 = new JButton("logo");
        JButton button2 = new JButton("login");
        JButton button3 = new JButton("registrazione");
        JButton button4 = new JButton("recuperoCredenziali");



        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(40,40,40,40);
        c.ipady = 40;
        c.ipadx = 80;
        c.gridx = 0;
        c.gridy = 0;
        Main.schermataAutenticazionePanel.add(button1, c);

        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(40,40,40,40);
        c.ipady = 40;
        c.ipadx = 80;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        Main.schermataAutenticazionePanel.add(button2, c);

        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(40,40,40,40);
        c.ipady = 40;
        c.ipadx = 80;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        Main.schermataAutenticazionePanel.add(button3, c);

        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(40,40,40,40);
        c.ipady = 40;
        c.ipadx = 80;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 3;
        Main.schermataAutenticazionePanel.add(button4, c);
    }


}

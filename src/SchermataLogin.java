import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataLogin {

    public static String mansione;
    public SchermataLogin(){
        GridBagConstraints c = new GridBagConstraints();




        JMenuBar menuBar = new JMenuBar();
        JMenu mansioneButtonMenu = new JMenu("Mansione");
        JRadioButtonMenuItem buttonFarmacista = new JRadioButtonMenuItem("Farmacista");
        JRadioButtonMenuItem buttonCorriere = new JRadioButtonMenuItem("Corriere");
        JRadioButtonMenuItem buttonImpiegatoAzienda = new JRadioButtonMenuItem("Impiegato Azienda");
        ButtonGroup group = new ButtonGroup();
        group.add(buttonFarmacista);
        group.add(buttonCorriere);
        group.add(buttonImpiegatoAzienda);
        mansioneButtonMenu.add(buttonFarmacista);
        mansioneButtonMenu.add(buttonImpiegatoAzienda);
        mansioneButtonMenu.add(buttonCorriere);
        menuBar.add(mansioneButtonMenu);
        JPanel pannello = new JPanel(new FlowLayout());
        pannello.add(menuBar);

        buttonFarmacista.addActionListener(new FarmacistaButtonActionListener());
        buttonCorriere.addActionListener(new CorriereButtonActionListener());
        buttonImpiegatoAzienda.addActionListener(new ImpiegatoAziendaActionListener());

        c.gridx = 0;
        c.gridy = 0;

        Main.schermataLoginPanel.add(pannello,c);

        c.gridx = 0;
        c.gridy = 1;
        Button.loginButton(150,150,c);
    }

    private static class FarmacistaButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataFarmacista";
        }
    }
    private static class CorriereButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataCorriere";
        }
    }
    private static class ImpiegatoAziendaActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mansione = "SchermataImpiegatoAzienda";
        }
    }

}

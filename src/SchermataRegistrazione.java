import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataRegistrazione {

    public static String mansione;

    public SchermataRegistrazione(){

    }

    private static void createMansioneMenu(){

        //MENU A CASCATA PER SELEZIONARE LA MANSIONE
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
        mansioneButtonMenu.add(buttonCorriere);
        mansioneButtonMenu.add(buttonImpiegatoAzienda);
        menuBar.add(mansioneButtonMenu);
        JPanel pannello = new JPanel(new FlowLayout());
        pannello.add(menuBar);
        buttonFarmacista.addActionListener(new SchermataRegistrazione.FarmacistaButtonActionListener());
        buttonCorriere.addActionListener(new SchermataRegistrazione.CorriereButtonActionListener());
        buttonImpiegatoAzienda.addActionListener(new SchermataRegistrazione.ImpiegatoAziendaActionListener());
        //FINE MENU A CASCATA

        Main.schermataLoginPanel.add(pannello,BorderLayout.NORTH);
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

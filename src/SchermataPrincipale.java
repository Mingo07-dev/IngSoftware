import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale {


    public static void main(String[] args){
       inizialize();
    }
    public static void inizialize(){
        JButton buttonFarmacistaView = new JButton("Schermata Farmacista");
        JButton buttonCorriereView = new JButton("Schermata Corriere");
        Main.schermataPrincipalePanel.add(buttonFarmacistaView);
        Main.schermataPrincipalePanel.add(buttonCorriereView);
        buttonFarmacistaView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchermataFarmacista schermataFarmacista = new SchermataFarmacista();
                schermataFarmacista.inizialize();
                Main.cardLayout.show(Main.mainPanel, "2");
            }
        });
        buttonCorriereView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchermataCorriere schermataCorriere = new SchermataCorriere();
                schermataCorriere.inizialize();
                Main.cardLayout.show(Main.mainPanel, "4");
            }
        });
    }
}

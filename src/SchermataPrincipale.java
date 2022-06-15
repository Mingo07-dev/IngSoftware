import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale {


    public static void main(String[] args){
       inizialize();
    }
    public static void inizialize(){
        JButton buttonNextView = new JButton("Login");
        Main.schermataPrincipalePanel.add(buttonNextView, BorderLayout.NORTH);
        buttonNextView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchermataFarmacista schermataFarmacista = new SchermataFarmacista();
                schermataFarmacista.inizialize();
                Main.cardLayout.show(Main.mainPanel, "2");
            }
        });
    }
}

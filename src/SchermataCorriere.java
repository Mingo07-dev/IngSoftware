import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataCorriere {
    public static void main(String[] args){
        inizialize();
    }
    public static void inizialize(){
        JButton buttonNextView = new JButton("Torna alla home");
        Main.schermataCorrierePanel.add(buttonNextView, BorderLayout.NORTH);
        buttonNextView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardLayout.show(Main.mainPanel, "1");
            }
        });
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button {
    public static String lastView = "SchermataAutenticazione";

    public static void createButtonChangeView(String currentView, JPanel viewToAddOn,String viewToShow, String buttonName){

        JButton buttonChangeView = new JButton(buttonName);
        viewToAddOn.add(buttonChangeView);
        buttonChangeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardLayout.show(Main.mainPanel, viewToShow);
                lastView = "" + currentView;
            }
        });
    }

    public static void createButtonGoBack(String currentView, JPanel viewToAddOn){

        JButton buttonChangeView = new JButton("Torna Indietro");
        viewToAddOn.add(buttonChangeView);
        buttonChangeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.cardLayout.show(Main.mainPanel, lastView);
                lastView = "" + currentView;
            }
        });
    }


}

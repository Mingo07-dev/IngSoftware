package farmacie.miglioriconnoi.GestionePrenotazioni.Views;

import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.GestionePrenotazioni.Control.ControlListaOrdini;
import farmacie.miglioriconnoi.Utils.Ricerca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class SchermataListaOrdini {
    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;
    public static JScrollPane sp;
    public static Table tableOrdini = null;
    public SchermataListaOrdini() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        bottoneTornaIndietro = new Button("Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        bottoneTornaAllaHome = new Button("Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        bottoneLogOut = new Button("Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        sp = new JScrollPane();

        ControlListaOrdini.fillTable();

        mainPanel.add(sp, BorderLayout.CENTER);
        JPanel ricerca = new JPanel();
        JTextField ricercaField = new JTextField("Ricerca Ex: '2022-06-20'");
        ricercaField.setPreferredSize(new Dimension(150,30));
        ricercaField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ricercaField.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        ricercaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!ricercaField.getText().equals("")){
                        Ricerca.restoreTable(tableOrdini.n, tableOrdini,ricercaField, ricercaField.getText());
                        Ricerca.searchInTableText(ricercaField.getText(), tableOrdini.n, tableOrdini);
                    }
                    else{
                        Ricerca.restoreTable(tableOrdini.n, tableOrdini,ricercaField, "Ricerca Ex: '2022-06-20'");
                    }
                }
            }
        });
        ricerca.add(ricercaField);
        mainNorthPanel.add(ricerca, BorderLayout.CENTER);
        ControlListaOrdini.setListeners();
        Main.schermataListaOrdiniPanel.add(mainPanel, BorderLayout.CENTER);
    }
}

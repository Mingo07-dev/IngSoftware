package farmacie.miglioriconnoi.GestionePrenotazioni.Views;

import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.GestionePrenotazioni.Control.ControlPrenotazione;
import farmacie.miglioriconnoi.Utils.Ricerca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class SchermataPrenotazione {
    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;
    public static Button buttonAggiorna;
    public static JScrollPane sp;
    public static Table tablePrenotazione = null;

    public SchermataPrenotazione() throws FileNotFoundException {
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

        ControlPrenotazione.fillTable();

        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        buttonAggiorna = new Button("Prenota", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);

        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);


        //RICERCA
        JPanel ricerca = new JPanel();
        JTextField ricercaField = new JTextField("Ricerca Nome Farmaco Ex: 'Brufen'");
        ricercaField.setPreferredSize(new Dimension(200,30));
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
                        Ricerca.restoreTable(tablePrenotazione.n, tablePrenotazione,ricercaField, ricercaField.getText());
                        Ricerca.searchInTablePrenota(ricercaField.getText(), tablePrenotazione.n, tablePrenotazione);
                    }
                    else{
                        Ricerca.restoreTable(tablePrenotazione.n, tablePrenotazione,ricercaField, "Ricerca Nome Farmaco Ex: 'Brufen'");
                    }
                }
            }
        });
        ricerca.add(ricercaField);
        mainNorthPanel.add(ricerca, BorderLayout.CENTER);
        //FINE RICERCA

        ControlPrenotazione.setListeners();
        Main.schermataPrenotazionePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

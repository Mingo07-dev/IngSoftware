package farmacie.miglioriconnoi.GestionePrenotazioni;


import farmacie.miglioriconnoi.Autenticazione.SchermataLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;
import farmacie.miglioriconnoi.Utils.Ricerca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataListaOrdini {
    Table tableConsegne = null;
    public SchermataListaOrdini() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataListaOrdini","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack("SchermataFarmacista");

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataListaOrdini","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataListaOrdini","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Id ordine", "Data consegna ordine", "Modifica Ordine", "Annulla Ordine"};
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessun Ordine");
        ResultSet queryResult = null;
        tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT id_ordine, data_consegna_ordine  FROM dbms_azienda.lista_ordini WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND stato_ordine = '0';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    tableConsegne = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    tableConsegne.fillTable_threeButton(queryResult.getString(1), "Modifica ordine", "Annulla Ordine", 3,4,5);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                sp = new JScrollPane(tableConsegne);
            } else {
                sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        ricercaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!ricercaField.getText().equals(""))
                        Ricerca.searchInTableText(ricercaField.getText(), tableConsegne.n, tableConsegne);
                    else{
                        Ricerca.restoreTable(tableConsegne.n, tableConsegne,ricercaField);
                    }
                }
            }
        });
        ricerca.add(ricercaField);
        mainNorthPanel.add(ricerca, BorderLayout.CENTER);
        Main.schermataListaOrdiniPanel.add(mainPanel, BorderLayout.CENTER);
    }
}

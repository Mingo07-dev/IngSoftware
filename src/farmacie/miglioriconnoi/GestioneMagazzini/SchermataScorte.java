package farmacie.miglioriconnoi.GestioneMagazzini;


import farmacie.miglioriconnoi.Autenticazione.SchermataLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataScorte {
    public SchermataScorte() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataScorte","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack("SchermataFarmacista");

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataScorte","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataScorte","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Nome farmaco", "Principio attivo", "Quantita disponibile", "Scadenza farmaco", "Quantità da scaricare"};
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Scorte vuote");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco, principio_attivo, quantita_disponibile, scadenza_farmaco FROM dbms_farmacia.elenco_scorte WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND quantita_disponibile > 0;");
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
                    tableConsegne.fillTable_oneEditText();
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

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        Button buttonScaricaScorte = new Button("Scarica scorte", 150, 30);
        buttonScaricaScorte.changeFontButton("Arial", 1, 15);
        if(tableConsegne != null) {
            buttonScaricaScorte.createListenerButtonAggiornaScaricoScorte("SchermataScorte", tableConsegne.n);
        } else{
            buttonScaricaScorte.createListenerButtonAggiornaScaricoScorte("SchermataScorte", 0);
        }

        mainSouthPanel.add(buttonScaricaScorte);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        Main.schermataScortePanel.add(mainPanel, BorderLayout.CENTER);
    }
}
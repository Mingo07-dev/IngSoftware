package farmacie.miglioriconnoi.GestioneConsegne;


import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SchermataConsegne {
    public static int id_Ordine;
    public SchermataConsegne() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataConsegne","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack("SchermataCorriere");

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataConsegne","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataConsegne","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Nome farmacia", "Indirizzo", "Recapito Telefonico", "Id Ordine", "Conferma Consegna"};
        LocalDate date = LocalDate.now();
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessuna consegna prevista per oggi");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmacia, Indirizzo_postale, Recapito_telefonico, Id_ordine FROM dbms_azienda.elenco_consegne WHERE dbms_azienda.elenco_consegne.Data_consegna = '" + date + "' AND dbms_azienda.elenco_consegne.Stato_consegna = '0';");
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
                    tableConsegne.fillTable_oneButton("Conferma Avvenuta Consegna", 1);
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

        Main.schermataConsegnePanel.add(mainPanel, BorderLayout.CENTER);

    }









    //SCHERMATA CONSEGNE DEL FARMACISTA
    public static void aggiornaTabellaFarmacista() throws FileNotFoundException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png", 100, 100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataConsegne", "Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1, 15);
        bottoneTornaIndietro.createListenerButtonGoBack("SchermataFarmacista");

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataConsegne", "Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1, 15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataConsegne", "Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1, 15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Id Ordine", "Carica Scorte"};
        LocalDate date = LocalDate.now();
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessuna consegna prevista per oggi");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Id_ordine FROM dbms_azienda.elenco_consegne WHERE dbms_azienda.elenco_consegne.Data_consegna = '" + date + "' AND dbms_azienda.elenco_consegne.Stato_consegna = '1' AND dbms_azienda.elenco_consegne.nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if (queryResult.next() != false) {
                try {
                    id_Ordine = queryResult.getInt(1);
                    tableConsegne = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    tableConsegne.fillTable_oneButtonCaricoScorte("Carica", 2);
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

        Main.schermataConsegnePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

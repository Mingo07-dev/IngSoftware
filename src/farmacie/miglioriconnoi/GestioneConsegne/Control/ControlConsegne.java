package farmacie.miglioriconnoi.GestioneConsegne.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestioneConsegne.Views.SchermataConsegne;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControlConsegne {
    public static Button bottoneTornaIndietro;
    public static Button bottoneTornaAllaHome;
    public static Button bottoneLogOut;

    public static void setListenersConsegneCorriere(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataConsegne.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataCorriere");
        SchermataConsegne.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataConsegne.bottoneLogOut.addListener(AC_Logout);
    }

    public static void fillTable(){
        String headers[] = {"Nome farmacia", "Indirizzo", "Recapito Telefonico", "Id Ordine", "Conferma Consegna"};
        LocalDate date = LocalDate.now();
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
                    tableConsegne.fillTable_oneButton("Conferma Avvenuta Consegna", 1,4);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataConsegne.sp = new JScrollPane(tableConsegne);
            } else {
                SchermataConsegne.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setListenersConsegneFarmacista(){

        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        ControlConsegne.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataFarmacista");
        ControlConsegne.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        ControlConsegne.bottoneLogOut.addListener(AC_Logout);
    }


    //AGGIORNA SCHERMATA CONSEGNE PER IL FARMACISTA
    public static void aggiornaTabellaFarmacista() throws FileNotFoundException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png", 100, 100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        bottoneTornaIndietro = new Button( "Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1, 15);

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        bottoneTornaAllaHome = new Button( "Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1, 15);

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        bottoneLogOut = new Button( "Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1, 15);

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
                    SchermataConsegne.id_Ordine = queryResult.getInt(1);
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
        setListenersConsegneFarmacista();
        Main.schermataConsegnePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

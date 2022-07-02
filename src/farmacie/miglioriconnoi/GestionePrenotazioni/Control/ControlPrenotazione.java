package farmacie.miglioriconnoi.GestionePrenotazioni.Control;

import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataPrenotazione;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlPrenotazione {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataPrenotazione.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataFarmacista");
        SchermataPrenotazione.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataPrenotazione.bottoneLogOut.addListener(AC_Logout);

        if(SchermataPrenotazione.tablePrenotazione != null) {
            ActionListener AC_Prenota = ActionListeners.createListenerButtonPrenota("SchermataPrenotazione", SchermataPrenotazione.tablePrenotazione.n, SchermataPrenotazione.tablePrenotazione, SchermataPrenotazione.buttonAggiorna);
            SchermataPrenotazione.buttonAggiorna.addListener(AC_Prenota);
        } else{
            ActionListener AC_Prenota = ActionListeners.createListenerButtonPrenota("SchermataPrenotazione", 0, SchermataPrenotazione.tablePrenotazione, SchermataPrenotazione.buttonAggiorna);
            SchermataPrenotazione.buttonAggiorna.addListener(AC_Prenota);
        }
    }

    public static void fillTable(){
        String headers[] = {"Farmaco", "Principio attivo", "Data di scadenza", "Quantità ordine"};
        JLabel resultLabel = new JLabel("Nessuna Ordine");
        ResultSet queryResult = null;
        SchermataPrenotazione.tablePrenotazione = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco, principio_attivo,min(data_scadenza) AS data_scadenza,quantita_disponibile FROM dbms_azienda.farmaco group by nome_farmaco;");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataPrenotazione.tablePrenotazione = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataPrenotazione.tablePrenotazione.fillTable_oneEditTextPrenotazione();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataPrenotazione.sp = new JScrollPane(SchermataPrenotazione.tablePrenotazione);
            } else {
                SchermataPrenotazione.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package farmacie.miglioriconnoi.GestionePrenotazioni.Control;

import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataVisualizzaDettaglioOrdine;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlVisualizzaDettaglioOrdine {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataVisualizzaDettaglioOrdine.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataListaOrdini");
        SchermataVisualizzaDettaglioOrdine.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataVisualizzaDettaglioOrdine.bottoneLogOut.addListener(AC_Logout);
    }

    public static void fillTable(){
        String headers[] = {"Farmaco", "Principio attivo", "Quantità", "Data di scadenza"};
        JLabel resultLabel = new JLabel("Nessun Ordine");
        ResultSet queryResult = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmaco, Principio_attivo, Quantita, Data_scadenza FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ SchermataVisualizzaDettaglioOrdine.Id_Ordine +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataVisualizzaDettaglioOrdine.table = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataVisualizzaDettaglioOrdine.table.fillTable_onlyData();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataVisualizzaDettaglioOrdine.sp = new JScrollPane(SchermataVisualizzaDettaglioOrdine.table);
            } else {
                SchermataVisualizzaDettaglioOrdine.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

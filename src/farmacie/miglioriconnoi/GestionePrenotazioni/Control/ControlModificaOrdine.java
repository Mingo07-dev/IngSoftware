package farmacie.miglioriconnoi.GestionePrenotazioni.Control;

import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataModificaOrdine;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlModificaOrdine {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataModificaOrdine.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataListaOrdini");
        SchermataModificaOrdine.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataModificaOrdine.bottoneLogOut.addListener(AC_Logout);

        if(SchermataModificaOrdine.table != null) {
            ActionListener AC_Aggiorna = ActionListeners.createListenerButtonAggiorna("SchermataModificaOrdine", SchermataModificaOrdine.table.n, SchermataModificaOrdine.table);
            SchermataModificaOrdine.buttonAggiorna.addListener(AC_Aggiorna);
        } else{
            ActionListener AC_Aggiorna = ActionListeners.createListenerButtonAggiorna("SchermataModificaOrdine", 0, SchermataModificaOrdine.table);
            SchermataModificaOrdine.buttonAggiorna.addListener(AC_Aggiorna);
        }
    }
    public static void fillTable(){
        String headers[] = {"Farmaco", "Principio attivo", "Data di scadenza", "Quantità attuale", "Modifica Quantità"};
        JLabel resultLabel = new JLabel("Nessuna Ordine");
        ResultSet queryResult = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmaco, Principio_attivo, Data_scadenza, Quantita FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ SchermataModificaOrdine.Id_Ordine +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataModificaOrdine.table = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataModificaOrdine.table.fillTable_oneEditTextModificaOrdine();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataModificaOrdine.sp = new JScrollPane(SchermataModificaOrdine.table);
            } else {
                SchermataModificaOrdine.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

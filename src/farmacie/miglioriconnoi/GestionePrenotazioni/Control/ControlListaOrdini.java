package farmacie.miglioriconnoi.GestionePrenotazioni.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataListaOrdini;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlListaOrdini {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataListaOrdini.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataFarmacista");
        SchermataListaOrdini.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataListaOrdini.bottoneLogOut.addListener(AC_Logout);
    }

    public static void fillTable(){
        String headers[] = {"Id ordine", "Data consegna ordine", "Modifica Ordine", "Annulla Ordine"};
        JLabel resultLabel = new JLabel("Nessun Ordine");
        ResultSet queryResult = null;
        SchermataListaOrdini.tableOrdini = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT id_ordine, data_consegna_ordine  FROM dbms_azienda.lista_ordini WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND stato_ordine = '0';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next()) {
                try {
                    SchermataListaOrdini.tableOrdini = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataListaOrdini.tableOrdini.fillTable_threeButton(queryResult.getString(1), "Modifica ordine", "Annulla Ordine", 3,4,5);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataListaOrdini.sp = new JScrollPane(SchermataListaOrdini.tableOrdini);
            } else {
                SchermataListaOrdini.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

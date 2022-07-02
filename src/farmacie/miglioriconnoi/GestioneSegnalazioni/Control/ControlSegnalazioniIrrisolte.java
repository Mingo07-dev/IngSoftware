package farmacie.miglioriconnoi.GestioneSegnalazioni.Control;

import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestioneSegnalazioni.View.SchermataSegnalazioniIrrisolte;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlSegnalazioniIrrisolte {

    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataSegnalazioniIrrisolte.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataImpiegatoAzienda");
        SchermataSegnalazioniIrrisolte.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataSegnalazioniIrrisolte.bottoneLogOut.addListener(AC_Logout);

    }

    public static void fillTable(){
        String headers[] = {"Nome farmacia", "Recapito Telefonico", "Id Ordine", "Segnalazione Risolta"};
        JLabel resultLabel = new JLabel("Nessuna segnalazione");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmacia, Recapito_telefonico, Id_ordine FROM dbms_azienda.schermata_segnalazione WHERE dbms_azienda.schermata_segnalazione.Stato_segnalazione = '0';");
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
                    tableConsegne.fillTable_oneButton("Segnalazione risolta", 6,3);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataSegnalazioniIrrisolte.sp = new JScrollPane(tableConsegne);
            } else {
                SchermataSegnalazioniIrrisolte.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package farmacie.miglioriconnoi.GestioneConsegne.Control;

import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestioneConsegne.Views.SchermataCaricoScorte;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlCaricoScorte {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataCaricoScorte.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataConsegne");
        SchermataCaricoScorte.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataCaricoScorte.bottoneLogOut.addListener(AC_Logout);

        ActionListener AC_CaricoScorte;
        if(SchermataCaricoScorte.tableCarico != null) {
            AC_CaricoScorte = ActionListeners.createListenerCaricoScorte(SchermataCaricoScorte.tableCarico.n,SchermataCaricoScorte.Id_Ordine, SchermataCaricoScorte.tableCarico);
            SchermataCaricoScorte.buttonAggiorna.addListener(AC_CaricoScorte);
        } else{
            AC_CaricoScorte = ActionListeners.createListenerCaricoScorte(0,0, SchermataCaricoScorte.tableCarico);
            SchermataCaricoScorte.buttonAggiorna.addListener(AC_CaricoScorte);
        }
    }

    public static void fillTable(){
        String headers[] = {"Nome Farmaco","Principio Attivo", "Quantità prenotate", "Scadenza", "Quantità ricevute"};
        JLabel resultLabel = new JLabel("Nessuna ordine");
        ResultSet queryResult = null;
        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco,principio_attivo, quantita, data_scadenza FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ SchermataCaricoScorte.Id_Ordine +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataCaricoScorte.tableCarico = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataCaricoScorte.tableCarico.fillTable_oneEditTextCaricoScorte();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataCaricoScorte.sp = new JScrollPane(SchermataCaricoScorte.tableCarico);
            } else {
                SchermataCaricoScorte.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package farmacie.miglioriconnoi.GestioneMagazzini.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestioneMagazzini.Views.SchermataScorte;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlScorte {

    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataScorte.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataFarmacista");
        SchermataScorte.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataScorte.bottoneLogOut.addListener(AC_Logout);

        if(SchermataScorte.table != null) {
            ActionListener AC_Scarica = ActionListeners.createListenerButtonScaricoScorte(SchermataScorte.table.n, SchermataScorte.table);
            SchermataScorte.buttonScaricaScorte.addListener(AC_Scarica);
        } else{
            ActionListener AC_Scarica = ActionListeners.createListenerButtonScaricoScorte(0, SchermataScorte.table);
            SchermataScorte.buttonScaricaScorte.addListener(AC_Scarica);
        }
    }

    public static void fillTable(){
        String headers[] = {"Nome farmaco", "Principio attivo", "Quantita disponibile", "Scadenza farmaco", "Quantità da scaricare"};
        JLabel resultLabel = new JLabel("Scorte vuote");
        ResultSet queryResult = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco, principio_attivo, quantita_disponibile, scadenza_farmaco FROM dbms_farmacia.elenco_scorte WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND quantita_disponibile > 0;");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataScorte.table = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    SchermataScorte.table.fillTable_oneEditText();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataScorte.sp = new JScrollPane(SchermataScorte.table);
            } else {
                SchermataScorte.sp = new JScrollPane(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

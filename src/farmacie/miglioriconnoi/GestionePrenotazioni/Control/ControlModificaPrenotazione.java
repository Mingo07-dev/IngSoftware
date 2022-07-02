package farmacie.miglioriconnoi.GestionePrenotazioni.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataModificaPrenotazioneAutomatica;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlModificaPrenotazione {
    public static void setListeners(){
        ActionListener AC_Home = ActionListeners.createActionListenerButtonHome();
        SchermataModificaPrenotazioneAutomatica.bottoneTornaAllaHome.addListener(AC_Home);

        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataFarmacista");
        SchermataModificaPrenotazioneAutomatica.bottoneTornaIndietro.addListener(AC_GoBack);

        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataModificaPrenotazioneAutomatica.bottoneLogOut.addListener(AC_Logout);

        ActionListener AC;
        if(SchermataModificaPrenotazioneAutomatica.table == null){
            AC = ActionListeners.createListenerButtonAggiornaPrenotazioneAutomatica("SchermataModificaPrenotazioneAutomatica", 0, SchermataModificaPrenotazioneAutomatica.table);
            SchermataModificaPrenotazioneAutomatica.buttonAggiorna.addActionListener(AC);
        }
        else{
            AC = ActionListeners.createListenerButtonAggiornaPrenotazioneAutomatica("SchermataModificaPrenotazioneAutomatica", SchermataModificaPrenotazioneAutomatica.table.n, SchermataModificaPrenotazioneAutomatica.table);
            SchermataModificaPrenotazioneAutomatica.buttonAggiorna.addActionListener(AC);
        }
    }

    public static void fillTable(){
        String headers[] = {"Farmaco", "Principio attivo", "Quantità attuale", "Modifica Quantità"};
        ResultSet queryResult = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco, principio_attivo, quantita FROM dbms_azienda.prenotazione_automatica WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                try {
                    SchermataModificaPrenotazioneAutomatica.table = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    // NEL LISTENER LA PRIMA VOLTA CHE VIENE PREMUTO IL BOTTONE intarray, intArrayOld e stringNome vengono presi da Prenotazione (prova con sout)
                    SchermataModificaPrenotazioneAutomatica.table.fillTable_oneEditTextPrenotazioneAutomatica();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                SchermataModificaPrenotazioneAutomatica.sp = new JScrollPane(SchermataModificaPrenotazioneAutomatica.table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

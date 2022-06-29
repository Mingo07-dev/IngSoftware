package farmacie.miglioriconnoi.GestionePrenotazioni;


import farmacie.miglioriconnoi.Autenticazione.SchermataLogin;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.Common.Table;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataModificaPrenotazioneAutomatica {
    public SchermataModificaPrenotazioneAutomatica() throws FileNotFoundException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataModificaPrenotazioneAutomatica","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack("SchermataFarmacista");

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataModificaPrenotazioneAutomatica","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataModificaPrenotazioneAutomatica","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Farmaco", "Principio attivo", "Quantità attuale", "Modifica Quantità"};
        JScrollPane sp = new JScrollPane();
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco, principio_attivo, quantita FROM dbms_azienda.prenotazione_automatica WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
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
                    // NEL LISTENER LA PRIMA VOLTA CHE VIENE PREMUTO IL BOTTONE intarray, intArrayOld e stringNome vengono presi da Prenotazione (prova con sout)
                    tableConsegne.fillTable_oneEditTextPrenotazioneAutomatica();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                sp = new JScrollPane(tableConsegne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        Button buttonAggiorna = new Button("Aggiorna", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);
        if(tableConsegne == null){
            buttonAggiorna.createListenerButtonAggiornaPrenotazioneAutomatica("SchermataModificaPrenotazioneAutomatica", 0, tableConsegne);
        }
        else{
            buttonAggiorna.createListenerButtonAggiornaPrenotazioneAutomatica("SchermataModificaPrenotazioneAutomatica", tableConsegne.n, tableConsegne);
        }
        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        Main.schermataModificaPrenotazioneAutomaticaPanel.add(mainPanel, BorderLayout.CENTER);
    }
}

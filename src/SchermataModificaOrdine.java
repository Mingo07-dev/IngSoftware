import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataModificaOrdine {
    public SchermataModificaOrdine() throws FileNotFoundException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataModificaOrdine","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack();

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataModificaOrdine","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataModificaOrdine","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Farmaco", "Principio attivo", "Data di scadenza", "Quantità attuale", "Modifica Quantità"};
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessuna Ordine");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmaco, Principio_attivo, Data_scadenza, Quantita FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ SchermataListaOrdini.Id_ordine +"';");
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
                    tableConsegne.fillTable_oneEditText();
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

        JPanel mainSouthPanel = new JPanel(new FlowLayout());

        Button buttonAggiorna = new Button("Aggiorna", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);
            buttonAggiorna.createListenerButtonAggiorna("SchermataModificaOrdine",tableConsegne.n);

        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        Main.schermataModificaOrdinePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

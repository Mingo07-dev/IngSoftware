import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataVisualizzaDettaglioOrdine {
    public SchermataVisualizzaDettaglioOrdine() throws FileNotFoundException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataVisualizzaDettaglioOrdine","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack();

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataVisualizzaDettaglioOrdine","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataVisualizzaDettaglioOrdine","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Farmaco", "Principio attivo", "Quantità", "Data di scadenza"};
        JScrollPane sp = new JScrollPane();
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT Nome_farmaco, Principio_attivo, Quantita, Data_scadenza FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ SchermataListaOrdini.Id_ordine +"';");
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
                    tableConsegne.fillTable_onlyData();
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                sp = new JScrollPane(tableConsegne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainPanel.add(sp, BorderLayout.CENTER);

        Main.schermataVisualizzaDettaglioOrdinePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

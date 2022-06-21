import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SchermataConsegne {
    public SchermataConsegne() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataConsegne","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack();

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataConsegne","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataConsegne","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Nome farmacia", "Indirizzo", "Recapito Telefonico", "Stato Consegna", "Conferma Consegna"};
        LocalDate date = LocalDate.now();
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessuna consegna prevista");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.elenco_consegne WHERE dbms_azienda.elenco_consegne.Data_consegna = '" + date + "';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        try {
            if(queryResult.next() != false) {
                //queryResult.first();
                try {
                    tableConsegne = new Table(headers, queryResult);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    tableConsegne.fillTable_oneButton("Conferma Avvenuta Consegna", 1);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                Main.schermataConsegnePanel.add(tableConsegne, BorderLayout.CENTER);
                //sp.add(tableConsegne);
            } else {
                Main.schermataConsegnePanel.add(resultLabel, BorderLayout.CENTER);
                //sp.add(resultLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //mainPanel.add(sp, BorderLayout.CENTER);

        //Main.schermataConsegnePanel.add(mainPanel, BorderLayout.CENTER);

    }
}

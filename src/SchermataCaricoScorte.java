import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchermataCaricoScorte {
    public static int Id_Ordine;
    public SchermataCaricoScorte(int Id_ordine) throws FileNotFoundException {
        this.Id_Ordine = Id_ordine;
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        Button bottoneTornaIndietro = new Button("SchermataCaricoScorte","Torna Indietro", 150, 30);
        bottoneTornaIndietro.changeFontButton("Arial", 1,15);
        bottoneTornaIndietro.createListenerButtonGoBack();

        mainUserOptionsPanel.add(bottoneTornaIndietro);

        Button bottoneTornaAllaHome = new Button("SchermataCaricoScorte","Home", 150, 30);
        bottoneTornaAllaHome.changeFontButton("Arial", 1,15);
        bottoneTornaAllaHome.createListenerButtonHome();

        mainUserOptionsPanel.add(bottoneTornaAllaHome);

        Button bottoneLogOut = new Button("SchermataCaricoScorte","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainUserOptionsPanel.add(bottoneLogOut);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        JPanel mainNorthLabelPanel = new JPanel(new FlowLayout());
        JLabel orderLabel = new JLabel("Ordine numero: " + this.Id_Ordine);

        mainNorthLabelPanel.add(orderLabel);

        mainNorthPanel.add(mainNorthLabelPanel, BorderLayout.CENTER);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        String headers[] = {"Nome Farmaco","Principio Attivo", "Quantità prenotate", "Scadenza", "Quantità ricevute"};
        JScrollPane sp = new JScrollPane();
        JLabel resultLabel = new JLabel("Nessuna ordine");
        ResultSet queryResult = null;
        Table tableConsegne = null;

        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco,principio_attivo, quantita, data_scadenza FROM dbms_azienda.dettaglio_ordine WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ this.Id_Ordine +"';");
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
                    tableConsegne.fillTable_oneEditTextCaricoScorte();
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

        Button buttonAggiorna = new Button("Carica Scorte", 150,30);
        buttonAggiorna.changeFontButton("Arial", 1, 15);
        if(tableConsegne != null) {
            buttonAggiorna.createListenerButtonAggiornaCaricoScorte("SchermataConsegne", tableConsegne.n,this.Id_Ordine);
        } else{
            buttonAggiorna.createListenerButtonAggiornaCaricoScorte("SchermataConsegne", 0,0);
        }
        mainSouthPanel.add(buttonAggiorna);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);

        Main.schermataCaricoScortePanel.add(mainPanel, BorderLayout.CENTER);
    }
}

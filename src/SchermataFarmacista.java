import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SchermataFarmacista  {
    public SchermataFarmacista() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String hed[] = {"id", "data", "modifica","annulla"};
        Table tabella = new Table(hed,Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.lista_ordini;"));
        tabella.fillTable_threeButton("id","modifica","annulla",1,1,1);
        JScrollPane scroll = new JScrollPane(tabella);
        Main.schermataFarmacistaPanel.add(scroll,BorderLayout.CENTER);
    }
}

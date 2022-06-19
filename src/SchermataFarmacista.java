import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SchermataFarmacista  {
    public SchermataFarmacista() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String hed[] = {"farmacia", "indirizzo", "telefono","ordine", "stato"};
        Table tabella = new Table(hed,Main.dbms_Azienda.getData("SELECT * FROM " + Main.dbms_Azienda.databaseName + ".consegne;"));
        tabella.createTable_checkBox();
        JScrollPane scroll = new JScrollPane(tabella);
        Main.schermataFarmacistaPanel.add(scroll,BorderLayout.CENTER);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SchermataFarmacista  {
    public SchermataFarmacista() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String hed[] = {"farmacia", "indirizzo", "telefono","ordine", "stato"};
        Table tabella = new Table(5,5,hed, Main.dbms_Azienda.getAll());
        JScrollPane scroll = new JScrollPane(tabella);
        Main.schermataFarmacistaPanel.add(scroll,BorderLayout.CENTER);
    }
}

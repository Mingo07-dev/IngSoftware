import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataFarmacista  {
    public SchermataFarmacista(){
        String hed[] = {"farmacia", "indirizzo", "telefono","ordine", "stato"};
        String data[] = {"nome1","indirizzo1","3345435","11111111","nome2","indirizzo2","3345435","11111111","nome3","indirizzo3","3345435","11111111"};
        Table tabella = new Table(5,5,hed,data);
        JScrollPane scroll = new JScrollPane(tabella);
        Main.schermataFarmacistaPanel.add(scroll,BorderLayout.CENTER);
    }
}

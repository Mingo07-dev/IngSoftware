import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale {


    public SchermataPrincipale(){
        Button.createButtonChangeView(Main.schermataPrincipalePanel,"SchermataFarmacista", "Schermata Farmacista");
        Button.createButtonChangeView(Main.schermataPrincipalePanel,"SchermataCorriere", "Schermata Corriere");
    }


}

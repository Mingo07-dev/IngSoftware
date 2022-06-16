import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale {


    public SchermataPrincipale(){


        Button.createButtonChangeView("SchermataPrincipale", Main.schermataPrincipalePanel,"SchermataFarmacista", "Schermata Farmacista");
        Button.createButtonChangeView("SchermataPrincipale", Main.schermataPrincipalePanel,"SchermataCorriere", "Schermata Corriere");
    }


}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataCorriere {
    public SchermataCorriere(){

        Button.createButtonChangeView("SchermataCorriere", Main.schermataCorrierePanel,"SchermataAutenticazione", "Home");
        Button.createButtonChangeView("SchermataCorriere", Main.schermataCorrierePanel,"SchermataImpiegatoAzienda", "Impiegato Azienda");
    }


}

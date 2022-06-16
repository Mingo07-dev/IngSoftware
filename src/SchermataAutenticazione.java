import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SchermataAutenticazione {
    public SchermataAutenticazione(){


        Button.createButtonChangeView("SchermataAutenticazione", Main.schermataAutenticazionePanel,"SchermataFarmacista", "Schermata Farmacista");
        Button.createButtonChangeView("SchermataAutenticazione", Main.schermataAutenticazionePanel,"SchermataCorriere", "Schermata Corriere");

    }


}

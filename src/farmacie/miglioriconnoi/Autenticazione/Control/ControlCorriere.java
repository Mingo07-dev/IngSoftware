package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataCorriere;
import farmacie.miglioriconnoi.Common.ActionListeners;

import java.awt.event.ActionListener;

public class ControlCorriere {
    public static void setListeners(){
        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataCorriere.bottoneLogOut.addListener(AC_Logout);

        ActionListener AC_Prenotazione = ActionListeners.createListenerShowConsegne();
        SchermataCorriere.buttonVisualizzaElencoConsegne.addListener(AC_Prenotazione);
    }
}

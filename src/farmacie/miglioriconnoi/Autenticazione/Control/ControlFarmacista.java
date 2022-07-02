package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataFarmacista;
import farmacie.miglioriconnoi.Common.ActionListeners;

import java.awt.event.ActionListener;

public class ControlFarmacista {
    public static void setListeners(){
        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataFarmacista.bottoneLogOut.addListener(AC_Logout);

        ActionListener AC_Prenotazione = ActionListeners.createListenerShowPrenotazione();
        SchermataFarmacista.prenotaFarmaci.addListener(AC_Prenotazione);

        ActionListener AC_ModificaPrenotazioneAutomatica = ActionListeners.createListenerShowModificaPrenotazioneAutomatica();
        SchermataFarmacista.modificaPrenotazioneAutomatica.addListener(AC_ModificaPrenotazioneAutomatica);

        ActionListener AC_CaricoScorte = ActionListeners.createListenerShowCaricoScorte();
        SchermataFarmacista.caricoScorte.addListener(AC_CaricoScorte);

        ActionListener AC_ControlloScorte = ActionListeners.createListenerShowControlloScorte();
        SchermataFarmacista.controlloScorte.addListener(AC_ControlloScorte);

        ActionListener AC_VisualizzaListaOrdini = ActionListeners.createListenerShowVisualizzaListaOrdini();
        SchermataFarmacista.visualizzaListaOrdini.addListener(AC_VisualizzaListaOrdini);

    }
}

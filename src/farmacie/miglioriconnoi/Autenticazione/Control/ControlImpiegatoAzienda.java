package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataImpiegatoAzienda;
import farmacie.miglioriconnoi.Common.ActionListeners;

import java.awt.event.ActionListener;

public class ControlImpiegatoAzienda {
    public static void setListeners(){
        ActionListener AC_Logout = ActionListeners.createActionListenerButtonLogout();
        SchermataImpiegatoAzienda.bottoneLogOut.addListener(AC_Logout);

        ActionListener AC_SegnalazioniRisolte = ActionListeners.createListenerShowSegnalazioniRisolte();
        SchermataImpiegatoAzienda.buttonSegnalazioniRisolte.addListener(AC_SegnalazioniRisolte);

        ActionListener AC_SegnalazioniIrrisolte = ActionListeners.createListenerShowSegnalazioniIrrisolte();
        SchermataImpiegatoAzienda.buttonSegnalazioniIrrisolte.addListener(AC_SegnalazioniIrrisolte);
    }
}

package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataAutenticazione;
import farmacie.miglioriconnoi.Common.ActionListeners;
import java.awt.event.ActionListener;

public class ControlAutenticazione {
    public static void setListenersAutenticazione(){
        ActionListener AC_Login = ActionListeners.createListenerShowLogin();
        ActionListener AC_Registrazione = ActionListeners.createListenerShowRegistrazione();
        ActionListener AC_RecuperoCredenziali = ActionListeners.createListenerShowRecuperoCredenziali();

        SchermataAutenticazione.buttonSchermataLogin.addListener(AC_Login);
        SchermataAutenticazione.buttonSchermataRegistrazione.addListener(AC_Registrazione);
        SchermataAutenticazione.buttonSchermataRecuperoCredenziali.addListener(AC_RecuperoCredenziali);
    }
}

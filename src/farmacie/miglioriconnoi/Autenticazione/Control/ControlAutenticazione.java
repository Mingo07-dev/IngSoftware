package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataAutenticazione;
import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Autenticazione.Views.SchermataRecuperoCredenziali;
import farmacie.miglioriconnoi.Autenticazione.Views.SchermataRegistrazione;
import farmacie.miglioriconnoi.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ControlAutenticazione {
    public static void setListenersAutenticazione(){
        ActionListener AC_Login = createListenerLogin();
        ActionListener AC_Registrazione = createListenerRegistrazione();
        ActionListener AC_RecuperoCredenziali = createListenerRecuperoCredenziali();

        SchermataAutenticazione.buttonSchermataLogin.addListener(AC_Login);
        SchermataAutenticazione.buttonSchermataRegistrazione.addListener(AC_Registrazione);
        SchermataAutenticazione.buttonSchermataRecuperoCredenziali.addListener(AC_RecuperoCredenziali);
    }

    public static ActionListener createListenerLogin(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA LOGIN
                Main.schermataLoginPanel.removeAll();
                try {
                    SchermataLogin schermataLogin = new SchermataLogin();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.schermataLoginPanel.repaint();
                Main.mainFrame.setVisible(true);

                //MOSTRA LOGIN
                Main.cardLayout.show(Main.mainPanel, "SchermataLogin");
            }
        };

        return AC;
    }
    public static ActionListener createListenerRegistrazione(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA REGISTRAZIONE
                Main.schermataRegistrazionePanel.removeAll();
                try {
                    SchermataRegistrazione schermataRegistrazione = new SchermataRegistrazione();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.schermataRegistrazionePanel.repaint();
                Main.mainFrame.setVisible(true);

                //MOSTRA REGISTRAZIONE
                Main.cardLayout.show(Main.mainPanel, "SchermataRegistrazione");
            }
        };

        return AC;
    }
    public static ActionListener createListenerRecuperoCredenziali(){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RICARICA RECUPERO CREDENZIALI
                Main.schermataRecuperoCredenzialiPanel.removeAll();
                try {
                    SchermataRecuperoCredenziali schermataRecuperoCredenziali = new SchermataRecuperoCredenziali();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.schermataRecuperoCredenzialiPanel.repaint();

                //MOSTRA RECUPERO CREDENZIALI
                Main.cardLayout.show(Main.mainPanel, "SchermataRecuperoCredenziali");
            }
        };

        return AC;
    }
}

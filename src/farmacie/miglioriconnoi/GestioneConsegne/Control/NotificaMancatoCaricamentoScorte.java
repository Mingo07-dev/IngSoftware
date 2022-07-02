package farmacie.miglioriconnoi.GestioneConsegne.Control;


import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.TimeScheduler;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificaMancatoCaricamentoScorte {
    public NotificaMancatoCaricamentoScorte(){
        TimeScheduler task = new TimeScheduler(20);
    }

    public static void notificaMancatoCaricamentoScorte(){
        if(SchermataLogin.nomeFarmacia.equals("")){
            TimeScheduler.checkDone = false;
        }
        else{
            ResultSet checkConsegne = null;
            try {
                checkConsegne = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.elenco_consegne WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND stato_consegna = 1;");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }

            if(checkConsegne != null){
                JOptionPane.showMessageDialog(new Frame(), "Hai ancora delle consegne da caricare!!.");
            }
        }
    }
}

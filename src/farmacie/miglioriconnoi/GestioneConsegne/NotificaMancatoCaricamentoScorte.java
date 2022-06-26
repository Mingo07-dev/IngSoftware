package farmacie.miglioriconnoi.GestioneConsegne;


import farmacie.miglioriconnoi.Autenticazione.SchermataLogin;
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
        if(SchermataLogin.nomeFarmacia == null){
            System.out.println("sono le 3");
            TimeScheduler.checkDone = false;
        }
        else{
            System.out.println("sono le 3 e sei loggato");
            ResultSet checkConsegne = null;
            try {
                checkConsegne = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.elenco_consegne WHERE nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"' AND stato_consegna = 1;");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }

            if(checkConsegne != null){
                System.out.println("sono le 3 e non hai caricato le scorte");
                JOptionPane.showMessageDialog(new Frame(), "Hai ancora delle consegne da caricare!!.");
            }
        }
    }
}

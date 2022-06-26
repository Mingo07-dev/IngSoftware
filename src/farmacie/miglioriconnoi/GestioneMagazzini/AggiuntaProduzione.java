package farmacie.miglioriconnoi.GestioneMagazzini;

import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.TimeScheduler;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AggiuntaProduzione {
    private static String[] nomiFarmaci = {"toradol", "proxizal", "tachipirina", "oki"};
    private static String[] principioAttivo= {"p1","p2","p3","p4"};
    private static int[] quantita = {2000,1000,5000,5000};
    public AggiuntaProduzione(){
        TimeScheduler task = new TimeScheduler(24, 1);
    }
    public static void aggiuntaProduzioneAzienda() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date dateNow = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.YEAR, 1);
        dateNow = (Date) calendar.getTime();
        LocalDate scadenza = dateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for(int i = 0; i < 4; i++){
            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmaco` (`nome_farmaco`, `principio_attivo`, `data_scadenza`, `quantita_disponibile`) VALUES ('" + nomiFarmaci[i] + "', '"+ principioAttivo[i] +"', '" + scadenza + "', '" + quantita[i] + "');");
        }
    }
}

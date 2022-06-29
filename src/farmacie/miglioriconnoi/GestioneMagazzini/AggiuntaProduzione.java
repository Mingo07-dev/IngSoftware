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
    private static String[] nomiFarmaci = {"Tachiparina", "Moment", "Buscofen", "Elocon", "Zirtec", "Augmentin", "Oki", "Normix", "Gaviscon", "Viagra", "Drovelis", "Carduran", "Enapren", "Pariet", "Donepezil", "Cosopt", "Blopres", "Vasexten", "Morfina", "Adrenalina"};
    private static String[] principioAttivo= {"Paracetamolo","Moment","Ibrobufdene","Mometasone furoato", "cetrizina diclorato", "amoxicillina", "Ibrobufene", "Rifaximina", "sodio alginato", "Sildenafil", "drospirenone", "doxazosina", "enalapril maleato", "rabeprazolo sodico", "donepezil cloridrato", "timololo", "idroclorotiazide", "barnidipina cloridrato", "morfina cloridrato", "adrenalina"};
    private static int[] quantita = {5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000};
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
        for(int i = 0; i < 20; i++){
            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmaco` (`nome_farmaco`, `principio_attivo`, `data_scadenza`, `quantita_disponibile`) VALUES ('" + nomiFarmaci[i] + "', '"+ principioAttivo[i] +"', '" + scadenza + "', '" + quantita[i] + "');");
        }
    }
}

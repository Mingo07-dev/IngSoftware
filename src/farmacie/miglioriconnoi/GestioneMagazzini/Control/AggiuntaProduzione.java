package farmacie.miglioriconnoi.GestioneMagazzini.Control;

import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.TimeScheduler;

import java.sql.ResultSet;
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
        TimeScheduler task = new TimeScheduler(4, 1);
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

        //SE CI SONO FARMACI CHE ARRIVANO A DISPONIBILITÀ 0 E HANNO ALTRI DOPPIONI LI CANCELLA
        ResultSet queryResult = null;
        int rows_farmaci_zero;
        try {
            queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE quantita_disponibile = '0';");
            queryResult.first();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        rows_farmaci_zero = Main.dbms_Azienda.getResultSetRows(queryResult);
        String nomeFarmaco_daCancellare;
        ResultSet queryResult1 = null;
        for(int i = 0; i < rows_farmaci_zero; i++){
            try {
                nomeFarmaco_daCancellare = queryResult.getString(1);
                queryResult.next();
                queryResult1 = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE nome_farmaco = '"+ nomeFarmaco_daCancellare +"';");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                throw new RuntimeException(ex);
            }

            if(Main.dbms_Azienda.getResultSetRows(queryResult1) > 1)
            {
                try {
                    Main.dbms_Azienda.setData("DELETE FROM `dbms_azienda`.`farmaco` WHERE (`nome_farmaco` = '" + nomeFarmaco_daCancellare + "') and (`quantita_disponibile` = '0');");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

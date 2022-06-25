import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AggiuntaProduzione {
    private static int[] nomiFarmaci;
    private static int[] principioAttivo;
    private static Date[] dataScadenza;
    private static int[] quantita;
    public AggiuntaProduzione(){
        TimeScheduler task = new TimeScheduler(25, 1);
    }
    public static void aggiuntaProduzioneAzienda() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for(int i = 0; i < 10; i++){
            ResultSet takeLastDate;
            //takeLastDate = Main.dbms_Azienda.getData("SELECT MAX(dbms_azienda.lista_ordini.data_scadenza) FROM dbms_azienda.farmaco WHERE nome_farmaco = '"+ nomiFarmaci[i] +"' AND principio_attivo = '"+ principioAttivo[i] +"';");
            //Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmaco` (`nome_farmaco`, `principio_attivo`, `data_scadenza`, `quantita_disponibile`) VALUES ('" + nomiFarmaci[i] + "', '"+ principioAttivo[i] +"', '" + dataScadenza[i] + "', '" + quantita[i] + "');");
        }
    }
}

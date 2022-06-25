import java.sql.ResultSet;
import java.sql.SQLException;

public class PrenotazioneAutomatica {
    public static int[] quantita;
    public static String[] stringNome;
    public static String[] principioAttivo;

    public PrenotazioneAutomatica(){
        TimeScheduler task = new TimeScheduler(25, 2);
    }
    public static void prenotazioneAutomatica(){
        ResultSet queryResult = null;
        /*try {
            queryResult = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.prenotazione_automatica WHERE dbms_azienda.dettaglio_ordine.Id_ordine = '"+ this.Id_Ordine +"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }*/
    }
}

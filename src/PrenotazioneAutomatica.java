import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class PrenotazioneAutomatica {
    public static String[] stringNome;
    public static String[] principioAttivo;
    public static int[] quantita;
    public static int recapitoTelefonicoFarmacia;
    public static String indirizzoFarmacia;

    public PrenotazioneAutomatica(){
        TimeScheduler task = new TimeScheduler(25, 2);
    }
    public static void prenotazioneAutomatica() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet nomeFarmacia = null;
        try {
            nomeFarmacia = Main.dbms_Azienda.getData("SELECT nome_farmacia FROM dbms_azienda.prenotazione_automatica GROUP BY nome_farmacia;");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        nomeFarmacia.first();
        String nomeFarmaciaString = nomeFarmacia.getString(1);

        ResultSet queryResult = null;
        LocalDate scadenza = getScadenza();
        try {
            queryResult = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.prenotazione_automatica WHERE nome_farmacia = '"+nomeFarmaciaString+"';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        queryResult.first();
        int n = Main.dbms_Azienda.getResultSetRows(queryResult);
        stringNome = new String[n];
        principioAttivo = new String[n];
        quantita = new int[n];
        int zeri = 0;
        for (int i = 0; i < n; i++) {
            stringNome[i] = queryResult.getString(1);
            principioAttivo[i] = queryResult.getString(2);
            quantita[i] = queryResult.getInt(3);
            queryResult.next();
            System.out.println(stringNome[i]);
            System.out.println(principioAttivo[i]);
            System.out.println(quantita[i]);
            if (quantita[i] == 0)
                zeri++;
        }

        String[] stringNome_temp;
        String[] principioAttivo_temp;
        int[] quantita_temp;
        stringNome_temp = new String[n - zeri];
        principioAttivo_temp = new String[n - zeri];
        quantita_temp = new int[n - zeri];

        for (int i = 0; i < n; i++) {
            if (quantita[i] != 0) {
                stringNome_temp[i] = stringNome[i];
                principioAttivo_temp[i] = principioAttivo[i];
                quantita_temp[i] = quantita[i];
            }
        }

        //PRENDE L'ULTIMO ID CREATO
        ResultSet idOrdine_Result = Main.dbms_Azienda.getData("SELECT MAX(dbms_azienda.lista_ordini.id_ordine) FROM dbms_azienda.lista_ordini;");
        idOrdine_Result.first();
        int id_ordine = idOrdine_Result.getInt(1) + 1;



        //PRENDE I DATI DELLA FARMACIA PER CREARE L'ORDINE E LA CONSEGNA
        ResultSet datiFarmacia;
        try {
            datiFarmacia = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.farmacie WHERE nome_farmacia = '" + nomeFarmaciaString + "';");
            datiFarmacia.first();
            recapitoTelefonicoFarmacia = datiFarmacia.getInt(2);
            indirizzoFarmacia = datiFarmacia.getString(3);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        LocalDate consegna = getConsegna();

        try {
            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`lista_ordini` (`id_ordine`, `data_consegna_ordine`, `nome_farmacia`, `stato_ordine`) VALUES ('" + id_ordine + "', '" + consegna + "', '" + nomeFarmaciaString + "', '0');");
            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`elenco_consegne` (`id_ordine`, `indirizzo_postale`, `recapito_telefonico`, `data_consegna`, `nome_farmacia`, `stato_consegna`) VALUES ('" + id_ordine + "', '" + indirizzoFarmacia + "', '" + recapitoTelefonicoFarmacia + "', '" + consegna + "', '" + nomeFarmaciaString + "', '0');");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }

        //AGGIUNGE I FARMACI AL DETTAGLIO DELL'ORDINE

        for (int i = 0; i < (n - zeri); i++) {
            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`dettaglio_ordine` ( `id_ordine`, `nome_farmaco`, `principio_attivo`, `quantita`, `data_scadenza`) VALUES ('" + id_ordine + "', '" + stringNome[i] + "', '" + principioAttivo[i] + "', '" + quantita[i] + "', '" + scadenza + "');");
        }

    }

    private static LocalDate getScadenza(){
        Date dateNow = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.YEAR, 1);
        dateNow = (Date) calendar.getTime();
        LocalDate scadenza = dateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return scadenza;
    }

    private static LocalDate getConsegna(){
        Date dateNow = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.HOUR, 72);
        dateNow = (Date) calendar.getTime();
        LocalDate consegna = dateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return consegna;
    }
}

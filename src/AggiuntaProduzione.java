public class AggiuntaProduzione {
    int quantità[];
    int nomiFarmaci[];
    public AggiuntaProduzione(){
        TimeScheduler task = new TimeScheduler(25, 1);
    }
    public static void aggiuntaProduzioneAzienda(){
        //Main.dbms_Farmacia.setData("UPDATE dbms_azienda.farmaco SET quantita_disponibile = quantita_disponibile + '"  + intarrayQuantitaArrivate[i] + "' WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '"+ principioAttivo[i] +"' AND scadenza_farmaco = '"+ dataScadenza[i]+"' AND nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
    }
}

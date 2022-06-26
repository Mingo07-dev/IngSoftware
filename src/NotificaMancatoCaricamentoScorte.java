public class NotificaMancatoCaricamentoScorte {
    public NotificaMancatoCaricamentoScorte(){
        TimeScheduler task = new TimeScheduler(20);
    }

    public static void notificaMancatoCaricamentoScorte(){
        if(SchermataLogin.nomeFarmacia == null){
            TimeScheduler.checkDone = false;
        }
        else{
            System.out.println("caricalescorte");
        }
    }
}

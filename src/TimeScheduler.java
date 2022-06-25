import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeScheduler
{
    //SCHEDULA UN'AZIONE DA COMPIERE IN UN CERTO ORARIO
    public TimeScheduler(int hour){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            boolean checkDone = false;
            @Override
            public void run() {

                Time startTime = new Time(hour,0,0);
                LocalTime startTimeLocal = startTime.toLocalTime();
                Time startTimeMax = new Time(hour+1,0,0);
                LocalTime startTimeMaxLocal = startTimeMax.toLocalTime();

                LocalTime nowTime = LocalTime.now();
                if(nowTime.compareTo(startTimeLocal) > 0 && nowTime.compareTo(startTimeMaxLocal) < 0 && checkDone == false){
                    NotificaMancatoCaricamentoScorte.notificaMancatoCaricamentoScorte();
                    checkDone = true;
                }
                else if(checkDone == true && nowTime.compareTo(startTimeLocal) < 0 && nowTime.compareTo(startTimeMaxLocal) > 0){
                    checkDone = false;
                }
            }
        }, 0, 1000);
    }

    public TimeScheduler(int day, int task){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            boolean checkDone = false;
            @Override
            public void run() {

                Date startDate = new Date();
                startDate.setDate(day);
                LocalDate dateStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate dateNow = LocalDate.now();
                if(dateNow.compareTo(dateStart) == 0 && checkDone == false){
                    switch (task){
                        case 1:
                            try {
                                AggiuntaProduzione.aggiuntaProduzioneAzienda();
                            } catch (SQLException | ClassNotFoundException | InstantiationException |
                                     IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            PrenotazioneAutomatica.prenotazioneAutomatica();
                            break;
                    }
                    checkDone = true;
                }
                else if(checkDone == true && dateNow.compareTo(dateStart) != 0){
                    checkDone = false;
                }
            }
        }, 0, 1000);
    }
}


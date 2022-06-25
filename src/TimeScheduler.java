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
    public static void scheduleTaskHour(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            boolean checkDone = false;
            @Override
            public void run() {

                Time startTime = new Time(20,0,0);
                LocalTime startTimeLocal = startTime.toLocalTime();
                Time startTimeMax = new Time(21,0,0);
                LocalTime startTimeMaxLocal = startTimeMax.toLocalTime();

                LocalTime nowTime = LocalTime.now();
                if(nowTime.compareTo(startTimeLocal) > 0 && nowTime.compareTo(startTimeMaxLocal) < 0 && checkDone == false){
                    System.out.println("ciao");
                    checkDone = true;
                }
                else if(checkDone == true && nowTime.compareTo(startTimeLocal) < 0 && nowTime.compareTo(startTimeMaxLocal) > 0){
                    checkDone = false;
                }
            }
        }, 0, 1000);
    }

    public static void scheduleTaskDays(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            boolean checkDone = false;
            @Override
            public void run() {

                Date startDate = new Date();
                startDate.setDate(25);
                LocalDate dateStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate dateNow = LocalDate.now();
                if(dateNow.compareTo(dateStart) == 0 && checkDone == false){
                    System.out.println("fatto");
                    checkDone = true;
                }
                else if(checkDone == true && dateNow.compareTo(dateStart) != 0){
                    checkDone = false;
                }
            }
        }, 0, 1000);
    }
}


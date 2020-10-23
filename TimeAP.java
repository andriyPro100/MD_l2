package ua.kpi.comsys.myaplication2;

import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeAP {
    int hours;
    int minutes;
    int seconds;

    public TimeAP(){
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public TimeAP(int hours, int minutes, int seconds){
        if (checkData()){
            this.hours = hours;
            this.seconds = seconds;
            this.minutes = minutes;
        }
    }

    public TimeAP(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        int hours_ = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int minutes_ = calendar.get(Calendar.MINUTE);    // gets month number, NOTE this is zero based!
        int seconds_ = calendar.get(Calendar.SECOND);
        if (checkData()){
            this.hours = hours_;
            this.seconds = seconds_;
            this.minutes = minutes_;
        }
    }

    public String getTime(){
        String a = "am";
        if ((int)(hours / 12) == 1){
            a = "pm";
        }
        String ret = String.format("%02d:%02d:%02d %s", hours%12, minutes, seconds, a);
        return ret;
    }

    public TimeAP sumTime(TimeAP timeAP){
        long a = this.toSeconds();
        long b = timeAP.toSeconds();
        a += b;
        TimeAP t = new TimeAP();
        t.toHMS(a);
        return t;
    }

    public TimeAP subTime(TimeAP timeAP){
        long a = this.toSeconds();
        long b = timeAP.toSeconds();
        a -= b;
        TimeAP t = new TimeAP();
        t.toHMS(a);
        return t;
    }

    private void toHMS(long b) {
        if (b < 0){
            b = (24*3600)+ b;
        }
        this.seconds = (int) (b % 60);
        this.minutes = (int) ((b)/60)%60;
        this.hours = (int) ((b)/3600)%24;
    }

    private long toSeconds() {
        return (hours * 3600) + (minutes * 60) + seconds;
    }

    public TimeAP sumTime(TimeAP timeAP, TimeAP timeAP2){
        long s1 = timeAP.toSeconds();
        long s2 = timeAP2.toSeconds();
        s1 = s1 + s2;
        TimeAP t = new TimeAP();
        t.toHMS(s1);
        return t;
    }

    public TimeAP subTime(TimeAP timeAP, TimeAP timeAP2){
        long s1 = timeAP.toSeconds();
        long s2 = timeAP2.toSeconds();
        s1 = s1 - s2;
        TimeAP t = new TimeAP();
        t.toHMS(s1);
        return t;
    }

    public boolean checkData(){
        if ((hours >= 0) & (hours <= 23)){
            if ((minutes >= 0) & (minutes <= 59)){
                if ((seconds >= 0) & (seconds <= 59)){
                    return true;
                }else {
                    MainActivity.errorMessage("перевірте вхідні значення – секунди ∈ [0, 59]");
                    return false;
                }
            }else{
                MainActivity.errorMessage("перевірте вхідні значення –  хвилини ∈ [0, 59]");
                return false;
            }
        }else{
            MainActivity.errorMessage("перевірте вхідні значення – години ∈ [0, 23]");
            return false;
        }
    }
}


package module;

import java.time.Duration;
import java.time.LocalTime;

public class Schedule {
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String day;

    public Schedule(String day, LocalTime startTime, LocalTime endTime){
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public long period(){
        return Duration.between(this.endTime, this.startTime).toMinutes();
    }

    public String toString(){
        return "Day: " + day +
                "\nPeriod: " + startTime + " - " + endTime;
    }
}

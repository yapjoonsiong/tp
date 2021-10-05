package module;

import java.time.Duration;
import java.time.LocalTime;

public class Schedule {
    protected String startTime;
    protected String location;
    protected String day;
    protected String comment;

    public Schedule(String day, String startTime, String location, String comment){
        this.day = day;
        this.startTime = startTime;
        this.location = location;
        this.comment = comment;
    }
    public String getLocation() {
        return this.location;
    }
    public String getComment() {
        return this.comment;
    }
    public String getDay() {
        return day;
    }

    public String getStartTime(){
        return startTime;
    }

    public String toString(){
        return "Day: " + day +
                "\nStart Time: " + startTime
                + "\nLocation: " + location
                +"\nComments: " + comment;
    }
}

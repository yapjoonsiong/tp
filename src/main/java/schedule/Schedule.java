package schedule;

import exceptions.NoCapExceptions;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    protected String startTime;
    protected String location;
    protected String day;
    protected String comment;

    /**
     * Constructor for Schdeule
     * During construction of Schedule, input format is checked.
     *
     * @param day       Day of class
     * @param startTime timeslot of class
     * @param location  location of the class
     * @param comment   additional comments for the class
     * @throws NoCapExceptions Throws an exception when a input format error is triggered.
     */

    public Schedule(String day, String startTime, String location, String comment) throws NoCapExceptions {
        if (location.length() > 16 || comment.length() > 16) {
            throw new NoCapExceptions("location and comment must be less than 17 characters");
        }
        if (!isCorrectDayFormat(day)) {
            throw new NoCapExceptions("Wrong day format. Please key in mon/tue/wed/thu/fri/sat in either LOWERCASE or "
                    + "UPPERCASE");
        }
        if (!isCorrectTimeFormat(startTime)) {
            throw new NoCapExceptions("Wrong time format. Please key in 1 hour blocks of time (eg. 1000/1300)");
        }
        this.day = day;
        this.startTime = startTime;
        this.location = location;
        this.comment = comment;
    }

    private boolean isCorrectTimeFormat(String time) {
        return time.length() == 4 && time.substring(2).equals("00") && Integer.parseInt(time.substring(0, 2)) < 24;
    }

    private boolean isCorrectDayFormat(String day) {
        return Objects.equals(day, "MON") || Objects.equals(day, "TUE") || Objects.equals(day, "WED")
                || Objects.equals(day, "THU") || Objects.equals(day, "FRI") || Objects.equals(day, "SAT");
    }

    /**
     * For deserialization from JSON file.
     */
    public Schedule() {
    }

    //Getters
    public String getLocation() {
        return this.location;
    }

    public String getComment() {
        return this.comment;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    //Setters
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Reformats Schedule for easier viewing.
     */
    @Override

    public String toString() {
        return "Day: " + day
                + "\nStart Time: " + startTime
                + "\nLocation: " + location
                + "\nComments: " + comment;
    }
}

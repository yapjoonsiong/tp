package schedule;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.Module;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleList {
    protected ArrayList<Schedule> scheduleList;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ScheduleList() {
        this.scheduleList = new ArrayList<Schedule>();
    }

    public void addClass(Schedule schedule) {
        this.scheduleList.add(schedule);
    }

    /**
     * Overload addClass method to take in String input
     * Method to add new Class object to scheduleList
     *
     * @param input User input class details
     * @throws NoCapExceptions Exception thrown when class already exists in timeslot or wrong formatting of input
     */
    public void addClass(String input) throws NoCapExceptions {
        String[] scheduleInfo = input.split("/");
        if (scheduleInfo.length != 4) {
            throw new NoCapExceptions("Please key in 4 variables for class details");
        }
        String day = scheduleInfo[0].toUpperCase(Locale.ROOT);
        String time = scheduleInfo[1];
        String location = scheduleInfo[2];
        String comment = scheduleInfo[3];
        if (isSlotFilled(day, time)) {
            throw new NoCapExceptions("A class already exists in this timeslot!");
        }
        Schedule schedule = new Schedule(day, time, location, comment);
        this.scheduleList.add(schedule);
        logger.log(Level.INFO, "Schedule added successfully");
    }

    public void deleteClass() {
        while (this.scheduleList.size() != 0) {
            this.scheduleList.remove(scheduleList.get(0));
        }
    }

    public int size() {
        return this.scheduleList.size();
    }

    public Schedule getSchedule(int index) {
        return this.scheduleList.get(index);
    }

    public ArrayList<Schedule> getScheduleList() {
        return this.scheduleList;
    }

    /**
     * Checks for whether a class already exists with the input day and time
     * Iterates through each module object in the moduleList and each schedule within the scheduleList of each module
     *
     * @param day User input
     * @param time User input
     * @return true if a class with matching day and time is found
     */
    private boolean isSlotFilled(String day, String time) {
        for (Module m : NoCap.moduleList.getModuleList()) {
            for (Schedule s : m.getScheduleList().scheduleList) {
                if (Objects.equals(s.getDay(), day) && Objects.equals(s.getStartTime(), time)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        int index = 1;
        String schedulePrint = "";
        for (Schedule s : scheduleList) {
            if (s != null) {
                schedulePrint = schedulePrint + String.valueOf(index) + ".\n";
                schedulePrint = schedulePrint + s.toString() + "\n";
                index++;
            }
        }
        return schedulePrint;
    }
}

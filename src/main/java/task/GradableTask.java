package task;

import exceptions.NoCapExceptions;

import java.time.DateTimeException;

public class GradableTask extends Task {
    protected int weightage;

    public GradableTask(String description, String date, int weightage) {
        super(description, date);
        this.weightage = weightage;
    }

    /**
     * For deserialization from JSON file.
     */
    public GradableTask() {
    }

    public int getWeightage() {
        return this.weightage;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getDescription() + " by: " + createFormattedDeadline() + " Weightage "
                + this.weightage + "% " + createStatusIcon();
    }
}
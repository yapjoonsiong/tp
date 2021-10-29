package task;

import exceptions.NoCapExceptions;

import java.time.DateTimeException;

public class GradableTask extends Task {
    protected int weightage;

    /**
     * Constructor for GradableTask, which inherits from Task.
     *
     * @param description Description of GradableTask
     * @param date        Deadline of GradableTask
     * @param weightage   Weightage of GradableTask
     */
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

    /**
     * Reformats GradableTask for easier viewing.
     */
    @Override
    public String toString() {
        return getDescription() + " by: " + createFormattedDeadline() + " Weightage "
                + this.weightage + "% " + createStatusIcon();
    }
}
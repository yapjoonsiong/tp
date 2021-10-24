package task;

public class GradableTask extends Task {
    protected int weightage;

    public GradableTask(String description, String date, int weightage) {
        super(description, date);
        this.weightage = weightage;
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

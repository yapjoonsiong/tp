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

    @Override
    public String toString() {
        return getDescription() + " by: " + getDeadline() + " Weightage " + this.weightage + "% " + createStatusIcon();
    }
}

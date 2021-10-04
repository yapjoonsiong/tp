package command;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    /**
     * @param description The description of the task given by the user
     */
    public Task(String description, String date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return "[" + (isDone ? "X" : " ") + "] ";
    }
    public String getDescription() {
        return this.description;
    }
    public String getDate() {
        return " by: " + this.date;
    }
    public String toString() {
        return getStatusIcon() + getDescription() + getDate();
    }
}

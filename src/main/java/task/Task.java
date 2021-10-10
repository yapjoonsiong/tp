package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    public boolean isDone() {
        return isDone;
    }


    /**
     * temp message.
     *
     * @param description The description of the task given by the user
     */
    public Task(String description, String date) {
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    /**
     * For deserialization from JSON file.
     */
    public Task() {
    }

    //Getters
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return " by: " + this.date;
    }

    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatusIcon() + getDescription() + getDate();
    }
}

package task;

import command.Parser;
import command.Ui;

import java.time.LocalDateTime;


public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;
    protected LocalDateTime deadline;

    /**
     * temp message.
     *
     * @param description The description of the task given by the user
     */
    public Task(String description, String date) {
        setDescription(description);
        setDate(date);
        setDone(false);
        setDeadline(date);
    }

    /**
     * For deserialization from JSON file.
     */
    public Task() {
    }

    //Getters
    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public boolean isDone() {
        return isDone;
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

    public void setDeadline(String date) {
        this.deadline = Parser.parseDate(date);
    }

    public String getDeadline() {
        return Parser.dateStringOutput(this.deadline);
    }

    public void markDone() {
        this.isDone = true;
    }

    public String createStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    public String toString() {
        return  createStatusIcon() + getDescription() + " by: " + getDeadline();
    }
}

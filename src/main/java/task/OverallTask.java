package task;

import java.util.Comparator;

/**
 * Task class used for listing out all available tasks in a semester.
 * Inherits from task class but adds a moduleName, weightage and gradable attribute.
 */
public class OverallTask extends Task {
    private final String moduleName;
    private final boolean gradable;
    private int weightage;

    /**
     * Constructor for overall task which takes in a Task object.
     *
     * @param task Task object used to construct the OverallTask object
     * @param moduleName Name of the module of the task
     */
    public OverallTask(Task task, String moduleName) {
        super(task.description, task.date);
        this.isDone = task.isDone;
        this.moduleName = moduleName;
        this.gradable = false;
    }

    /**
     * Constructor for overall task which takes in a GradableTask object.
     *
     * @param task GradableTask object used to construct the OverallTask object
     * @param moduleName Name of the module of the task
     */
    public OverallTask(GradableTask task, String moduleName) {
        super(task.description, task.date);
        this.isDone = task.isDone;
        this.moduleName = moduleName;
        this.gradable = true;
        this.weightage = task.weightage;
    }

    /**
     * Comparator used to compare deadline between OverallTask objects.
     */
    public static Comparator<OverallTask> dateComparator = Comparator.comparing(t -> t.deadline);

    /**
     * Comparator used to compare "done" status between OverallTask objects.
     */
    public static Comparator<OverallTask> statusComparator = (task1, task2) -> {
        if (task1.isDone && !task2.isDone) {
            return -1;
        } else if (!task1.isDone && task2.isDone) {
            return 1;
        } else {
            return 0;
        }
    };

    private String getGradableString() {
        return gradable ? "[G]" : "[ ]";
    }

    private String getWeightageString() {
        return gradable ? "[Weightage: " + this.weightage + "%]" : "";
    }

    @Override
    public String toString() {
        return "[" + moduleName + "]" + getGradableString() + super.toString() + " " + getWeightageString();
    }

}

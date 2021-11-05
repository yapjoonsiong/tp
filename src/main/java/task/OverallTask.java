package task;

import java.util.Comparator;

/**
 * Task class used for listing out all available tasks in a semester.
 * Inherits from task class but adds a moduleName, weightage and gradable attribute.
 */
public class OverallTask extends Task {
    //Constants for comparators
    private static final int RIGHT_HEAVY = -1;
    private static final int LEFT_HEAVY = 1;
    private static final int EQUAL = 0;

    private final String moduleName;
    private final boolean isGradable;
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
        this.isGradable = false;
        this.isLate = task.isLate;
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
        this.isGradable = true;
        this.weightage = task.weightage;
        this.isLate = task.isLate;
    }

    public boolean isGradable() {
        return isGradable;
    }

    /**
     * Comparator used to compare deadlines between OverallTask objects.
     * An overall task that has an earlier deadline is considered less than an overall task with a
     * later deadline, and vice versa. If both tasks have the same deadline, they are
     * considered equal.
     */
    public static Comparator<OverallTask> dateComparator = Comparator.comparing(t -> t.deadline);


    /**
     * Comparator used to compare "done" status between OverallTask objects.
     * If task1 is done while task2 is not, task1 is greater than task2 and vice versa.
     * If both task1 and task2 have the same status, they are considered equal.
     */
    public static Comparator<OverallTask> statusComparator = (task1, task2) -> {
        if (task1.isDone && !task2.isDone) {
            return LEFT_HEAVY;
        } else if (!task1.isDone && task2.isDone) {
            return RIGHT_HEAVY;
        } else {
            return EQUAL;
        }
    };

    private String getGradableString() {
        return isGradable ? "[G]" : "[ ]";
    }

    private String getWeightageString() {
        return isGradable ? "[Weightage: " + this.weightage + "%]" : "";
    }

    private String getModuleNameString() {
        return "[" + moduleName + "]";
    }

    @Override
    public String toString() {
        return getModuleNameString() + getGradableString() + super.toString() + " " + getWeightageString();
    }



}

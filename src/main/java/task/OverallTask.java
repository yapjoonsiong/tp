package task;

import java.util.Comparator;

public class OverallTask extends Task {
    private String moduleName;

    public OverallTask(String description, String date, String moduleName, Boolean done) {
        super(description, date);
        this.isDone = done;
        this.moduleName = moduleName;
    }

    public static Comparator<OverallTask> dateComparator = Comparator.comparing(t -> t.deadline);

    public static Comparator<OverallTask> statusComparator = (task1, task2) -> {
        if (task1.isDone && !task2.isDone) {
            return -1;
        } else if (!task1.isDone && task2.isDone) {
            return 1;
        } else {
            return 0;
        }
    };

    @Override
    public String toString() {
        return "[" + moduleName + "] " + super.toString();
    }

}

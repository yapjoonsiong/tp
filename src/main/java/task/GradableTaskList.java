package task;


import command.VisualiseGradable;
import command.parser.Parser;
import command.Ui;
import exceptions.NoCapExceptions;
import command.Ui;
import command.parser.ParserChecks;


import java.net.StandardSocketOptions;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradableTaskList extends TaskList {
    protected ArrayList<GradableTask> gradableTaskList;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GradableTaskList() {
        this.gradableTaskList = new ArrayList<>();
        logger.setLevel(Level.OFF);
    }

    public ArrayList<GradableTask> getGradableTaskList() {
        return gradableTaskList;
    }

    protected static String getDate(String description) {
        try {
            int datePos = description.indexOf(ParserChecks.START_OF_DATE);
            int weightagePos = description.indexOf(ParserChecks.START_OF_WEIGHTAGE);
            return description.substring(datePos, weightagePos).replace(ParserChecks.START_OF_DATE, "").trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    private static int getWeightage(String description) {
        try {
            int weightagePos = description.indexOf(ParserChecks.START_OF_WEIGHTAGE);
            return Integer.parseInt(description.substring(weightagePos)
                    .replace(ParserChecks.START_OF_WEIGHTAGE, "").trim());
        } catch (StringIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private boolean checkTotalWeightage(int w) {
        int total = 0;
        for (GradableTask g : gradableTaskList) {
            total += g.getWeightage();
        }
        total += w;

        return total <= 100;
    }

    public void addGradableTask(String module, String userInput) throws DateTimeException {
        logger.log(Level.INFO, "Successfully added task");
        String date = getDate(userInput);
        int weightage = getWeightage(userInput);
        if (weightage <= 0 || weightage > 100) {
            Ui.wrongWeightage();
        } else if (!checkTotalWeightage(weightage)) {
            Ui.wrongWeightageSplits();
        } else if (date.isBlank()) {
            Ui.missingDate();
        } else {
            try {
                String description = removeDate(userInput);
                GradableTask newGradableTask = new GradableTask(description, date, weightage);
                this.gradableTaskList.add(taskCount, newGradableTask);
                this.taskCount = gradableTaskList.size();
                Ui.addTaskMessage(newGradableTask, module);
            } catch (DateTimeException e) {
                Ui.wrongDateTimeFormat();
            }
        }
    }

    public GradableTask getGradableTask(int index) {
        logger.log(Level.INFO, "Get GradableTask");
        return this.gradableTaskList.get(index);
    }

    public int size() {
        logger.log(Level.INFO, "Get size of task list");
        return this.gradableTaskList.size();
    }

    public GradableTask getGradableTaskFromIndex(String input) {
        int index;
        GradableTask g = null;
        try {
            index = Integer.parseInt(input) - 1;
            if (isValidIndex(index)) {
                g = gradableTaskList.get(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printInvalidIndex();
        }
        return g;
    }

    boolean isValidIndex(int index) {
        if (index < 0) {
            Ui.printInvalidIndex();
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        int index = 1;
        String gradableTaskPrint = "";
        for (GradableTask g : gradableTaskList) {
            if (g != null) {
                gradableTaskPrint = gradableTaskPrint + String.valueOf(index) + " ";
                gradableTaskPrint = gradableTaskPrint + g.toString() + "\n";
                index++;
            }
        }
        return gradableTaskPrint;
    }

}

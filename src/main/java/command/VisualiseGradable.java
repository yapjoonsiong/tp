package command;

import task.GradableTask;
import task.GradableTaskList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VisualiseGradable {
    String[] signs = new String[]{"#", "@", "&", "+"};

    public GradableTaskList gradableTaskList;

    /**
     * Constructor for VisualiseGrdable.
     *
     * @param gl GradableTaskList to be visualised.
     */
    public VisualiseGradable(GradableTaskList gl) {
        assert (gl != null);
        this.gradableTaskList = gl;
    }

    private int getLength(GradableTask gradableTask) {
        return gradableTask.getWeightage();
    }

    private String printMidBar() {
        StringBuilder midLine = new StringBuilder();
        int count = 0;
        try {
            while (count < this.gradableTaskList.size()) {
                for (int i = 0; i < getLength(this.gradableTaskList.getGradableTask(count)); i++) {
                    midLine.append(signs[count % 4]);
                }
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        return midLine.toString();
    }

    private String printBottomBar() {
        StringBuilder bottomLine = new StringBuilder();
        StringBuilder legend = new StringBuilder();
        int count = 0;
        try {
            while (count < this.gradableTaskList.size()) {
                StringBuilder bottomLinePart = new StringBuilder();
                int descriptionLength = this.gradableTaskList.getGradableTask(count).getDescription().length();
                int weightageLength = getLength(this.gradableTaskList.getGradableTask(count));
                int dashLength = (weightageLength - 3) / 2;
                bottomLinePart.append("|");
                bottomLinePart.append("-".repeat(Math.max(0, (dashLength))));
                bottomLinePart.append(count + 1);
                bottomLinePart.append("-".repeat(Math.max(0, (dashLength))));
                while (bottomLinePart.toString().length() < weightageLength - 1) {
                    bottomLinePart.append("-");
                }
                bottomLinePart.append("|");
                bottomLine.append(bottomLinePart);
                legend.append(count + 1);
                legend.append(": ");
                legend.append(this.gradableTaskList.getGradableTask(count).getDescription());
                legend.append(System.lineSeparator());
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        bottomLine.append(System.lineSeparator());
        bottomLine.append(legend);
        return bottomLine.toString();
    }

    private String printTopBar() {
        StringBuilder topLine = new StringBuilder();
        int count = 0;
        try {
            while (count < this.gradableTaskList.size()) {
                StringBuilder linePart = new StringBuilder();
                int weightageLength = getLength(this.gradableTaskList.getGradableTask(count));
                int dashLength = (weightageLength - 5) / 2;
                linePart.append("<");
                linePart.append("=".repeat(Math.max(0, (dashLength))));
                linePart.append(this.gradableTaskList.getGradableTask(count).getWeightage());
                linePart.append("%");
                linePart.append("=".repeat(Math.max(0, (dashLength))));
                while (linePart.toString().length() < weightageLength - 1) {
                    linePart.append("=");
                }
                linePart.append(">");
                topLine.append(linePart);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        return topLine.toString();
    }

    private List<GradableTask> sortByDone() {
        List<GradableTask> sorted = new ArrayList<GradableTask>();
        sorted.addAll(this.gradableTaskList.getGradableTaskList());
        sorted.sort(new Comparator<GradableTask>() {
            @Override
            public int compare(GradableTask o1, GradableTask o2) {
                return Boolean.compare(o1.isDone(), o2.isDone());
            }
        });

        return sorted;
    }

    private void showByDone() {
        List<GradableTask> sorted = sortByDone();
        int count = 0;
        for (GradableTask g : sorted) {
            if (!g.isDone()) {
                count++;
            }
        }
        List<GradableTask> undone = sorted.subList(0, count);
        System.out.println("Uncompleted:");
        for (GradableTask g : undone) {
            System.out.println(g);
        }
        List<GradableTask> done = sorted.subList(count, sorted.size());
        System.out.println("Completed:");
        for (GradableTask g : done) {
            System.out.println(g);
        }
    }

    /**
     * Method to print the GradableTaskList to the terminal
     * Individual methods to print out each part of the visualised GradableTaskList is called in order.
     */
    public void print() {
        System.out.println("BREAKDOWN:");
        System.out.println(printTopBar());
        System.out.println(printMidBar());
        System.out.println(printBottomBar());
        System.out.print(this.gradableTaskList);
    }

}

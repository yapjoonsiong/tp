package command;

import task.GradableTask;
import task.GradableTaskList;

public class VisualiseGradable {
    String[] signs = new String[]{
            "#", "@", "&","+"
    };
    public GradableTaskList gradableTaskList;

    public VisualiseGradable(GradableTaskList gl){
        assert(gl != null);
        this.gradableTaskList = gl;
    }

    private int getLength(GradableTask gradableTask){
        return gradableTask.getWeightage();
    }

    private String printMidBar(){
        StringBuilder midLine = new StringBuilder();
        int count = 0;
        try {
            while (count < this.gradableTaskList.size()) {
                for (int i = 0; i < getLength(this.gradableTaskList.getGradableTask(count)); i++) {
                    midLine.append(signs[count % 4]);
                }
                count++;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        return midLine.toString();
    }

    private String printBottomBar(){
        StringBuilder bottomLine = new StringBuilder();
        int count = 0;
        try{
            while (count < this.gradableTaskList.size()) {
                StringBuilder bottomLinePart = new StringBuilder();
                int descriptionLength = this.gradableTaskList.getGradableTask(count).getDescription().length();
                int weightageLength = getLength(this.gradableTaskList.getGradableTask(count));
                int dashLength = (weightageLength - 2 - descriptionLength) / 2;
                bottomLinePart.append("|");
                bottomLinePart.append("-".repeat(Math.max(0, (dashLength))));
                bottomLinePart.append(this.gradableTaskList.getGradableTask(count).getDescription());
                bottomLinePart.append("-".repeat(Math.max(0, (dashLength))));
                while(bottomLinePart.toString().length() < weightageLength - 1){
                    bottomLinePart.append("-");
                }
                bottomLinePart.append("|");
                bottomLine.append(bottomLinePart.toString());
                count++;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        return bottomLine.toString();
    }

    private String printTopBar(){
        StringBuilder topLine = new StringBuilder();
        int count = 0;
        try{
            while (count < this.gradableTaskList.size()) {
                StringBuilder linePart = new StringBuilder();
                int weightageLength = getLength(this.gradableTaskList.getGradableTask(count));
                int dashLength = (weightageLength - 5) / 2;
                linePart.append("<");
                linePart.append("=".repeat(Math.max(0, (dashLength))));
                linePart.append(this.gradableTaskList.getGradableTask(count).getWeightage());
                linePart.append("%");
                linePart.append("=".repeat(Math.max(0, (dashLength))));
                while(linePart.toString().length() < weightageLength-1){
                    linePart.append("=");
                }
                linePart.append(">");
                topLine.append(linePart.toString());
                count++;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        return topLine.toString();
    }

    public void print(){
        System.out.println(printTopBar());
        System.out.println(printMidBar());
        System.out.println(printBottomBar());
    }

}

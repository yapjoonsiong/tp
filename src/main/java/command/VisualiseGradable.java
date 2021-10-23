package command;

import task.GradableTask;
import task.GradableTaskList;

public class VisualiseGradable {
    String[] signs = new String[]{
            "#", "@", "&","+"
    };
    public GradableTaskList gradableTaskList;

    public VisualiseGradable(GradableTaskList gl){
        this.gradableTaskList = gl;
    }

    private int getLength(GradableTask gradableTask){
        return gradableTask.getWeightage()/2;
    }

    private String printTopBar(){
        StringBuilder topLine = new StringBuilder();
        int count = 0;

        while(gradableTaskList.get(count) != null){
            for(int i = 0; i  < getLength(gradableTaskList.getGradableTask(count)); i++){
                topLine.append(signs[count % 4]);
            }
            count++;
        }
        return topLine.toString();
    }

    public void print(){
        System.out.println(printTopBar());
    }

}

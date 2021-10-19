package semester;

import module.Module;
import module.Schedule;

import java.util.ArrayList;

public class SemesterList {
    protected int credits;
    protected double points;
    protected double cap;
    protected int accessedSemesterIndex;
    private ArrayList<Semester> semesterList;

    public SemesterList() {
        this.accessedSemesterIndex = 0;
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.semesterList = new ArrayList<>();
        setUp();
    }

    private void setUp() {
        for (int i = 0; i < 10; i++) {
            int year = (i / 2) + 1;
            int sem = (i % 2) + 1;
            String semesterDescription = "Y" + year + "S" + sem;
            //Semester semester = new Semester(semesterDescription);
            add(semesterDescription);
        }
    }

    public void add(Semester semester) {
        this.semesterList.add(semester);
    }

    public void add(String semesterDescription) {
        Semester semester = new Semester(semesterDescription);
        this.semesterList.add(semester);
    }

    public void listSemesters() {
        int i = 1;
        for (Semester semester : semesterList) {
            System.out.println(i + " : " + semester.getSem());
            i++;
        }
    }

    public Semester getAccessedSemester() {
        return this.semesterList.get(getAccessedSemesterIndex());
    }

    private void updateCredits() {
        int c = 0;
        for (Semester semester : semesterList) {
            c += semester.getCredits();
        }
        credits = c;
    }

    private void updatePoints() {
        double p = 0;
        for (Semester semester : semesterList) {
            p += semester.getPoints();
        }
        points = p;
    }

    private void updateCap() {
        updateCredits();
        updatePoints();
        cap = points / credits;
    }

    public void setAccessedSemesterIndex(int i) {
        this.accessedSemesterIndex = i - 1;
    }

    public double getCap() {
        updateCap();
        return cap;
    }

    public int getAccessedSemesterIndex() {
        return accessedSemesterIndex;
    }

    public void visualiseCap() {
        System.out.print("Cumulative CAP: " + getCap());
        for (Semester semester : semesterList) {
            System.out.println(semester.getSem() + ": " + semester.getCap());
        }
        /*for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 50; j++) {
                if (j < 3) {
                    if (i >= 5 && i <= 30 && i % 5 == 0) {
                        System.out.print((6 - (5/ i)) + ".00");
                    } else {
                        System.out.print("    ");
                    }
                    j += 3;
                }
                if (j == 4 && i <= 30 || i == 30 && j > 4) {
                    System.out.print("#");
                }
                if (i == 31 && j % 5 == 0) {
                    //print semester label
                }

            }
        }*/

    }
}

package Semester;

import java.util.ArrayList;

public class SemesterList {
    protected int credits;
    protected double points;
    protected double cap;
    private ArrayList<Semester> semesterList;

    public SemesterList() {
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.semesterList = new ArrayList<>();
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

    public double getCap() {
        updateCap();
        return cap;
    }

    public void visualiseCap() {
        System.out.print("Cumulative CAP: " + getCap());
        for (Semester semester : semesterList) {
            System.out.println(semester.getSemester() + ": " + semester.getCap());
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


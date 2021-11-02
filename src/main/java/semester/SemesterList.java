package semester;

import exceptions.NoCapExceptions;

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

    //Getters fo Json serialization and deserialization
    public int getCredits() {
        return credits;
    }

    public double getPoints() {
        return points;
    }

    public ArrayList<Semester> getSemesterList() {
        return semesterList;
    }

    /**
     * Method to add 10 Semesters to semesterList by default in the constructor.
     */
    private void setUp() {
        for (int i = 0; i < 10; i++) {
            int year = (i / 2) + 1;
            int sem = (i % 2) + 1;
            String semesterDescription = "Y" + year + "S" + sem;
            add(semesterDescription);
        }
    }

    private void add(Semester semester) {
        this.semesterList.add(semester);
    }

    /**
     * Method to add a Semester to semesterList.
     *
     * @param semesterDescription User input description of the semester.
     */
    public void add(String semesterDescription) {
        Semester semester = new Semester(semesterDescription);
        this.semesterList.add(semester);
    }

    /**
     * Method to print existing semesters in the semesterList.
     */
    public void printSemesters() {
        int i = 1;
        for (Semester semester : semesterList) {
            System.out.println(i + " : " + semester.getSemester());
            i++;
        }
    }

    /**
     * Method to return the Semester with the corresponding accessedSemesterIndex.
     *
     * @return Semester object.
     */
    public Semester extractAccessedSemester() {
        return this.semesterList.get(getAccessedSemesterIndex());
    }

    private void updateCredits() {
        int c = 0;
        for (Semester semester : semesterList) {
            semester.updateCredits();
            c += semester.getCredits();
        }
        credits = c;
    }

    private void updatePoints() {
        double p = 0;
        for (Semester semester : semesterList) {
            semester.updatePoints();
            p += semester.getPoints();
        }
        points = p;
    }

    /**
     * Method to update the aggregate CAP of all Semesters in semesterList.
     */
    public void updateCap() throws NoCapExceptions {
        updateCredits();
        updatePoints();
        if (credits == 0) {
            throw new NoCapExceptions("Unable to calculate cap as no credit assigned to any existing module");
        }
        cap = points / credits;
    }

    /**
     * Method to set accessedSemesterIndex.
     *
     * @param i User input index.
     */
    public void setAccessedSemesterIndex(int i) {
        if (i < 0 || i > 9) {
            throw new ArrayIndexOutOfBoundsException("Please key in a number from 1-10");
        }
        this.accessedSemesterIndex = i;
    }

    public double getCap() {
        return cap;
    }

    public int getAccessedSemesterIndex() {
        return accessedSemesterIndex;
    }

    /**
     * Method to get the Semester with the corresponding index in semesterList.
     *
     * @param index User input.
     * @return Semester object.
     */
    public Semester get(int index) {
        if (index < 0 || index > 9) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.semesterList.get(index);
    }

    /**
     * Method to print aggregated CAP and CAP of each Semester.
     */
    public void printAllCap() throws NoCapExceptions {
        updateCap();
        System.out.println("Cumulative CAP: " + (String)String.format("%.2f", getCap()));
        for (Semester semester : semesterList) {
            if (semester.getCredits() > 0) {
                System.out.println(semester.getSemester() + ": " + (String)String.format("%.2f", semester.getCap()));
            } else {
                System.out.println(semester.getSemester() + ": 0.00");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Cumulative Credits: ").append(this.credits).append(System.lineSeparator())
                .append("Points: ").append(this.points).append(System.lineSeparator())
                .append("CAP: ").append(this.cap).append(System.lineSeparator())
                .append("Semesters: ").append(System.lineSeparator());
        int semesterCount = 1;
        for (Semester semester : semesterList) {
            string.append(semesterCount).append(". ").append(System.lineSeparator())
                    .append(semester).append(System.lineSeparator());
        }
        return string.toString();
    }
}

package semester;

import command.NoCap;
import exceptions.NoCapExceptions;
import module.ModuleList;
import module.Module;


public class Semester {
    protected int credits;
    protected double points;
    protected double cap;
    protected String semester;
    public ModuleList moduleList;

    public Semester(String semester) {
        this.semester = semester;
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.moduleList = new ModuleList();
    }

    /**
     * For deserialization from JSON file.
     */
    public Semester() {
    }

    /**
     * Method to update number of credits taken in the Semester.
     * Iterates through every Module object in ModuleList and adds up each module's credits.
     */
    protected void updateCredits() {
        int c = 0;
        for (Module module : moduleList.getModuleList()) {
            if (module.getCredits() > 0) {
                c += module.getCredits();
            }
        }
        credits = c;
    }

    /**
     * Method to update the weighted points earned in the Semester.
     * Iterates through every Module Object in ModuleList to return their grade points.
     * Multiplies grade points with the number of credits to get weighted points earned.
     */
    protected void updatePoints() {
        double p = 0;
        for (Module module : moduleList.getModuleList()) {
            if (module.getCredits() > 0) {
                p += module.getCredits() * module.getPoints();
            }
        }
        points = p;
    }

    public void updateCap() throws NoCapExceptions {
        updateCredits();
        updatePoints();
        if (credits == 0) {
            throw new NoCapExceptions("Unable to calculate cap as no credit assigned to any existing module");
        }
        cap = points / credits;
    }

    public int getCredits() {
        return credits;
    }

    public double getPoints() {
        return points;
    }

    public double getCap() {
        return cap;
    }

    public ModuleList getModuleList() {
        return this.moduleList;
    }

    public String getSemester() {
        return this.semester;
    }

    public void printCap() throws NoCapExceptions {
        updateCap();
        System.out.println("This semester's CAP: " + (String)String.format("%.2f", getCap()));
    }

    @Override
    public String toString() {
        return "Credits: " + this.credits + System.lineSeparator()
                + "Points: " + this.points + System.lineSeparator()
                + "CAP: " + (this.cap) + System.lineSeparator()
                + "Semester: " + this.semester + System.lineSeparator()
                + "Modules: " + this.moduleList + System.lineSeparator();
    }
}

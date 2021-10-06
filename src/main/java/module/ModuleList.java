package module;

import java.util.ArrayList;
import java.util.Objects;

public class ModuleList {

    private ArrayList<Module> moduleList;

    public ModuleList() {
        this.moduleList = new ArrayList<>();
    }

    public void add(Module module) {
        this.moduleList.add(module);
    }

    public void delete(Module module) {
        moduleList.remove(module);
    }

    public int size() {
        return this.moduleList.size();
    }

    public Module get(int index) {
        return this.moduleList.get(index);
    }

    public void printModules() {
        for (int i = 0; i < moduleList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(moduleList.get(i));
        }
    }

    private String formatTimeString(int time) {
        String timeString;
        if (time < 10) {
            timeString = "0" + time + "00";
        } else {
            timeString = time + "00";
        }
        return timeString;
    }

    private int moduleAtTime(String timeString, int r) {
        int moduleIndex = -1;
        String day;
        if (r <= 6) {
            day = "MON";
        } else if (r <= 11) {
            day = "TUE";
        } else if (r <= 16) {
            day = "WED";
        } else if (r <= 21) {
            day = "THU";
        } else if (r <= 26) {
            day = "FRI";
        } else {
            day = "SAT";
        }
        for (int i = 0; i < moduleList.size(); i++) {
            for (int j = 0; j < moduleList.get(i).size(); j++) {
                if (Objects.equals(moduleList.get(i).get(j).getDay(), day)
                        && Objects.equals(moduleList.get(i).get(j).getStartTime(), timeString)) {
                    moduleIndex = i;
                    //i = moduleList.size();
                    break;
                }
            }
        }
        return moduleIndex;
    }

    private int classAtTime(String timeString, int r) {
        int classIndex = 0;
        String day;
        if (r >= 3 && r <= 6) {
            day = "MON";
        } else if (r >= 8 && r <= 11) {
            day = "TUE";
        } else if (r >= 13 && r <= 16) {
            day = "WED";
        } else if (r >= 18 && r <= 21) {
            day = "THU";
        } else if (r >= 23 && r <= 26) {
            day = "FRI";
        } else {
            day = "SAT";
        }
        for (int i = 0; i < moduleList.size(); i++) {
            for (int j = 0; j < moduleList.get(i).size(); j++) {
                if (Objects.equals(moduleList.get(i).get(j).getDay(), day)
                        && Objects.equals(moduleList.get(i).get(j).getStartTime(), timeString)) {
                    classIndex = j;
                    break;
                }
            }
        }
        return classIndex;
    }

    public void printTimeTable() {
        int height = 33;
        int length = 207;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < length; c++) {
                if ((c + 12) % 20 == 0) {
                    int time = ((c + 12) / 20) + 7;
                    String timeString = formatTimeString(time);
                    if (r == 1) { // print time header
                        System.out.print(timeString);
                        c += 4;
                    } else if ((r + 2) % 5 == 0) { //print module name if class exists in timeslot
                        int moduleIndex = moduleAtTime(timeString, r);
                        if (moduleIndex >= 0) {
                            System.out.print(moduleList.get(moduleIndex).moduleName);
                            c += moduleList.get(moduleIndex).moduleName.length();
                        }
                    } else if ((r + 1) % 5 == 0) { //print class location
                        int moduleIndex = moduleAtTime(timeString, r);
                        int classIndex = classAtTime(timeString, r);
                        if (moduleIndex >= 0) {
                            System.out.print(moduleList.get(moduleIndex).get(classIndex).getLocation());
                            c += moduleList.get(moduleIndex).get(classIndex).getLocation().length();
                        }
                    } else if (r > 0 && r % 5 == 0) { //print class comment
                        int moduleIndex = moduleAtTime(timeString, r);
                        int classIndex = classAtTime(timeString, r);
                        if (moduleIndex >= 0) {
                            System.out.print(moduleList.get(moduleIndex).get(classIndex).getComment());
                            c += moduleList.get(moduleIndex).get(classIndex).getComment().length();
                        }
                    }
                } else if ((r + 2) % 5 == 0 && c == 2) { //print day
                    int day = (r + 2) / 5;
                    switch (day) {
                    case 1:
                        System.out.print("MON");
                        break;
                    case 2:
                        System.out.print("TUE");
                        break;
                    case 3:
                        System.out.print("WED");
                        break;
                    case 4:
                        System.out.print("THU");
                        break;
                    case 5:
                        System.out.print("FRI");
                        break;
                    case 6:
                        System.out.print("SAT");
                        break;
                    default:
                        System.out.print("INVALID");
                    }
                    c += 2;
                } else if (c == length - 1) {
                    System.out.println("#");
                } else if ((r - 2) % 5 == 0 || r == 0 || r == height - 1 || (c - 6) % 20 == 0 || c == 0) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

}

//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;

import Files.FileHandler;

import java.util.ArrayList;

import static java.lang.String.format;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDiscipline;
    private FileHandler files = new FileHandler();

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
    }

    public DisciplineEnum getDiscipline() {
        return swimDiscipline;
    }

    public boolean hasPracticeTime() {
        ArrayList<Training> times = files.getAllSavedTimes();
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                return true;
            }
        }
        return false;
    }

    public String basisStringForSaveMember() {
        return getName() + ";" + getAge() + ";" + getActive();
    }

    public String getStringForSaving() {
        if (getDiscipline() == null) {
            return basisStringForSaveMember();
        } else {
            return basisStringForSaveMember() + ";"  + getDiscipline();
        }
    }

    @Override
    public String toString() {
        if (swimDiscipline == null) {
            return basisMemberToString() +
                    "----------------------------------------------\n";
        } else {
            return basisMemberToString() +
                   "Svømmedisciplin: " + swimDiscipline + '\n' +
                   "----------------------------------------------\n";
        }
    }

    public ArrayList<Training> getTimes() {
        ArrayList<Training> result = new ArrayList<>();
        ArrayList<Training> times = files.getAllSavedTimes();
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                result.add(time);
            }
        }
        return result;
    }

    public Training getBestTime() {
        ArrayList<Training> swimmerTimes = getTimes();
        swimmerTimes.sort(new Sorting());
        if (swimmerTimes.size() > 0) {
            return swimmerTimes.get(0);
        } else {
            return null;
        }
    }


    //return format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", member.getName(), "|", member.getAge(), "|", member.getActive(), "|", member.getDiscipline(), "|", trainingTime);
}

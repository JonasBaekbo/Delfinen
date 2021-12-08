//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;


import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDiscipline;

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
    }

    public DisciplineEnum getDiscipline() {
        return swimDiscipline;
    }

    public boolean hasPracticeTime(ArrayList<Training> times) {
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Training> getTimes(ArrayList<Training> times) {
        ArrayList<Training> result = new ArrayList<>();
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                result.add(time);
            }
        }
        return result;
    }

    public Training getBestTime(ArrayList<Training> times) {
        ArrayList<Training> swimmerTimes = getTimes(times);
        swimmerTimes.sort(new Sorting());
        if (swimmerTimes.size() > 0) {
            return swimmerTimes.get(0);
        } else {
            return null;
        }
    }

    public String stringForSaving() {
        if (getDiscipline() == null) {
            return getName() + ";" + getAge() + ";" + getActive();
        } else {
            return getName() + ";" + getAge() + ";" + getActive() + ";" + getDiscipline();
        }
    }

    @Override
    public String toString() {
        //Vi "oversætter" true/false da bruger fladen er på dansk
        String activeStatus;
        if (getActive()) {
            activeStatus = "Ja";
        } else {
            activeStatus = "Nej";
        }
        if (swimDiscipline == null) {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktiv: " + activeStatus + '\n' +
                    "----------------------------------------------\n";
        } else {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktiv: " + activeStatus + '\n' +
                    "Svømmedisciplin: " + swimDiscipline + '\n' +
                    "----------------------------------------------\n";
        }
    }
}

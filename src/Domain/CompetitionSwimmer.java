//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;


import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDiscipline;
    // private FileHandler files = new FileHandler();
    //TODO: fjern fra diagram

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
    }

    public DisciplineEnum getDiscipline() {
        return swimDiscipline;
    }


    //TODO: ændre diagram så den får arraylist med
    public boolean hasPracticeTime(ArrayList<Training> times) {
        //ArrayList<Training> times = files.getAllSavedTimes();
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                return true;
            }
        }
        return false;
    }

    //TODO: fjern fra diagrammer
   /* public String basisStringForSaveMember() {
        return getName() + ";" + getAge() + ";" + getActive();
    }*/

    public String getStringForSaving() {
        if (getDiscipline() == null) {
            return getName() + ";" + getAge() + ";" + getActive();
        } else {
            return getName() + ";" + getAge() + ";" + getActive() + ";" + getDiscipline();
        }
    }

    @Override
    public String toString() {
        if (swimDiscipline == null) {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktiv: " + isActive + '\n' +
                    "----------------------------------------------\n";
        } else {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktiv: " + isActive + '\n' +
                    "Svømmedisciplin: " + swimDiscipline + '\n' +
                    "----------------------------------------------\n";
        }
    }

    //TODO: ændre diagram så den får arraylist med
    public ArrayList<Training> getTimes(ArrayList<Training> times) {
        ArrayList<Training> result = new ArrayList<>();
        //AArrayList<Training> times
        for (Training time : times) {
            if (time.getMemberName().equalsIgnoreCase(getName())) {
                result.add(time);
            }
        }
        return result;
    }

    //TODO: ændre diagram så den får arraylist med
    public Training getBestTime(ArrayList<Training> times) {
        ArrayList<Training> swimmerTimes = getTimes(times);
        swimmerTimes.sort(new Sorting());
        if (swimmerTimes.size() > 0) {
            return swimmerTimes.get(0);
        } else {
            return null;
        }
    }
}

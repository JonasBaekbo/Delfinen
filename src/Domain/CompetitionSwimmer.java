//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.String.format;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDiscipline;
    private LocalTime practiceTime = null;
    private LocalDate practiceDate;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ArrayList<Competition> competitions = new ArrayList<>();
    private LocalDate competitionDate;

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline, LocalTime practiceTime, LocalDate practiceDate) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
        this.practiceTime = practiceTime;
        this.practiceDate = practiceDate;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDiscipline, LocalTime practiceTime, LocalDate practiceDate, String competitionName, String place, LocalDate competitionDate, LocalTime competitionTime) {
        super(name, age, isActive);
        this.swimDiscipline = swimDiscipline;
        this.practiceTime = practiceTime;
        this.practiceDate = practiceDate;
        this.competitionDate = competitionDate;
        addCompetition(new Competition(competitionName, place, competitionDate, competitionTime));
    }

    public DisciplineEnum getSwimDiscipline() {
        return swimDiscipline;
    }

    public LocalTime getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(LocalTime practiceTime) {
        this.practiceTime = practiceTime;
    }

    public void setPracticeDate(String practiceDate) {
        this.practiceDate = LocalDate.parse(practiceDate, dateFormatter);
    }

    public LocalDate setCompetitionDate(String competitionDate) {
        this.competitionDate = LocalDate.parse(competitionDate, dateFormatter);
        return this.competitionDate;
    }

    public LocalDate getPracticeDate() {
        return practiceDate;
    }

    public void addCompetition(Competition competition) {
        competitions.clear();
        competitions.add(competition);
    }

    public Competition getCompetition() {
        return competitions.get(0);
    }

    public ArrayList<Competition> getCompetitions() { return competitions; }

    public String basisStringForSaveMember() {
        return getName() + ";" + getAge() + ";" + getActive();
    }

    public String getStringForSaving() {
        if (getSwimDiscipline() == null) {
            return basisStringForSaveMember();
        } else if (getPracticeTime() == null) {
            return basisStringForSaveMember() + ";"
                    + getSwimDiscipline();
        } else if (getCompetitions().size() == 0) {
            return basisStringForSaveMember() + ";"
                    + getSwimDiscipline() + ";"
                    + getPracticeTime() + ";"
                    + getPracticeDate();
        } else {
            Competition competition = getCompetition();
            return basisStringForSaveMember() + ";"
                    + getSwimDiscipline() + ";"
                    + getPracticeTime() + ";"
                    + getPracticeDate() + ";"
                    + competition.getConvention() + ";"
                    + competition.getConventionPlace() + ";"
                    + competition.getCompetitionDate() + ";"
                    + competition.getCompetitionTime();
        }
    }

    @Override
    public String toString() {
        if (swimDiscipline == null) {
            return basisToString() +
                    "----------------------------------------------\n";
        } else if (practiceTime == null) {
            return basisToString() +
                    "Svømmedisciplin: " + swimDiscipline + '\n' +
                    "----------------------------------------------\n";
        } else if (competitions.isEmpty()) {
            return basisToString() +
                    "Svømmedisciplin: " + swimDiscipline + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "----------------------------------------------\n";
        } else {
            Competition competition = getCompetition();
            return basisToString() +
                    "Svømmedisciplin: " + swimDiscipline + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "stævne navn: " + competition.getConvention() + '\n' +
                    "placering: " + competition.getConventionPlace() + '\n' +
                    "Stævne dato " + competition.getCompetitionDate() + '\n' +
                    "Stævne tid: " + competition.getCompetitionTime() + '\n' +
                    "----------------------------------------------\n";
        }
    }

    public String informationToTable() {
        return format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", getName(), "|", getAge(), "|", getActive(), "|", getSwimDiscipline(), "|", getPracticeTime());
    }
}

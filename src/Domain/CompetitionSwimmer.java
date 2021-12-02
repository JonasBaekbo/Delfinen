package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.String.format;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDisciplin;
    private LocalTime practiceTime = null;
    private LocalDate practiceDate;
    private ArrayList<Competition> competitions = new ArrayList<>();
    private LocalDate competitionDate;

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDisciplin) {
        super(name, age, isActive);
        this.swimDisciplin = swimDisciplin;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDisciplin, LocalTime practiceTime, LocalDate practiceDate) {
        super(name, age, isActive);
        this.swimDisciplin = swimDisciplin;
        this.practiceTime = practiceTime;
        this.practiceDate = practiceDate;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDisciplin, LocalTime practiceTime, LocalDate practiceDate, String competitionName, String place, LocalDate competitionDate, LocalTime competitionTime) {
        super(name, age, isActive);
        this.swimDisciplin = swimDisciplin;
        this.practiceTime = practiceTime;
        this.practiceDate = practiceDate;
        this.competitionDate = competitionDate;
        addCompetition(new Competition(competitionName, place, competitionDate, competitionTime));
    }

    public DisciplineEnum getSwimDisciplin() {
        return swimDisciplin;
    }

    public LocalTime getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(LocalTime practiceTime) {
        this.practiceTime = practiceTime;
    }

    public void setPracticeDate(String practiceDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.practiceDate = LocalDate.parse(practiceDate, formatter);
    }

    public LocalDate setCompetitionDate (String competitionDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.competitionDate = LocalDate.parse(competitionDate, formatter);
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

    public ArrayList<Competition> getCompetitions() {
        return competitions;

    }


//TODO: bør basisString være her også?
    public String saveMember() {

        if (getSwimDisciplin() == null) {
            return basisString();

        } else if (getPracticeTime() == null) {
            return basisString() + ";" + getSwimDisciplin();

        } else if (getCompetitions().size() == 0) {
            return basisString() + ";" + getSwimDisciplin() + ";" + getPracticeTime() + ";" + getPracticeDate();

        } else {
            Competition competition = getCompetition();
            return basisString() + ";" + getSwimDisciplin() + ";" + getPracticeTime() + ";" + getPracticeDate() + ";" +
                    competition.getConvention() + ";" + competition.getConventionPlace() + ";" +competition.getCompetitionDate() + ";" + competition.getCompetitionTime();


    }}

    @Override
    public String toString() {
        if (swimDisciplin == null) {
            return basisToString() +
                    "----------------------------------------------" + '\n' + "";
        } else if (practiceTime == null) {
            return basisToString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (competitions.isEmpty()) {
            return basisToString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else {
            Competition competition = getCompetition();
            return basisToString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "stævne navn: " + competition.getConvention() + '\n' +
                    "placering: " + competition.getConventionPlace() + '\n' +
                    "Stævne dato " + competition.getCompetitionDate() + '\n' +
                    "Stævne tid: " + competition.getCompetitionTime() + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }
    }


    //TODO: find ud af om det er det "tilladt" at sende strenge i format
    public String informationToTable() {
        return format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", getName(), "|", getAge(), "|", getActive(), "|", getSwimDisciplin(), "|", getPracticeTime());}

}

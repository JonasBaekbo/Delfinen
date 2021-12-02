package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.String.format;

public class CompetitionSwimmer extends Member {
    private DisciplineEnum swimDisciplin;
    //TODO: er time i korrekt format, tror det er i timer,minutter og sekunder og ikke mm:ss:MMMM?
    private LocalTime practiceTime = null;
    private LocalDate practiceDate;
    private ArrayList<Competition> competitions = new ArrayList<>();

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

    public CompetitionSwimmer(String name, String age, boolean isActive, DisciplineEnum swimDisciplin, LocalTime practiceTime, LocalDate practiceDate, String competitonName, String place, LocalTime competitontime) {
        super(name, age, isActive);
        this.swimDisciplin = swimDisciplin;
        this.practiceTime = practiceTime;
        this.practiceDate = practiceDate;
        addCompetition(new Competition(competitonName, place, competitontime));
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
        LocalDate dateToAdd = LocalDate.parse(practiceDate, formatter);
        this.practiceDate = dateToAdd;
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

    public String addCompetitionAndTimeAndDateTooMember() {

        if (getSwimDisciplin() == null) {
            return basisString();

        } else if (getPracticeTime() == null) {
            return basisString() + ";" + getSwimDisciplin();

        } else if (getCompetitions().size() == 0) {
            return basisString() + ";" + getSwimDisciplin() + ";" + getPracticeTime() + ";" + getPracticeDate();

        } else {
            Competition competition = getCompetition();
            return basisString() + ";" + getSwimDisciplin() + ";" + getPracticeTime() + ";" + getPracticeDate() + ";" +
                    competition.getConvention() + ";" + competition.getConventionPlace() + ";" + competition.getConventionTime();

        }
    }

    public String saveNewMember() {

        if (getSwimDisciplin() == null) {
            return basisString();

        } else if (getPracticeTime() == null) {
            return basisString() + ";" + getSwimDisciplin();

        } else {
            return basisString() + ";" + getSwimDisciplin() + ";" + getPracticeTime();
        }


    }
    @Override
    public String toString() {
        if (swimDisciplin == null) {
            return basisToStringString() +
                    "----------------------------------------------" + '\n' + "";
        } else if (practiceTime == null) {
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (competitions.isEmpty()) {
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else {
            Competition competition = getCompetition();
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + practiceTime + '\n' +
                    "stævne navn: " + competition.getConvention() + '\n' +
                    "placering: " + competition.getConventionPlace() + '\n' +
                    "Stævne tid: " + competition.getConventionTime() + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }
    }


    //TODO: find ud af om det er det "tilladt" at sende strenge i format
    public String informationToTable() {
        String tableContent = format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s %-20s", getName(), "|", getAge(), "|", getActive(), "|", getSwimDisciplin(), "|", getPracticeTime());
        return tableContent;}

}

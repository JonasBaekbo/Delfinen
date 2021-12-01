package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    //TODO: enum?
    private String swimDisciplin = null;
    //TODO: er time i korrekt format?
    private LocalTime swimTime = null;
    private LocalDate swimDate;
    private ArrayList<Competition> competitions = new ArrayList<>();

    public CompetitionSwimmer(String name, String age, boolean isActive, String svømmediciplin) {
        super(name, age, isActive);
        this.swimDisciplin = svømmediciplin;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, String svømmediciplin, LocalTime time, LocalDate date) {
        super(name, age, isActive);
        this.swimDisciplin = svømmediciplin;
        this.swimTime = time;
        this.swimDate = date;
    }

    public CompetitionSwimmer(String name, String age, boolean isActive, String svømmediciplin, LocalTime time, LocalDate date, String competitonName, String place, LocalTime competitontime) {
        super(name, age, isActive);
        this.swimDisciplin = svømmediciplin;
        this.swimTime = time;
        this.swimDate = date;
        addCompetition(new Competition(competitonName, place, competitontime));
    }

    public String getSwimDisciplin() {
        return swimDisciplin;
    }

    public LocalTime getSwimTime() {
        return swimTime;
    }

    public void setSwimTime(LocalTime swimTime) {
        this.swimTime = swimTime;
    }

    public void setSwimDate(String swimDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateToAdd = LocalDate.parse(swimDate, formatter);
        this.swimDate = dateToAdd;
    }

    public LocalDate getSwimDate() {
        return swimDate;
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

        } else if (getSwimTime() == null) {
            return basisString() + ";" + getSwimDisciplin();

        } else if (getCompetitions().size() == 0) {
            return basisString() + ";" + getSwimDisciplin() + ";" + getSwimTime() + ";" + getSwimDate();

        } else {
            Competition competition = getCompetition();
            return basisString() + ";" + getSwimDisciplin() + ";" + getSwimTime() + ";" + getSwimDate() + ";" +
                    competition.getConvention() + ";" + competition.getConventionPlace() + ";" + competition.getConventionTime();

        }
    }

    public String saveNewMember() {

        if (getSwimDisciplin() == null) {
            return basisString();

        } else if (getSwimTime() == null) {
            return basisString() + ";" + getSwimDisciplin();

        } else {
            return basisString() + ";" + getSwimDisciplin() + ";" + getSwimTime();
        }


    }

    @Override
    public String toString() {
        if (swimDisciplin == null) {
            return basisToStringString() +
                    "----------------------------------------------" + '\n' + "";
        } else if (swimTime == null) {
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (competitions.isEmpty()) {
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + swimTime + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else {
            Competition competition = getCompetition();
            return basisToStringString() +
                    "Svømmedisciplin: " + swimDisciplin + '\n' +
                    "tid: " + swimTime + '\n' +
                    "stævne navn: " + competition.getConvention() + '\n' +
                    "placering: " + competition.getConventionPlace() + '\n' +
                    "Stævne tid: " + competition.getConventionTime() + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }
    }
}

//@ Adam Lasson, @Johanne Riis-Weitling
package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Member {
    private String name;
    private String age;
    private String activityForm; //e.g. motionist eller konkurrencesvømmer.
    private String activityLevel; //e.g. Aktivt eller passivt medlem.
    private String svømmediciplin = null;
    private LocalTime time = null;
    private LocalDate date;
    private ArrayList<Competition> competitions = new ArrayList<>();


    public Member(String name, String age, String activityForm, String activityLevel) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
    }

    public Member(String name, String age, String activityForm, String activityLevel, String svømmediciplin) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
    }

    public Member(String name, String age, String activityForm, String activityLevel, String svømmediciplin, LocalTime time, LocalDate date) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
        this.time = time;
        this.date = date;
    }

    public Member(String name, String age, String activityForm, String activityLevel, String svømmediciplin,
                  LocalTime time, LocalDate date, String competitonName, String place, LocalTime competitontime) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
        this.time = time;
        this.date = date;
        addCompetition(new Competition(competitonName, place, competitontime));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getActivityForm() {
        return activityForm;
    }

    public void setActivityForm(String activityForm) {
        this.activityForm = activityForm;

    }


    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getSwimmingDiscipline() {
        return svømmediciplin;
    }

    public void setSvømmediciplin(String svømmediciplin) {
        this.svømmediciplin = svømmediciplin;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateToAdd = LocalDate.parse(date, formatter);
        this.date = dateToAdd;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addCompetition(Competition c) {
        competitions.clear();
        competitions.add(c);

    }

    public Competition getCompetition() {
        return competitions.get(0);
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;

    }

    public String basisString() {
        return getName() + ";" + getAge() + ";" + getActivityForm() + ";" + getActivityLevel();

    }

    public String saveNewMember() {

        if (getSwimmingDiscipline() == null) {
            return basisString();

        } else if (getTime() == null) {
            return basisString() + ";" + getSwimmingDiscipline();

        } else {
            return basisString() + ";" + getSwimmingDiscipline() + ";" + getTime();
        }


    }

    public String addCompetitonAndTimeAndDateTooMember() {

        if (getSwimmingDiscipline() == null) {
            return basisString();

        } else if (getTime() == null) {
            return basisString() + ";" + getSwimmingDiscipline();

        } else if (getCompetitions().size() == 0) {
            return basisString() + ";" + getSwimmingDiscipline() + ";" + getTime() + ";" + getDate();

        } else {
            Competition competition = getCompetition();
            return basisString() + ";" + getSwimmingDiscipline() + ";" + getTime() + ";" + getDate() + ";" +
                    competition.getConvention() + ";" + competition.getPlace() + ";" + competition.getTime();

        }
    }

    public String getInvoiceLine() {
        return getName() + "; " + getAge() + ";" + getActivityLevel();
    }

    public String basisToStringString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktivitetsform: " + activityForm + '\n' +
                "Medlemskabs status: " + activityLevel + '\n';
    }

    @Override
    public String toString() {
        if (svømmediciplin == null) {
            return basisToStringString() +
                    "----------------------------------------------" + '\n' + "";
        } else if (time == null) {
            return basisToStringString() +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (competitions.isEmpty()) {
            return basisToStringString() +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "tid: " + time + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else {
            Competition competition = getCompetition();
            return basisToStringString() +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "tid: " + time + '\n' +
                    "stævne navn: " + competition.getConvention() + '\n' +
                    "placering: " + competition.getPlace() + '\n' +
                    "Stævne tid: " + competition.getTime() + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }
    }
}



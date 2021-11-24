//@ Adam Lasson
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
    private ArrayList<Competitions> competitions= new ArrayList<>();



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

    @Override
    public String toString() {
        if (svømmediciplin == null) {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }else if (time == null){
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel+ '\n'+
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }else{
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel+ '\n'+
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "tid: " + time + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }
    }




    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getSvømmediciplin() {
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

    public void addCompetition(Competitions c){
        competitions.add(c);

    }

    public ArrayList<Competitions> getCompetitions() {
        return competitions;
    }
}

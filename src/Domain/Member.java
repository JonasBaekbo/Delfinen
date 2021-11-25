//@ Adam Lasson, Mikkel Sandell
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
    private ArrayList<Competitions> competitions = new ArrayList<>();


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
                  LocalTime time, LocalDate date, String competitonName, String place,  LocalTime competitontime) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
        this.time = time;
        this.date = date;
        addCompetition(new Competitions(competitonName, place, competitontime));

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
                    "Medlemskabs status: " + activityLevel + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (time == null) {
            return  "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemskabs status: " + activityLevel + '\n' +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "----------------------------------------------" + '\n' + "";
        } else if (competitions.isEmpty()){
            return  "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemskabs status: " + activityLevel + '\n' +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "tid: " + time + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }else{
            return  "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemskabs status: " + activityLevel + '\n' +
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
                    "tid: " + time + '\n' +
                    "stævne navn: " + getCompetition().getConvention() + '\n'+
                    "placering: " + getCompetition().getPlace() + '\n' +
                    "Stævne tid: " + getCompetition().getTime() + '\n' +
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
        competitions.clear();
        competitions.add(c);

    }

    public Competitions getCompetition(){
        return competitions.get(0);
    }

    public ArrayList<Competitions> getCompetitions() {
        return competitions;
    }


    public static String chooseSwimDesiplin(String svømmediciplinChosen) {
        String svømmediciplin="";

        if (svømmediciplinChosen.equals("1")) {
            svømmediciplin = "Butterfly";
        } else if (svømmediciplinChosen.equals( "2")) {
            svømmediciplin = "Crawl";
        } else if (svømmediciplinChosen.equals("3")) {
            svømmediciplin = "Rygcrawl";
        } else if (svømmediciplinChosen.equals( "4")) {
            svømmediciplin = "Brystsvømning";
        } else {
            svømmediciplin ="Ikke gyldigt indput";
        }
        return svømmediciplin;
    }


    public static String chooseActivityLevel(String userInput) {
        String activityLevel = "";
        if (userInput.equals("1")) {
            activityLevel = "Aktivt";
        } else if (userInput.equals("2")) {
            activityLevel = "Passivt";
        }
        return activityLevel;
    }

    public static String chooseActivityForm(String userInput) {
        String activityForm = "";
        if (userInput.equals( "1")) {
            activityForm = "Motionist";
        } else if (userInput.equals( "2")) {
            activityForm = "Konkurrence";
        }
        return activityForm;
    }

}



//@ Adam
package Domain;

public class Member {
    String name;
    String age;
    String activityForm; //e.g. motionist eller konkurrencesvømmer.
    String activityLevel; //e.g. Aktivt eller passivt medlem.
    String svømmediciplin = null;


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
        if (svømmediciplin ==null) {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel;
        }else{
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel+ '\n'+
                    "svømmediciplin: " + svømmediciplin;
        }
    }




    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}

package Domain;

public class Member {
    String name;
    String age;
    String activityForm; //e.g. motionist eller konkurrencesvømmer.
    String activityLevel; //e.g. Aktivt eller passivt medlem.


    public Member(String name, String age, String activityForm, String activityLevel){
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
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
        return  "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktivitetsform: " + activityForm + '\n' +
                "Medlemsskabs status: " + activityLevel;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}

public class Member {
    String name;
    int age;
    String activityForm; //e.g. junior- eller seniorsvømmer, motionist eller konkurrencesvømmer.
    String activityLevel; //e.g. Aktivt eller passivt medlem.


    public Member(String name, int age, String activityForm, String activityLevel){
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
}

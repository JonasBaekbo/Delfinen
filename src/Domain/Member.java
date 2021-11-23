//@ Adam Lasson
package Domain;

public class Member {
    private String name;
    private String age;
    private String activityForm; //e.g. motionist eller konkurrencesvømmer.
    private String activityLevel; //e.g. Aktivt eller passivt medlem.
    private String svømmediciplin = null;
    private String time;
    private boolean subPaid;


    public Member(String name, String age, String activityForm, String activityLevel) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.subPaid=false;
    }

    public Member(String name, String age, String activityForm, String activityLevel, String svømmediciplin) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
        this.subPaid=false;
    }
    public Member(String name, String age, String activityForm, String activityLevel, String svømmediciplin, String time) {
        this.name = name;
        this.age = age;
        this.activityForm = activityForm;
        this.activityLevel = activityLevel;
        this.svømmediciplin = svømmediciplin;
        this.time = time;
        this.subPaid=false;
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

    public void setSubPaid(String paid) {
        if(paid.equals("ja"))
            this.subPaid=true;
    }

    public boolean isSubPaid() {
        return subPaid;
    }

    @Override
    public String toString() {
        if (svømmediciplin == null) {
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel + '\n' +
                    "----------------------------------------------" + '\n' + "";
        }else{
            return "Medlemsnavn: " + name + '\n' +
                    "Alder: " + age + '\n' +
                    "Aktivitetsform: " + activityForm + '\n' +
                    "Medlemsskabs status: " + activityLevel+ '\n'+
                    "Svømmedisciplin: " + svømmediciplin + '\n' +
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

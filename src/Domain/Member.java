//@ Adam Lasson, @Johanne Riis-Weitling
package Domain;


import java.util.ArrayList;

public class Member {
    private String name;
    private String age;
    //private boolean activityForm; //e.g. motionist eller konkurrencesvømmer.
    private boolean isActive; //e.g. Aktivt eller passivt medlem.


    public Member(String name, String age, boolean isActive) {
        this.name = name;
        this.age = age;
        //this.activityForm = activityForm;
        this.isActive = isActive;
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

   /* public String getActivityForm() {
        return activityForm;
    }

    public void setActivityForm(String activityForm) {
        this.activityForm = activityForm;

    }*/


    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


    public String basisString() {
        return getName() + ";" + getAge() + ";" + getActive();

    }

    public String saveNewMember() {
        return basisString();

    }


    public String getInvoiceLine() {
        if (getActive()) {
            return getName() + "; " + getAge() + "; Aktivt medlemskab";
        } else {
            return getName() + "; " + getAge() + "; Passivt medlemskab";
        }
    }

    public String basisToStringString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Medlemskabs status: " + isActive + '\n';
    }

    @Override
    public String toString() {
        return basisToStringString() +
                "----------------------------------------------" + '\n' + "";
    }


    public Member findMemberByName(ArrayList<Member> members, String memberName) {
        Member foundMember = null;
        for (Member member : members) {
            if (memberName.equalsIgnoreCase(member.getName())) {
                foundMember = member;
            }
        }
        return foundMember;
    }
}



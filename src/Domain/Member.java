//@ Adam Lasson, @Johanne Riis-Weitling
package Domain;

public class Member {
    private String name;
    private String age;
    private boolean isActive; //e.g. Aktivt eller passivt medlem.


    public Member(String name, String age, boolean isActive) {
        this.name = name;
        this.age = age;
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

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

//TODO: disse to er ens hvordan skal de slås sammen?
    public String basisString() {
        return getName() + ";" + getAge() + ";" + getActive();

    }
    //TODO: disse to er ens hvordan skal de slås sammen?
    public String saveMember() {
        return basisString();

    }

   public String basisToString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktiv: " + isActive + '\n';
    }
//TODO: rette lidt til i toString, forvirre lidt på competitionSwimmer ?
    @Override
    public String toString() {
        return basisToString()+
                "----------------------------------------------" + '\n' + "";
    }

    public String getInvoiceLine() {
        if (getActive()) {
            return getName() + "; " + getAge() + "; Aktivt medlemskab";
        } else {
            return getName() + "; " + getAge() + "; Passivt medlemskab";
        }
    }

}



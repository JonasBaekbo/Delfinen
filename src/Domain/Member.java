//@ Adam Lasson, @Johanne Riis-Weitling, Mikkel Sandell
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

    public String getAge() {
        return age;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStringForSaving() {
        // TODO: omdøb i diagrammer
        return getName() + ";" + getAge() + ";" + getActive();
    }

    public String basisToString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktiv: " + isActive + '\n';
    }

    @Override
    public String toString() {
        return basisToString() + "----------------------------------------------\n";
    }

    public String getInvoiceLine() {
        if (getActive()) {
            return getName() + "; " + getAge() + "; Aktivt medlemskab";
        } else {
            return getName() + "; " + getAge() + "; Passivt medlemskab";
        }
    }
}



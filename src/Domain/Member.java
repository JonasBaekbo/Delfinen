//@ Adam Lasson, Johanne Riis-Weitling, Mikkel Sandell
package Domain;

import static java.lang.String.format;

public class Member {
    protected String name;
    protected String age;
    protected boolean isActive; //e.g. Aktivt eller passivt medlem.
    private DisciplineEnum discipline;

    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75; // der er 25% rabat for medlemmer over 60
    private double subscriptionFee;

    public Member(String name, String age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.discipline = null;
    }

    public String getName() {
        return name;
    }

    public DisciplineEnum getDiscipline() {
        return discipline;
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

    public String stringForSaving() {
        return getName() + ";" + getAge() + ";" + getActive();
    }

    public double getSubscriptionFee() {
        if (!getActive()) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubscriptionFee();
        }
        return subscriptionFee;
    }

    private double calculateSubscriptionFee() {
        int age = Integer.parseInt(getAge());

        if (age < 18) {
            subscriptionFee = below18Fee;
        } else if (age >= 60) {
            subscriptionFee = above18Fee * seniorFeeDiscount;
        } else {
            subscriptionFee = above18Fee;
        }

        return Math.round(subscriptionFee);
    }

    public String generateInvoiceLine(int invoiceNumber) {
        double amount = getSubscriptionFee();
        return invoiceNumber + ";" + getInvoiceLine() + ";" + Math.round(amount) + ";" + "ikke betalt";

    }

    public String getInvoiceLine() {
        if (getActive()) {
            return getName() + "; " + getAge() + "; Aktivt medlemskab";
        } else {
            return getName() + "; " + getAge() + "; Passivt medlemskab";
        }
    }

    @Override
    public String toString() {
        return "Medlemsnavn: " + name + '\n' +
                "Alder: " + age + '\n' +
                "Aktiv: " + isActive + '\n' +
                "----------------------------------------------\n";
    }

    public String informationToTable() {
        return format("%-20s %15s %-20s %15s %-20s %15s %-20s %15s", getName(), "|", getAge(), "|", getActive(), "|", getDiscipline(), "|");
    }

}
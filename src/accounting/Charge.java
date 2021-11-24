package accounting;

public class Charge {
    private String chargeNumber;
    private String name;
    private String age;
    private String activityLevel;
    private String amount;
    private String isPaid;

    public Charge(String chargeNumber,String name, String age, String activityLevel, String amount, String isPaid) {
        this.chargeNumber=chargeNumber;
        this.name = name;
        this.age = age;
        this.activityLevel = activityLevel;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    public String getName() {
        return this.name;
    }

    public String getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;

    }

    public String getChargeNumber() {
        return chargeNumber;
    }

    @Override
    public String toString() {
        return chargeNumber+";"+name + ";" + age + ";" + activityLevel + ";" + amount + ";" + isPaid;
    }
}

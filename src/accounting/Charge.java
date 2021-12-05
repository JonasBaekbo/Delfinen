package accounting;

public class Charge {
    private String chargeNumber;
    private String name;
    private String age;
    private String isActive;
    private String amount;
    private String isPaid;

    public Charge(String chargeNumber, String name, String age, String isActive, String amount, String isPaid) {
        this.chargeNumber = chargeNumber;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
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
        // Dan en streng som kan gemmes i en fil
        return chargeNumber + ";" + name + ";" + age + ";" + isActive + ";" + amount + ";" + isPaid;
    }
}

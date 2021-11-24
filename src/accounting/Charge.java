package accounting;

public class Charge {
    private String name;
    private String age;
    private String activityLevel;
    private String amount;
    private String isPaid;

    public Charge(String name, String age, String activityLevel, String amount, String isPaid) {
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

    @Override
    public String toString() {
        return name + ";" + age + ";" + activityLevel + ";" + amount + ";" + isPaid;
    }
}

package accounting;
import Member;

public class SubscriptionFee {
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75;


    public double getSubscriptionFee() {
        double subscriptionFee;
        if (!isMemberActive()) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubFee();
        }
        return subscriptionFee;

    }

    private boolean isMemberActive() {
        boolean active;
        if (getActivityLevel.equals("active")) {
            active = true;
        } else {
            active = false;
        }
        return active;
    }

    private double calculateSubFee() {
        double calculatedSubscriptionFee;
        int age = member.getAge;
        if (age < 18) {
            calculatedSubscriptionFee = below18Fee;
        } else if (age < 60) {
            calculatedSubscriptionFee = above18Fee;
        } else {
            calculatedSubscriptionFee = above18Fee * seniorFeeDiscount;
        }
        return calculatedSubscriptionFee;
    }
}


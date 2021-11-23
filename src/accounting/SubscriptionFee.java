//@Johanne Riis-Weitling
package accounting;

import Domain.Member;

import java.util.ArrayList;

public class SubscriptionFee {
    private Member member;
    private ArrayList<Member> memberArrayList = new ArrayList<>();
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75;
    double subscriptionFee;


    public SubscriptionFee(Member member) {
        this.member = member;
    }

    public double getSubscriptionFee() {
        if (!isMemberActive()) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubFee();
        }
        return subscriptionFee;

    }

    private boolean isMemberActive() {
        boolean isActive;
        if (getActivityLevel().equals("active")) {
            isActive = true;
        } else {
            isActive = false;
        }
        return isActive;
    }

    private double calculateSubFee() {
        int age = getAge();
        if (age < 18) {
            subscriptionFee = below18Fee;
        } else if (age >= 60) {
            subscriptionFee = above18Fee * seniorFeeDiscount;
        } else {
            subscriptionFee = above18Fee;
        }

        return subscriptionFee;
    }

    private int getAge() {
        int age = Integer.parseInt(member.getAge());
        return age;
    }


    private String getActivityLevel() {
        return member.getActivityLevel();
    }

    public double calculateTotalSubscription() {
        double totalSubscription = 0;
        double under18SubTotal;
        double above18SubTotal;
        double over60SubTotal;

        for (Member member : memberArrayList){
        totalSubscription += getSubscriptionFee(member)
        }
        return totalSubscription;

    }


    @Override
    public String toString() {
        return "Medlem: " + member + ".\nKontingent: " + getSubscriptionFee();
    }
}


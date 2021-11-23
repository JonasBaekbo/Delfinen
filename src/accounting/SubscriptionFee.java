//@Johanne Riis-Weitling
package accounting;

import Domain.Member;

import java.util.ArrayList;

public class SubscriptionFee {
    private Member member;
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75;
    double subscriptionFee;



    public double getSubscriptionFee(Member member) {
        if (!isMemberActive(member)) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubFee(member);
        }
        return subscriptionFee;

    }

    private boolean isMemberActive(Member member) {
        boolean isActive;
        if (member.getActivityLevel().equals("Aktivt")) {
            isActive = true;
        } else {
            isActive = false;
        }
        return isActive;
    }

    private double calculateSubFee(Member member) {
        int age = Integer.parseInt(member.getAge());
        if (age < 18) {
            subscriptionFee = below18Fee;
        } else if (age >= 60) {
            subscriptionFee = above18Fee * seniorFeeDiscount;
        } else {
            subscriptionFee = above18Fee;
        }

        return subscriptionFee;
    }

    public String calculateTotalSubscription(ArrayList<Member> memberArrayList) {
        double totalSubscription = 0;
        double under18SubTotal;
        double above18SubTotal;
        double over60SubTotal;

        for (Member member : memberArrayList){
        totalSubscription += getSubscriptionFee(member);
        }
        return Double.toString(totalSubscription);

    }


    @Override
    public String toString() {
        return "Medlem: " + member + ".\nKontingent: " + getSubscriptionFee(member);
    }
}


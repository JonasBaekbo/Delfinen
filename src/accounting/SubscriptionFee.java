//@Johanne Riis-Weitling
package accounting;

import Domain.Member;

import java.util.ArrayList;

public class SubscriptionFee {
    private Member member;
    private double below18Fee = 1000;
    private double above18Fee = 1600;
    private double passiveFee = 500;
    private double seniorFeeDiscount = 0.75;//der er 25% rabat for medlemmer over 60
    double subscriptionFee;

    public double getSubscriptionFee(Member member) {
        if (!isMemberActive(member)) {
            subscriptionFee = passiveFee;
        } else {
            subscriptionFee = calculateSubscriptionFee(member);
        }
        return subscriptionFee;
    }

    private boolean isMemberActive(Member member) {
        boolean isActive;
        isActive = member.getActivityLevel().equals("Aktivt");
        return isActive;
    }

    private double calculateSubscriptionFee(Member member) {
        int age = getAgeAsInt(member);
        if (age < 18) {
            subscriptionFee = below18Fee;
        } else if (age >= 60) {
            subscriptionFee = above18Fee * seniorFeeDiscount;
        } else {
            subscriptionFee = above18Fee;
        }
        return subscriptionFee;
    }

    public String calculateExpectedSubFeeTotal(ArrayList<Member> memberArrayList) {
        double totalSubscription = 0;

        for (Member member : memberArrayList){
            totalSubscription += getSubscriptionFee(member);
        }
        return Double.toString(totalSubscription);
    }

        public int getAgeAsInt(Member member){
            return Integer.parseInt(member.getAge());
        }


    @Override
    public String toString() {
        return "Medlem: " + member + ".\nKontingent: " + getSubscriptionFee(member);
    }
}


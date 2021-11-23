//@Johanne Riis-Weitling
package accounting;

import Domain.Member;

public class SubscriptionFee {
    private Member member;

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
        if(getActivityLevel().equals("active")){
        isActive=true;}
        else{isActive=false;}
       return isActive;
    }

    private double calculateSubFee() {
        int age = getAge();
          if(age<18){
                subscriptionFee=below18Fee;}
            else if (age>=60){
              subscriptionFee=above18Fee*seniorFeeDiscount;}
            else{ subscriptionFee= above18Fee;}

            return subscriptionFee;
        }

    private int getAge() {
       int age = Integer.parseInt(member.getAge());
       return age;
    }

    private String getActivityLevel(){
        return member.getActivityLevel();
    }

    @Override
    public String toString() {
        return "Medlem: " + member + ".\nKontingent: "+ getSubscriptionFee();
    }
}


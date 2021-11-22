package accounting;

import Domain.Member;

public class SubscriptionFee {
    private double below18Fee = 1000;
    private  double above18Fee= 1600;
    private double passiveFee=500;
    private double seniorFeeDiscount = 0.75;



    public double getsubscriptionFee() {
        double subscribtionFee;
        if (!isMemberActive()) {
            subscribtionFee = passiveFee;
        } else {
            subscribtionFee = calculateSubFee();
        }
        return subscribtionFee;

    }

    private boolean isMemberActive() {
        return true;
       // return Member.getActivityLevel();
    }

    private double calculateSubFee() {
        double subscribtionFee;
       /* int age = Member.getAge();
          if(age<=18){
                subscribtionFee=below18Fee;}
            else if ((age>18) && (age<60)){
                subscribtionFee= above18Fee;}
            else{
                subscribtionFee=above18Fee*seniorFeeDiscount;
            }
            return subscribtionFee;*/
        return 0;
        }
        }
    /*}


}*/

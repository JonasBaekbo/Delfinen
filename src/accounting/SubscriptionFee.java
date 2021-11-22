package accounting;

import Domain.Member;

public class SubscriptionFee {
    private double below18Fee = 1000;
    private  double above18Fee= 1600;
    private double passiveFee=500;
    private double seniorFeeDiscount = 0.75;



    public double getSubscriptionFee() {
        double subscribtionFee;
        if (!isMemberActive()) {
            subscribtionFee = passiveFee;
        } else {
            subscribtionFee = calculateSubFee();
        }
        return subscribtionFee;

    }

    private boolean isMemberActive() {
        boolean isActive;
        if(getActivityLevel().equals("active")){
        isActive=true;}
        else{isActive=false;}
       return isActive;
    }

    private double calculateSubFee() {
        double subscribtionFee;
      int age = getAge();
          if(age<=18){
                subscribtionFee=below18Fee;}
            else if (age<60){
                subscribtionFee= above18Fee;}
            else{
                subscribtionFee=above18Fee*seniorFeeDiscount;
            }
            return subscribtionFee;
        }

    private int getAge() {
       //return Member.getAge();
       // int age = Integer.parseInt(Member.getAge);
        //TODO: fjern hardcoded return
        return 16;

    }
    private String getActivityLevel(){
        //return Member.getActivityLevel();
        //TODO: fjern hardcoded return
        return "active";
    }
}


package accounting;

public class SubscriptionFee {
    private double below18Fee = 1000;
    private  double above18Fee= 1600;
    private double passiveFee=500;
    private double seniorFeeDiscount = 0.75;



    public double getsubscriptionFee(){
        double subscribtionFee;
        if (getActivityLevel.equals("pasive")){
            subscribtionFee=passiveFee;}
        else if(getAge<=18){
            subscribtionFee=below18Fee;}
        else if (getAge>18&&getAge<60){
            subscribtionFee= above18Fee;}
        else{
            subscribtionFee=above18Fee*seniorFeeDiscount;
        }
        return subscribtionFee;
    }


}

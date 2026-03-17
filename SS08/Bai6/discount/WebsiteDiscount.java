package Bai6.discount;

public class WebsiteDiscount implements DiscountStrategy {
    public double applyDiscount(double amount){
        double d=amount*0.10;
        System.out.println("Áp dụng giảm giá 10%: "+(long)d);
        return amount-d;
    }
}

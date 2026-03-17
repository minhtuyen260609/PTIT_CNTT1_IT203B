package Bai6.discount;

public class FirstTimeDiscount implements DiscountStrategy {
    public double applyDiscount(double amount){
        double d=amount*0.15;
        System.out.println("Áp dụng giảm giá 15% (lần đầu): "+(long)d);
        return amount-d;
    }
}

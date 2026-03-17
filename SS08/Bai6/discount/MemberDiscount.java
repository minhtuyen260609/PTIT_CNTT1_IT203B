package Bai6.discount;

public class MemberDiscount implements DiscountStrategy {
    public double applyDiscount(double amount){
        double d=amount*0.05;
        System.out.println("Áp dụng giảm giá 5% (thành viên): "+(long)d);
        return amount-d;
    }
}

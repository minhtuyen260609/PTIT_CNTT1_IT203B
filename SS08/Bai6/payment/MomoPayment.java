package Bai6.payment;

public class MomoPayment implements PaymentMethod {
    public void pay(double amount){
        System.out.println("Xử lý thanh toán MoMo: "+(long)amount);
    }
}

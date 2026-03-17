package Bai6;

import Bai6.discount.DiscountStrategy;
import Bai6.factory.SalesChannelFactory;
import Bai6.notification.NotificationService;
import Bai6.payment.PaymentMethod;

public class OrderService{
    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;
    public OrderService(SalesChannelFactory factory){
        discount=factory.createDiscountStrategy();
        payment=factory.createPaymentMethod();
        notification=factory.createNotificationService();
    }
    public void checkout(String product,double price,int qty){
        double total=price*qty;
        double finalAmount=discount.applyDiscount(total);
        payment.pay(finalAmount);
        notification.notifyUser();
    }
}

package Bai6.factory;

import Bai6.discount.DiscountStrategy;
import Bai6.discount.FirstTimeDiscount;
import Bai6.notification.NotificationService;
import Bai6.notification.PushNotification;
import Bai6.payment.MomoPayment;
import Bai6.payment.PaymentMethod;

public class MobileAppFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy(){return new FirstTimeDiscount();}
    public PaymentMethod createPaymentMethod(){return new MomoPayment();}
    public NotificationService createNotificationService(){return new PushNotification();}
}

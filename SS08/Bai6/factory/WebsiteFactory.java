package Bai6.factory;

import Bai6.discount.DiscountStrategy;
import Bai6.discount.WebsiteDiscount;
import Bai6.notification.EmailNotification;
import Bai6.notification.NotificationService;
import Bai6.payment.CreditCardPayment;
import Bai6.payment.PaymentMethod;

public class WebsiteFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy(){return new WebsiteDiscount();}
    public PaymentMethod createPaymentMethod(){return new CreditCardPayment();}
    public NotificationService createNotificationService(){return new EmailNotification();}
}

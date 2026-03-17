package Bai6.factory;

import Bai6.discount.DiscountStrategy;
import Bai6.discount.MemberDiscount;
import Bai6.notification.NotificationService;
import Bai6.notification.PrintReceipt;
import Bai6.payment.CODPayment;
import Bai6.payment.PaymentMethod;

public class POSFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy(){return new MemberDiscount();}
    public PaymentMethod createPaymentMethod(){return new CODPayment();}
    public NotificationService createNotificationService(){return new PrintReceipt();}
}


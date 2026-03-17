package Bai6.factory;

import Bai6.discount.DiscountStrategy;
import Bai6.notification.NotificationService;
import Bai6.payment.PaymentMethod;

public interface SalesChannelFactory{
    DiscountStrategy createDiscountStrategy();
    PaymentMethod createPaymentMethod();
    NotificationService createNotificationService();
}

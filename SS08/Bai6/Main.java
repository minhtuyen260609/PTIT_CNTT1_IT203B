package Bai6;

import Bai6.factory.MobileAppFactory;
import Bai6.factory.POSFactory;
import Bai6.factory.SalesChannelFactory;
import Bai6.factory.WebsiteFactory;

import java.util.*;

public class Main{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("\n1.Website");
            System.out.println("2.Mobile App");
            System.out.println("3.POS");
            System.out.println("4.Thoát");
            System.out.print("Chọn kênh: ");
            int c=sc.nextInt();
            if(c==4) return;
            SalesChannelFactory factory=null;
            if(c==1){factory=new WebsiteFactory();System.out.println("Bạn đã chọn kênh Website");}
            else if(c==2){factory=new MobileAppFactory();System.out.println("Bạn đã chọn kênh Mobile App");}
            else if(c==3){factory=new POSFactory();System.out.println("Bạn đã chọn kênh POS");}
            if(factory==null) continue;
            sc.nextLine();
            System.out.print("Sản phẩm: ");
            String name=sc.nextLine();
            System.out.print("Giá: ");
            double price=sc.nextDouble();
            System.out.print("Số lượng: ");
            int qty=sc.nextInt();
            OrderService order=new OrderService(factory);
            order.checkout(name,price,qty);
        }
    }
}

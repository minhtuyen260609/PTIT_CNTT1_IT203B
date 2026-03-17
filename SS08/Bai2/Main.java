package Bai2;

import java.util.*;
public class Main{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        SmartHomeFacade facade=new SmartHomeFacade();
        TemperatureSensor sensor=new ThermometerAdapter(new OldThermometer());
        while(true){
            System.out.println("\n1.Xem nhiệt độ");
            System.out.println("2.Chế độ rời nhà");
            System.out.println("3.Chế độ ngủ");
            System.out.println("4.Thoát");
            System.out.print("Chọn: ");
            int c=sc.nextInt();
            switch(c){
                case 1:
                    double t=facade.getCurrentTemperature(sensor);
                    System.out.printf("Nhiệt độ hiện tại: %.1f°C\n",t);
                    break;
                case 2:
                    facade.leaveHome();
                    break;
                case 3:
                    facade.sleepMode();
                    break;
                case 4:
                    return;
            }
        }
    }
}

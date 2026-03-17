package Bai5;

import java.util.*;

public class Main{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        Light light=new Light();
        Fan fan=new Fan();
        AirConditioner ac=new AirConditioner();
        TemperatureSensor sensor=new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);
        Command sleep=new SleepModeCommand(light,fan,ac);
        while(true){
            System.out.println("\n1.Kích hoạt chế độ ngủ");
            System.out.println("2.Thay đổi nhiệt độ");
            System.out.println("3.Xem trạng thái thiết bị");
            System.out.println("4.Thoát");
            System.out.print("Chọn: ");
            int c=sc.nextInt();
            switch(c){
                case 1:
                    sleep.execute();
                    break;
                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int t=sc.nextInt();
                    sensor.setTemperature(t);
                    break;
                case 3:
                    System.out.println(light.status());
                    System.out.println(fan.status());
                    System.out.println(ac.status());
                    break;
                case 4:
                    return;
            }
        }
    }
}

package Bai1;

import java.util.*;
public class Bai{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        List<Device>devices=new ArrayList<>();
        HardwareConnection connection=null;
        while(true){
            System.out.println("\n1.Kết nối phần cứng");
            System.out.println("2.Tạo thiết bị mới");
            System.out.println("3.Bật thiết bị");
            System.out.println("4.Tắt thiết bị");
            System.out.println("5.Thoát");
            System.out.print("Chọn: ");
            int c=sc.nextInt();
            switch(c){
                case 1:
                    connection=HardwareConnection.getInstance();
                    connection.connect();
                    break;
                case 2:
                    System.out.println("Chọn loại: 1.Đèn 2.Quạt 3.Điều hòa");
                    int t=sc.nextInt();
                    DeviceFactory f=null;
                    if(t==1) f=new LightFactory();
                    else if(t==2) f=new FanFactory();
                    else if(t==3) f=new AirConditionerFactory();
                    if(f!=null) devices.add(f.createDevice());
                    break;
                case 3:
                    if(devices.isEmpty()) break;
                    System.out.print("Chọn thiết bị: ");
                    int on=sc.nextInt();
                    if(on>0&&on<=devices.size()) devices.get(on-1).turnOn();
                    break;
                case 4:
                    if(devices.isEmpty()) break;
                    System.out.print("Chọn thiết bị: ");
                    int off=sc.nextInt();
                    if(off>0&&off<=devices.size()) devices.get(off-1).turnOff();
                    break;
                case 5:
                    return;
            }
        }
    }
}
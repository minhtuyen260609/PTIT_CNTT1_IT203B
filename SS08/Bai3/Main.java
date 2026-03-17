package Bai3;

import java.util.*;

public class Main{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        RemoteControl remote=new RemoteControl();
        Light light=new Light();
        Fan fan=new Fan();
        AirConditioner ac=new AirConditioner();
        while(true){
            System.out.println("\n1.Gán nút");
            System.out.println("2.Nhấn nút");
            System.out.println("3.Undo");
            System.out.println("4.Thoát");
            System.out.print("Chọn: ");
            int c=sc.nextInt();
            switch(c){
                case 1:
                    System.out.print("Chọn nút: ");
                    int slot=sc.nextInt();
                    System.out.println("1.Bật đèn 2.Tắt đèn 3.Bật quạt 4.Tắt quạt 5.Set điều hòa");
                    int type=sc.nextInt();
                    Command cmd=null;
                    if(type==1) cmd=new LightOnCommand(light);
                    else if(type==2) cmd=new LightOffCommand(light);
                    else if(type==3) cmd=new FanOnCommand(fan);
                    else if(type==4) cmd=new FanOffCommand(fan);
                    else if(type==5){
                        System.out.print("Nhiệt độ: ");
                        int t=sc.nextInt();
                        cmd=new ACSetTemperatureCommand(ac,t);
                    }
                    if(cmd!=null) remote.setCommand(slot,cmd);
                    break;
                case 2:
                    System.out.print("Nhấn nút: ");
                    int s=sc.nextInt();
                    remote.pressButton(s);
                    break;
                case 3:
                    System.out.println("Undo:");
                    remote.undo();
                    break;
                case 4:
                    return;
            }
        }
    }
}

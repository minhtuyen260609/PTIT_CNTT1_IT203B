package Bai5;

public class SleepModeCommand implements Command{
    private Light light;
    private Fan fan;
    private AirConditioner ac;
    public SleepModeCommand(Light l,Fan f,AirConditioner a){
        light=l;fan=f;ac=a;
    }
    public void execute(){
        System.out.println("SleepMode: Tắt đèn");
        System.out.println("SleepMode: Điều hòa set 28°C");
        System.out.println("SleepMode: Quạt tốc độ thấp");
        light.off();
        ac.setTemperature(28);
        fan.setLow();
    }
}

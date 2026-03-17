package Bai5;

public class AirConditioner implements Observer{
    private int temperature=25;
    public void setTemperature(int t){
        temperature=t;
        System.out.println("Điều hòa: Nhiệt độ = "+temperature);
    }
    public void update(int t){
        if(t>30){
            System.out.println("Điều hòa: Nhiệt độ = "+temperature+" (vẫn giữ)");
        }
    }
    public String status(){
        return "Điều hòa: "+temperature+"°C";
    }
}

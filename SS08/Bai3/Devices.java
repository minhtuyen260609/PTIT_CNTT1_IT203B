package Bai3;

class Light{
    public void on(){System.out.println("Đèn: Bật");}
    public void off(){System.out.println("Đèn: Tắt");}
}

class Fan{
    public void on(){System.out.println("Quạt: Bật");}
    public void off(){System.out.println("Quạt: Tắt");}
}

class AirConditioner{
    private int temperature=25;
    public void setTemperature(int t){
        temperature=t;
        System.out.println("Điều hòa: Nhiệt độ = "+temperature);
    }
    public int getTemperature(){return temperature;}
}

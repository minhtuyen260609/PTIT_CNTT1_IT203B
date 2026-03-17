package Bai2;

class SmartHomeFacade{
    public void leaveHome(){
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Tắt quạt");
        System.out.println("FACADE: Tắt điều hòa");
    }
    public void sleepMode(){
        System.out.println("FACADE: Tắt đèn");
        System.out.println("FACADE: Điều hòa set 28°C");
        System.out.println("FACADE: Quạt chạy tốc độ thấp");
    }
    public double getCurrentTemperature(TemperatureSensor sensor){
        return sensor.getTemperatureCelsius();
    }
}

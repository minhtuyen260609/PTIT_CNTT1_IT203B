package Bai2;

class ThermometerAdapter implements TemperatureSensor{
    private OldThermometer oldThermometer;
    public ThermometerAdapter(OldThermometer oldThermometer){
        this.oldThermometer=oldThermometer;
    }
    public double getTemperatureCelsius(){
        int f=oldThermometer.getTemperatureFahrenheit();
        return (f-32)*5.0/9.0;
    }
}

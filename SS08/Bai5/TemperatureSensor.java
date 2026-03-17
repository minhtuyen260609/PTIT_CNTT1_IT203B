package Bai5;

import java.util.*;

public class TemperatureSensor implements Subject{
    private List<Observer>observers=new ArrayList<>();
    private int temperature;
    public void attach(Observer o){observers.add(o);}
    public void detach(Observer o){observers.remove(o);}
    public void notifyObservers(){
        for(Observer o:observers) o.update(temperature);
    }
    public void setTemperature(int t){
        temperature=t;
        System.out.println("Cảm biến: Nhiệt độ = "+temperature);
        notifyObservers();
    }
}

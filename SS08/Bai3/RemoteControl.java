package Bai3;

import java.util.*;

class LightOnCommand implements Command{
    private Light light;
    public LightOnCommand(Light l){light=l;}
    public void execute(){light.on();}
    public void undo(){light.off();}
    public String toString(){return "LightOnCommand";}
}

class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light l){light=l;}
    public void execute(){light.off();}
    public void undo(){light.on();}
    public String toString(){return "LightOffCommand";}
}

class FanOnCommand implements Command{
    private Fan fan;
    public FanOnCommand(Fan f){fan=f;}
    public void execute(){fan.on();}
    public void undo(){fan.off();}
    public String toString(){return "FanOnCommand";}
}

class FanOffCommand implements Command{
    private Fan fan;
    public FanOffCommand(Fan f){fan=f;}
    public void execute(){fan.off();}
    public void undo(){fan.on();}
    public String toString(){return "FanOffCommand";}
}

class ACSetTemperatureCommand implements Command{
    private AirConditioner ac;
    private int newTemp;
    private int oldTemp;
    public ACSetTemperatureCommand(AirConditioner ac,int t){
        this.ac=ac;newTemp=t;
    }
    public void execute(){
        oldTemp=ac.getTemperature();
        ac.setTemperature(newTemp);
    }
    public void undo(){
        ac.setTemperature(oldTemp);
        System.out.println("Undo: Điều hòa: Nhiệt độ = "+oldTemp);
    }
    public String toString(){return "ACSetTempCommand("+newTemp+")";}
}

class RemoteControl{
    private Map<Integer,Command>slots=new HashMap<>();
    private Stack<Command>history=new Stack<>();
    public void setCommand(int slot,Command cmd){
        slots.put(slot,cmd);
        System.out.println("Đã gán "+cmd+" cho nút "+slot);
    }
    public void pressButton(int slot){
        Command cmd=slots.get(slot);
        if(cmd!=null){
            cmd.execute();
            history.push(cmd);
        }
    }
    public void undo(){
        if(!history.isEmpty()){
            Command cmd=history.pop();
            cmd.undo();
        }
    }
}

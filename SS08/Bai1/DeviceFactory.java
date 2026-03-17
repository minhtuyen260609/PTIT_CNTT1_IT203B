package Bai1;

abstract class DeviceFactory{
    abstract Device createDevice();
}

class LightFactory extends DeviceFactory{
    Device createDevice(){
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}

class FanFactory extends DeviceFactory{
    Device createDevice(){
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}

class AirConditionerFactory extends DeviceFactory{
    Device createDevice(){
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}

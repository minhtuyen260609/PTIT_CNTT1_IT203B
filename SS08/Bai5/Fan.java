package Bai5;

public class Fan implements Observer{
    private String speed="Tắt";
    public void setLow(){
        speed="Thấp";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }
    public void setHigh(){
        speed="Mạnh";
        System.out.println("Quạt: Chạy tốc độ mạnh");
    }
    public void update(int t){
        if(t>30){
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
            speed="Mạnh";
        }
    }
    public String status(){
        return "Quạt: "+speed;
    }
}

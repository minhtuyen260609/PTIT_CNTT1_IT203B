package Bai4;

public class Fan implements Observer{
    public void update(int t){
        if(t<20) System.out.println("Quạt: Nhiệt độ thấp, tự động TẮT");
        else if(t<=25) System.out.println("Quạt: Nhiệt độ trung bình, chạy tốc độ vừa");
        else System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
    }
}

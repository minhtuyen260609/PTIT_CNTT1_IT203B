import java.util.Scanner;
public class Bai1{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        try{
            System.out.print("Nhập năm sinh: ");
            String input=sc.nextLine();
            int birthYear=Integer.parseInt(input);
            int age=2026-birthYear;
            System.out.println("Tuổi của bạn là: "+age);
        }catch(NumberFormatException e){
            System.out.println("Lỗi: Vui lòng nhập năm sinh bằng số.");
        }finally{
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
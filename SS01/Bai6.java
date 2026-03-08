import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class InvalidAgeExceptions extends Exception{
    public InvalidAgeExceptions(String msg){
        super(msg);
    }
}

class Logger{
    public static void logError(String msg){
        DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("[ERROR] "+LocalDateTime.now().format(f)+" - "+msg);
    }
}

class Userss{
    private String name;
    private int age;

    public void setName(String name){
        this.name=name;
    }

    public void setAge(int age) throws InvalidAgeException{
        if(age<0){
            throw new InvalidAgeException("Tuổi không thể âm!");
        }
        this.age=age;
    }

    public void printUser(){
        if(name!=null){
            System.out.println("Tên người dùng: "+name);
        }else{
            System.out.println("Tên chưa được thiết lập.");
        }
        System.out.println("Tuổi: "+age);
    }
}

public class Bai6{

    public static void saveToFile() throws IOException{
        throw new IOException("Không thể ghi file!");
    }

    public static void processUserData() throws IOException{
        saveToFile();
    }

    public static void main(String[]args){

        Scanner sc=new Scanner(System.in);

        Userss u=new Userss();
        u.setName("Minh");

        try{
            System.out.print("Nhập năm sinh: ");
            String input=sc.nextLine();
            int birthYear=Integer.parseInt(input);
            int age=2026-birthYear;
            u.setAge(age);
        }catch(NumberFormatException e){
            Logger.logError("Năm sinh không hợp lệ.");
        }catch(InvalidAgeException e){
            Logger.logError(e.getMessage());
        }

        u.printUser();

        try{
            System.out.print("Nhập tổng số người: ");
            int total=sc.nextInt();
            System.out.print("Nhập số nhóm: ");
            int groups=sc.nextInt();
            int result=total/groups;
            System.out.println("Mỗi nhóm có "+result+" người.");
        }catch(ArithmeticException e){
            Logger.logError("Không thể chia cho 0!");
        }

        try{
            processUserData();
        }catch(IOException e){
            Logger.logError(e.getMessage());
        }

        sc.close();
        System.out.println("Chương trình kết thúc an toàn.");
    }
}
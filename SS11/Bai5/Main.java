package Bai5;

import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        DoctorDAO dao=new DoctorDAO();
        while(true){
            System.out.println("1.Xem DS bác sĩ");
            System.out.println("2.Thêm bác sĩ");
            System.out.println("3.Thống kê chuyên khoa");
            System.out.println("4.Thoát");
            int ch=Integer.parseInt(sc.nextLine());
            switch(ch){
                case 1:
                    for(Doctor d:dao.findAll()){
                        System.out.println(d.getId()+" | "+d.getName()+" | "+d.getSpecialty());
                    }
                    break;
                case 2:
                    System.out.print("Mã: ");String id=sc.nextLine();
                    System.out.print("Tên: ");String name=sc.nextLine();
                    System.out.print("Chuyên khoa: ");String sp=sc.nextLine();
                    try{
                        dao.insert(new Doctor(id,name,sp));
                        System.out.println("Thêm thành công");
                    }catch(Exception e){
                        System.out.println("Lỗi: "+e.getMessage());
                    }
                    break;
                case 3:
                    dao.stats();
                    break;
                case 4:
                    return;
            }
        }
    }
}

class InvalidAgeException extends Exception{
    public InvalidAgeException(String msg){
        super(msg);
    }
}

class Users{
    private int age;

    public void setAge(int age) throws InvalidAgeException{
        if(age<0){
            throw new InvalidAgeException("Tuổi không thể âm!");
        }
        this.age=age;
    }

    public int getAge(){
        return age;
    }
}

public class Bai5{
    public static void main(String[]args){
        Users u=new Users();
        try{
            u.setAge(-5);
            System.out.println("Tuổi: "+u.getAge());
        }catch(InvalidAgeException e){
            System.out.println("Lỗi nghiệp vụ: "+e.getMessage());
        }
        System.out.println("Chương trình vẫn tiếp tục chạy...");
    }
}
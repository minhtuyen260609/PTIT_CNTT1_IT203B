@FunctionalInterface
interface Authenticatable{
    String getPassword();

    default boolean isAuthenticated(){
        return getPassword()!=null&&!getPassword().isEmpty();
    }

    static String encrypt(String rawPassword){
        return "ENC_"+rawPassword;
    }
}

class Users implements Authenticatable{
    String password;
    Users(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
}

public class Bai3{
    public static void main(String[]args){
        Users u1=new Users("123456");
        Users u2=new Users("");

        System.out.println(u1.isAuthenticated());
        System.out.println(u2.isAuthenticated());

        System.out.println(Authenticatable.encrypt("mypassword"));
    }
}
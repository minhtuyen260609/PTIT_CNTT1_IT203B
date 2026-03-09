@FunctionalInterface
interface UserProcessor{
    String process(User u);
}

class User{
    String username;
    User(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
}

class UserUtils{
    public static String convertToUpperCase(User u){
        return u.getUsername().toUpperCase();
    }
}

public class Bai6{
    public static void main(String[]args){
        UserProcessor processor=UserUtils::convertToUpperCase;

        User user=new User("minh");

        System.out.println(processor.process(user));
    }
}
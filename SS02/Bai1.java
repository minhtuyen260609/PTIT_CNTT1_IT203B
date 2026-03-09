import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

class User{
    String username;
    String role;
    User(String username,String role){
        this.username=username;
        this.role=role;
    }
    public String getUsername(){
        return username;
    }
    public String getRole(){
        return role;
    }
    public String toString(){
        return "Username: "+username+", Role: "+role;
    }
}

public class Bai1{
    public static void main(String[]args){
        Predicate<User>isAdmin=user->user.getRole().equals("ADMIN");
        Function<User,String>getUsername=user->user.getUsername();
        Consumer<User>printUser=user->System.out.println(user);
        Supplier<User>createUser=()->new User("guest","USER");

        User u1=new User("minh","ADMIN");
        User u2=createUser.get();

        System.out.println(isAdmin.test(u1));
        System.out.println(getUsername.apply(u1));
        printUser.accept(u1);
        printUser.accept(u2);
    }
}